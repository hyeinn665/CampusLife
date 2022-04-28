package kr.or.iei.managerboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.managerboard.model.vo.ManagerBoard;
import kr.or.iei.managerboard.model.vo.NoticeComment;



public class ManagerBoardDao {
	//공지사항 리스트 + 페이징 조회 + 카테고리 게시판
	public ArrayList<ManagerBoard> selectManagerBoardList(Connection conn, int start, int end, String category) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ManagerBoard> list = new ArrayList<ManagerBoard>();
		String query = "select * from (select rownum as rnum,n.* from (select * from managerboard";
		if(!category.equals("전체")) {
			query += " where category=? ";
		}
		query += " order by mb_no desc)n) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			int i = 1;
			if(!category.equals("전체")) {
				pstmt.setString(i++, category);
			}
			pstmt.setInt(i++, start);
			pstmt.setInt(i++, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ManagerBoard mb = new ManagerBoard();
				mb.setMbNo(rset.getInt("mb_no"));
				mb.setMbTitle(rset.getString("mb_title"));
				mb.setMbWriter(rset.getString("mb_writer"));
				mb.setMbContent(rset.getString("mb_content"));;
				mb.setReadCount(rset.getInt("read_count"));
				mb.setRegDate(rset.getString("reg_date"));
				mb.setFilename(rset.getString("filename"));
				mb.setFilepath(rset.getString("filepath"));
				mb.setCategory(rset.getString("category"));
				list.add(mb);
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

	
	//공지사항 전체 페이지 계산을 위한 전체 게시물 수 조회
	public int totalManagerBoardCount(Connection conn,String category) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from managerboard";
		if(!category.equals("전체")) {
			query += " where category=?";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			if(!category.equals("전체")) {
				pstmt.setString(1, category);
			}
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
	//공지사항 업로드
	public int insertManagerBoard(Connection conn, ManagerBoard mb) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into managerboard values(managerboard_seq.nextval,?,?,?,0,to_char(sysdate, 'yyyy-mm-dd'),?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mb.getMbTitle());
			pstmt.setString(2, mb.getMbWriter());
			pstmt.setString(3, mb.getMbContent());
			pstmt.setString(4, mb.getFilename());
			pstmt.setString(5, mb.getFilepath());
			pstmt.setString(6, mb.getCategory());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//공지사항 상세보기 - 조회수 업데이트
	public int updateReadCount(Connection conn, int mbNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update managerboard set read_count = read_count+1 where mb_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mbNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//공지사항 게시글 한 개 조회
	public ManagerBoard selectOneManagerBoard(Connection conn, int mbNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ManagerBoard mb = null;
		String query = "select * from managerboard where mb_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mbNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				mb = new ManagerBoard();
				mb.setMbNo(rset.getInt("mb_no"));
				mb.setMbTitle(rset.getString("mb_title"));
				mb.setMbWriter(rset.getString("mb_writer"));
				mb.setMbContent(rset.getString("mb_content"));
				mb.setReadCount(rset.getInt("read_count"));
				mb.setRegDate(rset.getString("reg_date"));
				mb.setFilename(rset.getString("filename"));
				mb.setFilepath(rset.getString("filepath"));
				mb.setCategory(rset.getString("category"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mb;
	}
	
	//공지사항 글삭제
	public int managerBoardDelete(Connection conn, int mbNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from managerboard where mb_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, mbNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//공지사항 파일처리 포함 업데이트
	public int managerBoardUpdate(Connection conn, ManagerBoard mb) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update managerboard set mb_title=?,mb_content=?,filename=?,filepath=?,category=? where mb_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mb.getMbTitle());
			pstmt.setString(2, mb.getMbContent());
			pstmt.setString(3, mb.getFilename());
			pstmt.setString(4, mb.getFilepath());
			pstmt.setString(5,mb.getCategory());
			pstmt.setInt(6, mb.getMbNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	

	
//댓글 대댓글--------------------------------------------------------------
	
	//글에 댓글 등록
	public int insertNoticeComment(Connection conn, NoticeComment nc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into notice_comment values(nc_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nc.getNcWriter());
			pstmt.setString(2, nc.getNcContent());
			pstmt.setInt(3, nc.getNoticeRef());
			/*nc.getNcRef()가 0이면 대댓글이 아니라 댓글이니까 참조에 들어갈 수 있는 null로 바꾸고
			 * (오라클은 지금 number지만, 문자열로 식 넣어도 자동변환 유연한 특성 이용) 그게 아니면 문자열로 바꿔서 넣음
			
			if(nc.getNcRef()==0) {
				pstmt.setString(4, null);
			}else {
				pstmt.setInt(4, nc.getNcRef());
			}*/
			pstmt.setString(4, (nc.getNcRef()==0)?null:String.valueOf(nc.getNcRef()));
			result = pstmt.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//글에 댓글창 추가
	public ArrayList<NoticeComment> selectNoticeComment(Connection conn, int mbNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		//null이면 대댓글 아직 없는 일반 댓글이니까
		String query = "select * from notice_comment where notice_ref=? and nc_ref is null";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mbNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				NoticeComment nc = new NoticeComment();
				nc.setNcNo(rset.getInt("nc_no"));
				nc.setNcWriter(rset.getString("nc_writer"));
				nc.setNcContent(rset.getString("nc_content"));
				nc.setNcDate(rset.getString("nc_date"));
				nc.setNoticeRef(rset.getInt("notice_ref"));
				nc.setNcRef(rset.getInt("nc_ref"));
				list.add(nc);
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
	
	//글에 대댓글창 추가
	public ArrayList<NoticeComment> selectNoticeReComment(Connection conn, int mbNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		//null이면 대댓글 아직 없는 일반 댓글이니까
		String query = "select * from notice_comment where notice_ref=? and nc_ref is not null";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mbNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				NoticeComment nc = new NoticeComment();
				nc.setNcNo(rset.getInt("nc_no"));
				nc.setNcWriter(rset.getString("nc_writer"));
				nc.setNcContent(rset.getString("nc_content"));
				nc.setNcDate(rset.getString("nc_date"));
				nc.setNoticeRef(rset.getInt("notice_ref"));
				nc.setNcRef(rset.getInt("nc_ref"));
				list.add(nc);
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
	//댓글 업데이트
	public int updateManagerBoardComment(Connection conn, NoticeComment nc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update notice_comment set nc_content=? where nc_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nc.getNcContent());
			pstmt.setInt(2, nc.getNcNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//댓글 삭제
	public int deleteComment(Connection conn, int ncNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from notice_comment where nc_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ncNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//검색기능 리스트 
	public ArrayList<ManagerBoard> selectManagerBoardList(Connection conn, int start, int end, String searchType, String keyword){
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<ManagerBoard> searchlist = new ArrayList<ManagerBoard>();
			String query = "select * from (select rownum as rnum,n.* from (select * from managerboard";
			if(searchType.equals("title")) {
				query += " where mb_title like ?";
			}else if(searchType.equals("content")) {
				query += " where mb_content like ?";
			}
			query += " order by mb_no desc)n) where rnum between ? and ?";
			try {
				pstmt = conn.prepareStatement(query);
				int i = 1;
				if(searchType.equals("title")) {
					pstmt.setString(i++, "%" + keyword + "%");
				}else if(searchType.equals("content")) {
					pstmt.setString(i++, "%" + keyword + "%");
				}
				pstmt.setInt(i++, start);
				pstmt.setInt(i++, end);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					ManagerBoard mb = new ManagerBoard();
					mb.setMbNo(rset.getInt("mb_no"));
					mb.setMbTitle(rset.getString("mb_title"));
					mb.setMbWriter(rset.getString("mb_writer"));
					mb.setMbContent(rset.getString("mb_content"));;
					mb.setReadCount(rset.getInt("read_count"));
					mb.setRegDate(rset.getString("reg_date"));
					mb.setFilename(rset.getString("filename"));
					mb.setFilepath(rset.getString("filepath"));
					mb.setCategory(rset.getString("category"));
					searchlist.add(mb);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return searchlist;
	}

	//검색해서 나오는 게시글 수
	public int totalManagerBoardCount(Connection conn, String searchType, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from managerboard";

		if(searchType.equals("title")) {
			query += " where mb_title like ?";
		}else if(searchType.equals("content")) {
			query += " where mb_content like ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			int i = 1;
			if(searchType.equals("title")) {
				pstmt.setString(i++, "%" + keyword + "%");
			}else if(searchType.equals("content")) {
				pstmt.setString(i++, "%" + keyword + "%");
			}
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
}
