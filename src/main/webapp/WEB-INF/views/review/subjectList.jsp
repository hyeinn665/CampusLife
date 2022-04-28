<%@page import="kr.or.iei.member.model.vo.Schedule"%>
<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@page import="kr.or.iei.review.model.vo.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.iei.review.model.vo.SelectiveSubject"%>
<%@page import="kr.or.iei.review.model.vo.RequiredSubject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<Subject> rList = (ArrayList<Subject>) request.getAttribute("rList");
ArrayList<Subject> sList = (ArrayList<Subject>) request.getAttribute("sList");
/* RequiredSubject rs=(RequiredSubject)request.getAttribute("rs");
SelectiveSubject ss=(SelectiveSubject)request.getAttribute("ss"); */
Schedule tt= (Schedule)request.getAttribute("tt");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/default.css" />
<link rel="stylesheet" href="/css/header.css" />
<style>
/* .body {
	width:1200px
	text-align: center;
	margin: 0 auto;
}

table {
	width: 1200px;
	background-color:white;
}

#th {
	height: 100px;
}

table tr>th:first-child {
	width: 20%;
}

table tr>th:nth-child(2) {
	width: 35%;
}

table tr>th:nth-child(3) {
	width: 25%;
}

table tr>th:last-child {
	width: 20%;
}

#r-table, #s-table {
	margin-top: 100px;
}

td {
	height: 50px;
}

button>a {
	text-decoration: none;
} */

		.subject-tbl a:hover{
			text-decoration:underline;
		}
		.subject-tbl tr{
			border-bottom:1px solid #ccc;
		}
		.subject-tbl th{
			background-color:#BFA2DB;
		}
		.subject-tbl tr>th:first-child{
			width:20%;
		}
		.subject-tbl tr>th:nth-child(2){
			width:35%;
		}
		.subject-tbl tr>th:nth-child(3){
			width:25%;
		}
		.subject-tbl tr>th:last-child{
			width:20%;
		}
		#btn{
			border:1px solid lightgray;
			border-radius:5px;
			padding:5px;
		}
		.t1{
			min-height:200px;
		}
td{
	border-right:1px solid lightgray;
}
td:last-child{
	border-right:none;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
		<div class="page-content">
			<div class="page-title">필수과목</div>
			<div class="t1">
				<table class="tbl tbl-hover subject-tbl">
					<tr class="tr-1">
						<th>번호</th>
						<th>과목명</th>
						<th>평점</th>
						<th></th>
					</tr>
					<%if(tt!=null) {%>
						<%if(m.getMemberNo()==tt.getMemberNo()) {%>
							<%for (Subject rs2 : rList) {%>
							<tr class="tr-1">
								<td><%=rs2.getSubjectNo()%></td>
								<td><%=rs2.getSubjectName()%></td>
								<td><%=rs2.getScoreAvg()%></td>
								<td>
									<a href="/reviewWriteFrm.do?memberNo=<%=m.getMemberNo() %>&subjectNo=<%=rs2.getSubjectNo()%>" id="btn">리뷰쓰기</a>
								</td>
							</tr>
							<%}%>
						<%}%>
					<%}%>
				</table>
			</div>
			<div class="t1">
			<table class="tbl tbl-hover subject-tbl t2">
				<div class="page-title">선택과목</div>
				<tr class="tr-1">
					<th>번호</th>
					<th>과목명</th>
					<th>평점</th>
					<th></th>
				</tr>
				<%if(tt!=null) {%>
					<%if(m.getMemberNo()==tt.getMemberNo()) {%>
						<%for (Subject ss2 : sList) {%>
						<tr class="tr-1">
							<td><%=ss2.getSubjectNo()%></td>
							<td><%=ss2.getSubjectName()%></td>
							<td><%=ss2.getScoreAvg()%></td>
							<td>
								<a href="/reviewWriteFrm.do?subjectNo=<%=ss2.getSubjectNo()%>" id="btn">리뷰쓰기</a>								
							</td> 
						</tr>
						<%}%>
					<%}%>
				<%}%>
			</table>
			<div class="t1">
		</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>