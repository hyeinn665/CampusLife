package kr.or.iei.main.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.book.model.service.BookService;
import kr.or.iei.book.model.vo.Book;
import kr.or.iei.freeboard.model.service.FreeboardService;
import kr.or.iei.freeboard.model.vo.Freeboard;
import kr.or.iei.freeboard.model.vo.MainPageData;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(name = "Main", urlPatterns = { "/main.do" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		BookService service = new BookService();
		ArrayList<Book> list = service.selectBookList();
		
		FreeboardService freeService = new FreeboardService();
		ArrayList<Freeboard> freeList = freeService.mainListFree();
		MainPageData mpd = new MainPageData(list, freeList);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/main.jsp");
		request.setAttribute("list", mpd.getList());
		request.setAttribute("freeList", mpd.getFreeList());
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
