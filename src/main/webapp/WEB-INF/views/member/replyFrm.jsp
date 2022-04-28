<%@page import="kr.or.iei.member.model.vo.Qna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Qna r = (Qna) request.getAttribute("r");
String writer = (String) request.getAttribute("writer");
String qnaTitle=(String) request.getAttribute("qnaTitle");
int qnaNo=(int)request.getAttribute("qnaNo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#msgWrite td, #msgWrite th {
	border: 1px solid #ccc;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="page-content">
		<div class="page-title">문의 답변 작성</div>
		
		<form action="/reply.do?qnaNo=<%=qnaNo %>" method="post">
			<table class="tbl" id="msgWrite">
				<tr class="tr-1">
					<th class="td-3">제목</th>
					<td colspan="3"><input type="text" name="qnaTitle"
						class="input-form" value="re) <%=qnaTitle %>"></td>
					<th class="td-3">수신자</th>
					<td><input type="hidden" name="receiver" value="<%=writer%>">
						<%=writer%></td>
						<input type="hidden" name="writer" value="<%=m.getMemberId()%>">
				</tr>
				<tr class="tr-1">
					<td colspan="7" style="text-align: left;"><textarea
							id="msgContent" name="qnaContent" class="input-form"></textarea></td>
				</tr>
				<tr>
					<td colspan="7"><button type="submit" class="btn bc1 bs4">답변 작성</button></td>
				</tr>
			</table>
		</form>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>