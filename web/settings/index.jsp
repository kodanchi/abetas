<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/29/2016
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/25253391/javascript-loading-screen-while-page-loads
--%>
<%--<%@ page import="java.io.*,java.util.*" %>--%>
<%@ page import="sessionListener.CookiesControl" %>

<%@ page import="ASDB.Settings_Select" %>
<%@ page import="java.util.ArrayList" %>

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
<%--
    <link href="/css/ct-paper.css" rel="stylesheet"/>
--%>
    <link href="/css/demo.css" rel="stylesheet" />
    <link href="/css/users.css" rel="stylesheet" />
    <link href="/css/flat-ui.css" rel="stylesheet" />
    <link href="/css/cus.css" rel="stylesheet" />
    <link href="/css/bootstrap-colorpicker.css" rel="stylesheet" />


<%--
    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>--%>

</head>
<body>


<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<%--<script src="/js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>--%>
<script src="/js/bootbox.min.js" type="application/javascript"></script>
<script src="/js/jquery.bsFormAlerts.js" type="application/javascript"></script>
<script src="/js/uploadInput.js" type="application/javascript"></script>
<div id="page">
<div id="header">
    <jsp:include page="/Header.jsp"/>
</div>


        <div class="container" id="space">
            <!-- Here is row -->
            <div class="row">
                <h2 id="setTitle" class="text-center">Settings</h2>
                <legend></legend>

                <%

                    //session = request.getSession(false);
                    boolean isAS = false;
                    if(request.getSession().getAttribute("userLvl") != null){
                        Integer ulvl = (Integer) request.getSession().getAttribute("userLvl");

                        switch (ulvl){
                            case 0:
                            case 1:
                                isAS = true;
                                break;
                        }

                    }else {
                        CookiesControl.removeCookie(response,"userCookie");
                        response.sendRedirect("/login");
                    }

                    Settings_Select adb = new Settings_Select();
                    ArrayList<String> userData = null;
                    if(isAS){
                        if (request.getSession().getAttribute("userLvl") != null){
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



                    if(request.getSession().getAttribute("Msg")!= null){
                        out.print("<script>");
                        out.print("\n" +
                                "bootbox.alert(\""+request.getSession().getAttribute("Msg")+"\");");

                        if(request.getSession().getAttribute("Msg").toString().startsWith("System")){

                            out.println("$(function(){\n");
                            out.println("$('#usrSection').hide();\n");
                            out.println("$('#sysSection').show();\n");
                            out.println("});\n");
                        }else {
                            out.println("$(function(){\n");
                            out.println("$('#usrSection').show();\n");
                            out.println("$('#sysSection').hide();\n");
                            out.println("$('#sysBtn').removeClass(\"active\");\n" +
                                    "    $('#usrBtn').addClass(\"active\");\n");

                            out.println("});\n");
                        }
                        out.print("</script>");

                        request.getSession().removeAttribute("Msg");
                    }else {
                        out.print("<script>$(function(){\n" +
                                "      $('#usrSection').hide();\n" +
                                "  });</script>");
                    }


                    }else {


                        if (request.getSession().getAttribute("Msg") != null) {
                            out.print("<script>");
                            out.print("\n" +
                                    "bootbox.alert(\"" + request.getSession().getAttribute("Msg") + "\");");

                            out.print("</script>");

                            request.getSession().removeAttribute("Msg");
                        }
                    }

                %>


<script>



</script>

                <div class="col-md-10 col-md-offset-1">


                    <br>

                    <%
                        if (isAS){


                    %>
                    <div id="sysSection">
                        <form name="sysform" id="sysform" action="/sysSettingsUpdate" method="post" enctype="multipart/form-data">
                            <p>You can change what do you want to change in the following form, and click the bottun down below</p>

                            <div class="form-group">
                                <label>University Name</label>
                                <input type="text" id="uname" name="uname" class="form-control" placeholder="University Name" value="<%if(userData != null)out.print(userData.get(0));%>" required>
                                <span data-alertid="uname"></span>

                            </div>

                            <div class="form-group">

                                <label>Collage Name</label>

                                <input type="text" id="cname" name="cname" class="form-control" placeholder="Collage Name" value="<%if(userData != null)out.print(userData.get(1));%>" required>
                                <span data-alertid="cname"></span>

                            </div>


                            <br>

                            <div class="row">
                                <div class="col-md-8 col-sm-8">
                                    <div class="form-group">
                                        <label>Header Background Color</label>
                                        <div class="input-group demo">
                                            <input type="text" name="color" value="<%if(userData != null && userData.get(3)!= null){out.print(userData.get(3));}%>" class="form-control" />

                                            <span class="input-group-addon"><i></i></span>

                                        </div>
                                        <script>
                                            $(function(){
                                                $('.demo').colorpicker();
                                            });
                                        </script>
                                    </div>
                                </div>
                            </div>

                            <br>
                            <div class="row">
                                <div class="col-md-8 col-sm-8">
                                    <div class="form-group">
                                        <label>University Logo</label>

                                        <div class="input-group">

                                            <span class="input-group-btn">
                                                <span class="btn btn-file" style="color:#ecf0f1; background-color: #7f8c8d;">
                                                    Browse&hellip; <input type="file" id="ulogo" name="ulogo" accept="image/png">
                                                </span>
                                            </span>

                                            <input type="text" class="form-control" value="" readonly>
                                            <span data-alertid="ulogo"></span>

                                        </div>
                                        <p class="help-block">Allowed size and type: 2MB, png</p>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-4 paddingImg" style="background-color: <%if(userData != null && userData.get(3)!= null){out.print(userData.get(3));}%>;">
                                    <img style="max-width: 270px" src="<%
                                    if(userData != null && userData.get(2)!= null){out.print(userData.get(2));
                                    }else { out.print("/img/logoHolder.png");}
                                    %>">

                                </div>


                            </div>




                            <button type="button" onclick="onSubmitUpdateSystem()" class="btn btn-primary btn-fill addBtn">Apply changes</button>

                        </form>
                    </div>
                    <%
                        }
                        userData = null;
                        if (request.getSession().getAttribute("userLvl") != null){
                            Integer userLvl = (Integer) request.getSession().getAttribute("userLvl");
                            String uname = (String) request.getSession().getAttribute("username");
                            String userId = (String) request.getSession().getAttribute("userId");
                            System.out.println("uname :"+ uname);
                            System.out.println("userId :"+ userId);
                            switch (userLvl){
                                case 0:
                                case 1:
                                     userData = adb.selectSuperuserForSettings(Integer.valueOf(userId));

                                    System.out.println("userData :"+ userData);
                                    break;
                                case 2:
                                    userData = adb.selectFaculty(uname);
                                    break;
                            }
                        }
                    %>

                    <br>

                    <div id="usrSection" >
                        <form name="usrform" id="usrform" action="/updateProfile" method="post">
                            <p>Make sure to apply the changes before leaving the page</p>

                            <div class="form-group">

                                <label for="fname">First Name</label>

                                <input type="text" id="fname" name="fname" class="form-control" placeholder="First Name" value="<%if(userData != null)out.print(userData.get(1));%>" required>
                                <span data-alertid="fname"></span>

                            </div>
                            <div class="form-group">

                                <label for="mname">Middle Name</label>

                                <input type="text" id="mname" name="mname" class="form-control" placeholder="Middle Name" value="<%if(userData != null)out.print(userData.get(2));%>" required>
                                <span data-alertid="mname"></span>
                            </div>

                            <div class="form-group">
                                <label for="lname">Last Name</label>
                                <input type="text" id="lname" name="lname" class="form-control" placeholder="Last Name" value="<%if(userData != null)out.print(userData.get(3));%>" required>
                                <span data-alertid="lname"></span>
                            </div>



                            <div class="form-group">
                                <label for="uemail">Email</label>
                                <input type="email" id="uemail" name="uemail" class="form-control" placeholder="Email" value="<%if(userData != null)out.print(userData.get(5));%>" required>
                                <span data-alertid="email"></span>

                            </div>

                            <div class="form-group">

                                <label for="uOldPassword">Current Password</label>

                                <input type="password" id="uOldPassword" name="uOldPassword" class="form-control" placeholder="Current Password" required />
                                <span data-alertid="password"></span>

                            </div>

                            <div class="form-group">
                                <div class = "panel panel-warning">
                                    <div class = "panel-body back">

                                        <div class="form-group">

                                            <label for="upassword">New Password</label>

                                            <input type="password" id="upassword" name="upassword" class="form-control" placeholder="New Password" required>

                                        </div>
                                        <div class="form-group">

                                            <label for="repassword">Re-enter new Password</label>

                                            <input type="password" name="reupassword" id="repassword" class="form-control" placeholder="new Password" required>
                                            <span data-alertid="renewpassword"></span>
                                        </div>


                                    </div>

                                    <div class = "panel-footer">Do not fill inside if you don't want to change your password!</div>
                                </div>
                            </div>



                            <br>
                            <br>

                            <input name="uOldemail" value="<%if(userData != null)out.print(userData.get(5));%>" hidden >
                            <input name="uid" value="<%if(userData != null)out.print(userData.get(0));%>" hidden>
                            <input name="uname" value="<%if(userData != null)out.print(userData.get(4));%>" hidden>
                            <input name="ulvl" value="<%if(request.getSession().getAttribute("userLvl") != null)out.print((Integer) request.getSession().getAttribute("userLvl"));%>" hidden>
                            <button type="button" onclick="onSubmitUpdateUser()" class="btn btn-primary" >Apply changes</button>

                        </form>
                    </div>








                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>
        </div>

<div id="footer">
    <jsp:include page="/Footer.jsp"/>
</div>
</div>
<div id="loading" ></div>
</body>

<!--  Plugins -->
<%--<script src="/js/ct-paper-checkbox.js"></script>
<script src="/js/ct-paper-radio.js"></script>
<script src="/js/ct-paper.js"></script>--%>
<script src="/js/bootstrap-select.js"></script>
<script src="/js/bootstrap-datepicker.js"></script>
<script src="/js/sys-usr-set.js"></script>
<script src="/js/bootstrap-colorpicker.min.js"></script>






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





    function show(id, value) {
        document.getElementById(id).style.display = value ? 'block' : 'none';
    }

    onReady(function () {
        show('page', true);
        show('loading', false);
    });


</script>

</html>