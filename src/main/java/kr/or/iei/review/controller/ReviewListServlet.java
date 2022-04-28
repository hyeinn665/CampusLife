package kr.or.iei.review.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.review.model.service.ReviewService;
import kr.or.iei.review.model.vo.ReviewPageData;

/**
 * Servlet implementation class ReviewListServlet
 */
@WebServlet(name = "ReviewList", urlPatterns = { "/reviewList.do" })
public class ReviewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewListServlet() {
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
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		// 3.비즈니스로직
		ReviewService service = new ReviewService();
		ReviewPageData rpd = service.selectReviewList(reqPage);
		// 4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/review/reviewList.jsp");
		request.setAttribute("list", rpd.getList());
		request.setAttribute("pageNavi", rpd.getPageNavi());
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
