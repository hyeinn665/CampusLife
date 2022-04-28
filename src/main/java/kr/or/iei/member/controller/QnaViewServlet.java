package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Msg;
import kr.or.iei.member.model.vo.Qna;

/**
 * Servlet implementation class QnaViewServlet
 */
@WebServlet(name = "QnaView", urlPatterns = { "/qnaView.do" })
public class QnaViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QnaViewServlet() {
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
		int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
		// 3.비즈니스로직
		MemberService service = new MemberService();
		Qna mv = service.selectOneQna(qnaNo);
		// Notice n=service.selectOneNotice(noticeNo);
		// 4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/qnaView.jsp");
		request.setAttribute("mv", mv);
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
