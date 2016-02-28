<%@ page import="EDB.E_Select" %>
<%@ page import="java.util.ArrayList" %><%--
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

    ArrayList<String> PIRubric = dbs.selectRubricsToEvaluate(Integer.parseInt(id),Integer.parseInt(pid),
            Integer.parseInt(tid),dataType);

    
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


                <div style="width:700px; font-size:14px; text-align: justify;">
                    <br /><br />
                    Choose Course: <select id="dropdownID">
                    <option value="overlap">overlap</option>
                    <option value="sideBySide" selected="selected">sideBySide</option>
                    <option value="stack">stack</option>
                </select>
                </div>
                <br />

                <div id="visualization"></div>

                <script type="text/javascript">

                    var container = document.getElementById('visualization');
                    var groups = new vis.DataSet();
                    groups.add({id: 0, content: "group0"})
                    groups.add({id: 1, content: "group1"})
                    groups.add({id: 2, content: "group2"})

                    var items = [
                        {x: '2014-06-11', y: 10, group:0},
                        {x: '2014-06-12', y: 25, group:0},
                        {x: '2014-06-13', y: 30, group:0},
                        {x: '2014-06-14', y: 10, group:0},
                        {x: '2014-06-15', y: 15, group:0},
                        {x: '2014-06-16', y: 30, group:0},
                        {x: '2014-06-11', y: 12, group:1},
                        {x: '2014-06-12', y: 15, group:1},
                        {x: '2014-06-13', y: 34, group:1},
                        {x: '2014-06-14', y: 24, group:1},
                        {x: '2014-06-15', y: 5,  group:1},
                        {x: '2014-06-16', y: 12, group:1},
                        {x: '2014-06-11', y: 22, group:2},
                        {x: '2014-06-12', y: 14, group:2},
                        {x: '2014-06-13', y: 24, group:2},
                        {x: '2014-06-14', y: 21, group:2},
                        {x: '2014-06-15', y: 30, group:2},
                        {x: '2014-06-16', y: 18, group:2}
                    ];

                    var dataset = new vis.DataSet(items);
                    var options = {
                        style:'bar',
                        stack:false,
                        barChart: {width:50, align:'center', sideBySide:true}, // align: left, center, right
                        drawPoints: false,
                        dataAxis: {
                            icons:true
                        },
                        orientation:'top',
                        start: '2014-06-10',
                        end: '2014-06-18'
                    };
                    var graph2d = new vis.Graph2d(container, items, groups, options);

                    var dropdown = document.getElementById("dropdownID");
                    dropdown.onchange = update;

                    function update() {
                        var options = {stack:dropdown.value === 'stack',barChart:{sideBySide:dropdown.value === 'sideBySide'}};
                        graph2d.setOptions(options);
                    }


                </script>


                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>


        <!-- Modal Bodies come here -->
    </div>
</div>
