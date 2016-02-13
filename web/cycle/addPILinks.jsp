<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/11/2016
  Time: 4:41 PM
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
                <h2 class="text-center">Performance Indicator Links</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p>Select a student outcome, performance indicator, course and type of form link them for <%out.print(programName);%> program:</p>

                    <form name="myform" action="/AddPILinks" method="post">

                        <div class="form-group">

                            <label>Student Outcome: </label>

                            <div class="btn-group">

                                <input type="hidden" name="LinkID" value="<%=request.getParameter("LinkID")%>">
                                <input type="hidden" name="OutValue" value="<%=request.getParameter("OutValue")%>">
                                <input type="hidden" name="PIValue" value="<%=request.getParameter("PIValue")%>">
                                <input type="hidden" name="CourseValue" value="<%=request.getParameter("CourseValue")%>">
                                <input type="hidden" name="TypeValue" value="<%=request.getParameter("TypeValue")%>">
                                <input type="hidden" name="PValue" value="<%=request.getParameter("PValue")%>">
                                <input type="hidden" name="TypeValue" value="<%=request.getParameter("TypeValue")%>">
                                <input type="hidden" name="RubricValue" value="<%=request.getParameter("RubricValue")%>">
                                <input type="hidden" name="TermValue" value="<%=request.getParameter("TermValue")%>">
                                <input type="hidden" name="programID" value="<%=request.getParameter("programID")%>">

                                <div class="col-xl-50 selectContainer" >
                                    <select class="form-control" name="Out">
                                        <%
                                            AS_Select bselect = new AS_Select();
                                            try {
                                                ArrayList<String> rs = bselect.selectOutForLink(Integer.parseInt(request.getParameter("programID")));

                                                for (int i=0; i<rs.size();i++) {
                                                    out.print("<option value="+rs.get(i).substring(0, rs.get(i).indexOf(':'))+">"+rs.get(i)+"</option>");
                                                }
                                            } catch (ClassNotFoundException e) {
                                                e.printStackTrace();
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>
                        </div>

                            <div class="form-group">

                                <label>Performance Indicator: </label>

                                <div class="btn-group">

                                    <div class="col-xl-25 selectContainer" >
                                        <select class="form-control" name="PI">
                                            <%
                                                AS_Select cselect = new AS_Select();
                                                try {
                                                    ArrayList<String> rs = cselect.selectPIForLink(Integer.parseInt(request.getParameter("programID")));

                                                    for (int i=0; i<rs.size();i++) {
                                                        out.print("<option value="+rs.get(i).substring(0, rs.get(i).indexOf(':'))+">"+rs.get(i)+"</option>");
                                                    }
                                                } catch (ClassNotFoundException e) {
                                                    e.printStackTrace();
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                            </div>

                        <div class="form-group">

                            <label>Course: </label>

                            <div class="btn-group">

                                <div class="col-xl-25 selectContainer" >
                                    <select class="form-control" name="Course">
                                        <%
                                            AS_Select dselect = new AS_Select();
                                            try {
                                                ArrayList<String> rs = dselect.selectCourseForLink(Integer.parseInt(request.getParameter("programID")));

                                                for (int i=0; i<rs.size();i++) {
                                                    out.print("<option value="+rs.get(i).substring(0, rs.get(i).indexOf(':'))+">"+rs.get(i)+"</option>");
                                                    System.out.println("dsffjkfsfkdsnjfkdsndjsknfjksdjdkfnjsksdjkfndsjknfdjksdfnkfsdkfdjsfsdkfnsjdfn         "+rs.get(i).substring(0, rs.get(i).indexOf(':')));
                                                }
                                            } catch (ClassNotFoundException e) {
                                                e.printStackTrace();
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">

                            <label>Type: </label>

                            <div class="btn-group">

                                <div class="col-xl-25 selectContainer" >
                                    <select class="form-control" name="Type">
                                        <option value="Summative">Summative</option>
                                        <option value="Formative">Formative</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="row tim-row">
                            <h2 class="text-center">Add Rubrics</h2>
                            <legend></legend>
                                <p>You need to enter the four rubrics and their details</p>

                                    <div class="form-group">
                                        <label>First rubrics</label>
                                        <input type="text" class="form-control" size="25" name="firstR" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <textarea class="form-control" rows="3" name="firstD" required></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label>Second rubrics</label>
                                        <input type="text" class="form-control" size="25" name="secondR" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <textarea class="form-control" rows="3" name="secondD" required></textarea>
                                    </div>

                                    <div class="form-group">
                                        <label>Third rubrics</label>
                                        <input type="text" class="form-control" size="25" name="thirdR" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <textarea class="form-control" rows="3" name="thirdD" required></textarea>
                                    </div>

                                    <div class="form-group">
                                        <label>Forth rubrics</label>
                                        <input type="text" class="form-control" size="25" name="forthR" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <textarea class="form-control" rows="3" name="forthD" required></textarea>
                                    </div>

                            <!-- End of row -->
                        </div>

                        <br>

                        <button class="btn btn-success btn-fill" type="submit"><%if (request.getParameter("OutValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-success btn-primary" href="index.jsp?page=LinkPIOutList&name=<%=request.getParameter("programID")%>">Cancel</a>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>
