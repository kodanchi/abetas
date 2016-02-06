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
 * Created by Ibrahim Abuaqel on 2/1/2016.
 */
@WebServlet(name = "AddProgram",
        urlPatterns = {"/Add Program"})
public class AddProgram extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");
        System.out.println(request.getParameter("id")+"           "+request.getParameter("name")+"             AdProg Servlet");
        if (request.getParameter("ProgramName").equals("null")) {
            System.out.println("#########################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");

        AS_Insert dba=new AS_Insert();
        AS_Select dbaS=new AS_Select();
        int id=0;
        ArrayList<String> data = new ArrayList<String>();
        try {
            data=dba.addProgramm(request.getParameter("Pname"), request.getParameter("Pmission"));
            id=dbaS.selectProgramID(request.getParameter("Pname"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        //out.println("logo: " + request.getParameter("logo"));
        out.println("H");
//System.out.println("ttrttttttttttttttttttttttttt name     "+ data.get(0)+"    id          " +String.valueOf(id));
        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        response.setHeader("Location", "http://localhost:8081/program/index.jsp?page=ObjList&name="+data.get(0)+"&id="+String.valueOf(id));
        }
        else {
            AS_Select dbaS=new AS_Select();
            ArrayList<String> data = new ArrayList<String>();

            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            System.out.println(request.getParameter("Objid")+"            AdObj Servlet IDIDIDIDIDDIIDID");

            String name="";
            AS_Update dba = new AS_Update();
            //AS_Select dbaS=new AS_Select();
            try {
                //id=dbaS.selectProgram(request.getParameter("Pname"));
                System.out.println(request.getParameter("id")+"           "+request.getParameter("Pname")+"         "+request.getParameter("Pmission")+"        Update   AdObj Servlet");
                dba.updateProgram(Integer.parseInt((request.getParameter("id"))), request.getParameter("Pname"),request.getParameter("Pmission"));

                name=dbaS.selectProgramName(Integer.parseInt((request.getParameter("id"))));

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
            response.setHeader("Location", "http://localhost:8081/program/index.jsp?page=ObjList&name="+name+"&id="+request.getParameter("id"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}