<%@ page import="EDB.E_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 2/28/2016
  Time: 2:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>
<script src="/js/vis.js"></script>
<link href="/css/vis.css" rel="stylesheet" type="text/css" />
<script>(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)})(window,document,'script','//www.google-analytics.com/analytics.js','ga');ga('create', 'UA-61231638-1', 'auto');ga('send', 'pageview');</script></head>
<%
    E_Select dbs = new E_Select();
    String id = request.getParameter("id");
    String tid = request.getParameter("tid");
    String pid = request.getParameter("pid");
    String dataType = request.getParameter("dataType");

    ArrayList<String> PIRubrics = null;
    ArrayList<String> PIResults = null;
    String[] styles = null;
    int[] results = null;
    int yAxis= 100;

    try {

         PIRubrics = dbs.selectRubricsToEvaluate(Integer.parseInt(id),Integer.parseInt(pid),
            Integer.parseInt(tid),dataType);


        PIResults = dbs.selectSummativeRubricResultsToEvaluate(Integer.parseInt(id),Integer.parseInt(pid),
                Integer.parseInt(tid),dataType);

        results = new int[]{0,0,0,0};

        for (String sRubric : PIResults){
            if(sRubric.equals(PIResults.get(0))){
                results[0]++;
            }else if(sRubric.equals(PIResults.get(1))){
                results[1]++;
            }else if(sRubric.equals(PIResults.get(2))){
                results[2]++;
            }else if(sRubric.equals(PIResults.get(3))){
                results[3]++;
            }
        }

        yAxis = (PIRubrics.size() * 100 )/PIResults.size();
        styles = new String[]{"#b87333","silver","gold","#e5e4e2"};


    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }


%>
<div class="section">
    <div class="container">
        <!--         what is row -->
        <div class="row tim-row">
            <h2 class="text-center">Rubrics Results</h2>
            <legend></legend>
            <div class="col-md-8 col-md-offset-2">
                <form name="myform" action="#" method="post">
                    <div class="row">

                        <div class="dropdown pull-left">

                            <h3 class="pull-left" style="margin-right:10px;">Course:</h3>


                            <a href="#" class="btn dropdown-toggle" data-toggle="dropdown">
                                Course
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#">2014-2015</a></li>
                                <li><a href="#">2015-2016</a></li>
                            </ul>
                        </div>


                        <button type="submit" class="btn btn-success btn-fill pull-right">Show Evidance</button>

                    </div>

                </form>


                <!--Load the AJAX API-->
                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                <script type="text/javascript">
                    google.charts.load("current", {packages:['corechart']});
                    google.charts.setOnLoadCallback(drawChart);
                    function drawChart() {
                        var data = google.visualization.arrayToDataTable([
                            ["Rubric",  {label: 'Parentage. of Students', type: 'number'}, { role: 'style' } ],
                                <%
                                for (int i=0;i < PIRubrics.size();i++){
                                    out.print("[\""+PIRubrics.get(i)+"\", "+ (results[i]!= 0 ? ( results[i] * 100 ) / PIResults.size() : 0 )+", \""+styles[i]+"\"]\n");
                                    if(i!=PIRubrics.size()-1) out.print(",");
                                }
                                %>
                            /*["Copper", 8.94, "#b87333"],
                            ["Silver", 10.49, "silver"],
                            ["Gold", 19.30, "gold"],
                            ["Platinum", 21.45, "color: #e5e4e2"]*/
                        ]);

                        var view = new google.visualization.DataView(data);
                        view.setColumns([0, 1,
                            { calc: "stringify",
                                sourceColumn: 1,
                                type: "string",
                                    role: "annotation" },
                            2]);

                        /*var formatter = new google.visualization.NumberFormat({pattern: '##%'});
                        // format column 1 of the DataTable
                        formatter.format(data, 1);*/

                        var formatter = new google.visualization.NumberFormat(
                                {suffix: '%', negativeColor: 'red', negativeParens: true, pattern:'#.#'});
                        formatter.format(data, 1); // Apply formatter to second column

                        var options = {
                            title: "Percentage of students in each rubrics for this PI,total No. of students : <%=PIResults.size()%>",
                            width: "100%",
                            height: 400,
                            bar: {groupWidth: "95%"},
                            legend: { position: "right" },
                            vAxis: {
                                minValue: 0,
                                maxValue: 100,
                                format: '#\'%\''
                            }
                    };
                        var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
                        chart.draw(view, options);
                    }
                </script>
                <div id="columnchart_values" style="width: 100%; height: 300px;"></div>

                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>


        <!-- Modal Bodies come here -->
    </div>
</div>
