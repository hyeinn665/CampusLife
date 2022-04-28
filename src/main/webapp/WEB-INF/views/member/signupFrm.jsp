<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
	.input-wrap{
		padding: 20px;
	
	}
	.input-wrap > label{
	font-size: 1.1em;
	margin-bottom: 10px;
	display: block;
	}
	
	.input-wrap > input{
	font-size: 1.1em;
	margin-bottom: 10px;
	width: 100%;
	height: 50px;
	}
	.submit-btn{
	height: 200px;
	width: 70%;
	padding: 10px;
	border-radius: 5px;
    padding: 15px;
	margin: 0 auto;
	}
	.submit-btn>button{
	font-size:20px; padding:20px 100px;
	border-radius: 3%;
	}

	
	.input-wrap>.id-wrap{
		display: flex;
	}
	.input-wrap>.id-wrap>#memberId{
		width: 50%;
		height: 46.59px;
	}
	.input-wrap>.id-wrap>#idChkBtn{
	width: 10%;
	height: 46.59px;
	line-height: 10px;
	}
	.input-wrap>select{
	font-size: 1.1em;
	width: 100%;
	height: 50px;
	}
	.page-content{
	background: #FFFF;
     margin: 20px auto;
     width: 70%;
	}
	.page-title{
	font-size: 10px;
	margin: 20px;
	text-align: center;
	display: block;
	font-weight: bold;
	}
	.login{
			width: 70%;
			background-color: #F0ECEB;
			margin: 0 auto;
			padding: 10px;
			border-radius: 5px;
			border: 1px solid #252a34;
	}

	
	</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-title">회원가입</div>
	<div class ="page-content">
	<div class = "login">
		<form name="checkIdFrm" action="/checkId.do">
			<input type="hidden" name="checkId">
		</form>

		<form name="checkNoFrm" action="/checkNo.do">
			<input type="hidden" name="checkNo">
		</form>
		
		<form action="/signup.do" method="post">

			<div class="input-wrap">
				<label for="memberId">아이디</label>
					<input type="text" name="memberId" id="memberId" class="input-form">
					<button type="button" id="idChkBtn" class="btn bc2">중복체크</button>
					중복체크를 꼭 해주세요!
			</div>
				
				<div class="input-wrap">
					<label for="memberPw">비밀번호</label> 
					<input type="password" name="memberPw" id="memberPw" class="input-form">
				</div>
				
				<div class="input-wrap">
					<label for="memberPw">비밀번호확인</label> 
					<input type="password" name="memberPwRe" id="memberPwRe" class="input-form">
				</div>
			
				<div class="input-wrap">
					<label for="memberNo">학번 혹은 교번</label> 
					<input type="text" name="memberNo" id="memberNo" class="input-form">
					<button type="button" id="noChkBtn" class="btn bc2">중복체크</button>
					중복체크를 꼭 해주세요!
				</div>
				
				<div class="input-wrap">
					<label for="memberName">이름</label> 
					<input type="text" name="memberName" id="memberName" class="input-form">
				</div>
				
				<div class="input-wrap">
					<label for="age">나이</label> 
					<input type="text" name="age" id="age" class="input-form">
				</div>
				
				<div class="input-wrap">
					<label for="grade">직책</label>
					<select id="grade" name="grade" size="1">
						<option value="">직책선택</option>
						<option value="교수">교수</option>
						<option value="학생">학생</option>
					</select>
				</div>
				
				<div class="input-wrap">
					<label for="phone">전화번호</label> 
					<input type="text" name="phone" id="phone" class="input-form">		
				</div>
				
				<div class="input-wrap">
					<label for="address">주소</label> 
					<input type="text" name="address" id="address" class="input-form" >
				</div>
		
				
		</div>
		<div class="submit-btn">
					<button type="submit" class="bc bc11 bs1">회원가입</button>
					
		</div>
				
				</div>
				<script>
				
					$("#idChkBtn").on("click", function(){
						const memberId = $(this).prev().val();
						if(memberId == ""){
							alert("아이디를 입력하세요!");
							return;
						}
						$("[name=checkId]").val(memberId);
						const popup = window.open("","checkId","left:700px, top=300px, width=300px, height=200px, menuber=no, status=no, scrollbars=yes");
						$("[name=checkIdFrm]").attr("target", "checkId");
						$("[name=checkIdFrm]").submit();
					});
				
				$("#noChkBtn").on("click", function(){
					const memberNo = $(this).prev().val();
					if(memberNo == ""){
						alert("학번을 입력하세요!");
						return;
					}
					$("[name=checkNo]").val(memberNo);
					const popup = window.open("","checkNo","left:700px, top=300px, width=300px, height=200px, menuber=no, status=no, scrollbars=yes");
					$("[name=checkNoFrm]").attr("target", "checkNo");
					$("[name=checkNoFrm]").submit();
				});
				
				</script>
</body>
</html>