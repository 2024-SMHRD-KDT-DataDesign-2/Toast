<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="assets/css/join.css">
    <script src="assets/js/failAlert.js"></script>
</head>

<body>
    <div id="join-status" data-join-failed="<%= request.getAttribute("joinFailed") %>"></div>

    <div class="login-page">
        <div class="form">
            <form action="JoinService" method="post" class="register-form" enctype="multipart/form-data">
                <div class="image-input">
                    <input type="file" id="imageInput" name="mem_img" accept="image/*" style="display: none;" />
                    <label for="imageInput">
                        <img id="imagePreview" src="images/default_mem_img.png" alt="Profile Image" width="100" height="100" />
                    </label>
                </div>
                <div class="header">
                    <span>회원가입</span>
                    <span class="underline"></span>
                </div>
                <input type="text" placeholder="이메일" name="mem_id" />
                <input type="password" placeholder="비밀번호" name="mem_pw" />
                <input type="text" placeholder="닉네임" name="mem_nick" />
                <input type="submit" value="회원가입" id="submitBt">
                <p class="message">
                    이미 등록하셨나요? <a href="login.jsp">로그인</a>
                </p>
            </form>
        </div>
    </div>

    <script src="assets/js/join.js"></script>
</body>

</html>