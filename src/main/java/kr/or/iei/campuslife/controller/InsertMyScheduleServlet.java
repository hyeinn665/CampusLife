package kr.or.iei.campuslife.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.campuslife.service.ScheduleService;
import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.review.model.vo.Subject2;
import kr.or.iei.campuslife.vo.Schedule;

/**
 * Servlet implementation class InsertMyScheduleServlet
 */
@WebServlet(name = "InsertMySchedule", urlPatterns = { "/insertMySchedule.do" })
public class InsertMyScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertMyScheduleServlet() {
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
		int subjectNo=Integer.parseInt(request.getParameter("subjectNo"));
		
		Schedule sd = new Schedule();
		sd.setSubjectNo(Integer.parseInt(request.getParameter("subjectNo")));
		sd.setProfNo(Integer.parseInt(request.getParameter("profNo")));
		String startTime = request.getParameter("startTime");
		String scheduleA = (String.valueOf(startTime.charAt(0)));
		int scheduleB = Integer.parseInt(startTime.substring(1, 2));
		sd.setScheduleA(scheduleA);
		sd.setScheduleB(scheduleB);
		sd.setStuNo(userNo);
		int duration = Integer.parseInt(request.getParameter("durationTime")); 
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		ScheduleService service = new ScheduleService();
		Subject2 s = new Subject2();
		s.setMemberNo(userNo);
		s.setSubjectNo(subjectNo);
		
		int scout = service.getScout(userNo, scheduleA, scheduleB);
		if(scout>0) {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "해당 시간엔 이미 강의가 있습니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/insertSchedule.do");
			view.forward(request, response);
		}else {
			int result = service.insertMySchedule(sd, duration);
			
			if(result>0) {
				request.setAttribute("title", "성공");
				request.setAttribute("msg", "강의 등록에 성공하셨습니다");
				request.setAttribute("icon", "success");
				MemberService service2 = new MemberService();
				int result2 = service2.insertCourse(s);
			}else {
				request.setAttribute("title", "실패");
				request.setAttribute("msg", "강의 등록에 실패하셨습니다");
				request.setAttribute("icon", "error");
			}
			request.setAttribute("loc", "/insertSchedule.do");
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
