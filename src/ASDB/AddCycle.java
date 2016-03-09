package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Created by Ibrahim Abuaqel on 2/7/2016.
 */
@WebServlet(name = "AddCycle",
        urlPatterns = {"/AddCycle"})
public class AddCycle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");

        AS_Insert dba = new AS_Insert();
        int id = 0;
        try {


            id = dba.addCycle();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
            /*PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");*/
        System.out.println("ttrttttttttttttttttttttttttt  id          " +String.valueOf(id));
            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
        try {
            //request.getSession().setAttribute("id",String.valueOf(id));
            //request.getRequestDispatcher("/cycle/index.jsp?page=addTerm").forward(request,response);
            response.sendRedirect("/cycle/index.jsp?page=piList&cycle="+id);
        }catch (NullPointerException e){
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
