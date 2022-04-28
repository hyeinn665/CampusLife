<%@page import="kr.or.iei.book.model.vo.Book"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    HashMap<Integer,String> map = (HashMap<Integer,String>)request.getAttribute("map");
        	Book b = (Book)request.getAttribute("b");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=b.getBookName()%> 수정하기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
     <style>
     	.input-box{
     		width: 80%;
     		margin: 20px auto;
     		background-color: #F0ECEB;
     		padding: 20px;
     		padding-bottom: 40px;
     		border-radius: 25px;
     	}
     	.input-wrap>*{
     		margin : 5px auto;
     		font-size: 1em;
     	}
     	.input-wrap{
     		width: 70%;
     		margin: 0 auto;
     	}
     	.form-select{
     		height: 50px;
     		border-radius: 10px;
     		width:100%;
     		border: 1px solid #ccc;
     		margin-top: 0;
     		padding-left: 10px;
     	}
     	.nextBtn{
     		display: block; 
     		margin-top: 10px;
     		height: 40px;
     		width:70%;
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
     	.nextBtn{
     		background-color: #BFA2DB;
     		color:#fff;
     		border: 1px solid #BFA2DB;
     		font-size: 1.1em;
     		cursor: pointer;
     	}
     	.nextBtn:hover{
     		background-color: #B79BD1;
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
     		width: 50%;
     		padding-bottom:20px;
     	}
     	.delFile{
     		display: block;
     		margin: 0 auto;
     		width: 200px;
     		padding: 5px;
     		margin-top: 10px;
     		border-radius: 10px;
     	}
     	#oldImg-view {
		    display: block;
		    margin: 0 auto;
		}
     </style>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<form action="/bookUpdate.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="memberNo" value="3">
			<input type="hidden" name="bookNo" value="<%=b.getBookNo() %>">
			<div class="input-box bookInfo">
				<div class="page-title">책 정보를 적어주세요</div>
				<div class="input-wrap">
					<input type="text" name="bookName" id="bookName" class="input-form bkInfo" placeholder="책이름" value="<%=b.getBookName()%>">
					<input type="text" name="bookWriter" id="bookWriter" class="input-form bkInfo" placeholder="저자" value="<%=b.getBookWriter() %>">
					<input type="text" name="publisher" id="publisher" class="input-form bkInfo" placeholder="출판사" value="<%=b.getPublisher()%>">
					<input type="text" name="listPrice" id="listPrice" class="input-form bkInfo" placeholder="정가" onchange="numCk(this);"  value="<%=b.getListPrice()%>">
					<select name="subjectNo" class="form-select bkInfo" aria-label="Default select example">
						<option value="" selected>--과목 선택--</option>
						<%for(int key : map.keySet()){ %>
						<option value="<%=key %>" <%if(b.getSubjectNo()==key){ %>selected<%} %>><%=map.get(key) %></option>
						<%} %>
					</select>
				</div>
			</div>
			<div class="trace input-box">
				<div class="page-title">책 상태를 체크해주세요</div>
				<div class="input-wrap">
					<div class="form-check-wrap">
						<div class="form-check">
							 <input class="form-check-input trace-input" type="checkbox" value="o" id="writtenTrace" name="writtenTrace" <%if(b.getWrittenTrace()!=null){%>checked<%}%>>
							 <label class="form-check-label" for="writtenTrace">
							   필기 흔적이 있는 경우
							 </label>
						</div>
					</div>
					<div class="form-check-wrap">
						<div class="form-check">
							 <input class="form-check-input trace-input" type="checkbox" value="o" id="tornTrace" name="tornTrace" <%if(b.getTornTrace()!=null){%>checked<%}%>>
							 <label class="form-check-label" for="tornTrace">
							   찢어진 흔적이 있는 경우
							 </label>
						</div>
					</div>
					<div class="form-check-wrap">
						<div class="form-check">
							 <input class="form-check-input trace-input" type="checkbox" value="o" id="discolor" name="discolor" <%if(b.getDiscolor()!=null){%>checked<%}%>>
							 <label class="form-check-label" for="discolor">
							   페이지 변색이 있는 경우
							 </label>
						</div>
					</div>
					<div class="form-check-wrap">
						<div class="form-check">
							 <input class="form-check-input trace-input" type="checkbox" value="o" id="nameTrace" name="nameTrace" <%if(b.getNameTrace()!=null){%>checked<%}%>>
							 <label class="form-check-label" for="nameTrace">
							   이름기입이 있는 경우
							 </label>
						</div>
					</div>
				</div>
			</div>
			<div class="input-box">
				<div class="page-title">희망가격을 입력하세요</div>
				<div class="input-wrap">
					<input type="text" name="hopePrice" id="hopePrice" class="input-form" placeholder="희망가격" onchange="numCk(this);" value="<%=b.getHopePrice()%>">
				</div>
			</div>
			<div class="input-box">
				<div class="page-title">사진을 첨부하세요</div>
				<input type="hidden" name="status" value="stay">
				<%if(b.getFilepath()==null){ %>
				<div id="img-viewer">
					<img id="img-view" src="/img/camera.png">
				</div>
				<div class="input-wrap">
					<input type="file" name="file" class="input-form" onChange="loadImg(this);" accept=".jpg,.png,.jpeg">
				</div>
				<%}else { %>
				<div id="img-viewer" style="margin-bottom:20px;">
					<img id="oldImg-view" src="/upload/book/<%=b.getFilepath()%>" width="200px" height="200px" style="margin:0 auto;">
					<img id="img-view" src="/img/camera.png" style="display: none;">
					<button type="button" class="bc2 delFile" id="fileDelBtn">삭제</button>
				</div>
				<div class="input-wrap">
					<input type="file" name="file" class="input-form newFile" style="display:none;" onChange="loadImg(this);" accept=".jpg,.png,.jpeg">
					<input type="text" name="oldFilepath" class="input-form" style="background: #ccc;" value="<%=b.getFilepath() %>" readonly>
				</div>
				<%} %>
			</div>
			<div class="input-box">
				<div class="page-title">추가 설명을 자유롭게 적어주세요.</div>
				<div class="input-wrap">
					<textarea class="input-form" name="bookComment" id="bookComment" maxlength="300" placeholder="300자 이내"><%=b.getBookComment()%></textarea>
				</div>
			</div>
			<div class="input-box">
				<div class="page-title">마지막으로 확인해주세요</div>
				<div class="input-wrap">
					<ul class="agreeList">
						<li>등록 후에는 판매 기록이 남게됩니다.</li>
						<li>거래에 관한 책임은 판매자에게 있습니다.</li>
						<li>커뮤니티 이용규칙에 어긋날 경우 삭제됩니다.</li>
						<li>판매 이후 [판매 완료]를 눌러주시기 바랍니다.</li>
					</ul>
					<button class="nextBtn" type="submit">수정하기</button>
				</div>
			</div>
		</form>
	</div>
	<script>
		$("#fileDelBtn").on("click",function(){
			$(".delFile").hide();
			$(".newFile").show();
			$("[name=status]").val("delete");
			$("[name=oldFilepath]").hide();
			$("#oldImg-view").hide();
			$("#img-view").show();
		});
		function loadImg(f){
			console.log(f.files);	//input file에서 선택된 파일이 여러개일 수 있으므로 배열로 처리
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
	</script>
</body>
</html>