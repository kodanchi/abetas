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
 * Created by Mojahed on 3/15/2016.
 */
@WebServlet(name = "FetchMatchedRubricServlet", urlPatterns = {"/fetchRubrics"})
public class FetchMatchedRubricServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("/////////////////////////FetchMatchedRubricServlet ");
        response.setContentType("text/plain");

        try {


            String outId = request.getParameter("out");
            String PIId = request.getParameter("pi");
            String pId = request.getParameter("pid");
            String id = request.getParameter("cid");
            String corId = request.getParameter("cor");

            System.out.println("outId : "+outId);
            System.out.println("PIId : "+PIId);
            System.out.println("pId : "+pId);
            System.out.println("id : "+id);
            System.out.println("corId : "+corId);


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

            out.println("<div class=\"form-group\">\n" +
                    "                                        <label>First rubrics</label>\n" +
                    "                                        <input type=\"text\" class=\"form-control\" size=\"25\" name=\"firstR\" readonly value=\""+A+"\" required>\n" +
                    "                                    </div>\n" +
                    "                                    <div class=\"form-group\">\n" +
                    "                                        <label>Description</label>\n" +
                    "                                        <textarea class=\"form-control\" rows=\"3\" name=\"firstD\" required>"+E+"</textarea>\n" +
                    "                                    </div>\n" +
                    "                                    <div class=\"form-group\">\n" +
                    "                                        <label>Second rubrics</label>\n" +
                    "                                        <input type=\"text\" class=\"form-control\" size=\"25\" name=\"secondR\" readonly value=\""+B+"\" required>\n" +
                    "                                    </div>\n" +
                    "                                    <div class=\"form-group\">\n" +
                    "                                        <label>Description</label>\n" +
                    "                                        <textarea class=\"form-control\" rows=\"3\" name=\"secondD\" required>"+F+"</textarea>\n" +
                    "                                    </div>\n" +
                    "\n" +
                    "                                    <div class=\"form-group\">\n" +
                    "                                        <label>Third rubrics</label>\n" +
                    "                                        <input type=\"text\" class=\"form-control\" size=\"25\" name=\"thirdR\" readonly value=\""+C+"\" required>\n" +
                    "                                    </div>\n" +
                    "                                    <div class=\"form-group\">\n" +
                    "                                        <label>Description</label>\n" +
                    "                                        <textarea class=\"form-control\" rows=\"3\" name=\"thirdD\" required>"+G+"</textarea>\n" +
                    "                                    </div>\n" +
                    "\n" +
                    "                                    <div class=\"form-group\">\n" +
                    "                                        <label>Forth rubrics</label>\n" +
                    "                                        <input type=\"text\" class=\"form-control\" size=\"25\" name=\"forthR\" readonly value=\""+D+"\" required>\n" +
                    "                                    </div>\n" +
                    "                                    <div class=\"form-group\">\n" +
                    "                                        <label>Description</label>\n" +
                    "                                        <textarea class=\"form-control\" rows=\"3\" name=\"forthD\" required>"+H+"</textarea>\n" +
                    "                                    </div>\n");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}