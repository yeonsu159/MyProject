<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
<link href="css/signstyle.css" rel="stylesheet" type="text/css">
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
	
<script src="script/jquery-3.3.1.min.js"></script>
<script>
$(function(){
	

	$('input[name=id]').on('keyup',function(event){
	var id = $(this).val();
	console.log('id변수 타입 : '+ typeof id);
	console.log('id : ' + id);
	
	
	$.ajax({
		url: 'joinIdCheck.do',
		data : {userid:id},
		success : function (isDup) {
			console.log("isDup 변수 타입 " + typeof(isDup));
			console.log('isDup :' + isDup);
			
			if(isDup){	//id중복확인
				$('div#id_check').html('사용 중인 아이디입니다.').css('color', 'red');
// 				$('input[name=id]').val(null).focus();
			} else{
				$('div#id_check').html('사용 가능한 아이디입니다.').css('color', 'white');
// 				$('input[name=pw]').val(null).focus();
			}	
			
		}
		});
	
	});
	
	// 비밀번호 중복 체크
	
});
</script>

<script>
var pwJ = /^[A-Za-z0-9]{4,12}$/; 
$(function(){
	
	$('input[name=id]').blur(function(){
		if($('input[name=id]').val().length < 4){
			$('#id_check').text('아이디는 4글자 이상 입력해 주세요').css('color','white');
		}
	});
	
	$('#pw').blur(function(){ // focus가 빠져나올때 확인가능
		if(pwJ.test($('#pw').val())){
			console.log('true');
			$('#pw_check').text('통과').css('color','white');
		} else{
			console.log('false');
			$('#pw_check').text('숫자 or 문자로만 4~12자리 입력');
			$('#pw_check').css('color', 'red');
		} 
	});
	
	$('#pw2').blur(function(){
		if($('#pw').val() != $(this).val()){
			$('#pw2_check').text('비밀번호가 일치하지 않습니다.').css('color','red');
	
		} else{
			$('#pw2_check').text('');
		}
		
	});

});
</script>

</head>
<body>
<!-- 머리부분-->
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
<!-- 머리부분-->
<div class="wrap">
    <h2>Sign up Here</h2>
    <form action="joinProcess.do" method="post" id="join" name="form_join">
    
        <input type="text" name="id" placeholder="아이디.." required>
        <div id="id_check"></div>
        <input type="password" name="pw" id="pw" placeholder="비밀번호.." required>
        <div id="pw_check"></div>
        <input type="password" name="pw2" id="pw2" placeholder="비밀번호 재확인.." required>
        <div id="pw2_check"></div>
        <input type="text" name="name" placeholder="Name.." required>
        <input type="text" name="email" placeholder="Email.." required>
        <input type="text" name="address" placeholder="주소..." >
        <input type="text" name="phone" placeholder="폰번호..." >
        <label>생년월일</label>
        &nbsp;&nbsp;&nbsp;
        <input type="date" name="birthday" placeholder="생년월일.."><br><br>
        <label>성별</label>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="radio" name="gender" value="여">여자
        <input type="radio" name="gender" value="남">남자<br>
        
        <input type="submit" value="회원가입">
        <input type="reset" value="다시 작성" class="cancel">

    </form>


</div>
</body>
</html>