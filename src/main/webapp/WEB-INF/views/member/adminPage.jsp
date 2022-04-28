<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
    	ArrayList<Member> plist = (ArrayList<Member>)request.getAttribute("plist");
    	ArrayList<Member> slist = (ArrayList<Member>)request.getAttribute("slist");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리페이지</title>
<style>
	.page-content{
		display: flex;
	}
	.left-menu{
		width: 250px;
		backgraound-color: yellow;
	}
	.tbl{
		margin-left: 40px;
		margin-top: 90px;
	}
	.left-menu>.page-title{
		font-size : 1.9em;
	}
	.memberlist,.blacklist{
		font-size : 1.5em;
		width : 200px;
		height: 50px;
		line-height: 50px;
		background-color: #F0D9FF;
		border-radius : 7px;
		margin-top: 7px;
		margin-bottom: 10px;
	}
	.memberlist> a{
		color: gray;
		margin-left:50px;
		text-align: center;
	}
	.blacklist> a{
		color: gray;
		margin-left:50px;
		text-align: center;
	}
	.bc33{
		width: 200px;
		height: 45px;
		border-radius: 10px;
	}
	.bc33:hover{
		background-color:black;
	}

	.bc33>span{
		color : #5d5858;
		font-size: 1.5em;
		width:200px;
	}

	.professor, .student{
		margin-top: 7px;
	}
	
	.blacklist{
		margin-top: 30px;
	}

}

</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="left-menu">
			<div class="page-title">회원관리</div>
			<div class="menu">
				<div class="memberlist"><a>전체 회원</a></div>
				<div class="member-detail">
					<button class="bc33 allMember" id="allMember"><span>전체 목록</span></button>
					<button class="bc33 professor" id="professor"><span>교수</span></button>
					<button class="bc33 student" id="student"><span>학생</span></button>
				</div>
				<div class="blacklist"><a>접근 권한</a></div>
				<div class="member-detail">
					<button class="bc33" id="blacklist"><span>블랙리스트</span></button>
				</div>
			</div>
		</div>
<!-- 기본 전체 회원 조회-------------------------------------->
		<table class="tbl tbl-hover" id="tbl1" style="border:1px solid #ccc;">
			<tr class="tr-4">
				<th>회원번호</th>
				<th>아이디</th>
				<th>등급</th>
				<th>이름</th>
				<th>나이</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>가입일</th>
			</tr>
			<%for(Member member : list){ %>
			<tr class="tr-1" style="border-bottom:1px solid #ccc;">
				<td style="background-color:rgba(0, 0, 0, 0.05);"><%=member.getMemberNo() %></td>
				<td><%=member.getMemberId() %></td>
				<td><%=member.getGrade() %></td>
				<td><%=member.getMemberName() %></td>
				<td><%=member.getAge() %></td>
				<td><%=member.getPhone() %></td>
				<td><%=member.getMemberAddr() %></td>
				<td><%=member.getEnrollDate() %></td>
			</tr>
			<%} %>
		</table>
		
<!-- 교수 조회-------------------------------------->
		<table class="tbl tbl-hover" id="tbl2" style="border:1px solid #ccc;">
			<tr class="tr-4">
				<th>회원번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th>나이</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>가입일</th>
			</tr>
			<%for(Member member : plist){ %>
			<tr class="tr-1" style="border-bottom:1px solid #ccc;">
				<td style="background-color:rgba(0, 0, 0, 0.05);"><%=member.getMemberNo() %></td>
				<td><%=member.getMemberId() %></td>
				<td><%=member.getMemberName() %></td>
				<td><%=member.getAge() %></td>
				<td><%=member.getPhone() %></td>
				<td><%=member.getMemberAddr() %></td>
				<td><%=member.getEnrollDate() %></td>
			</tr>
			<%} %>
		</table>	

<!-- 학생 조회-------------------------------------->
		<table class="tbl tbl-hover" id="tbl3" style="border:1px solid #ccc;">
			<tr class="tr-4" style=height:30px>
				<th>회원번호</th>
				<th>아이디</th>
				<th>등급</th>
				<th>이름</th>
				<th>나이</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>누적 경고횟수</th>
			</tr>
			<%for(Member member : slist){ %>
			<tr class="tr-1" style="border-bottom:1px solid #ccc;height:30px">
				<td style="background-color:rgba(0, 0, 0, 0.05);"><%=member.getMemberNo() %></td>
				<td><%=member.getMemberId() %></td>
				<td><%=member.getGrade() %></td>
				<td><%=member.getMemberName() %></td>
				<td><%=member.getAge() %></td>
				<td><%=member.getPhone() %></td>
				<td style="border-right:1px solid #ccc;"><%=member.getMemberAddr() %></td>
				<% if(member.getBlack1()+member.getBlack2()+member.getBlack3()==0) {%>
				<td><%=member.getBlack1()+member.getBlack2()+member.getBlack3() %></td>
				<% }else {%>
				<td style="color:red;"><%=member.getBlack1()+member.getBlack2()+member.getBlack3() %></td>
				<%} %>
			</tr>
			<%} %>
		</table>	

