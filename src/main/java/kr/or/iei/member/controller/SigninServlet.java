package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;


/**
 * Servlet implementation class SigninServlert
 */
@WebServlet(name = "Signin", urlPatterns = { "/signin.do" })
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
				request.setCharacterEncoding("utf-8");
				//2. 값추출
				String memberId = request.getParameter("memberId");
				String memberPw = request.getParameter("memberPw");
				Member member = new Member();
				member.setMemberId(memberId);
				member.setMemberPw(memberPw);
				//3. 비즈니스로직
				MemberService service = new MemberService();
				Member m = service.selectOneMember(member);
				//4. 결과처리
				RequestDispatcher view
				= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				if( m != null) {
					
					int hp = (m.getBlack1()+m.getBlack2()+m.getBlack3());
					
					if(hp >= 5) {
						request.setAttribute("title", "경고횟수 초과");
						request.setAttribute("msg", "경고횟수 5회를 초과하였습니다. 관리자에게 문의하세요");
						request.setAttribute("icon", "warning");
						
					}else {
				
						request.setAttribute("title", "로그인 성공");
						request.setAttribute("msg", "안녕하세요");
						request.setAttribute("icon", "success");
						
						
						HttpSession session = request.getSession();
						session.setAttribute("m", m);
			
					}
				}else {
					request.setAttribute("title", "로그인 실패");
					request.setAttribute("msg", "아이디 또는 비밀번호를 확인하세요");
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
