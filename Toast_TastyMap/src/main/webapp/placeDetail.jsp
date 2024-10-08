<!DOCTYPE html>
<html lang="ko">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>식당 소개 페이지</title>
<link rel="stylesheet" type="text/css" href="assets/css/placeDetail.css">
</head>

<body>

	<div class="header">
		<span class="back-btn" onclick="goBack()">←</span>
		<h1>${place.place_name}</h1>
	</div>

	<div class="container">
		<!-- 식당 정보 -->
		<div class="restaurant-info">
			<h2>${place.place_name}</h2>
			<p>${place.category }</p>
			<div>
				★ ${avg_ratings} | <a
					href="ReviewListService?place_idx=${place.place_idx }">방문자 리뷰
					${countReview }개</a>
			</div>
		</div>

		<div class="restaurant-details">
			<p>📍 ${place.place_addr}</p>
			<p>
				⏰
				<c:choose>
					<c:when test='${isOpen.equals("Open")}'>
                        영업 중 | ${closeTime} 영업 종료
                    </c:when>
					<c:otherwise>
                        영업 종료
                    </c:otherwise>
				</c:choose>
			</p>
			<p>📞 ${place.place_tel}</p>
		</div>

		<!-- 메뉴 섹션 -->
		<div class="menu-section">
			<h3>메뉴</h3>
			<div class="menu-list">
				<c:forEach var="menu" items="${menus }">
					<div class="menu-item">
						<div class="menu-name">${menu.menu_name}</div>
						<div class="menu-price">${menu.menu_price}원</div>
					</div>
				</c:forEach>
			</div>
		</div>

		<div class="keyword-section">
			<h3>키워드</h3>
			<div class="keyword-list">
				<c:choose>
					<c:when test="${place.keyword != 'X'}">
						<c:set var="keywords"
							value="${fn:split(fn:trim(place.keyword), ',')}" />
						<c:forEach var="keyword" items="${keywords}" varStatus="status">
							<c:if test="${keyword != 'X'}">
								<div class="keyword-item">${keyword}</div>
								<!-- 3번째마다 줄바꿈 -->
								<c:if test="${(status.index + 1) % 2 == 0}">
									<div class="clear"></div>
								</c:if>
							</c:if>
						</c:forEach>
					</c:when>
				</c:choose>
				<c:if test="${place.parking_yn eq 'y'.charAt(0)}">
					<div class="keyword-item">🚗 주차</div>
				</c:if>
			</div>
		</div>


		<script src='assets/js/placeDetail.js'></script>
</body>
</html>
