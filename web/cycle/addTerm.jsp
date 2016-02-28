<%@ page import="ASDB.AS_Select" %>
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

                        /*out.print("<div id=\"alert\"  class=\"alert alert-danger fade in\"  role=\"alert\" >\n" +
                                "                        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                                "                            <span aria-hidden=\"true\">&times;</span>\n" +
                                "                        </button>\n" +
                                "                        <strong id=\"alertt\" >\n" +
                                "                            " + request.getParameter("err")+
                                "                        </strong>\n" +
                                "                    </div>");*/

        out.print("<script type=\"text/javascript\">\n" +
                "    $(window).load(function(){\n" +
                "        $('#myModal').modal('show');\n" +
                "    });\n" +
                "</script>" +
                "<!-- Modal -->\n" +
                "                    <div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
                "                        <div class=\"modal-dialog\">\n" +
                "                            <div class=\"modal-content\">\n" +
                "                                <div class=\"modal-header\">\n" +
                "                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n" +
                "                                    <h4 class=\"modal-title\" id=\"myModalLabel\">INFO</h4>\n" +
                "                                </div>\n" +
                "                                <div class=\"modal-body\">\n");
        out.print(request.getSession().getAttribute("errMsg"));
        request.getSession().removeAttribute("errMsg");

        out.print("                                </div>\n" +
                "                                <div class=\"modal-footer\">\n" +
                "\n" +
                "                                    <div class=\"text-center\">\n" +
                "                                        <a type=\"button\"  data-dismiss=\"modal\"  class=\"btn btn-default btn-simple\">OK</a>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>");


    }

%>

<%
    String id = "";
    if(request.getParameter("cycle") != null){
        id  = request.getParameter("cycle");
        out.print("id is : "+id);
    }

%>
                <div class="section">


                    <div class="container">
                        <!-- what is row -->
                        <div class="row tim-row">



                            <h2 class="text-center">Add Term</h2>
                            <legend></legend>
                            <div class="col-md-10 col-md-offset-1">
                                <p class="text-center">You need to enter the terms of cycle number <%out.print(id);%></p>

                                <div>
                                    <!-- Default panel contents -->

                                    <!-- Table -->
                                    <div class="row tim-row">
                                        <div class="row tim-row">
                                            <form id="addTermForm" method="post" action="/AddTerm">
                                                <div class="col-md-6">

                                                    <div class="row">
                                                        <label>Term</label>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">

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

                                                <div class="col-md-6">
                                                    <div class="row">
                                                        <label>Year</label>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <div class="col-md-5">
                                                                <ul class="list-inline">
                                                                    <li><label>From</label></li>
                                                                    <li><input class="form-control" onchange="onYearChng(this);" value="<%=tfYear%>" id="fyear" name="fyear" type="number" max="2099" min="2000" /></li>
                                                                </ul>

                                                            </div>
                                                            <div class="col-md-5">
                                                                <ul class="list-inline">
                                                                    <li><label>To </label></li>
                                                                    <li><input class="form-control" onchange="onYearChng(this);" value="<%=ttYear%>" id="tyear" name="tyear" type="number"  max="2099" min="2000" /></li>
                                                                </ul>

                                                            </div>

                                                            <div class="col-md-2">
                                                                <button type="submit" class="btn btn-success">Add</button>

                                                            </div>



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
                                                                        }
                                                                    }


                                                                }
                                                            </script>
                                                            <script>
                                                                $(function(){
                                                                    $("#addTermForm").submit(function() {
                                                                        var inputVal = $("#fyear").val();
                                                                        $(document).trigger("clear-alert-id.example");
                                                                        if (inputVal.length < 3) {
                                                                            $(document).trigger("set-alert-id-example", [
                                                                                {
                                                                                    message: "Please enter at least 3 characters",
                                                                                    priority: "error"
                                                                                },
                                                                                {
                                                                                    message: "This is an info alert",
                                                                                    priority: "info"
                                                                                }
                                                                            ]);
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
                                        <Legend></Legend>
                                        <div class="row panel panel-default">
                                            <table class="table">
                                                <tr>
                                                    <th>Name</th>
                                                    <th>Year</th>
                                                    <th>Edit</th>
                                                    <th>Delete</th>
                                                </tr>
                                                <%

                                                    AS_Select dba = new AS_Select();

                                                    try {
                                                        ArrayList<ArrayList<String>> rs = dba.selectAddTerm(id);
                                                        ArrayList<String> rsRow ;

                                                        for (int i=0; i<rs.size();i++){
                                                            rsRow = new ArrayList<String>();
                                                            rsRow = rs.get(i);
                                                            out.print("<tr>");
                                                            for (int j=1; j<rsRow.size();j++) {
                                                                out.print("<td>"+rsRow.get(j)+"</td>");

                                                            }
                                                            out.print("<td><form method=\"post\" action=\"index.jsp\">");
                                                            out.print("<input name=\"page\" value=\"includeCourse\" hidden />\n");
                                                            out.print("<input name=\"term\" value=\""+rsRow.get(0)+"\" hidden />\n");
                                                            out.print("<input name=\"cycle\" value=\""+id+"\" hidden />\n");
                                                            out.print("<button  type=\"submit\" title=\"Edit\" class=\"btn btn-warning btn-simple\"><i class=\"fa fa-pencil fa-2x \"></i></button>\n</form></td>");

                                                            out.print("<td><form method=\"post\" action=\"/DeleteTerm\">\n");
                                                            out.print("<input name=\"term\" value=\""+rsRow.get(0)+"\" hidden />\n");
                                                            out.print("<input name=\"cycle\" value=\""+id+"\" hidden />\n");
                                                            out.print("<button  type=\"submit\" title=\"Delete\" class=\"btn btn-danger btn-simple\"><i class=\"fa fa-trash-o fa-2x \"></i></button>\n</form></td>");
                                                        }

                                                    } catch (ClassNotFoundException e) {
                                                        e.printStackTrace();
                                                    } catch (SQLException e) {
                                                        e.printStackTrace();
                                                    }


                                                %>

                                            </table>
                                        </div>
                                    </div>



                                </div>
                                <a class="btn btn-success pull-right" href="index.jsp">Cycles List</a>



                                <!-- End of col -->
                            </div>

                            <!-- End of row -->
                        </div>


                        <!-- End of container -->
                    </div>
                </div>