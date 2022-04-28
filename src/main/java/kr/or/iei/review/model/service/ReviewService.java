package kr.or.iei.review.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.vo.Schedule;
import kr.or.iei.review.model.dao.ReviewDao;
import kr.or.iei.review.model.vo.RequiredSubject;
import kr.or.iei.review.model.vo.Review;
import kr.or.iei.review.model.vo.ReviewPageData;
import kr.or.iei.review.model.vo.SelectiveSubject;
import kr.or.iei.review.model.vo.Subject;

public class ReviewService {
	public ReviewPageData selectReviewList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		ReviewDao dao = new ReviewDao();
		/** 페이징처리 */
		// 1.결정사항 : 한 페이지당 게시물 수
		int numPerPage = 10;
		/*
		 * reqpage=1 -> 1 and 10 reqpage=2 -> 11 and 20 reqpage=3 -> 21 and 30 게시물
		 * rownum 범위 계산
		 */
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		ArrayList<Review> list = dao.selectReviewList(conn, start, end);
		// 페이징처리
		// 전체 페이지 계산을 위한 전체 계시물 수 조회
		int totalCount = dao.totalReviewCount(conn);
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
			pageNavi += "<a class='page-item' href='/reviewList.do?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		// 페이지 숫자
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-item' href='/reviewList.do?reqPage=" + pageNo + "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			} else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/reviewList.do?reqPage=" + pageNo + "'>";
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
			pageNavi += "<a class='page-item' href='/reviewList.do?reqPage=" + pageNo + "'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		System.out.println(pageNavi);
		ReviewPageData rpd = new ReviewPageData(list,pageNavi);

		JDBCTemplate.close(conn);
		return rpd;
	}

	public ArrayList<Subject> rsList(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ReviewDao dao=new ReviewDao();
		ArrayList<Subject> rList=dao.rsList(conn, memberNo);
		JDBCTemplate.close(conn);
		return rList;
	}

	public ArrayList<Subject> ssList(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ReviewDao dao=new ReviewDao();
		ArrayList<Subject> sList=dao.ssList(conn, memberNo);
		JDBCTemplate.close(conn);
		return sList;
	}

	public Subject searchSubjectName(int subjectNo) {
		Connection conn=JDBCTemplate.getConnection();
		ReviewDao dao=new ReviewDao();
		Subject s = dao.searchSubjectName(conn, subjectNo);
		JDBCTemplate.close(conn);
		return s;
	}


	public Subject getSubjectName(int subjectNo) {
		Connection conn=JDBCTemplate.getConnection();
		ReviewDao dao=new ReviewDao();
		Subject s = dao.getSubjectName(conn, subjectNo);
		JDBCTemplate.close(conn);
		return s;
	}

	public int insertReview(Review r) {
		Connection conn = JDBCTemplate.getConnection();
		ReviewDao dao = new ReviewDao();
		int result = dao.insertReview(conn, r);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateScore(Review r) {
		Connection conn = JDBCTemplate.getConnection();
		ReviewDao dao = new ReviewDao();
		int result = dao.updateScore(conn, r);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Schedule TimeTable(int memberNo) {
		Connection conn=JDBCTemplate.getConnection();
		ReviewDao dao=new ReviewDao();
		Schedule tt = dao.TimeTable(conn, memberNo);
		JDBCTemplate.close(conn);
		return tt;
	}

	public boolean deleteReview(String reviewNos) {
		Connection conn = JDBCTemplate.getConnection();
		ReviewDao dao=new ReviewDao();
		StringTokenizer sT=new StringTokenizer(reviewNos,"/");
		boolean result=true;
		while(sT.hasMoreTokens()) {
			int reviewNo=Integer.parseInt(sT.nextToken());
			int deleteResult=dao.deleteReview(conn, reviewNo);
			if(deleteResult==0) {
				result=false;
				break;
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public boolean updateScore(String subjectNames) {
		Connection conn = JDBCTemplate.getConnection();
		ReviewDao dao=new ReviewDao();
		StringTokenizer sT=new StringTokenizer(subjectNames,"/");
		boolean result2=true;
		while(sT.hasMoreTokens()) {
			String subjectName=sT.nextToken();
			int updateResult=dao.updateScore(conn, subjectName);
			if(updateResult==0) {
				result2=false;
				break;
			}
		}
		if(result2) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result2;
	}

	public boolean deleteQna(String qnaNos) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao=new MemberDao();
		StringTokenizer sT=new StringTokenizer(qnaNos,"/");
		boolean result=true;
		while(sT.hasMoreTokens()) {
			int qnaNo=Integer.parseInt(sT.nextToken());
			int deleteResult=dao.deleteQna(conn, qnaNo);
			if(deleteResult==0) {
				result=false;
				break;
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Review> selectReviewList() {
		Connection conn = JDBCTemplate.getConnection();
		ReviewDao dao = new ReviewDao();
		ArrayList<Review> list = dao.selectReviewList(conn);
		
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Subject> allRsList() {
		Connection conn = JDBCTemplate.getConnection();
		ReviewDao dao=new ReviewDao();
		ArrayList<Subject> rList=dao.allRsList(conn);
		JDBCTemplate.close(conn);
		return rList;
	}

	public ArrayList<Subject> allSsList() {
		Connection conn = JDBCTemplate.getConnection();
		ReviewDao dao=new ReviewDao();
		ArrayList<Subject> rList=dao.allSsList(conn);
		JDBCTemplate.close(conn);
		return rList;
	}

	public Schedule allSubject() {
		Connection conn=JDBCTemplate.getConnection();
		ReviewDao dao=new ReviewDao();
		Schedule tt = dao.allSubject(conn);
		JDBCTemplate.close(conn);
		return tt;
	}
}
