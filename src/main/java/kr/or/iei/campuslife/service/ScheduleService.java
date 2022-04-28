package kr.or.iei.campuslife.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Set;

import common.JDBCTemplate;
import kr.or.iei.campuslife.dao.ScheduleDao;
import kr.or.iei.campuslife.dao.SubjectDao;
import kr.or.iei.campuslife.vo.Schedule;
import kr.or.iei.campuslife.vo.Subject;

public class ScheduleService {
	public ArrayList<Schedule> getScheduleList(int userNo){
		Connection conn = JDBCTemplate.getConnection();
		ScheduleDao dao = new ScheduleDao();
		ArrayList<Schedule> sl = dao.getScheduleList(conn, userNo);
		return sl;
	}

	public int deleteMySubject(int userNo, int subjectNo) {
		Connection conn = JDBCTemplate.getConnection();
		ScheduleDao dao = new ScheduleDao();
		int result = dao.deleteMySubject(conn, userNo, subjectNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertMySchedule(Schedule sd, int durationTime) {
		Connection conn = JDBCTemplate.getConnection();
		ScheduleDao dao = new ScheduleDao();
		int result = dao.insertMySchedule(conn, sd, durationTime);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int getScout(int userNo, String scheduleA, int scheduleB) {
		Connection conn = JDBCTemplate.getConnection();
		ScheduleDao dao = new ScheduleDao();
		int scout = dao.getScout(conn, userNo, scheduleA, scheduleB);
		return scout;
	}

	public Set<Integer> getMySubjectNo(int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		ScheduleDao dao = new ScheduleDao();
		Set<Integer> mySjNo = dao.getMySubjectNo(conn, userNo);
		JDBCTemplate.close(conn);
		return mySjNo;
	}

	public int deleteMySubject2(int userNo, int subjectNo) {
		Connection conn = JDBCTemplate.getConnection();
		ScheduleDao dao = new ScheduleDao();
		int result = dao.deleteMySubject2(conn, userNo, subjectNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}


}
