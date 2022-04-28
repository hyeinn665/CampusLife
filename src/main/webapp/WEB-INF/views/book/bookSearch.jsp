<%@page import="kr.or.iei.book.model.vo.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<Book> list = (ArrayList<Book>)request.getAttribute("list");
        	String search = (String)request.getAttribute("search");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=search%> 검색</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
   	<style>
		.page-search{
    		background: #F0ECEB;
    		border-radius: 10px;
    	}
    	#button-addon2{
    		border: 1px solid #ccc;
    	}
    	.input-group{
    		padding-top: 50px;
    		width: 70%;
    		margin: 0 auto;
    	}
    	.page-content>div{
    		width: 70%;
    		margin: 20px auto;
    	}
    	.oneBook{
    		overflow: hidden;
    		padding: 10px 30px;
    		border: 1px solid #CCCCCC;
    		border-bottom: none;
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
    	.oneBook>a>div{
    		float : left;    		
    	}
    	.book-img{
    		border: 1px solid #ccc;
    		height: 120px;
    		width: 80px;
    		border-radius: 10px;
    	}
    	.detail{
    		color : #666666;
    		font-size: 0.8rem;
    		height: 20px;
    		margin: 0;
    	}
    	.book-info{
    		margin: 10px 0;
    		margin-left: 30px;
    		height: 100px;
    		width: 60%;
    	}
    	.price{
    		font-size: 1.1rem;
    		color: #FF5959;
    	}
    	.saleShow{
    		height: 120px;
    	}   
    	.saleShow>button{
    		margin: 30px 0;
    	}
    	.tr-3:first-child>td:first-child {
			width:130px;
			text-align: center;
		}
		.tbl th, .tbl td{
			text-align: left;
		}
		.sellend{
			height: 40px;
			width:80px;
			background-color:#50445C;
			color:#fff;
			border:none;
			line-height: 10px;
			border-radius: 10px;
		}
		.num{
			width: 6%;
		}
		.num>p{
			line-height:120px;
			margin: 0;
			color:#50445C;
		}
		.btn-box{
			overflow: hidden;
			margin: 10px auto;
		}
		.btn-box>a{
			float:right;
			background-color: #BFA2DB;
			color:#fff;
			border-radius: 15px;
		}
		.search-result{
			text-align: center;
			padding: 50px;
			font-size: 1.2em;
			font-weight: bold; 
		}
		.search-result>span{
			color:#FF5959;
		}
		.search-result>p{
			font-size: 15px;
			font-weight: normal;
			color: #8B888F;
			margin-top: 10px;
		}
		.noResult-box{
			background: #ddd;
			border-radius: 10px;
			text-align: center;
			padding:100px;
			margin-bottom: 20px;
		}
		.searchForm{
			width: 80%;
			margin: 0 auto;
			padding-top: 50px;
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
		.searchForm>button{
			width: 10%;
			height:50px;
			border-top-right-radius: 10px;
			border-bottom-right-radius: 10px;
			background-color: #BFA2DB;
			border: 2px solid #BFA2DB;
			font-size:15px;
			color:#fff;
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
			<form class="searchForm" action="/bookSearch.do" method="get">
				<select name="searchSelect">
					<option value="1">책 제목</option>
					<option value="2">작가</option>
					<option value="3">과목</option>
				</select><div><input type="text" class="input-form searchBook" name="searchBook" placeholder="검색어를 입력하세요" value="<%=search %>"><div class="search-auto">
				</div></div><button class="bc9" type="submit">검색</button>
			</form>
			<div class="search-result">
				"<span><%=search %></span>" 검색결과입니다.
				<p>검색결과 총 <%=list.size() %> 건 입니다.</p>
			</div>
		</div>
		
		<div class="bookList">
		<%
		if(list!=null){
		int i = 0;
		for(Book b : list){ %>
			<div class="oneBook">
				<a href="/bookView.do?bookNo=<%=b.getBookNo() %>">
					<div class="num"><p><%=list.size()-i++ %></p></div>
					<div class="book-img">
					<%if(b.getFilepath()==null){ %>
						<img src="/img/book.png" style="display:block; margin:40px auto;">
					<%}else{ %>
						<img src="/upload/book/<%=b.getFilepath() %>" style="width:100%; height:100%; border-radius: 10px;">
					<%} %>
					</div>
					<div class="book-info">
						<h3 class="book-title"><%=b.getBookName() %></h3>
						<p class="detail subject"><%=b.getBookWriter() %> <span style="color:#B0B0B0;">지음</span></p>
						<p class="detail publisher">출판사</p>
						<span class="price"><%=b.getHopePrice() %>원</span> <span class="listPrice"><%=b.getListPrice() %>원</span>
					</div>
					<div class="saleShow">
						<%if(b.getSellEnd()!=null){ %>
						<button class="sellend">거래완료</button>	
						<%}%>
					</div>
				</a>
			</div>
			<%} }else if(list==null){ %>
			<div class="noResult-box">
				<img src="/img/question.png"> 검색결과가 없습니다.
			</div>
			<%} %>
			<div class="btn-box">
				<%if(m!=null){ %>
				<a class="btn writeBtn" href="/bookWriteFrm.do">판매하기</a>
				<%} %>
			</div>
		</div>
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