<%@ page import="com.database.ASDB" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 1/28/2016
  Time: 6:50 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>Program Management</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/ct-paper.css" rel="stylesheet"/>
    <link href="css/demo.css" rel="stylesheet" />

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
            <!--         what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Program Management</h2>
                <h4 class="text-center">Program Name</h4>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">

                    <ul class="list-inline" style="margin-bottom:-10px;">
                        <li><p>Mission Statement:</p></li>
                        <li><p><a class="btn btn-warning btn-simple" href="#"><i class="fa fa-pencil fa-2x"></i></a></p></li>
                    </ul>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                    <ul class="list-inline" style="margin-bottom:-10px;">
                        <li><p>Program Objectives:</p></li>
                        <li><p><a class="btn btn-warning btn-simple" href="#"><i class="fa fa-pencil fa-2x"></i></a></p></li>
                    </ul>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                    <ul class="list-inline" style="margin-bottom:-10px;">
                        <li><p>Student Outcomes:</p></li>
                        <li><p><a class="btn btn-warning btn-simple" href="#"><i class="fa fa-pencil fa-2x"></i></a></p></li>
                    </ul>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>



                    <ul class="list-inline" style="margin-bottom:-10px;">
                        <li><p>Courses: </p></li>
                        <li><p><a class="btn btn-warning btn-simple" href="#"><i class="fa fa-pencil fa-2x"></i></a></p></li>
                    </ul>





                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table">
                            <tr>
                                <th>Course Name</th>
                                <th>Code</th>
                                <th>Level</th>
                            </tr>
                            <%

                                ASDB dba=new ASDB();
                                try {
                                    ArrayList<ArrayList<String>> rs = dba.selectProgramManagementFig30();
                                    ArrayList<String> rsRow ;

                                    for (int i=0; i<rs.size();i++){
                                        rsRow = new ArrayList<String>();
                                        rsRow = rs.get(i);
                                        out.print("<tr>");
                                        for (int j=0; j<rsRow.size();j++) {
                                            out.print("<td>"+rsRow.get(j)+"</td>");

                                        }
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

                    <button class="btn btn-primary">Print</button>
                    <button class="btn btn-danger btn-fill">Delete</button>
                    <button class="btn btn-primary pull-right">Back</button>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


        </div>
    </div>
    <!-- End of main -->
</div>



<div id="footer"></div>

</body>

<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>

<!--  Plugins -->
<script src="js/ct-paper-checkbox.js"></script>
<script src="js/ct-paper-radio.js"></script>
<script src="js/bootstrap-select.js"></script>
<script src="js/bootstrap-datepicker.js"></script>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>

<script src="js/ct-paper.js"></script>
<script>

    $( "#slider-range" ).slider({
        range: true,
        min: 0,
        max: 500,
        values: [ 75, 300 ],
    });
    $( "#slider-default" ).slider({
        value: 70,
        orientation: "horizontal",
        range: "min",
        animate: true
    });
    $('.btn-tooltip').tooltip('show');
    $('.radio').on('toggle', function() { });


    $(function(){
        $("#header").load("Header.jsp");
        $("#footer").load("Footer.jsp");
    });

</script>

</html>
