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
            <nav class="navbar navbar-inverse navbar-info" role="navigation" style="margin-bottom:-0px;">
                <div class="row" style="margin-bottom:10px;">
                    <div class="navbar-header" style="width:100%">

                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
                            <span class="sr-only">Toggle navigation</span>
                        </button>
                        <a class="navbar-brand centerI" href="http://www.uod.edu.sa"> <img width="235px" class="checkboxgroup" src="https://vle.uod.edu.sa/bbcswebdav/institution/LoginPage/logo.png" alt="University Of Dammam"></a>
                    </div>
                </div>
                <div class="row">
                    <div style="max-width:500px; margin-right:auto; margin-left:auto;">
                        <div class="collapse navbar-collapse" id="navbar-collapse-01">
                            <ul class="nav navbar-nav">
                                <li class="active"><a href="#">Hello Ali</a></li>
                                <li><a href="#">Home</a></li>
                                <li><a href="#">Setting</a></li>
                                <li><a href="#">Logout</a></li>
                            </ul>
                        </div>
                        <!-- /.navbar-collapse -->
                    </div>
                </div>
            </nav>                <!-- /navbar -->
        </div>
    </div>
</header>
