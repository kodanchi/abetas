package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ibrahim Abuaqel on 2/2/2016.
 */
@WebServlet(name = "AddObj",
        urlPatterns = {"/Add Program Objective"})
public class AddObj extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(request.getParameter("ObjValue")+"            AdObj Servlet ))))))))))))))))))))))))))))))))))))))))))))))))))))))");

        //ArrayList<String> data = new ArrayList<String>();
        if (request.getParameter("ObjValue").equals("null")) {
            System.out.println("#################################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");

            int id = 0;
            P_AS_Insert dba = new P_AS_Insert();
            //P_AS_Select dbaS=new P_AS_Select();
            try {
                //id=dbaS.selectProgram(request.getParameter("Pname"));
                dba.addObject(request.getParameter("Obj"), Integer.parseInt(request.getParameter("id")));
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
            response.setHeader("Location", "/program/index.jsp?page=ObjList&name=" + request.getParameter("name") + "&id=" + request.getParameter("id"));
        }
        else {
            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            System.out.println(request.getParameter("Objid")+"            AdObj Servlet IDIDIDIDIDDIIDID");

            int id = 0;
            P_AS_Update dba = new P_AS_Update();
            //P_AS_Select dbaS=new P_AS_Select();
            try {
                //id=dbaS.selectProgram(request.getParameter("Pname"));
                dba.updateObjective(Integer.parseInt(request.getParameter("Objid")), request.getParameter("Obj"));
                System.out.println(request.getParameter("id")+"           "+request.getParameter("name")+"          Update   AdObj Servlet");
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
            response.setHeader("Location", "/program/index.jsp?page=ObjList&name=" + request.getParameter("name") + "&id=" + request.getParameter("id"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
