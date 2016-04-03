<%@ page import="org.apache.poi.ss.usermodel.Cell" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.*" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFWorkbook" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFSheet" %>
<%@ page import="org.apache.poi.ss.usermodel.Row" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="ASDB.ImportUserSheet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.UUID" %>
<%@ page import="ASDB.U_AS_Select" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 2/1/2016
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/4253660/passing-object-from-jsp-to-servlet
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>
    <div class="container">
        <!-- Here is row -->
        <div class="row">
            <h2 class="text-center">User Management</h2>
            <div class="col-md-8 col-md-offset-2">
                <p>Please confirm that the imported data are as intended, you can click re-upload to discard the current imported file and import it again.</p>

                    <!-- Table -->
                    <table class="table table-hover table-striped table-bordered text-center">
                        <tr>
                            <th>Firstname</th>
                            <th>Middlename</th>
                            <th>Lastname</th>
                            <th>Username</th>
                            <th>Access level</th>
                            <th>Email</th>
                        </tr>

<%
    boolean isValid = true;

    try {



        U_AS_Select dbs = new U_AS_Select();

        if(request.getAttribute("sheetData")== null){
            response.getHeader("index.jsp");
        }else if(request.getMethod().equals("post") && request.getParameter("file")!= null){
            /*System.out.println("inside if :"+request.getParameter("file"));
            //ArrayList<ArrayList<String>> dataArr = (ArrayList<ArrayList<String>>) request.getAttribute("sheetData");
            //request.setAttribute("Data", dataArr);
            //request.getRequestDispatcher("/").forward(request, response);
            //RequestDispatcher rd = request.getRequestDispatcher("/upload/users");

            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }*/
        }
        //String sheetDataId = request.getParameter("sheetData");
        //System.out.println("sheetDataId in upload :"+sheetDataId);

        Object dataObj = request.getSession().getAttribute("sheetData");
        //Object dataObj = request.getAttribute(sheetD);
        //request.getSession().removeAttribute(sheetD);



        ArrayList<ArrayList<String>> dataArr = (ArrayList<ArrayList<String>>) dataObj;
        ArrayList<String> dataRow;

        System.out.println(dataArr);

        for(int i=0;i<dataArr.size();i++){
            dataRow = dataArr.get(i);
            out.print("<tr>");
            for(int j=0;j<dataRow.size();j++){

                if((j == 0 || j == 1 || j == 2) && (dataRow.get(j).length() < 3 && dataRow.get(j).length() > 20) ){
                    out.print("<td  class=\"danger\">");
                    out.print("<p  data-toggle=\"tooltip\"  title=\"First name must be in the range 3-20 characters\">");

                    isValid = false;
                }else if(j == 3 && dbs.selectUserIfExist(dataRow.get(3))){
                    out.print("<td  class=\"danger\">");
                    out.print("<p  data-toggle=\"tooltip\"  title=\"username existed\">");

                    isValid = false;
                }else if(j == 5 && dbs.selectEmailIfExist(dataRow.get(5))){
                    out.print("<td class=\"danger\">");
                    out.print("<p  data-toggle=\"tooltip\"  title=\"email existed\">");
                    isValid = false;
                }else {
                    out.print("<td>");
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

    //String sheetDataId = UUID.randomUUID().toString();

    //request.removeAttribute("sheetData");

    //request.setAttribute("cData", dataArr);








%>
                    </table>



                <%
                    if(!isValid){
                        out.print("<p class=\"red\">You cannot upload these data because the data which indicated red may not be valid data due to:</br>" +
                                "<ul>" +
                                "   <li>users names must be in the range 3-20 characters.</li>\n" +
                                "   <li>users names must have only alphabetic letters.</li>\n" +
                                "   <li>users emails must be in valid email format.</li>\n" +
                                "   <li>username or email is already existed in the database.</li>\n" +
                                "</ul>\n" +
                                "                    please change the data in the sheet and click re-upload to upload it again. </p>");
                    }
                %>

                <form method="post" action="/upload/users" >
                    <a  class="btn btn-primary" href="/users/index.jsp?page=import">re-upload</a>
                    <input name="file" value="sheetData" hidden>

                    <%
                        if(isValid){
                            out.print("<button class=\"btn btn-primary\"  type=\"submit\">Upload</button>\n");
                        }else {
                            out.print("<script type=\"text/javascript\">\n" +
                                    "    $(window).load(function(){\n" +
                                    "       bootbox.alert(\"Some of the data are duplicated, please check the table below where red cells indicated\")\n" +
                                    "    });\n" +
                                    "</script>");
                        }
                    %>
                    <a href="/users/index.jsp" class="btn btn-default pull-right">Cancel</a>
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

