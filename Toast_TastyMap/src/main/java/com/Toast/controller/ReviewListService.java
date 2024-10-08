package com.Toast.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Toast.model.reviewDAO;
import com.Toast.model.reviewDTO;

@WebServlet("/ReviewListService")
public class ReviewListService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int placeIdx = Integer.parseInt(request.getParameter("place_idx"));
		
		reviewDAO dao = new reviewDAO();
		List<reviewDTO> placeReviews = dao.getPlaceReviews(placeIdx);
		int countReview = dao.countReviewByIdx(placeIdx);
		
		request.getSession().setAttribute("placeReviews", placeReviews);
		request.getSession().setAttribute("countReview", countReview);
		response.sendRedirect("reviewList.jsp");
	
	}

}
