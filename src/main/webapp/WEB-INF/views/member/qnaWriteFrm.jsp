<%@page import="kr.or.iei.member.model.vo.Qna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Qna r = (Qna) request.getAttribute("r");
String memberId = (String) request.getAttribute("memberId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#qnaWrite td, #qnaWrite th {
	border: 1px solid #ccc;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="page-content">
		<div class="page-title">문의 작성</div>
		
		<form action="/qnaWrite.do?writer=<%=m.getMemberId() %>" method="post">
			<table class="tbl" id="qnaWrite">
				<tr class="tr-1">
					<th class="td-3">제목</th>
					<td colspan="3"><input type="text" name="qnaTitle"
						class="input-form"></td>
				</tr>
				<tr class="tr-1">
					<td colspan="7" style="text-align: left;"><textarea
							id="qnaContent" name="qnaContent" class="input-form"></textarea></td>
				</tr>
				<tr>
					<td colspan="7"><button type="submit" class="btn bc1 bs4">문의 등록</button></td>
				</tr>
			</table>
		</form>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>