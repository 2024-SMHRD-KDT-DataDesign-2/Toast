package com.Toast.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Toast.model.memberDTO;
import com.Toast.model.placeDAO;
import com.Toast.model.placeDTO;
import com.Toast.model.reviewDAO;
import com.Toast.model.reviewDTO;

@WebServlet("/VisitedPlaceService")
public class VisitedPlaceService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        memberDTO info = (memberDTO) session.getAttribute("info");

        placeDAO placeDao = new placeDAO();
        reviewDAO reviewDao = new reviewDAO();

        List<placeDTO> placeList = placeDao.getVisitedPlaceByIdx(info.getMem_id());

        List<Integer> visitTimesList = new ArrayList<>();
        List<Double> avgRatingList = new ArrayList<>();

        for (placeDTO place : placeList) {
            reviewDTO review = new reviewDTO();
            review.setMem_id(info.getMem_id());
            review.setPlace_idx(place.getPlace_idx());

            int visitTimes = reviewDao.getVisitedTimes(review);
            double avgRating = reviewDao.getVisitedRatings(review);

            visitTimesList.add(visitTimes);
            avgRatingList.add(avgRating);
        }

        session.setAttribute("visitedPlace", placeList);
        session.setAttribute("visitTimesList", visitTimesList);
        session.setAttribute("avgRatingList", avgRatingList);

        response.sendRedirect("visitedPlace.jsp");
    }
}
