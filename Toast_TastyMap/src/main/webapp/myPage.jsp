<%@page import="com.Toast.model.memberDTO"%>
<%@page import="com.Toast.model.memberDAO"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>마이 페이지</title>
<link rel="stylesheet" type="text/css" href="assets/css/myPage.css">
<script src="assets/js/myPage.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<div class="container">
		<div class="header">
			<span class="back-btn" onclick="goBack()">←</span>
			<h1>마이 페이지</h1>
		</div>

		<!-- Profile Card -->
		<div class="profile-card">
			<!-- 이미지 클릭 시 이미지 수정 모달 열기 -->
			<label for="profileImageInput"> <c:if
					test="${not empty info.mem_img }">
					<img id="imagePreview"
						src="${pageContext.request.contextPath}/${info.mem_img}"
						alt="Profile Image" class="profile-image"
						onclick="openModal('imageModal')" />
				</c:if>
			</label>
			<div class="profile-info">
				<!-- 실버 클릭 시 등급 수정 모달 열기 -->
				<h2 class="rank" style="cursor: pointer;"
					onmouseover="this.style.textDecoration='underline';"
					onmouseout="this.style.textDecoration='none';"
					onclick="openModal('rankModal')">${info.mem_grade}</h2>
				<h2 class="username">${info.mem_nick}</h2>
				<a id="userEmail">${info.mem_id}</a>
				<p id="changePassword" onclick="openModal('passwordModal')">비밀번호
					수정</p>
				<p>|</p>
				<p id="changeNickname" onclick="openModal('nicknameModal')">닉네임
					수정</p>
			</div>
		</div>

		<div class="review-card">
			<div>
				<h3>작성한 리뷰 ${countReviewMem }개         </h3>
				<button onclick="location.href='VisitedPlaceService'"
					style="text-align: right; margin-bottom: 10px;">방문 식당 모아보기</button>
			</div>
			
			<br>

			<c:if test="${empty reviewList}">
				<p>작성한 리뷰가 없습니다.</p>
			</c:if>

			<c:if test="${not empty reviewList }">
				<ul class="review-list">
					<c:forEach begin="0" end="${fn:length(reviewList)}" var="rl">
						<li onClick="location.href='ReviewDetailService?review_idx=${reviewList[rl].review_idx }'">
						<a
							href="ReviewDetailService?review_idx=${reviewList[rl].review_idx }">
								<c:out value="${placeName[rl] }" /> <p
								style="text-align: right;"> <c:forEach
										begin="1" end="${reviewList[rl].review_ratings }" var="star">
									★
								</c:forEach>
							</p>
						</a>
						</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>

		<!-- 등급 모달 -->
		<div id="rankModal" class="modal">
			<div class="modal-content">
				<div class="modal-header">
					<h2>등급</h2>
					<span class="modal-close" onclick="closeModal('rankModal')">&times;</span>
				</div>
				<div class="modal-body">
					<ul class="rank-list" style="list-style: none; padding: 0;">
						<li class="rank-item"
							style="display: flex; justify-content: space-between; align-items: center; padding: 10px; background-color: #465A69; color: white; margin-bottom: 5px; border-radius: 10px;">
							<div class="rank-name">다이아</div> <span class="rank-detail">리본
								250개 이상</span>
						</li>
						<li class="rank-item"
							style="display: flex; justify-content: space-between; align-items: center; padding: 10px; background-color: #C7CDD9; color: #465A69; margin-bottom: 5px; border-radius: 10px;">
							<div class="rank-name">플래티넘</div> <span class="rank-detail">리본
								160개 이상</span>
						</li>
						<li class="rank-item"
							style="display: flex; justify-content: space-between; align-items: center; padding: 10px; background-color: #E7C999; color: white; margin-bottom: 5px; border-radius: 10px;">
							<div class="rank-name">골드</div> <span class="rank-detail">리본
								90개 이상</span>
						</li>
						<li class="rank-item"
							style="display: flex; justify-content: space-between; align-items: center; padding: 10px; background-color: #D3D4D9; color: #465A69; margin-bottom: 5px; border-radius: 10px;">
							<div class="rank-name">실버</div> <span class="rank-detail">리본
								30개 이상</span>
						</li>
						<li class="rank-item"
							style="display: flex; justify-content: space-between; align-items: center; padding: 10px; background-color: #A89478; color: white; border-radius: 10px;">
							<div class="rank-name">브론즈</div> <span class="rank-detail">리본
								0개 이상 (기본)</span>
						</li>
					</ul>
				</div>
			</div>
		</div>


		<!-- 이미지 수정 모달 -->
		<div id="imageModal" class="modal">
			<div class="modal-content">
				<div class="modal-header">
					<h2>이미지 수정</h2>
					<span class="modal-close" onclick="closeModal('imageModal')">&times;</span>
				</div>
				<div class="modal-body">
					<form action="UpdateImgService" method="post"
						enctype="multipart/form-data">
						<input type="file" id="profileImageInput" name="mem_img"
							accept="image/*" class="input-field">
						<button type="submit" class="submit-button">이미지 수정</button>
					</form>
				</div>
			</div>
		</div>

		<!-- 닉네임 수정 모달 -->
		<div id="nicknameModal" class="modal">
			<div class="modal-content">
				<div class="modal-header">
					<h2>닉네임 수정</h2>
					<span class="modal-close" onclick="closeModal('nicknameModal')">&times;</span>
				</div>
				<div class="modal-body">
					<form action="UpdateNickService" method="post">
						<input type="text" name="mem_nick" placeholder="새 닉네임 입력"
							class="input-field">
						<button type="submit" class="submit-button">완료</button>
					</form>
				</div>
			</div>
		</div>

		<!-- 비밀번호 수정 모달 -->
		<div id="passwordModal" class="modal">
			<div class="modal-content">
				<div class="modal-header">
					<h2>비밀번호 수정</h2>
					<span class="modal-close" onclick="closeModal('passwordModal')">&times;</span>
				</div>
				<div class="modal-body">
					<form action="UpdatePwService" method="post">
						<input type="password" name="current_mem_pw" placeholder="기존 비밀번호"
							class="input-field"> <input type="password" name="mem_pw"
							placeholder="새 비밀번호" class="input-field">
						<button type="submit" class="submit-button">비밀번호 수정</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
