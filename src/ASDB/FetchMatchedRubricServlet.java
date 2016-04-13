package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * FetchMatchedRubricServlet is used by ajax to fetch rubrics descriptions when outcome, performance indicator and course
 * are matched to a result in the database>
 */
@WebServlet(name = "FetchMatchedRubricServlet", urlPatterns = {"/fetchRubrics"})
public class FetchMatchedRubricServlet extends HttpServlet {
    /**
     * write the four rubric name
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");

        try {


            String outId = request.getParameter("out");
            String PIId = request.getParameter("pi");
            String pId = request.getParameter("pid");
            String id = request.getParameter("cid");
            String corId = request.getParameter("cor");




            String A="";
            String B="";
            String C="";
            String D="";
            String E="";
            String F="";
            String G="";
            String H="";

            C_AS_Select dbs = new C_AS_Select();

            ArrayList<String> rubrics = dbs.selectMatchedRubrics(Integer.valueOf(outId),Integer.valueOf(PIId),
                    Integer.valueOf(pId),corId);

            if(rubrics != null){
                E =rubrics.get(0);
                F =rubrics.get(1);
                G =rubrics.get(2);
                H =rubrics.get(3);
            }




            C_AS_Select nselect = new C_AS_Select();

            ArrayList<String> rsss = nselect.selectRubricNames(Integer.parseInt(id));

            A=rsss.get(0);
            B=rsss.get(1);
            C=rsss.get(2);
            D=rsss.get(3);




            PrintWriter out = response.getWriter();

            out.println("<script>\n" +
                    "                        $(function(){\n" +
                    "                             document.getElementById(\"firstD\").innerHTML=\""+A+"\";\n" +
                    "                             document.getElementById(\"secondD\").innerHTML=\""+B+"\";\n" +
                    "                             document.getElementById(\"thirdD\").innerHTML=\""+C+"\";\n" +
                    "                             document.getElementById(\"forthD\").innerHTML=\""+D+"\";\n" +
                    "                        });\n" +
                    "                    </script>" +
                    "");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