<!-- 블랙리스트 조회-------------------------------------->
		<table class="tbl tbl-hover" id="tbl4" style="border:1px solid #ccc;">
			<tr class="tr-4" rowspan="4">
				<th colspan="4" style="border-right:1px solid #ccc; border-bottom:1px solid #ccc; color:purple; font-size:1.2em;">회원정보</th>
				<th colspan="6" style="border-bottom:1px solid #ccc; color:purple; font-size:1.2em;">경고사항</th>
			<tr class="tr-2" >
				<th>회원번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th style="border-right:1px solid #ccc;">전화번호</th>
				<th>욕설<br>타인비방</th>
				<th>상업성<br>광고</th>
				<th>허위사실<br> 유포</th>
				<th>확인</th>
				<th style="border-right:1px solid #ccc;">누적<br>경고수</th>
				<th>조치사항</th>
			</tr>
			<%for(Member member : slist){ %>
			<tr class="tr-1" style="border-bottom:1px solid #ccc; height:100px;">
				<td><%=member.getMemberNo() %></td>
				<td><%=member.getMemberId() %></td>				
				<td><%=member.getMemberName() %></td>
				<td style="border-right:1px solid #ccc;"><%=member.getPhone() %></td>
				<% if(member.getBlack1()+member.getBlack2()+member.getBlack3()<5) {%>
				<td>
					<input type="checkbox" class="black1" name="black1" id="black1" value="0" style="width:17px;height:17px;">
					<span class="amount">총 <%=member.getBlack1() %></span>
				</td>
				<td>
					<input type="checkbox" class="black2" name="black2" id="black2" value="0" style="width:17px;height:17px;">
					<span class="amount">총 <%=member.getBlack2() %></span>
				</td>
				<td>
					<input type="checkbox" class="black3" name="black3" id="black3" value="0" style="width:17px;height:17px;">
					<span class="amount">총 <%=member.getBlack3() %></span>
				</td>
				<td>
					<button class="bc22 checkBlacklist" style="width:40px;height:30px;">적용</button>
				</td>
				<% }else {%>
				<td>
					<span class="amount">총 <%=member.getBlack1() %></span>
				</td>
				<td>
					<span class="amount">총 <%=member.getBlack2() %></span>
				</td>
				<td>
					<span class="amount">총 <%=member.getBlack3() %></span>
				</td>
				<td>
					<button class="bc22 checkBlacklist" style="width:40px;height:30px;">적용</button>
				</td>
				<%} %>
				

				<% if(member.getBlack1()+member.getBlack2()+member.getBlack3()<5) {%>
				<td style="border-right:1px solid #ccc;"><%=member.getBlack1()+member.getBlack2()+member.getBlack3() %></td>
				<% }else {%>
				<td style="color:red;border-right:1px solid #ccc;"><%=member.getBlack1()+member.getBlack2()+member.getBlack3() %></td>
				<%} %>
				<td>
					<% if(member.getBlack1()+member.getBlack2()+member.getBlack3()>=3) {%>
					<button class="bc11 blackOption1" style="width:100px;height:40px;"><p style="font-size:1.1em;">경고쪽지 전송<p></span></button><br>
					<span class="blackOption11" style="display: none;color:#BFA2DB">쪽지 전송완료<br></span>
					<%} %>
					<% if(member.getBlack1()+member.getBlack2()+member.getBlack3()>=5) {%>
					<span style="color:red;">로그인 불가상태</span>
					<%} %>
				</td>
			</tr>
			<%} %>
		</table>
	</div>
	

	
	<script>
	//카테고리별 버튼
	const selectCategory = $(".member-detail").attr("value");
	$(".member-detail>button").each(function(index,item){
		if($(item).children().text() == selectCategory){
			$(item).children().css("color","#B762C1");
			$(item).children().css("font-size","1.7em");
		}
	});
	
	//<블랙리스트 관리>
	//경고 횟수 3회 이상일 때 쪽지보내기
	$(".blackOption1").on("click",function(){
		if(confirm("경고 쪽지를 보내시겠습니까?")){
			$(this).hide();
			$(this).parent().find("span").show();
		}
		$(".blackOption1").off("click");
	});
	
	//경고 횟수 5회 이상일 때 회원정지
	
	
	//반영 버튼 눌렀을 때
	$(".checkBlacklist").on("click",function(){
		const memberNo = $(this).parent().parent().children().eq(0).text();
		const black1 = $(this).parent().parent().find("input").eq(0).prop("checked");
		const black2 = $(this).parent().parent().find("input").eq(1).prop("checked");
		const black3 = $(this).parent().parent().find("input").eq(2).prop("checked");
		//const warningCount = $(this).next().val();
		//console.log(memberNo,black1,black2,black3);
		let value1=0;
		let value2=0;
		let value3=0;
		if(black1){
			value1=1;
		}
		if(black2){
			value2=1;
		}
		if(black3){
			value3=1
		}
		location.href="/adminBlacklist.do?memberNo="+memberNo+"&black1="+value1+"&black2="+value2+"&black3="+value3;
		
	});
	
	$(document).ready(function(){	
		$("#blacklist").on("click",function(){
		      $("#tbl1").hide();
		      $("#tbl2").hide();
		      $("#tbl3").hide();
		      $("#tbl4").show();
		 });
		$("#blacklist").trigger("click");
	});

	
	$("#allMember").on("click",function(){
	      $("#tbl2").hide();
	      $("#tbl3").hide();
	      $("#tbl4").hide();
	      $("#tbl1").show();
	    });
	
	$("#professor").on("click",function(){
	      $("#tbl1").hide();
	      $("#tbl3").hide();
	      $("#tbl4").hide();
	      $("#tbl2").show();
	    });
	
	$("#student").on("click",function(){
	      $("#tbl1").hide();
	      $("#tbl2").hide();
	      $("#tbl4").hide();
	      $("#tbl3").show();
	    });
	  
	$("#blacklist").on("click",function(){
	      $("#tbl1").hide();
	      $("#tbl2").hide();
	      $("#tbl3").hide();
	      $("#tbl4").show();
	    });

	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>