<%@page import="kr.or.iei.member.model.vo.Msg"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Msg r = (Msg) request.getAttribute("r");
String memberId = (String) request.getAttribute("memberId");
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
		<div class="page-title">쪽지 작성</div>
		
		<form action="/msgWrite.do" method="post">
			<table class="tbl" id="msgWrite">
				<tr class="tr-1">
					<th class="td-3">제목</th>
					<td colspan="3">
					<input type="text" name="msgTitle" class="input-form"></td>
					<th class="td-3">수신자</th>
					<%if(!m.getMemberId().equals(memberId)) {%>
						<td>
							<input type="hidden" name="receiver" value="<%=memberId%>">
							<%=memberId%>				
							<input type="hidden" name="writer" value="<%=m.getMemberId()%>">
						</td>
					<%} else{ %>
						<td style="width:162px;">
							<input type="text" name="receiver" class="input-form">
							<input type="hidden" name="writer" value="<%=m.getMemberId()%>">
						</td>
					<%} %>
				</tr>
				<tr class="tr-1">
					<td colspan="7" style="text-align: left;"><textarea
							id="msgContent" name="msgContent" class="input-form"></textarea></td>
				</tr>
				<tr>
					<td colspan="7"><button type="submit" class="btn bc1 bs4 send">쪽지보내기</button></td>
				</tr>
			</table>
		</form>
	</div>
	<script>
	$(".send").on("click",function(event){
		const title=$("[name=msgTitle]").val();
		const content=$("[name=msgContent]").val();
		const receiver=$("[name=receiver]").val();
		if(title.length==0){
			alert("제목을 입력하십시오.");
			event.preventDefault();
		} else if(content.length==0){
			alert("내용을 입력하십시오.");
			event.preventDefault();
		} else if(receiver.length==0){
			alert("수신자를 입력하십시오.");
			event.preventDefault();
		}
	})
		
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>