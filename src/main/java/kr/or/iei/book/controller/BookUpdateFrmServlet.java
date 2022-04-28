package kr.or.iei.book.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.book.model.service.BookService;
import kr.or.iei.book.model.vo.Book;
import kr.or.iei.book.model.vo.BookSubject;

/**
 * Servlet implementation class BookUpdateFrmServlet
 */
@WebServlet(name = "BookUpdateFrm", urlPatterns = { "/bookUpdateFrm.do" })
public class BookUpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookUpdateFrmServlet() {
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
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		//3. 비즈니스로직
		BookService service = new BookService();
		Book b = service.getBook(bookNo);
		HashMap<Integer, String> map = service.selectSubject();
		BookSubject bs = new BookSubject(map, b);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/book/bookUpdateFrm.jsp");
		request.setAttribute("b", bs.getB());
		request.setAttribute("map", bs.getMap());
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
