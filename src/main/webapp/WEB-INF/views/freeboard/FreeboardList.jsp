<%@page import="kr.or.iei.freeboard.model.vo.Freeboard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//여기서꺼낼때 list란이름으로꺼낸게 response였겠죠 그걸 ArrayList로 형변환하려고해서 난에러
    	//에러보실때는 지금제가 블럭친부분보이시죠? 저기를알려주시면 해결이좀더빨라요
    	ArrayList<Freeboard> list = (ArrayList<Freeboard>)request.getAttribute("list");
    	String pageNavi = (String)request.getAttribute("pageNavi");
    %>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="UTF-8" />
   
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
		.freeboard-tbl tr{
			border-bottom:1px solid #ffff;
		}
		.freeboard-tbl th{
			color:lightgray;
			background-color:black;
		}
		.freeboard-tbl tr>th:first-child{
			width:10%;
		}
		.freeboard-tbl tr>th:nth-child(2){
			width:45%;
		}
		.freeboard-tbl tr>td:nth-child(2){
			text-align:left;
		}
		.freeboard-tbl tr>th:nth-child(3){
			width:15%;
		}
		.freeboard-tbl tr>th:nth-child(4){
			width:20%;
		}
		.freeboard-tbl tr>th:last-child{
			width:10%;
		}
	</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">자유게시판</div>
	
		<table class="tbl tbl-hover freeboard-tbl">
		<tr class="tr-2">
				<th>글번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<%for(Freeboard f : list){ %>
			<tr class="tr-1">
				<td><%=f.getFreeNo() %></td>
				<td>
					<a href="/freeboardView.do?freeNo=<%=f.getFreeNo() %>">
						<%=f.getFreeTitle() %>
					</a>
				</td>
				<td><%=f.getFreeDate() %></td>
				<td><%=f.getReadCount() %></td>	
			</tr>
			<%} %>
			
		</table>
		<div id="pageNavi"><%=pageNavi %></div>
		<%if(m!=null){ %>
		<a class="btn bc1 writeBtn" href="/freeboardWriteFrm.do">작성</a>
		<%} %>
</body>
</html>