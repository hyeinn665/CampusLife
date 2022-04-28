<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    HashMap<Integer, String> map = (HashMap<Integer, String>)request.getAttribute("map");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매하기</title>
     <style>
     	.input-wrap{
     		width: 70%;
     		margin: 30px auto;
     		background-color: #F0ECEB;
     		padding: 20px;
     		padding-bottom: 30px;
     		border-radius: 25px;
     	}
     	.inputBox{
     		width: 70%;
     		margin: 10px auto;
     	}
     	.inputBox>*{
     		width: 100%;
     		margin : 5px auto;
     		font-size: 1em;
     	}
     	.form-select{
     		height: 50px;
     		border-radius: 10px;
     		border: 1px solid #ccc;
     		margin-top: 0;
     		padding-left: 10px;
     	}
     	.nextBtn{
     		background-color: #BFA2DB;
     		margin-top: 10px;
     		height: 40px;
     		color:#fff;
     		border: 1px solid #BFA2DB;
     		font-size: 1.1em;
     		cursor: pointer;
     	}
     	.nextBtn:hover{
     		background-color: #B79BD1;
     	}
     	.form-check-wrap{
     		background: #fff;
     		border-radius: 10px;
     		height: 30px;
     	}
     	.form-check{
     		padding-left: 20px;
     		padding-top: 3px;
     	}
     	.input-form, .nextBtn{
     		border-radius: 10px;
     	}
     	#img-viewer{
     		margin-bottom: 30px;
     	}
     	#img-view{
     		display: block;
     		margin: 0 auto;
     	}
     	.page-title{
     		text-align: center;
     	}
     	.agreeList{
     		padding-bottom:20px;
     		margin-left: 150px;
     	}
     	.title-wrap{
     		text-align: center;
     		width: 70%;
     		margin: 0 auto;
     		font-size: 1.8em;
     		font-weight: bolder;
     		color:#A63EC5;
     	}
     </style>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="title-wrap"><img src="/img/book.png"> 책 거래 글쓰기</div>
		<form action="/bookWrite.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="memberNo" value="<%=m.getMemberNo()%>">
			<div class="input-wrap bookInfo">
				<div class="page-title">책 정보를 적어주세요</div>
				<div class="inputBox">
					<input type="text" name="bookName" id="bookName" class="input-form must" placeholder="책이름">
					<input type="text" name="bookWriter" id="bookWriter" class="input-form must" placeholder="저자">
					<input type="text" name="publisher" id="publisher" class="input-form must" placeholder="출판사">
					<input type="text" name="listPrice" id="listPrice" class="input-form must" placeholder="정가" onchange="numCk(this);">
					<select name="subjectNo" class="form-select must" aria-label="Default select example">
						<option value="" selected>--과목 선택--</option>
						<%for(int key : map.keySet()){ %>
						<option value="<%=key %>"><%=map.get(key) %></option>
						<%} %>
					</select>
				</div>
			</div>
			<div class="trace input-wrap">
				<div class="page-title">책 상태를 체크해주세요</div>
				<div class="inputBox">
					<div class="form-check-wrap">
						<div class="form-check">
							 <input class="form-check-input trace-input" type="checkbox" value="o" id="writtenTrace" name="writtenTrace">
							 <label class="form-check-label" for="writtenTrace">
							   필기 흔적이 있는 경우
							 </label>
						</div>
					</div>
					<div class="form-check-wrap">
						<div class="form-check">
							 <input class="form-check-input trace-input" type="checkbox" value="o" id="tornTrace" name="tornTrace">
							 <label class="form-check-label" for="tornTrace">
							   찢어진 흔적이 있는 경우
							 </label>
						</div>
					</div>
					<div class="form-check-wrap">
						<div class="form-check">
							 <input class="form-check-input trace-input" type="checkbox" value="o" id="discolor" name="discolor">
							 <label class="form-check-label" for="discolor">
							   페이지 변색이 있는 경우
							 </label>
						</div>
					</div>
					<div class="form-check-wrap">
						<div class="form-check">
							 <input class="form-check-input trace-input" type="checkbox" value="o" id="nameTrace" name="nameTrace">
							 <label class="form-check-label" for="nameTrace">
							   이름기입이 있는 경우
							 </label>
						</div>
					</div>
				</div>
			</div>
			<div class="input-wrap">
				<div class="page-title">희망가격을 입력하세요</div>
				<div class="inputBox">
					<input type="text" name="hopePrice" id="hopePrice" class="input-form must" placeholder="희망가격" onchange="numCk(this);">
				</div>
			</div>
			<div class="input-wrap">
				<div class="page-title">사진을 첨부하세요</div>
				<div class="inputBox">
					<div id="img-viewer">
						<img id="img-view" src="/img/camera.png">
					</div>
					<input type="file" name="file" class="input-form" onChange="loadImg(this);" accept=".jpg,.png,.jpeg">
				</div>
			</div>
			<div class="input-wrap">
				<div class="page-title">추가 설명을 자유롭게 적어주세요.</div>
				<div class="inputBox">
					<textarea class="input-form" name="bookComment" id="bookComment" maxlength="300" placeholder="300자 이내"></textarea>
				</div>
			</div>
			<div class="input-wrap">
				<div class="page-title">마지막으로 확인해주세요</div>
				<div class="inputBox">
					<ul class="agreeList">
						<li>등록 후에는 판매 기록이 남게됩니다.</li>
						<li>거래에 관한 책임은 판매자에게 있습니다.</li>
						<li>커뮤니티 이용규칙에 어긋날 경우 삭제됩니다.</li>
						<li>판매 이후 [판매 완료]를 눌러주시기 바랍니다.</li>
					</ul>
					<button class="nextBtn" type="submit">동의 및 등록하기</button>
				</div>
			</div>
		</form>
	</div>
	<script>
		function loadImg(f){
			//배열의 길이가 0이 아니고, 배열 첫번째 파일이 정상적인 파일이면 
			if(f.files.length != 0 && f.files[0] != 0){
				const reader = new FileReader();//파일의 정보를 읽어올 수 있는 객체 생성
				reader.readAsDataURL(f.files[0]);//선택한 파일의 경로를 가져옴
				//읽기가 완료되면 onload이벤트 발생(비동기식이기때문에)/위에서 가져온 경로가 e에 들어있음
				reader.onload = function(e){
					$("#img-view").attr("src",e.target.result);
					$("#img-view").attr("width","200px");
					$("#img-view").attr("height","200px");
				}
			}else{
				$("#img-view").attr("src","");
			}
		}
		function numCk(obj){
			const numCk = /^[0-9]$/;
			if(isNaN($(obj).val())){
				alert("숫자만 입력가능합니다.");
				$(obj).val("").select();
			}	
		}
		$(".nextBtn").on("click",function(){
			let trueCk = false;
			const must = $(".must");
			must.each(function(index,item){
				if($(item).val()==""){
					trueCk = true;
				}
			});
			if(!trueCk){
				if(!confirm("글 등록하시겠습니까?")){
					return false;
				}
			}else{
				alert("입력칸을 모두 입력해주세요.");
				must.eq(0).focus();
				return false;
			}
		});
	</script>
</body>
</html>