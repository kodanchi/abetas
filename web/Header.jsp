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
<header>
    <div class="navbar-info">
        <div class="container">
            <nav class="navbar navbar-inverse navbar-info" role="navigation" style="margin-top:10px; margin-bottom:-0px;">

                <div class="row">
                    <div class=" col-sm-4 col-sm-offset-4">
                        <div class="navbar-header center-block">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
                                <span class="sr-only">Toggle navigation</span>
                            </button>
                            <a class="navbar-brand" href="http://www.uod.edu.sa"> <img height="35px" src="https://vle.uod.edu.sa/bbcswebdav/institution/LoginPage/logo.png" alt="University Of Dammam" style=""></a>
                        </div></div>

                </div>

                <div class="row">
                    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                        <div class="collapse navbar-collapse" id="navbar-collapse-01">
                            <ul class="nav navbar-nav">
                                <li class="active"><a href="#fakelink">Hello Ali</a></li>
                                <li><a href="#fakelink">Home</a></li>
                                <li><a href="#fakelink">Setting</a></li>
                                <li><a href="#fakelink">Logout</a></li>
                            </ul>
                        </div>
                        <!-- /.navbar-collapse -->
                    </div>
                </div>
            </nav>
            <!-- /navbar -->
        </div>
    </div>
</header>
<!-- end header  -->