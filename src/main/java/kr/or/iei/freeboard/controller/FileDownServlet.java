package kr.or.iei.freeboard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.freeboard.model.service.FreeboardService;
import kr.or.iei.freeboard.model.vo.Freeboard;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet(name = "FileDown", urlPatterns = { "/fileDown.do" })
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownServlet() {
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
		String root = getServletContext().getRealPath("/");
		String downLoadFile = root+"upload/freeboard/"+f.getFilePath();
		FileInputStream fis = new FileInputStream(downLoadFile);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		String resFilename = new String(f.getFileName().getBytes("UTF-8"),"ISO-8859-1");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+resFilename);
		while(true) {
			int read = bis.read();
			if(read != -1) {
				bos.write(read);
			}else {
				break;
			}
		}
		bos.close();
		bis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
