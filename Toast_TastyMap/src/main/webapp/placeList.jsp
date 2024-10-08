<%@page import="com.Toast.model.filteringDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>식당 리스트</title>
<link rel="stylesheet" type="text/css" href="assets/css/placeList.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>

	<div class="container">

		<div class="header">
			<span class="back-btn" onclick="goBack()">←</span>
			<h1>식당 목록</h1>
		</div>

		<div class="section">
			<div class="filter-section">
				<button id="filter-reset-bt" onclick="resetFilters()">필터
					초기화</button>
				<form>
					<select name="district" id="district"
						onchange="loadDistrictData(this.value)">
						<option value="광주전체">광주전체</option>
						<option value="광산구">광산구</option>
						<option value="동구">동구</option>
						<option value="서구">서구</option>
						<option value="남구">남구</option>
						<option value="북구">북구</option>
					</select>
				</form>
				<form>
					<select name="filter_paking" id="filter_parking"
						onchange="filterParkingData(this.value)">
						<option value="" disabled selected>주차</option>
						<option value="주차가능">주차가능</option>
					</select>
				</form>
				<form>
					<select name="filter_time" id="filter_time"
						onchange="filterTimeData(this.value)">
						<!-- '24시간영업하는', '저녁에만영업하는', '연중무휴', '낮에만영업하는', '새벽까지영업하는', '아침뷔페를하는', '아침을먹을수있는', '브런치메뉴가있는', '점심뷔페를하는' -->
						<option value="" disabled selected>시간</option>
						<option value="24시간영업하는">24시간영업하는</option>
						<option value="연중무휴">연중무휴</option>
						<option value="낮에만영업하는">낮에만영업하는</option>
						<option value="저녁에만영업하는">저녁에만영업하는</option>
						<option value="새벽까지영업하는">새벽까지영업하는</option>
						<option value="아침뷔페를하는">아침뷔페를하는</option>
						<option value="아침을먹을수있는">아침을먹을수있는</option>
					</select>
				</form>
			</div>
			<div class="filter-section">
				<form>
					<select name="filter_space" id="filter_space"
						onchange="filterSpaceData(this.value)">
						<!-- '미술작품이있는','루프탑이있는', '테라스가있는', '카운터석이있는', '정원이나산책로가있는', '포토존이있는', '프라이빗룸이있는' -->
						<option value="" disabled selected>공간</option>
						<option value="미술작품이있는">미술작품이있는</option>
						<option value="루프탑이있는">루프탑이있는</option>
						<option value="테라스가있는">테라스가있는</option>
						<option value="카운터석이있는">카운터석이있는</option>
						<option value="정원이나산책로가있는">정원이나산책로가있는</option>
						<option value="포토존이있는">포토존이있는</option>
						<option value="프라이빗룸이있는">프라이빗룸이있는</option>
					</select>
				</form>

				<form>
					<select name="filter_atmosphere" id="filter_atmosphere"
						onchange="filterAtmosphereData(this.value)">
						<!-- '가족과외식하기좋은', '연인과같이가면좋은', '인테리어가좋은', '비즈니스다이닝에좋은', '상견례에좋은', '외국인접대에좋은', '소개팅하기좋은', '술한잔하기좋은' -->
						<option value="" disabled selected>분위기</option>
						<option value="가족과외식하기좋은">가족과외식하기좋은</option>
						<option value="연인과같이가면좋은">연인과같이가면좋은</option>
						<option value="인테리어가좋은">인테리어가좋은</option>
						<option value="비즈니스다이닝에좋은">비즈니스다이닝에좋은</option>
						<option value="상견례에좋은">상견례에좋은</option>
						<option value="외국인접대에좋은">외국인접대에좋은</option>
						<option value="소개팅하기좋은">소개팅하기좋은</option>
						<option value="술한잔하기좋은">술한잔하기좋은</option>
					</select>
				</form>

				<form>
					<select name="filter_else" id="filter_else"
						onchange="filterElseData(this.value)">
						<!-- '반려동물동반가능', '켈리더블임팩트맛집', '코카-콜라레드리본맛집' -->
						<option value="" disabled selected>그외</option>
						<option value="반려동물동반가능">반려동물동반가능</option>
						<option value="켈리더블임팩트맛집">켈리더블임팩트맛집</option>
						<option value="코카-콜라레드리본맛집">코카-콜라레드리본맛집</option>
					</select>
				</form>
			</div>

			<div class="restaurant-list" id="restaurant-list">
				<c:if test="${empty placeList}">
					<p>해당 조건에 해당하는 식당이 없습니다.</p>
				</c:if>

				<c:forEach var="place" items="${placeList}">
					<div class="restaurant-block"
						onClick="location.href='PlaceDetailService?place_idx=${place.place_idx}'">
						<h2>${place.place_name}</h2>
						<p>전화번호: ${place.place_tel}</p>
						<p>주소: ${place.place_addr}</p>
						<%-- <a href="PlaceDetailService?place_idx=${place.place_idx}">상세보기</a> --%>
					</div>
				</c:forEach>
			</div>
		</div>

		<script type="text/javascript" src="assets/js/placeList.js"></script>
</body>
</html>
