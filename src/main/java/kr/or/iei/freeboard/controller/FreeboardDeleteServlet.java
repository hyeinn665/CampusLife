package kr.or.iei.freeboard.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.freeboard.model.service.FreeboardService;
import kr.or.iei.freeboard.model.vo.Freeboard;


/**
 * Servlet implementation class FreeboardDeleteServlet
 */
@WebServlet(name = "FreeboardDelete", urlPatterns = { "/freeboardDelete.do" })
public class FreeboardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeboardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		FreeboardService service = new FreeboardService();
		Freeboard f = service.getFreeboard(freeNo);
		int result = service.freeboardDelete(freeNo);
		RequestDispatcher view
		= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			if(f.getFilePath() != null) {
				String root = getServletContext().getRealPath("/");
				String deleteFile = root+"upload/freeboard/"+f.getFilePath();
				File delFile = new File(deleteFile);
				delFile.delete();
			}
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "공지사항 삭제 완료");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/freeboardList.do?reqPage=1");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "공지사항 삭제 실패");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/freeboardView.do?freeNo="+freeNo);
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
