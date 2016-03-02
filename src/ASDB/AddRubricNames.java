package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ibrahim Abuaqel on 3/2/2016.
 */
@WebServlet(name = "AddRubricNames",
        urlPatterns = {"/AddRubricNames"})
public class AddRubricNames extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################OOOOOOOOOOOOOOOOOOOOO");
        System.out.println("        "+request.getParameter("RubricID")+"     Rubric Servlet");
        if (request.getParameter("RubricID").equals("null")) {
            //ArrayList<String> data = new ArrayList<String>();
            String id = request.getParameter("cycle");
            AS_Insert dba=new AS_Insert();
            //P_AS_Select dbaS=new P_AS_Select();
            try {
                //id=dbaS.selectProgram(request.getParameter("Pname"));
                System.out.println("        ooooooooooooooo            "+request.getParameter("firstR"));
                dba.addRubricNames(request.getParameter("firstR"),request.getParameter("secondR"),request.getParameter("thirdR"),request.getParameter("forthR"),Integer.parseInt(id));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            //out.println(id+"       fggfdggfdgdgdsffdgdgffgggdfdgdffd");

            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm&cycle="+id);
        }
        /*else {
            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            System.out.println(request.getParameter("RubricID")+"            Rubric Servlet IDIDIDIDIDDIIDID");

            int id = 0;
            AS_Update dba = new AS_Update();
            //P_AS_Select dbaS=new P_AS_Select();
            try {
                //id=dbaS.selectProgram(request.getParameter("Pname"));
                dba.updateRubricNames(request.getParameter("firstR"),request.getParameter("secondR"),request.getParameter("thirdR"),request.getParameter("forthR"), Integer.parseInt(request.getParameter("RubricID")));
                System.out.println(request.getParameter("id")+"          Update   Rubric Servlet");
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
            //response.setHeader("Location", "/program/index.jsp?page=OutcomeList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm&cycle="+id);

        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
