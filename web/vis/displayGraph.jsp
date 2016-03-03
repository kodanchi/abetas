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
<script src="/js/bootstrap-select.min.js" type="text/javascript"></script>

<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script  type="text/javascript">
    google.charts.load("current", {packages:['corechart']});
</script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>

<%--
<script>(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)})(window,document,'script','//www.google-analytics.com/analytics.js','ga');ga('create', 'UA-61231638-1', 'auto');ga('send', 'pageview');</script></head>
--%>
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
            Integer.parseInt(tid));


        PIResults = dbs.selectSummativeRubricResultsToEvaluate(Integer.parseInt(id),Integer.parseInt(pid),
                Integer.parseInt(tid));

        results = new int[]{0,0,0,0};

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
                <div class="row">

                    <div class="col-md-4 pull-left">
                        <select id="courseList" onchange="onCourseChange(<%=tid%>,<%=pid%>,<%=id%>);" class="" data-live-search="true">
                        <%
                            ArrayList<ArrayList<String>> coursesList = dbs.selectCoursesOfSummativeToEvaluate(Integer.parseInt(tid),
                                    Integer.parseInt(pid));
                            for(ArrayList<String> course : coursesList){
                                out.print(" <option value=\""+course.get(0)+"\" data-tokens=\""+course.get(1)+"\">"+course.get(1)+"</option>");
                            }
                        %>

                            <%--<option value="" data-tokens=" "> </option>
                            <option data-tokens="mustard">Burger, Shake and a Smile</option>
                            <option data-tokens="frosting">Sugar, Spice and all things nice</option>--%>
                        </select>

                        <script>
                            function onCourseChange(tid,pid,pIid){
                                var cl = document.getElementById("courseList");
                                var cid = cl.options[cl.selectedIndex].value;
                                show('page', false);
                                show('loading', true);
                                $.ajax({
                                    type: 'POST',
                                    data:{tid: tid,
                                        pid: pid,
                                        cid: cid,
                                        pIid: pIid},
                                    url:'/SelectCourseServlet',
                                    success: function(result){
                                        $('#sectionList').html(result);
                                        show('page', true);
                                        show('loading', false);

                                    }

                                })
                            }
                        </script>
<%--

                        <script>
                            var data = google.visualization.arrayToDataTable([
                                ["Rubric",  {label: 'Parentage. of Students', type: 'number'}, { role: 'style' } ],
                                <%
                                for (int i=0;i < PIRubrics.size();i++){
                                    out.print("[\""+PIRubrics.get(i)+"\", "+ (results[i]!= 0 ? ( results[i] * 100 ) / PIResults.size() : 0 )+", \""+styles[i]+"\"]\n");
                                    if(i!=PIRubrics.size()-1) out.print(",");
                                }
                                %>
                            ]);
                            chart.draw(data,options);
                        </script>
--%>


                    </div>




                </div>

                <div class="row">
                    <div class="col-lg-10" id="sectionList">
                        <div class="row">

                            </div>
                        <div class="row">
                            <div id="chart_wrap"><div id="chart"></div></div>

                            <script type="text/javascript">

                                google.charts.load("current", {packages:['corechart']});

                                $(window).on("throttledresize", function (event) {
                                    var options = {
                                        title: "Percentage of students in each rubrics for this PI,total No. of students : <%=PIResults.size()%>",
                                        bar: {groupWidth: "95%"},
                                        legend: { position: "right" },
                                        width: '100%',
                                        height: '100%',
                                        vAxis: {
                                            minValue: 0,
                                            maxValue: 100,
                                            format: '#\'%\''
                                        },
                                        animation: {
                                            duration: 1000,
                                            easing: 'out'
                                        }
                                    };

                                    var arrData = [
                                        ["Rubric",  {label: 'Parentage. of Students', type: 'number'}, { role: 'style' } ],
                                        <%
                                        for (int i=0;i < PIRubrics.size();i++){
                                            out.print("[\""+PIRubrics.get(i)+"\", "+ (results[i]!= 0 ? ( results[i] * 100 ) / PIResults.size() : 0 )+", \""+styles[i]+"\"]\n");
                                            if(i!=PIRubrics.size()-1) out.print(",");
                                        }
                                        %>

                                    ];
                                    var data = google.visualization.arrayToDataTable(arrData);

                                    drawChart(data,options);
                                });

                                google.charts.setOnLoadCallback(function() {
                                    $(function() {
                                        var options = {
                                            title: "Percentage of students in each rubrics for this PI,total No. of students : <%=PIResults.size()%>",
                                            bar: {groupWidth: "95%"},
                                            legend: { position: "right" },
                                            width: '100%',
                                            height: '100%',
                                            vAxis: {
                                                minValue: 0,
                                                maxValue: 100,
                                                format: '#\'%\''
                                            },
                                            animation: {
                                                duration: 1000,
                                                easing: 'out'
                                            }
                                        };

                                        var arrData = [
                                            ["Rubric",  {label: 'Parentage. of Students', type: 'number'}, { role: 'style' } ],
                                            <%
                                            for (int i=0;i < PIRubrics.size();i++){
                                                out.print("[\""+PIRubrics.get(i)+"\", "+ (results[i]!= 0 ? ( results[i] * 100 ) / PIResults.size() : 0 )+", \""+styles[i]+"\"]\n");
                                                if(i!=PIRubrics.size()-1) out.print(",");
                                            }
                                            %>

                                        ];
                                        var data = google.visualization.arrayToDataTable(arrData);

                                        drawChart(data,options);
                                    });
                                });

                                function drawChart(data,options) {


                                    var container = document.getElementById("chart");


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


                                    var chart = new google.visualization.ColumnChart(container);
                                    google.visualization.events.addListener(chart, 'ready',
                                            function() {

                                            });

                                    chart.draw(view, options);

                                }

                            </script>
                            </div>


                    </div>
                </div>






                <div class="row">
                    <div class="col-md-2 pull-right" id="evidence">
                        <button type="submit" class="btn btn-success btn-fill pull-right">Show Evidance</button>
                    </div>
                </div>
                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>


        <!-- Modal Bodies come here -->
    </div>
</div>
