package kr.or.iei.managerboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.iei.managerboard.model.service.ManagerBoardService;
import kr.or.iei.managerboard.model.vo.ManagerBoard;


/**
 * Servlet implementation class ManagerBoardWriteServlet
 */
@WebServlet(name = "ManagerBoardWrite", urlPatterns = { "/managerBoardWrite.do" })
public class ManagerBoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerBoardWriteServlet() {
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
		//2-1. 파일을 먼저 업로드
			//1) 파일업로드 경로 지정
		String root = getServletContext().getRealPath("/"); //webapp폴더 절대경로를 구함
		String saveDirectory = root+"upload/managerboard";
		//System.out.println("파일저장경로 : "+saveDirectory);////
			//2) 파일업로드 최대용량 지정
		int maxSize = 10*1024*1024;	//최대 10mb로 설정
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			
		//2-2. 값을 추출
		String mbTitle = mRequest.getParameter("mbTitle");
		String mbWriter = mRequest.getParameter("mbWriter");
		String mbContent = mRequest.getParameter("mbContent");
		String filename = mRequest.getOriginalFileName("file");	//화면에서 업로드하는 실제 파일이름
		String filepath = mRequest.getFilesystemName("file");	//서버에 저장되는 파일이름
		String category = mRequest.getParameter("category");
		
		ManagerBoard mb = new ManagerBoard(); 
		mb.setMbTitle(mbTitle);
		mb.setMbWriter(mbWriter);
		mb.setMbContent(mbContent);
		mb.setFilename(filename);
		mb.setFilepath(filepath);
		mb.setCategory(category);

		//3.비즈니스로직
		ManagerBoardService service = new ManagerBoardService();
		int result = service.insertManagerBoard(mb);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "공지사항이 등록되었습니다.");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "공지사항 등록 중 문제가 발생했습니다.");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/managerBoardList.do?reqPage=1&category=전체");
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
