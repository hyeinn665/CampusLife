package kr.or.iei.managerboard.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.managerboard.model.dao.ManagerBoardDao;
import kr.or.iei.managerboard.model.vo.ManagerBoard;
import kr.or.iei.managerboard.model.vo.ManagerBoardPageData;
import kr.or.iei.managerboard.model.vo.ManagerBoardViewData;
import kr.or.iei.managerboard.model.vo.NoticeComment;

public class ManagerBoardService {
	
	
	//공지사항 업로드
	public int insertManagerBoard(ManagerBoard mb) {
		Connection conn = JDBCTemplate.getConnection();
		ManagerBoardDao dao  = new ManagerBoardDao();
		int result = dao.insertManagerBoard(conn,mb);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	//공지사항 한 개 상세보기 
	public ManagerBoard selectOneManagerBoardView(int mbNo) {
		Connection conn = JDBCTemplate.getConnection();
		ManagerBoardDao dao = new ManagerBoardDao();
		//<추가한 코드> 조회수도 올라갈 수 있도록!!
		int result = dao.updateReadCount(conn,mbNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
		ManagerBoard mb = dao.selectOneManagerBoard(conn,mbNo);
		JDBCTemplate.close(conn);
		return mb;
	}
	//글 하나 가져오기
	public ManagerBoard getManagerBoard(int mbNo) {
		Connection conn = JDBCTemplate.getConnection();
		ManagerBoardDao dao = new ManagerBoardDao();
		ManagerBoard mb = dao.selectOneManagerBoard(conn, mbNo);
		JDBCTemplate.close(conn);
		return mb;
	}

	//공지사항 삭제
	public int managerBoardDelete(int mbNo) {
		Connection conn = JDBCTemplate.getConnection();
		ManagerBoardDao dao = new ManagerBoardDao();
		int result = dao.managerBoardDelete(conn,mbNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	//공지사항 파일처림 포함 업데이트
	public int managerBoardUpdate(ManagerBoard mb) {
		Connection conn = JDBCTemplate.getConnection();
		ManagerBoardDao dao = new ManagerBoardDao();
		int result = dao.managerBoardUpdate(conn,mb);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
///////////////////////////////////////////
	//댓글 등록
	public int insertNoticeComment(NoticeComment nc) {
		Connection conn = JDBCTemplate.getConnection();
		ManagerBoardDao dao = new ManagerBoardDao();
		int result = dao.insertNoticeComment(conn,nc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	//공지사항 한 개 상세보기 + 댓글창 추가 메소드
	public ManagerBoardViewData selectManagerBoardView(int mbNo) {
		Connection conn = JDBCTemplate.getConnection();
		ManagerBoardDao dao = new ManagerBoardDao();
		//<추가한 코드> 조회수도 올라갈 수 있도록!!
		int result = dao.updateReadCount(conn,mbNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
		//공지사항 정보 : Notice 먼저 조회
		ManagerBoard mb = dao.selectOneManagerBoard(conn, mbNo);
		//공지사항에 달려있는 일반댓글 조회 : ArrayList 댓글 조회
		ArrayList<NoticeComment> commentList = dao.selectNoticeComment(conn,mbNo);
		//공지사항에 달려있는 댓글의 댓글 조회(대댓글들)
		ArrayList<NoticeComment> reCommentList = dao.selectNoticeReComment(conn, mbNo);
		
		JDBCTemplate.close(conn);
		ManagerBoardViewData mbvd = new ManagerBoardViewData(mb, commentList, reCommentList);
		return mbvd;
	}
	//댓글 업데이트
	public int updateManagerBoardComment(NoticeComment nc) {
		Connection conn = JDBCTemplate.getConnection();
		ManagerBoardDao dao = new ManagerBoardDao();
		int result = dao.updateManagerBoardComment(conn,nc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//댓글 삭제
	public int deleteComment(int ncNo) {
		Connection conn = JDBCTemplate.getConnection();
		ManagerBoardDao dao = new ManagerBoardDao();
		int result = dao.deleteComment(conn,ncNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
		
	//공지사항 리스트 + 페이징 조회 + 카테고리 게시판
	public ManagerBoardPageData selectManagerBoardList(int reqPage, String category) {
		Connection conn = JDBCTemplate.getConnection();
		ManagerBoardDao dao = new ManagerBoardDao();
		/* 페이징처리*/
		//1.결정사항 : 한 페이지당 게시물 수
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage+1;
		
		ArrayList<ManagerBoard> list = dao.selectManagerBoardList(conn,start,end,category);

		//페이징처리
		//전체 페이지 계산을 위한 전체 게시물 수 조회
		int totalCount = dao.totalManagerBoardCount(conn, category);
		//전체 페이지 수
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		//페이지 네비게이션의 길이 지정
		int pageNaviSize = 5;
		//페이지 모양 지정
		//1~5페이지 요청시		-> 1 2 3 4 5
		//6~10페이지 요청시	-> 6 7 8 9 10
		
		//페이지 네비게이션 시작번호 계산
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		//페이지 네비게이션 제작 시작
		String pageNavi = "<ul class='pagination circle-style'>";
		//이전버튼
		if(pageNo != 1) {	//1페이지부터 뜨면 이전버튼 필요없으니까
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/managerBoardList.do?reqPage="+(pageNo-1)+"&category="+category+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		//페이지숫자
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/managerBoardList.do?reqPage="+pageNo+"&category="+category+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/managerBoardList.do?reqPage="+pageNo+"&category="+category+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) { //최대페이지
				break;
			}
		}
		//다음버튼
		if(pageNo<=totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/managerBoardList.do?reqPage="+pageNo+"&category="+category+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		
		ManagerBoardPageData mbpd = new ManagerBoardPageData(list,pageNavi);
		JDBCTemplate.close(conn);
		
		return mbpd;
	}


	//검색 기능
	public ManagerBoardPageData selectManagerBoardList(int reqPage, String searchType, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		ManagerBoardDao dao = new ManagerBoardDao();
		/* 페이징처리*/
		//1.결정사항 : 한 페이지당 게시물 수
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage+1;
		
		ArrayList<ManagerBoard> searchlist = dao.selectManagerBoardList(conn,start,end,searchType,keyword);

		//페이징처리
		//전체 페이지 계산을 위한 전체 게시물 수 조회
		int totalCount = dao.totalManagerBoardCount(conn, searchType,keyword);
		//전체 페이지 수
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		//페이지 네비게이션의 길이 지정
		int pageNaviSize = 5;
		//페이지 모양 지정
		//1~5페이지 요청시		-> 1 2 3 4 5
		//6~10페이지 요청시	-> 6 7 8 9 10
		
		//페이지 네비게이션 시작번호 계산
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		//페이지 네비게이션 제작 시작
		String pageNavi = "<ul class='pagination circle-style'>";
		//이전버튼
		if(pageNo != 1) {	//1페이지부터 뜨면 이전버튼 필요없으니까
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/managerBoardList.do?reqPage="+(pageNo-1)+"&searchType="+searchType+"&keyword="+keyword+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		//페이지숫자
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/managerBoardList.do?reqPage="+pageNo+"&searchType="+searchType+"&keyword="+keyword+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/managerBoardList.do?reqPage="+pageNo+"&searchType="+searchType+"&keyword="+keyword+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) { //최대페이지
				break;
			}
		}
		//다음버튼
		if(pageNo<=totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/managerBoardList.do?reqPage="+pageNo+"&searchType="+searchType+"&keyword="+keyword+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		
		ManagerBoardPageData mbpd = new ManagerBoardPageData(searchlist,pageNavi);
		JDBCTemplate.close(conn);
		
		return mbpd;
	}



	
	
}
