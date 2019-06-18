<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script src="script/jquery-3.3.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script>
//구글 시각화 API를 로딩하는 메소드
google.charts.load('current', {packages: ['corechart']});



function drawChart() {
	columnChart1();
	columnChart2();
	stackedColumnChart1();
	stackedColumnChart2();
	lineChart1();
	lineChart2();
	scatterChart1();
	scatterChart2();
	pieChart1();
}

$(document).ready(function(){
	
	// 구글 시각화 API가 로딩이 완료되면,
	// 인자로 전달된 콜백함수를 내부적으로 호출하여 차트를 그리는 메소드
	google.charts.setOnLoadCallback(function () {
		$.ajax({
			url : 'chartList.do',
			success : function(result){
				console.log('result : ' + result);
				columnChart1(result);
			}
		});
	});
	
	
});

//묶은 세로 막대형 차트 1
function columnChart1(arraylist) {
	
	var dataTable = google.visualization.arrayToDataTable(arraylist);
	// 옵션객체 준비
	var options = {
			title: '거주지 별 인원수',
			hAxis: {
				title: '거주지',
				titleTextStyle: {
					color: 'red'
				}
			}
	};
	// 차트를 그릴 영역인 div 객체를 가져옴 
	var objDiv = document.getElementById('column_chart_div1');
	// 인자로 전달한 div 객체의 영역에 컬럼차트를 그릴수 있는 차트객체를 반환
	var chart = new google.visualization.PieChart(objDiv);
	// 차트객체에 데이터테이블과 옵션 객체를 인자로 전달하여 차트 그리는 메소드
	chart.draw(dataTable, options);
} // drawColumnChart1()의 끝

</script>
</head>
<body>
<!-- 머리부분-->
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
<!-- 머리부분-->
<br>
<br>
<button id="btn">버튼</button>
	<div id="column_chart_div1" style="width: 900px; height: 500px;"></div>
</body>
</html>