package kr.or.iei.freeboard.controller;

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
import kr.or.iei.member.model.vo.Member;


/**
 * Servlet implementation class FreeboardWriteServlet
 */
@WebServlet(name = "FreeboardWrite", urlPatterns = { "/freeboardWrite.do" })
public class FreeboardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeboardWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
	        String root = this.getServletContext().getRealPath("/");
	        String saveDirectory = root + "upload/freeboard";
	        System.out.println("파일저장경로 : " + saveDirectory);
	        int maxSize = 10485760;
	        MultipartRequest mRequest 
	        = new MultipartRequest(request,
	        					saveDirectory,
	        					maxSize,
	        					"UTF-8",
	        					new DefaultFileRenamePolicy()
	        					);
	        int memberNo = Integer.parseInt(mRequest.getParameter("memberNo"));
	        String freeTitle = mRequest.getParameter("freeTitle");
	        String freeContent = mRequest.getParameter("freeContent");
	        String filename = mRequest.getOriginalFileName("file");
	        String filepath = mRequest.getFilesystemName("file");
	        
	        Freeboard f = new Freeboard();
	        
	        f.setMemberNo(memberNo);
	        f.setFreeTitle(freeTitle);
	        f.setFreeContent(freeContent);
	        f.setFileName(filename);
	        f.setFilePath(filepath);
	        
	        FreeboardService service = new FreeboardService();
	        int result = service.insertFreeboard(f);
	        
	        RequestDispatcher view 
	        = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
	        if (result > 0) {
	            request.setAttribute("title", "성공");
	            request.setAttribute("msg", "등록되었습니다.");
	            request.setAttribute("icon", "success");
	        } else {
	            request.setAttribute("title", "실패");
	            request.setAttribute("msg", "등록 중 문제가 발생했습니다.");
	            request.setAttribute("icon", "error");
	        }

	        request.setAttribute("loc", "/freeboardList.do?reqPage=1");
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
