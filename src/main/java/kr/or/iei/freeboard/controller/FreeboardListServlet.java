package kr.or.iei.freeboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.freeboard.model.service.FreeboardService;
import kr.or.iei.freeboard.model.vo.Freeboard;
import kr.or.iei.freeboard.model.vo.FreeboardPageData;

/**
 * Servlet implementation class FreeboardListServlet
 */
@WebServlet(name = "FreeboardList", urlPatterns = { "/freeboardList.do" })
public class FreeboardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeboardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	int reqPage = Integer.parseInt(request.getParameter("reqPage"));
	FreeboardService service = new FreeboardService();
	FreeboardPageData npd = service.selectFreeboardList(reqPage);
	//ArrayList<Freeboard> list = service.selectFreeboardList();
	RequestDispatcher view 
	= request.getRequestDispatcher("/WEB-INF/views/freeboard/FreeboardList.jsp");
	//여기서 화면으로 보내줘야될객체가 어떤객체죠? 맞아요 여기 좀전에 response가있었는데
	request.setAttribute("list", npd.getList());
	request.setAttribute("pageNavi", npd.getPageNavi());
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
