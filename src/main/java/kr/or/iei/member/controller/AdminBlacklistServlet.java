package kr.or.iei.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;

/**
 * Servlet implementation class AdminBlacklistServlet
 */
@WebServlet(name = "AdminBlacklist", urlPatterns = { "/adminBlacklist.do" })
public class AdminBlacklistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBlacklistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
				request.setCharacterEncoding("utf-8");
				//2.값추출
				int memberNo = Integer.parseInt(request.getParameter("memberNo"));
				int black1 = Integer.parseInt(request.getParameter("black1"));
				int black2 = Integer.parseInt(request.getParameter("black2"));
				int black3 = Integer.parseInt(request.getParameter("black3"));
				//3.비즈니스로직
				MemberService service = new MemberService();
				int result = service.blacklistUpdate(memberNo,black1,black2,black3);
				
				//4.화면처리
				response.sendRedirect("/adminPage.do");
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
