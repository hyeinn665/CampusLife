package kr.or.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;


/**
 * Servlet implementation class AllMemberServlet
 */
@WebServlet(name = "AllMember", urlPatterns = { "/allMember.do" })
public class AllMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.화면에서 보내준 값 추출
		//3.비즈니스로직
		Member m = new Member();
		MemberService service = new MemberService();
		ArrayList<Member> list = service.selectAllMember();
		//4.결과처리
		//결과를 처리할 화면이 동적인페이지인지.. 정적페이지인지 구분
		//동적페이지 -> 서블릿에서 갖고 있는 데이터를 화면에서 사용해야하는지
		//정적페이지 -> 서블릿에서 갖고 있는 데이터를 화면에서 사용하지 않아도 되는 경우
		//동적페이지 -> 서블릿에서 갖고 있는 데이터를 화면에서 사용하지 않아도 되는 경우
		//동적페이지 결과처리방법
		//4-1. 결과를 처리할 jsp 지정
		RequestDispatcher view
		=request.getRequestDispatcher("/WEB-INF/views/allMember.jsp");
		//4-2. 화면구성에 필요한 데이터를 등록
		
		request.setAttribute("list", list);
		//4-3. 페이지 이동
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
