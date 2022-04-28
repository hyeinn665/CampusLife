<%@page import="kr.or.iei.freeboard.model.vo.Freeboard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Freeboard f = (Freeboard)request.getAttribute("f");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.freeboardUpdateFrm td, .freeboardUpdateFrm th{
		border : 1px solid #eee;
	}
	.file-box{
		display: felx;
		align-items: center;
		justify-content: center;
	}
	.freeboardUpdateFrm	.file-box>*{
		padding: 0.2rem 0.8rem;
	}
	.page-title{
	font-size: 10px;
	margin: 20px;
	text-align: center;
	display: block;
	font-weight: bold;
	}
.page-content{
	background: #F0D9FF;

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
	border: 1px solid #252a34;
}


.submit-btn{
	width: 70%;
	font-size:20px; 
	padding:20px 1000px;
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
	<%@include file = "/WEB-INF/views/common/header.jsp" %>
	
			<div class="page-title">자유게시판 수정</div>
			<div class="page-content">
			<div class="write">
			<form action="/freeboardUpdate.do" method="post" enctype="multipart/form-data">
				 <input type="hidden" name="freeNo" value="<%=f.getFreeNo() %>">	
				<div class = "tbl freeboardUpdateFrm">		
				  <tr class="tr-1">
						<th class="td-3">제목</th>
						<td>
							<input type="text" name="freeTitle"
							 class="input-form" vlaue="<%=f.getFreeTitle() %>">
						</td>
					</tr>
	
					<div class="tr-2">
					<div class="td-1">⊙</div>
					<input type="hidden" name="memberNo" value="<%=m.getMemberNo()%>">
					<label for="td-2">첨부파일</label> 
					<input type="hidden" name="status" value="stay">
							<%if(f.getFilePath() != null) {%>
							<div class="file-box">
								<img src="/img/file.png" width="16px" class="delFile">
								<span class="delFile"><%=f.getFileName() %></span>
								<button type="button" class="btn bc1 delFile" id="fileDelBtn">삭제</button>
								<input type="file" name="file" style="display:none;">
								<input type="hidden" name="oldFilename" value="<%=f.getFileName() %>">
								<input type="hidden" name="oldFilepath" value="<%=f.getFilePath() %>">
							</div>
							<%} else { %>
								<input type="file" name="file">
							<%} %>		
					</div>
					
					<!-- 
					<tr class="tr-1">
						<th class="td-3">첨부파일</th>
						<td>
							<input type="hidden" name="status" value="stay">
							<%if(f.getFilePath() != null) {%>
							<div class="file-box">
								<img src="/img/file.png" width="16px" class="delFile">
								<span class="delFile"><%=f.getFileName() %></span>
								<button type="button" class="btn bc1 delFile" id="fileDelBtn">삭제</button>
								<input type="file" name="file" style="display:none;">
								<input type="hidden" name="oldFilename" value="<%=f.getFileName() %>">
								<input type="hidden" name="oldFilepath" value="<%=f.getFilePath() %>">
							</div>
							<%} else { %>
								<input type="file" name="file">
							<%} %>		
						</td>
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
						<button type="submit" class="bc bc11 bs1"> 수정등록</button>
						
				</div> 
				</div>
				<!--
					<tr class="tr-1">
						<th class="td-3">내용</th>
						<td><textarea class="input-form" name="freeContent"><%=f.getFreeContent() %></textarea></td>
					</tr>
					<tr class="tr-1">
						<th colspan="2"><button class="btn bc5 bs4">수정완료</button>
					</tr>
					-->
				
			</form>
			</div>
			</div>
			<script>
				$("#fileDelBtn").on("click", function(){
					$(".delFile").hide();
					$(this).next().show();
					$("[name=status]").val("delete");
				});
			</script>
		</div>

</body>
</html>