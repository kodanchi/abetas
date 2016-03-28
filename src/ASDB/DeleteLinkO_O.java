package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ibrahim Abuaqel on 2/6/2016.
 */
@WebServlet(name = "DeleteLinkO_O",
        urlPatterns = {"/Delete Link Objective and Outcome"})
public class DeleteLinkO_O extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        System.out.println(request.getParameter("id")+"           ");
        //ArrayList<String> data = new ArrayList<String>();
        int id=0;
        C_AS_Delete dba=new C_AS_Delete();
        //C_AS_Select dbaS=new C_AS_Select();
        try {
            //id=dbaS.selectProgram(request.getParameter("Pname"));
            dba.deleteLinkO_O(Integer.parseInt(request.getParameter("Linkid")));
            Auditor.add((String)request.getSession().getAttribute("username"),"Deleted Outcome/Objective link (ID : "+request.getParameter("Linkid")+")");

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
        response.setHeader("Location", "/program/index.jsp?page=LinkOutObj&name=" + request.getParameter("name") +
                "&id=" + request.getParameter("id"));
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
