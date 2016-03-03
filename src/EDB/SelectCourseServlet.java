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

            int[] results = new int[]{0,0,0,0};
            String[] styles = new String[]{"#b87333","silver","gold","#e5e4e2"};


            if(cid.equals("Overall")){
                PIRubrics = dbs.selectRubricsToEvaluate(pIid,pid,tid);
                PIResults = dbs.selectSummativeRubricResultsToEvaluate(pIid,pid,tid);



                for (String sRubric : PIResults){
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

                out.print("");

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



                out.print("<div class=\"row\">\n");
                out.print("<select id=\"sectionList\" onchange=\"onSectionChange("+tid+","+pid+","+pIid+","+cid+")\" class=\"\" data-live-search=\"true\">");
                out.print(" <option value=\"Overall\" data-tokens=\"overall\">Overall</option>");
                for (ArrayList<String> section : SectionList){
                    out.print(" <option value=\""+section.get(0)+"\" data-tokens=\""+section.get(0)+"\">"+section.get(0)+"</option>");
                }
                out.print("</select>");
                        out.print("                            </div>\n" +
                        "                        <div class=\"row\">\n" +
                        "                            <div id=\"chart_wrap\"><div id=\"chart\"></div></div>\n" +
                        "                            <!--Load the AJAX API-->\n" +
                        "                            " +
                        "                            <script type=\"text/javascript\">\n" +
                        "\n" +

                        "\n"
                        /*"                                $(window).on(\"throttledresize\", function (event) {\n" +
                        "                                    var options = {\n" +
                        "                                        title: \"Percentage of students in each rubrics for this PI,total No. of students : <%=PIResults.size()%>\",\n" +
                        "                                        bar: {groupWidth: \"95%\"},\n" +
                        "                                        legend: { position: \"right\" },\n" +
                        "                                        width: '100%',\n" +
                        "                                        height: '100%',\n" +
                        "                                        vAxis: {\n" +
                        "                                            minValue: 0,\n" +
                        "                                            maxValue: 100,\n" +
                        "                                            format: '#\\'%\\''\n" +
                        "                                        },\n" +
                        "                                        animation: {\n" +
                        "                                            duration: 1000,\n" +
                        "                                            easing: 'out'\n" +
                        "                                        }\n" +
                        "                                    };\n" +
                        "\n" +
                        "                                    var arrData = [\n" +
                        "                                        [\"Rubric\",  {label: 'Parentage. of Students', type: 'number'}, { role: 'style' } ],\n" +
                        "                                        <%\n" +
                        "                                        for (int i=0;i < PIRubrics.size();i++){\n" +
                        "                                            out.print(\"[\\\"\"+PIRubrics.get(i)+\"\\\", \"+ (results[i]!= 0 ? ( results[i] * 100 ) / PIResults.size() : 0 )+\", \\\"\"+styles[i]+\"\\\"]\\n\");\n" +
                        "                                            if(i!=PIRubrics.size()-1) out.print(\",\");\n" +
                        "                                        }\n" +
                        "                                        %>\n" +
                        "\n" +
                        "                                    ];\n" +
                        "                                    var data = google.visualization.arrayToDataTable(arrData);\n" +
                        "\n" +
                        "                                    drawChart(data,options);\n" +
                        "                                });\n" +
                        "\n" */+
                        "                                google.charts.setOnLoadCallback(function() {\n" +
                        "                                    $(function() {\n" );
                out.println("var options = {\n" +
                        "                        title: \"Percentage of students in each rubrics for this Course,total No. of students : "+PIResults.size()+"\",\n" +
                        "                        legend: { position: \"right\" },\n" +
                        "                        vAxis: {\n" +
                        "                            minValue: 0,\n" +
                        "                            maxValue: 100,\n" +
                        "                            format: '#\\'%\\''\n" +
                        "                        }," +
                        "chartArea: {\n" +
                        "            width: 800,\n" +
                        "            height: 500\n" +
                        "        }\n" +
                        "                    };");
                out.print("arrData = [\n" +
                        "   [\"Rubric\",  {label: 'Parentage. of Students', type: 'number'}, { role: 'style' } ],");

                for (int i=0;i < PIRubrics.size();i++){
                    out.print("[\""+PIRubrics.get(i)+"\", "+ (results[i]!= 0 ? ( results[i] * 100 ) / PIResults.size() : 0 )+", \""+styles[i]+"\"]\n");
                    if(i!=PIRubrics.size()-1) out.print(",");
                }
                out.print("];");
                out.println("var data = google.visualization.arrayToDataTable(arrData);");
                        out.print("                                        drawChart(data,options);\n" +
                        "                                    });\n" +
                        "                                });\n" +
                        "\n" +
                        "                                function drawChart(data,options) {\n" +
                        "\n" +
                        "\n" +
                        "                                    var container = document.getElementById(\"chart\");\n" +
                        "\n" +
                        "\n" +
                        "                                    var view = new google.visualization.DataView(data);\n" +
                        "                                    view.setColumns([0, 1,\n" +
                        "                                        { calc: \"stringify\",\n" +
                        "                                            sourceColumn: 1,\n" +
                        "                                            type: \"string\",\n" +
                        "                                            role: \"annotation\" },\n" +
                        "                                        2]);\n" +
                        "\n" +
                        "\n" +
                        "                                    /*var formatter = new google.visualization.NumberFormat({pattern: '##%'});\n" +
                        "                                     // format column 1 of the DataTable\n" +
                        "                                     formatter.format(data, 1);*/\n" +
                        "\n" +
                        "                                    var formatter = new google.visualization.NumberFormat(\n" +
                        "                                            {suffix: '%', negativeColor: 'red', negativeParens: true, pattern:'#.#'});\n" +
                        "                                    formatter.format(data, 1); // Apply formatter to second column\n" +
                        "\n" +
                        "\n" +
                        "                                    var chart = new google.visualization.ColumnChart(container);\n" +
                        "                                    google.visualization.events.addListener(chart, 'ready',\n" +
                        "                                            function() {\n" +
                        "\n" +
                        "                                            });\n" +
                        "\n" +
                        "                                    chart.draw(view, options);\n" +
                        "\n" +
                        "                                }\n" +
                        "\n" +
                        "                            </script>\n" +
                        "                            </div>");


                out.print("<script>\n" +
                        "                            function onSectionChange(tid,pid,pIid,cid){\n" +
                        "                                var cl = document.getElementById(\"sectionList\");\n" +
                        "                                var sid = cl.options[cl.selectedIndex].value;\n" +
                        "                                show('page', false);\n" +
                        "                                show('loading', true);\n" +
                        "                                $.ajax({\n" +
                        "                                    type: 'POST',\n" +
                        "                                    data:{tid: tid,\n" +
                        "                                        pid: pid,\n" +
                        "                                        cid: cid,\n" +
                        "                                        pIid: pIid,\n" +
                        "                                        sid: sid},\n" +
                        "                                    url:'/SelectSectionServlet',\n" +
                        "                                    success: function(result){\n" +
                        "                                        $('#evidance').html(result);\n" +
                        "                                        show('page', true);\n" +
                        "                                        show('loading', false);\n" +
                        "\n" +
                        "                                    }\n" +
                        "\n" +
                        "                                })\n" +
                        "                            }\n");


                /*out.print("var options = {\n" +
                        "                            title: \"Percentage of students in each rubrics for this PI,total No. of students : "+PIResults.size()+"\",\n" +
                        "                            width: \"100%\",\n" +
                        "                            height: 400,\n" +
                        "                            bar: {groupWidth: \"95%\"},\n" +
                        "                            legend: { position: \"right\" },\n" +
                        "                            vAxis: {\n" +
                        "                                minValue: 0,\n" +
                        "                                maxValue: 100,\n" +
                        "                                format: '#\\'%\\''\n" +
                        "                            },\n" +
                        "                            animation: {\n" +
                        "                                duration: 1000,\n" +
                        "                                easing: 'out'\n" +
                        "                            }" +
                        "");
                out.print("chart.draw(data,options);");*/




                //out.println("options['title'] = 'Percentage of students in each rubrics for this Course \n Total No. of students : "+PIResults.size()+"';");
                //out.println("drawChart(data,options);");
                //out.println("$('#chart').s(new { style = \"margin:0 auto; width: 100%; height:100%; \" })");
                out.println("</script>");
                //out.print("<script src=\"/js/chartDraw.js\" type=\"text/javascript\"></script>");
                /*out.print("<script>");
                out.print("drawChart("+PIResults.size()+");");
                out.print("</script>");*/


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
