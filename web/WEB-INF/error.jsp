<%--
<%@ page import="Backup.Backup" %>--%>
<%@ page import="java.io.File" %>
<%@ page import="java.nio.file.*" %>
<%@ page import="java.nio.file.attribute.BasicFileAttributes" %>
<%@ page import="java.nio.file.attribute.BasicFileAttributeView" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>Error page</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/flat-ui.css" rel="stylesheet" />
    <link href="css/cus.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/fonts.css" rel="stylesheet">

</head>

<body>
<div id="page">
    <div id="header">
        <jsp:include page="/Header.jsp"/>
    </div>


    <div class="main">
        <div class="section">
            <div class="container">
                <!-- Here is row -->
                <div class="row">
                    <h2 class="text-center">Error !!</h2>
                    <legend></legend>
                    <div>
                        <p>Error page</p>
                        </div>


                        <!-- End of col -->
                    </div>

                    <!-- End of row -->
                </div>

            </div>
        </div>
    </div>


    <div id="footer">
        <jsp:include page="/Footer.jsp"/>
    </div>
</div>
<div id="loading" ></div>
</body>

<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
<!-- <script src="code.jquery.com/jquery-1.10.2.js"></script> -->
<script src="js/bootstrap.js" type="text/javascript"></script>

<!--  Plugins -->
<script src="js/bootstrap-select.js"></script>
<script src="js/bootstrap-datepicker.js"></script>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>

<script src="js/flat-ui.min.js"></script>
<script src="js/flat-ui-select.js"></script>
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


</script>

</html>