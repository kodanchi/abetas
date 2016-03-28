package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ibrahim Abuaqel on 3/9/2016.
 */
@WebServlet(name = "SetDefault",
        urlPatterns = {"/SetDefault"})
public class SetDefault extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");
        System.out.println("##########################################################     "+request.getParameter("cycle")+"     gggggggggggggggggg    "+request.getParameter("term"));

        String id = request.getParameter("cycle");
        C_AS_Update dba = new C_AS_Update();
        try {

            dba.updateDefault(Integer.parseInt(request.getParameter("term")));
            Auditor.add((String)request.getSession().getAttribute("username"),"Set term to default (Term ID: "+request.getParameter("term")+")");


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
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm&cycle="+id);
        }catch (NullPointerException e){
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
