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

@WebServlet("/LoginService")
public class LoginService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String mem_id = request.getParameter("mem_id");
        String mem_pw = request.getParameter("mem_pw");

        memberDTO dto = new memberDTO();
        dto.setMem_id(mem_id);
        dto.setMem_pw(mem_pw);

        memberDAO dao = new memberDAO();
        memberDTO info = dao.login(dto);

        if (info != null) {
            HttpSession session = request.getSession();
            session.setAttribute("info", info);
            response.sendRedirect("main.jsp");
        } else {
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
