<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/15/2016
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<%

    String id = "";
    String Termid = "";
    if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
        id  = (String) request.getSession().getAttribute("id");
        Termid  = (String) request.getSession().getAttribute("Termid");
        out.println("id is : "+id);
        out.print("Termid is : "+Termid);
    }

%>

<%
    AS_Select aselect = new AS_Select();
    String programName = "";

    try {
        System.out.println("scscsc     "+Integer.parseInt(request.getParameter("programID")));
        programName = aselect.selectProgramName(Integer.parseInt(request.getParameter("programID")));

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>

<div class="main">
    <div class="section">
        <div class="container" id="space">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Course Information</h2>
                <h4 class="text-center">Program: <%out.print(programName);%></h4>
                <h4 class="text-center">Course: <%out.print(request.getParameter("courseCode"));%> | <%out.print(request.getParameter("courseName"));%> </h4>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p>*Click "Add" to add new student</p>

                    <div class="form-group">

                        <!-- Large button group -->
                        <div class="btn-group">

                            <input type="hidden" name="programName" value="<%=programName%>">
                        </div>
                    </div>

                    <div class="form-group">


                    </div>

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table">
                            <tr>
                                <th>Student ID</th>
                                <th>Name</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            <%

                                AS_Select bselect = new AS_Select();

                                try {
                                    ArrayList<ArrayList<String>> rs = bselect.selectStudents(Integer.parseInt(Termid));
                                    ArrayList<String> rsRow ;

                                    for (int i=0; i<rs.size();i++){
                                        rsRow = new ArrayList<String>();
                                        rsRow = rs.get(i);
                                        out.print("<tr>");
                                        for (int j=1; j<rsRow.size();j++) {
                                            out.print("<td>");
                                            out.print(rsRow.get(j));
                                            out.print("</td>");
                                        }
                                        out.print("<td>" +
                                                "                            <form method=\"post\" action=\"index.jsp\">\n" +
                                                "                            <input name=\"page\" value=\"updateStudent\" hidden />\n" +
                                                "                            <input name=\"S_ID\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"NValue\" value=\""+rsRow.get(1)+"\" hidden />\n" +
                                                "                            <input name=\"IDValue\" value=\""+rsRow.get(2)+"\" hidden />\n" +
                                                "                            <input name=\"programID\" value=\""+request.getParameter("programID")+"\" hidden />\n" +
                                                "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-warning btn-simple\"><i class=\"fa fa-pencil fa-2x \"></i></button>\n" +
                                                "                            </form>" +
                                                "                            </td>" +
                                                "                            <form method=\"post\" action=\"/DeletePILink\">\n" +
                                                "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                "                            <input name=\"LinkID\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"programID\" value=\""+request.getParameter("programID")+"\" hidden />\n" +
                                                "                               <td>" +
                                                "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-danger btn-simple\"><i class=\"fa fa-trash-o fa-2x \"></i></button>\n" +
                                                "                               </td>"+
                                                "                        </form>" +
                                                "</tr>");
                                    }

                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                            %>
                        </table>
                    </div>

                    <br>

                    <a class="btn btn-success btn-fill" href="index.jsp?page=addStudent&programID=<%=request.getParameter("programID")%>&courseCode=<%=request.getParameter("courseCode")%>&courseName=<%=request.getParameter("courseName")%>&F_ID=<%=request.getParameter("F_ID")%>&programName=<%=request.getParameter("programName")%>">Add</a>
                    <a class="btn btn-success btn-primary" href="index.jsp?page=piList">Cancel</a>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>