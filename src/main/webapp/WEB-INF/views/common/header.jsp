<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Member m = (Member)session.getAttribute("m");
    %>
<!-- 구글 아이콘 -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- jquery -->
    <script src="/js/jquery-3.6.0.js"></script>
    <!-- 기본 CSS -->
    <link rel="stylesheet" href="/css/default.css">
    <link rel="stylesheet" href="/css/header.css">
    <!-- 기본 js -->
    <script src="/js/default.js"></script>
    <script src="/js/sweetalert.min.js"></script>
    <style>
	header{
		background-color: #fff;
		border-bottom: 1px solid #F0D9FF;
		padding-bottom: 10px;
	}
	header>nav{
		
	}
	header>nav>.navi>li>a{
		color:#7F7C82;
		border: 3px solid rgba(0,0,0,0);
		font-size: 18px;
	}
	header>nav>.navi>li>a:hover{
		color:#BFA2DB;
		border-bottom: 3px solid #BFA2DB;
	}
	header>.site-logo>a{
		color: #BFA2DB;
	}
	.bc11{
		border: 2px solid #BFA2DB;
		border-radius: 5px;
		color:#7F7C82;
	}
	.bc11:hover{
		background-color: #BFA2DB;
	}
	#logoImg{
		display: inline-block;
		width:65px;
		line-height: 60px;
	}
	</style>
    <header>
    <div class="site-logo">
        <a href="/"><img src="/img/logo2.png" id="logoImg"></a>
      </div>
      <nav>
        <ul class="navi">
          <li><a href="/mySchedule.do">시간표</a></li>
		  <li><a href="#">수강후기</a>
	    		<ul class="sub-navi">
					<li><a href="/reviewList.do?reqPage=1">후기 보기</a></li>
					<%if(m!=null&&!m.getGrade().equals("관리자")) {%>
						<li><a href="/subjectList.do?memberNo=<%=m.getMemberNo()%>">후기 작성</a></li>
					<%} %>
				</ul>
		  </li>
          <li><a href="/freeboardList.do?reqPage=1">자유게시판</a></li>
          <li><a href="/bookList.do?reqPage=1">책거래게시판</a></li>
          <li><%if(m!=null) {%>
					<%if(m.getGrade().equals("관리자")) {%>
					<li><a href="/qnaAdmin.do?reqPage=1">1:1 문의</a></li>
					<%} else{%>
				<li><a href="/qna.do?reqPage=1&memberId=<%=m.getMemberId()%>">1:1 문의</a></li>
			<%} %><%} %>
		  </li>
		  <li><a href="/managerBoardList.do?reqPage=1&category=전체">공지사항</a></li>
          <%if(m!=null) {%>
				<li><a href="#">쪽지</a>
					<ul class="sub-navi">
						<li><a href="/msgList.do?reqPage=1&memberId=<%=m.getMemberId()%>">받은 쪽지</a></li>
						<li><a href="/sendList.do?reqPage=1&memberId=<%=m.getMemberId()%>">보낸 쪽지</a></li>
						<li><a href="/writeMsgFrm.do?reqPage=1&memberId=<%=m.getMemberId()%>">쪽지보내기</a></li>
					</ul>
				</li>
			<%} %>
        </ul>
      </nav>
      <div class="header-link">
      <%if(m==null){ %>
        <button class="btn bc11 modal-open-btn" target="#login-modal">SIGN IN</button>
        <a class="btn bc11" href="/signupFrm.do">SIGN UP</a>
        <%} else{ %>
        <!--1. 세션으로 처리할때 <a class="btn bc11" href="/mypage1.do>"><%=m.getMemberName() %></a> -->
        <a class="btn bc11" href="/mypage2.do?memberId=<%=m.getMemberId()%>"><%=m.getMemberName() %></a>
        <a class="btn bc11" href="/logout.do">LOGOUT</a>
        <%} %>
      </div>
    </header>
    <%if(m==null){ %>
        <div id="login-modal" class="modal-bg" style="z-index: 9999; position: absolute;">
      <div class="modal-wrap">
        <div class="modal-head">
          <h2>SIGN IN</h2>
          <span class="material-icons close-icon modal-close">close</span>
        </div>
        <form action="/signin.do" method="post">
	        <div class="modal-content">
	          <div class="input-box">
	          	<label for="memberId">아이디</label>
	          	<input type="text" name="memberId" class="input-form" id="memberId" placeholder="아이디입력">
	          </div>
	          <div class="input-box">
	          	<label for="memberPw">비밀번호</label>
	          	<input type="password" name="memberPw" class="input-form" id="memberPw" placeholder="비밀번호입력">
	          </div>
	          <div class="input-box link-box">
	          	<a href="#">아이디/비밀번호 찾기</a>
	          </div>
	        </div>
	        <div class="modal-foot">
	          <button type="submit" class="btn bc11">확인</button>
	          <button type="button" class="btn bc11 modal-close">취소</button>
	        </div>
        </form>
      </div>
    </div>
    <%}%>
	<!-- <script>
		$(".modal-open-btn").on("click", function(){
			const navi=$(".navi>li");
			navi.css("position","static");
		})
	</script> -->
	