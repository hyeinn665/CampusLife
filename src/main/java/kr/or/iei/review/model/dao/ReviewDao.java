package kr.or.iei.review.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.member.model.vo.Schedule;
import kr.or.iei.review.model.vo.RequiredSubject;
import kr.or.iei.review.model.vo.Review;
import kr.or.iei.review.model.vo.SelectiveSubject;
import kr.or.iei.review.model.vo.Subject;

public class ReviewDao {

	public ArrayList<Review> selectReviewList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> list = new ArrayList<Review>();
		String query = "select * from (select rownum as rnum,n.* from (select * from reviewboard r JOIN subject s ON (r.subject_no = s.subject_no) order by review_no desc)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Review r = new Review();
				r.setReviewNo(rset.getInt("review_no"));
				r.setSubjectNo(rset.getInt("subject_no"));
				r.setSubjectName(rset.getString("subject_name"));
				r.setReviewContent(rset.getString("review_content"));
				r.setReviewScore(rset.getInt("review_score"));
				r.setReqDate(rset.getString("req_date"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int totalReviewCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from reviewboard";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Subject> rsList(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Subject> rList = new ArrayList<Subject>();
		String query = "select t.member_no, t.subject_no, subject_name, round(score_avg,2) from sub_mem t join subject s on (s.subject_no=t.subject_no) where t.member_no=? and select_subject=1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Subject rs = new Subject();
				rs.setMemberNo(rset.getInt("member_no"));
				rs.setSubjectNo(rset.getInt("subject_no"));
				rs.setSubjectName(rset.getString("subject_name"));
				rs.setScoreAvg(rset.getDouble("round(score_avg,2)"));
				rList.add(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return rList;
	}

	public ArrayList<Subject> ssList(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Subject> sList = new ArrayList<Subject>();
		String query = "select t.member_no, t.subject_no, subject_name, round(score_avg,2) from sub_mem t join subject s on (s.subject_no=t.subject_no) where t.member_no=? and select_subject=2";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Subject ss = new Subject();
				ss.setMemberNo(rset.getInt("member_no"));
				ss.setSubjectNo(rset.getInt("subject_no"));
				ss.setSubjectName(rset.getString("subject_name"));
				ss.setScoreAvg(rset.getDouble("round(score_avg,2)"));
				sList.add(ss);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return sList;
	}

	public Subject searchSubjectName(Connection conn, int subjectNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Subject s = null;
		String query = "select * from subject where subject_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, subjectNo);
			rset = pstmt.executeQuery();
			s = new Subject();
			s.setSubjectName(rset.getString("subject_name"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return s;
	}

	public Subject getSubjectName(Connection conn, int subjectNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Subject s = null;
		String query = "select * from subject where subject_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, subjectNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				s = new Subject();
				s.setSubjectNo(subjectNo);
				s.setSubjectName(rset.getString("subject_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return s;
	}

	public int insertReview(Connection conn, Review r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into reviewboard values(review_seq.nextval, ?,?, ?, ?,to_char(sysdate,'yyyy-mm-dd'))";
		// String query="update subject set score_avg=(select avg(review_score) from
		// reviewboard where subject_no=?) where subject_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			// pstmt2 = conn.prepareStatement(query2);
			pstmt.setInt(1, r.getSubjectNo());
			pstmt.setInt(2, r.getMemberNo());
			pstmt.setString(3, r.getReviewContent());
			pstmt.setInt(4, r.getReviewScore());
			/*
			 * pstmt2.setInt(1, r.getSubjectNo()); pstmt2.setInt(2, r.getSubjectNo());
			 */
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateScore(Connection conn, Review r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update subject set score_avg=(select avg(review_score) from reviewboard where subject_no=?) where subject_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, r.getSubjectNo());
			pstmt.setInt(2, r.getSubjectNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

//	public EnrollSubject EnrollSubject(Connection conn, int memberNo) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		EnrollSubject es = null;
//		String query = "select * from enroll_sub where member_no=?";
//
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, memberNo);
//			rset = pstmt.executeQuery();
//			if (rset.next()) {
//				es= new EnrollSubject();
//				es.setMemberNo(rset.getInt("member_no"));
//				es.setSubjectNo(rset.getInt("subject_no"));
//				es.setEnrollDate(rset.getString("enroll_date"));		
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			JDBCTemplate.close(rset);
//			JDBCTemplate.close(pstmt);
//		}
//		return es;
//	}
	public Schedule TimeTable(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Schedule tt = null;
		String query = "select t.member_no, t.subject_no, subject_name, round(score_avg,2) from sub_mem t join subject s on (s.subject_no=t.subject_no) where t.member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				tt= new Schedule();
				tt.setMemberNo(rset.getInt("member_no"));
				tt.setSubjectNo(rset.getInt("subject_no"));
				tt.setSubjectName(rset.getString("subject_name"));
				tt.setScoreAvg(rset.getDouble("round(score_avg,2)"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return tt;
	}

	public int deleteReview(Connection conn, int reviewNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from reviewboard where review_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateScore(Connection conn, String subjectName) {
		PreparedStatement pstmt = null;
		int result2 = 0;
		String query = "update subject set score_avg=(select avg(review_score) from reviewboard where subject_name=?) where subject_name=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subjectName);
			pstmt.setString(2, subjectName);
			result2 = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result2;
	}

	public ArrayList<Review> selectReviewList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> list = new ArrayList<Review>();
		String query = "select * from (select rownum as rnum,n.* from (select * from reviewboard r JOIN subject s ON (r.subject_no = s.subject_no) order by review_no desc)n)";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Review r = new Review();
				r.setReviewNo(rset.getInt("review_no"));
				r.setSubjectNo(rset.getInt("subject_no"));
				r.setSubjectName(rset.getString("subject_name"));
				r.setReviewContent(rset.getString("review_content"));
				r.setReviewScore(rset.getInt("review_score"));
				r.setReqDate(rset.getString("req_date"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Subject> allRsList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Subject> rList = new ArrayList<Subject>();
		String query = "select t.member_no, t.subject_no, subject_name, round(score_avg,2) from sub_mem t join subject s on (s.subject_no=t.subject_no) where select_subject=1";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Subject rs = new Subject();
				rs.setMemberNo(rset.getInt("member_no"));
				rs.setSubjectNo(rset.getInt("subject_no"));
				rs.setSubjectName(rset.getString("subject_name"));
				rs.setScoreAvg(rset.getDouble("round(score_avg,2)"));
				rList.add(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return rList;
	}

	public ArrayList<Subject> allSsList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Subject> rList = new ArrayList<Subject>();
		String query = "select t.member_no, t.subject_no, subject_name, round(score_avg,2) from sub_mem t join subject s on (s.subject_no=t.subject_no) where select_subject=2";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Subject rs = new Subject();
				rs.setMemberNo(rset.getInt("member_no"));
				rs.setSubjectNo(rset.getInt("subject_no"));
				rs.setSubjectName(rset.getString("subject_name"));
				rs.setScoreAvg(rset.getDouble("round(score_avg,2)"));
				rList.add(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return rList;
	}

	public Schedule allSubject(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Schedule tt = null;
		String query = "select t.member_no, t.subject_no, subject_name, round(score_avg,2) from sub_mem t join subject s on (s.subject_no=t.subject_no)";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				tt= new Schedule();
				tt.setMemberNo(rset.getInt("member_no"));
				tt.setSubjectNo(rset.getInt("subject_no"));
				tt.setSubjectName(rset.getString("subject_name"));
				tt.setScoreAvg(rset.getDouble("round(score_avg,2)"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return tt;
	}

}
