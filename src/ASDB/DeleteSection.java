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
@WebServlet(name = "DeleteSection",
        urlPatterns = {"/DeleteSection"})
public class DeleteSection extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        System.out.println(request.getParameter("section")+"    <<section ID       ");
        //ArrayList<String> data = new ArrayList<String>();
        int id=0;
        AS_Delete dba=new AS_Delete();
        //AS_Select dbaS=new AS_Select();
        try {
            //id=dbaS.selectProgram(request.getParameter("Pname"));
            dba.deleteSection(Integer.parseInt(request.getParameter("section")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        //out.println("logo: " + request.getParameter("logo"));
        //out.println(id+"       fggfdggfdgdgdsffdgdgffgggdfdgdffd");

        System.out.println(request.getParameter("cycle")+"                vdgfsg            ");
        //response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        //response.setHeader("Location", "http://localhost:8081/program/index.jsp?page=OutcomeList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
        //response.setHeader("Location", "http://localhost:8081/cycle/index.jsp?page=cycleList");
        try {
            //request.getSession().setAttribute("id",String.valueOf(id));
            //request.getRequestDispatcher("/cycle/index.jsp?page=piList").forward(request,response);
            response.sendRedirect("/cycle/index.jsp?page=CourseInfo&cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+
                    "&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode"));
        }catch (NullPointerException e){
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
