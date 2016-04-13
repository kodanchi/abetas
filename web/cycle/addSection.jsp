<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<%

    /**
     * used to display add section for a course.
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
    String sectionId = request.getParameter("section");

    int fid = 0;
    if(request.getParameter("section") != null){


        try {
            fid = aselect.selectSectionFacultyID(Integer.parseInt(request.getParameter("section")));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>

        <div class="container" id="space">

            <div class="row">
                <div class="col-md-7 col-md-offset-2">
                    <nav>
                        <ol class="cd-breadcrumb triangle small">
                            <li class="current"><em>Faculty member</em></li>
                            <li ><em>Students</em></li>
                        </ol>
                    </nav>
                </div>
            </div>
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
                        out.print("Select");
                    }%> Faculty member</p>

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



                            <div class="form-group">

                                <p>Select a faculty member to teach this course:</p>
                                <select class="form-control" name="F_ID" required>
                                    <%
                                        C_AS_Select cselect = new C_AS_Select();
                                        try {
                                            ArrayList<String> rs = cselect.selectFacultyForCourse();

                                            for (int i=0; i<rs.size();i++) {
                                                out.print("<option value="+rs.get(i).substring(0, rs.get(i).indexOf(':')));

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
                        <a class="btn btn-default pull-right" href="index.jsp?page=CourseInfo&cycle=<%=id%>&term=<%=Termid%>&programID=<%=request.getParameter("programID")%>&courseCode=<%=request.getParameter("courseCode")%>">Cancel</a>

                        <button class="btn btn-primary pull-right" type="submit"><%if (request.getParameter("section")!=null) {out.print("Next");} else out.print("Next");%></button>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

        </div>
