package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ibrahim Abuaqel on 2/10/2016.
 */
@WebServlet(name = "DeleteStudent",
        urlPatterns = {"/DeleteStudent"})
public class DeleteStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        System.out.println(request.getParameter("section")+"    <<section ID       ");
        //ArrayList<String> data = new ArrayList<String>();
        int id=0;
        C_AS_Delete dba=new C_AS_Delete();
        //C_AS_Select dbaS=new C_AS_Select();
        try {
            //id=dbaS.selectProgram(request.getParameter("Pname"));
            dba.deleteStudent(Integer.parseInt(request.getParameter("S_ID")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        //out.println("logo: " + request.getParameter("logo"));
        //out.println(id+"       fggfdggfdgdgdsffdgdgffgggdfdgdffd");

        System.out.println(request.getParameter("section")+"                <<section            ");
        //response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        //response.setHeader("Location", "http://localhost:8081/program/index.jsp?page=OutcomeList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
        //response.setHeader("Location", "http://localhost:8081/cycle/index.jsp?page=cycleList");
        try {
            //request.getSession().setAttribute("id",String.valueOf(id));
            //request.getRequestDispatcher("/cycle/index.jsp?page=piList").forward(request,response);
            response.sendRedirect("/cycle/index.jsp?page=studentList&cycle="+request.getParameter("cycle")+"&term="+
                    request.getParameter("term")+"&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+
                    "&courseName="+request.getParameter("courseName")+"&section="+request.getParameter("section"));
        }catch (NullPointerException e){
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
