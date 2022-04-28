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
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class DeleteMySubjectServlet
 */
@WebServlet(name = "DeleteMySubject", urlPatterns = { "/deleteMySubject.do" })
public class DeleteMySubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMySubjectServlet() {
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
		int subjectNo = Integer.parseInt(request.getParameter("subjectNo"));
		
		ScheduleService service = new ScheduleService();
		int result = service.deleteMySubject(userNo, subjectNo); 
		int result2=service.deleteMySubject2(userNo, subjectNo);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "강의 삭제에 성공하셨습니다");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "강의 삭제에 실패하셨습니다");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/mySubject.do");
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
