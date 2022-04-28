package kr.or.iei.managerboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.managerboard.model.service.ManagerBoardService;
import kr.or.iei.managerboard.model.vo.NoticeComment;


/**
 * Servlet implementation class NoticeCommentUpdateServlet
 */
@WebServlet(name = "NoticeManagerBoardCommentUpdate", urlPatterns = { "/noticeCommentUpdate2.do" })
public class NoticeCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeCommentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		int ncNo = Integer.parseInt(request.getParameter("ncNo"));
		int mbNo = Integer.parseInt(request.getParameter("mbNo"));
		String ncContent = request.getParameter("ncContent");
		//No랑 Content는 업데이트용, noticeNo는 페이지이동용
		NoticeComment nc = new NoticeComment();
		nc.setNcNo(ncNo);
		nc.setNcContent(ncContent);
		//3.비즈니스로직
		ManagerBoardService service = new ManagerBoardService();
		int result = service.updateManagerBoardComment(nc);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "댓글 수정 완료!");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "댓글 수정 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/managerBoardView.do?mbNo="+mbNo);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
