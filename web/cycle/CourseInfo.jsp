<%@ page import="ASDB.AS_Select" %>
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
    AS_Select aselect = new AS_Select();
    String programName = "";
    String courseName = "";
    String courseCode = request.getParameter("CourseValue");

    try {
        System.out.println("scscsc     "+Integer.parseInt(request.getParameter("programID")));
        programName = aselect.selectProgramName(Integer.parseInt(request.getParameter("programID")));
        courseName = aselect.selectCourseName(request.getParameter("CourseValue"));

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
                <h4 class="text-center">Course: <%out.print(courseCode);%> | <%out.print(courseName);%> </h4>
                <legend></legend>

                <div class="col-md-10 col-md-offset-1">

                    <h3 class="text-center">Sections</h3>

                <!-- Table -->
                <table class="table">
                    <tr>

                        <th>Section ID</th>
                        <th>Faculty</th>
                        <th>Edit</th>
                        <th>Delete</th>

                    </tr>
                    <%


                        if(request.getParameter("programID")!=null) {

                            //AS_Select aselect = new AS_Select();
                            try {
                                ArrayList<ArrayList<String>> rs = aselect.selectCourseSections(Integer.parseInt(request.getParameter("term")),request.getParameter("CourseValue"));
                                ArrayList<String> rsRow;

                                for (int i = 0; i < rs.size(); i++) {
                                    rsRow = new ArrayList<String>();
                                    rsRow = rs.get(i);
                                    out.print("<tr>");
                                    for (int j = 0; j < rsRow.size(); j++) {
                                        out.print("<td>" + rsRow.get(j) + "</td>");
                                    }
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
                                            "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-warning btn-simple\"><i class=\"fa fa-pencil fa-2x \"></i></button>\n" +
                                            "                               </td>" +
                                            "                            </form>" +
                                            "                            <form method=\"post\" action=\"/DeletePI\">\n" +
                                            "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                            "                            <input name=\"PILabel\" value=\"" + rsRow.get(0) + "\" hidden />\n" +
                                            "                            <input name=\"programID\" value=\"" + request.getParameter("programID") + "\" hidden />\n" +
                                            "                               <td>" +
                                            "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-danger btn-simple\"><i class=\"fa fa-trash-o fa-2x \"></i></button>\n" +
                                            "                               </td>" +
                                            "                        </form>" +
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

                </table>
                    </div>
                    <div class="col-md-10 col-md-offset-1">

                        <form name="myform" action="index.jsp" method="post">

                            <%--<div class="col-xl-50 selectContainer" >
                                <p>Select a faculty member to teach this course:</p>
                                <select class="form-control" name="F_ID" required>
                                    <%
                                        AS_Select cselect = new AS_Select();
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
                            <button class="btn btn-success btn-fill pull-left" type="submit">Add</button>

                        </form>

                        <a class="btn btn-success btn-fill pull-right" type="submit">Next</a>
                        <a class="btn btn-success btn-primary pull-right" href="index.jsp?page=piList&cycle=<%=id%>&term=<%=Termid%>">Back</a>




                        <!-- End of col -->
                    </div>


                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>