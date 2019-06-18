<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body>
<!-- 머리부분-->
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
<!-- 머리부분-->

<div class="container">
        <div class="box700" style="background-color:#ffffff; padding:20px; margin:20px auto">
            <h3>회원정보</h3>
            <hr/>
            
            <div class="form-inline" style="margin-bottom:5px">
                <label style="width:100px">아이디</label>
                <input type="text" class="form-control" value="${member.id}"/>
            </div>
            
            <div class="form-inline" style="margin-bottom:5px">
                <label style="width:100px">이름</label>
                <input type="text" class="form-control" value="${member.name}"/>
            </div>  
             <div class="form-inline" style="margin-bottom:5px">
                <label style="width:100px">생일</label>
                <input type="text" class="form-control" value="${member.birthday}"/>
            </div>
             <div class="form-inline" style="margin-bottom:5px">
                <label style="width:100px">이메일</label>
                <input type="text" class="form-control" value="${member.email}"/>
            </div>    
             <div class="form-inline" style="margin-bottom:5px">
                <label style="width:100px">주소</label>
                <input type="text" class="form-control" value="${member.address}"/>
            </div> 
            
            <div class="form-inline" style="margin-bottom:5px">
                <label style="width:100px">전화번호</label>
              	<input type="text" class="form-control" value="${member.tel}"/>
            </div>
            
            <div class="form-inline" style="margin-bottom:5px">
                <label style="width:100px">전화번호</label>
              	<input type="text" class="form-control" value="${member.reg_date}"/>
            </div>
            
            <hr/>
            
            <div align="center">
            <button class="btn btn-primary">회원정보 수정</button>
            </div>
        </div>
    
    </div>




</body>
</html>