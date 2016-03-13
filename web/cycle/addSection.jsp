<%@ page import="ASDB.C_AS_Select" %>
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
    String sectionId = request.getParameter("section");
    System.out.println("SID : "+sectionId);

    int fid = 0;
    if(request.getParameter("section") != null){


        try {
            System.out.println("scscsc     "+Integer.parseInt(request.getParameter("section")));
            fid = aselect.selectSectionFacultyID(Integer.parseInt(request.getParameter("section")));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>

        <div class="container" id="space">
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center"><%if (request.getParameter("section")!=null) {
                    out.print("Update");
                } else {
                    out.print("Add");
                }%> Section</h2>
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("section")!=null) {
                        out.print("Update");
                    } else {
                        out.print("Enter");
                    }%> the student information</p>

                    <form name="myform" action="/AddSection" method="post">

                        <div class="form-group">

                            <!-- Large button group -->


                                <input type="hidden" name="programName" value="<%=programName%>">
                                <input type="hidden" name="programID" value="<%=request.getParameter("programID")%>">
                                <input type="hidden" name="courseCode" value="<%=request.getParameter("courseCode")%>">
                                <input type="hidden" name="courseName" value="<%=request.getParameter("courseName")%>">
                                <input type="hidden" name="programName" value="<%=request.getParameter("programName")%>">
                                <input type="hidden" name="section" value="<%=sectionId%>">
                                <input type="hidden" name="cycle" value="<%=id%>">
                                <input type="hidden" name="term" value="<%=Termid%>">



                                <%
                                    System.out.println(request.getParameter("programName"));
                                    System.out.println(request.getParameter("programID"));
                                    System.out.println(request.getParameter("courseCode"));
                                    System.out.println(request.getParameter("courseName"));
                                    System.out.println(request.getParameter("programName"));

                                %>


                            <div class="form-group">

                                        <p>Select a faculty member to teach this course:</p>
                                        <select class="form-control" name="F_ID" required>
                                            <%
                                                C_AS_Select cselect = new C_AS_Select();
                                                try {
                                                    ArrayList<String> rs = cselect.selectFacultyForCourse();

                                                    for (int i=0; i<rs.size();i++) {
                                                        out.print("<option value="+rs.get(i).substring(0, rs.get(i).indexOf(':')));

                                                        System.out.println("fid : "+fid + "  "+ rs.get(i).substring(0, rs.get(i).indexOf(':')));
                                                        if(Integer.parseInt(rs.get(i).substring(0, rs.get(i).indexOf(':'))) == fid){
                                                           out.print(" selected ");
                                                        }
                                                        out.print(">"+rs.get(i).replaceAll(".*:", "")+"</option>");
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



                        <div class="form-group">

                            <input type="hidden" name="NValue" value="<%=request.getParameter("section")%>">

                        </div>

                        <br>
                        <button class="btn btn-primary " type="submit"><%if (request.getParameter("section")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-default pull-right" href="index.jsp?page=CourseInfo&cycle=<%=id%>&term=<%=Termid%>">Cancel</a>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

        </div>
