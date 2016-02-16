<%@ page import="ASDB.AS_Select" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 2/1/2016
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/11832930/html-input-file-accept-attribute-file-type-csv
  http://stackoverflow.com/questions/15201071/how-to-get-full-path-of-selected-file-on-change-of-input-type-file-using-jav
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.js" type="text/javascript"></script>
<script src="/js/uploadInput.js" type="text/javascript"></script>


<%


    if(request.getSession().getAttribute("errMsg") != null){


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


    <div class="section">
        <div class="container" id="space">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Import Excel File</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">

                    <%
                        String dataType = "";
                        if(request.getParameter("data") != null){
                            String importFile = request.getParameter("data");
                            if(importFile.equals("obj")){
                                dataType = "obj";
                            }else if(importFile.equals("outcomes")){
                                dataType = "outcomes";
                            }else if(importFile.equals("courses")){
                                dataType = "courses";
                            }
                        }

                    /*if(request.getParameter("err") != null){

                        *//*out.print("<div id=\"alert\"  class=\"alert alert-danger fade in\"  role=\"alert\" >\n" +
                                "                        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                                "                            <span aria-hidden=\"true\">&times;</span>\n" +
                                "                        </button>\n" +
                                "                        <strong id=\"alertt\" >\n" +
                                "                            " + request.getParameter("err")+
                                "                        </strong>\n" +
                                "                    </div>");*//*

                        out.print("<script type=\"text/javascript\">\n" +
                                "    $(window).load(function(){\n" +
                                "        $('#myMsgModal').modal('show');\n" +
                                "    });\n" +
                                "    function goToNormal(){\n" +
                                "        window.location.href =\"/program/index.jsp?name="+request.getParameter("name")+"&id="+request.getParameter("id")+"&page=import&data="+request.getParameter("data")+"\";\n" +
                                "    }\n" +
                                "</script>" +
                                "<!-- Modal -->\n" +
                                "                    <div class=\"modal fade\" id=\"myMsgModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
                                "                        <div class=\"modal-dialog\">\n" +
                                "                            <div class=\"modal-content\">\n" +
                                "                                <div class=\"modal-header\">\n" +
                                "                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n" +
                                "                                    <h4 class=\"modal-title\" id=\"myModalLabel\">ERROR</h4>\n" +
                                "                                </div>\n" +
                                "                                <div class=\"modal-body\">\n" +
                                                                        request.getParameter("err") +
                                "                                </div>\n" +
                                "                                <div class=\"modal-footer\">\n" +
                                "\n" +
                                "                                    <div class=\"text-center\">\n" +
                                "                                        <a type=\"button\"  data-dismiss=\"modal\"   class=\"btn btn-default btn-simple\">OK</a>\n" +
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                            </div>\n" +
                                "                        </div>\n" +
                                "                    </div>");


                    }*/

                %>


                    <div class="row tim-row">
                        <p class="col-md-6 lead pull-left">Please insure that the excel file format is following the same format as the figure, you are responsible for any wrong important data.</p>


                        <div class="col-md-6 img-responsive pull-right">
                            <img src="http://i.stack.imgur.com/irt3e.jpg" class="img-responsive" alt="excel format">
                        </div>
                    </div>
                    <form name="myform" action="/import/program" method="post" enctype="multipart/form-data">
                        <input type="text" name="data-type" value="<%=dataType%>" hidden/>
                        <input type="text" name="id" value="<%=request.getParameter("id")%>" hidden/>
                        <input type="text" name="name" value="<%=request.getParameter("name")%>" hidden/>
                        <div class="row tim-row">
                            <div class="col-md-8 col-md-offset-2">


                                <div class="input-group">
    <span class="input-group-btn">
                    <span class="btn btn-fill btn-primary btn-file">
                        Browse&hellip; <input type="file" ACCEPT="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" name="excelInput" required>
                    </span>
    </span>
                                    <input type="text" class="form-control" readonly>
                                </div>
                                <!--    <input class="form-control" type="text" name="first" value="" size="50" /> -->
                                <!--    <input class="btn btn-lg btn-success pull-right" type="submit" value="Browse" name="AddUser"/> -->


                            </div>
                        </div>

                        <button type="submit"  class="btn btn-success btn-fill">Upload</button>

                        <a class="btn btn-primary pull-right" href="/program/index.jsp?back=<%=dataType%>&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Back</a>
                    </form>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>

<script src="/js/ct-paper.js"></script>


</html>