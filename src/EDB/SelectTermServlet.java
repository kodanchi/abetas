package EDB;

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
 * Created by Mojahed on 2/27/2016.
 */
@WebServlet(name = "SelectTermServlet", urlPatterns = {"/SelectTermServlet"})
public class SelectTermServlet extends HttpServlet {
    private E_Select dbs;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/////////////////////////SelectTermServlet tis : "+request.getParameter("tid")+" ,,");
        response.setContentType("text/plain");
        dbs = new E_Select();
        int tid = Integer.parseInt(request.getParameter("tid"));
        try {
            ArrayList<ArrayList<String>> programList = dbs.selectAllProgramsToEvaluate();

            PrintWriter out = response.getWriter();
            for (ArrayList<String> program : programList){
                out.print("<a href=\"dddd.jsp\" class=\"list-group-item \"" +
                        "onclick=\"new function (){\n" +
                        "                    show('page', false);\n" +
                        "                    show('loading', true);\n" +
                        "                    $.ajax({\n" +
                        "                       type: 'POST',\n" +
                        "                       data:{" +
                        "                           pid: "+program.get(0)+","+
                        "                           tid: "+tid+
                        "                       },\n" +
                        "                       url:'/SelectProgramServlet',\n" +
                        "                       success: function(result){\n" +
                        "                        $('#pIList').html(result);\n" +
                        "                        show('page', true);\n" +
                        "                        show('loading', false);\n" +
                        "                       }\n" +
                        "                    })\n" +
                        "                }\"" +
                        "\"> "+program.get(1)+"</a>");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
