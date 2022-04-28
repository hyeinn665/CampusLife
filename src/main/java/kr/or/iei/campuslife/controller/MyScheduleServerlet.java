package kr.or.iei.campuslife.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
import kr.or.iei.member.model.vo.*;
import kr.or.iei.campuslife.vo.Schedule;
import kr.or.iei.campuslife.vo.Subject;

/**
 * Servlet implementation class MyScheduleServerlet
 */
@WebServlet(name = "MySchedule", urlPatterns = { "/mySchedule.do" })
public class MyScheduleServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyScheduleServerlet() {
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
		
		if(m == null) {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "로그인을 진행해주세요");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			view.forward(request, response);
		}else {
			int userNo = m.getMemberNo();
			
			ScheduleService sdService = new ScheduleService();
			ArrayList<Schedule> sdl = sdService.getScheduleList(userNo);
			Set<Integer> setSchedule = new HashSet<Integer>();
			for(int i=0;i<sdl.size();i++) {
				setSchedule.add(sdl.get(i).getSubjectNo());
			}
			SubjectService sjService = new SubjectService();
			ArrayList<Subject> sjl = sjService.getMySubject(setSchedule);
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/schedule/scheduleMain.jsp");
			request.setAttribute("sjl", sjl);
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
