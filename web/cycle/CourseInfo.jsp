<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/13/2016
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>


<%

    String id = "";
    String Termid = "";
    if(request.getParameter("cycle") != null && request.getParameter("term") != null){
        id  = request.getParameter("cycle");
        Termid  = request.getParameter("term");
    }

%>

<%
    C_AS_Select aselect = new C_AS_Select();
    String programName = "";
    String courseName = "";
    String courseCode = request.getParameter("courseCode");

    try {
        System.out.println("programID     "+Integer.parseInt(request.getParameter("programID")));
        programName = aselect.selectProgramName(Integer.parseInt(request.getParameter("programID")));
        courseName = aselect.selectCourseName(request.getParameter("courseCode"));

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>

        <div class="container" id="space">
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Course Information</h2>
                <h6 class="text-center">Program: <%out.print(programName);%></h6>
                <h6 class="text-center">Course: <%out.print(courseCode);%> | <%out.print(courseName);%> </h6>

                <div class="col-md-10 col-md-offset-1">

                    <h4 class="text-center">Sections</h4>

                    <div class="input-group"> <span class="input-group-addon">Filter</span>

                        <input id="filter" type="text" class="form-control" placeholder=" by section ID or faculty ">
                    </div>
                        <!-- Table -->
                        <table class="table table-hover table-striped table-bordered text-center">
                            <tr>

                                <th>Section ID</th>
                                <th>Faculty</th>
                                <th>Edit</th>
                                <th>Delete</th>

                            </tr>
                            <tbody class="searchable">
                                <%


                                    if(request.getParameter("programID")!=null) {

                                        String facultyName = null;

                                        //C_AS_Select aselect = new C_AS_Select();
                                        try {
                                            ArrayList<ArrayList<String>> rs = aselect.selectCourseSections(Integer.parseInt(request.getParameter("term")),request.getParameter("courseCode"));
                                            ArrayList<String> rsRow;

                                            for (int i = 0; i < rs.size(); i++) {
                                                rsRow = new ArrayList<String>();
                                                rsRow = rs.get(i);
                                                out.print("<tr>");
                                                for (int j = 0; j < rsRow.size() - 1; j++) {
                                                    out.print("<td>" + rsRow.get(j) + "</td>");
                                                }

                                                facultyName = aselect.selectSectionFaculty(Integer.parseInt(rsRow.get(0)));
                                                out.print("<td>" + facultyName + "</td>");
                                                out.print("<td>" +
                                                        "                            <form method=\"post\" action=\"index.jsp\">\n" +
                                                        "                            <input name=\"page\" value=\"updateSection\" hidden />\n" +
                                                        "                            <input name=\"section\" value=\"" + rsRow.get(0) + "\" hidden />\n" +
                                                        "                            <input name=\"cycle\" value=\"" + id + "\" hidden />\n" +
                                                        "                            <input name=\"term\" value=\"" + Termid + "\" hidden />\n" +
                                                        "                            <input name=\"courseCode\" value=\"" + courseCode + "\" hidden />\n" +
                                                        "                            <input name=\"courseName\" value=\"" + courseName + "\" hidden />\n" +
                                                        "                            <input name=\"programID\" value=\"" + request.getParameter("programID") + "\" hidden />\n" +
                                                        "                            <input name=\"programName\" value=\"" + programName + "\" hidden />\n" +
                                                        "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                        "                               </td>" +
                                                        "                            <td></form>" +
                                                        "                            <form method=\"post\" class=\"delForm\" action=\"/DeleteSection\">\n" +
                                                        "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                        "                            <input name=\"section\" value=\"" + rsRow.get(0) + "\" hidden />\n" +
                                                        "                            <input name=\"cycle\" value=\"" + id + "\" hidden />\n" +
                                                        "                            <input name=\"term\" value=\"" + Termid + "\" hidden />\n" +
                                                        "                            <input name=\"courseCode\" value=\"" + courseCode + "\" hidden />\n" +
                                                        "                            <input name=\"courseName\" value=\"" + courseName + "\" hidden />\n" +
                                                        "                            <input name=\"programID\" value=\"" + request.getParameter("programID") + "\" hidden />\n" +
                                                        "                            <input name=\"programName\" value=\"" + programName + "\" hidden />\n" +
                                                        "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-link btn-T \"><i class=\"fui-trash icon30\"></i></button>\n" +
                                                        "                        </form></td>" +
                                                        "</tr>");
                                            }

                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        System.out.println("  yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy           "+ request.getParameter("programID"));
                                    }else {
                                        System.out.println("  gsgsgsg    gsgsggssdfgs       djskvdsj    sgsgs   sgsgsgsg   fsdsdg            ");
                                    }

                                %>
                            </tbody>

                        </table>
                    </div>
                    <div class="col-md-10 col-md-offset-1">

                        <form name="myform" action="index.jsp" method="post">

                            <%--<div class="col-xl-50 selectContainer" >
                                <p>Select a faculty member to teach this course:</p>
                                <select class="form-control" name="F_ID" required>
                                    <%
                                        C_AS_Select cselect = new C_AS_Select();
                                        try {
                                            ArrayList<String> rs = cselect.selectFacultyForCourse();

                                            for (int i=0; i<rs.size();i++) {
                                                out.print("<option value="+rs.get(i).substring(0, rs.get(i).indexOf(':'))+">"+rs.get(i).replaceAll(".*:", "")+"</option>");
                                            }
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    %>
                                </select>

                            </div>--%>

                                <input type="hidden" name="page" value="addSection">
                                <input type="hidden" name="cycle" value="<%=id%>">
                                <input type="hidden" name="term" value="<%=Termid%>">
                                <input type="hidden" name="courseCode" value="<%=courseCode%>">
                                <input type="hidden" name="courseName" value="<%=courseName%>">
                                <input type="hidden" name="programID" value="<%=request.getParameter("programID")%>">
                                <input type="hidden" name="programName" value="<%=programName%>">

                            <%--<div class="panel panel-default">

                            </div>
                            <p>*Click "Next" to add student</p>--%>
                            <button class="btn btn-primary pull-left" type="submit">Add</button>

                        </form>

                        <a class="btn btn-primary pull-right" href="index.jsp?page=includeCourse&cycle=<%=id%>&term=<%=Termid%>&programID=<%=request.getParameter("programID")%>">Finish</a>



                        <!-- End of col -->
                    </div>


                <!-- End of row -->
            </div>


        </div>
