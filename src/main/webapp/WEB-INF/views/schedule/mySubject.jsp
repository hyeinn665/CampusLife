<%@page import="java.util.Map"%>
<%@page import="kr.or.iei.campuslife.vo.Subject"%>
<%@page import="kr.or.iei.campuslife.vo.Schedule"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<Schedule> sdl = (ArrayList<Schedule>)request.getAttribute("sdl");
    ArrayList<Subject> sjl = (ArrayList<Subject>)request.getAttribute("sjl");
    Map<Integer,String> profName = (Map<Integer,String>)request.getAttribute("profName");
    Schedule sd = new Schedule();
    Subject sj = new Subject();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 강의</title>
<style>
	thead{
		background: #F0D9FF;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	
	<div>
		<%@include file="/WEB-INF/views/common/scheduleHeader.jsp" %>
	</div>
	<div class="page-title"><h1>내 강의 목록</h1></div>
		<div class="page-content">
			<table border="1" width="1200px">
			<thead>
				<th>강의 번호</th>
				<th>강의 이름</th>
				<th>교수 이름</th>
				<th>강의 시작 시간</th>
				<th>강의 시간</th>
				<th>강의 삭제</th>
			</thead>>
				<% for(int i=0;i<sjl.size();i++){ %>
					<% sj = sjl.get(i); %>
					<th class="subjectNo"><%=sj.getSubjectNo() %></th>
					<th class="subjectName"><%=sj.getSubjectName() %></th>
					<th class="profNo"><%=profName.get(sj.getProfNo()) %></th>
					<th class="startTime"><%=sj.getStartTime() %></th>
					<th class="durationTime"><%=sj.getDurationTime() %></th>
					<th>
					<form action="/deleteMySubject.do">
					<input type="hidden" name="subjectNo" value="<%=sj.getSubjectNo()%>">
					<button type="submit" class="add-subject">강의 삭제</button>
					</form>
					</th>
					<tr>
				<% } %>
			</table>
		</div>
</body>
</html>