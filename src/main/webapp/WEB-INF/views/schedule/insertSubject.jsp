<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의 생성</title>
<style>
	.input-wrap{
		padding: 10px;
		border: 1px solid #F0D9FF;
	}
	.input-wrap > label{
		font-size: 1.1em;
		margin-bottom: 10px;
		display: block;
	}
	.submit-btn{
		padding: 15px;
		margin: 20px 0px;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div>
		<%@include file="/WEB-INF/views/common/scheduleHeader.jsp" %>
	</div>
	<div>
		<div class="page-title"><h1>강의 등록</h1></div>
		<hr>
		<div class="page-content">
			<form action="/insertLecture.do" method="post">
				<div class="input-wrap">
					<label for="subjectName">과목명</label>
					<input type="text" name="subjectName" size="20">
				</div>
				<div class="input-wrap">
					<label for="grade">과목명</label>
					<select name="grade">
						<option value="-1" disabled>학년선택</option>
						<option value="1">1학년</option>
						<option value="2">2학년</option>
						<option value="3">3학년</option>
						<option value="4">4학년</option>
					</select>
				</div>
				<div class="input-wrap">
					<label for="startTime1">강의 요일</label>
					<select name="startTime1">
						<option value="-1" disabled>요일선택</option>
						<option value="A">월요일</option>
						<option value="B">화요일</option>
						<option value="C">수요일</option>
						<option value="D">목요일</option>
						<option value="E">금요일</option>
					</select>
				</div>
				<div class="input-wrap startTime">
					<label for="startTime2">시작교시 선택</label>
					<select name="startTime2">
						<option value="-1" disabled>시작교시선택</option>
						<option value="1">1교시</option>
						<option value="2">2교시</option>
						<option value="3">3교시</option>
						<option value="4">4교시</option>
						<option value="5">5교시</option>
						<option value="6">6교시</option>
						<option value="7">7교시</option>
						<option value="8">8교시</option>
						<option value="9">9교시</option>
						<option value="10">10교시</option>
						<option value="11">11교시</option>
						<option value="12">12교시</option>
					</select>
				</div>
				<div class="input-wrap">
					<label for="durationTime">수업 시간</label>
					<select name="durationTime">
						<option value="-1" disabled>수업 시간</option>
						<option value="1">1시간</option>
						<option value="2">2시간</option>
						<option value="3">3시간</option>
					</select>
				</div>
				<div class="input-wrap">
					<label for="selectSubject">필수/선택</label>
					<select name="selectSubject">
						<option value="-1" disabled>필수/선택</option>
						<option value="1">필수</option>
						<option value="2">선택</option>
					</select>
				</div>
			<button type="submit" class="bc bc88 bs4">강의 등록</button>
			</form>
		</div>
	</div>
</body>
</html>