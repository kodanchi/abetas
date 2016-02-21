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
<%@ page import="ASDB.ImportSheet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 2/1/2016
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/4253660/passing-object-from-jsp-to-servlet
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id ="";
    String name ="";
    String dataType = "";
    if(request.getParameter("data") != null && request.getParameter("id") != null){
        id = request.getParameter("id");
        name = request.getParameter("name");
        dataType = request.getParameter("data");

    }
%>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<div class="section">
    <div class="container">
        <!--         what is row -->
        <div class="row tim-row">
            <h2 class="text-center">User Management</h2>
            <legend></legend>
            <div class="col-md-8 col-md-offset-2">
                <p>Please confirm that the imported data are as intended, you can click re-upload to discard the current imported file and import it again.</p>
                <div class="panel panel-default">
                    <!-- Default panel contents -->

                    <!-- Table -->
                    <table class="table">
                        <tr>
                            <%
                                if(dataType.equals("obj")){
                                    out.print("<th>Objective</th>");
                                }else if (dataType.equals("outcomes")){
                                    out.print("<th>Outcomes</th>");
                                }else if(dataType.equals("course")){

                                }
                            %>
                        </tr>

<%

    /*if(request.getAttribute("sheetData")== null){
        response.getHeader("index.jsp");
    }else if(request.getMethod().equals("post") && request.getParameter("file")!= null){
        *//*System.out.println("inside if :"+request.getParameter("file"));
        //ArrayList<ArrayList<String>> dataArr = (ArrayList<ArrayList<String>>) request.getAttribute("sheetData");
        //request.setAttribute("Data", dataArr);
        //request.getRequestDispatcher("/").forward(request, response);
        //RequestDispatcher rd = request.getRequestDispatcher("/upload/users");

        try {
            rd.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }*//*
    }*/
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
            out.print("<td>");
            out.print(dataRow.get(j));
            out.print("</td>");
        }
        out.print("</tr>");
    }

    //String sheetDataId = UUID.randomUUID().toString();

    //request.removeAttribute("sheetData");

    //request.setAttribute("cData", dataArr);








%>
                    </table>
                </div>

                <form method="post" action="/upload/program" >
                    <a class="btn btn-success btn-fill" href="/program/index.jsp?name=<%=name%>&id=<%=id%>&page=import&data=<%=dataType%>">re-upload</a>
                    <input name="data-type" value="<%=dataType%>" hidden>
                    <input name="name" value="<%=name%>" hidden>
                    <input name="id" value="<%=id%>" hidden>
                    <button class="btn btn-primary"  type="submit">Upload</button>
                    <a href="/program/index.jsp?name=<%=name%>&id=<%=id%>&page=ObjList" class="btn btn-primary">Cancel</a>
                </form>




                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>


    </div>
</div>

