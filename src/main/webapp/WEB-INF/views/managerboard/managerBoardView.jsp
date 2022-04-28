<%@page import="kr.or.iei.managerboard.model.vo.NoticeComment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.iei.managerboard.model.vo.ManagerBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ManagerBoard mb = (ManagerBoard)request.getAttribute("mb");
    	ArrayList<NoticeComment> commentList = (ArrayList<NoticeComment>)request.getAttribute("commentList"); 
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style>
		#managerBoardView th, #managerBoardView td{
			border: 1px solid #eee;
		}
		#managerBoardContent{
			min-height: 300px;
			text-align: left;
			font-family: ns-light;
		}
		
		.inputCommentBox{
			margin: 50px;
		}
		.inputCommentBox>form>ul{
			list-style-type: none;
			display: flex;
		}
		.inputCommentBox>form>ul>li:first-child{
			width: 15%;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		.inputCommentBox>form>ul>li:first-child>span{
			font-size: 80px;
			color: #ccc;
		}
		.inputCommentBox>form>ul>li:nth-child(2){
			width: 75%;
		}
		.inputCommentBox>form>ul>li:nth-child(2)>textarea.input-form{
			height: 96px;
			min-height: 96px;
		}
		.inputCommentBox>form>ul>li:last-child{
			width:10%;
		}
				
	</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">공지사항</div>
		<table class="tbl" id="managerBoardView">
			<tr class="tr-3">
				<th colspan="6" class="td-4"><%=mb.getMbTitle() %></th>
			</tr>
			<tr class="tr-1">
				<th class="td-4" style="border-bottom: 1px solid #dad9d9;">작성자</th>
				<td><%=mb.getMbWriter() %></td>
				<th class="td-4" style="border-bottom: 1px solid #dad9d9;">조회수</th>
				<td><%=mb.getReadCount() %></td>
				<th class="td-4">작성일</th>
				<td><%=mb.getRegDate() %></td>
			</tr>
			<tr class="tr-1">
				<th class="td-4" style="border-bottom: 1px solid #dad9d9;">분류</th>
				<td><%=mb.getCategory() %></td>
				<th class="td-4" style="border-bottom: 1px solid #dad9d9;">첨부파일</th>
				<td colspan="4">
					<%if(mb.getFilename() !=null) {%>
						<img src="/img/file.png" width="16px">
						<a href="/fileDown2.do?mbNo=<%=mb.getMbNo() %>"><%=mb.getFilename() %></a>
					<%} %>
				</td>
			</tr>
			<tr class="tr-1">
				<th class="td-4">내용</th>
				<td colspan="5">
					<div id="managerBoardContent"><%=mb.getMbContentBr() %></div>
				</td>
			</tr>
			<!--로그인 되어 있음 && member 로그인 아이디가 notice 작성자와 같을 떄!-->
			<%if(m!=null && m.getMemberId().equals(mb.getMbWriter())) {%>
			<tr class="tr-1">
				<th colspan="6">
					<!-- 수정은 페이지 이동, 삭제는 기능 동작 -->
					<a class="btn bc11" href="/managerBoardUpdateFrm.do?mbNo=<%=mb.getMbNo() %>">수정</a>
					<button class="btn bc11" onclick="managerBoardDelete('<%=mb.getMbNo() %>');">삭제</button>
				</th>
			</tr>
			<%} %>
		</table>
		
		<!-- 댓글 입력창 (대댓글 입력창은 아래쪽으로 위치 옮김------------------------------------------ -->
		<%if(m!=null) {%> <!-- 로그인 되어있을 때 댓글 작성할 수 있게 -->
		<div class="inputCommentBox">
			<form action="/insertComment2.do" method="post">
				<ul>
					<li>
						<span class="material-icons">account_box</span>
					</li>
					<li>
						<input type="hidden" name="ncWriter" value="<%=m.getMemberId()%>">
						<input type="hidden" name="noticeRef" value="<%=mb.getMbNo()%>">
						<input type="hidden" name="ncRef" value="0"> <!-- 아무것도 참조하지 않는다 0 -->
						<textarea class="input-form" name="ncContent"></textarea>
					</li>
					<li>
						<button type="submit" class="btn bc11 bs4">등록</button>
					</li>
				</ul>
			</form>
		</div>
		<%} %>
		
		<!-- 댓글 목록 출력  ------------------------------------->
		
		<div class="commentBox" style="border:1px solid #ccc; border-radius:10px;margin:40px auto;">
		<h2 style="padding:10px; color:#bfa2db">댓글</h2>
			<%for(NoticeComment nc : commentList) {%>
				<ul class="posting-comment">
					<li>
						<span class="material-icons">account_box</span>
					</li>
					<li>
						<p class="comment-info">
							<span><%=nc.getNcWriter() %></span>
							<span><%=nc.getNcDate() %></span>
						</p>
						<p class="comment-content"><%=nc.getNcContent() %></p>
						<textarea name="ncContent" class="input-form" style="display: none; min-height:90px;"><%=nc.getNcContent() %></textarea>
						<p class="comment-link">
							<%if(m != null) {%>
								<%if(m.getMemberId().equals(nc.getNcWriter())){ %>
									<a href="javascript:void(0)" onclick="modifyComment(this,'<%=nc.getNcNo()%>','<%=mb.getMbNo() %>')">수정</a>
									<a href="javascript:void(0)" onclick="deleteComment(this,'<%=nc.getNcNo()%>','<%=mb.getMbNo() %>')">삭제</a>
								<%} %>
								
							<%}//댓글 링크모음 로그인 체크 %>
						</p>
					</li>
				</ul>				
			<%}//댓글 출력용 for문 종료 %>
		</div>
		
			<!-- 공지사항 삭제 스크립트 -> 한번 물어보게 -->
		<script>
			function managerBoardDelete(mbNo){
				if(confirm("삭제하시겠습니까?")){
					location.href="/managerBoardDelete.do?mbNo="+mbNo;
				}
			}
			<!--댓글 답글달기 버튼 눌렀을 때-->
						
			<!-- 댓글,대댓글 수정-->
			function modifyComment(obj,ncNo,mbNo){
				$(obj).parent().prev().show();	//textarea를 화면에 보여줌
				$(obj).parent().prev().prev().hide();	//기존 댓글은 화면에서 숨김
				//수정 -> 수정완료
				$(obj).text("수정완료");
				$(obj).attr("onclick","modifyComplete(this,'"+ncNo+"','"+mbNo+"')");
				//삭제 -> 수정취소
				$(obj).next().text("수정취소");
				$(obj).next().attr("onclick","modifyCancel(this,'"+ncNo+"','"+mbNo+"')");
				//답글달기 버튼 숨김
				$(obj).next().next().hide();
			}
			function modifyCancel(obj,ncNo,mbNo){
				$(obj).parent().prev().hide();	//textarea 숨김
				$(obj).parent().prev().prev().show();	//기존댓글 보여줌
				//수정완료 -> 수정
				$(obj).prev().text("수정");
				$(obj).prev().attr("onclick", "modifyComment(this,'"+ncNo+"','"+mbNo+"')");
				//수정취소 -> 삭제
				$(obj).text("삭제");
				$(obj).attr("onclick", "deleteComment(this,'"+ncNo+"','"+mbNo+"')");
				//답글달기 버튼 보여줌
				$(obj).next().show();
			}
			function modifyComplete(obj,ncNo,mbNo){
				//form태그 생성해서 보내는 방법
				const form = $("<form action='/noticeCommentUpdate2.do' method='post'></form>");
				//form태그 자식으로 input 태그 추가(ncNo)
				form.append($("<input type='text' name='ncNo' value='"+ncNo+"'>"));	
				//form태그 자식으로 input 태그 추가(mbNo)
				form.append($("<input type='text' name='mbNo' value='"+mbNo+"'>"));
				//form태그 자식으로 수정한 댓글 내용이 들어있는 textarea를 추가
				form.append($(obj).parent().prev());
				//생성된 form태그를 html 본문으로 삽입
				$("body").append(form);
				//form태그 submit 여기로 '/noticeCommentUpdate2.do'
				form.submit();
			}
			<!--댓글,대댓글 삭제-->
			function deleteComment(obj,ncNo,mbNo){
				if(confirm("댓글을 삭제하시겠습니까?")){
					location.href="/deleteComment2.do?ncNo="+ncNo+"&mbNo="+mbNo;
				}
			}
			
		</script>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>