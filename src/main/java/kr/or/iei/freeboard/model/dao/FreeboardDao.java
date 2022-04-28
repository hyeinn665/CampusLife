package kr.or.iei.freeboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.freeboard.model.vo.Freeboard;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.review.model.vo.Review;

public class FreeboardDao {

	/*
	public ArrayList<Freeboard> selectFreeboardList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Freeboard> list = new ArrayList<Freeboard>();
		String query = "select * from freeboard";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Freeboard f = new Freeboard();
				f.setFreeNo(rset.getInt("free_no"));
				f.setMemberNo(rset.getInt("member_no"));
				f.setFreeTitle(rset.getString("free_title"));
				f.setFreeContent(rset.getString("free_content"));
				f.setReadCount(rset.getInt("read_count"));
				f.setGoodCount(rset.getInt("good_count"));
				f.setFreeDate(rset.getString("free_date"));
				f.setFileName(rset.getString("file_name"));
				f.setFilePath(rset.getString("file_path"));
				list.add(f);
				
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
	*/

	public ArrayList<Freeboard> selectFreeboardList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
        ResultSet rset = null;
        ArrayList<Freeboard> list = new ArrayList();
        String query = "select * from (select rownum as rnum,f.* from (select * from freeboard order by free_no desc)f) where rnum between ? and ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);
            rset = pstmt.executeQuery();

            while(rset.next()) {
            	Freeboard f = new Freeboard();
				f.setFreeNo(rset.getInt("free_no"));
				f.setFreeTitle(rset.getString("free_title"));
				f.setFreeContent(rset.getString("free_content"));
				f.setReadCount(rset.getInt("read_count"));
				f.setGoodCount(rset.getInt("good_count"));
				f.setFreeDate(rset.getString("free_date"));
				f.setFileName(rset.getString("filename"));
				f.setFilePath(rset.getString("filepath"));
				
				list.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }

        return list;
    }

	public int totalFreeboardCount(Connection conn) {
		 PreparedStatement pstmt = null;
	        ResultSet rset = null;
	        int result = 0;
	        String query = "select count(*) as cnt from freeboard";

	        try {
	            pstmt = conn.prepareStatement(query);
	            rset = pstmt.executeQuery();
	            if (rset.next()) {
	                result = rset.getInt("cnt");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            JDBCTemplate.close(rset);
	            JDBCTemplate.close(pstmt);
	        }

	        return result;
	    }

	public int insertFreeboard(Connection conn, Freeboard f) {
		 PreparedStatement pstmt = null;
	        int result = 0;
	        String query = "insert into Freeboard values(FREEBOARD_SEQ.NEXTVAL,?,?,?,0,0,TO_CHAR(SYSDATE,'YYYY-MM-DD'),?,?)";
	        try {
	            pstmt = conn.prepareStatement(query);
	            pstmt.setInt(1, f.getMemberNo());
	            pstmt.setString(2, f.getFreeTitle());
	            pstmt.setString(3, f.getFreeContent());
	            pstmt.setString(4, f.getFileName());
	            pstmt.setString(5, f.getFilePath());
	            result = pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            JDBCTemplate.close(pstmt);
	        }

	        return result;
	    }

	public int updateReadCount(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update freeboard set read_count = read_count+1 where free_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Freeboard selectOneFreeboard(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Freeboard f = null;
		String query = "select * from Freeboard where free_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				f = new Freeboard();
				f.setFreeNo(rset.getInt("free_no"));
				f.setFreeTitle(rset.getString("free_title"));
				f.setFreeContent(rset.getString("free_content"));
				f.setReadCount(rset.getInt("read_count"));
				f.setGoodCount(rset.getInt("good_count"));
				f.setFreeDate(rset.getString("free_date"));
				f.setFileName(rset.getString("filename"));
				f.setFilePath(rset.getString("filepath"));
				f.setMemberNo(rset.getInt("member_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return f;
	}

	public int freeboardDelete(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from freeboard where free_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int freeboardUpdate(Connection conn, Freeboard f) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update freeboard set free_title=?, free_content=?, filename=?, filepath=? where free_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, f.getFreeTitle());
			pstmt.setString(2, f.getFreeContent());
			pstmt.setString(3, f.getFileName());
			pstmt.setString(4, f.getFilePath());
			pstmt.setInt(5, f.getFreeNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	public ArrayList<Freeboard> mainListFree(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Freeboard> list = new ArrayList<Freeboard>();
		String query = "select * from (select rownum as rnum,n.* from (select * from freeboard order by free_no DESC)n) where rnum < 6";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Freeboard f = new Freeboard();
				f.setFreeNo(rset.getInt("free_no"));
				f.setFreeTitle(rset.getString("free_title"));
				list.add(f);
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
}
