package kr.or.iei.book.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.book.model.service.BookService;

/**
 * Servlet implementation class BookWriteFrmServlet
 */
@WebServlet(name = "BookWriteFrm", urlPatterns = { "/bookWriteFrm.do" })
public class BookWriteFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookWriteFrmServlet() {
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
		//3. 비즈니스로직
		BookService service = new BookService();
		HashMap<Integer, String> map = service.selectSubject();
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/book/bookWriteFrm.jsp");
		request.setAttribute("map", map);
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
