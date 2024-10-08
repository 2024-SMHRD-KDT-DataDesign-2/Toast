package com.Toast.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Toast.model.commentDAO;
import com.Toast.model.reviewDTO;

@WebServlet("/CommentDeleteService")
public class CommentDeleteService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		int comment_id = Integer.parseInt(request.getParameter("comment_id"));
		
		commentDAO dao = new commentDAO();
		int row = dao.deleteComment(comment_id);
		
		if (row > 0) {
			System.out.println("댓글 삭제 성공!");
		} else {
			System.out.println("댓글 삭제 실패 ㅜㅜ");
		}
		
		HttpSession session = request.getSession();
		reviewDTO reviewDetail = (reviewDTO)(session.getAttribute("reviewDetail"));
		int review_idx = reviewDetail.getReview_idx();
		response.sendRedirect("ReviewDetailService?review_idx="+review_idx);
	
	}

}
