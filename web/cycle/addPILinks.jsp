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
                                <input type="hidden" name="cycle" value="<%=id%>">
                                <input type="hidden" name="term" value="<%=Termid%>">

                                <div class="col-xl-50 selectContainer" >
                                    <select class="form-control" name="Out">
                                        <%
                                            AS_Select bselect = new AS_Select();
                                            try {
                                                ArrayList<String> rs = bselect.selectOutForLink(Integer.parseInt(request.getParameter("programID")));

                                                for (int i=0; i<rs.size();i++) {
                                                    out.print("<option value=\""+rs.get(i).substring(0, rs.get(i).indexOf(':')));
                                                    out.print("\"");
                                                    if (request.getParameter("OutValue") != null && rs.get(i).substring(0, rs.get(i).indexOf(':')).equals(request.getParameter("OutValue"))) {
                                                        System.out.println("fdvfdfdgdbfjkvbjk njkrenjk nejrklnj jkl njkln gjklng jkngjlk nglknl gknkl kn krenrklelkg klnlg nreklnrklenjreklgnklregklgn kl grgrre");
                                                        out.print(" selected");
                                                    }
                                                    out.print(">");
                                                    out.print(rs.get(i)+"</option>");
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

                                                        out.print("<option value=\""+rs.get(i).substring(0, rs.get(i).indexOf(':')));
                                                        out.print("\"");
                                                        if (request.getParameter("PIValue") != null && rs.get(i).substring(0, rs.get(i).indexOf(':')).equals(request.getParameter("PIValue"))) {
                                                            System.out.println("fdvfdfdgdbfjkvbjk njkrenjk nejrklnj jkl njkln gjklng jkngjlk nglknl gknkl kn krenrklelkg klnlg nreklnrklenjreklgnklregklgn kl grgrre");
                                                            out.print(" selected");
                                                        }
                                                        out.print(">");
                                                        out.print(rs.get(i)+"</option>");
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


                                                    out.print("<option value=\""+rs.get(i).substring(0, rs.get(i).indexOf(':')));
                                                    out.print("\"");
                                                    if (request.getParameter("CourseValue") != null && rs.get(i).substring(0, rs.get(i).indexOf(':')).equals(request.getParameter("CourseValue"))) {
                                                        System.out.println("fdvfdfdgdbfjkvbjk njkrenjk nejrklnj jkl njkln gjklng jkngjlk nglknl gknkl kn krenrklelkg klnlg nreklnrklenjreklgnklregklgn kl grgrre");
                                                        out.print(" selected");
                                                    }
                                                    out.print(">");
                                                    out.print(rs.get(i)+"</option>");


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
                                        <option value="Summative"
                                        <% if (request.getParameter("TypeValue") != null && "Summative".equals(request.getParameter("TypeValue"))) {
                                            out.print(" selected");
                                        }
                                            out.print(">");
                                            out.print("Summative </option>\"");

                                        %>
                                        <option value="Formative"
                                        <% if (request.getParameter("TypeValue") != null && "Formative".equals(request.getParameter("TypeValue"))) {
                                            out.print(" selected");
                                        }
                                            out.print(">");
                                            out.print("Formative </option>\"");

                                        %>

                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="row tim-row">
                            <h2 class="text-center">Add Rubrics</h2>
                            <legend></legend>
                                <p>You need to enter the four rubrics and their details</p>
                            <%
                                String A="";
                                String B="";
                                String C="";
                                String D="";
                                String E="";
                                String F="";
                                String G="";
                                String H="";

                                if (request.getParameter("RubricValue")!=null) {
                                    AS_Select eselect = new AS_Select();
                                    try {
                                        ArrayList<String> rss = eselect.selectRubrics(Integer.parseInt(request.getParameter("RubricValue")));

                                        A=rss.get(0);
                                        B=rss.get(1);
                                        C=rss.get(2);
                                        D=rss.get(3);
                                        E=rss.get(4);
                                        F=rss.get(5);
                                        G=rss.get(6);
                                        H=rss.get(7);

                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            %>
                                    <div class="form-group">
                                        <label>First rubrics</label>
                                        <input type="text" class="form-control" size="25" name="firstR" value="<%if (request.getParameter("RubricValue")!=null) {out.print(A);}%>" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <textarea class="form-control" rows="3" name="firstD" required><%if (request.getParameter("RubricValue")!=null) {out.print(B);}%></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label>Second rubrics</label>
                                        <input type="text" class="form-control" size="25" name="secondR" value="<%if (request.getParameter("RubricValue")!=null) {out.print(C);}%>" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <textarea class="form-control" rows="3" name="secondD" required><%if (request.getParameter("RubricValue")!=null) {out.print(D);}%></textarea>
                                    </div>

                                    <div class="form-group">
                                        <label>Third rubrics</label>
                                        <input type="text" class="form-control" size="25" name="thirdR" value="<%if (request.getParameter("RubricValue")!=null) {out.print(E);}%>" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <textarea class="form-control" rows="3" name="thirdD" required><%if (request.getParameter("RubricValue")!=null) {out.print(F);}%></textarea>
                                    </div>

                                    <div class="form-group">
                                        <label>Forth rubrics</label>
                                        <input type="text" class="form-control" size="25" name="forthR" value="<%if (request.getParameter("RubricValue")!=null) {out.print(G);}%>" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <textarea class="form-control" rows="3" name="forthD" required><%if (request.getParameter("RubricValue")!=null) {out.print(H);}%></textarea>
                                    </div>

                            <!-- End of row -->
                        </div>

                        <br>

                        <button class="btn btn-success btn-fill" type="submit"><%if (request.getParameter("OutValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-success btn-primary" >Cancel</a>
                        <button class="btn btn-primary pull-right" href="index.jsp?page=LinkPIOutList&cycle=<%=id%>&term=<%=Termid%>&name=<%=request.getParameter("programID")%>" >Finish</button>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>
