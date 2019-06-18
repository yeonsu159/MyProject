<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Write</title>
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
<h1>Update Notice</h1>

<form action="boardModifyProcess.do" method="post" name="frm" enctype="multipart/form-data">
<!-- num = 위에서 파라미터로 받은 값 -->
<input type="hidden" name="num" value="${param.num }">
<input type="hidden" name="pageNum" value="${param.pageNum }">
<table id="notice">
<tr><th>작성자명</th><td><input type="text" name="name" value="${board.name }"></td></tr>
<tr><th>비밀번호</th><td><input type="password" name="pw"></td></tr>
<tr><th>글제목</th><td><input type="text" name="subject" value="${board.subject }"></td></tr>
<tr>
<th>파일</th>
<td>
<input type="file" name="newFilename">
${board.filename }
<input type="hidden" name="oldFilename" value="${board.filename }">
</td>
</tr>
<tr>
	<th>글내용</th>
	<td><textarea rows="13" cols="40" name="content" >${board.content }</textarea></td>
</tr>
</table>


<div id="table_search">
<input type="submit" value="글쓰기" class="btn" >
<input type="reset" value="다시 작성" class="btn">
<input type="button" value="글목록" class="btn" onclick="location.href='boardList.do?pageNum=${param.pageNum}';">
</div>
</form>
<div class="clear"></div>

</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
	<hr />
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>