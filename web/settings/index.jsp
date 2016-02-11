<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/29/2016
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/25253391/javascript-loading-screen-while-page-loads
--%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="sessionListener.CookiesControl" %>
<%@ page import="com.sun.corba.se.impl.orbutil.ObjectUtility" %>
<%@ page import="ASDB.AS_Select" %>
<%@ page import="javax.persistence.criteria.CriteriaBuilder" %>
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
<script src="/js/sys_user_settings.js" type="text/javascript"></script>
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
                <h2 id="setTitle" class="text-center">Settings</h2>
                <legend></legend>

                <%
                    session = request.getSession(false);
                    boolean isAS = false;
                    if(session.getAttribute("userLvl") != null){
                        Integer ulvl = (Integer)session.getAttribute("userLvl");

                        switch (ulvl){
                            case 0:
                            case 1:
                                isAS = true;
                                break;
                        }

                    }else {
                        CookiesControl.removeCookie(response,"userCookie");
                    }

                    AS_Select adb = new AS_Select();
                    ArrayList<String> userData = null;
                    if(isAS){
                        if (session.getAttribute("userLvl") != null){
                            userData = adb.selectSystemSettings();
                        }
                        out.print("<div class=\"container\">\n" +
                                "                    <ul class=\"nav nav-pills nav-pills-primary\">\n" +
                                "\n" +
                                "                        <li id=\"sysBtn\" class=\"active\"><a href=\"#\" onclick=\"showSysSettings()\">System Setting</a></li>\n" +
                                "\n" +
                                "                        <li id=\"usrBtn\" ><a href=\"#\" onclick=\"showUsrSettings()\">User Setting</a></li>\n" +
                                "\n" +
                                "                    </ul>\n" +
                                "                </div>");

                        out.print("<script>\n" +
                                "                    $(document).ready(function(){\n" +
                                "                    $('#usrSection').hide();\n" +
                                "                    });\n" +
                                "                </script>");
                    }
                %>




                <div class="col-md-10 col-md-offset-1">


                    <br>
                    <br>

                    <%
                        if (isAS){


                    %>
                    <div id="sysSection">
                        <form name="sysform" action="#" method="post">
                            <p>You can change what do you want to change in the following form, and click the bottun down below</p>

                            <div class="form-group">
                                <label>University Name</label>
                                <input type="text" class="form-control" placeholder="University Name" value="<%if(userData != null)out.print(userData.get(0));%>" required>
                            </div>

                            <div class="form-group">

                                <label>Collage Name</label>

                                <input type="text" class="form-control" placeholder="Collage Name" value="<%if(userData != null)out.print(userData.get(1));%>" required>

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
                                            <input type="text" class="form-control" value="<%if(userData != null)out.print(userData.get(2));%>" readonly>
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
                    <%
                        }
                        userData = null;
                        if (session.getAttribute("userLvl") != null){
                            Integer userId = (Integer) session.getAttribute("userLvl");
                            String uname = (String) session.getAttribute("username");

                            switch (userId){
                                case 0:
                                case 1:
                                     userData = adb.selectSuperuser(uname);
                                    break;
                                case 2:
                                    userData = adb.selectFaculty(uname);
                                    break;
                            }
                        }
                    %>

                    <br>

                    <div id="usrSection" >
                        <form name="usrform" action="/updateProfile" method="post">
                            <p>Make sure to apply the changes before leaving the page</p>

                            <div class="form-group">

                                <label for="fname">First Name</label>

                                <input type="text" id="fname" name="fname" class="form-control" placeholder="First Name" value="<%if(userData != null)out.print(userData.get(1));%>" required>

                            </div>
                            <div class="form-group">

                                <label for="mname">Middle Name</label>

                                <input type="text" id="mname" name="mname" class="form-control" placeholder="Middle Name" value="<%if(userData != null)out.print(userData.get(2));%>" required>

                            </div>

                            <div class="form-group">
                                <label for="lname">Last Name</label>
                                <input type="text" id="lname" name="lname" class="form-control" placeholder="Last Name" value="<%if(userData != null)out.print(userData.get(3));%>" required>
                            </div>



                            <div class="form-group">
                                <label for="uemail">Email</label>
                                <input type="email" id="uemail" name="uemail" class="form-control" placeholder="Email" value="<%if(userData != null)out.print(userData.get(5));%>" required>
                            </div>

                            <div class="form-group">

                                <label for="upassword">Password</label>

                                <input type="password" id="upassword" name="upassword" class="form-control" placeholder="Password" required>

                            </div>
                            <div class="form-group">

                                <label for="repassword">Re-enter Password</label>

                                <input type="password" name="repassword" id="repassword" class="form-control" placeholder="Password" required>

                            </div>

                            <br>
                            <br>

                            <button type="button" onclick="onSubmitUpdateUser()" class="btn btn-success btn-fill">Apply changes</button>

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



    function showSysSettings(){
            $('#usrSection').hide();
        $('#sysSection').show();
        $('#sysBtn').addClass("active");
        $('#usrBtn').removeClass("active");

    }
    function showUsrSettings(){
        $('#usrSection').show();
        $('#sysSection').hide();
        $('#usrBtn').addClass("active");
        $('#sysBtn').removeClass("active");

    }


    function show(id, value) {
        document.getElementById(id).style.display = value ? 'block' : 'none';
    }

    onReady(function () {
        show('page', true);
        show('loading', false);
    });


</script>

</html>