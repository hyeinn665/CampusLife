<%@page import="kr.or.iei.managerboard.model.vo.ManagerBoard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    ArrayList<ManagerBoard> list = (ArrayList<ManagerBoard>)request.getAttribute("list");
    String pageNavi = (String)request.getAttribute("pageNavi");
	String category = (String)request.getAttribute("category");
	//ArrayList<ManagerBoard> searchlist = (ArrayList<ManagerBoard>)request.getAttribute("searchlist");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.managerBoard-tbl a:hover{
		text-decoration: underline;
	}
	.managerBoard-tbl tr{
		border-bottom: 1px solid #ccc;
	}
	.managerBoard-tbl tr>th:first-child{
		width:10%;
	}
	.managerBoard-tbl tr>th:nth-child(2){
		width:45%;
	}
	.managerBoard-tbl tr>td:nth-child(2){
		text-align:left;
	}
	.managerBoard-tbl tr>th:nth-child(3){
		width:15%
	}
	.managerBoard-tbl tr>th:nth-child(4){
		width:20%
	}
	.managerBoard-tbl tr>th:nth-child(5){
		width:10%
	}
	#pageNavi{
		margin: 30px;
	}
	.category-row{
	border-right: 1px solid #eee8e8d1;
	border-left: 1px solid #eee8e8d1;
	}
	.managerBoard-tbl tr>th:nth-child(2) {
    width: 13%;
    }
    .managerBoard-tbl tr>th:nth-child(3) {
    width: 35%;
    }
  	.managerBoard-tbl tr>td:nth-child(2) {
    text-align: center;
    }
	.bc33{
		width: 200px;
		height: 90px;
		border-radius: 10px;
		margin-bottom:0;
	}
	.bc33>span{
		color : #ccc;
		font-size: 1.5em;
	}
	.qna{
		margin-left:30px;
		width: 350px;
	}
	.qna:hover{
		
	}
	.c1,.c2,.c3,.c4{
		width:160px;	
	}
	#input1{
    width: 100%;
    height: 40px;
    line-height: 20px;
    padding: 10px;
    box-sizing: border-box;
    border: 2px solid #d6d6d6;
    color: #292929;
    font-size: 14px;
    }
    .categoryBtn{
    	width:237px;
    }

}
	
</style>
<title>Insert title here</title>
</head>
<body>
	<%@include file = "/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title" style="text-align:center; font-size:2em; margin-bottom:40px; border-bottom:6px solid #BFA2DB">
			<span>공지사항</span>
		</div>
		<div class="managerBoard-detail" value="<%=category%>">
			<button class="bc33 c1 categoryBtn" id="c0"><span>전체</span></button>
			<button class="bc33 c1 categoryBtn" id="c1"><span>학과 공지사항</span></button>
			<button class="bc33 c2 categoryBtn" id="c2"><span>공모전</span></button>
			<button class="bc33 c3 categoryBtn" id="c3"><span>동아리/학회</span></button>
			<button class="bc33 c4 categoryBtn" id="c4"><span>취업지원</span></button>
			<!-- <button class="bc33 qna" id="qna"><span>Q&A</span></button> -->
		</div>
		<br><br>
		
		<!-- 검색기능 -->
			<ul class="up-navi" style="list-style-type:none;display: inline-block;float:right;">
				<li class="up1" style="text-align:right;margin-bottom:10px;">
					<% if(m !=null && m.getGrade().equals("관리자")) {%>
					<a class="btn bc8 writerBtn" href="/managerBoardWriteFrm.do" style="height:50px;line-height:15px;width:100px;text-align:center;border-radius:10px;">
					글쓰기</a>
					<% }%>						
				</li>
				<li class="up2" style="text-align:right;margin-bottom:10px;">
					<form action="/searchManagerBoard.do" style="width:400px;text-align:right;">
						<input id="input1" type="hidden" name="reqPage" value="1" >
						<select name="searchType"style="height:35px;width:80px;border: 3px solid #BFA2DB;color:purple;">
							<option ${(param.searchType=="title")?"selected":""} value="title"><span>제목</span></option>
							<option ${(param.searchType=="content")?"selected":""} value="content">내용</option>
						</select> 
						<% if(request.getParameter("keyword")==null) {%>
						<input type="text" name="keyword" style="height:30px;border: 3px solid #BFA2DB;">
						<%}else{ %>
						<input type="text" name="keyword" value="<%=request.getParameter("keyword")%>" style="height:30px;border: 3px solid #BFA2DB;">
						<%} %>
						<input id="input2" type="submit" value="검색" style="width:70px;height:35px;border: 3px solid #BFA2DB;color:purple;">
					</form>	
				</li>
			</ul>			
		
		<div class="t">
		<table class="tbl tbl-hover managerBoard-tbl">
			<tr class="tr-2">
				<th>번호</th><th>분류</th><th style="width:500px;">제목</th><th style="width:100px;">작성자</th><th style="width:150px;">작성일</th><th>조회수</th>
			</tr>
			<%for(ManagerBoard mb : list) {%>
			<tr class="tr-1">
				<td style="border-right: 1px solid #eee8e8d1;"><%=mb.getMbNo() %></td>
				<td style="border-right: 1px solid #eee8e8d1;"><%=mb.getCategory() %></td>
				<td>
					<a href="/managerBoardView.do?mbNo=<%=mb.getMbNo()%>"><%=mb.getMbTitle() %></a>
				</td>
				<td class="category-row"><%=mb.getMbWriter()%></td>
				<td style="border-right: 1px solid #eee8e8d1;"><%=mb.getRegDate() %></td>
				<td><%=mb.getReadCount() %></td>
			</tr>
			<%} %>
		</table>
		<div id="pageNavi"><%=pageNavi %></div>
		</div>
	</div>
	
	<!-- 검색 테이블 -->
	
	
	<script>
	//카테고리별 버튼
		const selectCategory = $(".managerBoard-detail").attr("value");
		$(".managerBoard-detail>button").each(function(index,item){
			if($(item).children().text() == selectCategory){
				$(item).children().css("color","#B762C1");
				$(item).children().css("font-size","1.7em");
			}
		});
		$(".categoryBtn").on("click",function(){
			const category = $(this).children().text();
			location.href="/managerBoardList.do?reqPage=1&category="+category;
		});
		
	//qna 버튼
		$("#qna").on("click",function(){
				
		})

	
	
	</script>
	<%@include file = "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>