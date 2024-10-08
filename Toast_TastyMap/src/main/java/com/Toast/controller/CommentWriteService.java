package com.Toast.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Toast.model.commentDAO;
import com.Toast.model.commentDTO;
import com.Toast.model.memberDTO;

@WebServlet("/CommentWriteService")
public class CommentWriteService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        memberDTO info = (memberDTO) session.getAttribute("info");

        if (info == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int review_idx = Integer.parseInt(request.getParameter("review_idx"));
        String comment_content = request.getParameter("comment_content");
        commentDTO comment = new commentDTO();
        comment.setMem_id(info.getMem_id());
        comment.setReview_idx(review_idx);
        comment.setComment_content(comment_content);

        commentDAO commentDao = new commentDAO();
        int result = commentDao.insertComment(comment);
        
        if (result > 0) {
            response.sendRedirect("ReviewDetailService?review_idx=" + review_idx);
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
