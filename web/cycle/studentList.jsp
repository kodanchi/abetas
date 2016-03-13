<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%--
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
    if(request.getParameter("cycle") != null && request.getParameter("term") != null){
        id  = request.getParameter("cycle");
        Termid  = request.getParameter("term");
        out.println("id is : "+id);
        out.print("Termid is : "+Termid);
    }

%>

<%
    C_AS_Select aselect = new C_AS_Select();
    String programName = "";

    try {
        System.out.println("scscsc     "+Integer.parseInt(request.getParameter("programID")));
        programName = aselect.selectProgramName(Integer.parseInt(request.getParameter("programID")));

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    String facultyName = "";

    try {
        System.out.println("scscsc     "+Integer.parseInt(request.getParameter("section")));
        facultyName = aselect.selectSectionFaculty(Integer.parseInt(request.getParameter("section")));

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>


<%

    if(request.getParameter("status") != null){

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
                "        $('#errModal').modal('show');\n" +
                "    });\n" +
                "    function goToNormal(){\n" +
                "        window.location.href =\"/program/\";\n" +
                "    }\n" +
                "</script>" +
                "<!-- Modal -->\n" +
                "                    <div class=\"modal fade\" id=\"errModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
                "                        <div class=\"modal-dialog\">\n" +
                "                            <div class=\"modal-content\">\n" +
                "                                <div class=\"modal-header\">\n" +
                "                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n" +
                "                                    <h4 class=\"modal-title\" id=\"myModalLabel\">INFO</h4>\n" +
                "                                </div>\n" +
                "                                <div class=\"modal-body\">\n");
        if(request.getParameter("status").equals("Success")){
            out.print("All user were added to the database Successfully.");
        } else if(request.getParameter("status").equals("failed")){
            out.print("Something wrong!, please try again.");
        }else if(request.getParameter("status").equals("userAdded")){
            out.print("User was added successfully!");
        }else if(request.getParameter("status").equals("userUpdated")){
            out.print("User was updated successfully!");
        }else if(request.getParameter("status").equals("userDeleted")){
            out.print("User was deleted successfully!");
        }
        out.print("                                </div>\n" +
                "                                <div class=\"modal-footer\">\n" +
                "\n" +
                "                                    <div class=\"text-center\">\n" +
                "                                        <a type=\"button\"  data-dismiss=\"modal\"   class=\"btn btn-default btn-simple\">OK</a>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>");


    }

%>

        <div class="container" id="space">
            <!--  Here is row -->
            <div class="row">
                <h2 class="text-center">Section Information</h2>
                <h4 class="text-center">Program: <%out.print(programName);%></h4>
                <h4 class="text-center">Course: <%out.print(request.getParameter("courseCode"));%> | <%out.print(request.getParameter("courseName"));%> </h4>
                <h4 class="text-center">Section: <%out.print(request.getParameter("section"));%> | Faculty: <%out.print(facultyName);%> </h4>
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
                        <table class="table table-hover table-striped table-bordered text-center">
                            <tr>
                                <th>Name</th>
                                <th>Student ID</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            <%

                                C_AS_Select bselect = new C_AS_Select();

                                try {
                                    ArrayList<ArrayList<String>> rs = bselect.selectStudents(Integer.parseInt(request.getParameter("section")));
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
                                                "                            <input name=\"cycle\" value=\""+id+"\" hidden />\n" +
                                                "                            <input name=\"term\" value=\""+Termid+"\" hidden />\n" +
                                                "                            <input name=\"section\" value=\""+request.getParameter("section")+"\" hidden />\n" +
                                                "                            <input name=\"courseCode\" value=\""+request.getParameter("courseCode")+"\" hidden />\n" +
                                                "                            <input name=\"courseName\" value=\""+request.getParameter("courseName")+"\" hidden />\n" +
                                                "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                "                            </form>" +
                                                "                            </td><td>" +
                                                "                            <form method=\"post\" action=\"/DeleteStudent\">\n" +
                                                "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                "                            <input name=\"S_ID\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"programID\" value=\""+request.getParameter("programID")+"\" hidden />\n" +
                                                "                            <input name=\"cycle\" value=\""+id+"\" hidden />\n" +
                                                "                            <input name=\"term\" value=\""+Termid+"\" hidden />\n" +
                                                "                            <input name=\"section\" value=\""+request.getParameter("section")+"\" hidden />\n" +
                                                "                            <input name=\"courseCode\" value=\""+request.getParameter("courseCode")+"\" hidden />\n" +
                                                "                            <input name=\"courseName\" value=\""+request.getParameter("courseName")+"\" hidden />\n" +
                                                "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-link btn-T \"><i class=\"fui-trash icon30\"></i></button>\n" +
                                                "                        </form></td>" +
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

                    <a class="btn btn-success btn-fill" data-toggle="modal" data-target="#addModal" >Add</a>

                    <a class="btn btn-primary pull-right"  href="index.jsp?page=CourseInfo&cycle=<%=id%>&term=<%=Termid%>&programID=<%=request.getParameter("programID")%>&courseCode=<%=request.getParameter("courseCode")%>" >Finish</a>


                    <!-- Modal -->
                    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">Add Students</h4>
                                </div>
                                <div class="modal-body">
                                    If you have the students details in an Excel sheet, you can import the file to add them all at once
                                </div>
                                <div class="modal-footer">
                                    <div class="left-side">
                                        <a type="button" href="index.jsp?page=addStudent&cycle=<%=id%>&term=<%=Termid%>&programID=<%=request.getParameter("programID")%>&courseCode=<%=request.getParameter("courseCode")%>&courseName=<%=request.getParameter("courseName")%>&section=<%=request.getParameter("section")%>"  class="btn btn-default btn-simple">Enter manually</a>
                                    </div>
                                    <div class="divider"></div>
                                    <div class="right-side">
                                        <a type="button" href="index.jsp?&cycle=<%=id%>&term=<%=Termid%>&programID=<%=request.getParameter("programID")%>&courseCode=<%=request.getParameter("courseCode")%>&courseName=<%=request.getParameter("courseName")%>&section=<%=request.getParameter("section")%>&page=import&data=students"   class="btn btn-default btn-simple">Import Excel file</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

        </div>
