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
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>

<%

    if(request.getSession().getAttribute("Msg")!= null){
        out.print("<script>");
        out.print("\n" +
                "bootbox.alert(\""+request.getSession().getAttribute("Msg")+"\");");

        out.print("</script>");

        request.getSession().removeAttribute("Msg");
    }
%>


<%
    String F_written_rubic = "";
    String F_instructor_feedback_comment = "";
    String F_instructor_feedback_obstacle = "";
    String F_instructor_feedback_improvement = "";
    String F_evidence = "";

    String faculatyId = null;
    String fDate = null;

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
                faculatyId = fData.get(6);
                fDate = fData.get(7);
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
    Integer fid = Integer.parseInt((String) request.getSession().getAttribute("userId"));

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

        <div class="container">
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Formative Data Collection Sheet</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p>Outcome and performance Indicator Indicator:</p>


                        <!-- Table -->
                        <table class="table table-striped table-bordered text-center">
                            <tr>
                                <th class="text-center">Student Outcome</th>
                                <td class="text-center"><% if(linkValues.size()!=0) {
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
                                </td>
                            </tr>
                            <tr>
                                <th>performance indicators</th>
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



                    <h6>Course Information:</h6>

                        <!-- Table -->
                        <table class="table table-striped table-bordered text-center">
                            <tr>
                                <th class="text-center">Course Code</th>
                                <td class="text-center"><%
                                        out.print(linkValues.get(2));
                                %></td>
                            </tr>
                            <tr>
                                <th>Course title</th>
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
                                <th>Level & Program</th>
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
                                <th>Term and Year</th>
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



                        <!-- Table -->
                        <table class="table table-striped table-bordered text-center">
                            <tr>
                                <th class="text-center">Rubric(s)</th>
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
                                        ArrayList<String> rubricsNames = new ArrayList<String>();
                                        try {
                                            rubrics = zselect.selectRubrics(Integer.parseInt(linkValues.get(4)));
                                            rubricsNames = zselect.selectRubricsFormativeNames(Integer.parseInt(request.getParameter("Formative_ID")));
                                            out.print(rubricsNames.get(0));
                                            N1 = rubricsNames.get(0);
                                            D1 = rubrics.get(0);
                                            N2 = rubricsNames.get(1);
                                            D2 = rubrics.get(1);
                                            N3 = rubricsNames.get(2);
                                            D3 = rubrics.get(2);
                                            N4 = rubricsNames.get(3);
                                            D4 = rubrics.get(3);
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
                                <th>Description</th>
                                <td><%out.print(D1);%></td>
                                <td><%out.print(D2);%></td>
                                <td><%out.print(D3);%></td>
                                <td><%out.print(D4);%></td>
                            </tr>
                        </table>


                        <div>

                            <h6>Written Rubrics</h6>
                            <div>
                                <textarea class="form-control" rows="4" readonly style="background-color: white;"> <%=F_written_rubic%></textarea>
                               </div>

                        </div>
                        <br>
                        <br>
                        <h6>Instructor Feedback:</h6>

                            <table class="table table-striped table-bordered text-center">
                                <tr>
                                    <th width="30%">Comment(s) on Success/Failure in Achieving Performance Indicator*: </th>
                                    <td><%=F_instructor_feedback_comment%></td>
                                </tr>
                                <tr>
                                    <th>Obstacles in Achieving Desired Progress*: </th>
                                    <td><%=F_instructor_feedback_obstacle%></td>
                                </tr>
                                <tr>
                                    <th>Areas of Improvement*: </th>
                                    <td><%=F_instructor_feedback_improvement%></td>
                                </tr>
                            </table>


                        <div class="row">
                            <div class="col-md-4 col-sm-4">
                                     <%
                                    if(!F_evidence.equals("")){

                                        out.print("<a  target=\"_blank\" href=\""+F_evidence+"\">Click here to view the evidence</a>");
                                    }else { out.print("<h4>no evidence were uploaded</h4>");}
                                    %>

                            </div>
                        </div>

                        <div class="row">
                            <label><strong>Faculty Name: </strong></label>
                            <label>
                                <strong>
                                <%
                                F_Select yselect = new F_Select();
                                try {
                                    String name = yselect.selectFacultyForForm(Integer.parseInt(faculatyId));

                                            out.print(name);

                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            %>
                                </strong></label>

                            <label class="pull-right"><strong>Date: <%=fDate%></strong></label>

                    <div class="row">
                        <a class="btn btn-primary" href="index.jsp" >Back</a>

                        <a class="btn btn-primary pull-right" id="print" onclick="window.print();">Print</a>

                    </div>
                    </div>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

        </div>
