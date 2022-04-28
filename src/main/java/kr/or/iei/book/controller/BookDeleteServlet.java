package kr.or.iei.book.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.book.model.service.BookService;
import kr.or.iei.book.model.vo.Book;

/**
 * Servlet implementation class BookDeleteServlet
 */
@WebServlet(name = "BookDelete", urlPatterns = { "/bookDelete.do" })
public class BookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDeleteServlet() {
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
		int result = service.deleteBook(bookNo);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			//파일이 존재하면 삭제
			if(b.getFilepath() != null) {
				String root = getServletContext().getRealPath("/");
				String deleteFile = root+"upload/book/"+b.getFilepath();
				File delFile = new File(deleteFile);
				delFile.delete();
			}
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "공지사항 삭제 완료");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/bookList.do?reqPage=1");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "공지사항 삭제 실패");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/bookView.do?bookNo="+bookNo);
		}
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
