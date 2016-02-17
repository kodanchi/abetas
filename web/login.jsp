<%@ page import="sessionListener.CookiesControl" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 2/8/2016
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/ct-paper.js"></script>
<script src="/js/ct-paper-checkbox.js"></script>


<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>ABETAS</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <link href="/css/bootstrap.css" rel="stylesheet" />
    <link href="/css/ct-paper.css" rel="stylesheet" />
    <link href="/css/examples.css" rel="stylesheet" />
    <link href="/css/loginPage.css" rel="stylesheet" />


    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/fonts.css" rel="stylesheet">

</head>

<body>

<div class="wrapper">
    <div class="register-background">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1 ">
                    <div class="register-card">
                        <h3 class="title"><img width="200px" src="/img/logodark.png"></h3>
                        <form class="login-form" action="/login" method="post">

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

                            <div class="form-group">
                                <label for="userName" class="title">Username</label>
                                <input rel="tooltip" id="userName" name="userName" type="text" class="form-control" placeholder="Username">
                            </div>
                            <div class="form-group">

                                <label for="userPassword" class="title">Password</label>
                                <input name="userPassword" id="userPassword" type="password" class="form-control" placeholder="Password">
                            </div>

                            <div class="form-group">

                                <label class="checkbox checkbox-green" >

                                    <input type="checkbox" name="remember" data-toggle="checkbox"   checked>

                                    Remember me

                                </label>
                            </div>
                            <button type="submit" name="login" value="Login" class="btn btn-danger btn-block">Login</button>
                        </form>
                        <div class="forgot">
                            <a class="btn btn-simple btn-danger">Forgot password?</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

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
