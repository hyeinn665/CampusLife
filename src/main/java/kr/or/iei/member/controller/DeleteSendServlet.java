package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;

/**
 * Servlet implementation class DeleteSendServlet
 */
@WebServlet(name = "DeleteSend", urlPatterns = { "/deleteSend.do" })
public class DeleteSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteSendServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String msgNos = request.getParameter("msgNos");
		String delSend = request.getParameter("delSend");
		MemberService service = new MemberService();
		boolean result = service.deleteSend(msgNos, delSend);
		int result2=service.checkDel();

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if (result) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "쪽지 삭제 완료");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/");
		} else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "쪽지 삭제 실패");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/");
		}
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
