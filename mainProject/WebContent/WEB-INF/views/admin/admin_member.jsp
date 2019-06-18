<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_memberList</title>
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script src="script/jquery-3.3.1.min.js"></script>

<script>
$(document).ready(function () {
	
	$('button#delete').on('click', function () {
		var str = $('form#frm').serialize();
		console.log(str);
		
		$.ajax({
			url: 'ad_Delete.do',
			data: str,
			success: function () {
				alert('삭제완료');
				location.reload();
			}
		});
		
	});
	
	
	
// 	$('button#update').on('click', function () {
// 		var str = $('form#frm').serialize();
// 		console.log(str);
		
// 		$.ajax({
// 			url: '',
// 			data: str,
// 			success: function () {
// 				alert('수정완료');
// 				location.reload();
// 			}
// 		});
		
// 	});
	
	
	
});


</script>

<style>
#limiter{
	margin: auto auto auto auto;

}

td{
border : 1px solid black;
}
</style>
</head>
<body>
	<!-- 머리부분-->
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	<!-- 머리부분-->

	<div id="limiter">
		<form method="post" id="frm">
		<table>
		<caption>회원 개인정보</caption>
			<tr>
			
				<td>ID</td>
				<td>PW</td>
				<td>NAME</td>
				<td>BIRTHDAY</td>
				<td>GENDER</td>
				<td>EMAIL</td>
				<td>ADDRESS</td>
				<td>TEL</td>
				<td>REG_DATE</td>
				<td>삭제</td>
				<td>수정</td>
				<td>선택</td>
			</tr>
			
			<c:forEach var="member" items="${list}">
				<tr>
					<td>${member.id}</td>
					<td>${member.password}</td>
					<td>${member.name}</td>
					<td>${member.birthday}</td>
					<td>${member.gender}</td>
					<td>${member.email}</td>
					<td>${member.address}</td>
					<td>${member.tel}</td>
					<td>${member.reg_date}</td>
					<td><a href="MemberDeleteAction.do?id=${member.id}">삭제</a></td>
            		<td><a href="memberupdate.do?id=${member.id}">수정</a></td>
             		<td class="chk">
             		<input type="checkbox" value="${member.id }" class="check" name="check"/>
             		</td>
             		
				</tr>
			</c:forEach>
		</table>
		</form>
		<button type="button" id="delete">탈퇴</button>
		<button type="button" id="update">수정</button>
	</div>

	<!-- 다리부분-->
	<hr />
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<!-- 다리부분-->
</body>
</html>