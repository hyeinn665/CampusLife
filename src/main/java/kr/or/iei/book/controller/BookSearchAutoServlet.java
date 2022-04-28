package kr.or.iei.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.iei.book.model.service.BookService;
import kr.or.iei.book.model.vo.Book;
import kr.or.iei.book.model.vo.BookSearchData;

/**
 * Servlet implementation class BookSearchAutoServlet
 */
@WebServlet(name = "BookSearchAuto", urlPatterns = { "/bookSearchAuto.do" })
public class BookSearchAutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearchAutoServlet() {
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
		String search = request.getParameter("search");
		int searchSelect = Integer.parseInt(request.getParameter("searchSelect"));
		//3. 비즈니스로직
		BookService service = new BookService();
		ArrayList<Book> list = null;
		if(searchSelect==1) {
			list = service.searchBookName(search);
		}else if(searchSelect==2) {
			list = service.searchBookWriter(search);
		}else {
			list = service.searchBookSubject(search);
		}
		//4. 결과처리
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		new Gson().toJson(list,out);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
