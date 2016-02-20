package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ibrahim Abuaqel on 2/4/2016.
 */
@WebServlet(name = "AddCourses",
        urlPatterns = {"/Add Courses"})
public class AddCourses extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(request.getParameter("Code")+"           "+request.getParameter("Cname")+"             AdObj Servlet");
        //ArrayList<String> data = new ArrayList<String>();
        if (request.getParameter("CourseName").equals("null")) {
            int id=0;
        AS_Insert dba=new AS_Insert();
        //AS_Select dbaS=new AS_Select();
        try {
            //id=dbaS.selectProgram(request.getParameter("Pname"));
            dba.addCourse(request.getParameter("Code"),request.getParameter("Cname"),Integer.parseInt(request.getParameter("level")),0,Integer.parseInt(request.getParameter("id")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        //out.println("logo: " + request.getParameter("logo"));
        //out.println(id+"       fggfdggfdgdgdsffdgdgffgggdfdgdffd");

        //System.out.println(data.get(0)+"                vdgfsg            "+data.get(1));
        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        //response.setHeader("Location", "http://localhost:8081/program/index.jsp?page=OutcomeList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
        response.setHeader("Location", "/program/index.jsp?page=CoursesList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
        }
        else {
            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            System.out.println(request.getParameter("Outid")+"            AdObj Servlet IDIDIDIDIDDIIDID");

            int id = 0;
            AS_Update dba = new AS_Update();
            //AS_Select dbaS=new AS_Select();
            try {
                //id=dbaS.selectProgram(request.getParameter("Pname"));
                System.out.println(request.getParameter("Code")+"           "+request.getParameter("Cname")+"          Update   AdObj Servlet");
                dba.updateCourse(Integer.parseInt(request.getParameter("Cid")), request.getParameter("Code"),request.getParameter("Cname"),Integer.parseInt(request.getParameter("level")));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            //out.println(id+"       fggfdggfdgdgdsffdgdgffgggdfdgdffd");

            //System.out.println(data.get(0)+"                vdgfsg            "+data.get(1));
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            //response.setHeader("Location", "http://localhost:8081/program/index.jsp?page=OutcomeList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
            response.setHeader("Location", "/program/index.jsp?page=CoursesList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
