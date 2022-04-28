package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.review.model.service.ReviewService;

/**
 * Servlet implementation class DeleteQnaServlet
 */
@WebServlet(name = "DeleteQna", urlPatterns = { "/deleteQna.do" })
public class DeleteQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteQnaServlet() {
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

		String qnaNos = request.getParameter("qnaNos");
		ReviewService service = new ReviewService();
		boolean result = service.deleteQna(qnaNos);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if (result) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "글삭제 완료");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/qnaAdmin.do?reqPage=1");
		} else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "글삭제 실패");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/qnaAdmin.do?reqPage=1");
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
