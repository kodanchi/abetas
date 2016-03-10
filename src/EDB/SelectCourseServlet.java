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
@WebServlet(name = "SelectCourseServlet", urlPatterns = {"/SelectCourseServlet"})
public class SelectCourseServlet extends HttpServlet {
    private E_Select dbs;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/////////////////////////SelectCourseServlet ");
        response.setContentType("text/plain");
        dbs = new E_Select();
        int tid = Integer.parseInt(request.getParameter("tid"));
        int pid = Integer.parseInt(request.getParameter("pid"));
        int pIid = Integer.parseInt(request.getParameter("pIid"));
        String cid =request.getParameter("cid");
        try {
            PrintWriter out = response.getWriter();


            ArrayList<ArrayList<String>> SectionList = null;
            ArrayList<String> PIRubrics = null;
            ArrayList<String> PIResults = null;

            float[] results = new float[]{0,0,0,0};
            //String[] styles = new String[]{"#b87333","silver","gold","#e5e4e2"};


            if(cid.equals("Overall")){


            }else {
                System.out.println("inside else");

                SectionList = dbs.selectSectionsCourseOfSummativeToEvaluate(tid,pid,cid);
                PIRubrics = dbs.selectRubricsToEvaluate(pIid,pid,tid);
                PIResults = dbs.selectSummativeRubricResultsOfCourseToEvaluate(pIid,pid,tid,cid);




                for (String sRubric : PIResults){
                    System.out.println("PIResults: "+sRubric);
                    if(sRubric.equals(PIRubrics.get(0))){
                        results[0]++;
                    }else if(sRubric.equals(PIRubrics.get(1))){
                        results[1]++;
                    }else if(sRubric.equals(PIRubrics.get(2))){
                        results[2]++;
                    }else if(sRubric.equals(PIRubrics.get(3))){
                        results[3]++;
                    }
                }



                out.print("<label for=\"sectionListSelect\" >Section: </label>");
                out.print("<select id=\"sectionListSelect\" onchange=\"onSectionChange("+tid+","+pid+","+pIid+",'"+cid+"')\" class=\"\" data-live-search=\"true\">");
                out.print(" <option value=\"overall\" data-tokens=\"overall\">Overall</option>");
                for (ArrayList<String> section : SectionList){
                    out.print("<option value=\""+section.get(0)+"\" data-tokens=\""+section.get(0)+"\">"+section.get(0)+"</option>\n");
                }
                out.print("</select>\n");
                /*out.println("<select id=\"sectionListSelect\" onchange=\"onSectionChange("+tid+","+pid+","+pIid+","+cid+")\" >\n" +
                        "                        <option>gg</option>\n" +
                        "                        <option>dd</option>\n" +
                        "                    </select>");*/


                out.print("<script type=\"text/javascript\">\n" );

                out.print("var cResults = [\n");
                for (int i=0;i < PIRubrics.size();i++){
                    out.print(results[i]!= 0 ? ( results[i] * 100 ) / PIResults.size() : 0 );
                    if(i!=PIRubrics.size()-1) out.print(",\n");
                }
                out.print("];\n");
                out.print("popChart(\"Course: "+cid+"\",\"rgba(169, 169, 169, 0.8)\",cResults);\n");
                out.print("function courseOverall(){\n");
                out.print("var rs = [\n");

                for (int i=0;i < PIRubrics.size();i++){
                    out.print(results[i]!= 0 ? ( results[i] * 100 ) / PIResults.size() : 0 );
                    if(i!=PIRubrics.size()-1) out.print(",\n");
                }

                out.print("];\n");
                out.print("popChart('Course: "+cid+"',\"rgba(169, 169, 169, 0.8)\",rs);\n");
                out.print("}\n");

                out.println("</script>\n");



                out.print("<script>\n" +
                        "                            function onSectionChange(tid,pid,pIid,cid){\n" +
                        "                                var cl = document.getElementById(\"sectionListSelect\");\n" +
                        "                                var sid = cl.options[cl.selectedIndex].value;\n" +
                        "                                if(sid == \"overall\"){\n" +
                        "                                    courseOverall();\n" +
                        "                                    $(\"#evidence\").hide();" +
                        "                                }else {\n" +
                        "                                    show('page', false);\n" +
                        "                                    show('loading', true);\n" +
                        "                                    $.ajax({\n" +
                        "                                        type: 'POST',\n" +
                        "                                        data: {\n" +
                        "                                            tid: tid,\n" +
                        "                                            pid: pid,\n" +
                        "                                            cid: cid,\n" +
                        "                                            pIid: pIid,\n" +
                        "                                            sid: sid" +
                        "                                        },\n" +
                        "                                        url: '/SelectSectionServlet',\n" +
                        "                                        success: function (result) {\n" +
                        "                                            $('#evidence').html(result);\n" +
                        "                                            $(\"#evidence\").show();" +
                        "                                            show('page', true);\n" +
                        "                                            show('loading', false);\n" +
                        "\n" +
                        "                                        }\n" +
                        "\n" +
                        "                                    })\n" +
                        "                                }\n" +
                        "                            }\n" +
                        "                        </script>");


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
