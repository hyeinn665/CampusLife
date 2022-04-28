package kr.or.iei.managerboard.controller;

import java.io.File;
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
 * Servlet implementation class ManagerBoardUpdateFrmServlet
 */
@WebServlet(name = "ManagerBoardUpdate", urlPatterns = { "/managerBoardUpdate.do" })
public class ManagerBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerBoardUpdateServlet() {
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
		//파일업로드 준비
		//2-1.파일업로드 경로 설정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/managerboard";
		//2-2. 업로드 파일 최대크기 지정
		int maxSize = 10*1024*1024;
		//2-3. request -> MultipartRequest로 변환(이때가 파일 업로드되는 시점!!)
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		int mbNo = Integer.parseInt(mRequest.getParameter("mbNo"));
		String mbTitle = mRequest.getParameter("mbTitle");
		String category = mRequest.getParameter("category");
		String mbContent = mRequest.getParameter("mbContent");
			
		//filename, filepath는 새 첨부파일 있으면 파일명으로, 새 첨부파일이 없으면 null
		String filename = mRequest.getOriginalFileName("file");
		String filepath = mRequest.getFilesystemName("file");
		//수정전 파일을 유지했으면 stay, 기존파일을 삭제했으면 delete
		String status = mRequest.getParameter("status");
		//수정전 파일이 존재했으면 값있음, 수정전 파일이 없으면 null
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");
	
		
		//status가 파일 여부 5가지 경우 구분해주니까
		if(status.equals("delete")) {	//수정전 파일을 삭제한 경우 서버에서 파일 삭제
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
			//(파일처리 경우의 수 기준) 1-2 1-3 2-1 2-2 만족
		}else if(oldFilename !=null){
			filename = oldFilename;
			filepath = oldFilepath;
			//나머지 1-1 만족
		}
		ManagerBoard mb = new ManagerBoard();
		mb.setMbNo(mbNo);
		mb.setMbTitle(mbTitle);
		mb.setCategory(category);
		mb.setMbContent(mbContent);
		mb.setFilename(filename);
		mb.setFilepath(filepath);
		//3.비즈니스로직
		ManagerBoardService service = new ManagerBoardService();
		int result = service.managerBoardUpdate(mb);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "공지사항 수정 완료");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "공지사항 수정 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/managerBoardView.do?mbNo="+mb.getMbNo());
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
