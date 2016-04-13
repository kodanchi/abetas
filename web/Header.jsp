
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    /**
     * used to display the header section.
     */

    String username = "Vistor";
    if(session.getAttribute("username")!= null){
        username = (String) session.getAttribute("username");
    }

    String ulogo = (String) request.getServletContext().getAttribute("ulogo");
    String uname = (String) request.getServletContext().getAttribute("uname");
    String cname = (String) request.getServletContext().getAttribute("cname");
    String color = (String) request.getServletContext().getAttribute("color");

%>
<header>
    <style>
        .block{
            color:<%if(color!= null){out.print(color);}%>;
        }
    </style>
    <div class="navbar-info" style=" background-color: <%if(color!= null){out.print(color);}%>;">
        <div class="container">
            <nav class="navbar navbar-info navbar-inverse" role="navigation" style="margin-bottom:-0px; background-color: <%if(color!= null){out.print(color);}%>;">
                <div class="row" style="margin-bottom:10px; margin-top: 20px;">
                    <div class="navbar-header" style="width:100%">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
                            <span class="sr-only">Toggle navigation</span>
                        </button>
                        <%
                            if(ulogo!= null){
                                out.println("<a class=\" centerI\" > \n" +
                                        "                            <img width=\"235px\" class=\"\" " +
                                        "src=\""+ulogo+"\" alt=\""+uname+"\"></a>");
                            }else {
                                out.println("<div class=\"title-uppercase white text-center\">"+cname+"</div>");
                            }
                        %>

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
                                        out.print("<li><a href=\"/user_manual.pdf\">Help</a></li>");
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
