package kr.or.iei.book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.book.model.service.BookService;
import kr.or.iei.book.model.vo.BookComment;

/**
 * Servlet implementation class BookCommentUpdateServlet
 */
@WebServlet(name = "BookCommentUpdate", urlPatterns = { "/bookCommentUpdate.do" })
public class BookCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookCommentUpdateServlet() {
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
		int bcNo =Integer.parseInt(request.getParameter("bcNo"));
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		String bcContent = request.getParameter("bcContent");
		BookComment bc = new BookComment();
		bc.setBcNo(bcNo);
		bc.setBcContent(bcContent);
		//3. 비즈니스로직
		BookService service = new BookService();
		int result = service.updateBookComment(bc);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "댓글 수정 완료!");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "댓글 수정 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/bookView.do?bookNo="+bookNo);
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
