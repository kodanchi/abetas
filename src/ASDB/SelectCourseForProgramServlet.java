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
 * Created by Mojahed on 3/14/2016.
 */
@WebServlet(name = "SelectCourseForProgramServlet", urlPatterns = {"/SelectCourseForProgramServlet"})
public class SelectCourseForProgramServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("/////////////////////////SelectCourseForProgramServlet ");
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String cid = request.getParameter("cid");
        String tid = request.getParameter("tid");
        String pid = request.getParameter("pid");
        out.print("<script src=\"/js/jquery-2.2.0.min.js\" type=\"text/javascript\"></script>\n" +
                "<script src=\"/js/bootstrap-select.min.js\" type=\"text/javascript\"></script>\n" +
                "<script src=\"/js/bootstrap.js\" type=\"text/javascript\"></script>\n");


/*

        out.print(
                "<form name=\"linkForm\" method=\"POST\" action=\"/includeCourse\">\n" +
                "        <input name=\"cycle\" value=\""+cid+"\" hidden/>\n" +
                "        <input name=\"term\" value=\""+tid+"\" hidden/>\n" +
                "\n" +
                "        <input type=\"hidden\" name=\"programID\" value=\""+pid+"\">\n" +
                "\n");


*/

        if(pid!=null) {
            C_AS_Select bselect = new C_AS_Select();
            try {
                ArrayList<String> rs = bselect.selectCourseForProgram(Integer.parseInt(pid), Integer.parseInt(tid));

                if (rs.size()!=0) {


                    //out.print("<p>Select courses to be evaluated for");
                    if(pid!=null) {
                        C_AS_Select ssselect = new C_AS_Select();
                        try {
                            String Pname = ssselect.selectProgramName(Integer.parseInt(pid));
                            //out.print(Pname);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    out.print("program</p>");
                    out.print("<select name=\"Code\"  class=\"selectpicker\" multiple>");

                    for (int i = 0; i < rs.size(); i++) {
                        System.out.println(rs.get(i).substring(0, rs.get(i).indexOf(':')));


                        out.print("<option");
                        out.print(" value=\"" + rs.get(i).substring(0, rs.get(i).indexOf(':')) + "\" >" + rs.get(i));
                        out.print("</option>");

                    }
                    out.print("</select>");

                    out.print("<button class=\"btn btn-primary\" type=\"submit\"> Submit </button><br><br><br>");
                }else {
                    out.print("There are no courses in this program<br><br>");
                    //Display error massage
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("no program");
        }

        out.print("\n" +
                "<script>\n" +
                "    $(function() {\n" +
                "        $('.selectpicker').selectpicker();\n" +
                "    });\n" +
                "</script>");


        /*out.print("</form>");
        out.print("<!-- Table -->\n" +
                "        <table class=\"table table-hover table-striped table-bordered text-center\">\n" +
                "        <tr>\n" +
                "\n" +
                "        <th>Code</th>\n" +
                "        <th>Name</th>\n" +
                "        <th>Level</th>\n" +
                "        <th>Course Info.</th>\n" +
                "        <th>Delete</th>\n" +
                "\n" +
                "        </tr>");



        if(tid!=null && pid!=null) {

            C_AS_Select aselect = new C_AS_Select();
            try {
                ArrayList<ArrayList<String>> rs = aselect.selectCourseForTerm(Integer.parseInt(pid),Integer.parseInt(tid));
                ArrayList<String> rsRow;

                for (int i = 0; i < rs.size(); i++) {
                    rsRow = new ArrayList<String>();
                    rsRow = rs.get(i);
                    out.print("<tr>");
                    for (int j = 2; j < rsRow.size(); j++) {
                        out.print("<td>" + rsRow.get(j) + "</td>");
                    }
                    out.print("<td>" +
                            "                            <form method=\"post\" action=\"index.jsp\">\n" +
                            "                            <input name=\"page\" value=\"CourseInfo\" hidden />\n" +
                            "                            <input name=\"cycle\" value=\""+cid+"\" hidden />\n" +
                            "                            <input name=\"term\" value=\""+tid+"\" hidden />\n" +
                            "                            <input name=\"courseCode\" value=\""+rsRow.get(2)+"\" hidden />\n" +
                            "                            <input name=\"programID\" value=\""+pid+"\" hidden />\n" +
                            "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                            "                            </form>" +
                            "                            </td><td>" +
                            "                            <form method=\"post\" action=\"/DeleteIC\">\n" +
                            "                            <input name=\"cycle\" value=\""+cid+"\" hidden />\n" +
                            "                            <input name=\"term\" value=\""+tid+"\" hidden />\n" +
                            "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                            "                            <input name=\"Code\" value=\"" + rsRow.get(2) + "\" hidden />\n" +
                            "                            <input name=\"programID\" value=\""+pid+"\" hidden />\n" +
                            "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-link btn-T \"><i class=\"fui-trash icon30\"></i></button>\n" +
                            "                        </form></td>" +
                            "</tr>");
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("  yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy           ");
        }else {
            System.out.println("  gsgsgsg    gsgsggssdfgs       djskvdsj    sgsgs   sgsgsgsg   fsdsdg            ");
        }


        out.print("</table>\n" +
                "\n" +
                "        <a class=\"btn btn-default pull-right\" href=\"index.jsp?page=addTerm&cycle="+cid+"\">Cancel</a>");


        if(pid!=null){
            out.print("<a class=\"btn btn-primary pull-right\" href=\"index.jsp?page=LinkPIOutList&cycle="+cid+
                    "&term="+tid+"&programID="+pid+"\">Next</a>\n");
        }
*/




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
