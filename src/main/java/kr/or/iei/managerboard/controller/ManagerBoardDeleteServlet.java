package kr.or.iei.managerboard.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.managerboard.model.service.ManagerBoardService;
import kr.or.iei.managerboard.model.vo.ManagerBoard;



/**
 * Servlet implementation class ManagerBoardDeleteServlet
 */
@WebServlet(name = "ManagerBoardDelete", urlPatterns = { "/managerBoardDelete.do" })
public class ManagerBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerBoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		int mbNo = Integer.parseInt(request.getParameter("mbNo"));
		//3.비즈니스로직
		ManagerBoardService service = new ManagerBoardService();
			//delete하기 전에 파일삭제 하기 위해 먼저 적어야 함!!!!!!!(글지우고 select해봤자 안뜨니까)
		ManagerBoard mb = service.getManagerBoard(mbNo);
		int result = service.managerBoardDelete(mbNo);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			//삭제 성공했을 때, 파일처리는 어떻게 할지
			//글삭제 실패했을 때 파일 지우면 안됨(글이 살아있을 수도 있으니까)
			if(mb.getFilepath() != null) {
				String root = getServletContext().getRealPath("/");
				String deleteFile = root+"upload/managerboard/"+mb.getFilepath();
				File delFile = new File(deleteFile);
				delFile.delete();
			}
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "공지사항 삭제 완료");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/managerBoardList.do?reqPage=1&category=전체");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "공지사항 삭제 실패");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/managerBoardView.do?mbNo="+mbNo);
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
