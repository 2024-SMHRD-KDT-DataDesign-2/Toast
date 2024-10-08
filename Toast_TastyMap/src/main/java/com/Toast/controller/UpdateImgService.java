package com.Toast.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.Toast.model.memberDAO;
import com.Toast.model.memberDTO;

@WebServlet("/UpdateImgService")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, // 1MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 100 // 100MB
)
public class UpdateImgService extends HttpServlet {
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
        
        String mem_id = info.getMem_id();
        String mem_pw = info.getMem_pw();
        String mem_nick = info.getMem_nick();
        int mem_ribbon = info.getMem_ribbon();
        String mem_grade = info.getMem_grade();
        
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        Part filePart = request.getPart("mem_img");
        String fileName = null;
        
        // 파일 처리
        if (filePart != null && filePart.getSize() > 0) {
            fileName = filePart.getSubmittedFileName();
            filePart.write(uploadPath + File.separator + fileName);  // 파일 저장
            fileName = "uploads/" + fileName;  // 경로 포함하여 저장
        } else {
            fileName = "images/default_mem_img.png";
        }
        
        memberDTO dto = new memberDTO(fileName, mem_id, mem_pw, mem_nick, mem_ribbon, mem_grade);
        memberDAO dao = new memberDAO();
        int result = dao.updateImg(dto);

        if (result > 0) {
            session.setAttribute("info", dto);
            response.sendRedirect("MyPageService");
        } else {
            request.setAttribute("UpdateFailed", true);
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }
    }
}
