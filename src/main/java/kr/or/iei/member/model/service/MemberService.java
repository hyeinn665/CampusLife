package kr.or.iei.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.Msg;
import kr.or.iei.member.model.vo.MsgPageData;
import kr.or.iei.member.model.vo.Qna;
import kr.or.iei.member.model.vo.QnaPageData;
import kr.or.iei.review.model.vo.Subject;
import kr.or.iei.review.model.vo.Subject2;

public class MemberService {

	//새로운 회원 세미프로젝트용!!!!!!!---------------------------------------------
	//관리자 - 전체 회원 리스트
	public Member selectOneMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		Member m = dao.selectOneMember(conn, member);
		JDBCTemplate.close(conn);
		return m;
	}
	public ArrayList<Member> selectAllmemberList() {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		ArrayList<Member> list = dao.selectAllMemberList(conn);
		JDBCTemplate.close(conn);
		return list;
		}
	//관리자 - 교수 리스트
	public ArrayList<Member> selectProfessorList() {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		ArrayList<Member> plist = dao.selectProfessorList(conn);
		JDBCTemplate.close(conn);
		return plist;
	}
	//관리자 - 학생 리스트
	public ArrayList<Member> selectStudentList() {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		ArrayList<Member> slist = dao.selectStudentList(conn);
		JDBCTemplate.close(conn);
		return slist;
	}
	//블랙리스트 업데이트
	public int blacklistUpdate(int memberNo, int black1, int black2, int black3) {
		Connection conn = JDBCTemplate.getConnection();
		System.out.println(black1);
		MemberDao dao = new MemberDao();
		int result = dao.blacklistUpdate(conn,memberNo,black1,black2,black3);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
//===== 다른 팀원==========================================================================
	
	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		int result = dao.insertMember(conn,m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
}

	public Member selectOneMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		Member m = dao.selectOneMember(conn, memberId);
		JDBCTemplate.close(conn);
		return m;
	}
	public Member selectOneMember(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		Member m = dao.selectOneMember(conn, memberNo);
		JDBCTemplate.close(conn);
		return m;
	}

	public int updateMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		int result = dao.updateMember(conn, member);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return 0;
	}

	public ArrayList<Member> selectAllMember() {
		// TODO Auto-generated method stub
		return null;
	}

	public MsgPageData selectMsgList(int reqPage, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		/** 페이징처리 */
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		ArrayList<Msg> list = dao.selectMsgList(conn, start, end, memberId);
		// 페이징처리
		// 전체 페이지 계산을 위한 전체 계시물 수 조회
		int totalCount = dao.receiveMsgCount(conn, memberId);
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
		// 페이지 네비게이션의 길이 지정
		int pageNaviSize = 5;
		// 페이지 모양 지정
		// 1~5 페이지 요청시 -> 1 2 3 4 5
		// 6~10 페이지 요청시 -> 6 7 8 9 10

		// 페이지 네비게이션 시작번호 계산
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		// 페이지 네비게이션 제작 시작
		String pageNavi = "<ur class='pagination circle-style'>";
		// 이전버튼
		if (pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/msgList.do?memberId=" + memberId + "&reqPage=" + (pageNo - 1)
					+ "'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		// 페이지 숫자
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-item' href='/msgList.do?memberId=" + memberId + "&reqPage="
						+ pageNo + "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			} else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/msgList.do?memberId=" + memberId + "&reqPage=" + pageNo + "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		// 다음버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/msgList.do?reqPage=" + pageNo + "'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		System.out.println(pageNavi);
		MsgPageData mpd = new MsgPageData(list, pageNavi);

		JDBCTemplate.close(conn);
		return mpd;
	}

	public MsgPageData selectSendList(int reqPage, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		/** 페이징처리 */
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		ArrayList<Msg> list = dao.selectSendList(conn, start, end, memberId);

		// 페이징처리
		// 전체 페이지 계산을 위한 전체 계시물 수 조회
		int totalCount = dao.sendMsgCount(conn, memberId);
		System.out.println(totalCount);
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
		// 페이지 네비게이션의 길이 지정
		int pageNaviSize = 5;
		// 페이지 모양 지정
		// 1~5 페이지 요청시 -> 1 2 3 4 5
		// 6~10 페이지 요청시 -> 6 7 8 9 10

		// 페이지 네비게이션 시작번호 계산
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		// 페이지 네비게이션 제작 시작
		String pageNavi = "<ur class='pagination circle-style'>";
		// 이전버튼
		if (pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/sendList.do?memberId=" + memberId + "&reqPage=" + (pageNo - 1)
					+ "'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		// 페이지 숫자
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-item' href='/sendList.do?memberId=" + memberId + "&reqPage="
						+ pageNo + "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			} else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/sendList.do?memberId=" + memberId + "&reqPage=" + pageNo
						+ "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		// 다음버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/sendList.do?memberId=" + memberId + "&reqPage=" + pageNo + "'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		MsgPageData mpd = new MsgPageData(list, pageNavi);

		JDBCTemplate.close(conn);
		return mpd;
	}

	public Msg selectOneMsg(int msgNo) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		Msg m = dao.selectOneMsg(conn, msgNo);
		JDBCTemplate.close(conn);
		return m;
	}

	public Msg selectReceiver(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		Msg r = dao.selectReceiver(conn, memberId);
		JDBCTemplate.close(conn);
		return r;
	}

	public int insertMsg(Msg m) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		int result = dao.insertMsg(conn, m);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public boolean deleteMsg(String msgNos, String delRec) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		StringTokenizer sT = new StringTokenizer(msgNos, "/");
		boolean result = true;
		while (sT.hasMoreTokens()) {
			int msgNo = Integer.parseInt(sT.nextToken());
			int deleteResult = dao.deleteMsg(conn, msgNo, delRec);
			if (deleteResult == 0) {
				result = false;
				break;
			}
		}
		if (result) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public QnaPageData selectQnaList(int reqPage, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		/** 페이징처리 */
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		ArrayList<Qna> list = dao.selectQnaList(conn, start, end, memberId);

		int totalCount = dao.receiveQnaCount(conn, memberId);
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		String pageNavi = "<ur class='pagination circle-style'>";
		if (pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/qna.do?memberId=" + memberId + "&reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-item' href='/qna.do?memberId=" + memberId + "&reqPage=" + pageNo
						+ "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			} else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/qna.do?memberId=" + memberId + "&reqPage=" + pageNo + "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		// 다음버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/qna.do?reqPage=" + pageNo + "'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		System.out.println(pageNavi);
		QnaPageData qpd = new QnaPageData(list, pageNavi);

		JDBCTemplate.close(conn);
		return qpd;
	}

	public QnaPageData selectQnaAdminList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		/** 페이징처리 */
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		ArrayList<Qna> list = dao.selectQnaAdminList(conn, start, end);

		int totalCount = dao.writeQnaCount(conn);
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		String pageNavi = "<ur class='pagination circle-style'>";
		if (pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/qnaAdmin.do?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-item' href='/qnaAdmin.do?reqPage=" + pageNo + "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			} else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/qnaAdmin.do?reqPage=" + pageNo + "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		// 다음버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/qnaAdmin.do?reqPage=" + pageNo + "'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		System.out.println(pageNavi);
		QnaPageData qpd = new QnaPageData(list, pageNavi);

		JDBCTemplate.close(conn);
		return qpd;
	}

	public int insertQna(Qna q) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		int result = dao.insertQna(conn, q);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Qna selectOneQna(int qnaNo) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		Qna mv = dao.selectOneQna(conn, qnaNo);
		JDBCTemplate.close(conn);
		return mv;
	}

	public int insertReply(Qna q) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		int result = dao.insertReply(conn, q);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public boolean deleteSend(String msgNos, String delSend) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		StringTokenizer sT = new StringTokenizer(msgNos, "/");
		boolean result = true;
		while (sT.hasMoreTokens()) {
			int msgNo = Integer.parseInt(sT.nextToken());
			int deleteResult = dao.deleteSend(conn, msgNo, delSend);
			if (deleteResult == 0) {
				result = false;
				break;
			}
		}
		if (result) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int checkDel() {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		int result = dao.checkDel(conn);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertCourse(Subject2 s) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		int result = dao.insertCourse(conn, s);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}