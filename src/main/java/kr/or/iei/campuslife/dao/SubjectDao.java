package kr.or.iei.campuslife.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import common.JDBCTemplate;
import kr.or.iei.campuslife.vo.Schedule;
import kr.or.iei.campuslife.vo.Subject;

public class SubjectDao {

	public int insertSubject(Connection conn, Subject s) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into subject values(subject_seq.nextval,?,?,?,?,?,0,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,s.getSubjectName());
			pstmt.setString(2,s.getStartTime());
			pstmt.setInt(3,s.getDurationTime());
			pstmt.setInt(4,s.getGrade());
			pstmt.setInt(5, s.getProfNo());
			pstmt.setInt(6, s.getSelectSubject());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Subject> getSubjectList(Connection conn){
		ArrayList<Subject> list = new ArrayList<Subject>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Subject s = null;
		
		String query = "select * from subject";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				s = new Subject();
				s.setSubjectNo(rset.getInt("subject_no"));
				s.setSubjectName(rset.getString("subject_name"));
				s.setStartTime(rset.getString("start_time"));
				s.setDurationTime(rset.getInt("duration_time"));
				s.setGrade(rset.getInt("grade"));
				s.setProfNo(rset.getInt("member_no"));
				list.add(s);
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

	public int deleteSubject(Connection conn, Subject s) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from subject where subject_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,s.getSubjectNo());
			
			result  = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Subject> mySubjectList(Connection conn, Set<Integer> setSchedule) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Subject> mySubjectList = new ArrayList<Subject>();
		
		String query = "select * from subject where subject_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			Iterator<Integer> it = setSchedule.iterator();
			while(it.hasNext()) {
				pstmt.setInt(1, it.next());
				rset = pstmt.executeQuery();
				if(rset.next()) {
					Subject sj = new Subject();
					sj.setSubjectNo(rset.getInt("subject_no"));
					sj.setSubjectName(rset.getString("subject_name"));
					sj.setStartTime(rset.getString("start_time"));
					sj.setDurationTime(rset.getInt("duration_time"));
					sj.setGrade(rset.getInt("grade"));
					sj.setProfNo(rset.getInt("member_no"));
					sj.setSelectSubject(rset.getInt("select_subject"));
					mySubjectList.add(sj);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return mySubjectList;
	}

	public int deleteLecture(Connection conn, int subjectNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from subject where subject_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,subjectNo);
			
			result  = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int getSubjectNo(Connection conn, String subjectName, String startTime) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int subjectNo = 0;
		
		String query = "select subject_no from subject where subject_name=? and start_time=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subjectName);
			pstmt.setString(2, startTime);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				subjectNo = rset.getInt("subject_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return subjectNo;
	}

	public Map<Integer, String> getProfName(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Map<Integer, String> mapName = new HashMap<Integer, String>();
		
		String query = "select member_no, member_name from member";

			try {
				pstmt = conn.prepareStatement(query);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					mapName.put((rset.getInt("member_no")),rset.getString("member_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
				JDBCTemplate.close(rset);
			}

		return mapName;
	}
	
	
}
