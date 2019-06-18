<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<style>
.margin{height:100px;width:100%;}
.button_margin{height:20px;width:100%;}
.board_type1_wrap{
max-width:1200px;width:100%;margin:auto;background:white;
text-align: center;
}
.input_box{
height: 20px; width: 200px;
border-radius:10px;background-color: white;
border: 1px solid #333}

.board_list_type1{width:100%;table-layout:fixed;}
.board_list_type1 thead tr{font-size:20px;color:white;background:black;height:60px;}
.board_list_type1 tbody tr{border-bottom:1px solid #e4e4e4;font-size:16px;height:50px;}
.board_list_type1 tbody tr .left{text-align:left;padding-left:10px;padding-right:10px;}
/* 각칸마다 비율*/
.board_list_type1 thead tr th:nth-child(1){width:5%}
.board_list_type1 thead tr th:nth-child(2){width:8%}
.board_list_type1 thead tr th:nth-child(3){width:60%;}
.board_list_type1 thead tr th:nth-child(4){width:10%}
.board_list_type1 thead tr th:nth-child(5){width:12%}

@media only screen and (max-width:760px){
	.active_type{display:none;}
	.board_list_type1 thead tr th:nth-child(1){width:10%}
	.board_list_type1 thead tr th:nth-child(2){width:0%}
	.board_list_type1 thead tr th:nth-child(3){width:90%;}
	.board_list_type1 thead tr th:nth-child(4){width:0%}
	.board_list_type1 thead tr th:nth-child(5){width:0%}
	}
	
tr:hover {
background-color: #f6f6f6;
}
.table_search{
display: inline-block;

}
.table_write{
display : inline-block
float: right;
}

.table_search1 {
   padding: 2px;
   margin: 0 0 0 5px;
   width: 90px;
   height: 23px;
   border: 1px solid #999;
   border-radius: 10px;
   color: black;
   background-repeat: repeat-x;
   background-position: center center;
   float: right;
   background-color: white;
   
}

.table_search1:hover {
background-color: #f6f6f6;
}

.table_write {
   padding: 2px;
   margin: 0 0 0 5px;
   width: 90px;
   height: 23px;
   border: 1px solid #999;
   border-radius: 10px;
   color: black;
   background-repeat: repeat-x;
   background-position: center center;
   background-color: white;
   
}

.table_write:hover {
background-color: #f6f6f6;
}


</style>
</head>
<body>
	<!-- 머리부분-->
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	<!-- 머리부분-->
	<div class="main">

		<div class="main-banner"></div>
		<!-- 게시판 -->
		<div id="main-content">
		
		<div class ="board_type1_wrap">
			
			
		<h1>게시판 [전체글개수: ${pageInfoMap.allRowCount}]</h1>
		<table class="board_list_type1">
		<thead>
		<tr>
			<th class="active_type">No.</th>
		    <th class="active_type">Writer</th>
		    <th class="ttitle">Title</th>
		    <th class="active_type">Date</th>
		    <th cclass="active_type">Read</th>
		</tr>
		</thead>
		<tbody>
		<c:choose>
	<c:when test="${not empty list }">
	

		<c:forEach var="board" items="${list}">
		<tr onclick="location.href='boardDetail.do?num=${board.num}&pageNum=${pageInfoMap.pageNum}'">
			<td class="active_type">${board.num}</td>
			<td class="active_type">${board.name}</td>
			<td class="left">
			<c:if test="${board.re_lev gt 0}"><!-- 답글일때 -->
				<c:set var="wid" value="${board.re_lev * 10}" /><%-- 답글 들여쓰기 레벨 값 저장용 --%>
				<img src="images/center/level.gif" style="width: ${wid}px; height: 13px;">
				<img src="images/center/re.gif">
			</c:if>
				${board.subject}
			</td>
			<td class="active_type">
				<fmt:formatDate value="${board.reg_date }" pattern="yyyy.MM.dd" />

			<td class="active_type">${board.readcount}</td>
		</tr>
		</c:forEach>
	</c:when>

<c:otherwise>
	<tr>
		<td colspan="5" class="active_type">게시판 글 없음...</td>
	</tr>
</c:otherwise>
</c:choose>    
</tbody>
		</table>
		<br>
		          
		<br>
		<br>
		
		<div class="table_form">
		<div class="table_search">
		 <form action="board.do" method="get">
		    <input type="text" name="search" class="input_box" value="${search}">
		    <input type="submit" value="검색" class="table_search1">
		     
		 </form>
		 
		 </div>
		 
		 <c:if test="${sessionScope.name ne null }">
		 <input type="button" value="글쓰기"  class="table_write"
		               onclick="location.href = 'boardWrite.do';"> 
		</c:if>
		
		 </div>

		 
		 <div id="page_control">

<c:if test="${pageInfoMap.allRowCount gt 0 }">
	<!--이전 블록이 존재하는지 확인 -->
	<c:if test="${pageInfoMap.startPage gt pageInfoMap.pageBlockSize }">
	<a href="board.do?pageNum=${pageInfoMap.startPage - pageInfoMap.pageBlockSize}&search=${search}">[이전]
	</a>
	</c:if>
	
	<c:forEach var="i" begin="${pageInfoMap.startPage}" end="${pageInfoMap.endPage}" step="1" >
	<c:choose>
	<c:when test="${i eq pageInfoMap.pageNum }">
	<a href="board.do?pageNum=${i }&search=${search}">
	<span style="color: blue; font-weight: bold;">[${i }]</span>
	</a>
	</c:when>
	<c:otherwise>
	<a href="board.do?pageNum=${i }&search=${search}">[${i }]</a>
	</c:otherwise>
	</c:choose>
	</c:forEach>
	<!-- 다음 블록이 존재하는지 확인 -->
	<c:if test="${pageInfoMap.endPage lt pageInfoMap.maxPage }">
	<a href="board.do?pageNum=${pageInfoMap.startPage + pageInfoMap.pageBlockSize}&search=${search}">[다음]
	</a>
	</c:if>

</c:if>


</div>
		 
		<div class="clear"></div>
		

			</div>
			<!-- main-inside -->
		</div>
		<!-- 게시판 -->
	</div>



	<!-- 다리부분-->
	<hr />
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<!-- 다리부분-->

</body>
</html>