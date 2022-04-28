package kr.or.iei.campuslife.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.campuslife.service.SubjectService;

/**
 * Servlet implementation class DeleteLetureServlet
 */
@WebServlet(name = "DeleteLeture", urlPatterns = { "/deleteLeture.do" })
public class DeleteLetureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteLetureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int subjectNo = Integer.parseInt(request.getParameter("subjectNo"));
		SubjectService service = new SubjectService();
		
		int result = service.deleteLecture(subjectNo);
		
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
		request.setAttribute("loc", "/deleteSubjectFrm.do");
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
