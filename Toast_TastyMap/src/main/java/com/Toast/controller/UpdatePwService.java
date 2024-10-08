package com.Toast.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Toast.model.memberDAO;
import com.Toast.model.memberDTO;

@WebServlet("/UpdatePwService")
public class UpdatePwService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        memberDTO info = (memberDTO) session.getAttribute("info");

        String current_mem_pw = request.getParameter("current_mem_pw");
        
        boolean up = false;
        if (current_mem_pw.equals(info.getMem_pw())) {
        	up = true;
        }
        
        String mem_pw = request.getParameter("mem_pw");
        
        String mem_img = info.getMem_img();
        String mem_id = info.getMem_id();
        String mem_nick = info.getMem_nick();
        int mem_ribbon = info.getMem_ribbon();
        String mem_grade = info.getMem_grade();
        
        if (mem_id == null) {
        	response.sendRedirect("login.jsp");
        	return;
        }
        
        memberDTO dto = new memberDTO(mem_img, mem_id, mem_pw, mem_nick, mem_ribbon, mem_grade);

        int result = 0;
        if (up) {
        	memberDAO dao = new memberDAO();
        	result = dao.updatePw(dto);
        }

        if (result > 0) {
        	session.setAttribute("info", dto);
            response.sendRedirect("MyPageService");
        } else {
        	request.setAttribute("UpdateFailed", true);
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }
    }
}
