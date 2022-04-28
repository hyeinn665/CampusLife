<%@page import="kr.or.iei.freeboard.model.vo.Freeboard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Freeboard f = (Freeboard)request.getAttribute("f");

    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style>
	#freeboardView th,#freeboardView td{
		border:1px solid #eee;
	}
	#freeContent{
		min-height:300px;
		text-align:left;
		font-family:ns-light;
	}
	.page-title{
	text-align: center;
	}
	.page-titlet>.th{
	color:lightgray;
	background-color: black;
	}
	</style>
	
</head>
<body>
	<%@include file = "/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">자유게시판</div>
		<table class="tbl" id="freeboardView">
			<tr class="tr-3">
				<th colspan="6"><%=f.getFreeTitle() %></th>
			</tr>
			
				<tr class="tr-1">
				
					<th class = "td-1">작성일</th>
					<td><%=f.getFreeDate() %></td>
					<th class= "td-1">조회수</th>
					<td><%=f.getReadCount() %></td>
				</tr>
				
				
				<tr class="tr-1">
					<th class="td-3">첨부파일</th>
					<td colspan="5">
						<%if(f.getFileName() != null){ %>
							<img src="/img/file.png" width="16px">
							<a href="/fileDown.do?freeNo=<%=f.getFreeNo() %>"><%=f.getFileName() %></a>
						<%} %>
					</td>
				</tr>
				
				
				<tr class="tr-1">
					<td colspan="5">
						<div id="freeContent"><%=f.getFreeContentBr() %></div>
					</td>
				</tr>
				
				
				<%if(m!=null && (m.getMemberNo())==(f.getMemberNo()) || m.getMemberNo()==1) { %>
				<tr class="tr-1">
					<th colspan="6">
						<a class="btn bc11" href="/freeboardUpdateFrm.do?freeNo=<%=f.getFreeNo() %>">수정</a>
						<button class="btn bc11" onclick="freeboardDelete('<%=f.getFreeNo() %>');">삭제</button>
					</th>
				</tr>

				<%} else{%>
				<tr class="tr-1">
					<th colspan="6">
						</th>
				</tr>
				<%} %>
		</table>
		<script>
			function freeboardDelete(freeNo){
				if(confirm("삭제하시겠습니까?")){
					location.href="/freeboardDelete.do?freeNo="+freeNo;
				}
			}
		</script>
	</div>

</body>
</html>