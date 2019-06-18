<%@page import="vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Write</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<script src="script/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function () {

	$('span.re_comment').on('click', function () {
		//  가장 가까운 부모요소 찾기
		var $container = $(this).closest('div.comment-container');
		var re_ref = $container.find('.re_ref').text();
		var re_lev = $container.find('.re_lev').text();
		var re_seq = $container.find('.re_seq').text();
		
		
		
		var str = '';
		str += '<form action="re_CommentProcess.do" method="post" name="re_frm">';
		str += '<input type="hidden" name="re_boardnum" value="${board.num}">';
		str += '<input type="hidden" name="re_ref" value="' + re_ref + '">';
		str += '<input type="hidden" name="re_lev" value="' + re_lev + '">';
		str += '<input type="hidden" name="re_seq" value="' + re_seq + '">';
		str += '<table id="notice">';
		str += '<tr><th>작성자명</th><td><input type="text" name="re_reply_id"></td></tr>';
		str += '<tr><th>비밀번호</th><td><input type="password" name="re_reply_pw"></td></tr>';
		str += '<tr>';
		str += '	<th>글내용</th>';
		str += '	<td><textarea rows="5" cols="30" name="re_reply_content"></textarea></td>';
		str += '</tr>';
		str += '</table>';

		str += '<div id="table_search">';
		str += '<input type="submit" value="댓글 쓰기" class="btn">';
		str += '<input type="reset" value="다시 작성" class="btn">';
		str += '</div>';
		str += '</form>';
		
		$(this).next().html(str);
	});
});
</script> 
<script>
$(function(){
	$('#comment_frm').submit(function(){
		var sdr = $('#comment_frm').serialize();
		console.log('쿼리스트링: ' + sdr);
		
		if($('#replycontent').val().trim() == ""){
			alert('댓글을 입력하세요');
			$("#replycontent").val("").focus();
		} else{
			$.ajax({
				url : 'boardCommentProcess.do',
				data : sdr,
				success : function(result){
					console.log('result : ' + result);
					if (result) {
						//location.href = 'boardDetail.do?num=${board.num}&pageNum=${param.pageNum}';
						location.reload();
					}
				}
			});
			
		}
		
		return false;
	});
});


</script>
<style>
#notice{
padding: 10px;
text-align: center;
width: 500px;
height: 500px;
}
td{


bor}
</style>
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
			<h1>Notice Content</h1>

			<table id="notice" border="1">
				<tr style="height: 10px">
					<th style="width:20%;">글번호</th>
					<td style="width:40%">${requestScope.board.num}</td>
					<th style="width:20%">조회수</th>
					<td style="width:40%">${board.readcount}</td>
				</tr>
				<tr style="height: 10px">
					<th>작성자</th>
					<td colspan="3">${board.name }</td>
					
				</tr>
				<tr style="height: 10px">
					<th>글제목</th>
					<td colspan="3">${board.subject }</td>
				</tr>
				<c:if test="${board.filename ne null}">
					<tr>
						<th>파일</th>
						<td colspan="3" class="left"><a
							href="upload/${board.filename }">${board.filename }</a> <br>
							<%
								String ext = (String) request.getAttribute("ext");

									if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("gif") || ext.equalsIgnoreCase("png")) {
							%> <img src="upload/${board.filename }"
							style="width: 50px; height: 50px;"> <%
 	}
 %></td>
					</tr>
				</c:if>
				<tr>
					<td colspan="4">${board.content}</td>
				</tr>
			</table>

			<%--
// 세션값 있으면(로그인 했으면)
// 수정, 삭제, 답글쓰기 버튼 보이게 설정
 --%>
 <br> <br> <br>
			<div id="table_search">
				<c:if test="${name ne null }">
					<input type="button" value="글수정" class="btn"
						onclick="location.href='boardModify.do?num=${board.num }&pageNum=${param.pageNum}';">
					<input type="button" value="글 삭제" class="btn"
						onclick="location.href='boardDelete.do?num=${board.num }&pageNum=${param.pageNum}';">
				</c:if>

				<input type="button" value="글목록" class="btn"
					onclick="location.href='board.do?&pageNum=${param.pageNum}';">
			</div>
			<div class="clear"></div>

		</article>
		<!-- 게시판 -->
		
		 <br> <br> <br>
		<!--  댓글 -->
		<form method="post" id="comment_frm">
			<input type="hidden" name="boardnum" value="${board.num}" id="boardnum">
			<input type="hidden" name="reply_id" value="${name}" id="replyid">
			<div>
			<textarea rows="5" cols="50" name="reply_content" id="replycontent">
			</textarea>
			</div>
			

			<div id="table_search">
				<input type="submit" name="comment_btn" value="댓글 쓰기" class="btn"> 
			</div>
		</form>

		
		<!-- 댓글 목록이 없을 경우 나타나지 않게 한다. -->

		<div>

			<c:forEach var="comment" items="${commentList}">
			<hr>
				<div class="comment-container">
					<div>
					<c:if test="${comment.re_lev gt 0}"><!-- 답글일때 -->
					<c:set var="wid" value="${comment.re_lev * 10}" /><%-- 답글 들여쓰기 레벨 값 저장용 --%>
					<img src="images/level.gif" style="width: ${wid}px; height: 13px;">
					<img src="images/re.gif">
					</c:if>
					작성자 : ${comment.id}</div>
					<div>댓글 내용 : ${comment.content}</div>
					<span class="re_ref" style="display: none;">${comment.re_ref}</span>
					<span class="re_lev" style="display: none;">${comment.re_lev}</span>
					<span class="re_seq" style="display: none;">${comment.re_seq}</span>
					<div>
						<a href="#">수정</a> <a href="#">삭제</a><br> 
						<span class="re_comment" onclick="${comment.num}">답변</span>
						<div class="re_comment_form"></div>
					</div>
				</div>
			</c:forEach>
		</div>



		<!-- 본문들어가는 곳 -->

		
		<div class="clear"></div>
		<!-- 푸터들어가는 곳 -->
		<hr>
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>