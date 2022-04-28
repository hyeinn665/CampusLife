package kr.or.iei.managerboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.managerboard.model.service.ManagerBoardService;
import kr.or.iei.managerboard.model.vo.ManagerBoardPageData;

/**
 * Servlet implementation class SearchManageBoardServlet
 */
@WebServlet(name = "SearchManagerBoard", urlPatterns = { "/searchManagerBoard.do" })
public class SearchManagerBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchManagerBoardServlet() {
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
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String searchType  = request.getParameter("searchType");
		String keyword = request.getParameter("keyword"); 
		
		//3.비즈니스로직
		ManagerBoardService service = new ManagerBoardService();
		ManagerBoardPageData mbpd = service.selectManagerBoardList(reqPage,searchType,keyword);
		
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/managerboard/managerBoardList.jsp");
		request.setAttribute("list", mbpd.getList());
		request.setAttribute("pageNavi", mbpd.getPageNavi());
		request.setAttribute("category", "전체");
		
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
