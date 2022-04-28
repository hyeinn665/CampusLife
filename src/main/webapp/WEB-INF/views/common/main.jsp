<%@page import="kr.or.iei.freeboard.model.vo.Freeboard"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="kr.or.iei.book.model.vo.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.iei.managerboard.model.vo.ManagerBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Book> list = (ArrayList<Book>)request.getAttribute("list");
    	ArrayList<Freeboard> freeList= (ArrayList<Freeboard>)request.getAttribute("freeList");
    %>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<style>
	/*왼쪽 콘텐츠*/
.page-content {
    width: 1200px;
    margin: 30px auto;
    margin-bottom: 10px;
    flex-grow: 1;
    display: flex;   
}
.page-title {
    font-family: ns-bold;
    padding: 20px 0px;
    font-size: 1.5rem;
}
.left-content{
    width: 300px;
}
.info-wrap{
    background-color: rgb(228, 203, 245);
    border-radius: 10px;
    width: 270px;
    height: 150px
}
.info-wrap>.info-img{
    height: 85px;
    border-bottom: 1px solid rgb(186, 183, 183);
}
.info-wrap>.info-img>span{
    font-size: 90px;
    color: #ccc;
    display: flex;
    justify-content: center;
    align-items: center;
}
.info-detail{
    text-align: center;
    margin: 10px;
}
.info button{
    border: none;
    display: inline-block;
    width: 110px;
    height: 40px;
    margin: 0 auto;
    padding: 0;
}
.after-login span{
    align-items: center;
    font-weight: bold;
}
.admin-menu button{
    width: 270px;
    height: 70px;
    background-color: rgb(197, 186, 172);
    font-weight: bold;
}

