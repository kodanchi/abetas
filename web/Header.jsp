<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 1/27/2016
  Time: 7:35 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = "Vistor";
    if(session.getAttribute("username")!= null){
        username = (String) session.getAttribute("username");
        System.out.println("from header, session is : "+request.getSession().getId());
        System.out.println("from header, username is : "+username);
    }

    AS_Select dbs = new AS_Select();
    ArrayList<String> uniData = dbs.selectUniversityLogo();

%>
<header>
    <style>
        .block{
            color:<%if(uniData != null && uniData.get(3)!= null){out.print(uniData.get(3));}%>;
        }
    </style>
    <div class="navbar-info" style=" background-color: <%if(uniData != null && uniData.get(3)!= null){out.print(uniData.get(3));}%>;">
        <div class="container">
            <nav class="navbar navbar-info navbar-inverse" role="navigation" style="margin-bottom:-0px; background-color: <%if(uniData != null && uniData.get(3)!= null){out.print(uniData.get(3));}%>;">
                <div class="row" style="margin-bottom:10px; margin-top: 20px;">
                    <div class="navbar-header" style="width:100%">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
                            <span class="sr-only">Toggle navigation</span>
                        </button>
                        <%
                            if(uniData.get(2)!= null){
                                out.println("<a class=\" centerI\" > \n" +
                                        "                            <img width=\"235px\" class=\"\" " +
                                        "src=\""+uniData.get(2)+"\" alt=\""+uniData.get(0)+"\"></a>");
                            }else {
                                out.println("<div class=\"title-uppercase text-white text-center col-sm-3 col-sm-offset-2\">"+uniData.get(1)+"</div>");
                            }
                        %>
                        <%--<a class="navbar-brand centerI" href="http://www.uod.edu.sa">
                            <img width="235px" class="checkboxgroup" src="https://vle.uod.edu.sa/bbcswebdav/institution/LoginPage/logo.png" alt="University Of Dammam"></a>--%>
                    </div>
                </div>
                <div class="row ">
                    <div style="max-width:500px; margin-right:auto; margin-left:auto;">
                        <div class="collapse navbar-collapse" id="navbar-collapse-01">
                            <ul class="nav navbar-nav">
                                <li class="active"><a >Hello <%=username%></a></li>
                                <li><a href="/index.jsp">Home</a></li>
                                <%
                                    if(session.getAttribute("userLvl")!= null){
                                        Integer userLvl = (Integer) request.getSession().getAttribute("userLvl");
                                        if(userLvl != 3)
                                        out.print("<li><a href=\"/settings\">Setting</a></li>");
                                    }
                                %>
                                <li><a href="/logout">Logout</a></li>
                            </ul>
                        </div>
                        <!-- /.navbar-collapse -->
                    </div>
                </div>
            </nav>                <!-- /navbar -->
        </div>
    </div>
</header>
