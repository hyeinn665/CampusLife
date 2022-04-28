<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.iei.member.model.vo.Qna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%	
    	ArrayList<Qna> list=(ArrayList<Qna>)request.getAttribute("list");
    	String pageNavi=(String)request.getAttribute("pageNavi");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		.msg-tbl a:hover{
			text-decoration:underline;
		}
		.msg-tbl tr{
			border-bottom:1px solid #ccc;
		}
		.msg-tbl th{
			background-color:#BFA2DB;
		}
		.msg-tbl tr>th:first-child{
			width:60%;
		}
		.msg-tbl tr>td:nth-child(2){
			padding-left:70px;
			overflow:hidden;
			text-align:left;
		}
		.msg-tbl tr>th:nth-child(2){
			width:20%;
		}
		.msg-tbl tr>th:last-child{
			width:20%;
		}
td>.navi {
  list-style-type: none;
}
td > .navi > li {
  position:relative;
}
td > .navi > li > a {
  display: block;
}
td > .navi > li:hover > .sub-navi {
  display : block;
}
td > .navi .sub-navi {
  position: absolute;
  background-color: #fff;
  list-style-type: none;
  display: none;
  border: 1px solid rgba(0, 0, 0, 0.2);
  left: 50px;
  min-width: 7rem;
}
	</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">1:1 문의</div>
		<a class="btn bc4 writeBtn" href="/qnaWriteFrm.do?memberId=<%=m.getMemberId()%>">문의하기</a>
		<table class="tbl tbl-hover msg-tbl">
			<tr>
				<th>제목</th><th>작성자</th><th>작성일</th>
			</tr>
				<%for(Qna l : list){ %>
				<tr class="tr-1">
					<td style="display:none;"><%=l.getQnaNo() %></td>
					<td><%if(l.getWriter().equals(m.getMemberId())) {%><a href="/qnaView.do?qnaNo=<%=l.getQnaNo()%>"><%=l.getQnaTitle() %></a>
					<%} else {%>
						<a href="/qnaView.do?qnaNo=<%=l.getQnaNo()%>" style="color:rgba(185, 31, 206, 0.863);"><%=l.getQnaTitle() %></a>
						<%} %></td>
					<td>
					<ul class="navi">						
						<%if(l.getWriter().equals(m.getMemberId())) {%>
						<li><%=l.getWriter() %></li>
						<%} else {%>
						<li style="color:rgba(185, 31, 206, 0.863);"><%=l.getWriter() %></li>
						<%} %>						
					</ul></td>
					<td><%=l.getReqDate() %></td>
				</tr>
				<%} %>
		</table>
		<div id="pageNavi"><%=pageNavi %></div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>