package kr.or.iei.book.controller;

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

import kr.or.iei.book.model.service.BookService;
import kr.or.iei.book.model.vo.Book;

/**
 * Servlet implementation class BookUpdateServlet
 */
@WebServlet(name = "BookUpdate", urlPatterns = { "/bookUpdate.do" })
public class BookUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		//2-1. 파일업로드 경로지정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/book";		
		//2-2. 파일업로드 최대크기 지정(10MB)
		int maxSize = 10*1024*1024;
		//2-3. request -> MultipartRequest로 변환(서버에 파일업로드시점)
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "utf-8", new DefaultFileRenamePolicy());
		//request -> MultipartRequest로 변환되면서 파일이 업로드
		//2-3. 값추출
		int bookNo = Integer.parseInt(mRequest.getParameter("bookNo"));
		int memberNo = Integer.parseInt(mRequest.getParameter("memberNo"));
		String bookName = mRequest.getParameter("bookName");
		String bookWriter = mRequest.getParameter("bookWriter");
		String publisher = mRequest.getParameter("publisher");
		int listPrice = Integer.parseInt(mRequest.getParameter("listPrice"));
		int subjectNo = Integer.parseInt(mRequest.getParameter("subjectNo"));
		String writtenTrace = mRequest.getParameter("writtenTrace");
		String tornTrace = mRequest.getParameter("tornTrace");
		String discolor = mRequest.getParameter("discolor");
		String nameTrace = mRequest.getParameter("nameTrace");
		int hopePrice = Integer.parseInt(mRequest.getParameter("hopePrice"));
		String file = mRequest.getFilesystemName("file");
		String bookComment = mRequest.getParameter("bookComment");
		//수정 전 파일을 유지했으면 stay, 수정 전 파일을 삭제했으면 delete
		String status = mRequest.getParameter("status");
		String oldFilepath = mRequest.getParameter("oldFilepath");
		if(status.equals("delete")) {//수정 전 파일을 삭제한 경우 서버에서 파일 삭제
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();		
		}else if(oldFilepath != null) {
			file = oldFilepath;				
		}
		Book b = new Book();
		b.setBookNo(bookNo);
		b.setMemberNo(memberNo);
		b.setBookName(bookName);
		b.setBookWriter(bookWriter);
		b.setPublisher(publisher);
		b.setListPrice(listPrice);
		b.setHopePrice(hopePrice);
		b.setSubjectNo(subjectNo);
		b.setWrittenTrace(writtenTrace);
		b.setTornTrace(tornTrace);
		b.setDiscolor(discolor);
		b.setNameTrace(nameTrace);
		b.setFilepath(file);
		b.setBookComment(bookComment);
		//3. 비즈니스로직
		BookService service = new BookService();
		int result = service.updateBook(b);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");			
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "수정 완료");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "수정 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/bookView.do?bookNo="+bookNo);
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
