package kr.or.iei.campuslife.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import common.JDBCTemplate;
import kr.or.iei.campuslife.dao.ScheduleDao;
import kr.or.iei.campuslife.dao.SubjectDao;
import kr.or.iei.campuslife.vo.Subject;

public class SubjectService {

	public int insertSubject(Subject s, int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		SubjectDao dao = new SubjectDao();
		int result = dao.insertSubject(conn,s);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	public ArrayList<Subject> getSubjectList(){
		ArrayList<Subject> list = null;
		Connection conn = JDBCTemplate.getConnection();
		SubjectDao dao = new SubjectDao();
		list = dao.getSubjectList(conn);
		JDBCTemplate.close(conn);
		return list;
	}
	
	public int deleteSubject(Subject s) {
		Connection conn = JDBCTemplate.getConnection();
		SubjectDao dao = new SubjectDao();
		int result = dao.deleteSubject(conn,s);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	public ArrayList<Subject> getSubjectList(int userNo){
		ArrayList<Subject> subjectList = null;
		Connection conn = JDBCTemplate.getConnection();
		SubjectDao dao = new SubjectDao();
		subjectList = dao.getSubjectList(conn);
		JDBCTemplate.close(conn);
		return subjectList;
	}

	public ArrayList<Subject> getMySubject(Set<Integer> setSchedule) {
		ArrayList<Subject> mySubjectList = null;
		Connection conn = JDBCTemplate.getConnection();
		SubjectDao dao = new SubjectDao();
		mySubjectList = dao.mySubjectList(conn, setSchedule);
		
		JDBCTemplate.close(conn);

		return mySubjectList;
	}

	public int deleteLecture(int subjectNo) {
		Connection conn = JDBCTemplate.getConnection();
		SubjectDao dao = new SubjectDao();
		int result = dao.deleteLecture(conn, subjectNo); 
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int getSubjectNo(String subjectName, String startTime) {
		Connection conn = JDBCTemplate.getConnection();
		SubjectDao dao = new SubjectDao();
		int subjectNo = dao.getSubjectNo(conn, subjectName, startTime);
		return subjectNo;
	}

	public Map<Integer, String> getProfName() {
		Connection conn = JDBCTemplate.getConnection();
		SubjectDao dao = new SubjectDao();
		Map<Integer, String> mapName = dao.getProfName(conn);
		return mapName;
	}

	
}
