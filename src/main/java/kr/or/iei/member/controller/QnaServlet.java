package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.MsgPageData;
import kr.or.iei.member.model.vo.QnaPageData;

/**
 * Servlet implementation class QnaServlet
 */
@WebServlet(name = "Qna", urlPatterns = { "/qna.do" })
public class QnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QnaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.인코딩
		request.setCharacterEncoding("utf-8");
		// 2.값추출
		String memberId = request.getParameter("memberId");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		System.out.println(reqPage);
		// 3.비즈니스로직
		MemberService service = new MemberService();
		QnaPageData qpd = service.selectQnaList(reqPage, memberId);
		// 4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/qnaList.jsp");
		request.setAttribute("list", qpd.getList());
		request.setAttribute("pageNavi", qpd.getPageNavi());
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
