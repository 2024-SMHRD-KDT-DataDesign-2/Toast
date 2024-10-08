package com.Toast.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Toast.model.reviewDAO;
import com.Toast.model.reviewDTO;

@WebServlet("/ReviewDeleteService")
public class ReviewDeleteService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		reviewDTO reviewDetail = (reviewDTO)session.getAttribute("reviewDetail");
		int place_idx = reviewDetail.getPlace_idx();
		
		reviewDAO dao = new reviewDAO();
		int row = dao.deleteReview(reviewDetail);
		
		if (row > 0) {
			System.out.println("리뷰 삭제 성공!");
			response.sendRedirect("ReviewListService?place_idx="+place_idx);
		} else {
			System.out.println("리뷰 삭제 실패 ㅜㅜ");
			response.sendRedirect("ReviewDetailService?review_idx="+reviewDetail.getReview_idx());
		}
	
	}

}
