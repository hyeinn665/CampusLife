package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReplyFrmServlet
 */
@WebServlet(name = "ReplyFrm", urlPatterns = { "/replyFrm.do" })
public class ReplyFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReplyFrmServlet() {
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
		String writer = request.getParameter("writer");
		int qnaNo=Integer.parseInt(request.getParameter("qnaNo"));
		String qnaTitle = request.getParameter("qnaTitle");
		// 3.비즈니스로직
		// 4.화면처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/replyFrm.jsp");
		request.setAttribute("writer", writer);
		request.setAttribute("qnaNo", qnaNo);
		request.setAttribute("qnaTitle", qnaTitle);
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
