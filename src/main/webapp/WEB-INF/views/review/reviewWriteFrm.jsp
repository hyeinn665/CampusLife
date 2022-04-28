<%@page import="kr.or.iei.review.model.vo.Subject"%>
<%@page import="kr.or.iei.review.model.vo.Review"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Subject s = (Subject) request.getAttribute("s");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/default.css" />
<link rel="stylesheet" href="/css/header.css" />
<style>
.star-wrap1 {
	overflow: hidden;
	margin:0 auto;
}

.star-wrap1>span {
	font-size: 40px;
	float: left;
	color: lightgray;
}
/* body {
	width: 1200px;
	text-align: center;
	margin: 0 auto;
}
form{
	width:600px;
	margin:0 auto;
}
#reviewWrite {
	width: 300px;
	height: 200px;
	border: 1px solid lightgray;
}
.star-wrap1{
	margin-left:220px;
}
#reviewWrite{
	margin-left:145px;
}

#btn {
	margin-left: 110px;
	margin-top: 20px;
} */
form{
	width:600px;
	margin:0 auto;
	text-align:center;
}
#btn {
	
}
[name=content]{
	height:400px;
}
.reviewWrite-tbl{
	border:1px solid lightgray;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	
	<div class="page-content">
		<div class="page-title">리뷰쓰기</div>
		<form action="/reviewWrite.do?memberNo=<%=m.getMemberNo() %>&subjectNo=<%=s.getSubjectNo()%>"
			method="post">
			
			<table class="tbl tbl-hover reviewWrite-tbl">				
				<tr>
					<td colspan="4"><h1><%=s.getSubjectName()%></h1></td>
				</tr>
				<tr>
					<td colspan="1">
						<div style="text-align:left;">별점</div>
						<div class="star-wrap1">							
							<span class="material-icons" id="star">star</span> <span
								class="material-icons" id="star">star</span> <span
								class="material-icons" id="star">star</span> <span
								class="material-icons" id="star">star</span> <span
								class="material-icons" id="star">star</span>
						</div> <input type="hidden" name="starscore" id="value" value="">
					</td>			
				<tr>
					<td colspan="4"><textarea class="input-form" name="content" style="height:400px;" id="content"></textarea></td>
				</tr>
				<tr>
					<td colspan="4"><button type="submit" class="btn" id="reviewSubmit">등록</button></td>
				</tr>
			</table>
		</form>
	</div>
	<script>
		$(function() {
			const stars = $(".star-wrap1>span");
			let index = 0;
			stars.on("click", function() {
				stars.css("color", "lightgray");
				index = stars.index(this);
				for (let i = 0; i <= index; i++) {
					stars.eq(i).css("color", "gold");
				}
			})
		});

		$(".star-wrap1 #star").on("click", function() {
			var star = $(this).index() + 1;
			const starscore = $(this).parent().next();
			starscore.val(star);
			
		})
		$("#reviewSubmit").on("click",function(event){
			const value=$("#value").val();
			const content=$("#content").val();
				if(value==""){
					alert("별점을 선택하십시오.");
					event.preventDefault();
				}
				if(content.length==0){
					alert("내용을 입력하십시오.");
					event.preventDefault();
				}
			})
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>