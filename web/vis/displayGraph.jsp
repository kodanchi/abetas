<%@ page import="EDB.E_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%@ page import="EDB.EncDec" %><%--
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
<script src="/js/moment.js" type="text/javascript"></script>
<script src="/js/Chart.bundle.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>


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

                    <%
                        String PIname= "";
                        if(request.getParameter("piname")!= null){
                            PIname= EncDec.getDecr(request.getParameter("piname"));
                        }
                    %>
                    <h4 class="text-center"><%=PIname%></h4>

                    <div class="col-md-4 pull-left">
                        <label for="courseListSelect" >Course: </label>
                        <select id="courseListSelect" onchange="onCourseChange(<%=tid%>,<%=pid%>,<%=id%>);" data-live-search="true">
                            <option value="overall" datat-token="overall" >Overall</option>
                            <%
                            ArrayList<ArrayList<String>> coursesList = dbs.selectCoursesOfSummativeToEvaluate(Integer.parseInt(tid),
                                    Integer.parseInt(pid));
                            for(ArrayList<String> course : coursesList){
                                out.print(" <option value=\""+course.get(0)+"\" data-tokens=\""+course.get(1)+"\">"+course.get(1)+"</option>");
                            }
                        %>


                        </select>

                        <script>
                            function onCourseChange(tid,pid,pIid){
                                var cl = document.getElementById("courseListSelect");
                                var cid = cl.options[cl.selectedIndex].value;
                                if(cid == "overall"){
                                    pIOverviewChart();
                                    $("#sectionList").hide();
                                    $("#evidence").hide();
                                }else {
                                    show('page', false);
                                    show('loading', true);
                                    $.ajax({
                                        type: 'POST',
                                        data: {
                                            tid: tid,
                                            pid: pid,
                                            cid: cid,
                                            pIid: pIid
                                        },
                                        url: '/SelectCourseServlet',
                                        success: function (result) {
                                            $('#sectionList').html(result);
                                            $('#sectionList').show();
                                            show('page', true);
                                            show('loading', false);

                                        }

                                    })
                                }
                            }
                        </script>



                    </div>


                    <div class="col-sm-3 pull-right" id="sectionList">

                    </div>
                </div>


                <div class="row">
                    <div  >

                        <div class="row">

                            <canvas id="canvas"></canvas>
                            <script>
                                var mychart;


                                var Rubrics = ["<%=PIRubrics.get(0)%>","<%=PIRubrics.get(1)%>","<%=PIRubrics.get(2)%>","<%=PIRubrics.get(3)%>"];


                                window.onload = dChart();
                                function dChart() {
                                    var Results = [<%
                                        for (int i=0;i < PIRubrics.size();i++){
                                            out.print(results[i]!= 0 ? ( results[i] * 100 ) / PIResults.size() : 0 );
                                            if(i!=PIRubrics.size()-1) out.print(",");
                                        }
                                        %>];

                                    var ctx = document.getElementById("canvas").getContext("2d");

                                    var barChartData = {
                                        labels: Rubrics,
                                        datasets: [{
                                            label: 'PI: <%=id%>',
                                            backgroundColor: "rgba(218, 165, 32, 0.8)",
                                            data: Results
                                        }]

                                    };
                                    window.mychart = new Chart(ctx, {
                                        type: 'bar',
                                        data: barChartData,
                                        options:{
                                            responsive : true,
                                            scales:{
                                                yAxes:[{
                                                    ticks:{
                                                        beginAtZero:true,
                                                        max : 100
                                                    }
                                                }]
                                            }
                                        }
                                    });

                                };

                                function pIOverviewChart() {
                                    var Results = [<%
                                        for (int i=0;i < PIRubrics.size();i++){
                                            out.print(results[i]!= 0 ? ( results[i] * 100 ) / PIResults.size() : 0 );
                                            if(i!=PIRubrics.size()-1) out.print(",");
                                        }
                                        %>];

                                    //var ctx = document.getElementById("canvas").getContext("2d");

                                    window.mychart.clear();
                                    window.mychart.data.datasets[0].label = "PI: <%=id%>"
                                    window.mychart.data.datasets[0].data = Results;
                                    window.mychart.data.datasets[0].backgroundColor = "rgba(218, 165, 32, 0.8)";
                                    window.mychart.update();

                                };

                                function popChart(title,backgroundColor,data) {


                                    window.mychart.clear();
                                    //window.mychart.addData(data,title);
                                    window.mychart.data.datasets[0].backgroundColor = backgroundColor;
                                    window.mychart.data.datasets[0].label = title;
                                    window.mychart.data.datasets[0].data = data;
                                    window.mychart.update();



                                };


                            </script>


                            </div>


                    </div>
                </div>






                <div class="row">
                    <div class="col-md-2 pull-right" id="evidence">

                    </div>
                </div>
                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>


        <!-- Modal Bodies come here -->
    </div>
</div>
