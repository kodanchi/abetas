<%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 1/27/2016
  Time: 7:35 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session.getAttribute("username")!= null){
        String un = "username";
        session.setAttribute("username",un);
        System.out.println("from header, session is : "+request.getSession().getId());
    }
%>
<script src="/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="/js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
<nav class="navbar navbar-ct-danger" role="navigation-demo" id="demo-navbar">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="http://www.uod.edu.sa">


                    <div class="logo">
                        <img class="addBtn" height="40px" src="http://www.uod.edu.sa/sites/all/themes/uod_base/logo.svg" alt="University Of Dammam">
                    </div>

            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navigation-example-2">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#" class="btn btn-default btn-simple">Home</a>
                </li>
                <li>
                    <a href="#" class="btn btn-default btn-simple">Setting</a>
                </li>
                <li>
                    <a href="#" class="btn btn-default btn-fill">Logout</a>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-->
</nav>
<!-- end navbar  -->