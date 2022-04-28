package kr.or.iei.review.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.or.iei.review.model.service.ReviewService;
import kr.or.iei.review.model.vo.Review;

/**
 * Servlet implementation class ReviewAjaxServlet
 */
@WebServlet(name = "ReviewAjax", urlPatterns = { "/reviewAjax.do" })
public class ReviewAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewAjaxServlet() {
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
		// 2.값추출
		// 3.비즈니스로직
		ReviewService service = new ReviewService();
		ArrayList<Review> list = service.selectReviewList();
		JSONArray reviewList = new JSONArray();// JSONObject를 list타입으로 처리할 객체
		if (!list.isEmpty()) {
			for (Review r : list) {
				JSONObject obj = new JSONObject();
				obj.put("reviewContent", r.getReviewContent());
				reviewList.add(obj);
			}
		}
		// 4.결과처리
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(reviewList);
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
