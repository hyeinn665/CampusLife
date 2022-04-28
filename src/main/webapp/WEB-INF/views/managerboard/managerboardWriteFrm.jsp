<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#managerBoardWrite td, #managerBoardWrite th{
		border : 1px solid #ccc;
	}
	span{
		padding-right:40px;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	
	<div class="page-content">
		<div class="page-title">공지사항 작성</div>
		<!-- 파일 업로드 조건 2가지> (1)method=post (2)enctype="multipart/form-data" -->
		<form action="/managerBoardWrite.do" method="post" enctype="multipart/form-data">
			<table class="tbl" id="managerBoardWrite">
				<tr class="tr-1">
					<th class="td-4">제목</th>
					<td colspan="3">
						<input type="text" name="mbTitle" class="input-form">
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-4">분류</th>
					<td colspan="3">
						<input type="radio" class="category" name="category" id="one" value="학과 공지사항" checked><label for="one"><span>학과 공지사항</span></label>
						<input type="radio" class="category" name="category" id="two" value="공모전"><label for="two"><span>공모전</span></label>
						<input type="radio" class="category" name="category" id="three" value="동아리/학회"><label for="three"><span>동아리/학회</span></label>
						<input type="radio" class="category" name="category" id="four" value="취업지원"><label for="four"><span>취업지원</span></label>
					</td> 
				</tr>
				<tr class="tr-1">
					<th class="td-4">작성자</th>
					<td>
						<input type="hidden" name="mbWriter" value="<%=m.getMemberId()%>"><%=m.getMemberId()%>
					</td>
					<th class="td-4">첨부파일</th>
					<td><input type="file" name="file"></td>
				</tr>
				<tr class="tr-1">
					<th class="td-4">내용</th>
					<td colspan="3" style="text-align: left;"><textarea id="managerBoardContent" name="mbContent" class="input-form"></textarea></td>
				</tr>
				<tr>
					<td colspan="4"><button type="submit" class="btn bc11 bs4">공지사항 등록</button>
				</tr>
			</table>
		</form>
		<p class="result"></p>
		
	</div>
	
	
	<script>
		//$('button').click(function(){
			//  let category = $("input[name='category']:checked").val();
			  //$('.result').html(category);

			  //location.href="/managerBoardWrite.do?mbTitle="+mbTitle+"&mbWriter="+mbWriter+"&mbContent="+mbContent+"&filename="+file+"&filepath="+file;
			//});
		
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
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>