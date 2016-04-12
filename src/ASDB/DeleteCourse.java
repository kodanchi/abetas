package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteCourse",
        urlPatterns = {"/Delete Course"})
public class DeleteCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        P_AS_Delete dba=new P_AS_Delete();
        try {
            dba.deleteCourse(request.getParameter("Courseid"));
            Auditor.add((String)request.getSession().getAttribute("username"),"deleted course (Course ID : "+request.getParameter("Courseid")+")");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        response.setHeader("Location", "/program/index.jsp?page=CoursesList&name=" + request.getParameter("name") + "&id=" + request.getParameter("id"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
