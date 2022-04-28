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
 * Servlet implementation class InsertCommentServlet
 */
@WebServlet(name = "InsertBookComment", urlPatterns = { "/insertBookComment.do" })
public class InsertBookCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBookCommentServlet() {
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
		BookComment bc = new BookComment();
		bc.setBcWriter(request.getParameter("bcWriter"));
		bc.setBookRef(Integer.parseInt(request.getParameter("bookRef")));
		bc.setBcRef(Integer.parseInt(request.getParameter("bcRef")));
		bc.setBcContent(request.getParameter("bcContent"));
		bc.setBcSecret(request.getParameter("bcSecret"));
		//3. 비즈니스로직
		BookService service = new BookService();
		int result = service.insertBookComment(bc);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "댓글 등록 완료");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "댓글 등록 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/bookView.do?bookNo="+bc.getBookRef());
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
