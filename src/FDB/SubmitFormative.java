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
@WebServlet(name = "SubmitFormative",
        urlPatterns = {"/SubmitFormative"})
public class SubmitFormative extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(request.getParameter("WrittenRubricsV")+"            F Servlet ))))))))))))))))))))))))))))))))))))))))))))))))))))))");

        //ArrayList<String> data = new ArrayList<String>();
            System.out.println("#################################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");

            int id = 0;
            F_Update dba = new F_Update();
            //F_Select dbaS=new F_Select();
            try {
                //id=dbaS.selectProgram(request.getParameter("Pname"));
                    if (request.getParameter("evidence") != null) {
                        dba.updateFormF(request.getParameter("WrittenRubrics"), request.getParameter("Comments"), request.getParameter("Obstacles"), request.getParameter("Improvement"), request.getParameter("evidence"), Integer.parseInt(request.getParameter("Formative_ID")));
                    } else {
                        dba.updateFormF(request.getParameter("WrittenRubrics"), request.getParameter("Comments"), request.getParameter("Obstacles"), request.getParameter("Improvement"), null, Integer.parseInt(request.getParameter("Formative_ID")));
                    }
                dba.updateSubmitFormF(Integer.parseInt(request.getParameter("Formative_ID")));
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
