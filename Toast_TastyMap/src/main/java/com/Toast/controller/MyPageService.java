package com.Toast.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Toast.model.memberDAO;
import com.Toast.model.memberDTO;
import com.Toast.model.reviewDAO;
import com.Toast.model.reviewDTO;

@WebServlet("/MyPageService")
public class MyPageService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		memberDTO info = (memberDTO) session.getAttribute("info");

		if (info == null) {
			response.sendRedirect("login.jsp");
		} else {
			reviewDAO dao = new reviewDAO();
			memberDAO Mdao = new memberDAO();
			List<String> placeName = dao.getPlaceNameByIdx(info.getMem_id());
			List<reviewDTO> reviewList = dao.getReviewsByMember(info.getMem_id());
			int countReviewMem = dao.countReviewByMember(info.getMem_id());
			int gradeResult = Mdao.updateGrage(info);

			if (gradeResult > 0) {
				System.out.println("등급 업데이트 성공");
				info.setMem_grade(Mdao.memGrade(info));
				request.setAttribute("info", info);
			} else {
				System.out.println("등급 업데이트 오류");
			}

			request.setAttribute("reviewList", reviewList);
			request.setAttribute("placeName", placeName);
			request.setAttribute("countReviewMem", countReviewMem);

			request.getRequestDispatcher("myPage.jsp").forward(request, response);
		}
	}
}
