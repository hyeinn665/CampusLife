package kr.or.iei.book.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.InternalFrameUI;

import kr.or.iei.book.model.service.BookService;
import kr.or.iei.book.model.vo.Book;
import kr.or.iei.book.model.vo.BookSearchData;

/**
 * Servlet implementation class BookSearchServlet
 */
@WebServlet(name = "BookSearch", urlPatterns = { "/bookSearch.do" })
public class BookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearchServlet() {
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
		String search = request.getParameter("searchBook");
		int searchSelect = Integer.parseInt(request.getParameter("searchSelect"));
		//3. 비즈니스로직
		BookService service = new BookService();
		BookSearchData bsd = service.searchBook(search,searchSelect);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/book/bookSearch.jsp");
		request.setAttribute("list", bsd.getList());
		request.setAttribute("search", bsd.getSearch());
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
