package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ibrahim Abuaqel on 2/15/2016.
 */
@WebServlet(name = "AddStudent",
        urlPatterns = {"/AddStudent"})
public class AddStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("NValue").equals("null")) {
            System.out.println("#########################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE  SID  "+  request.getParameter("Student_ID"));

            AS_Insert dba = new AS_Insert();
            String id = request.getParameter("cycle");
            String Termid = request.getParameter("term");
            System.out.println("##########################################################  Sname  "+request.getParameter("Sname")+"          ");

            try {
                //System.out.println("ttrttttttttttttttttttttttttt  Program id          " + request.getParameter("programID") + "ttrttttttttttttttttttttttttt           ");
                System.out.println("@@@@@@@@@@@@@@@@@@@    2222");
                System.out.println(request.getParameter("programName"));
                System.out.println(request.getParameter("programID"));
                System.out.println(request.getParameter("courseCode"));
                System.out.println(request.getParameter("courseName"));
                System.out.println(request.getParameter("F_ID"));
                System.out.println(request.getParameter("programName"));
                System.out.println("@@@@@@@@@@@@@@@@@@@    2222");

                System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWW  " + request.getParameter("Sname")+"     "+Termid+"    "+request.getParameter("Student_ID")+"      "+request.getParameter("courseCode")+"     "+request.getParameter("F_ID")+"        "+Termid);

                dba.addStudent(request.getParameter("Sname"),Integer.parseInt(request.getParameter("Student_ID")),Integer.parseInt(request.getParameter("section")));
                //Display error message.
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");*/
            System.out.println("ttrttttttttttttttttttttttttt  Rubric id          ttrttttttttttttttttttttttttt           ");
            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
            try {
                response.sendRedirect("/cycle/index.jsp?page=studentList&cycle="+id+"&term="+Termid+"&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+"&courseName="+request.getParameter("courseName")+"&section="+request.getParameter("section"));
                //request.getRequestDispatcher("/cycle/index.jsp?page=LinkPIOutList&programID="+request.getParameter("programID")).forward(request, response);
            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }
        } else {
            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");

            AS_Insert dba = new AS_Insert();
            String id = request.getParameter("cycle");
            String Termid = request.getParameter("term");
            System.out.println("##########################################################  Sname  "+request.getParameter("Sname")+"          ");

            try {
                //System.out.println("ttrttttttttttttttttttttttttt  Program id          " + request.getParameter("programID") + "ttrttttttttttttttttttttttttt           ");
                dba.addStudent(request.getParameter("Sname"),Integer.parseInt(request.getParameter("Student_ID")),Integer.parseInt(request.getParameter("section")));
                //Display error message.
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");*/
            System.out.println("ttrttttttttttttttttttttttttt  Rubric id          ttrttttttttttttttttttttttttt           ");
            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
            try {
                response.sendRedirect("/cycle/index.jsp?page=studentList&cycle="+id+"&term="+Termid+"&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+"&courseName="+request.getParameter("courseName")+"&F_ID="+request.getParameter("F_ID")+"&programName="+request.getParameter("programName"));
                //request.getRequestDispatcher("/cycle/index.jsp?page=LinkPIOutList&programID="+request.getParameter("programID")).forward(request, response);
            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
