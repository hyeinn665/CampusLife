package kr.or.iei.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.Msg;
import kr.or.iei.member.model.vo.Qna;
import kr.or.iei.review.model.vo.Subject;
import kr.or.iei.review.model.vo.Subject2;

public class MemberDao {
	public ArrayList<Member> selectAllMemberList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getString("enroll_date"));
				list.add(m);
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

	// 관리자 마이페이지 - 교수 리스트
	public ArrayList<Member> selectProfessorList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> plist = new ArrayList<Member>();
		String query = "select * from member where grade='교수'";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getString("enroll_date"));
				plist.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return plist;
	}

	// 관리자 마이페이지 - 학생 리스트
	public ArrayList<Member> selectStudentList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> slist = new ArrayList<Member>();
		String query = "select * from member where grade='학생'";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getString("enroll_date"));
				m.setBlack1(rset.getInt("black1"));
				m.setBlack2(rset.getInt("black2"));
				m.setBlack3(rset.getInt("black3"));
				slist.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return slist;
	}

	// 블랙리스트 업데이트
	public int blacklistUpdate(Connection conn, int memberNo, int black1, int black2, int black3) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set black1=black1+?,black2=black2+?,black3=black3+? where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, black1);
			pstmt.setInt(2, black2);
			pstmt.setInt(3, black3);
			pstmt.setInt(4, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Member selectOneMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member where member_id=? and member_pw=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getString("enroll_Date"));
				m.setBlack1(rset.getInt("black1"));
				m.setBlack2(rset.getInt("black2"));
				m.setBlack3(rset.getInt("black3"));

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}


	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(?,?,?,?,?,?,?,?,to_char(sysdate,'yyyy/mm/dd'),0,0,0)";

		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, m.getMemberNo());
			pstmt.setString(2, m.getMemberId());
			pstmt.setString(3, m.getMemberPw());
			pstmt.setString(4, m.getMemberName());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getPhone());
			pstmt.setString(7, m.getMemberAddr());
			pstmt.setString(8, m.getGrade());
			result=pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;

	}

	public Member selectOneMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member where member_id=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getString("enroll_Date"));
				m.setBlack1(rset.getInt("black1"));
				m.setBlack2(rset.getInt("black2"));
				m.setBlack3(rset.getInt("black3"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return m;
		
	}
	public Member selectOneMember(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member where member_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getString("enroll_Date"));
				m.setBlack1(rset.getInt("black1"));
				m.setBlack2(rset.getInt("black2"));
				m.setBlack3(rset.getInt("black3"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return m;
		
	}

	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_pw=?, phone=?, member_addr=? where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getMemberAddr());
			pstmt.setString(4, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int deleteMember(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int receiveMsgCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from msg where receiver=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
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

	public int sendMsgCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from msg where writer=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
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

	public ArrayList<Msg> selectMsgList(Connection conn, int start, int end, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Msg> list = new ArrayList<Msg>();
		String query = "select * from (select rownum as rnum,n.* from (select * from msg where receiver=? and del_rec='n' order by msg_no desc)n) where rnum between ? and ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Msg msg = new Msg();
				msg.setMsgNo(rset.getInt("msg_no"));
				msg.setMsgTitle(rset.getString("msg_title"));
				msg.setWriter(rset.getString("writer"));
				msg.setDelRec(rset.getString("del_rec"));
				msg.setReqDate(rset.getString("req_date"));
				list.add(msg);
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

	public ArrayList<Msg> selectSendList(Connection conn, int start, int end, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Msg> list = new ArrayList<Msg>();
		String query = "select * from (select rownum as rnum,n.* from (select * from msg where writer=? and del_send='n' order by msg_no desc)n) where rnum between ? and ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Msg msg = new Msg();
				msg.setMsgNo(rset.getInt("msg_no"));
				msg.setMsgTitle(rset.getString("msg_title"));
				msg.setReceiver(rset.getString("receiver"));
				msg.setDelSend(rset.getString("del_send"));
				msg.setReqDate(rset.getString("req_date"));
				list.add(msg);
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

	public Msg selectOneMsg(Connection conn, int msgNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Msg m = null;
		String query = "select * from msg where msg_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, msgNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				m = new Msg();
				m.setMsgTitle(rset.getString("msg_title"));
				m.setWriter(rset.getString("writer"));
				m.setReqDate(rset.getString("req_date"));
				m.setMsgContent(rset.getString("msg_content"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return m;
	}

	public Msg selectReceiver(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Msg r = null;
		String query = "select * from msg where receiver=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				r = new Msg();
				r.setMsgContent(rset.getString("msg_content"));
				r.setMsgNo(rset.getInt("msg_no"));
				r.setMsgTitle(rset.getString("msg_title"));
				r.setReqDate(rset.getString("req_date"));
				r.setWriter(rset.getString("writer"));
				r.setReceiver(rset.getString("receiver"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return r;
	}

	public int insertMsg(Connection conn, Msg m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into msg values(msg_seq.nextval,?,?,?,?,default,default, to_char(sysdate,'yyyy-mm-dd'))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getWriter());
			pstmt.setString(2, m.getReceiver());
			pstmt.setString(3, m.getMsgTitle());
			pstmt.setString(4, m.getMsgContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteMsg(Connection conn, int msgNo, String delRec) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update msg set del_rec=? where msg_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, delRec);
			pstmt.setInt(2, msgNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Qna> selectQnaList(Connection conn, int start, int end, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Qna> list = new ArrayList<Qna>();
		String query = "select * from (select rownum as rnum,n.* from (select * from Qna where writer=? or receiver=? order by qna_no desc)n) where rnum between ? and ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberId);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Qna qna = new Qna();
				qna.setQnaNo(rset.getInt("qna_no"));
				qna.setQnaTitle(rset.getString("qna_title"));
				qna.setWriter(rset.getString("writer"));
				qna.setReqDate(rset.getString("req_date"));
				list.add(qna);
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

	public int receiveQnaCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from qna where writer=? or receiver=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberId);
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

	public ArrayList<Qna> selectQnaAdminList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Qna> list = new ArrayList<Qna>();
		String query = "select * from (select rownum as rnum,n.* from (select * from Qna order by qna_no desc)n) where rnum between ? and ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Qna qna = new Qna();
				qna.setQnaNo(rset.getInt("qna_no"));
				qna.setQnaTitle(rset.getString("qna_title"));
				qna.setWriter(rset.getString("writer"));
				qna.setReqDate(rset.getString("req_date"));
				list.add(qna);
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

	public int writeQnaCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from qna";
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

	public int insertQna(Connection conn, Qna q) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into qna values(qna_seq.nextval,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, q.getWriter());
			pstmt.setString(2, q.getReceiver());
			pstmt.setString(3, q.getQnaTitle());
			pstmt.setString(4, q.getQnaContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Qna selectOneQna(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Qna q = null;
		String query = "select * from qna where qna_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				q = new Qna();
				q.setQnaNo(rset.getInt("qna_no"));
				q.setQnaTitle(rset.getString("qna_title"));
				q.setQnaContent(rset.getString("qna_content"));
				q.setReqDate(rset.getString("req_date"));
				q.setWriter(rset.getString("writer"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return q;
	}

	public int insertReply(Connection conn, Qna q) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into qna values(?-1,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, q.getQnaNo());
			pstmt.setString(2, q.getWriter());
			pstmt.setString(3, q.getReceiver());
			pstmt.setString(4, q.getQnaTitle());
			pstmt.setString(5, q.getQnaContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteQna(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from qna where qna_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteSend(Connection conn, int msgNo, String delSend) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update msg set del_send=? where msg_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, delSend);
			pstmt.setInt(2, msgNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int checkDel(Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from msg where del_rec='y' and del_send='y'";
		try {
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertCourse(Connection conn, Subject2 s) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into sub_mem values(sm_seq.nextval,?,?)";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, s.getMemberNo());
			pstmt.setInt(2, s.getSubjectNo());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
