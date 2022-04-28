package kr.or.iei.freeboard.controller;

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

import kr.or.iei.freeboard.model.service.FreeboardService;
import kr.or.iei.freeboard.model.vo.Freeboard;

/**
 * Servlet implementation class FreeboardUpdateServlet
 */
@WebServlet(name = "FreeboardUpdate", urlPatterns = { "/freeboardUpdate.do" })
public class FreeboardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeboardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/freeboard";
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request,
														saveDirectory,
														maxSize,
														"UTF-8",
														new DefaultFileRenamePolicy()
														);
		
		int freeNo = Integer.parseInt(mRequest.getParameter("freeNo"));
		String freeTitle = mRequest.getParameter("freeTitle");
		String freeContent = mRequest.getParameter("freeContent");
		
		String filename = mRequest.getOriginalFileName("file");
		String filepath = mRequest.getFilesystemName("file");
		
		String status = mRequest.getParameter("status");
		
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");
		if(status.equals("delete")) {
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
		}else if(oldFilename != null) {
			filename = oldFilename;
			filepath = oldFilepath;
			
		}
		
		Freeboard f = new Freeboard();
		f.setFreeNo(freeNo);
		f.setFreeTitle(freeTitle);
		f.setFreeContent(freeContent);
		f.setFileName(filename);
		f.setFilePath(filepath);
		
		FreeboardService service = new FreeboardService();
		int result = service.freeboardUpdate(f);
		
		RequestDispatcher view
		= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "수정 완료");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "수정 실패");
			request.setAttribute("icon", "error");
		}
		  request.setAttribute("loc", "/freeboardView.do?freeNo="+freeNo);
		//request.setAttribute("loc", "/freeboardView.do?freeNo="+f.getFreeNo());
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
