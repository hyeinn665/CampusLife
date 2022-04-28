package kr.or.iei.freeboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.freeboard.model.service.FreeboardService;
import kr.or.iei.freeboard.model.vo.Freeboard;

/**
 * Servlet implementation class FreeboardViewServlet
 */
@WebServlet(name = "freeboardView", urlPatterns = { "/freeboardView.do" })
public class FreeboardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeboardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		FreeboardService service = new FreeboardService();
		Freeboard f = service.selectOneFreeboard(freeNo);
		RequestDispatcher view
		= request.getRequestDispatcher("/WEB-INF/views/freeboard/freeboardView.jsp");
		request.setAttribute("f", f);
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
