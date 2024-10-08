<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<link rel="stylesheet" href="assets/css/login.css" />
<script src="assets/js/failAlert.js"></script>
</head>
<body>
	<div id="login-status" data-login-failed="<%= request.getAttribute("loginFailed") %>"></div>
	
	<div class="login-page">
		<div class="form">
			<form action="LoginService" method="post">
				<div class="header">
					<span>LOGIN</span> <span class="underline"></span>
				</div>
				<input type="text" placeholder="아이디" name="mem_id" /> <input
					type="password" placeholder="비밀번호" name="mem_pw" /> <input
					type="submit" value="로그인" id="submitBt">
				<p class="message">
					<a href="join.jsp">회원가입</a>
				</p>
			</form>
		</div>
	</div>
	<script src="assets/js/login.js"></script>
</body>
</html>