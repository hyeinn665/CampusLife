package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Msg;
import kr.or.iei.member.model.vo.Qna;

/**
 * Servlet implementation class QnaWriteServlet
 */
@WebServlet(name = "QnaWrite", urlPatterns = { "/qnaWrite.do" })
public class QnaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.인코딩
		request.setCharacterEncoding("utf-8");
		// 2.값추출
		String writer=request.getParameter("writer");
		String receiver=request.getParameter("receiver");
		String qnaTitle=request.getParameter("qnaTitle");
		String qnaContent=request.getParameter("qnaContent");
		Qna q=new Qna();
		q.setWriter(writer);
		q.setReceiver(receiver);
		q.setQnaTitle(qnaTitle);
		q.setQnaContent(qnaContent);
		// 3.비즈니스로직
		MemberService service=new MemberService();
		int result = service.insertQna(q);
		// 4.화면처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if (result > 0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "문의를 등록했습니다");
			request.setAttribute("icon", "success");
		} else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "문의 등록 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/");
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
