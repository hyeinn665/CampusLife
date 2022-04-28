package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class CheckNoServlet
 */
@WebServlet(name = "CheckNo", urlPatterns = { "/checkNo.do" })
public class CheckNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckNoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int memberNo = Integer.parseInt(request.getParameter("checkNo"));
		
		MemberService service = new MemberService();
		Member m = service.selectOneMember(memberNo);
		
		RequestDispatcher view 
		= request.getRequestDispatcher("/WEB-INF/views/member/checkNo.jsp");
		if(m != null) {
			request.setAttribute("result", false);
		}else {
			request.setAttribute("result", true);
		}
		request.setAttribute("memberNo", memberNo);
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
