<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>리뷰 화면</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/reviewList.css">

</head>

<body>
	<div class="container">
		<div class="header">
			<span class="back-btn" onclick="goBack()">←</span>
			<h1>${place.place_name }</h1>
			<a href="reviewWrite.jsp?place_idx=${place.place_idx }">작성</a>
		</div>

		<div class="review-count">작성된 리뷰 ${countReview}개</div>

		<div class="review-item">
			<c:forEach var="review" items="${ placeReviews}">
				<div onClick="location.href='ReviewDetailService?review_idx=${review.review_idx}'">
				<div class="review-header">
					<div class="review-title">${review.mem_id}</div>
					<c:forEach begin="1" end="${review.review_ratings }" var="star">
						★
					</c:forEach>
				</div>
				<div class="review-description">
					👍 ${review.review_content_good}
				</div>
				<div class="review-footer"></div>
				<hr class="separator">
				</div>
			</c:forEach>
		</div>
	</div>
</body>
<script src="assets/js/reviewList.js"></script>
</html>