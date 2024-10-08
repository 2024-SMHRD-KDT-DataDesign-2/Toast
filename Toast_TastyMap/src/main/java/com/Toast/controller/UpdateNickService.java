package com.Toast.controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Toast.model.memberDAO;
import com.Toast.model.memberDTO;

@WebServlet("/UpdateNickService")
public class UpdateNickService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        memberDTO info = (memberDTO) session.getAttribute("info");

        if (info == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String mem_img = info.getMem_img();
        String mem_id = info.getMem_id();
        String mem_pw = info.getMem_pw();
        String mem_nick = request.getParameter("mem_nick");
        int mem_ribbon = info.getMem_ribbon();
        String mem_grade = info.getMem_grade();

        memberDTO dto = new memberDTO(mem_img, mem_id, mem_pw, mem_nick, mem_ribbon, mem_grade);
        memberDAO dao = new memberDAO();

        try {
            int result = dao.updateNick(dto);

            if (result > 0) {
                // 성공적으로 업데이트된 경우 세션 정보 갱신
                session.setAttribute("info", dto);
                response.sendRedirect("MyPageService");
            } else {
                request.setAttribute("UpdateFailed", true);
                request.getRequestDispatcher("main.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("UpdateFailed", true);
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }
    }
}
