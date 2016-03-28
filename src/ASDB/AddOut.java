package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ibrahim Abuaqel on 2/3/2016.
 */
@WebServlet(name = "AddOut",
        urlPatterns = {"/Add Student Outcome"})
public class AddOut extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################OOOOOOOOOOOOOOOOOOOOO");
        System.out.println(request.getParameter("id")+"           "+request.getParameter("name")+"        "+request.getParameter("OutValue")+"     AdOut Servlet");
        if (request.getParameter("OutValue").equals("null")) {
        //ArrayList<String> data = new ArrayList<String>();
        int id=0;
        P_AS_Insert dba=new P_AS_Insert();
        //P_AS_Select dbaS=new P_AS_Select();
        try {
            //id=dbaS.selectProgram(request.getParameter("Pname"));
            System.out.println("        "+request.getParameter("id")+"        ooooooooooooooo            "+request.getParameter("Out"));
            dba.addOutcome(request.getParameter("Out"),Integer.parseInt(request.getParameter("id")));
            Auditor.add((String)request.getSession().getAttribute("username"),"added new outcome (ID program: "+request.getParameter("id")+")");

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
        response.setHeader("Location", "/program/index.jsp?page=OutcomeList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
        }
        else {
            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            System.out.println(request.getParameter("Outid")+"            AdObj Servlet IDIDIDIDIDDIIDID");

            int id = 0;
            P_AS_Update dba = new P_AS_Update();
            //P_AS_Select dbaS=new P_AS_Select();
            try {
                //id=dbaS.selectProgram(request.getParameter("Pname"));
                dba.updateOutcome(Integer.parseInt(request.getParameter("Outid")), request.getParameter("Out"));
                System.out.println(request.getParameter("id")+"           "+request.getParameter("name")+"          Update   AdObj Servlet");
                Auditor.add((String)request.getSession().getAttribute("username"),"Updated outcome (ID : "+request.getParameter("Outid")+")");

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
            response.setHeader("Location", "/program/index.jsp?page=OutcomeList&name=" + request.getParameter("name") + "&id=" + request.getParameter("id"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
