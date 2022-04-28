<%@page import="kr.or.iei.review.model.vo.Review"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<Review> list = (ArrayList<Review>) request.getAttribute("list");
String pageNavi = (String) request.getAttribute("pageNavi");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/default.css" />
<link rel="stylesheet" href="/css/header.css" />
<style>
/* body {
	width: 1200px;
	text-align: center;
	margin: 0 auto;
}

table {
	width: 1200px;
}

#title {
	text-align: left;
}

#th {
	height: 100px;
}

table {
	border: 1px solid lightgray;
}

table tr {
	border-bottom: 1px solid #ccc;
}

table th {
	background-color: lightgray;
}

table td {
	border: 1px solid lightgray;
	height: 30px;
}

table tr>th:first-child {
	width: 10%;
}

table tr>th:nth-child(2) {
	width: 15%;
}

table tr>th:nth-child(3) {
	width: 45%;
}

table tr>th:nth-child(4) {
	width: 10%;
}

table tr>th:last-child {
	width: 20%;
} */

.star-wrap1 {
	overflow: hidden;
}

.star-wrap1>span {
	margin-left: 2px;
	font-size: 20px;
	float: left;
	color: gold;
}

.review-tbl a:hover{
	text-decoration:underline;
}
.review-tbl tr{
	border-bottom:1px solid #ccc;
}

.review-tbl th{
	background-color:#BFA2DB;
}
.review-tbl tr>th:first-child{
	width:3%;
}
.review-tbl tr>th:nth-child(2){
	width:7%;
}
.review-tbl tr>th:nth-child(3){
	width:10%;
}
.review-tbl tr>th:nth-child(4){
	width:55%;
}
.review-tbl tr>th:nth-child(4){
	width:55%;
}
.review-tbl tr>th:nth-child(5){
	width:15%;
}
.review-tbl tr>th:last-child{
	width:10%;
}
.review-tbl tr th:first-child,.review-tbl tr td:first-child{
	background-color:white;
	border-bottom:1px solid white;
}
td{
	border-right:1px solid lightgray;
}
td:first-child, td:last-child{
	border-right:none;
}
</style>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">강의리뷰</div>
		<table class="tbl tbl-hover review-tbl">
			<tr class="tr-1">
				<th><%if(m!=null) {%>
					<%if(m.getGrade().equals("관리자")) {%>
					<input type="checkbox" id="allChk">
					<%} %>
					<%} %></th>
				<th>번호</th>
				<th>과목명</th>
				<th>리뷰내용</th>
				<th>별점</th>
				<th>작성일</th>
			</tr>
			<%for (Review r : list) {%>
			<tr class="tr-1">
				<td><%if(m!=null) {%>
					<%if(m.getGrade().equals("관리자")) {%>
					<input type="checkbox" class="chk">
					<%} %>
					<%} %>
				</td>
				<td><%=r.getReviewNo()%></td>
				<td><%=r.getSubjectName()%></td>
				<td><div id="rc" style="max-height:300px; overflow:hidden;"><%=r.getReviewContent()%></div></td>
				<td>
					<%if (r.getReviewScore() == 5) {%>
					<div class="star-wrap1">
						<%for (int i = 0; i < 5; i++) {%>
						<span class="material-icons" id="star">star</span>
						<%}%>
					</div> <%}%> 
					<%if (r.getReviewScore() == 4) {%>
					<div class="star-wrap1">
						<%for (int i = 0; i < 4; i++) {%>
						<span class="material-icons" id="star">star</span>
						<%}%>
					</div> <%}%> 
					<%if (r.getReviewScore() == 3) {%>
					<div class="star-wrap1">
						<%for (int i = 0; i < 3; i++) {%>
						<span class="material-icons" id="star">star</span>
						<%}%>
					</div> <%}%> 
					<%if (r.getReviewScore() == 2) {%>
					<div class="star-wrap1">
						<%for (int i = 0; i < 2; i++) {%>
						<span class="material-icons" id="star">star</span>
						<%}%>
					</div> <%}%> 
					<%if (r.getReviewScore() == 1) {%>
					<div class="star-wrap1">
						<%for (int i = 0; i < 1; i++) {%>
						<span class="material-icons" id="star" style="text-align: center;">star</span>
						<%}%>
					</div> <%}%>
				</td>
				<%-- <td><%=r.getReviewScore()%></td> --%>
				<td><%=r.getReqDate()%></td>
			</tr>
			<%
			}
			%>
			
		</table>
		<br>
		<div>
			<%if(m!=null) {%>
				<%if(m.getGrade().equals("관리자")) {%>
					<button class="btn bc44 bs1 deleteReview">리뷰삭제</button>
				<%} %>
			<%} %>
		</div>
		<br> 
		<br>
		<div id="pageNavi"><%=pageNavi%></div>
	</div>
	<script>
	$(".deleteReview").on("click",function(){
		const check=$(".chk:checked");
		if(check.length==0){
			alert("선택된 리뷰가 없습니다.");
			return;
		}
		const reviewNos=new Array();
		const subjectNames=new Array();
		check.each(function(index,item){
			reviewNos.push($(item).parent().next().text());
			subjectNames.push($(item).parent().next().next().text());
		});
		location.href="/deleteReview.do?reviewNos="+reviewNos.join("/")+"&subjectNames="+subjectNames.join("/");
	});
	$("#allChk").on("click",function () { 
		if ($("#allChk").prop("checked")) {
			$(".chk").prop("checked", true);
		} else {
			$(".chk").prop("checked", false); 
		} 
	});
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>