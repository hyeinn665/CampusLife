package kr.or.iei.campuslife.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.campuslife.service.SubjectService;
import kr.or.iei.campuslife.vo.Subject;
import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.review.model.vo.Subject2;

/**
 * Servlet implementation class InsertScheduleServlet
 */
@WebServlet(name = "InsertSchedule", urlPatterns = { "/insertSchedule.do" })
public class InsertScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		SubjectService sjService = new SubjectService();
		ArrayList<Subject> sjl = sjService.getSubjectList();
		ArrayList<Integer> profNo = new ArrayList<Integer>();
		
		for(int i=0;i<sjl.size();i++) {
			profNo.add(sjl.get(i).getProfNo());
		}
		
		
		Map<Integer, String> profName = sjService.getProfName();
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/schedule/insertSchedule.jsp");
		request.setAttribute("sjl", sjl);
		request.setAttribute("profName", profName);
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
