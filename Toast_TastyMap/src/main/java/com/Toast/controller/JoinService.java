package com.Toast.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.Toast.model.memberDAO;
import com.Toast.model.memberDTO;

@WebServlet("/JoinService")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1,  // 1MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 100   // 100MB
)
public class JoinService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String mem_id = request.getParameter("mem_id");
        String mem_pw = request.getParameter("mem_pw");
        String mem_nick = request.getParameter("mem_nick");

        memberDAO dao = new memberDAO();
        
        if (dao.isIdDuplicated(mem_id)) {
            request.setAttribute("joinFailed", true);
            RequestDispatcher rd = request.getRequestDispatcher("join.jsp");
            rd.forward(request, response);
            return;
        }

        // 이미지 파일 저장 경로 설정
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // 이미지 파일 업로드 처리
        Part filePart = request.getPart("mem_img");
        String fileName = null;

        // 파일 처리
        if (filePart != null && filePart.getSize() > 0) {
            fileName = filePart.getSubmittedFileName();
            filePart.write(uploadPath + File.separator + fileName);  // 파일 저장
            fileName = "uploads/" + fileName;  // 경로 포함하여 저장
        } else {
            // 파일이 없을 경우 기본 이미지 설정
            fileName = "images/default_mem_img.png";
        }

        memberDTO dto = new memberDTO(fileName, mem_id, mem_pw, mem_nick, 0, "브론즈");
        
        int result = 0;
        try {
        	result = dao.join(dto);
        } finally {
        	if (result > 0) {
        		response.sendRedirect("login.jsp");
        	} else {
        		request.setAttribute("joinFailed", true);
        		RequestDispatcher rd = request.getRequestDispatcher("join.jsp");
        		rd.forward(request, response);
        	}
        }
    }
}

