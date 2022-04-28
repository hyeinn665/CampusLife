package kr.or.iei.managerboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.managerboard.model.service.ManagerBoardService;
import kr.or.iei.managerboard.model.vo.ManagerBoard;

/**
 * Servlet implementation class ManagerBoardUpdateFrmServlet
 */
@WebServlet(name = "ManagerBoardUpdateFrm", urlPatterns = { "/managerBoardUpdateFrm.do" })
public class ManagerBoardUpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerBoardUpdateFrmServlet() {
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
		int mbNo = Integer.parseInt(request.getParameter("mbNo"));
		//3.비즈니스로직
		ManagerBoardService service = new ManagerBoardService();
		//새로운 글이 아니라 기존 글을 수정하는 것이므로
		ManagerBoard mb = service.getManagerBoard(mbNo);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/managerboard/managerBoardUpdateFrm.jsp");
		request.setAttribute("mb", mb);
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
