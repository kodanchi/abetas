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

            Integer userLvl = (Integer) request.getSession().getAttribute("userLvl");
            ArrayList<ArrayList<String>> programList = null;

            if(userLvl ==3){
                Integer eid = Integer.parseInt((String) request.getSession().getAttribute("userId"));
                programList = dbs.selectAllProgramsofEvaluatorToEvaluate(eid);
            }else {
                programList = dbs.selectAllProgramsToEvaluate();
            }


            PrintWriter out = response.getWriter();
            out.print("<a href=\"#programs"+"-"+tid+"\" class=\"list-group-item list-group-item-info\" data-toggle=\"collapse\"><i class=\"glyphicon glyphicon-chevron-right\"></i>Programs</a>");
            out.print(" <div class=\"list-group  collapse \" id=\"programs"+"-"+tid+"\" >");
            for (ArrayList<String> program : programList){
                out.print("<a href=\"#program-"+program.get(0)+"-"+tid+"\" class=\"list-group-item  \" " +
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
                        "                        $('#program-"+program.get(0)+"-"+tid+"').html(result);\n" +
                        "                        show('page', true);\n" +
                        "                        show('loading', false);\n" +
                        "                        scrollTo('program-"+program.get(0)+"-"+tid+"');" +
                        "                       }\n" +
                        "                    })\n" +
                        "                }\"" +
                        "data-toggle=\"collapse\">\n" +
                        "                            <i class=\"glyphicon glyphicon-chevron-right\"></i>"+program.get(1)+"\n" +
                        "                        </a>\n" +
                        "                        <div class=\"list-group collapse\" id=\"program-"+program.get(0)+"-"+tid+"\" name=\"program-"+program.get(0)+"-"+tid+"\">\n" +
                        "                        </div>" );
            }
            out.print("</div>");

            out.print("<script>");
            out.print("$(function() {\n" +
                    "\n" +
                    "                    $('.list-group-item').on('click', function() {\n" +
                    "                        $('.glyphicon', this)\n" +
                    "                                .toggleClass('glyphicon-chevron-right')\n" +
                    "                                .toggleClass('glyphicon-chevron-down');\n" +
                    "                    });\n" +
                    "\n" +
                    "                });");
            out.print("</script>");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
