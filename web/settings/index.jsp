<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/29/2016
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/25253391/javascript-loading-screen-while-page-loads
--%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>User Management</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <link href="/css/bootstrap.css" rel="stylesheet" />
    <link href="/css/ct-paper.css" rel="stylesheet"/>
    <link href="/css/demo.css" rel="stylesheet" />
    <link href="/css/users.css" rel="stylesheet" />
    <link href="/css/cus.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>

</head>
<body>
<script src="/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="/js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
<div id="page">
<div id="header">
    <jsp:include page="/Header.jsp"/>
</div>

<div id="main" class="main">
    <div class="section">
        <div class="container" id="space">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 id="setTitle" class="text-center">User Settings</h2>
                <legend></legend>

                <div class="container">
                    <ul class="nav nav-pills nav-pills-primary">

                        <li id="sysBtn" class="active"><a onclick="showSysSettings()">System Setting</a></li>

                        <li id="usrBtn" ><a onclick="showUsrSettings()">User Setting</a></li>

                    </ul>
                </div>


                <div class="col-md-10 col-md-offset-1">


                    <br>
                    <br>

                    <div id="sysSection">
                        <form name="sysform" action="#" method="post">
                            <p>You can change what do you want to change in the following form, and click the bottun down below</p>

                            <div class="form-group">
                                <label>University Name</label>
                                <input type="text" class="form-control" placeholder="University Name" required>
                            </div>

                            <div class="form-group">

                                <label>Collage Name</label>

                                <input type="text" class="form-control" placeholder="Collage Name" required>

                            </div>
                            <div class="form-group">

                                <label>Dean First Name</label>

                                <input type="text" class="form-control" placeholder="First Name" required>

                            </div>
                            <div class="form-group">

                                <label>Dean Middle Name</label>

                                <input type="text" class="form-control" placeholder="Middle Name" required>

                            </div>

                            <div class="form-group">
                                <label>Dean Last Name</label>
                                <input type="text" class="form-control" placeholder="Last Name" required>
                            </div>


                            <br>
                            <br>
                            <div class="row tim-row">
                                <div class="col-md-8 col-sm-8">
                                    <div class="form-group">
                                        <label>University Logo</label>

                                        <div class="input-group">
                <span class="input-group-btn">
                    <span class="btn btn-fill btn-primary btn-file">
                        Browse&hellip; <input type="file">
                    </span>
                </span>
                                            <input type="text" class="form-control" readonly>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-4">
                                    <img src="http://design.ubuntu.com/wp-content/uploads/ubuntu-logo32.png">

                                </div>
                            </div>

                            <button type="submit" class="btn btn-success btn-fill addBtn">Apply changes</button>

                        </form>
                    </div>

                    <br>

                    <div id="usrSection" >
                        <form name="usrform" action="#" method="post">
                            <p>Make sure to apply the changes before leaving the page</p>

                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" class="form-control" placeholder="Email" required>
                            </div>

                            <div class="form-group">

                                <label>Password</label>

                                <input type="password" class="form-control" placeholder="Password" required>

                            </div>
                            <div class="form-group">

                                <label>Re-enter Password</label>

                                <input type="password" class="form-control" placeholder="Password" required>

                            </div>

                            <br>
                            <br>

                            <button type="submit" class="btn btn-success btn-fill">Apply changes</button>

                        </form>
                    </div>








                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>
        </div>
    </div>
</div>

<!--   end modal  -->


<div id="footer">
    <jsp:include page="/Footer.jsp"/>
</div>
</div>
<div id="loading" ></div>
</body>

<script src="/js/bootstrap.js" type="text/javascript"></script>

<!--  Plugins -->
<script src="/js/ct-paper-checkbox.js"></script>
<script src="/js/ct-paper-radio.js"></script>
<script src="/js/bootstrap-select.js"></script>
<script src="/js/bootstrap-datepicker.js"></script>

<script src="/js/ct-paper.js"></script>




<script>
    $.ajaxPrefilter(function( options, originalOptions, jqXHR ) { options.async = true; });
    /*$( "#slider-range" ).slider({
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
        //$("#header").load("/header.jsp");
        //$("#main").load("userslist.jsp");
        //$("#footer").load("/footer.jsp");
    });


    function addUser(){
        $("#main").load("adduser.jsp");
    }

    function cancel(){
        $("#main").load("userslist.jsp");
    }

     */

    function onReady(callback) {
        var intervalID = window.setInterval(checkReady, 100);
        function checkReady() {
            if (document.getElementsByTagName('body')[0] !== undefined) {
                window.clearInterval(intervalID);
                callback.call(this);
            }
        }
    }

    $(document).ready(function(){
        $('#usrSection').display = "none";

        function showSysSettings(){
            $('#usrSection').display = "none";
            $('#sysSection').display = "block";
            $('#sysBtn').style().addClass("active");
            $('#usrBtn').style().removeClass("active");

        }
        function showUsrSettings(){
            $('#usrSection').display = "block";
            $('#sysSection').display = "none";
            $('#usrBtn').style().addClass("active");
            $('#sysBtn').style().removeClass("active");

        }
    });


    function show(id, value) {
        document.getElementById(id).style.display = value ? 'block' : 'none';
    }

    onReady(function () {
        show('page', true);
        show('loading', false);
    });


</script>

</html>