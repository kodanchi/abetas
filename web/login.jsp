<%@ page import="Listeners.CookiesControl" %><%--
<%@ page import="Listeners.CookiesControl" %>
<%@ page import="java.util.HashMap" %>
--%>
<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 2/8/2016
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>





<!doctype html>
<html lang="en">

<head>
    <link href="/css/bootstrap.css" rel="stylesheet" />
    <link href="/css/examples.css" rel="stylesheet" />
    <link href="/css/loginPage.css" rel="stylesheet" />
    <link href="/css/flat-ui.css" rel="stylesheet" />
    <link href="/css/checkbox.css" rel="stylesheet" />
    <link href="/css/users.css" rel="stylesheet" />
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>ABETAS</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <script src="/js/jquery-2.2.0.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/bootbox.min.js"></script>
    <script src="/js/flat-ui.min.js"></script>
    <script src="/js/prettify.js"></script>
    <script src="/js/jquery.bsFormAlerts.js"></script>
    <script src="/js/pageloading.js"></script>

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/fonts.css" rel="stylesheet">
    <link href="/css/cus.css" rel="stylesheet">

</head>


<body class="register-background">
    <script>
        $(document).ready( function(){
            $('#forgotDiv').hide();
            $('#passcodeDiv').hide();
            $('#passResetDiv').hide();
        });
    </script>
    <div id="page">
        <div class="wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-md-4 col-md-offset-4">

                        <%--<div class="login">
                            <div class="login-screen">
                                <div class="login-icon">
                                    <img src="img/logo.png" alt="Welcome to Mail App">
                                    <h4>Welcome to <small>ABETAS</small></h4>
                                </div>

                                <div class="login-form">
                                    <div class="form-group">
                                        <input type="text" class="form-control login-field" value="" placeholder="Enter your name" id="login-name">
                                        <label class="login-field-icon fui-user" for="login-name"></label>
                                    </div>

                                    <div class="form-group">
                                        <input type="password" class="form-control login-field" value="" placeholder="Password" id="login-pass">
                                        <label class="login-field-icon fui-lock" for="login-pass"></label>
                                    </div>

                                    <a class="btn btn-primary btn-lg btn-block" href="#">Log in</a>
                                    <a class="login-link" href="#">Lost your password?</a>
                                </div>
                            </div>
                        </div>--%>

                        <div class="register-card">
                            <div class="title"><img width="200px" src="/img/logowhite.png"></div>


                            <%

                                if(request.getParameter("status")!= null){
                                    if(request.getParameter("status").equals("failedLogin")){
                                                            /*out.print("<p class=\"bg-danger\">");
                                                            out.print("wrong username or password!");
                                                            out.print("</p>");*/
                                        out.print("\n" +
                                                "<div class=\"alert alert-danger text-center fade in\">\n" +
                                                "<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"+
                                                "wrong username or password"+
                                                "</div>");
                                    }else if(request.getParameter("status").equals("missingData")){
                                        out.print("<div class=\"alert alert-danger text-center fade in\">");
                                        out.print("<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>");
                                        out.print("Missing username or password");
                                        out.print("</div>");
                                    }
                                }
                            %>



                            <div id="loginDiv" class="login-form">
                                <form action="/login" method="post">
                                    <span class="small" id="prinfo"></span>
                                    <div class="form-group">
                                        <input data-toggle="tooltip" title="Username" id="userName" name="userName" type="text" class="form-control" placeholder="Username" autofocus/>
                                    </div>
                                    <div class="form-group">
                                        <input name="userPassword" id="userPassword" type="password" class="form-control" placeholder="Password">
                                    </div>

                                    <%--<div class="form-group">

                                        <label class="checkbox" >

                                            <input type="checkbox" name="remember" data-toggle="checkbox"   checked>

                                            Remember me

                                        </label>


                                    </div>--%>
                                    <p ><input type="checkbox" name="remember" id="test1"
                                             <%
                                                 String HEIL = CookiesControl.getCookieValue(request,"rememberCookie");
                                                 if (HEIL!=null && HEIL.equals("true")){
                                                     out.print("checked");
                                                 }
                                             %>
                                    /><label  for="test1"><span class="ui"></span>Remember me</label></p>
                                    <input type="hidden" name="backURL" value="<%if (request.getParameter("logout")==null) {
                                    if(request.getHeader("referer")!=null) {
                                        out.print(request.getHeader("referer"));
                                    }else {
                                        out.print("/index.jsp");
                                    }
                                    }else {
                                    out.print("/index.jsp");
                                    }%>" />

                                    <button type="submit" name="login" value="Login" class="btn btn-primary btn-block">Login</button>
                                    <div class="forgot">
                                        <button type="button" class="btn btn-default btn-block" onclick="new function(){
                                                                            $('#loginDiv').hide();
                                                                            $('#forgotDiv').show();
                                                                        }">Password Recovery</button>
                                    </div>
                                </form>
                            </div>
                            <div id="forgotDiv" class="login-form">

                                <form >
                                    <div class="form-group">
                                        <input data-toggle="tooltip" title="Enter your Email" id="userEmail" name="userEmail"
                                               type="email" class="form-control" placeholder="Email" required autofocus/>
                                        <span class="small"  data-alertid="emailMsg" id="emailMsg"></span >
                                    </div>
                                    <button type="button" class="btn btn-primary btn-block" onclick="onRecoverPassword()">Recover Password</button>
                                    <button type="button" class="btn btn-default btn-block" onclick="new function(){
                                                                        $('#loginDiv').show();
                                                                        $('#forgotDiv').hide();
                                                                    }">Cancel</button>
                                </form>
                                <%--<script>
                                    $(document).trigger("clear-alert-id.emailMsg");
                                    $(document).trigger("set-alert-id-emailMsg", [
                                        {
                                            message: "Email Does not exist!, Try more time!",
                                            priority: "info"
                                        }
                                    ]);
                                </script>--%>
                                <script>
                                    function onRecoverPassword(){
                                        var useremail = document.getElementById("userEmail");
                                        $(document).trigger("clear-alert-id.emailMsg");
                                        if(useremail.value == ""){
                                            $(document).trigger("set-alert-id-emailMsg", [
                                                {
                                                    message: "Please enter your email",
                                                    priority: "error"
                                                }
                                            ]);
                                        }else {
                                            show('page', false);
                                            show('loading', true);
                                            $.ajax({
                                                type: 'POST',
                                                data: {
                                                    email: useremail.value
                                                },
                                                url: '/Reset',
                                                success: function (result) {
                                                    $('#passcodeDiv').html(result);
                                                    $('#passcodeDiv').show();
                                                    $('#forgotDiv').hide();
                                                    show('page', true);
                                                    show('loading', false);

                                                }

                                            })
                                        }
                                    }
                                </script>
                            </div>
                            <div id="passcodeDiv" class="login-form">

                            </div>

                            <div id="passResetDiv" class="login-form">
                                <%

                                    if(request.getSession().getAttribute("loginMsg")!= null){
                                        String msg = (String) request.getSession().getAttribute("loginMsg");

                                            out.println("<form >\n" +
                                                    "                                    <div class=\"form-group\">\n" +
                                                    "                                        <input data-toggle=\"tooltip\" title=\"Enter the new Password\" id=\"newPassword\" name=\"newPassword\"\n" +
                                                    "                                               type=\"password\" class=\"form-control\" placeholder=\"New Passoword\" required autofocus/></div>\n" +
                                                    "                                    <div class=\"form-group\">\n" +
                                                    "                                        <input data-toggle=\"tooltip\" title=\"Re-Enter the new Password\" id=\"newRePassword\" name=\"newPassword\"\n" +
                                                    "                                               type=\"password\" class=\"form-control\" placeholder=\"New Passoword\" required autofocus/>\n" +
                                                    "                                        <span class=\"small\"  data-alertid=\"passwordMsg\" id=\"passwordMsg\"></span >\n" +
                                                    "                                    </div>\n" +
                                                    "                                    <button type=\"button\" class=\"btn btn-primary btn-block\" onclick=\"onEnterPasscode()\">Reset Password</button>\n" +
                                                    "                                    <button type=\"button\" class=\"btn btn-default btn-block\" onclick=\"new function(){\n" +
                                                    "                                                                        $('#loginDiv').show();\n" +
                                                    "                                                                        $('#passResetDiv').hide();\n" +
                                                    "                                                                    }\">Cancel</button>\n" +
                                                    "                                </form>\n" +
                                                    "                                <script>\n" +
                                                    "                                    function onEnterPasscode(){\n" +
                                                    "                                        var newPassword = document.getElementById(\"newPassword\");\n" +
                                                    "                                        var newRePassword = document.getElementById(\"newRePassword\");\n" +
                                                    "                                        $(document).trigger(\"clear-alert-id.passwordMsg\");\n" +
                                                    "                                        if(newPassword.value == \"\" || newRePassword.value == \"\"){\n" +
                                                    "                                            $(document).trigger(\"set-alert-id-passwordMsg\", [\n" +
                                                    "                                                {\n" +
                                                    "                                                    message: \"Please enter the new password twice\",\n" +
                                                    "                                                    priority: \"error\"\n" +
                                                    "                                                }\n" +
                                                    "                                            ]);\n" +
                                                    "                                            newPassword.focus();\n" +
                                                    "                                        }else if(newPassword.value != newRePassword.value){\n" +
                                                    "                                            $(document).trigger(\"set-alert-id-passwordMsg\", [\n" +
                                                    "                                                {\n" +
                                                    "                                                    message: \"Password in the two fields doesn't match!\",\n" +
                                                    "                                                    priority: \"error\"\n" +
                                                    "                                                }\n" +
                                                    "                                            ]);\n" +
                                                    "                                            newPassword.focus();\n" +
                                                    "                                        }else {\n" +
                                                    "                                            show('page', false);\n" +
                                                    "                                            show('loading', true);\n" +
                                                    "                                            $.ajax({\n" +
                                                    "                                                type: 'POST',\n" +
                                                    "                                                data: {\n" +
                                                    "                                                    email: '"+msg+"',\n" +
                                                    "                                                    pw: newPassword.value\n" +
                                                    "                                                },\n" +
                                                    "                                                url: '/passReset',\n" +
                                                    "                                                success: function (result) {\n" +
                                                    "                                                    $('#prinfo').html(result);\n" +
                                                    "                                                    show('page', true);\n" +
                                                    "                                                    show('loading', false);\n" +
                                                    "\n" +
                                                    "                                                }\n" +
                                                    "\n" +
                                                    "                                            })\n" +
                                                    "                                        }\n" +
                                                    "                                    }\n" +
                                                    "                                $('#passResetDiv').show();\n" +
                                                    "                                $('#passcodeDiv').hide();\n" +
                                                    "                                </script>");
                                            out.print("<script>");
                                            out.print("$(document).ready( function(){\n");
                                            out.print("$('#loginDiv').hide();\n");
                                            out.print("$('#passResetDiv').show();\n");
                                            out.print("\n" +
                                                    "bootbox.alert(\"Welcome, this is the first time you logged in to the " +
                                                    "system, so you need to change your password for security purposes\");\n");
                                            out.print("});\n");
                                            out.print("</script>");



                                        request.getSession().removeAttribute("loginMsg");
                                    }
                                %>
                            </div>




                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="loading" ></div>


