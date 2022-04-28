package kr.or.iei.campuslife.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class InsertLectureServlet
 */
@WebServlet(name = "InsertLecture", urlPatterns = { "/insertLecture.do" })
public class InsertLectureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertLectureServlet() {
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
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		int durationTime = Integer.parseInt(request.getParameter("durationTime"));
		String startTimeA = request.getParameter("startTime1");
		int startTimeB = Integer.parseInt(request.getParameter("startTime2"));
		
		if(startTimeB+durationTime>12) {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "12교시를 넘을수 없습니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/insertSubject.do");
			view.forward(request, response);
		}else {
			Subject sj = new Subject();
			sj.setSubjectName(request.getParameter("subjectName"));
			sj.setGrade(Integer.parseInt(request.getParameter("grade")));
			sj.setStartTime(startTimeA+startTimeB);
			sj.setDurationTime(durationTime);
			sj.setSelectSubject(Integer.parseInt(request.getParameter("selectSubject")));
			sj.setProfNo(userNo);
			
			SubjectService sjService = new SubjectService();
			int sjResult = sjService.insertSubject(sj, userNo);
			int subjectNo = sjService.getSubjectNo(sj.getSubjectName(), sj.getStartTime());
			
			
			ScheduleService sdService = new ScheduleService();
			Schedule sd = new Schedule();
			sd.setSubjectNo(subjectNo);
			sd.setProfNo(sj.getProfNo());
			sd.setScheduleA(startTimeA);
			sd.setScheduleB(startTimeB);
			sd.setStuNo(userNo);
			
			int sdResult = sdService.insertMySchedule(sd, durationTime);
			

			if(sjResult*sdResult>0) {
				request.setAttribute("title", "성공");
				request.setAttribute("msg", "강의 등록에 성공하셨습니다");
				request.setAttribute("icon", "success");
			}else {
				request.setAttribute("title", "실패");
				request.setAttribute("msg", "강의 등록에 실패하셨습니다");
				request.setAttribute("icon", "error");
			}
			request.setAttribute("loc", "/mySchedule.do");
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
