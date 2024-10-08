<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰 페이지</title>
    <link rel="stylesheet" type="text/css" href="assets/css/reviewWrite.css">
</head>

<body>
    <div class="container">
        <div class="header">
            <span class="back-btn" onclick="goBack()">←</span>
            <h1>리뷰</h1>
        </div>

        <form action="ReviewWriteService?place_idx=${place.place_idx }" method="post" enctype="multipart/form-data">
            <!-- Content -->
            <div class="content">
                <h2>${place.place_name}</h2>

                <!-- 별점 선택 -->
                <div class="rating-select">
                    <label for="review_ratings">별점 선택:</label>
                    <select name="review_ratings" id="review_ratings">
                        <option value="1">★</option>
                        <option value="2">★★</option>
                        <option value="3">★★★</option>
                        <option value="4">★★★★</option>
                        <option value="5">★★★★★</option>
                    </select>
                </div>

                <hr class="separator">

                <!-- Image Preview -->
                <img id="imagePreview" alt="사진 미리보기" style="display:none;" />

                <!-- Photo Upload Section -->
                <div class="photo-upload">
                    <button type="button" onclick="triggerFileInput()">사진 등록(최대 3장)</button>
                    <input type="file" id="photoInput" name="review_img1" accept="image/*" style="display:none;" multiple>
                </div>

                <p>👍 이런 점이 좋아요</p>
                <div class="input-box">
                    <textarea rows="2" name="review_content_good" style="width: 100%; border: none; background-color: #f4f4f4;"></textarea>
                </div>

                <p>👎 이런 점은 개선해 주세요</p>
                <div class="input-box">
                    <textarea rows="2" name="review_content_bad" style="width: 100%; border: none; background-color: #f4f4f4;"></textarea>
                </div>

                <p>❤️ 이 메뉴 추천해요</p>
                <div class="input-box">
                    <textarea rows="2" name="review_content_recommend" style="width: 100%; border: none; background-color: #f4f4f4;"></textarea>
                </div>

                <hr class="separator">

                <!-- 등록 버튼 -->
                <input type="submit" class="submit-btn" value="제출">
            </div>
        </form>
    </div>

    <script type="text/javascript" src="assets/js/reviewWrite.js"></script>
</body>
</html>
