package kr.or.iei.campuslife.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.campuslife.service.ScheduleService;
import kr.or.iei.campuslife.service.SubjectService;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.campuslife.vo.Schedule;
import kr.or.iei.campuslife.vo.Subject;

/**
 * Servlet implementation class MySubjectServlet
 */
@WebServlet(name = "mySubject", urlPatterns = { "/mySubject.do" })
public class MySubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MySubjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("m");
		int userNo = m.getMemberNo();
		
		ScheduleService sdService = new ScheduleService();
		ArrayList<Schedule> sdl = sdService.getScheduleList(userNo);
		Set<Integer> setSchedule = new HashSet<Integer>();
		for(int i=0;i<sdl.size();i++) {
			setSchedule.add(sdl.get(i).getSubjectNo());
		}
		SubjectService sjService = new SubjectService();
		ArrayList<Subject> sjl = sjService.getMySubject(setSchedule);
		Map<Integer, String> profName = sjService.getProfName();
		
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/schedule/mySubject.jsp");
		request.setAttribute("sdl", sdl);
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
