<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<title>Notice Write</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<script src="script/jquery-3.3.1.min.js"></script>
</head>
<body>
		<!-- 헤더들어가는 곳 -->
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<!-- 헤더들어가는 곳 -->
		<br><br>

<h1>Email</h1>
<form action="emailProcess.do" method="post" enctype="multipart/form-data">
받는사람 이메일주소: <input type="text" name="receiver"><br>
메일 제목: <input type="text" name="subject"><br>
첨부 파일: <input type="file" name="filename"><br>
메일 내용: <textarea rows="7" cols="40" name="content"></textarea><br>
<button type="submit">이메일 전송</button>
</form>

</body>
</html>