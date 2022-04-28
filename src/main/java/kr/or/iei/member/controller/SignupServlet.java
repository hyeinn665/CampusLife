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
 * Servlet implementation class SignupServlet
 */
@WebServlet(name = "Signup", urlPatterns = { "/signup.do" })
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member m = new Member();
		m.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
		m.setMemberId(request.getParameter("memberId"));
		m.setMemberPw(request.getParameter("memberPw"));
		m.setMemberName(request.getParameter("memberName"));
		m.setAge(Integer.parseInt(request.getParameter("age")));
		m.setGrade(request.getParameter("grade"));
		m.setPhone(request.getParameter("phone"));
		m.setMemberAddr(request.getParameter("address"));
	
		MemberService service = new MemberService();
		int result = service.insertMember(m);
	
		RequestDispatcher view 
		= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "환영합니다");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "회원가입에 실패했습니다.");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/");
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
