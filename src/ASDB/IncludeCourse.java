package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * IncludeCourse is to include a course to a term to be evaluated in the cycle.
 */
@WebServlet(name = "IncludeCourse",
        urlPatterns = {"/includeCourse"})
public class IncludeCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] code = request.getParameterValues("Code");
        C_AS_Insert dba = new C_AS_Insert();
        String id = request.getParameter("cycle");
        String Termid = request.getParameter("term");


        try {
            for (int i=0;i<code.length; i++) {
                dba.includeCourse(code[i],Integer.parseInt(Termid));
                Auditor.add((String)request.getSession().getAttribute("username"),"Included course to term (Course ID: "
                        +request.getParameter("Courseid")+", Term ID: "+Termid+")");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            response.sendRedirect("/cycle/index.jsp?page=includeCourse&cycle="+id+"&term="+Termid+"&programID="+
                    request.getParameter("programID"));

        } catch (NullPointerException e) {
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
