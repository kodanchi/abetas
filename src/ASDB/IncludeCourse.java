package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ibrahim Abuaqel on 2/14/2016.
 */
@WebServlet(name = "IncludeCourse",
        urlPatterns = {"/includeCourse"})
public class IncludeCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("#########################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        System.out.println("####################################"+request.getParameter("Code")+"######################      "+request.getParameter("programID"));
        String[] code = request.getParameterValues("Code");
        AS_Insert dba = new AS_Insert();
        AS_Select sdba = new AS_Select();
        String id = request.getParameter("cycle");
        String Termid = request.getParameter("term");

        int programID = 0;

        try {
            //System.out.println("ttrttttttttttttttttttttttttt  Program name          " + request.getParameter("programName") + "ttrttttttttttttttttttttttttt           ");
            for (int i=0;i<code.length; i++) {
                System.out.println(code[i]);
                dba.includeCourse(code[i],Integer.parseInt(Termid));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

            /*PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");*/
        //System.out.println("ttrttttttttttttttttttttttttt  Programid          " + programID + "ttrttttttttttttttttttttttttt           ");
            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
        try {
            //request.getRequestDispatcher("/cycle/index.jsp?page=includeCourse").forward(request, response);
            response.sendRedirect("/cycle/index.jsp?page=includeCourse&cycle="+id+"&term="+Termid+"&programID="+request.getParameter("programID"));

        } catch (NullPointerException e) {
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
