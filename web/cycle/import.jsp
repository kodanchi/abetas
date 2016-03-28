<%@ page import="ASDB.P_AS_Select" %><%--
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


        out.print("<script type=\"text/javascript\">\n" +
                "    $(window).load(function(){\n" +
                "       bootbox.alert(\""+request.getSession().getAttribute("errMsg")+"\")\n" +
                "    });\n" +
                "</script>");
        request.getSession().removeAttribute("errMsg");


    }

%>


        <div class="container" id="space">
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Import Excel File</h2>
                <div class="col-md-10 col-md-offset-1">

                    <%
                        String dataType = "";
                        String imgurl="";
                        String text="Please insure that the excel file format is following the same format as the figure, you are responsible for any wrong important data. ";
                        if(request.getParameter("data") != null){
                            String importFile = request.getParameter("data");
                            if(importFile.equals("students")){
                                dataType = "students";
                                imgurl="/img/studentsExecl.JPG";
                                text=text+"The first column: student ID the second column: student name";
                            }else if(importFile.equals("pis")){
                                dataType = "pis";
                                imgurl="/img/PIsExecl.JPG";
                                text=text+"The column name: performance indicator";
                            }
                        }


                %>


                    <div class="row">
                        <p class="col-md-6 lead pull-left"><%=text%></p>


                        <div class="col-md-6 img-responsive pull-right form-group">
                            <img src="<%=imgurl%>" class="img-responsive" alt="excel format">
                        </div>
                    </div>
                    <form name="myform" action="/import/cycle" method="post" enctype="multipart/form-data">
                        <input type="text" name="data-type" value="<%=dataType%>" hidden/>
                        <input type="text" name="cycle" value="<%=request.getParameter("cycle")%>" hidden/>
                        <input type="text" name="term" value="<%=request.getParameter("term")%>" hidden/>
                        <input type="text" name="programID" value="<%=request.getParameter("programID")%>" hidden/>
                        <%
                            if(dataType.equals("students")){
                                out.print("<input type=\"text\" name=\"courseCode\" value=\""+request.getParameter("courseCode")+"\" hidden/>\n" +
                        " <input type=\"text\" name=\"courseName\" value=\""+request.getParameter("courseName")+"\" hidden/>\n" +
                        " <input type=\"text\" name=\"section\" value=\""+request.getParameter("section")+"\"  hidden/>");
                            }
                        %>
                        <div class="row">
                            <div class="col-md-8 col-md-offset-2">


                                <div class="input-group form-group">
    <span class="input-group-btn">
                    <span class="btn btn-file" style="color:#ecf0f1; background-color: #7f8c8d;">
                        Browse&hellip; <input type="file" ACCEPT="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" name="excelInput" required>
                    </span>
    </span>
                                    <input type="text" class="form-control" readonly>
                                </div>
                                <!--    <input class="form-control" type="text" name="first" value="" size="50" /> -->
                                <!--    <input class="btn btn-lg btn-success pull-right" type="submit" value="Browse" name="AddUser"/> -->


                            </div>
                        </div>

                        <button type="submit"  class="btn btn-primary pull-right">Upload</button>

                        <a class="btn btn-default pull-right" href="<%
                            if(dataType.equals("students")){
                                out.print("/cycle/index.jsp?back="+ dataType +"&cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+
                                "&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+
                                "&courseName="+request.getParameter("courseName")+"&section="+request.getParameter("section"));
                            }else if(dataType.equals("pis")){
                                out.print("/cycle/index.jsp?back="+ dataType +"&cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+
                                "&programID="+request.getParameter("programID"));
                            }
                        %>">Cancel</a>
                    </form>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

        </div>

<script src="/js/ct-paper.js"></script>


</html>