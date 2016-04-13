
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.js" type="text/javascript"></script>
<script src="/js/uploadInput.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>
<script src="/js/jquery.bsFormAlerts.js" type="text/javascript"></script>
<script src="/js/excel-validation.js" type="text/javascript"></script>
<%

    /**
     * used to display objectives/outcomes/courses import page.
     */

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
            <div class="row form-group">
                <h2 class="text-center">Import Excel File</h2>
                <div class="col-md-10 col-md-offset-1">

                    <%
                        String dataType = "";
                        String imgurl= "";
                        String text="Please insure that the excel file format is following the same format as the figure, you are responsible for any wrong important data. ";
                        if(request.getParameter("data") != null){
                            String importFile = request.getParameter("data");
                            if(importFile.equals("obj")){
                                dataType = "obj";
                                imgurl="/img/objectiveExecl.JPG";
                                text=text+"The column name: objective";
                            }else if(importFile.equals("outcomes")){
                                dataType = "outcomes";
                                imgurl="/img/outcomeExecl.JPG";
                                text=text+"The column name: outcome";
                            }else if(importFile.equals("courses")){
                                dataType = "courses";
                                imgurl="/img/coursesExecl.JPG";
                                text=text+"The first column: course name the second column: course code the third column: course level \n Note: the course level is between 1 to 10";
                            }
                        }


                %>


                    <div class="row">
                        <p class="col-md-6 lead pull-left"><%=text%></p>


                        <div class="col-md-6 img-responsive pull-right">
                            <img src="<%=imgurl%>" class="img-responsive" alt="excel format">
                        </div>
                    </div>
                    <form  id="import" name="myform" action="/import/program" method="post" enctype="multipart/form-data">
                        <input type="text" name="data-type" value="<%=dataType%>" hidden/>
                        <input type="text" name="id" value="<%=request.getParameter("id")%>" hidden/>
                        <input type="text" name="name" value="<%=request.getParameter("name")%>" hidden/>
                        <div class="row tim-row">
                            <div class="col-md-8 col-md-offset-2">


                            <div class="form-group ">
                                <div class=" input-group">
                                    <span class="input-group-btn">
                                        <span class="btn btn-file" style="color:#ecf0f1; background-color: #7f8c8d;">
                                            Browse&hellip; <input type="file" ACCEPT="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" name="excelInput" id="excelInput" >
                                        </span>
                                    </span>
                                    <input type="text" class="form-control" readonly>
                                </div>
                                <span data-alertid="exceli"></span>
                            </div>


                            </div>
                        </div>

                        <button type="submit"  class="btn btn-success btn-fill">Upload</button>

                        <a class="btn btn-primary pull-right" href="/program/index.jsp?back=<%=dataType%>&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Back</a>
                    </form>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

            <!-- End of container -->
        </div>

<script src="/js/ct-paper.js"></script>


</html>