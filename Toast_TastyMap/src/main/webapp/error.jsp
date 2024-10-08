<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error!</title>
</head>
<body align="center">
	<div></div>
	<c:if test="${!empty info }">
		<a href="main.jsp">메인 페이지로 이동</a>
	</c:if>
	<c:if test="${empty info }">
		<a href="login.jsp">로그인 페이지로 이동</a>
	</c:if>
</body>
</html>