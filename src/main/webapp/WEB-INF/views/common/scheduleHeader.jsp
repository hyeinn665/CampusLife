<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	HttpSession ses = request.getSession(false);
		Member g = (Member)ses.getAttribute("m");
		String myGrade = g.getGrade();
    %>
    
    <link rel="stylesheet" href="/css/header.css">
    
    <style>
    	.page-title{
    		text-align: center;
    	}
    	.page-content{
    		display: block;
    		margin: 0 auto;
    	}
    	.prof-hidden{
    		display:none;
    	}
    	.stu-hidden{
    		display:none;
    	}
    </style>

    <header class="hed">
    		<nav>
    			<ul class="navi">
    				<li><a href="/mySchedule.do">내 시간표</a></li>
    				<li class="stu-hidden"><a href="/insertSchedule.do">수강 신청</a></li>
    				<li class="stu-hidden"><a href="/mySubject.do">수강중인 과목</a></li>
    				<li class="prof-hidden"><a href="/insertSubject.do">과목 생성</a></li>
    				<li class="prof-hidden"><a href="/deleteSubjectFrm.do">과목 삭제</a><li>
    			</ul>
    		</nav>
    </header>
    
    <script>
    	<%	if(myGrade.equals("교수")){ %>
    			$('.prof-hidden').removeClass('prof-hidden');
    	<% } %>
    	<%	if(myGrade.equals("학생")){ %>
		$('.stu-hidden').removeClass('stu-hidden');
		<% } %>
    </script>