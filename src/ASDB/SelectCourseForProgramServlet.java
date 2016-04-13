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
 * SelectCourseForProgramServlet is used by ajax to retrieve courses of selected program.
 */
@WebServlet(name = "SelectCourseForProgramServlet", urlPatterns = {"/SelectCourseForProgramServlet"})
public class SelectCourseForProgramServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String tid = request.getParameter("tid");
        String pid = request.getParameter("pid");
        out.print("<script src=\"/js/jquery-2.2.0.min.js\" type=\"text/javascript\"></script>\n" +
                "<script src=\"/js/bootstrap-select.min.js\" type=\"text/javascript\"></script>\n" +
                "<script src=\"/js/bootstrap.js\" type=\"text/javascript\"></script>\n");

        if(pid!=null) {
            C_AS_Select dbs = new C_AS_Select();
            try {
                ArrayList<String> rs = dbs.selectCourseForProgram(Integer.parseInt(pid), Integer.parseInt(tid));

                if (rs.size()!=0) {
                    out.print("program</p>");
                    out.print("<select name=\"Code\"  class=\"selectpicker\" multiple>");

                    for (int i = 0; i < rs.size(); i++) {

                        out.print("<option");
                        out.print(" value=\"" + rs.get(i).substring(0, rs.get(i).indexOf(':')) + "\" >" + rs.get(i));
                        out.print("</option>");

                    }
                    out.print("</select>");

                    out.print("<button class=\"btn btn-primary\" type=\"submit\"> Submit </button><br><br><br>");
                }else {
                    out.print("There are no courses in this program<br><br>");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        out.print("\n" +
                "<script>\n" +
                "    $(function() {\n" +
                "        $('.selectpicker').selectpicker();\n" +
                "    });\n" +
                "</script>");




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
