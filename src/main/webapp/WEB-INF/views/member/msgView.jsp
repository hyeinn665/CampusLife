<%@page import="kr.or.iei.member.model.vo.Msg"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Msg mv=(Msg)request.getAttribute("mv");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#msgView th,#msgView td{
		border:1px solid #eee;
	}
	#msgContent{
		min-height:300px;
		text-align:left;
		font-family:ns-light;
	}
	.td3{
		background-color:#F0D9FF;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
		<div class="page-content">
			<div class="page-title">쪽지</div>
		<table class="tbl" id="msgView">
			<tr>
				<th class="td3">제목</th>
				<th colspan="5"><%=mv.getMsgTitle() %></th>
			</tr>
			<tr class="tr-1">
				<th class="td3">작성자</th>
				<td><%=mv.getWriter() %></td>
				<th class="td3">작성일</th>
				<td><%=mv.getReqDate() %></td>
			</tr>
			<tr class="tr-1">
				<th class="td3">내용</th>
				<td colspan="5">
					<div id="msgContent"><%=mv.getMsgContentBr() %></div>
				</td>
			</tr>
			<%if(!mv.getWriter().equals(m.getMemberId())) {%>
			<tr>
				<td colspan="6"><a href="/writeMsgFrm.do?memberId=<%=mv.getWriter()%>"  class="btn bc1 bs4"> 답장하기</a></td>
			</tr>
			<%} %>
		</table>
		</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>