<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
<link href="css/loginstyle.css" rel="stylesheet" type="text/css">
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
	<script src="script/jquery-3.3.1.min.js"></script>
<script>
$(function(){
	$('#loginfrm').submit(function(){ //form태그를 submit전송했을 때 실행
		var str = $('#loginfrm').serialize();
		console.log('쿼리스트링: ' + str);
		
		$.ajax({
			url : 'loginCheck.do',
			data : str,
			success : function(check){
				console.log('check : ' + check);
				
				if(check == -1){ //아이디 불일치
					$('div#log_check').html('아이디가 일치하지 않습니다.').css('color','white');
				} else if(check == 0){ // 비밀번호 불일치
					$('div#log_check').html('비밀번호가 일치하지 않습니다.').css('color','white');
				} else { // 아이디랑 비밀번호 일치
					location.href = 'main.do';
				}
			}
			
		});
		// submit 보내지말라고 쓴거임
		return false;
	});
});
</script>

</head>

<body>
<!-- 머리부분-->
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
<!-- 머리부분-->
	<div class="login">
		<h2>Log In Here</h2>
		<form  id="loginfrm" method="post">
			<input type="text" name="loginid" id="id" placeholder="Enter id"> 
			<input type="password" name="loginpw" id="pw" placeholder="Enter password">
			<div id="log_check"></div>
			<input type="submit" name="login" value="Log In" id="loginbtn"> 
			<a href="#">Forget Password</a>
		</form>

	</div>

</body>
</html>