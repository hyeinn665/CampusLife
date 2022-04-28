<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="idFind" method="post">
		<div class="searchNo">
			<label>학번 or 교번</label>
				<input type="text" name="memberNo" class="btn-no" placeholder = "학번 혹은 교번을 입력하세요">
				
		</div>
		<div class="btnSearch">
			<input type="button" name="enter" value="찾기" onClick="id_search()">
			<input type="button" name="cancle" value="취소" onClick="history.back()">
		</div>
	</form>
</body>
</html>