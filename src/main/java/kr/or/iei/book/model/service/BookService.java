package kr.or.iei.book.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import common.JDBCTemplate;
import kr.or.iei.book.model.vo.BookPageData;
import kr.or.iei.book.model.vo.BookSearchData;
import kr.or.iei.book.model.vo.BookViewData;
import kr.or.iei.book.model.dao.BookDao;
import kr.or.iei.book.model.vo.Book;
import kr.or.iei.book.model.vo.BookComment;

public class BookService {

	public BookPageData selectBookList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		//1. 결정사항 : 한 페이지당 게시물 수
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		ArrayList<Book> list = dao.selectBookList(conn,start,end);
		int totalCount = dao.totalBookCount(conn);
		int totalPage = 0;
		if(totalCount%numPerPage==0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		//페이지 네비게이션의 길이 지정
		int pageNaviSize = 5;
		//페이지 모양 지정
		// 1~5페이지 요청시  -> 1 2 3 4 5
		// 6~10페이지 요청시 -> 6 7 8 9 10
		
		//페이지 네비게이션 시작번호 계산
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		//페이지 네비게이션 제작 시작
		String pageNavi = "<ul class='pagination circle-style'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/bookList.do?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		//페이지숫자
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/bookList.do?reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/bookList.do?reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/bookList.do?reqPage="+pageNo+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		HashMap<Integer, String> map = dao.selectSubject(conn);
		BookPageData bpd = new BookPageData(list, pageNavi,map,totalCount,reqPage);
		
		JDBCTemplate.close(conn);
		return bpd;
	}

	public Book selectOneBook(int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		Book b = dao.selectOneBook(conn, bookNo);
		JDBCTemplate.close(conn);
		return b;
	}

	public HashMap<Integer, String> selectSubject() {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		HashMap<Integer, String> map = dao.selectSubject(conn);
		JDBCTemplate.close(conn);
		return map;
	}

	public int insertBook(Book b) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		int result = dao.insertBook(conn,b);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Book getBook(int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		Book b = dao.selectOneBook(conn,bookNo);
		JDBCTemplate.close(conn);
		return b;
	}

	public int deleteBook(int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		int result = dao.deleteBook(conn, bookNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateBook(Book b) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		int result = dao.updateBook(conn,b);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public BookSearchData searchBook(String search, int searchSelect) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		ArrayList<Book> list = null;
		if(searchSelect==1) {
			list = dao.searchBookName(conn,search);
		}else if(searchSelect==2) {
			list = dao.searchBookWriter(conn,search);
		}else if(searchSelect==3){
			list = dao.searchBookSubject(conn,search);
		}
		BookSearchData bsd = new BookSearchData(list, search);
		JDBCTemplate.close(conn);
		return bsd;
	}

	public int insertBookComment(BookComment bc) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		int result = dao.insertBookComment(conn, bc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public BookViewData selectBookView(int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		HashMap<Integer, String> map = dao.selectSubject(conn);
		Book b = dao.selectOneBook(conn, bookNo);
		ArrayList<BookComment> commentList = dao.selectBookComment(conn,bookNo);
		ArrayList<BookComment> reCommentList = dao.selectBookReComment(conn, bookNo);
		JDBCTemplate.close(conn);
		BookViewData bvd = new BookViewData(map, b, commentList, reCommentList);
		return bvd;
	}

	public int bookSellUpdate(int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		Book b = dao.selectOneBook(conn, bookNo);
		int result = dao.sellUpdate(conn,b);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteComment(int bcNo) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		int result = dao.deleteComment(conn,bcNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateBookComment(BookComment bc) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		int result = dao.updateBookComment(conn,bc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Book> searchBookName(String search) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		ArrayList<Book> list = dao.searchBookName(conn, search);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Book> searchBookWriter(String search) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		ArrayList<Book> list = dao.searchBookWriter(conn, search);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Book> searchBookSubject(String search) {
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		ArrayList<Book> list = dao.searchBookSubject(conn, search);
		JDBCTemplate.close(conn);
		return list;
	}
	public ArrayList<Book> selectBookList(){
		Connection conn = JDBCTemplate.getConnection();
		BookDao dao = new BookDao();
		ArrayList<Book> list = dao.selectBookList(conn); 
		JDBCTemplate.close(conn);
		return list;
	}
}
