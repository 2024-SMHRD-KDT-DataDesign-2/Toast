<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방문 식당</title>
<link rel="stylesheet" type="text/css"
	href="assets/css/visitedPlace.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<span class="back-btn" onclick="goBack()">←</span>
			<h1>방문 식당</h1>
		</div>
		
		<div class="restaurant-list">
			<c:if test="${empty visitedPlace }">
				<p>방문한 식당이 없습니다.</p>
			</c:if>
			
			<c:if test="${!empty visitedPlace }">
				<c:forEach begin="0" end="${fn:length(visitedPlace)-1}" var="i">
				<%-- <c:forEach var="place" items="${visitedPlace }"> --%>
					<div class="restaurant-block" onClick="location.href='PlaceDetailService?place_idx=${visitedPlace[i].place_idx}'">
						<h2>${visitedPlace[i].place_name }</h2>
						<p>방문 횟수: ${visitTimesList[i]}회</p>
	                    <p>평균 평점: ${avgRatingList[i]}점</p>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
	<script type="text/javascript" src="assets/js/visitedPlace.js"></script>
</body>
</html>