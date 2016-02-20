<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/13/2016
  Time: 6:24 PM
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
                <h2 class="text-center">Add Student</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("NValue")!=null) {out.print("Update");} else out.print("Enter");%> the student information</p>

                    <form name="myform" action="/AddStudent" method="post">

                        <div class="form-group">

                            <!-- Large button group -->
                            <div class="btn-group">

                                <input type="hidden" name="programName" value="<%=programName%>">
                                <input type="hidden" name="programID" value="<%=request.getParameter("programID")%>">
                                <input type="hidden" name="courseCode" value="<%=request.getParameter("courseCode")%>">
                                <input type="hidden" name="courseName" value="<%=request.getParameter("courseName")%>">
                                <input type="hidden" name="section" value="<%=request.getParameter("section")%>">
<%--
                                <input type="hidden" name="programName" value="<%=request.getParameter("programName")%>">
--%>
                                <input type="hidden" name="cycle" value="<%=id%>">
                                <input type="hidden" name="term" value="<%=Termid%>">

                                <%
                                    //System.out.println(request.getParameter("programName"));
                                    System.out.println(request.getParameter("programID"));
                                    System.out.println(request.getParameter("courseCode"));
                                    System.out.println(request.getParameter("courseName"));
                                    System.out.println(request.getParameter("section"));
                                    //System.out.println(request.getParameter("programName"));

                                %>
                                <label>Student ID: </label>
                                <input type="text" name="Student_ID" placeholder="Student ID" value="<%if (request.getParameter("IDValue")!=null) {out.print(request.getParameter("IDValue"));}%>">

                                <label>Student Name: </label>
                                <input type="text" name="Sname" placeholder="Student Name" value="<%if (request.getParameter("NValue")!=null) {out.print(request.getParameter("NValue"));}%>">
                            </div>
                        </div>


                        <div class="form-group">

                            <input type="hidden" name="NValue" value="<%=request.getParameter("NValue")%>">
                            <input type="hidden" name="S_ID" value="<%=request.getParameter("S_ID")%>">

                        </div>

                        <br>
                        <button class="btn btn-success btn-fill" type="submit"><%if (request.getParameter("NValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-success btn-primary" href="${pageContext.request.contextPath}/cycle/index.jsp?page=studentList&cycle=<%=request.getParameter("cycle")%>
                        &term=<%=request.getParameter("term")%>&programID=<%=request.getParameter("programID")%>&courseCode=<%=request.getParameter("courseCode")%>
                        &courseName=<%=request.getParameter("courseName")%>&section=<%=request.getParameter("section")%>">Cancel</a>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>