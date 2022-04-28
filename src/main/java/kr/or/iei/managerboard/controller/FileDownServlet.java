package kr.or.iei.managerboard.controller;

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

import kr.or.iei.managerboard.model.service.ManagerBoardService;
import kr.or.iei.managerboard.model.vo.ManagerBoard;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet(name = "FileDown2", urlPatterns = { "/fileDown2.do" })
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
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		int mbNo = Integer.parseInt(request.getParameter("mbNo"));
		//3.비즈니스로직
		//filename이랑filepath가 다 필요하니까 게시글 하나 통째로 가져옴
		//그냥 상세보기로 글 하나 조회하는건 '조회수'가 올라가므로 getNotice service를 새로 만듬
		ManagerBoardService service = new ManagerBoardService();
		ManagerBoard mb = service.getManagerBoard(mbNo);
		//4.결과처리
		//파일과 현재 서블릿을 연결 (파일 우선 서버에 가져옴)
		String root = getServletContext().getRealPath("/");//여기까지 webapp폴더
		String downLoadFile = root+"upload/managerboard/"+mb.getFilepath();
		//파일 -> 서블릿 -> 사용자
		//파일과 서블릿 연결
		//파일을 서블릿으로 읽어오기 위한 스트림생성
		FileInputStream fis = new FileInputStream(downLoadFile);//주스트림
		BufferedInputStream bis = new BufferedInputStream(fis);	//보조스트림
		//읽어온 파일을 사용자에게 전달할 스트림 생성
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		//파일명처리(크롬기준 파일명 정상적으로 다운되게 함)
		String resFilename = new String(mb.getFilename().getBytes("UTF-8"),"ISO-8859-1");
		//파일 다운로드를 위한 HTTP Header 설정 
		response.setContentType("application/octet-stream");//페이지 이동안하고 파일만 줄 때 타입
		response.setHeader("Content-Disposition", "attachment;filename="+resFilename); //파일명
		//파일전송
		while(true) {
			int read = bis.read();	//bis : 서블릿이랑 파일이 연결 -> read : 파일 데이터 읽어옴
			if(read != -1) {		//파일 데이터 나눠서 계속 보내다가 다 불러오면 -1 -> while문 나가라
				bos.write(read);	//bos : 서블릿이랑 브라우저가 연결 -> write : 불러온 값을 보여줌
			}else {		//-1이면 파일 다 불러온 것이므로 끝(파일 남아있으면 파일 '데이터'를 가져옴)
				break;	//데이터 없으면 끝내기
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
