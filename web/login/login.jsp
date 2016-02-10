<%@ page import="sessionListener.CookiesControl" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 2/8/2016
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ABETAS</title>
    <meta name="description" content="An interactive getting started guide for Brackets.">

    <script href="/js/jquery-2.2.0.min.js" ></script>
    <link rel="stylesheet" href="/css/loginPage.css">
    <link rel="stylesheet" href="/css/bootstrap.css">

</head>

<body>
<div class="row">
    <div class="col-md-4 col-md-offset-4 well vcenter">
        <form class="form-signin" method="post" action="/login">
            <h4><%
                if(CookiesControl.getCookieValue(request,"MY_SESSION_COOKIE")!= null){

                    HashMap<String, HttpSession> lactiveUsers = (HashMap<String, HttpSession>) request.getServletContext().getAttribute("activeUsers");
                    // Get the existing session
                    HttpSession lsession = lactiveUsers.get(CookiesControl.getCookieValue(request,"MY_SESSION_COOKIE"));
                    if(lsession == null) {
                        response.sendRedirect("/index.jsp");
                    }
                }
                if(request.getParameter("status")!= null){
                    if(request.getParameter("status").equals("failedLogin")){
                        out.print("wrong username or password!");
                    }else if(request.getParameter("status").equals("missingData")){
                        out.print("Missing username or password!");
                    }
                }
            %></h4>
            <h2 class="form-signin-heading">Please sign in</h2>
            <label for="userEmail" class="sr-only">Email address</label>
            <input name="userName" type="text" id="userEmail" class="form-control" placeholder="Username" required autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" name="userPassword" class="form-control" placeholder="Password" required>
            <div class="checkbox">
                <label>
                    <input type="checkbox" name="remember" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            <button class="btn btn-link">Forgot your password?</button>

        </form>

    </div>
    <!-- /col-md -->
</div>
<!-- /row -->

</body>

</html>