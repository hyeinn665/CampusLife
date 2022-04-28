package kr.or.iei.review.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.review.model.service.ReviewService;
import kr.or.iei.review.model.vo.Review;

/**
 * Servlet implementation class ReviewWriteServlet
 */
@WebServlet(name = "ReviewWrite", urlPatterns = { "/reviewWrite.do" })
public class ReviewWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewWriteServlet() {
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
		int subjectNo = Integer.parseInt(request.getParameter("subjectNo"));
		int memberNo=Integer.parseInt(request.getParameter("memberNo"));
		int starscore = Integer.parseInt(request.getParameter("starscore"));
		String content = request.getParameter("content");
		Review r = new Review();
		r.setSubjectNo(subjectNo);
		r.setMemberNo(memberNo);
		r.setReviewScore(starscore);
		r.setReviewContent(content);
		// 3.비즈니스로직
		ReviewService service = new ReviewService();
		int result = service.insertReview(r);
		int result2=service.updateScore(r);
		// 4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if (result*result2 > 0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "수강후기가 등록되었습니다.");
			request.setAttribute("icon", "success");
		} else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "중복으로 등록 할 수 없습니다.");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/reviewList.do?reqPage=1");
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
