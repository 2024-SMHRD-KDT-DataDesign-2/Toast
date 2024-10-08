package com.Toast.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.Toast.model.placeDAO;
import com.Toast.model.placeDTO;

@WebServlet("/JsonService")
public class JsonService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		placeDAO dao = new placeDAO();
		List<placeDTO> dto = dao.getPlaces();
		String json = convertPlacesToJson(dto);
        
        System.out.println("json: " + json);
		
		 response.setContentType("application/json");
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().write(json);
		 
		
	}
	
	public String convertPlacesToJson(List<placeDTO> dto) {
		ObjectMapper objectMapper = new ObjectMapper();
	    String jsonString = "";
	    try {
	        jsonString = objectMapper.writeValueAsString(dto);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return jsonString;
	}

}
