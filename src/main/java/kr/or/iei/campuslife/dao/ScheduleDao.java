package kr.or.iei.campuslife.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import common.JDBCTemplate;
import kr.or.iei.campuslife.vo.Schedule;
import kr.or.iei.campuslife.vo.Subject;

public class ScheduleDao {
	
	public ArrayList<Schedule> getScheduleList(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Schedule> sl = new ArrayList<Schedule>();
		
		String query = "select * from schedule where stu_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Schedule sd = new Schedule();
				sd.setScheduleNo(rset.getInt("schedule_no"));
				sd.setProfNo(rset.getInt("prof_no"));
				sd.setSubjectNo(rset.getInt("subject_no"));
				sd.setScheduleA(rset.getString("schedule_A"));
				sd.setScheduleB(rset.getInt("schedule_B"));
				sd.setStuNo(rset.getInt("stu_no"));
				sl.add(sd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sl;
	}

	public int getScheduleListSize(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int scheduleListSize = 0;
		
		String query = "select count(DISTINCT subject_no) as sdsize FROM schedule where stu_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				scheduleListSize = rset.getInt("sdsize");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return scheduleListSize;
	}

	public int deleteMySubject(Connection conn, int userNo, int subjectNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from schedule where subject_no = ? and stu_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, subjectNo);
			pstmt.setInt(2, userNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertMySchedule(Connection conn, Schedule sd, int durationTime) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into schedule values(schedule_seq.nextval, ?, ?, ?, ?, ?)";
		
		for(int i=0;i<durationTime;i++) {
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, sd.getSubjectNo());
				pstmt.setInt(2, sd.getProfNo());
				pstmt.setString(3, sd.getScheduleA());
				pstmt.setInt(4, sd.getScheduleB()+i);
				pstmt.setInt(5, sd.getStuNo());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
		}
		if(result>0) {
			return result;
		}else {
			return -1;
		}
	}

	public int getScout(Connection conn, int userNo, String scheduleA, int scheduleB) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int scout = 0;
		
		String query = "select * from schedule where stu_no=? and schedule_a=? and schedule_b=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			pstmt.setString(2, scheduleA);
			pstmt.setInt(3, scheduleB);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				scout = rset.getInt("subject_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		if(scout>0) {
			return scout;
		}else {
			return -1;
		}
	}

	public Set<Integer> getMySubjectNo(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Set<Integer> mySjL = new HashSet<Integer>();
		
		String query = "select subject_no from schedule where stu_no= ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				mySjL.add(rset.getInt("subject_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return mySjL;
	}

	public int deleteMySubject2(Connection conn, int userNo, int subjectNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from sub_mem where subject_no = ? and member_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, subjectNo);
			pstmt.setInt(2, userNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
}
