<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 2/2/2016
  Time: 7:51 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/jquery.bsFormAlerts.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>


<%

    String tName = "";
    String tfYear = "";
    String ttYear = "0";

    if(request.getSession().getAttribute("errMsg") != null){

        String[] courseOldVal = (request.getSession().getAttribute("TermVal") != null ? (String[]) request.getSession().getAttribute("TermVal") : null);
        System.out.print("arry of user data : "+ courseOldVal[1]);
        if(courseOldVal != null){

            tName = courseOldVal[0];
            tfYear = courseOldVal[1];
            ttYear = courseOldVal[2];
            request.getSession().removeAttribute("TermVal");
        }


        out.print("<script type=\"text/javascript\">\n" +
                "    $(window).load(function(){\n" +
                "       bootbox.alert(\""+request.getSession().getAttribute("errMsg")+"\")\n" +
                "    });\n" +
                "</script>");
        request.getSession().removeAttribute("errMsg");


    }else {
        out.print("<script>\n" +
                "                                                                    $(document).ready(function(){\n" +
                "                                                                        var d = new Date();\n" +
                "                                                                        var n = d.getFullYear();\n" +
                "                                                                        fromInput = document.getElementById(\"fyear\");\n" +
                "                                                                        toInput = document.getElementById(\"tyear\");\n" +
                "                                                                        fromInput.value = n;\n" +
                "                                                                        toInput.value = n+1;\n" +
                "                                                                    });\n" +
                "                                                                    </script>");
    }

%>

<%
    String id = "";
    if(request.getParameter("cycle") != null){
        id  = request.getParameter("cycle");

    }

%>

