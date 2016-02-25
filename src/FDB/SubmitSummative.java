package FDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ibrahim Abuaqel on 2/23/2016.
 */
@WebServlet(name = "SubmitSummative",
        urlPatterns = {"/SubmitSummative"})
public class SubmitSummative extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(request.getParameter("Summative_ID")+"            SubmitSummative Servlet ))))))))))))))))))))))))))))))))))))))))))))))))))))))");

        //ArrayList<String> data = new ArrayList<String>();
        System.out.println("#################################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");

        int id = 0;
        F_Update dba = new F_Update();
        F_Delete dbd = new F_Delete();
        F_Insert dbi = new F_Insert();
        //AS_Select dbaS=new AS_Select();
        try {
            //id=dbaS.selectProgram(request.getParameter("Pname"));
            if (request.getParameter("evidence") != null) {
                dba.updateFormS(request.getParameter("evidence"), Integer.parseInt(request.getParameter("Summative_ID")));

            } else {
                dba.updateFormS(null, Integer.parseInt(request.getParameter("Summative_ID")));
            }
            dba.updateSubmitFormS(Integer.parseInt(request.getParameter("Summative_ID")));
            int size=0;
            size=Integer.parseInt(request.getParameter("studentsNumber"));
            dbd.deleteSummRub(Integer.parseInt(request.getParameter("Summative_ID")));
            for (int i =0; i < size; i++){

                dbi.addSummativeRubric(Integer.parseInt(request.getParameter("Summative_ID")),request.getParameter(("optionsRadios"+i)),Integer.parseInt(request.getParameter(("SID"+i))));
            }
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
        //response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        //response.setHeader("Location", "http://localhost:8081/program/index.jsp?page=OutcomeList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
        response.sendRedirect( "/form/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
