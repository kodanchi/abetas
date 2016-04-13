
<%@ page import="java.util.ArrayList" %>
<%@ page import="ASDB.C_AS_Select" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%


    /**
     * used to display upload page.
     */


    String id ="";
    String term ="";
    String dataType = "";
    if(request.getParameter("cycle") != null && request.getParameter("term") != null){
        id = request.getParameter("cycle");
        term = request.getParameter("term");
        dataType = request.getParameter("data");

    }
%>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>



    <div class="container">
        <!--  Here is row -->
        <div class="row">
            <h2 class="text-center">Import Excel Sheet</h2>
            <legend></legend>
            <div class="col-md-8 col-md-offset-2">
                <p>Please confirm that the imported data are as intended, you can click re-upload to discard the current imported file and import it again.</p>
                <div class="panel panel-default">
                    <!-- Default panel contents -->

                    <!-- Table -->
                    <table class="table table-hover table-striped table-bordered text-center">
                        <tr>
                            <%
                                boolean isValid = true;
                                C_AS_Select dbs = new C_AS_Select();
                                if(dataType.equals("students")){
                                    out.print("<th>Student ID</th>");
                                    out.print("<th>Student Name</th>");
                                }else if (dataType.equals("pis")){
                                    out.print("<th>Performance Indicator</th>");
                                    out.print("<th>threshold</th>");
                                }
                            %>
                        </tr>

<%
try {



    Object dataObj = request.getSession().getAttribute("sheetData");



    ArrayList<ArrayList<String>> dataArr = (ArrayList<ArrayList<String>>) dataObj;
    ArrayList<String> dataRow;


    for(int i=0;i<dataArr.size();i++){
        out.print("<tr>");
        dataRow = dataArr.get(i);



        for(int j=0;j<dataRow.size();j++){


            if(dataType.equals("students")){
                if(j == 0 && dbs.isStudentIDExist(dataRow.get(0),Integer.parseInt(request.getParameter("section")))){
                    out.print("<td class=\"danger\">");
                    out.print("<p  data-toggle=\"tooltip\"  title=\"Student ID existed\">");
                    isValid = false;
                }else {
                    out.print("<td>");
                }

            }else if (dataType.equals("pis")){
                if(j == 0 && dbs.isPIExist(dataRow.get(0), Integer.parseInt(request.getParameter("programID")), Integer.parseInt(id))){
                    out.print("<td class=\"danger\">");
                    out.print("<p  data-toggle=\"tooltip\"  title=\"Performance Indicator existed\">");

                    isValid = false;
                }else {
                    out.print("<td>");
                }
            }



            out.print(dataRow.get(j));
            out.print("</p>");
            out.print("</td>");
        }
        out.print("</tr>");
    }




}catch (Exception e){
    e.fillInStackTrace();
}



%>
                    </table>
                </div>
                    <%
                        if(!isValid){
                            out.print("<p class=\"red\">You cannot upload these data because the data which indicated red are already existed in the database,\n" +
                                    "                    please change the data in the sheet and click re-upload to upload it again. </p>");
                        }
                    %>


                <form method="post" action="/upload/cycle" >
                    <a class="btn btn-success btn-fill" href="/cycle/index.jsp?name=<%=term%>&id=<%=id%>&page=import&data=<%=dataType%>">re-upload</a>
                    <input name="data-type" value="<%=dataType%>" hidden>
                    <input type="text" name="cycle" value="<%=request.getParameter("cycle")%>" hidden/>
                    <input type="text" name="term" value="<%=request.getParameter("term")%>" hidden/>
                    <input type="text" name="programID" value="<%=request.getParameter("programID")%>" hidden/>
                    <%
                        if(dataType.equals("students")){
                            out.print("<input type=\"text\" name=\"courseCode\" value=\""+request.getParameter("courseCode")+"\" hidden/>\n" +
                                    " <input type=\"text\" name=\"courseName\" value=\""+request.getParameter("courseName")+"\" hidden/>\n" +
                                    " <input type=\"text\" name=\"section\" value=\""+request.getParameter("section")+"\"  hidden/>");
                        }

                        if(isValid){
                            out.print("<button class=\"btn btn-primary\"  type=\"submit\">Upload</button>");

                        }else {
                            out.print("<script type=\"text/javascript\">\n" +
                                    "    $(window).load(function(){\n" +
                                    "       bootbox.alert(\"Some of the data are duplicated, please check the table below where red cells indicated\")\n" +
                                    "    });\n" +
                                    "</script>");
                        }

                    %>

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

                <script>
                    $(document).ready(function(){
                        $('[data-toggle="tooltip"]').tooltip();
                    });
                </script>


                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>


    </div>

