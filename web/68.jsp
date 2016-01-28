<%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 1/28/2016
  Time: 7:18 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>Formative Data Collection Sheet</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/ct-paper.css" rel="stylesheet" />
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
                <h2 class="text-center">Formative Data Collection Sheet</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">
                    <p>Outcome and performance Indicator Indicator:</p>

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table">
                            <tr>
                                <th>Student Outcome</th>
                                <th>e.Outcome</th>
                            </tr>
                            <tr>
                                <td>performance indicators</td>
                                <td>ii.PI</td>
                            </tr>
                        </table>
                    </div>

                    <p>Course Information:</p>

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table">
                            <tr>
                                <th>Course Code:</th>
                                <th>CIS-211</th>
                            </tr>
                            <tr>
                                <td>Course title:</td>
                                <td>Fundamentals of Information System</td>
                            </tr>

                            <tr>
                                <td>Level & Program:</td>
                                <td>1,CIS</td>
                            </tr>

                            <tr>
                                <td>Team and Year:</td>
                                <td>1, 2015-16</td>
                            </tr>
                        </table>
                    </div>

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table">
                            <tr>
                                <th>Rubric(s):</th>
                                <th>Poor</th>
                                <th>Developing</th>
                                <th>Developed</th>
                                <th>Examplary</th>
                            </tr>
                            <tr>
                                <td>e(ii)</td>
                                <td>1</td>
                                <td>2</td>
                                <td>3</td>
                                <td>4</td>
                            </tr>
                        </table>
                    </div>

                    <form name="formativeForm" action="formHandler.jsp" method="post">
                        <div>
                            <p>Some text</p>
                            <textarea name="formDesInput" class="form-control" rows="4"></textarea>
                        </div>
                        <p>Instructor Feedback:</p>

                        <div class="panel panel-default">

                            <table>
                                <tr>
                                    <th>Comment(s) on Success/Failure in Achieving Performance Indicator*: </th>
                                    <td>something</td>
                                </tr>

                                <tr>
                                    <th>Obstacles in Achieving Desired Progress*:</th>
                                    <td>something</td>
                                </tr>
                                <tr>
                                    <th>Areas of Improvement*:</th>
                                    <td>something</td>
                                </tr>

                                <tr>

                                </tr>

                            </table>
                        </div>
                    </form>
                    <div class="row tim-row">
                        <label>Faculty Name :</label>
                        <label>--</label>
                        <label class="pull-right">--</label>

                        <label class="pull-right">Date :</label>
                    </div>
                    <button class="btn btn-success btn-fill">Upload evidence</button>
                    <button class="btn btn-primary">Save</button>
                    <button class="btn btn-primary pull-right">Submit</button>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


        </div>
    </div>
    <!-- End of main -->
</div>


<div>
</div>
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
        $("#header").load("Header.jsp");
        $("#footer").load("Footer.jsp");
    });
</script>

</html>
