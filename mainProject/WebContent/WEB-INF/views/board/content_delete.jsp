<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Delete</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">


</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->


<!-- 게시판 -->
<article>
<h1>Notice Delete</h1>
<form action="boardDeleteProcess.do" method="post" name="frm">
<input type="hidden" name="num" value="${param.num }">
<input type="hidden" name="pageNum" value="${param.pageNum }">
<table id="notice">
<tr>
	<th>비밀번호</th><td>
	<input type="password" name="pw"></td>
</tr>

</table>


<div id="table_search">
<input type="submit" value="글삭제" class="btn" >
<input type="reset" value="다시 작성" class="btn">
<input type="button" value="글목록" class="btn" onclick="location.href='board.do?&pageNum=${board.pageNum }';">
</div>
</form>
<div class="clear"></div>

</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>