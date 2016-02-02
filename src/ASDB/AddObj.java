package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Ibrahim Abuaqel on 2/2/2016.
 */
@WebServlet(name = "AddObj",
        urlPatterns = {"/Add Program Objective"})
public class AddObj extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");
        //ArrayList<String> data = new ArrayList<String>();
        int id=0;
        AS_Insert dba=new AS_Insert();
        AS_Select dbaS=new AS_Select();
        try {
            id=dbaS.selectProgram(request.getParameter("Pname"));
            dba.addObject(request.getParameter("Obj"),id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        //out.println("logo: " + request.getParameter("logo"));
        out.println(id+"       fggfdggfdgdgdsffdgdgffgggdfdgdffd");

        //System.out.println(data.get(0)+"                vdgfsg            "+data.get(1));
        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        //back to saif page
        //response.setHeader("Location", "http://localhost:8081/program/index.jsp?page=add&label="+label+"&FK="+);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
