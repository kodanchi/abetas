<%@ page import="com.database.ASDB" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 1/27/2016
  Time: 7:03 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>






<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>Add Courses</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <link href="/css/bootstrap.css" rel="stylesheet" />
    <link href="/css/ct-paper.css" rel="stylesheet" />
    <link href="/css/demo.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>

</head>

<body>
<div id="header"></div>

<div class="main">
    <div class="section">
        <div class="container">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Cycle Configuration</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p class="text-center">You need to enter the terms of the cycle</p>

                    <div>
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <div class="row tim-row">
                            <div class="col-md-6">
                                <form>
                                    <div class="row tim-row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input type="text" name="termsname" class="form-control" required>

                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Year</label>
                                                <div class="dropdown">
                                                    <a href="#" class="btn dropdown-toggle" data-toggle="dropdown">
                                                        Year
                                                        <b class="caret"></b>
                                                    </a>
                                                    <ul class="dropdown-menu">
                                                        <li><a href="#">2014-2015</a></li>
                                                        <li><a href="#">2015-2016</a></li>
                                                    </ul>
                                                </div>

                                            </div>
                                        </div>

                                    </div>
                                    <button type="submit" class="btn btn-success">Add</button>

                                </form>
                            </div>

                            <div class="col-md-6 panel panel-default">
                                <table class="table">
                                    <tr>
                                        <th>Name</th>
                                        <th>Year</th>
                                        <th>edit</th>
                                        <th>delete</th>
                                    </tr>
                                    <%

                                        ASDB dba = new ASDB();

                                        try {
                                            ArrayList<ArrayList<String>> rs = dba.selectAddTerm();
                                            ArrayList<String> rsRow ;

                                            for (int i=0; i<rs.size();i++){
                                                rsRow = new ArrayList<String>();
                                                rsRow = rs.get(i);
                                                out.print("<tr>");
                                                for (int j=0; j<rsRow.size();j++) {
                                                    out.print("<td>"+rsRow.get(j)+"</td>");

                                                }
                                                out.print("<td><a class=\"btn btn-warning btn-simple\" href=\"#\"><i class=\"fa fa-pencil fa-2x\"></i></a></td>\n");
                                                out.print("<td><a class=\"btn btn-danger btn-simple\" href=\"#\"><i class=\"fa fa-trash-o fa-2x\"></i></a></td>\n");
                                                out.print("</tr>");
                                            }

                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }















                                    %>

                                </table>
                            </div>
                        </div>



                    </div>
                    <button class="btn btn-success pull-right">Cancel</button>

                    <button class="btn btn-success pull-right">Next</button>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>
<!--   end modal  -->

<div id="footer"></div>

</body>

<script src="/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="/js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.js" type="text/javascript"></script>

<!--  Plugins -->
<script src="/js/ct-paper-checkbox.js"></script>
<script src="/js/ct-paper-radio.js"></script>
<script src="/js/bootstrap-select.js"></script>
<script src="/js/bootstrap-datepicker.js"></script>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>

<script src="/js/ct-paper.js"></script>
<script>
    $("#slider-range").slider({
        range: true,
        min: 0,
        max: 500,
        values: [75, 300],
    });
    $("#slider-default").slider({
        value: 70,
        orientation: "horizontal",
        range: "min",
        animate: true
    });
    $('.btn-tooltip').tooltip('show');
    $('.radio').on('toggle', function () {});


    $(function () {
        $("#header").load("header.html");
        $("#footer").load("footer.html");
    });
</script>

</html>