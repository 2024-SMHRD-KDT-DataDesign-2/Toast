package com.Toast.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.Toast.model.memberDTO;
import com.Toast.model.reviewDAO;
import com.Toast.model.reviewDTO;

@WebServlet("/ReviewUpdateService")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class ReviewUpdateService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		memberDTO info = (memberDTO) session.getAttribute("info");

		String mem_id = info.getMem_id();
		int place_idx = Integer.parseInt(request.getParameter("place_idx"));
		int review_idx = Integer.parseInt(request.getParameter("review_idx"));
		String review_content_good = request.getParameter("review_content_good");
		String review_content_bad = request.getParameter("review_content_bad");
		String review_content_recommend = request.getParameter("review_content_recommend");

		int review_ratings = 0;
		try {
		    review_ratings = Integer.parseInt(request.getParameter("review_ratings"));
		} finally {
			System.out.println("review ratings: " + review_ratings);
			review_ratings = 1;
		}

		String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
		System.out.println("파일이 저장될 경로: " + uploadPath);
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		String[] imagePaths = new String[3];
		Collection<Part> parts = request.getParts();  // 여러 개의 파일 처리

		int imageCount = 0;
		for (Part part : parts) {
		    // 파일 이름이 있는 part만 처리
		    if (part.getName().equals("review_img[]") && part.getSize() > 0 && imageCount < 3) {
		        String fileName = part.getSubmittedFileName();
		        if (fileName != null && !fileName.isEmpty()) {
		            part.write(uploadPath + File.separator + fileName);  // 파일 저장
		            imagePaths[imageCount] = "uploads/" + fileName;  // 저장 경로 설정
		            imageCount++;
		        }
		    }
		}

		reviewDTO review = new reviewDTO();
		review.setMem_id(mem_id);
		review.setPlace_idx(place_idx);
		review.setReview_idx(review_idx);
		review.setReview_content_good(review_content_good);
		review.setReview_content_bad(review_content_bad);
		review.setReview_content_recommend(review_content_recommend);
		review.setReview_ratings(review_ratings);
		review.setReview_img1(imagePaths[0] != null ? imagePaths[0] : ""); // 이미지 경로 설정
		review.setReview_img2(imagePaths[1] != null ? imagePaths[1] : "");
		review.setReview_img3(imagePaths[2] != null ? imagePaths[2] : "");

		reviewDAO dao = new reviewDAO();
		int result = dao.updateReview(review);

		if (result > 0) {
			System.out.println("리뷰 수정 성공");

			for (String imagePath : imagePaths) {
				if (imagePath != null && !imagePath.isEmpty()) {
					File imageFile = new File(getServletContext().getRealPath("") + File.separator + imagePath);
					if (imageFile.exists()) {
						System.out.println("이미지 파일이 성공적으로 저장되었습니다: " + imageFile.getAbsolutePath());
					} else {
						System.out.println("이미지 파일이 저장되지 않았습니다: " + imageFile.getAbsolutePath());
					}
				}
			}

		} else {
			System.out.println("리뷰 수정 실패");
		}

		response.sendRedirect("ReviewListService?place_idx=" + place_idx);
	}
}