<%--<div class="wrapper">
    <div class="register-background">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1 ">
                    <div class="register-card">
                        <img src="./img/logo.png"/>
                        <form class="login-form" method="post" action="/login">

                            <%

                                if(request.getParameter("status")!= null){
                                    if(request.getParameter("status").equals("failedLogin")){
                                        out.print("<p class=\"bg-danger\">");
                                        out.print("wrong username or password!");
                                        out.print("</p>");
                                    }else if(request.getParameter("status").equals("missingData")){
                                        out.print("<p class=\"bg-danger\">");
                                        out.print("Missing username or password!");
                                        out.print("</p>");
                                    }
                                }
                            %>

                            <div class="form-group">
                                <label for="userName" class="title">Username</label>
                                <input id="userName" name="userName" type="text" class="form-control" placeholder="Username" required autofocus>
                            </div>
                            <div class="form-group">

                                <label for="userPassword" class="title">Password</label>
                                <input id="userPassword" name="userPassword" type="password" class="form-control" placeholder="Password" required>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="remember" value="remember-me"> Remember me
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <button type="submit" name="login" value="Login" class="btn btn-danger btn-block">Login</button>
                        </form>
                        <div class="forgot">
                            <a href="#" class="btn btn-simple btn-danger">Forgot password?</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>--%>

</body>
</html>
