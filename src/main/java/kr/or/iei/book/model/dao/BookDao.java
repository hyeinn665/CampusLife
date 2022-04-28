package kr.or.iei.book.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.JDBCTemplate;
import kr.or.iei.book.model.vo.Book;
import kr.or.iei.book.model.vo.BookComment;

public class BookDao {

	public ArrayList<Book> selectBookList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> list = new ArrayList<Book>();
		String query = "select * from (select rownum as rnum,n.* from (select * from book order by book_no DESC)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Book b = new Book();
				b.setBookNo(rset.getInt("book_no"));
				b.setBookName(rset.getString("book_name"));
				b.setBookWriter(rset.getString("book_writer"));
				b.setPublisher(rset.getString("publisher"));
				b.setSubjectNo(rset.getInt("subject_no"));
				b.setListPrice(rset.getInt("list_price"));
				b.setHopePrice(rset.getInt("hope_price"));
				b.setWrittenTrace(rset.getString("written_trace"));
				b.setTornTrace(rset.getString("torn_trace"));
				b.setDiscolor(rset.getString("discolor"));
				b.setNameTrace(rset.getString("name_trace"));
				b.setSellEnd(rset.getString("sell_end"));
				b.setFilepath(rset.getString("filepath"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int totalBookCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from book";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Book selectOneBook(Connection conn, int bookNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Book b = null;
		String query = "select * from book where book_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				b = new Book();
				b.setBookNo(rset.getInt("book_no"));
				b.setSubjectNo(rset.getInt("subject_no"));
				b.setMemberNo(rset.getInt("member_no"));
				b.setBookName(rset.getString("book_name"));
				b.setBookWriter(rset.getString("book_writer"));
				b.setPublisher(rset.getString("publisher"));
				b.setListPrice(rset.getInt("list_price"));
				b.setHopePrice(rset.getInt("hope_price"));
				b.setWrittenTrace(rset.getString("written_trace"));
				b.setTornTrace(rset.getString("torn_trace"));
				b.setDiscolor(rset.getString("discolor"));
				b.setNameTrace(rset.getString("name_trace"));
				b.setSellEnd(rset.getString("sell_end"));
				b.setFilepath(rset.getString("filepath"));
				b.setBookComment(rset.getString("book_comment"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return b;
	}

	public HashMap<Integer, String> selectSubject(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		String query = "select subject_no, subject_name from subject";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				map.put(rset.getInt("subject_no"), rset.getString("subject_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return map;
	}

	public int insertBook(Connection conn, Book b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into book values(book_seq.nextval,?,?,?,?,?,?,?,?,?,?,NULL,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getMemberNo());
			pstmt.setString(2, b.getBookName());
			pstmt.setString(3, b.getBookWriter());
			pstmt.setString(4, b.getPublisher());
			pstmt.setInt(5, b.getListPrice());
			pstmt.setInt(6, b.getHopePrice());
			pstmt.setString(7, b.getWrittenTrace());
			pstmt.setString(8, b.getTornTrace());
			pstmt.setString(9, b.getDiscolor());
			pstmt.setString(10, b.getNameTrace());
			pstmt.setString(11, b.getFilepath());
			pstmt.setString(12, b.getBookComment());
			pstmt.setInt(13, b.getSubjectNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteBook(Connection conn, int bookNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from book where book_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateBook(Connection conn, Book b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update book set subject_no=?, book_name=?,book_writer=?,publisher=?,list_price=?,hope_price=?,written_trace=?,torn_trace=?,discolor=?,name_trace=?,filepath=?,book_comment=? where book_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getSubjectNo());
			pstmt.setString(2, b.getBookName());
			pstmt.setString(3, b.getBookWriter());
			pstmt.setString(4, b.getPublisher());
			pstmt.setInt(5, b.getListPrice());
			pstmt.setInt(6, b.getHopePrice());
			pstmt.setString(7, b.getWrittenTrace());
			pstmt.setString(8, b.getTornTrace());
			pstmt.setString(9, b.getDiscolor());
			pstmt.setString(10, b.getNameTrace());
			pstmt.setString(11, b.getFilepath());
			pstmt.setString(12, b.getBookComment());
			pstmt.setInt(13, b.getBookNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Book> searchBookName(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> list = new ArrayList<Book>();;
		String query = "select * from book where BOOK_NAME like '%'||?||'%'";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Book b = new Book();
				b.setBookNo(rset.getInt("book_no"));
				b.setSubjectNo(rset.getInt("subject_no"));
				b.setMemberNo(rset.getInt("member_no"));
				b.setBookName(rset.getString("book_name"));
				b.setBookWriter(rset.getString("book_writer"));
				b.setPublisher(rset.getString("publisher"));
				b.setListPrice(rset.getInt("list_price"));
				b.setHopePrice(rset.getInt("hope_price"));
				b.setWrittenTrace(rset.getString("written_trace"));
				b.setTornTrace(rset.getString("torn_trace"));
				b.setDiscolor(rset.getString("discolor"));
				b.setNameTrace(rset.getString("name_trace"));
				b.setSellEnd(rset.getString("sell_end"));
				b.setFilepath(rset.getString("filepath"));
				b.setBookComment(rset.getString("book_comment"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int insertBookComment(Connection conn, BookComment bc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into book_comment values(bc_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bc.getBcWriter());
			pstmt.setString(2, bc.getBcContent());
			pstmt.setInt(3, bc.getBookRef());
			pstmt.setString(4, (bc.getBcRef()==0)?null:String.valueOf(bc.getBcRef()));
			pstmt.setString(5, bc.getBcSecret());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<BookComment> selectBookComment(Connection conn, int bookNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookComment> list = new ArrayList<BookComment>();
		String query = "select * from book_comment where book_ref=? and bc_ref is null";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BookComment bc = new BookComment();
				bc.setBcNo(rset.getInt("bc_no"));
				bc.setBcWriter(rset.getString("bc_writer"));
				bc.setBcContent(rset.getString("bc_content"));
				bc.setBcDate(rset.getString("bc_date"));
				bc.setBookRef(rset.getInt("book_ref"));
				bc.setBcRef(rset.getInt("bc_ref"));
				bc.setBcSecret(rset.getString("bc_secret"));
				list.add(bc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public ArrayList<BookComment> selectBookReComment(Connection conn, int bookNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookComment> list = new ArrayList<BookComment>();
		String query = "select * from Book_comment where book_ref=? and bc_ref is not null";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BookComment bc = new BookComment();
				bc.setBcNo(rset.getInt("bc_no"));
				bc.setBcWriter(rset.getString("bc_writer"));
				bc.setBcContent(rset.getString("bc_content"));
				bc.setBcDate(rset.getString("bc_date"));
				bc.setBookRef(rset.getInt("book_ref"));
				bc.setBcRef(rset.getInt("bc_ref"));
				bc.setBcSecret(rset.getString("bc_secret"));
				list.add(bc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public int sellUpdate(Connection conn, Book b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update book set sell_end='o' where book_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getBookNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteComment(Connection conn, int bcNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from book_comment where bc_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bcNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateBookComment(Connection conn, BookComment bc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update book_comment set bc_content=? where bc_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bc.getBcContent());
			pstmt.setInt(2, bc.getBcNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Book> searchBookWriter(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> list = new ArrayList<Book>();;
		String query = "select * from book where BOOK_writer like '%'||?||'%'";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Book b = new Book();
				b.setBookNo(rset.getInt("book_no"));
				b.setSubjectNo(rset.getInt("subject_no"));
				b.setMemberNo(rset.getInt("member_no"));
				b.setBookName(rset.getString("book_name"));
				b.setBookWriter(rset.getString("book_writer"));
				b.setPublisher(rset.getString("publisher"));
				b.setListPrice(rset.getInt("list_price"));
				b.setHopePrice(rset.getInt("hope_price"));
				b.setWrittenTrace(rset.getString("written_trace"));
				b.setTornTrace(rset.getString("torn_trace"));
				b.setDiscolor(rset.getString("discolor"));
				b.setNameTrace(rset.getString("name_trace"));
				b.setSellEnd(rset.getString("sell_end"));
				b.setFilepath(rset.getString("filepath"));
				b.setBookComment(rset.getString("book_comment"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Book> searchBookSubject(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> list = new ArrayList<Book>();;
		String query = "select * from book where subject_no in(select subject_no from subject where subject_name like '%'||?||'%')";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Book b = new Book();
				b.setBookNo(rset.getInt("book_no"));
				b.setSubjectNo(rset.getInt("subject_no"));
				b.setMemberNo(rset.getInt("member_no"));
				b.setBookName(rset.getString("book_name"));
				b.setBookWriter(rset.getString("book_writer"));
				b.setPublisher(rset.getString("publisher"));
				b.setListPrice(rset.getInt("list_price"));
				b.setHopePrice(rset.getInt("hope_price"));
				b.setWrittenTrace(rset.getString("written_trace"));
				b.setTornTrace(rset.getString("torn_trace"));
				b.setDiscolor(rset.getString("discolor"));
				b.setNameTrace(rset.getString("name_trace"));
				b.setSellEnd(rset.getString("sell_end"));
				b.setFilepath(rset.getString("filepath"));
				b.setBookComment(rset.getString("book_comment"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	public ArrayList<Book> selectBookList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> list = new ArrayList<Book>();
		String query = "select * from (select rownum as rnum,n.* from (select * from book order by book_no DESC)n) where rnum < 4";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Book b = new Book();
				b.setBookNo(rset.getInt("book_no"));
				b.setBookName(rset.getString("book_name"));
				b.setFilepath(rset.getString("filepath"));
				b.setHopePrice(rset.getInt("hope_price"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
}
