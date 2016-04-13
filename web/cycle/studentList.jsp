<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>
<script src="/js/stupidtable.min.js" type="text/javascript"></script>


<%

    /**
     * used to display students list page
     */



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

    try {
        programName = aselect.selectProgramName(Integer.parseInt(request.getParameter("programID")));

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    String facultyName = "";

    try {
        facultyName = aselect.selectSectionFaculty(Integer.parseInt(request.getParameter("section")));

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>


<%

    if(request.getParameter("status") != null){

        out.print("<script type=\"text/javascript\">\n" +
                "    $(window).load(function(){\n" +
                "       bootbox.alert(\""+request.getParameter("status")+"\")\n" +
                "    });\n" +
                "</script>");
    }

%>

        <div class="container" id="space">

            <div class="row">
                <div class="col-md-7 col-md-offset-2">
                    <nav>
                        <ol class="cd-breadcrumb triangle small">
                            <li ><em>Faculty member</em></li>
                            <li class="current"><em>Students</em></li>
                        </ol>
                    </nav>
                </div>
            </div>

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
                        <div class="input-group"> <span class="input-group-addon">Filter</span>

                            <input id="filter" type="text" class="form-control" placeholder=" by student name or ID ">
                        </div>
                        <!-- Table -->
                        <table class="table table-hover table-striped table-bordered text-center" id="sTable">
                            <thead>
                            <tr>
                                <th data-sort="string">Name</th>
                                <th data-sort="int">Student ID</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <tbody class="searchable">
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
                                                    "                            <form method=\"post\" class=\"delForm\" action=\"/DeleteStudent\">\n" +
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
                            </tbody>
                        </table>
                    </div>

                    <br>

                    <a class="btn btn-primary" onclick="importPopup('index.jsp?&cycle=<%=id%>&term=<%=Termid%>&programID=<%=request.getParameter("programID")%>&courseCode=<%=request.getParameter("courseCode")%>&courseName=<%=request.getParameter("courseName")%>&section=<%=request.getParameter("section")%>&page=import&data=students',
                            'index.jsp?page=addStudent&cycle=<%=id%>&term=<%=Termid%>&programID=<%=request.getParameter("programID")%>&courseCode=<%=request.getParameter("courseCode")%>&courseName=<%=request.getParameter("courseName")%>&section=<%=request.getParameter("section")%>',
                            'Add new student',
                            'If you have the students details in an Excel sheet, you can import the file to add them all at once')" >Add</a>

                    <a class="btn btn-primary pull-right"  href="index.jsp?page=CourseInfo&cycle=<%=id%>&term=<%=Termid%>&programID=<%=request.getParameter("programID")%>&courseCode=<%=request.getParameter("courseCode")%>" >Finish</a>

                    <script>
                        $("#sTable").stupidtable();
                    </script>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

        </div>
