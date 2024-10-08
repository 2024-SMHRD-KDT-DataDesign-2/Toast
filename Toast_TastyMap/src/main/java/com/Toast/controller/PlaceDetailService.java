package com.Toast.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Toast.model.menuDAO;
import com.Toast.model.menuDTO;
import com.Toast.model.placeDAO;
import com.Toast.model.placeDTO;
import com.Toast.model.reviewDAO;

@WebServlet("/PlaceDetailService")
public class PlaceDetailService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("filterState");
		
		int placeIdx = 0;
		if (request.getParameter("place_idx") != null) {
			placeIdx = Integer.parseInt(request.getParameter("place_idx"));
		} else {
			placeIdx = (int)session.getAttribute("place_idx_err");
		}
		
		placeDAO Pdao = new placeDAO();
		menuDAO Mdao = new menuDAO();
		reviewDAO Rdao = new reviewDAO();
		
		placeDTO Pdto_place = Pdao.getPlaceByIdx(placeIdx);
		String Pdto_isOpen = Pdao.isOpen(placeIdx);
		String Pdto_closeTime = Pdao.closeTime(placeIdx);
		List<menuDTO> Mdto = Mdao.getMenuByIdx(placeIdx);
		int Rdto = Rdao.countReviewByIdx(placeIdx);
		double avg_ratings = Rdao.getAvgRatings(placeIdx);
		
		if (Pdto_place != null) {
			session.setAttribute("place", Pdto_place);
			session.setAttribute("isOpen", Pdto_isOpen);
			session.setAttribute("closeTime", Pdto_closeTime);
			session.setAttribute("menus", Mdto);
			session.setAttribute("countReview", Rdto);
			session.setAttribute("avg_ratings", avg_ratings);
			request.getRequestDispatcher("placeDetail.jsp").forward(request, response);
		} else {
			response.sendRedirect("PlaceListService");
		}
	
	}

}
