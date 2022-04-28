<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.iei.member.model.vo.Msg"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%	
    	ArrayList<Msg> list=(ArrayList<Msg>)request.getAttribute("list");
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
			width:5%;
		}
		.msg-tbl tr>th:nth-child(2){
			width:55%;
		}
		.msg-tbl tr>td:nth-child(3){
			text-align:center;
		}
		.msg-tbl tr>th:nth-child(3){
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
		<div class="page-title">받은 쪽지</div>
		<table class="tbl tbl-hover msg-tbl">
			<tr>
				<th><input type="checkbox" id="allChk"></th><th>제목</th><th>작성자</th><th>받은 날짜</th>
			</tr>
			<%for(Msg l : list){ %>
			<tr class="tr-1">
				<td><input type="checkbox" class="chk"></td>
				<td style="display:none;"><%=l.getMsgNo() %></td>
				<td><a href="/msgView.do?msgNo=<%=l.getMsgNo()%>"><%=l.getMsgTitle() %></a></td>
				<td>
				<ul class="navi">
					<li><a href="#"><%=l.getWriter() %></a>
						<ul class="sub-navi">
							<li><a href="/writeMsgFrm.do?memberId=<%=l.getWriter()%>">쪽지보내기</a></li>
						</ul>
					</li>
				</ul></td>
				<td><%=l.getReqDate() %></td>
			</tr>			
			<%} %>
		</table>
		<br>
		<div>
			<button class="btn bc44 bs1 deleteMsg">삭제</button>
		</div>
		<div id="pageNavi"><%=pageNavi %></div>
	</div>
	<script>
	$(".deleteMsg").on("click",function(){
		const check=$(".chk:checked");
		if(check.length==0){
			alert("선택된 쪽지가 없습니다.");
			return;
		}
		const msgNos=new Array();
		check.each(function(index,item){
			msgNos.push($(item).parent().next().text());
		});
		location.href="/deleteMsg.do?delRec=y&msgNos="+msgNos.join("/");
	});
	$("#allChk").on("click",function () { 
		if ($("#allChk").prop("checked")) {
			$(".chk").prop("checked", true);
		} else {
			$(".chk").prop("checked", false); 
		} 
	});
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>