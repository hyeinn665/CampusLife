<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.HashMap"%>
<%@page import="kr.or.iei.book.model.vo.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<Book> list = (ArrayList<Book>)request.getAttribute("list");
        	String pageNavi = (String)request.getAttribute("pageNavi");
        	HashMap<Integer,String> map = (HashMap<Integer,String>)request.getAttribute("map"); 
        	int totalCount = (int)request.getAttribute("totalCount");
        	int pageNo = (int)request.getAttribute("pageNo");
    %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
    	.page-search>*{
    		margin: 5px auto;
			text-align: center;
			display: block;
			
    	}
    	.page-search{
    		width:70%;
    		padding: 20px;
    		padding-bottom : 0;
    		margin: 0 auto;
    		position: relative;
    	}
    	.page-search>span{
    		color:#595959;
    	}
    	.bookList{
    		margin-top: 90px;
    	}
    	.input-group{
    		padding-top: 50px;
    		width: 70%;
    		margin: 0 auto;
    	}
    	.oneBook{
    		overflow: hidden;
    		padding: 15px 30px;
    		border: 1px solid #CCCCCC;
    		border-bottom: none;
    		width: 70%;
    		box-sizing: border-box;
    		margin: 0 auto;
    	}
    	.oneBook:hover{
    		background-color: #F0ECEB;
    	}
    	.oneBook>a>div{
    		float : left;    		
    	}
    	.bookList>div:first-child{
    		border-top-right-radius: 10px;
    		border-top-left-radius: 10px;
    	}
    	.bookList>div:nth-last-child(2){
    		border-bottom-left-radius: 10px;
    		border-bottom-right-radius: 10px;
    		border-bottom: 1px solid #CCC;
    	}
    	.book-img{
    		border: 1px solid #ccc;
    		height: 120px;
    		width: 80px;
    		border-radius: 10px;
    	}
    	.book-title{
    		color:#474747;
    	}
    	.detail{
    		color : #666666;
    		font-size: 0.8rem;
    		height: 20px;
    		margin: 0;
    	}
    	.book-info{
    		padding-left: 30px;
    		height: 100px;
    		width: 60%;
    	}
    	.price{
    		font-size: 1.1rem;
    		color: #FF5959;
    	}
    	.tr-3:first-child>td:first-child {
			width:130px;
			text-align: center;
		}
		.tbl th, .tbl td{
			text-align: left;
		}
		.sellend{
			width:80px;
			height: 40px;
			border-radius: 10px;
			background-color: #50445C;
			color:#fff;
			border: none;
			margin: 40px auto;
		}
		.num{
			width: 6%;
		}
		.num>p{
			line-height:120px;
			color:#50445C;
			font-size: 15px;
		}
		.btn-box{
			overflow: hidden;
			width: 70%;
			margin: 10px auto;
		}
		.btn-box>a{
			float:right;
			background-color: #BFA2DB;
			color:#fff;
			border-radius: 15px;
		}
		.btn-box>a:hover{
			background-color: #B79BD1;
		}
		.searchForm{
			width: 80%;
			margin: 20px auto;
			margin-bottom: 0;
			padding-top: 10px;
		}
		.searchForm>div{
			width:78%;
			margin: 0;
			display: inline-block;
		}
		.searchForm input{
			height:50px;
			display: inline-block;
			font-size: 15px;
			border: 2px solid #BFA2DB;
		}
		.searchForm input:focus{
			outline: none;
		}
		.searchForm button{
			width: 10%;
			height:50px;
			border-top-right-radius: 10px;
			border-bottom-right-radius: 10px;
			font-size: 15px;
		}
		[name=searchSelect]{
			width: 12%;
			border: 2px solid #BFA2DB;
			border-top-left-radius: 10px;
			border-bottom-left-radius: 10px;
			height:50px;
			border-right: none;
			font-size: 15px;
			color:#666;
		}
		[name=searchSelect]:focus{
			outline: none;
		}
		.bc9{
			background-color: #BFA2DB;
			border: none;
			color:#fff;
		}
		.bc9:hover{
			background-color: #B79BD1;
		}
		.bc12{
			background-color: #774599;
			border: 2px solid #F0D9FF;
			color:#fff;
		}
		.listPrice{
			text-decoration: line-through;
			font-size: 15px;
			color:#B0B0B0;
		}
		.search-auto{
			width:523px;
			background-color: #fff;
			margin: 0 auto;
			position: absolute;
			border: 2px solid #B79BD1;
			border-top: none;
			box-sizing:border-box;
			border-bottom-left-radius: 10px;
			border-bottom-right-radius: 10px;
		}
		.search-img>*{
			width:100%;
			height:100%;
		}
		.search-one{
			overflow: hidden;
			border-bottom: 2px solid #B79BD1;
			height:33%;
		}
		.search-one:hover{
			background-color: #F0ECEB;
		}
		.search-one>*{
			float:left;
			margin: 20px;
			text-align: left;
		}
		.search-img{
			width:40px;
			height:60px;
			margin-right:0;
			border: 1px solid #ccc;
		}
		.search-auto>a:last-child>div {
			border-bottom: none;
			border-bottom-left-radius: 10px;
			border-bottom-right-radius: 10px;
		}
		.bookNameTitle{
			font-size: 15px;
		}
		.info>p:last-child{
			font-size: 0.9em;
		}		
    </style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-search">
			<h3>도서검색</h3>
			<span>찾고싶은 도서를 검색하세요.</span>
			<form class="searchForm" action="/bookSearch.do" method="get">
				<select name="searchSelect">
					<option value="1">책 제목</option>
					<option value="2">작가</option>
					<option value="3">과목</option>
				</select><div><input type="text" class="input-form searchBook" name="searchBook" placeholder="검색어를 입력하세요"><div class="search-auto">
				</div></div><button class="bc9" type="submit">검색</button>
			</form>
		</div>
		
		<div class="bookList">
		<%
		int listNum = (totalCount-(pageNo-1)*10);
		for(Book b : list){ 
			%>
			<div class="oneBook">
				<a href="/bookView.do?bookNo=<%=b.getBookNo() %>">
					<div class="num"><p><%=listNum-- %></p></div>
					<div class="book-img">
					<%if(b.getFilepath()==null){ %>
						<img src="/img/book.png" style="display:block; margin:40px auto;">
					<%}else{ %>
						<img src="/upload/book/<%=b.getFilepath() %>" style="width:100%; height:100%; border-radius: 10px;">
					<%} %>
					</div>
					<%
						DecimalFormat dec = new DecimalFormat("###,###");
						String price = dec.format(b.getHopePrice());
					%>
					<div class="book-info">
						<h3 class="book-title"><%=b.getBookName() %></h3>
						<p class="detail publisher"><%=b.getPublisher() %></p>
						<p class="detail writer"><%=b.getBookWriter() %> <span style="color:#B0B0B0;">지음</span></p>
						<p class="detail subject"><%=map.get(b.getSubjectNo()) %> <span style="color:#B0B0B0;">수업</span></p>
						<span class="price"><%=price %>원</span> <span class="listPrice"><%=b.getListPrice() %>원</span>
					</div>
					<div class="saleShow">
						<%if(b.getSellEnd()!=null){ %>
							<button class="sellend">거래완료</button>	
						<%}%>
					</div>
				</a>
			</div>
			<%}%>
			<div class="btn-box">
				<%if(m!=null){ %>
				<a class="btn writeBtn" href="/bookWriteFrm.do">판매하기</a>
				<%} %>
			</div>
		</div>
		<div id="pageNavi"><%=pageNavi %></div>
	</div>
	<script>
		$(function(){
			$(".search-auto").hide();
			window.onclick = function(e){
				if(e.target!=$(".search-auto")){
					$(".search-auto").hide();
				}
			}
		});
		$(".searchBook").on("keyup",function(){
			var search = $(".searchBook").val();
			var searchSelect = $("[name=searchSelect]").val();
			$.ajax({
				url : "/bookSearchAuto.do",
				type : "get",
				data : ({search:search,searchSelect:searchSelect}),
				success : function(data){	
				$(".search-auto").empty();
					if(data.length!=0){
						$(".search-auto").show();
						let length = data.length>3?3:data.length;
						for(let i=0; i<length; i++){
							const p = data[i];
							const aTag = $("<a>");
							aTag.attr("href","/bookView.do?bookNo="+p.bookNo);
							const item = $("<div>");
							item.addClass("search-one");
							const imgDiv = $("<div>");
							imgDiv.addClass("search-img");
							const img = $("<img>");
							if(p.filepath==null){
								img.attr("src","/img/book.png");
							}else{
								img.attr("src","/upload/book/"+p.filepath);
							}
							imgDiv.append(img);
							const infoDiv = $("<div>");
							infoDiv.addClass("info");
							const bookName = $("<p>");
							bookName.addClass("bookNameTitle");
							bookName.append(p.bookName);
								
							const hopePrice = $("<p>");
							hopePrice.addClass("price");										
							hopePrice.append(p.hopePrice+"원");
								
							const publisher = $("<span>");
							publisher.addClass("detail");
							publisher.append(p.publisher);
								
							infoDiv.append(bookName);
							infoDiv.append(publisher);
							infoDiv.append(hopePrice);
							item.append(imgDiv).append(infoDiv);
							aTag.append(item);
							$(".search-auto").append(aTag);
						}
					}else{
						$(".search-auto").hide();
					}
				},
				error : function(){
					console.log("서버호출실패");
				}
			});
		});
	</script>
</body>
</html>