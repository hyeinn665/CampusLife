package kr.or.iei.freeboard.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.freeboard.model.dao.FreeboardDao;
import kr.or.iei.freeboard.model.vo.Freeboard;
import kr.or.iei.freeboard.model.vo.FreeboardPageData;
import kr.or.iei.review.model.dao.ReviewDao;
import kr.or.iei.review.model.vo.Review;


public class FreeboardService {
/*
	public ArrayList<Freeboard> selectFreeboardList() {
		Connection conn = JDBCTemplate.getConnection();
		FreeboardDao dao = new FreeboardDao();
		ArrayList<Freeboard> list = dao.selectFreeboardList(conn);
		JDBCTemplate.close(conn);
		return list;
	}
	*/

	public FreeboardPageData selectFreeboardList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
        FreeboardDao dao = new FreeboardDao();

        int numPerPage = 10;
	
        int end = reqPage * numPerPage;
        int start = end - numPerPage + 1;
        ArrayList<Freeboard> list = dao.selectFreeboardList(conn, start, end);

        int totalCount = dao.totalFreeboardCount(conn);

		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
            totalPage = totalCount / numPerPage;
        } else {
            totalPage = totalCount / numPerPage + 1;
        }

        int pageNaviSize = 3;
        
        int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
        
        String pageNavi = "<ul class='pagination'>";

        if (pageNo != 1) {
            pageNavi = pageNavi + "<li>";
            pageNavi = pageNavi + "<a class='page-item' href='/freeboardList.do?reqPage=" + (pageNo - 1) + "'>";
            pageNavi = pageNavi + "<span class='material-icons'>chevron_left</span>";
            pageNavi = pageNavi + "</a></li>";
        }

        for(int i = 0; i < pageNaviSize; ++i) {
            if (pageNo == reqPage) {
                pageNavi = pageNavi + "<li>";
                pageNavi = pageNavi + "<a class='page-item active-page' href='/freeboardList.do?reqPage=" + pageNo + "'>";
                pageNavi = pageNavi + pageNo;
                pageNavi = pageNavi + "</a></li>";
            } else {
                pageNavi = pageNavi + "<li>";
                pageNavi = pageNavi + "<a class='page-item' href='/freeboardList.do?reqPage=" + pageNo + "'>";
                pageNavi = pageNavi + pageNo;
                pageNavi = pageNavi + "</a></li>";
            }

            pageNo++;
            if (pageNo > totalPage) {
                break;
            }
        }
        
        if (pageNo <= totalPage) {
            pageNavi = pageNavi + "<li>";
            pageNavi = pageNavi + "<a class='page-item' href='/freeboardList.do?reqPage=" + pageNo + "'>";
            pageNavi = pageNavi + "<span class='material-icons'>chevron_right</span>";
            pageNavi = pageNavi + "</a></li>";
        }

        pageNavi += "</ul>";
        System.out.println(pageNavi);
        FreeboardPageData fpd = new FreeboardPageData(list, pageNavi);
        JDBCTemplate.close(conn);
        return fpd;
    }

	public int insertFreeboard(Freeboard f) {
		 Connection conn = JDBCTemplate.getConnection();
	        FreeboardDao dao = new FreeboardDao();
	        int result = dao.insertFreeboard(conn, f);
	        if (result > 0) {
	            JDBCTemplate.commit(conn);
	        } else {
	            JDBCTemplate.rollback(conn);
	        }

	        JDBCTemplate.close(conn);
	        return result;
	    }

	public Freeboard selectOneFreeboard(int freeNo) {
		Connection conn = JDBCTemplate.getConnection();
		FreeboardDao dao = new FreeboardDao();
		int result = dao.updateReadCount(conn, freeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
		Freeboard f = dao.selectOneFreeboard(conn, freeNo);
		JDBCTemplate.close(conn);
		return f;
	}

	public Freeboard getFreeboard(int freeNo) {
		Connection conn = JDBCTemplate.getConnection();
		FreeboardDao dao = new FreeboardDao();
		Freeboard f = dao.selectOneFreeboard(conn, freeNo);
		JDBCTemplate.close(conn);
		return f;
	}

	public int freeboardDelete(int freeNo) {
		Connection conn = JDBCTemplate.getConnection();
		FreeboardDao dao = new FreeboardDao();
		int result = dao.freeboardDelete(conn, freeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int freeboardUpdate(Freeboard f) {
		Connection conn = JDBCTemplate.getConnection();
		FreeboardDao dao = new FreeboardDao();
		int result = dao.freeboardUpdate(conn, f);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public ArrayList<Freeboard> mainListFree() {
		Connection conn = JDBCTemplate.getConnection();
		FreeboardDao dao = new FreeboardDao();
		ArrayList<Freeboard> list = dao.mainListFree(conn);
		JDBCTemplate.close(conn);
		return list;
	}
}
