<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>리뷰 수정</title>
<link rel="stylesheet" type="text/css" href="assets/css/reviewWrite.css">
</head>

<body>
	<div class="container">
		<div class="header">
			<span class="back-btn" onclick="goBack()">←</span>
			<h1>리뷰 수정</h1>
		</div>

		<form action="ReviewUpdateService?place_idx=${place.place_idx }"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="review_idx"
				value="${reviewDetail.review_idx}">

			<div class="content">
				<h2>${place.place_name}</h2>

				<div class="rating-select">
					<label for="review_ratings">별점 선택:</label> <select
						name="review_ratings" id="review_ratings">
						<option value="1"
							${reviewDetail.review_ratings == 1 ? 'selected' : ''}>★</option>
						<option value="2"
							${reviewDetail.review_ratings == 2 ? 'selected' : ''}>★★</option>
						<option value="3"
							${reviewDetail.review_ratings == 3 ? 'selected' : ''}>★★★</option>
						<option value="4"
							${reviewDetail.review_ratings == 4 ? 'selected' : ''}>★★★★</option>
						<option value="5"
							${reviewDetail.review_ratings == 5 ? 'selected' : ''}>★★★★★</option>
					</select>
				</div>

				<hr class="separator">

				<!-- Image Preview -->
                <img id="imagePreview" alt="사진 미리보기" style="display:none;" />

                <!-- Photo Upload Section -->
                <div class="photo-upload">
                    <button type="button" onclick="triggerFileInput()">사진 등록(최대 3장)</button>
                    <input type="file" id="photoInput" name="review_img[]" accept="image/*" style="display:none;" multiple>
                </div>

				<p>👍 이런 점이 좋아요</p>
				<div class="input-box">
					<textarea rows="2" name="review_content_good"
						style="width: 100%; border: none; background-color: #f4f4f4;">${reviewDetail.review_content_good}</textarea>
				</div>

				<p>👎 이런 점은 개선해 주세요</p>
				<div class="input-box">
					<textarea rows="2" name="review_content_bad"
						style="width: 100%; border: none; background-color: #f4f4f4;">${reviewDetail.review_content_bad}</textarea>
				</div>

				<p>❤️ 이 메뉴 추천해요</p>
				<div class="input-box">
					<textarea rows="2" name="review_content_recommend"
						style="width: 100%; border: none; background-color: #f4f4f4;">${reviewDetail.review_content_recommend}</textarea>
				</div>

				<hr class="separator">

				<!-- 수정 버튼 -->
				<input type="submit" class="submit-btn" value="수정">
			</div>
		</form>
	</div>

	<script type="text/javascript" src="assets/js/reviewWrite.js"></script>
</body>
</html>
