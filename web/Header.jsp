<%@ page import="ASDB.Settings_Select" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 1/27/2016
  Time: 7:35 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username="";
    if(session.getAttribute("username")!= null){
        username = (String) session.getAttribute("username");
        System.out.println("from header, session is : "+request.getSession().getId()+" , username is : "+session.getAttribute("username"));
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
            <%--<a href="http://www.uod.edu.sa">--%>


                <%
                    Settings_Select sdb = new Settings_Select();
                    ArrayList<String> logoData = sdb.selectLogo();

                    if(logoData.get(1) != null){
                       out.print("<div class=\"logo\">\n" +
                               "                        <img class=\"addBtn\" height=\"40px\" src=\""+logoData.get(1)+"\" alt=\"University Of Dammam\">\n" +
                               "                    </div>");
                    }else {
                        out.print("<div class=\"logo\">\n" +
                                "                        <h3\">\n" +
                                logoData.get(0) +
                                "</h3>" +
                                "                    </div>");
                    }

                %>


            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navigation-example-2">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/index.jsp" class="btn btn-default btn-simple">Home</a>
                </li>
                <li>
                    <a href="/settings" class="btn btn-default btn-simple">Setting</a>
                </li>
                <li>
                    <a href="/logout" class="btn btn-default btn-fill">Logout</a>
                </li>
                <li>
                    <p class="btn btn-primary btn-fill">Hello <%=username%></p>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-->
</nav>
<!-- end navbar  -->