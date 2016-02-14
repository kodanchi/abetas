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
@WebServlet(name = "AddLinkObjOut",
        urlPatterns = {"/Add Link Outcome and Objective"})
public class AddLinkObjOut extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################LLLLLLLLLLLLLL");
        System.out.println(request.getParameter("id")+"           "+request.getParameter("name")+"             AdLinkO_O Servlet");
        //ArrayList<String> data = new ArrayList<String>();
        if (request.getParameter("ObjLinkValue").equals("null")) {
            int id=0;
        AS_Insert dba=new AS_Insert();
        //AS_Select dbaS=new AS_Select();
        try {
            //id=dbaS.selectProgram(request.getParameter("Pname"));
            System.out.println("        "+request.getParameter("id")+"        LLLLLLLLLLLLLLLLL      Obj      "+Integer.parseInt(request.getParameter("Obj"))+"        Out            "+Integer.parseInt(request.getParameter("Out")));
            dba.addLinkObj_Out(Integer.parseInt(request.getParameter("Out")),Integer.parseInt(request.getParameter("Obj")),Integer.parseInt(request.getParameter("id")));
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
        response.setHeader("Location", "/program/index.jsp?page=LinkOutObj&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
        }
        else {
            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            System.out.println(request.getParameter("Outid")+"            AdObj Servlet IDIDIDIDIDDIIDID");

            int id = 0;
            AS_Update dba = new AS_Update();
            //AS_Select dbaS=new AS_Select();
            try {
                //id=dbaS.selectProgram(request.getParameter("Pname"));
                dba.updateLinkObj_Out(Integer.parseInt(request.getParameter("Linkid")), Integer.parseInt(request.getParameter("Obj")), Integer.parseInt(request.getParameter("Out")));
                System.out.println(Integer.parseInt(request.getParameter("Linkid"))+"           "+Integer.parseInt(request.getParameter("ObjLinkValue").substring(0, request.getParameter("ObjLinkValue").indexOf(':')))+"                     "+Integer.parseInt(request.getParameter("OutLinkValue").substring(0, request.getParameter("OutLinkValue").indexOf(':')))+"          Update   AdObj Servlet");
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
            response.setHeader("Location", "/program/index.jsp?page=LinkOutObj&name=" + request.getParameter("name") + "&id=" + request.getParameter("id"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
