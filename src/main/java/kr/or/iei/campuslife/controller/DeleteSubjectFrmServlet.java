package kr.or.iei.campuslife.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.campuslife.service.SubjectService;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.campuslife.vo.Subject;

/**
 * Servlet implementation class DeleteSubjectFrmServlet
 */
@WebServlet(name = "DeleteSubjectFrm", urlPatterns = { "/deleteSubjectFrm.do" })
public class DeleteSubjectFrmServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSubjectFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      
      HttpSession session = request.getSession(false);
      Member m = (Member)session.getAttribute("m");
      int userNo = m.getMemberNo();
      
      SubjectService service = new SubjectService();
      ArrayList<Subject> sjl = service.getSubjectList(userNo);
      ArrayList<Subject> sj = new ArrayList<Subject>();
      for(int i=0;i<sjl.size();i++) {
         if(sjl.get(i).getProfNo() == userNo) {
            sj.add(sjl.get(i));
         }
      }
      
      RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/schedule/deleteSubjectFrm.jsp");
      request.setAttribute("sj", sj);
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