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


                    <form name="myform" action="index.jsp?page=studentList" method="post">

                    <div class="col-xl-50 selectContainer" >
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

                    </div>

                        <br>
                    <div class="form-group">

                        <input type="hidden" name="courseCode" value="<%=courseCode%>">
                        <input type="hidden" name="courseName" value="<%=courseName%>">
                        <input type="hidden" name="programID" value="<%=request.getParameter("programID")%>">
                        <input type="hidden" name="programName" value="<%=programName%>">

                    </div>

                    <div class="panel panel-default">

                    </div>
                    <p>*Click "Next" to add student</p>
                    <br>
                        <button class="btn btn-success btn-fill" type="submit">Next</button>
                        <a class="btn btn-success btn-primary" href="index.jsp?page=piList">Cancel</a>

                    </form>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>