package com.Toast.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Toast.model.placeDAO;
import com.Toast.model.placeDTO;
import com.Toast.model.filteringDTO;

@WebServlet("/PlaceListService")
public class PlaceListService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String reset = request.getParameter("reset");
		if (reset != null && reset.equals("true")) {
			session.removeAttribute("filterState");
		}

		// session에 filterState가 없다면: 새로운 객체 생성
		// 있다면: 기존 필터 상태 가져오기 (조건 중첩 가능)
		filteringDTO filterState = null;
		if (session.getAttribute("filterState") == null) {
			filterState = new filteringDTO();
		} else {
			filterState = (filteringDTO) session.getAttribute("filterState");
		}

		String district = request.getParameter("district");
		String filter_parking = request.getParameter("filter_parking");
		String filter_time = request.getParameter("filter_time");
		String filter_space = request.getParameter("filter_space");
		String filter_atmosphere = request.getParameter("filter_atmosphere");
		String filter_else = request.getParameter("filter_else");

		if (district != null && !district.equals("광주전체")) {
			filterState.setPlace_district(district);
		} else {
			filterState.setPlace_district(filterState.getPlace_district());
		}

		if (filter_parking != null) {
			filterState.setParking_yn(filter_parking.equals("주차가능") ? 'y' : '\0');
		} else {
			filterState.setParking_yn('\0');
		}

		// keywords가 없다면 ? 새로운 리스트 생성 : 있다면 session에서 가져오기
		List<String> keywords = filterState.getKeywords() == null ? new ArrayList<>() : filterState.getKeywords();

		if (filter_time != null) {
			keywords = keywords.stream().filter(keyword -> !isTimeKeyword(keyword))  // 특정 그룹의 키워드가 아닌 키워드만
					.collect(Collectors.toList());  // 모아서 List 객체로 변환
			keywords.add(filter_time);  // 새로운 키워드 추가
		}

		if (filter_space != null) {
			keywords = keywords.stream().filter(keyword -> !isSpaceKeyword(keyword))
					.collect(Collectors.toList());
			keywords.add(filter_space);
		}

		if (filter_atmosphere != null) {
			keywords = keywords.stream().filter(keyword -> !isAtmosphereKeyword(keyword))
					.collect(Collectors.toList());
			keywords.add(filter_atmosphere);
		}

		if (filter_else != null) {
			keywords = keywords.stream().filter(keyword -> !isElseKeyword(keyword))
					.collect(Collectors.toList());
			keywords.add(filter_else);
		}

		filterState.setKeywords(keywords);

		session.setAttribute("filterState", filterState);

		System.out.println("------------------------------");
		System.out.println("filterState: " + filterState);
		System.out.println("parking: " + filterState.getParking_yn());
		System.out.println("district: " + filterState.getPlace_district());
		System.out.println("keywords: " + filterState.getKeywords());
		for (String k : filterState.getKeywords()) {
			System.out.println(k);
		}

		placeDAO dao = new placeDAO();
		ArrayList<placeDTO> placeList = dao.filtering(filterState);

		session.setAttribute("placeList", placeList);
		request.getRequestDispatcher("placeList.jsp").forward(request, response);
	}
	
	// 키워드가 해당 그룹에 있으면 true
	private boolean isTimeKeyword(String keyword) {
		List<String> timeKeywords = Arrays.asList("24시간영업하는", "저녁에만영업하는", "연중무휴", "낮에만영업하는", "새벽까지영업하는", "아침뷔페를하는",
				"아침을먹을수있는", "브런치메뉴가있는", "점심뷔페를하는");
		return timeKeywords.contains(keyword);
	}

	private boolean isSpaceKeyword(String keyword) {
		List<String> spaceKeywords = Arrays.asList("미술작품이있는", "루프탑이있는", "테라스가있는", "카운터석이있는", "정원이나산책로가있는", "포토존이있는",
				"프라이빗룸이있는");
		return spaceKeywords.contains(keyword);
	}

	private boolean isAtmosphereKeyword(String keyword) {
		List<String> atmosphereKeywords = Arrays.asList("가족과외식하기좋은", "연인과같이가면좋은", "인테리어가좋은", "비즈니스다이닝에좋은", "상견례에좋은",
				"외국인접대에좋은", "소개팅하기좋은", "술한잔하기좋은");
		return atmosphereKeywords.contains(keyword);
	}

	private boolean isElseKeyword(String keyword) {
		List<String> elseKeywords = Arrays.asList("반려동물동반가능", "켈리더블임팩트맛집", "코카-콜라레드리본맛집");
		return elseKeywords.contains(keyword);
	}

}