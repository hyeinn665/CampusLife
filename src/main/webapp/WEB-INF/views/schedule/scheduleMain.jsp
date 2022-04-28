<%@page import="kr.or.iei.campuslife.vo.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% 
    	ArrayList<Subject> sjl = (ArrayList<Subject>)request.getAttribute("sjl");
    	ArrayList<String> startTimeA = new ArrayList<String>();
    	ArrayList<Integer> startTimeB = new ArrayList<Integer>();
    	ArrayList<Integer> durationTime = new ArrayList<Integer>();
    	ArrayList<String> subjectName = new ArrayList<String>();
		for(int i=0;i<sjl.size();i++){
			startTimeA.add(String.valueOf(sjl.get(i).getStartTime().charAt(0)));
			startTimeB.add(Integer.parseInt(sjl.get(i).getStartTime().substring(1,2)));
			durationTime.add(sjl.get(i).getDurationTime());
			subjectName.add(sjl.get(i).getSubjectName());
		}
		int index = 0;
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시간표 메인</title>
<style>
	.scheduler{
		height: 600px;
	}
	.page-content{
		height: 900px;
	}
	thead{
		background: #F0D9FF;
	}
	td{
		text-align: center;
	}
</style>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div>
		<%@include file="/WEB-INF/views/common/scheduleHeader.jsp" %>
	</div>
	<div class="page-title"><h1>내 시간표</h1></div>
		<div class="page-content">
			<table border="1" width="1200px" height="600px" class="scheduler">
			<thead>
				<th width="200px" height="60px"></th>
				<th width="200px">월</th>
				<th width="200px">화</th>
				<th width="200px">수</th>
				<th width="200px">목</th>
				<th width="200px">금</th>
			<thead>
				<tbody>
				<% for(int i=1;i<13;i++){ %>
					<%for(int j=0;j<6;j++){ %>
						<%if(j==0){%>
						<th><%=i%>교시</th>
						<%}else if(j==1){%>
							<td rowspan="1" id="<%="A"+i%>"></td>
						<%}else if(j==2){%>
							<td rowspan="1" id="<%="B"+i%>"></td>
						<%}else if(j==3){%>
							<td rowspan="1" id="<%="C"+i%>"></td>
						<%}else if(j==4){%>
							<td rowspan="1" id="<%="D"+i%>"></td>
						<%}else if(j==5){%>
							<td rowspan="1" id="<%="E"+i%>"></td>
					<%} %>
					<%} %>
					<tr>
				<%} %>
				</tbody>
			</table>
		</div>
		<script>
		<% for(int i=0;i<sjl.size();i++){ %>
			<%for(int j=0;j<durationTime.get(i);j++){ %>
			$('#'+'<%=startTimeA.get(i)%>'+'<%=startTimeB.get(i) + index%>').css("background","#3CC2FF");
			$('#'+'<%=startTimeA.get(i)%>'+'<%=startTimeB.get(i)%>').attr("rowspan","<%=durationTime.get(i)%>");
			<%if(index>0){%>
			$('#'+'<%=startTimeA.get(i)%>'+'<%=startTimeB.get(i) + index%>').remove();
			<%}%>
				<% index++; %>
			<% } %>
			<%index = 0;%>
			$('#'+'<%=startTimeA.get(i)%>'+'<%=startTimeB.get(i)%>').text("<%=subjectName.get(i)%>");
		<% } %>
		</script>
</body>
</html>