/*메인 콘텐츠*/
.main-content{
    width: 600px;
}
.board-type{
    border-radius: 10px;
    border: 1px solid rgb(210, 202, 210);
    margin: 0 0 0 10px;
    width: 550px;
    margin-bottom: 30px;
}
.board>li{
    list-style-type: none;
    border-top: 1px solid rgb(225, 216, 225);
}
.board>li:hover{
    background-color: rgb(240, 236, 236);
}
.board a{
    padding-left: 40px;
    width: 100%;
    height: 40px;
    line-height: 40px;
}
.board p{
    padding-left: 40px;
    width: 90%;
    height: 40px;
    line-height: 40px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
.board-title{
    background-color: rgb(246, 234, 247);
}

h3{
    font-size: 1.3em;
    background-color: rgb(240, 236, 236);
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
    color: blueviolet;
    padding-left: 40px;
    padding-right: 40px;
    line-height: 50px;
    height: 50px;
}

/*오른쪽 콘텐츠*/
.right-content{
    width: 300px;
}
.book-board{
    border-radius: 10px;
    border: 1px solid rgb(210, 202, 210);
    margin: 0 auto;
    width: 280px;
    margin-bottom: 30px;
}
.img-box{
    height: 170px;
    width: 170px;
    margin: 0 auto;
    background-color: beige;
}
/*책게시판*/
.book-title{
	padding: 0;
	display: block;
	text-align: center;
	line-height: 25px;
	margin-bottom: 10px;
	margin-top: 10px;
}
.bookPrice{
	text-align: center;
	display: block;
	font-size: 1.1rem;
    color: #FF5959;
}
.bookimg{
	border: 1px solid #ccc;
}
#bookATag{
	padding: 0;
	width: 100%;
}
.info button{
    border: none;
    display: inline-block;
    width: 110px;
    height: 40px;
    margin: 0 auto;
    padding: 0;
}
.info a{
	border: none;
    display: inline-block;
    width: 110px;
    height: 40px;
    margin: 0 auto;
    padding-top: 8px;
}
</style>
</head>
<body>
	<!-- header 삽입 -->
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="page-content">
		<div class="left-content">
            <div class="info-wrap">
                <div class="info-img">
                    <span class="material-icons">account_box</span>
                </div>   
                <div class="info">
                    <div class="info-detail">
                        <div class="info-button">
                       <%if(m == null) {%>
                         <button class="btn bc11 modal-open-btn" target="#login-modal">로그인</button>
                           <a class="btn bc88" href="/signupFrm.do">회원가입</a>
                         <%}else { %>
                         <button class="btn bc88"><%=m.getMemberName() %></button>
                          <a class = "btn bc88" href="/logout.do">로그아웃</a>
                             <%} %>
                        </div>
                        <div class="after-login">
                           
                        </div>
                    </div>
                </div>
                <% if(m !=null && m.getGrade().equals("관리자")) {%>
                <div class="admin-menu">
                    <a href="/adminPage.do"><button class="btn bc33">회원정보 / 블랙리스트 관리</button></a>
                </div>              
                <%} %>
            </div>
        </div>
        <div class="main-content">
            <div class="board-type freeboard">
                <a href="#" class="board-title"><h3>자유 게시판</h3></a></a>
                <ul class="board">
                <%for(Freeboard f : freeList){ %>
                    <li><a class="board-content" href="/freeboardView.do?freeNo=<%=f.getFreeNo() %>"><%=f.getFreeTitle() %></a></li>
                 <%} %>
                </ul>
            </div>
            <div class="board-type review">
                <a href="/reviewList.do?reqPage=1" class="board-title" id="bb"><h3>수업 후기</h3></a>
                <ul class="board" id="reviewAjax">
                    
                </ul>
            </div>
            <div class="board-type managerboard">
                <a href="/managerBoardList.do?reqPage=1&category=전체" class="board-title"><h3>공지사항 / QnA</h3></a></a>
                <ul class="board">                
                    <li><a class="board-content" href="/managerBoardList.do?reqPage=1&category=학과%20공지사항">바로가기 | 학과 공지사항</a></li>
                    <li><a class="board-content" href="/managerBoardList.do?reqPage=1&category=공모전">바로가기 | 공모전</a></li>
                    <li><a class="board-content" href="/managerBoardList.do?reqPage=1&category=동아리/학회">바로가기 | 동아리/학회</a></li>
                    <li><a class="board-content" href="/managerBoardList.do?reqPage=1&category=취업지원">바로가기 | 취업지원</a></li>                
                </ul>
            </div>
        </div>

        <div class="right-content">
            <div class="book-board">
                <a href="/bookList.do?reqPage=1" class="board-title"><h3>중고거래</h3></a></a>
                <ul class="board">
                	<%for(Book b : list){ %>
                    <li>
                    		<%
								DecimalFormat dec = new DecimalFormat("###,###");
								String price = dec.format(b.getHopePrice());
							%>
                        <a class="board-content" id="bookATag" href="/bookView.do?bookNo=<%=b.getBookNo() %>">
                            <span class="book-title"><%=b.getBookName() %></span>
                            <div class="img-box bookimg">
                            	<%if(b.getFilepath()!=null){ %>
                                <img src="/upload/book/<%=b.getFilepath()%>" style="width:100%; height:100%;">
                                <%}else{ %>
                                <img src="/img/camera.png" style="margin: 78px auto; display: block; ">
                                <%} %>
                            </div>
                            <span class="bookPrice"><%=price %>원</span>
                        </a>
                    </li>
                    <%} %>
                </ul>
            </div>
        </div>
    </div>
	<script>
	window.onload=function() {
		const review=$("#reviewAjax");
		$.ajax({
			url : "/reviewAjax.do",
			type : "get",
			success : function(data) {
				review.empty();
				for(let i=0;i<5;i++) {
					review.append("<li><p class=\"board-content\">"+data[i].reviewContent+"</p></li>");
				}
			},
			error : function() {
				console.log("서버호출실패");
			}
		});
	};
	</script>
	<!-- footer삽입 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>