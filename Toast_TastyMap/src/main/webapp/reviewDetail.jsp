<!DOCTYPE html>
<html lang="ko">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>리뷰 화면</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/reviewDetail.css">
</head>

<body>

	<%
	int placeIdx = (int) session.getAttribute("place_idx_err");
	%>

	<div class="header">
		<span class="back-btn" onclick="goBack()">←</span>
		<h1>리뷰</h1>
	</div>

	<!-- 리뷰 본문 -->
	<div class="review-container">
		<div class="review-header">
			<div class="review-title"
				onClick="location.href='PlaceDetailService?place_name=${review_place_name}'">${review_place_name}</div>
			<c:forEach begin="1" end="${reviewDetail.review_ratings }" var="star">
				★
			</c:forEach>
		</div>
		<div class="review-subtitle">
			작성자: ${review_mem_nick} <br> 작성일: ${reviewDetail.created_at }
			<c:if test="${review_mem_nick.equals(info.mem_nick) }">
				<a href="reviewUpdate.jsp"><button>수정</button></a>
				<a href="ReviewDeleteService"><button>삭제</button></a>
			</c:if>
		</div>

		<c:if test="${not empty reviewDetail.review_img1}">
			<section id="article-images">
				<div id="image-slider">
					<div class="slider-wrap">
						<div class="slider">
							<!-- 첫 번째 이미지 -->
							<c:if test="${not empty reviewDetail.review_img1}">
								<span> <a href="#"> <img class="portrait"
										src="${pageContext.request.contextPath}/${reviewDetail.review_img1}">
								</a>
								</span>
							</c:if>

							<!-- 두 번째 이미지 -->
							<c:if test="${not empty reviewDetail.review_img2}">
								<span> <a href="#"> <img class="portrait"
										src="${pageContext.request.contextPath}/${reviewDetail.review_img2}">
								</a>
								</span>
							</c:if>

							<!-- 세 번째 이미지 -->
							<c:if test="${not empty reviewDetail.review_img3}">
								<span> <a href="#"> <img class="portrait"
										src="${pageContext.request.contextPath}/${reviewDetail.review_img3}">
								</a>
								</span>
							</c:if>
						</div>
					</div>
				</div>
			</section>
		</c:if>

		<br>
		<!-- 좋아요 섹션 -->
		<div class="review-section">
			<div class="review-section-title">
				<span class="review-section-icon">👍</span> <span>이런 점이 좋아요</span>
			</div>
			<div class="review-section-content">${reviewDetail.review_content_good}</div>
		</div>

		<!-- 개선점 섹션 -->
		<div class="review-section">
			<div class="review-section-title">
				<span class="review-section-icon">👎</span> <span>이런 점은 개선해
					주세요</span>
			</div>
			<div class="review-section-content">${reviewDetail.review_content_bad}</div>
		</div>

		<!-- 추천 메뉴 섹션 -->
		<div class="review-section">
			<div class="review-section-title">
				<span class="review-section-icon">❤️</span> <span>이 메뉴 추천해요</span>
			</div>
			<div class="review-section-content">${reviewDetail.review_content_recommend}</div>
		</div>
		<br> <span class="underline"></span> <br><br>
		<span class="underline"></span> <br>
		<div class="comment-section">
			<h3>댓글</h3>
			<br>

			<c:if test="${not empty comments }">
				<c:forEach begin="0" end="${fn:length(comments)-1}" var="i">
					<div class="comment">
						<img class="comment-img"
							src="${pageContext.request.contextPath}/${memImg[i].mem_img }">
						<div class="comment-content">
							<div class="comment-nickname">${comments[i].mem_id }
								<c:if test="${comments[i].mem_id.equals(info.mem_id) }">
									<a
										href="CommentDeleteService?comment_id=${comments[i].comment_id }"><button>삭제</button></a>
								</c:if>
							</div>
							<div class="comment-text">${comments[i].comment_content }</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
			<!-- 댓글 작성 -->
			<form
				action="CommentWriteService?review_idx=${reviewDetail.review_idx }"
				method="post">
				<input type="hidden" name="review_idx" value="${review.review_idx}">
				<div class="comment-input-section">
					<textarea class="comment-input" name="comment_content"
						placeholder="댓글을 입력하세요"></textarea>
					<button class="submit-btn" type="submit">작성</button>
				</div>
			</form>
		</div>


	</div>

	<script src="assets/js/reviewDetail.js"></script>

</body>

</html>