<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.iei.book.model.vo.BookComment"%>
<%@page import="java.util.HashMap"%>
<%@page import="kr.or.iei.book.model.vo.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Book b = (Book)request.getAttribute("b");
        	HashMap<Integer,String> map = (HashMap<Integer,String>)request.getAttribute("map");
        	ArrayList<BookComment> commentList = (ArrayList<BookComment>)request.getAttribute("commentList");
        	ArrayList<BookComment> reCommentList = (ArrayList<BookComment>)request.getAttribute("reCommentList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=b.getBookName()%> 상세페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
    	.center-wrap{
    		width: 90%;
    		margin: 20px auto;
    		overflow: hidden;
    	}
    	.input-box-wrap{
     		width: 90%;
     		margin: 20px auto;
     		background: #fff;
     		padding: 20px;
     		border-radius: 10px;
     	}
     	.input-box-wrap:first-child>.center-wrap>div{
     		float: left;
     	}
     	.img-viewer{
     		margin-bottom:10px;
     		width: 260px;
     		height: 330px;
     		margin-right: 80px;
     		border: 1px solid #ccc;
     		border-radius: 10px;
     	}
     	.book-info-wrap{
     		width:40%;
     		margin-top: 5px;
     	}
     	.book-detail{
     		overflow: hidden;
     	}
     	.book-detail>div{
     		float: left;
     	}
     	.book-detail>div:first-child>p{
     		width: 80px;
     		margin: 0;
     		color: #9D99A1;
     	}
     	.book-detail>div:last-child>p{
     		margin: 0;
     		margin-left: 20px;
     		color: #7F7C82;
     	}
     	.info-box{
     		margin-top: 20px;
     		margin-bottom: 20px;
     	}
     	.trace-tbl tr>td:first-child{
     		color: #9D99A1;
     		width: 99px;
     	}
     	.trace-tbl tr>td:not(:first-child){
     		width:40px;
     		color: #CDD0CB;
     	}
     	.hope{
     		font-size: 30px;
     		color: #FF5959;
     	}
     	.comment-box{
     		height: 200px;
     	}
     	.buyBtn{
     		margin: 0 auto;
     		display: block;
     		width: 300px;
     		height: 50px;
     		border-radius: 10px;
     		background-color: #BFA2DB;
     		color : #fff;
     		border: 1px solid #BFA2DB;
     		box-shadow: 1px 1px 8px 0px #C3BEF0;
     		cursor: pointer;
     		font-size: 1.1rem;
     	}
     	.buyBtn:hover{
     		background-color: #B79BD1;
     	}
     	.btn-box{
     		text-align: right;
     		width: 73%;
     		margin: 0 auto;
     	}
     	.btn-box>*{
     		border-radius: 10px;
     	}
		.inputCommentBox{
			margin: 20px auto;
			width: 90%;
			padding:20px;
			padding-top: 40px;
			background-color: #fff;
			border-radius: 10px;
		}
		.inputCommentBox>form>ul{
			list-style-type: none;
			display: flex;
			padding: 0;
		}
		.inputCommentBox>form>ul>li:first-child {
			width: 15%;
			display: flex;
			justify-content: center;
			align-items: center;
			border: 1px solid #ccc;
			border-right: none;
			border-top-left-radius: 5px;
			border-bottom-left-radius: 5px;
		}
		.inputCommentBox>form>ul>li:first-child>span {
			font-size: 80px;
		}
		.inputCommentBox>form>ul>li:nth-child(2) {
			width: 70%;
		}
		.inputCommentBox>form>ul>li:nth-child(2)>textarea.input-form{
			height: 96px;
			min-height: 96px;
		}
		.inputCommentBox>form>ul>li:last-child {
			width: 15%;
		}
		.inputRecommentBox{
			margin: 30px 0px;
			display: none;
		}
		.inputRecommentBox>form>ul{
			list-style-type: none;
			display: flex;
			padding: 0;
		}
		.inputRecommentBox>form>ul>li{
			width: 15%;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		.inputRecommentBox>form>ul>li:first-child>span {
			font-size: 50px;
			color: #ccc;
		}
		.inputRecommentBox>form>ul>li:nth-child(2) {
			width: 75%;
		}
		.inputRecommentBox>form>ul>li:nth-child(2)>textarea.input-form {
			height: 96px;
			min-height: 96px;
		}
		.inputRecommentBox>form>ul>li:last-child {
			width: 15%;
		}
		.wrap{
			width: 70%;
			background-color: #F0ECEB;
			margin: 0 auto;
			padding: 20px;
			border-radius: 5px;
		}
		.secret{
			text-align: right;
			margin-top: 10px;
		}
		.bc15{
			background-color: #BFA2DB;
			border: 2px solid #BFA2DB;
			color:#fff;
			cursor: pointer;
			border-top-right-radius: 10px;
			border-bottom-right-radius: 10px;
		}
    </style>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="wrap">
			<div class="input-box-wrap">
				<div class="center-wrap">
					<div class="img-viewer">
						<%if(b.getFilepath()!=null){ %>
						<img src="/upload/book/<%=b.getFilepath()%>" width="100%" height="100%" style="border-radius: 10px;"> 
						<%}else{ %>
						<img src="/img/camera.png" style="display: block; margin: 140px auto;" width="50px" height="50px">
						<%} %>
					</div>
					<div class="book-info-wrap">
						<div class="book-info">
							<div class="page-title" style="padding-top:0px;"><%=b.getBookName() %></div>
							<div class="book-detail">
								<div>
									<p class="detail-ex">저자</p>
									<p class="detail-ex">출판사</p>
									<p class="detail-ex">필요과목</p>
								</div>
								<div>
									<p class="detail-ex"><%=b.getBookWriter() %></p> 
									<p class="detail-ex"><%=b.getPublisher() %></p>
									<p class="detail-ex"><%=map.get(b.getSubjectNo()) %></p>
								</div>
							</div>
							<div class="info-box">
								<table class="trace-tbl">
									<tr>
										<td>필기 흔적</td>
										<td style="<%if(b.getWrittenTrace()==null){%>color:#9E9365;<%}%>">없음</td>
										<td style="<%if(b.getWrittenTrace()!=null){%>color:#9E9365;<%}%>">있음</td>
									</tr>
									<tr>
										<td>훼손 흔적</td>
										<td style="<%if(b.getTornTrace()==null){%>color:#9E9365;<%}%>">없음</td>
										<td style="<%if(b.getTornTrace()!=null){%>color:#9E9365;<%}%>">있음</td>
									</tr>
									<tr>
										<td>변색 흔적</td>
										<td style="<%if(b.getDiscolor()==null){%>color:#9E9365;<%}%>">없음</td>
										<td style="<%if(b.getDiscolor()!=null){%>color:#9E9365;<%}%>">있음</td>
									</tr>
									<tr>
										<td>이름 기입</td>
										<td style="<%if(b.getNameTrace()==null){%>color:#9E9365;<%}%>">없음</td>
										<td style="<%if(b.getNameTrace()!=null){%>color:#9E9365;<%}%>">있음</td>
									</tr>
								</table>
							</div>
							<%
								DecimalFormat dec = new DecimalFormat("###,###");
								String price = dec.format(b.getHopePrice());
							%>
							<div class="info-box-wrap">
								<span class="hope"><%=price %>원</span>
								<span class="listPrice" style="text-decoration: line-through; color: #ccc;"><%=b.getListPrice() %>원</span>
							</div>
						</div>
					</div>
				</div>
				<%if(m!=null&&m.getMemberNo()==b.getMemberNo()){ 
					if(b.getSellEnd()==null){ %>
						<button class="buyBtn" onclick="bookSellUpdate(<%=b.getBookNo()%>)">판매 완료하기</button>
					<%}else{ %>
						<button type="button" class="bc1 buyBtn">이 책은 판매가 완료되었습니다.</button>
					<%} %>
				<%}else{ 
					if(b.getSellEnd()==null){ %>
						<button type="button" class="buyBtn passBtn">구매가능합니다.</button>
					<%}else{ %>
						<button type="button" class="bc1 buyBtn">이 책은 판매가 완료되었습니다.</button>
				<%} } %>
			</div>
			<div class="input-box-wrap">
				<div class="center-wrap comment-box">
					<div class="page-title" style="padding-top:0px;">판매자 코멘트</div>
					<%if(b.getBookComment()!=null){ %>
					<p style="padding-left: 10px;"><%=b.getBookComment() %></p>
					<%} %>
				</div>
			</div>
			<%if(m!=null){ %>
			<div class="inputCommentBox">
				<form action="/insertBookComment.do" method="post">
					<ul>
						<li>
							<span class="material-icons">account_box</span>
						</li>
						<li>
							<input type="hidden" name="bcWriter" value="<%=m.getMemberId() %>">
							<input type="hidden" name="bookRef" value="<%=b.getBookNo() %>">
							<input type="hidden" name="bcRef" value="0">
							<textarea class="input-form" name="bcContent"></textarea>
						</li>
						<li>
							<button type="submit" class="bc15 bs4">등록</button>
						</li>
					</ul>
					<div class="secret"><input type="checkbox" name="bcSecret" id="bcSecret" value="o"><label for="bcSecret"> 비밀댓글</label></div>					
				</form>
			</div>
			<%} %>
			<%if(!commentList.isEmpty()){ %>
			<div class="commentBox input-box-wrap">
				<%for(BookComment bc : commentList){ %>
					<ul class="posting-comment">
						<li>
							<span class="material-icons">account_box</span>
						</li>
						<li>
							<p class="comment-info">
							<%if(bc.getBcSecret()!=null){ %>
								<%if(m!=null && (m.getGrade().equals("관리자") || m.getMemberNo()==b.getMemberNo() || bc.getBcWriter().equals(m.getMemberId()))){ %>
									<span><%=bc.getBcWriter() %></span>
								<%}else { %>
									<span>익명</span>
								<%} %>
							<%}else {%><span><%=bc.getBcWriter() %></span><%} %>
								<span><%=bc.getBcDate() %></span>
								</p>
								
							<%if(bc.getBcSecret()!=null){ %>
								<%if(m!=null && (m.getGrade().equals("관리자") || m.getMemberNo()==b.getMemberNo() || bc.getBcWriter().equals(m.getMemberId()))){ %>
									<p class="comment-content"><img src="/img/secret.png"><%=bc.getBcContent() %></p>
								<%}else { %>
									<p class="comment-content"><img src="/img/secret.png">비밀댓글입니다.</p>
								<%} %>
							<%}else{ %><p class="comment-content"><%=bc.getBcContent() %></p><%} %>
							
							<textarea name="bcContent" class="input-form" style="display:none;min-height: 90px;"><%=bc.getBcContent() %></textarea>
							<p class="comment-link">
								<%if(m != null) { %>
									<%if(m.getMemberId().equals(bc.getBcWriter())) { %>
										<a href="javascript:void(0)" onclick="modifyComment(this, '<%=bc.getBcNo()%>','<%=b.getBookNo()%>')">수정</a>
										<a href="javascript:void(0)" onclick="deleteComment(this, '<%=bc.getBcNo()%>','<%=b.getBookNo()%>')">삭제</a>
									<%} 
									if(b.getMemberNo()==m.getMemberNo() || bc.getBcWriter().equals(m.getMemberId())){%>
									<a href="javascript:void(0)" class="recShow">답글달기</a><%} %>
								<%}//댓글 링크모음 로그인 체크 %>
							</p>
						</li>
					</ul>
					
					<%if(m != null) { %>
					<div class="inputRecommentBox">
						<form action="/insertBookComment.do" method="post">
							<ul>
								<li>
									<span class="material-icons">subdirectory_arrow_right</span>
								</li>
								<li>
									<%if(bc.getBcSecret()!=null){ %>
									<input type="hidden" name="bcSecret" value="o">
									<%} %>
									<input type="hidden" name="bcWriter" value="<%=m.getMemberId() %>">
									<input type="hidden" name="bookRef" value="<%=b.getBookNo() %>">
									<input type="hidden" name="bcRef" value="<%=bc.getBcNo() %>">
									<textarea class="input-form" name="bcContent"></textarea>
								</li>
								<li>
									<button type="submit" class="bc15 bs4">등록</button>
								</li>
							</ul>
						</form>
					</div>
					<%}//대댓글 입력 form 작성조건 %>
					<%for(BookComment bcc : reCommentList){ %>
						<%if(bcc.getBcRef() == bc.getBcNo()) { %>
							<ul class="posting-comment reply">
								<li>
									<span class="material-icons">subdirectory_arrow_right</span>
									<span class="material-icons">account_box</span>
								</li>
								<li>
									<p class="comment-info">
									<%if(bcc.getBcSecret()!=null){ %>
										<%if(m!=null && (m.getGrade().equals("관리자") || m.getMemberNo()==b.getMemberNo() || bcc.getBcWriter().equals(m.getMemberId()) || bc.getBcWriter().equals(m.getMemberId()))){ %>
											<span><%=bcc.getBcWriter() %></span>
										<%}else{ %>
											<span>익명</span>
										<%} %>
									<%}else{ %>
										<span><%=bcc.getBcWriter() %></span>
									<%} %>
										<span><%=bcc.getBcDate() %></span>
									</p>
									<%if(bcc.getBcSecret()!=null){ %>
										<%if(m!=null && (m.getGrade().equals("관리자") || m.getMemberNo()==b.getMemberNo() || bcc.getBcWriter().equals(m.getMemberId()) || bc.getBcWriter().equals(m.getMemberId()))){ %>
											<p class="comment-content"><img src="/img/secret.png"> <%=bcc.getBcContent() %></p>
										<%}else{ %>
											<p class="comment-content"><img src="/img/secret.png">비밀댓글입니다.</p>
										<%} %>
									<%}else{ %>
										<p class="comment-content"><%=bcc.getBcContent() %></p>
									<%} %>
									<textarea name="bcContent" class="input-form" style="display:none;min-height: 90px;"><%=bcc.getBcContent() %></textarea>
									<p class="comment-link">
										<%if(m != null) { %>
											<%if(m.getMemberId().equals(bcc.getBcWriter())) { %>
												<a href="javascript:void(0)" onclick="modifyComment(this, '<%=bcc.getBcNo()%>','<%=b.getBookNo()%>')">수정</a>
												<a href="javascript:void(0)" onclick="deleteComment(this, '<%=bcc.getBcNo()%>','<%=b.getBookNo()%>')">삭제</a>
											<%} %>
										<%} %>
									</p>
								</li>
							</ul>
						<%}//해당 댓글의 대댓글인지 검사하는 조건 %>
					<%} %>
				<%}//댓글 출력용 for문 종료 %>
				
			</div>
		<%} %>
		</div>
		<%if(m!=null&&b.getMemberNo()==m.getMemberNo()){ %>
		<div class="btn-box"> 
			<button class="btn bc15" onclick="bookDelete('<%=b.getBookNo() %>');">삭제</button>
			<a class="btn bc15" href="/bookUpdateFrm.do?bookNo=<%=b.getBookNo() %>">수정</a>
		</div>
		<%} %>
	</div>
	<script>
		function bookSellUpdate(bookNo){
			if(confirm("판매완료로 변경하시겠습니까?")){
				location.href="/bookSellUpdate.do?bookNo="+bookNo;
			}			
		}
		function bookDelete(bookNo){
			if(confirm("삭제하시겠습니까?")){
				location.href="/bookDelete.do?bookNo="+bookNo;
			}
		}
		$(".recShow").on("click",function(){
			const idx = $(".recShow").index(this);
			if($(this).text() == "답글달기"){
				$(this).text("취소");
			}else{
				$(this).text("답글달기");
			}
			$(".inputRecommentBox").eq(idx).toggle();
			$(".inputRecommentBox").eq(idx).find("textarea").focus();
		});
		function modifyComment(obj,bcNo,bookNo) {
			$(obj).parent().prev().show();//textarea를 화면에 보여줌
			$(obj).parent().prev().prev().hide();//기존 댓글은 화면에서 숨김
			//수정 -> 수정완료
			$(obj).text("수정완료");
			$(obj).attr("onclick", "modifyComplete(this, '"+bcNo+"','"+bookNo+"')");
			//삭제 -> 수정취소
			$(obj).next().text("수정취소");
			$(obj).next().attr("onclick", "modifyCancel(this, '"+bcNo+"','"+bookNo+"')");
			//답글달기버튼 숨김
			$(obj).next().next().hide();
		}
		function modifyCancel(obj,bcNo,bookNo) {
			$(obj).parent().prev().hide();		//textarea 숨김
			$(obj).parent().prev().prev().show();//기존댓글 보여줌
			//수정완료 -> 수정
			$(obj).prev().text("수정");
			$(obj).prev().attr("onclick", "modifyComment(this, '"+bcNo+"','"+bookNo+"')");
			//수정취소 -> 삭제
			$(obj).text("삭제");
			$(obj).attr("onclick", "deleteComment(this, '"+bcNo+"','"+bookNo+"')");
			//답글달기버튼 보여줌
			$(obj).next().show();
		}
		function modifyComplete(obj,bcNo,bookNo) {
			//form태그 생성
			const form = $("<form action='/bookCommentUpdate.do' method='post'></form>");
			//form태그 자식으로 input태그 추가(ncNo)
			form.append($("<input type='text' name='bcNo' value='"+bcNo+"'>"));
			//form태그 자식으로 input태그 추가(noticeNo)
			form.append($("<input type='text' name='bookNo' value='"+bookNo+"'>"));
			//form태그 자식으로 수정한 댓글 내용이 들어있는 textarea를 추가
			form.append($(obj).parent().prev());
			//생성된 form태그를 html 본문으로 삽입
			$("body").append(form);
			//form태그 submit
			form.submit();
		}
		function deleteComment(obj,bcNo,bookNo) {
			if(confirm("댓글을 삭제하시겠습니까?")){
				location.href="/deleteBookComment.do?bcNo="+bcNo+"&bookNo="+bookNo;
			}
		}
	</script>
</body>
</html>