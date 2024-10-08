<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 모바일 반응형 지원 -->
<title>메인</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="assets/css/main.css" />
<script type="text/javascript"
	src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=q98o3g3nlb"></script>
<script src="assets/js/failAlert.js"></script>
</head>

<body>

	<%
	// main.jsp에 진입할 때 filterState 초기화
	session.removeAttribute("filterState");
	%>

	<div id="update-status"
		data-update-failed="<%=request.getAttribute("UpdateFailed") != null ? request.getAttribute("UpdateFailed") : false%>"></div>

	<%
	Object updateFailed = request.getAttribute("UpdateFailed");
	System.out.println("UpdateFailed: " + updateFailed);
	%>

	<div class="header">
		<h1 id="menuBtn">&#x2630;</h1>
	</div>

	<!-- 네이버 지도 -->
	<div id="map"></div>

	<!-- 블루리본 정보 박스 -->
	<div class="map-container">
		<div class="info-box">
			<h2>ABOUT 블루리본</h2>
			<p>블루리본은 우리나라 최초의 그리고 최고의 맛집 가이드북입니다</p>
		</div>
	</div>

	<!-- 사이드바 -->
	<div id="mySidebar" class="sidebar">
		<a href="javascript:void(0)" class="closebtn" id="closeBtn">&times;</a>
		<!-- 닫기 버튼 -->
		<a href="PlaceListService">음식점 리스트</a> <a href="MyPageService">마이
			페이지</a> <a href="LogoutService">로그아웃</a>
	</div>

	<script src="assets/js/main.js"></script>

</body>
</html>
