<%@ page import="EDB.E_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="EDB.EncDec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/bootstrap-select.min.js" type="text/javascript"></script>
<script src="/js/moment.js" type="text/javascript"></script>
<script src="/js/Chart.bundle.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>


<%
    /**
     * displayGraph page used to display the evaluation graph
     */
    E_Select dbs = new E_Select();
    String id = request.getParameter("id");
    String tid = request.getParameter("tid");
    String pid = request.getParameter("pid");

    ArrayList<String> PIRubrics = null;
    ArrayList<String> PIResults = null;
    float[] results = null;

    try {

         PIRubrics = dbs.selectRubricsToEvaluate(Integer.parseInt(tid));


        PIResults = dbs.selectSummativeRubricResultsToEvaluate(Integer.parseInt(id),Integer.parseInt(pid),
                Integer.parseInt(tid));

        results = new float[]{0,0,0,0};

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
                                ArrayList<ArrayList<String>> coursesList = null;
                                try {
                                    coursesList = dbs.selectCoursesOfSummativeToEvaluate(Integer.parseInt(tid), Integer.parseInt(pid));
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                for(ArrayList<String> course : coursesList){
                                out.print(" <option value=\""+course.get(0)+"\" data-tokens=\""+course.get(1)+"\">"+course.get(1)+"</option>");
                            }
                        %>
                        </select>

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

                                    window.mychart.clear();
                                    window.mychart.data.datasets[0].label = "PI: <%=id%>"
                                    window.mychart.data.datasets[0].data = Results;
                                    window.mychart.data.datasets[0].backgroundColor = "rgba(218, 165, 32, 0.8)";
                                    window.mychart.update();

                                };

                                function popChart(title,backgroundColor,data) {


                                    window.mychart.clear();
                                    window.mychart.data.datasets[0].backgroundColor = backgroundColor;
                                    window.mychart.data.datasets[0].label = title;
                                    window.mychart.data.datasets[0].data = data;
                                    window.mychart.update();

                                };

                            </script>


                            </div>


                    </div>

                    <div class="col-md-7 " id="writtenResult"></div>

                </div>


                <script>
                    function covr(){
                        document.getElementById("writtenResult").innerHTML = '<%
                                    int threshold =0;
                                        try {
                                                threshold = dbs.selectThresholdToEvaluate(Integer.parseInt(id));} catch (ClassNotFoundException e) {
                                                    e.printStackTrace();
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                }
                                    float developed = results[2]!= 0 ? ( results[2] * 100 ) / PIResults.size() : 0;
                                    float exemplary = results[3]!= 0 ? ( results[3] * 100 ) / PIResults.size() : 0;
                                    float passOrFailresults = developed + exemplary;
                                    if(passOrFailresults > threshold ) {
                                        out.print("<Strong style=\"color:Green;\">The requirements were met</Strong>");
                                        out.print(String.format("</br> the result is more than the threshold (%s) by (%.2f)",threshold+"%",
                                                ( passOrFailresults - threshold)));
                                    }else {
                                        out.print("<Strong style=\"color:Red;\">The requirements were not met</Strong>");
                                        out.print(String.format("</br> the result is less than the threshold (%s) by (%.2f)",threshold+"%",
                                                (threshold - passOrFailresults)));
                                    }
                                    %>';
                    }
                    function onCourseChange(tid,pid,pIid){
                        var cl = document.getElementById("courseListSelect");
                        var cid = cl.options[cl.selectedIndex].value;
                        if(cid == "overall"){
                            pIOverviewChart();
                            $("#sectionList").hide();
                            $("#evidence").hide();
                            covr();
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
                                    sovr();

                                }

                            })
                        }
                    }
                    $(function(){
                        covr();
                    });
                </script>



                <div class="row" id="evidence">
                    <div class="col-md-2 pull-right" >

                    </div>
                </div>

                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>


        <!-- Modal Bodies come here -->
    </div>
</div>
