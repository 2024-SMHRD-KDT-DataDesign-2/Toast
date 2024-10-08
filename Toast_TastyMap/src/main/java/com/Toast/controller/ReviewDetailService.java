package com.Toast.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import com.Toast.model.commentDAO;
import com.Toast.model.commentDTO;
import com.Toast.model.placeDTO;
import com.Toast.model.reviewDAO;
import com.Toast.model.reviewDTO;

// 특정 리뷰의 상세 정보 조회
@WebServlet("/ReviewDetailService")
public class ReviewDetailService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	int review_idx = Integer.parseInt(request.getParameter("review_idx"));
    	HttpSession session = request.getSession();
    	placeDTO place = (placeDTO)session.getAttribute("place");
    	
        reviewDAO dao = new reviewDAO();
        reviewDTO review = dao.getReviewById(review_idx);
        String place_name = dao.getPlaceNameByReviewIdx(review_idx);
        String mem_nick = dao.getMemNickByReviewIdx(review_idx);
        
        commentDAO Cdao = new commentDAO();
        List<commentDTO> comments = Cdao.getCommentsByReview(review_idx);
        List<String> memImg = Cdao.getCommentsMemImg(review_idx);
        
        System.out.println("memImg: "+memImg);
        
        session.setAttribute("reviewDetail", review);
        session.setAttribute("review_place_name", place_name);
        session.setAttribute("review_mem_nick", mem_nick);
        session.setAttribute("comments", comments);
        session.setAttribute("memImg", memImg);
        session.setAttribute("place_idx_err", review.getPlace_idx());

        response.sendRedirect("reviewDetail.jsp");
    }
}
