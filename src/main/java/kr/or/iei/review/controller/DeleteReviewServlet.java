package kr.or.iei.review.controller;

import java.io.File;
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
 * Servlet implementation class DeleteReviewServlet
 */
@WebServlet(name = "DeleteReview", urlPatterns = { "/deleteReview.do" })
public class DeleteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String reviewNos=request.getParameter("reviewNos");
		String subjectNames=request.getParameter("subjectNames");
		System.out.println(reviewNos);
		System.out.println(subjectNames);
		ReviewService service=new ReviewService();
		boolean result=service.deleteReview(reviewNos);
		boolean result2=service.updateScore(subjectNames);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if (result&&result2) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "수강후기 삭제 완료");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/reviewList.do?reqPage=1");
		} else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "수강후기 삭제 실패");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/reviewList.do?reqPage=1");
		}
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
