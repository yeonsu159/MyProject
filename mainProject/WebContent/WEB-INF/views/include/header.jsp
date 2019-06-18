<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//세션값 가져오기
String name = (String)session.getAttribute("name");
String id = (String)session.getAttribute("id");
%>
<div class="menu">
		<a href="main.do" class="brand"><i class="material-icons">import_contacts</i></a>
		<nav>
			<ul>
				<li><a href="main.do">Home</a></li>
				<li><a href="#">About</a>
					<ul>
						<li><a href="#">List 1</a></li>
						<li><a href="#">List 2</a></li>
						<li><a href="#">List 3</a></li>
						<li><a href="#">List 4</a></li>
					</ul>
				</li>
				<%
				if(name != null && name.equals("관리자")){
				%>
				<li>
				<a href="#">관리자 운영</a>
					<ul>
						<li><a href="memberList.do">회원 보기</a></li>
						<li><a href="chart.do">통계</a></li>
						<li><a href="email.do">이메일 관리</a>
					</ul>
				
				</li>
				<%
				}
				%>
				<%
				if(name != null){
				%>
				<li>
				<a href="mylist.do?id=<%=id %>">마이페이지</a>
				</li>
				<%
				}
				%>
				<li><a href="board.do">게시판</a></li>
				<li>
				<%
				if(name == null){
				%>
				<a href="login.do">로그인</a>
				<%
				}else{ 
				%><a><%=name%>&nbsp;님</a>
				<% 
				}
				%>
				</li>
				<li>
				<%
				if(name == null){
				%>
				<a href="join.do">회원가입</a>
				<%
				}else{ 
				%>
				<a href="logout.do">로그아웃</a> 
				<% 
				}
				%>
				</li>
			</ul>
		</nav>
	</div>