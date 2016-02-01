<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/1/2016
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>Add Program</title>

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
<body style="background-color:#1a1817;">
<div id="header"></div>

<div class="main">
    <div class="section">
        <div class="container" id="space">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Add Program</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p>You need to enter the name and the misson of the program:</p>

                    <form name="myform" action="#" method="post">

                        <div class="form-group">

                            <label>Program Name</label>

                            <input type="text" class="form-control" placeholder="Program Name" required>

                        </div>

                        <div class="form-group">

                            <label>Mission Statement</label>

                            <textarea class="form-control" rows="4" cols="50" placeholder="Mission Statement" required></textarea>

                        </div>

                        <br>
                        <button type="submit" class="btn btn-success btn-fill">Add</button>
                        <button type="button" class="btn btn-primary">Cancel</button>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
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
