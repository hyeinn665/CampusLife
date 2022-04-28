<%@page import="kr.or.iei.managerboard.model.vo.ManagerBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ManagerBoard mb = (ManagerBoard)request.getAttribute("mb");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.managerBoardUpdateFrm td, .managerBoardUpdateFrm th{
		border: 1px solid #eee;
	}
	.file-box{
		display: flex;
		align-items: center;
		jusfity-content: center;
	}
	.noticeUpdateFrm .file-box>*{
		padding: 0.2rem 0.8rem;
	}
	span{
		padding-right:20px;
	}
</style>
</head>
<body>
	<%@include file = "/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	
	<div class="page-content">
		<div class="page-title">공지사항 수정</div>
		<form action = "/managerBoardUpdate.do" method="post" enctype="multipart/form-data">
			<!-- 파일처리 업데이트를 위한 noticeNo는 아무데나 넣기 -->
			<input type="hidden" name="mbNo" value="<%=mb.getMbNo() %>">
			<table class="tbl managerBoardUpdateFrm">
				<tr class="tr-1">
					<th class="td-4">제목</th>
					<td colspan="3">
						<input type="text" name="mbTitle" class="input-form" value="<%=mb.getMbTitle() %>">
					</td>
				</tr>
				
				<tr class="tr-1">
					<th class="td-4">분류</th>
					<td style="width:500px;">
						<%if(mb.getCategory().equals("학과 공지사항")) {%>
						<input type="radio" class="category" name="category" id="one" value="학과 공지사항" checked><label for="one"><span>학과 공지사항</span></label>
						<input type="radio" class="category" name="category" id="two" value="공모전"><label for="two"><span>공모전</span></label>
						<input type="radio" class="category" name="category" id="three" value="동아리/학회"><label for="three"><span>동아리/학회</span></label>
						<input type="radio" class="category" name="category" id="four" value="취업지원"><label for="four"><span>취업지원</span></label>
						<%} else if(mb.getCategory().equals("공모전")) {%>
						<input type="radio" class="category" name="category" id="one" value="학과 공지사항"><label for="one"><span>학과 공지사항</span></label>
						<input type="radio" class="category" name="category" id="two" value="공모전" checked><label for="two"><span>공모전</span></label>
						<input type="radio" class="category" name="category" id="three" value="동아리/학회"><label for="three"><span>동아리/학회</span></label>
						<input type="radio" class="category" name="category" id="four" value="취업지원"><label for="four"><span>취업지원</span></label>
						<%} else if(mb.getCategory().equals("동아리/학회")) {%>
						<input type="radio" class="category" name="category" id="one" value="학과 공지사항"><label for="one"><span>학과 공지사항</span></label>
						<input type="radio" class="category" name="category" id="two" value="공모전"><label for="two"><span>공모전</span></label>
						<input type="radio" class="category" name="category" id="three" value="동아리/학회" checked><label for="three"><span>동아리/학회</span></label>
						<input type="radio" class="category" name="category" id="four" value="취업지원"><label for="four"><span>취업지원</span></label>
						<%} else if(mb.getCategory().equals("취업지원")) {%>
						<input type="radio" class="category" name="category" id="one" value="학과 공지사항"><label for="one"><span>학과 공지사항</span></label>
						<input type="radio" class="category" name="category" id="two" value="공모전"><label for="two"><span>공모전</span></label>
						<input type="radio" class="category" name="category" id="three" value="동아리/학회"><label for="three"><span>동아리/학회</span></label>
						<input type="radio" class="category" name="category" id="four" value="취업지원" checked><label for="four"><span>취업지원</span></label>
						<%} %>
					</td>
					<th class="td-4">첨부파일</th>
					<td>	<!-- 첨부파일은 아직 '사용자' 컴퓨터 내에만 있는 것이므로 눈으로 보여주는 장치 설정해줌(눈속임?)-->
						<input type="hidden" name="status" value="stay">
						<%if(mb.getFilepath()!=null) {%>
							<div class="file-box">
								<img src="/img/file.png" width="16px" class="delFile">
								<span class="delFile"><%=mb.getFilename() %></span>
								<button type="button" class="btn bc1 delFile" id="fileDelBtn">삭제</button>
								<input type="file" name="file" style="display:none;">
								<!-- 기존 파일명 유지하기 위한 작업 -->
								<input type="hidden" name="oldFilename" value="<%=mb.getFilename() %>">							
								<input type="hidden" name="oldFilepath" value="<%=mb.getFilepath() %>">							
							</div>
						<%} else { %>
							<input type="file" name="file">						
						<%} %>
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-4">내용</th>
					<td colspan="3" style="text-align: left;"><textarea class="input-form" id="managerBoardContent" name="mbContent"><%=mb.getMbContentBr() %></textarea></td>
				</tr>
				<tr class="tr-1">
					<th colspan="4"><button class="btn bc11 bs4">수정완료</button></th>
				</tr>
			</table>			
		</form>
		
		<script>
		$("#managerBoardContent").summernote({
			height: 400,
			lang: "ko-KR",
			callbacks: {
				onImageUpload : function(files){
					uploadImage(files[0],this); //this=summernote editor
				}
			}
		});
		//editor 정보랑 같이 여기로 전달함
		function uploadImage(file,editor){
			//ajax를 통해 서버에 이미지를 업로드하고 업로드 경로를 받아옴
			//form 태그와 동일한 효과를 내는 FormData객체 생성
			const form = new FormData();
			form.append("file",file);
			$.ajax({
				url : "/uploadImages.do",
				type : "post", //파일이니까
				data : form,	
				processData : false, //파일이니까
				contentType : false, //파일이니까
				success : function(data){	
					//결과로 받은 이미지파일 경로를 에디터에 추가
					$(editor).summernote("insertImage",data);
				}
			});
			//processData : 기본값 true {key1:value1, key2:value2, key3:value3}
			//				-> 파일전송 시 String이 아니라 파일형태로 전송하기 위해서 기본값 제거
			//contentType : 기본값 "application/x-www-form-urlencoded;charset=UTF-8"
			//				-> form태그 전송 시 enctype의 기본값임
			//				-> enctype="multipart/form-data"로 설정하기 위해 기본값을 제거
		}

			$("#fileDelBtn").on("click",function(){
				$(".delFile").hide();
				$(this).next().show();
				$("[name=status]").val("delete");
			});
		</script>
		
	</div>
	<%@include file = "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>