<div class="container" id="space">

    <div class="row">
        <div class="col-md-7 col-md-offset-2">
            <nav>
                <ol class="cd-breadcrumb triangle small">
                    <li ><em>PI</em></li>
                    <li><em>Rubrics Names</em></li>
                    <li class="current"><em>Terms</em></li>
                </ol>
            </nav>
        </div>
    </div>
                        <!-- Here is row -->
                        <div class="container row">
                            <h2 class="text-center">Terms</h2>

                            <p class="text-center">You need to enter the terms of cycle number <%out.print(id);%></p>


                            <div class="col-md-12">

                                    <div class="row notPrintable">
                                            <form id="addTermForm" method="post" action="/AddTerm">

                                                <div class="col-md-6 col-sm-12">
                                                    <div class="form-group">
                                                    <div class="row">
                                                        <h6>Term</h6>
                                                    </div>
                                                    <div class="row">

                                                            <select class="form-control" name="termName" required>
                                                                <%
                                                                    if (!tName.equals("")){
                                                                        out.print("<option value=\"Term 1\"");
                                                                        if(tName.equals("Term 1"))out.print(" selected ");
                                                                        out.print(">Term 1</option>\n");
                                                                        out.print("<option value=\"Term 2\"");
                                                                        if(tName.equals("Term 2"))out.print(" selected ");
                                                                        out.print(">Term 2</option>\n");
                                                                        out.print("<option value=\"Term 3\"");
                                                                        if(tName.equals("Term 3"))out.print(" selected ");
                                                                        out.print(">Term 3</option>\n");
                                                                        out.print("<option value=\"Term 4\"");
                                                                        if(tName.equals("Term 4"))out.print(" selected ");
                                                                        out.print(">Term 4</option>\n");

                                                                    }else {
                                                                        out.print("<option value=\"Term 1\" selected>Term 1</option>\n" +
                                                                                "<option value=\"Term 2\">Term 2</option>\n" +
                                                                                "<option value=\"Term 3\">Term 3</option>\n" +
                                                                                "<option value=\"Term 4\">Term 4</option>");
                                                                    }
                                                                %>
                                                                <%--<option value="Term 1" selected>Term 1</option>
                                                                <option value="Term 2">Term 2</option>
                                                                <option value="Term 3">Term 3</option>
                                                                <option value="Term 4">Term 4</option>--%>
                                                            </select>
                                                            <input name="cycle" value="<%=id%>" hidden/>
                                                    </div>
                                                </div>

                                                </div>

                                                <div class="col-md-6 col-sm-12 fmName">
                                                    <div class="form-group">

                                                        <div class="row">
                                                            <h6>Year</h6>
                                                        </div>
                                                        <div class="row">
                                                                    <ul class="list-inline">
                                                                        <li><label>From</label></li>
                                                                        <li><input class="form-control courseNResult" onchange="onYearChng(this);" value="<%=tfYear%>" id="fyear" name="fyear" type="number" max="2099" min="2000" /></li>
                                                                        <li><label>To </label></li>
                                                                        <li><input class="form-control courseNResult" onchange="onYearChng(this);" value="<%=ttYear%>" id="tyear" name="tyear" type="number"  max="2099" min="2000" /></li>

                                                                    <button type="submit" class="btn btn-primary">Add</button>
                                                                    </ul>




                                                                <script>


                                                                    /*function myFunction() {
                                                                     var d = new Date();
                                                                     var n = d.getFullYear();
                                                                     document.getElementById("date").innerHTML = n;
                                                                     }*/
                                                                    /*(function(){
                                                                     var d = new Date();
                                                                     var n = d.getFullYear();
                                                                     document.getElementById("date").innerHTML = n;
                                                                     }

                                                                     )();*/

                                                                    function onYearChng(input){
                                                                        var d = new Date();
                                                                        var n = d.getFullYear();
                                                                         fromInput = document.getElementById("fyear");
                                                                         toInput = document.getElementById("tyear");
                                                                        $(document).trigger("clear-alert-id.yearAlert");
                                                                        if(input.value < 2000 ){
                                                                            input.value = n;
                                                                            input.focus();
                                                                            $(document).trigger("set-alert-id-yearAlert", [
                                                                                {
                                                                                    message: "Year must be more than 2000",
                                                                                    priority: "error"
                                                                                }
                                                                            ]);
                                                                        }else if(input.value > 2099){
                                                                            input.value = n;
                                                                            input.focus();
                                                                            $(document).trigger("set-alert-id-yearAlert", [
                                                                                {
                                                                                    message: "Year must be less than 2099",
                                                                                    priority: "error"
                                                                                }
                                                                            ]);
                                                                        }else {
                                                                            if(toInput.value < fromInput.value){
                                                                                toInput.focus();
                                                                                $(document).trigger("set-alert-id-yearAlert", [
                                                                                    {
                                                                                        message: "Year period is not valid",
                                                                                        priority: "error"
                                                                                    }
                                                                                ]);
                                                                            }else if((toInput.value - fromInput.value)>1){
                                                                                toInput.focus();
                                                                                $(document).trigger("set-alert-id-yearAlert", [
                                                                                    {
                                                                                        message: "Year period is not valid",
                                                                                        priority: "error"
                                                                                    }
                                                                                ]);
                                                                            }
                                                                        }


                                                                    }
                                                                </script>
                                                                <script>
                                                                    $(function(){
                                                                        $("#addTermForm").submit(function() {
                                                                            var d = new Date();
                                                                            var n = d.getFullYear();
                                                                            fromInput = document.getElementById("fyear");
                                                                            toInput = document.getElementById("tyear");
                                                                            $(document).trigger("clear-alert-id.yearAlert");
                                                                            if(input.value < 2000 ){
                                                                                input.value = n;
                                                                                input.focus();
                                                                                $(document).trigger("set-alert-id-yearAlert", [
                                                                                    {
                                                                                        message: "Year must be more than 2000",
                                                                                        priority: "error"
                                                                                    }
                                                                                ]);
                                                                                return false;
                                                                            }else if(input.value > 2099){
                                                                                input.value = n;
                                                                                input.focus();
                                                                                $(document).trigger("set-alert-id-yearAlert", [
                                                                                    {
                                                                                        message: "Year must be less than 2099",
                                                                                        priority: "error"
                                                                                    }
                                                                                ]);
                                                                                return false;
                                                                            }else {
                                                                                if(toInput.value < fromInput.value){
                                                                                    toInput.focus();
                                                                                    $(document).trigger("set-alert-id-yearAlert", [
                                                                                        {
                                                                                            message: "Year period is not valid",
                                                                                            priority: "error"
                                                                                        }
                                                                                    ]);
                                                                                    return false;
                                                                                }else if((toInput.value - fromInput.value)>1){
                                                                                    toInput.focus();
                                                                                    $(document).trigger("set-alert-id-yearAlert", [
                                                                                        {
                                                                                            message: "Year period is not valid",
                                                                                            priority: "error"
                                                                                        }
                                                                                    ]);
                                                                                }
                                                                            }


                                                                        });
                                                                    });
                                                                </script>


                                                            </div>
                                                        </div>
                                                    <div class="row">
                                                        <span data-alertid="yearAlert"></span>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                        <div class="row">
                                            <div class="input-group notPrintable"> <span class="input-group-addon">Filter</span>

                                                <input id="filter" type="text" class="form-control" placeholder=" by term name or year  ">
                                            </div>
                                            <table class="table table-hover table-striped table-bordered text-center">
                                                <tr>
                                                    <th>Name</th>
                                                    <th>Year</th>
                                                    <th>Set Default</th>
                                                    <th class="notPrintable">Edit</th>
                                                    <th class="notPrintable">Delete</th>
                                                </tr>
                                                <tbody class="searchable">
                                                    <%

                                                        C_AS_Select dba = new C_AS_Select();

                                                        try {
                                                            ArrayList<ArrayList<String>> rs = dba.selectAddTerm(Integer.parseInt(id));
                                                            ArrayList<String> rsRow ;

                                                            for (int i=0; i<rs.size();i++){
                                                                rsRow = new ArrayList<String>();
                                                                rsRow = rs.get(i);
                                                                out.print("<tr>");
                                                                for (int j=1; j<rsRow.size();j++) {
                                                                    if (j==3){
                                                                        if (Integer.valueOf(rsRow.get(j))==1){
                                                                            out.print("<td>"+"Default"+"</td>");
                                                                        }
                                                                        else if (Integer.valueOf(rsRow.get(j))==0){
                                                                            //out.print("<td>"+"-"+"</td>");
                                                                            out.print("<td><form method=\"post\" action=\"/SetDefault\">\n");
                                                                            out.print("<input name=\"term\" value=\""+rsRow.get(0)+"\" hidden />\n");
                                                                            out.print("<input name=\"cycle\" value=\""+id+"\" hidden />\n");
                                                                            out.print("<p id=\"print-note\" >Not Default</p>\n");
                                                                            out.print("<button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y\"><i class=\"fui-new icon30 \"></i></button>\n</form></td>");
                                                                        }
                                                                    }else {
                                                                        out.print("<td>"+rsRow.get(j)+"</td>");
                                                                    }
                                                                }
                                                                out.print("<td class=\"notPrintable\"><form method=\"post\" action=\"index.jsp\">");
                                                                out.print("<input name=\"page\" value=\"includeCourse\" hidden />\n");
                                                                out.print("<input name=\"term\" value=\""+rsRow.get(0)+"\" hidden />\n");
                                                                out.print("<input name=\"cycle\" value=\""+id+"\" hidden />\n");
                                                                out.print("<button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y\"><i class=\"fui-new icon30 \"></i></button>\n</form></td>");



                                                                out.print("<td  class=\"notPrintable\"><form class=\"delForm\" method=\"post\" action=\"/DeleteTerm\">\n");
                                                                out.print("<input name=\"term\" value=\""+rsRow.get(0)+"\" hidden />\n");
                                                                out.print("<input name=\"cycle\" value=\""+id+"\" hidden />\n");
                                                                out.print("<button  type=\"submit\" title=\"Delete\" class=\"btn btn-link btn-T\"><i class=\"fui-trash icon30 \"></i></button>\n</form></td>");
                                                            }

                                                        } catch (ClassNotFoundException e) {
                                                            e.printStackTrace();
                                                        } catch (SQLException e) {
                                                            e.printStackTrace();
                                                        }


                                                    %>
                                                </tbody>

                                            </table>
                                        </div>
                                    </div>
                            <a class="btn btn-primary" href="index.jsp?page=rubricNames&cycle=<%=id%>&programID=<%=request.getParameter("programID")%>">Back</a>

                                    <a class="btn btn-info pull-right" href="index.jsp">Cycles List</a>



                                <!-- End of col -->
                            </div>

                            <!-- End of row -->
                        </div>

                    </div>
