package kr.or.iei.managerboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.managerboard.model.service.ManagerBoardService;
import kr.or.iei.managerboard.model.vo.ManagerBoardViewData;

/**
 * Servlet implementation class ManagerBoardViewServlet
 */
@WebServlet(name = "ManagerBoardView", urlPatterns = { "/managerBoardView.do" })
public class ManagerBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerBoardViewServlet() {
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
		int mbNo = Integer.parseInt(request.getParameter("mbNo"));		
		//3. 비즈니스로직
		ManagerBoardService service = new ManagerBoardService();
		ManagerBoardViewData mbvd = service.selectManagerBoardView(mbNo);
		
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/managerboard/managerBoardView.jsp");
		request.setAttribute("mb", mbvd.getMb());
		request.setAttribute("commentList", mbvd.getCommentList());
		request.setAttribute("reCommentList", mbvd.getReCommentList());
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
