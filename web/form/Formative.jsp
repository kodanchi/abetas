<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="FDB.F_Select" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/21/2016
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<%
    String F_written_rubic = "";
    String F_instructor_feedback_comment = "";
    String F_instructor_feedback_obstacle = "";
    String F_instructor_feedback_improvement = "";
    String F_evidence = "";
        F_Select dbs = new F_Select();
        ArrayList<String> fData = new ArrayList<String>();
        try {
            fData = dbs.selectFormative(Integer.parseInt(request.getParameter("Formative_ID")));
            if(fData != null){
                F_written_rubic = fData.get(0);
                F_instructor_feedback_comment = fData.get(1);
                F_instructor_feedback_obstacle = fData.get(2);
                F_instructor_feedback_improvement = fData.get(3);
                F_evidence = fData.get(4);
            }
            if(F_written_rubic == null) F_written_rubic="";
            if(F_instructor_feedback_comment == null) F_instructor_feedback_comment="";
            if(F_instructor_feedback_obstacle == null) F_instructor_feedback_obstacle="";
            if(F_instructor_feedback_improvement == null) F_instructor_feedback_improvement="";
            if(F_evidence == null) F_evidence="";

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

%>


<%
    Integer fid = (Integer) request.getSession().getAttribute("userLvl");

    String FK_Link_ID="";
    if(request.getParameter("Formative_ID")!=null) {
    F_Select select = new F_Select();
    try {
        FK_Link_ID = select.selectLinkIDOfFormF(Integer.parseInt(request.getParameter("Formative_ID")));
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    ArrayList<String> linkValues = new ArrayList<String>();
    if(!FK_Link_ID.equals("")) {
        F_Select aselect = new F_Select();
        try {
            linkValues = aselect.selectPILinksValuse(Integer.parseInt(FK_Link_ID));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>

<div class="main">
    <div class="section">
        <div class="container">
            <!--         what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Formative Data Collection Sheet</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">
                    <p>Outcome and performance Indicator Indicator:</p>

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table table-striped table-bordered text-center">
                            <tr>
                                <th class="text-center">Student Outcome:</th>
                                <th class="text-center"><% if(linkValues.size()!=0) {
                                    F_Select bselect = new F_Select();
                                    try {
                                        ArrayList<String> rs = bselect.selectOutForLinkSingle(Integer.parseInt(linkValues.get(0)));
                                        out.print(rs.get(0));
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                                %>
                                </th>
                            </tr>
                            <tr>
                                <td>performance indicators</td>
                                <td><% if(linkValues.size()!=0) {
                                    F_Select cselect = new F_Select();
                                    try {
                                        ArrayList<String> rs = cselect.selectPIForLinkSingle(Integer.parseInt(linkValues.get(1)));
                                        out.print(rs.get(0));
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                                %></td>
                            </tr>
                        </table>
                    </div>

                    <p>Course Information:</p>

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table table-striped table-bordered text-center">
                            <tr>
                                <th class="text-center">Course Code:</th>
                                <th class="text-center"><%
                                        out.print(linkValues.get(2));
                                %></th>
                            </tr>
                            <tr>
                                <td>Course title:</td>
                                <td><%
                                    if(linkValues.size()!=0) {
                                        F_Select dselect = new F_Select();
                                        try {
                                            String name = dselect.selectCourseName(linkValues.get(2));

                                            out.print(name);

                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                %></td>
                            </tr>

                            <tr>
                                <td>Level & Program:</td>
                                <td><%
                                    if(linkValues.size()!=0) {
                                        F_Select eselect = new F_Select();
                                        try {
                                            String level = eselect.selectCourseLevel(linkValues.get(2));

                                            out.print(level);

                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                %>,<%
                                    if(linkValues.size()!=0) {
                                        F_Select fselect = new F_Select();
                                        try {
                                            String name = fselect.selectProgramName(Integer.parseInt(linkValues.get(5)));

                                            out.print(name);

                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                %></td>
                            </tr>

                            <tr>
                                <td>Term and Year:</td>
                                <td><%
                                    if(linkValues.size()!=0) {
                                        F_Select gselect = new F_Select();
                                        try {
                                            String name = gselect.selectTermName(Integer.parseInt(linkValues.get(6)));

                                            out.print(name);

                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                %>, <%
                                    if(linkValues.size()!=0) {
                                        F_Select hselect = new F_Select();
                                        try {
                                            String year = hselect.selectTermYear(Integer.parseInt(linkValues.get(6)));

                                            out.print(year);

                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                %></td>
                            </tr>
                        </table>
                    </div>

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table table-striped table-bordered text-center">
                            <tr>
                                <th class="text-center">Rubric(s):</th>
                                <th class="text-center"><%
                                    String N1 = "";
                                    String D1 = "";
                                    String N2 = "";
                                    String D2 = "";
                                    String N3 = "";
                                    String D3 = "";
                                    String N4 = "";
                                    String D4 = "";
                                    if(linkValues.size()!=0) {
                                    F_Select zselect = new F_Select();
                                    ArrayList<String> rubrics = new ArrayList<String>();
                                    try {
                                        rubrics = zselect.selectRubrics(Integer.parseInt(linkValues.get(4)));
                                        out.print(rubrics.get(0));
                                        N1 = rubrics.get(0);
                                        D1 = rubrics.get(1);
                                        N2 = rubrics.get(2);
                                        D2 = rubrics.get(3);
                                        N3 = rubrics.get(4);
                                        D3 = rubrics.get(5);
                                        N4 = rubrics.get(6);
                                        D4 = rubrics.get(7);
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                                %></th>
                                <th class="text-center"><%out.print(N2);%></th>
                                <th class="text-center"><%out.print(N3);%></th>
                                <th class="text-center"><%out.print(N4);%></th>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td><%out.print(D1);%></td>
                                <td><%out.print(D2);%></td>
                                <td><%out.print(D3);%></td>
                                <td><%out.print(D4);%></td>
                            </tr>
                        </table>
                    </div>

                    <form name="formativeForm" method="post" enctype="multipart/form-data">
                        <%--<input type="hidden" name="WrittenRubricsV" value="<%=F_written_rubic%>">
                        <input type="hidden" name="CommentsV" value="<%=F_instructor_feedback_comment%>">
                        <input type="hidden" name="ObstaclesV" value="<%=F_instructor_feedback_obstacle%>">
                        <input type="hidden" name="ImprovementV" value="<%=F_instructor_feedback_improvement%>">
                        <input type="hidden" name="evidenceV" value="<%=F_evidence%>">--%>
                        <input type="hidden" name="Formative_ID" value="<%=request.getParameter("Formative_ID")%>">

                        <div>
                            <p>Written Rubrics</p>
                            <textarea name="WrittenRubrics" class="form-control" rows="4"><%=F_written_rubic%></textarea>
                        </div>
                        <br>
                        <br>
                        <p>Instructor Feedback:</p>

                        <div class="panel panel-default">

                            <table>
                                <tr>
                                    <th>Comment(s) on Success/Failure in Achieving Performance Indicator*: </th>
                                    <td><textarea name="Comments" class="form-control" rows="4"><%=F_instructor_feedback_comment%></textarea></td>
                                </tr>
                                <tr>
                                    <th>Obstacles in Achieving Desired Progress*: </th>
                                    <td><textarea name="Obstacles" class="form-control" rows="4"><%=F_instructor_feedback_obstacle%></textarea></td>
                                </tr>
                                <tr>
                                    <th>Areas of Improvement*: </th>
                                    <td><textarea name="Improvement" class="form-control" rows="4"><%=F_instructor_feedback_improvement%></textarea></td>
                                </tr>
                            </table>
                            <label>Evidence: </label><input type="text" name="evidence" value="<%=F_evidence%>">
                            <br>
                            <br>
                        </div>
                        <div class="row tim-row">
                                <div class="col-md-8 col-sm-8">
                                    <div class="form-group">
                                        <label>University Logo</label>

                                        <div class="input-group">
                                            <span class="input-group-btn">
                                                <span class="btn btn-fill btn-primary btn-file">
                                                    Browse&hellip; <input type="file" id="evidence" name="evidence" accept="image/png">
                                                </span>
                                            </span>
                                            <input type="text" class="form-control" value="" readonly>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-4">
                                    <img style="max-width: 270px" src="<%
                                    if(!F_evidence.equals("")){out.print(F_evidence);
                                    }else { out.print("/img/logoHolder.png");}
                                    %>">

                                </div>
                        </div>
                        <div class="row tim-row">
                            <label>Faculty Name: </label>
                            <label><%
                                F_Select yselect = new F_Select();
                                try {
                                    String name = yselect.selectFacultyForForm(fid);

                                            out.print(name);

                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            %></label>
                            <label class="pull-right" id="date"><script>
                                /*function myFunction() {
                                 var d = new Date();
                                 var n = d.getFullYear();
                                 document.getElementById("date").innerHTML = n;
                                 }*/
                                (function(){
                                    var d = new Date();
                                    document.getElementById("date").innerHTML = "&nbsp;"+d.toDateString();
                                })();
                            </script></label>

                            <label class="pull-right">Date: </label>
                    </div>
                    <div class="row tim-row">
                        <button class="btn btn-primary" formaction="/SaveFormative" type="submit">Save</button>

                        <button class="btn btn-primary pull-right" formaction="/SubmitFormative" type="submit">Submit</button>
                    </div>
                    </form>

                    <button class="btn btn-success btn-fill">Upload evidence</button>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

        </div>
    </div>
    <!-- End of main -->
</div>