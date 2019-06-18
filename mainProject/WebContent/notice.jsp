<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<style>
.margin{height:100px;width:100%;}
.button_margin{height:20px;width:100%;}
.board_type1_wrap{max-width:1200px;width:100%;margin:auto;background:white;}
/*목록*/

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

.board_list_type1 tbody tr td:nth-child(1){width:5%}
.board_list_type1 tbody tr td:nth-child(2){width:8%}
.board_list_type1 tbody tr td:nth-child(3){width:60%;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;}
.board_list_type1 tbody tr td:nth-child(4){width:10%}
.board_list_type1 tbody tr td:nth-child(5){width:12%}
@media only screen and (max-width:760px){
	.active_type{display:none;}
	.board_list_type1 thead tr th:nth-child(1){width:10%}
	.board_list_type1 thead tr th:nth-child(2){width:0%}
	.board_list_type1 thead tr th:nth-child(3){width:90%;}
	.board_list_type1 thead tr th:nth-child(4){width:0%}
	.board_list_type1 thead tr th:nth-child(5){width:0%}
	
	.board_list_type1 tbody tr td:nth-child(1){width:10%}
	.board_list_type1 tbody tr td:nth-child(2){width:0%}
	.board_list_type1 tbody tr td:nth-child(3){width:90%;}
	.board_list_type1 tbody tr td:nth-child(4){width:0%}
	.board_list_type1 tbody tr td:nth-child(5){width:0%}
}

</style>

</head>
<body>

   <!-- 헤더들어가는 곳 -->
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
   <!-- 헤더들어가는 곳 -->
   <div class="content" data-content="content-2">
	<h2>반응형 게시판 UI</h2>
	<div class="board_type1_wrap">
		<table class="board_list_type1">
			
			<thead>
				<tr>
					<th><input type="checkbox" /></th>
					<th class="active_type">번호</th>
					<th>제목</th>
					<th class="active_type">작성자</th>
					<th class="active_type">작성날짜</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox" /></td>
					<td class="active_type">1</td>
					<td class="left">게시물1번입니다.</td>
					<td class="active_type">관리자</td>
					<td class="active_type">2017-12-19</td>
				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td class="active_type">1</td>
					<td class="left">게시물1번입니다.게시물1번입니다.게시물1번입니다.게시물1번입니다.게시물1번입니다.</td>
					<td class="active_type">관리자</td>
					<td class="active_type">2017-12-19</td>
				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td class="active_type">1</td>
					<td class="left">게시물1번입니다.</td>
					<td class="active_type">관리자</td>
					<td class="active_type">2017-12-19</td>
				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td class="active_type">1</td>
					<td class="left">게시물1번입니다.</td>
					<td class="active_type">관리자</td>
					<td class="active_type">2017-12-19</td>
				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td class="active_type">1</td>
					<td class="left">게시물1번입니다.</td>
					<td class="active_type">관리자</td>
					<td class="active_type">2017-12-19</td>
				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td class="active_type">1</td>
					<td class="left">게시물1번입니다.</td>
					<td class="active_type">관리자</td>
					<td class="active_type">2017-12-19</td>
				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td class="active_type">1</td>
					<td class="left">게시물1번입니다.</td>
					<td class="active_type">관리자</td>
					<td class="active_type">2017-12-19</td>
				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td class="active_type">1</td>
					<td class="left">게시물1번입니다.</td>
					<td class="active_type">관리자</td>
					<td class="active_type">2017-12-19</td>
				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td class="active_type">1</td>
					<td class="left">게시물1번입니다.</td>
					<td class="active_type">관리자</td>
					<td class="active_type">2017-12-19</td>
				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td class="active_type">1</td>
					<td class="left">게시물1번입니다.</td>
					<td class="active_type">관리자</td>
					<td class="active_type">2017-12-19</td>
				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td class="active_type">1</td>
					<td class="left">게시물1번입니다.</td>
					<td class="active_type">관리자</td>
					<td class="active_type">2017-12-19</td>
				</tr>
			</tbody>
		</table>
	</div>
   
   <!-- 푸터들어가는 곳 -->
   	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
   <!-- 푸터들어가는 곳 -->

</body>
</html>