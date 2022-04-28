package kr.or.iei.managerboard.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class UploadImagesServlet
 */
@WebServlet(name = "UploadImages", urlPatterns = { "/uploadImages.do" })
public class UploadImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImagesServlet() {
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
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"/upload/editor";
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		String filepath = mRequest.getFilesystemName("file"); //다운로드 아니니까 filepath만 필요
		//3.비즈니스로직
		//4.결과처리
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("/upload/editor/"+filepath); //파일경로 그대로 ajax에 되돌려줌
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
