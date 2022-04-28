<%@page import="kr.or.iei.freeboard.model.vo.Freeboard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 작성</title>
<style>
.page-title{
	font-size: 10px;
	margin: 20px;
	text-align: center;
	display: block;
	font-weight: bold;
	}
.page-content{

}
.write{
	height: 1000px;
	width: 90%;
	background-color: #FFFF;
	margin: 30px auto;
	padding-top: 20px;
    padding-right: 20px;
    padding-bottom: 20px;
    padding-left: 20px;
	border-radius: 5px;
	border: 15px solid #F0D9FF;
	min-height:10px;
}


.submit-btn{
	width: 70%;
	font-size:20px; padding:20px 1000px;
	border-radius: 4%;
	}
	
.title>label{
	font-size: 1.1em;
	margin-bottom: 10px;
	width: 100%;
	height: 50px;
}
.tr-2>label{
	display: inline-block;
	font-size: 1.1em;
 	width: 100px;
}
.tr-2>input{
font-size: 1.1em;
	margin-bottom: 5px;
	
}
.tr-2>.td-1{
	display: inline-block;
    width: 600px;
    }

           

.writer>label{
font-size: 1.3em;
}

.writer>textarea{
resize: none;
min-height: 1000px;

}

.tr-1>label{
font-size: 1.1em;
}
</style>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp"%>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	
	<div class="page-title">자유게시판 작성</div>
	<div class="page-content">
	<div class="write">
		<form action="/freeboardWrite.do" method="post" enctype="multipart/form-data">		
			<div class="tbl" id="freeboardWrite">	

				<tr class="tr-1">			
					<th class="td-1">제목</th>
					<td colspan="3">
						<input type="text" id ="freeTitle" name="freeTitle" class="input-form">
					</td>			
				</tr>
			
				
				<div class="tr-2">
					<div class="td-1">⊙</div>
					<input type="hidden" name="memberNo" value="<%=m.getMemberNo()%>">
					<label for="td-2">첨부파일</label> <input type="file" name="file">
				</div>
				
				<!--
				<tr class="tr-1">
					<input type="hidden" name="memberNo" value="<%=m.getMemberNo()%>">
					<th class="td-1">첨부파일</th>
					<td><input type="file" name="file"></td>
				</tr>
				-->
				
				<div class="writer">
				<label for="td-1">↓</label>
				<label for="td-1">↓</label>
				<label for="td-1">작성</label>
					<td colspan="4" style="text-align:left;">
					<textarea id="freeContent" name="freeContent" class="input-form"></textarea>
					</td>
					
					본 게시판에선 폭언 욕설을 금지합니다. 적발시 경고를 받게 되며 경고횟수가 초과될시 사이트 접속에 어려움이 생길수 있습니다.
					<div class="submit-btn">
					<button type="submit" class="bc bc11 bs1"> 등록</button>
				</div> 
				</div>

				
			
		</form>
	</div>
	</div>
	<script>
		
	</script>
</body>
</html>