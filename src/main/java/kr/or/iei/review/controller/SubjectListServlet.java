package kr.or.iei.review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.vo.Schedule;
import kr.or.iei.review.model.service.ReviewService;
import kr.or.iei.review.model.vo.RequiredSubject;
import kr.or.iei.review.model.vo.ReviewPageData;
import kr.or.iei.review.model.vo.SelectiveSubject;
import kr.or.iei.review.model.vo.Subject;

/**
 * Servlet implementation class SubjectListServlet
 */
@WebServlet(name = "SubjectList", urlPatterns = { "/subjectList.do" })
public class SubjectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubjectListServlet() {
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
		int memberNo=Integer.parseInt(request.getParameter("memberNo"));
		// 3.비즈니스로직
		ReviewService service = new ReviewService();
		Schedule tt=  service.TimeTable(memberNo);
		ArrayList<Subject> rList=service.rsList(memberNo);
		ArrayList<Subject> sList=service.ssList(memberNo);
		System.out.println(memberNo);
		System.out.println(rList);
		// 4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/review/subjectList.jsp");
		request.setAttribute("rList", rList);
		request.setAttribute("sList", sList);
		request.setAttribute("tt", tt);
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
