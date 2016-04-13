<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="EDB.E_Select" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%@ page import="org.apache.commons.codec.Encoder" %>
<%@ page import="EDB.EncDec" %>
<%@ page import="org.omg.Messaging.SYNC_WITH_TRANSPORT" %><%--
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

    String Formative_Section_ID = null;
    String faculatyId = null;
    String fDate = null;

        E_Select dbs = new E_Select();
        ArrayList<String> fData = new ArrayList<String>();
        try {
            if(request.getParameter("id") != null) {
                Formative_Section_ID = dbs.selectFormmativeIdToEvaluate(Integer.parseInt(request.getParameter("id")));
                System.out.println("id : " + request.getParameter("id"));
            }

            
            fData = dbs.selectFormative(Integer.parseInt(Formative_Section_ID));
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
    //Integer fid = Integer.parseInt((String) request.getSession().getAttribute("userId"));

    String FK_Link_ID="";
    if(Formative_Section_ID !=null) {
    E_Select select = new E_Select();
    try {
        FK_Link_ID = select.selectLinkIDOfFormF(Integer.parseInt(Formative_Section_ID));
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    ArrayList<String> linkValues = new ArrayList<String>();
    if(!FK_Link_ID.equals("")) {
        E_Select aselect = new E_Select();
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
                                    E_Select bselect = new E_Select();
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
                                    E_Select cselect = new E_Select();
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
                                        E_Select dselect = new E_Select();
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
                                        E_Select eselect = new E_Select();
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
                                        E_Select fselect = new E_Select();
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
                                        E_Select gselect = new E_Select();
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
                                        E_Select hselect = new E_Select();
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
                                    E_Select zselect = new E_Select();
                                        ArrayList<String> rubrics = new ArrayList<String>();
                                        ArrayList<String> rubricsNames = new ArrayList<String>();
                                        try {
                                            rubrics = zselect.selectRubrics(Integer.parseInt(linkValues.get(4)));
                                            rubricsNames = zselect.selectRubricsFormativeNames(Integer.parseInt(Formative_Section_ID));
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
                                <td>Description</td>
                                <td><%out.print(D1);%></td>
                                <td><%out.print(D2);%></td>
                                <td><%out.print(D3);%></td>
                                <td><%out.print(D4);%></td>
                            </tr>
                        </table>
                    </div>

                    <div>


                        <div>
                            <p>Written Rubrics</p>
                            <div><%=F_written_rubic%></div>
                        </div>
                        <br>
                        <br>
                        <p>Instructor Feedback:</p>

                        <div class="panel panel-default">

                            <table>
                                <tr>
                                    <th>Comment(s) on Success/Failure in Achieving Performance Indicator*: </th>
                                    <td><div><%=F_instructor_feedback_comment%></div></td>
                                </tr>
                                <tr>
                                    <th>Obstacles in Achieving Desired Progress*: </th>
                                    <td><div><%=F_instructor_feedback_obstacle%></div></td>
                                </tr>
                                <tr>
                                    <th>Areas of Improvement*: </th>
                                    <td><div><%=F_instructor_feedback_improvement%></div></td>
                                </tr>
                            </table>

                        </div>
                        <div class="row tim-row">

                                <div class="col-md-4 col-sm-4">
                                     <%
                                    if(!F_evidence.equals("")){

                                        out.print("<a  target=\"_blank\" href=\""+F_evidence+"\">Click here to view the evidence</a>");
                                    }else { out.print("<h4>no evidence were uploaded</h4>");}
                                    %>

                                </div>
                        </div>
                        <div class="row tim-row">
                            <label>Faculty Name: </label>
                            <label><%
                                E_Select yselect = new E_Select();
                                try {
                                    String name = yselect.selectFacultyForForm(Integer.parseInt(faculatyId));

                                            out.print(name);

                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            %></label>

                            <label class="pull-right">Date: <%=fDate%></label>
                    </div>
                    <div class="row tim-row">
                        <a class="btn btn-primary" href="index.jsp" >Back</a>

                        <a class="btn btn-primary pull-right" id="print" onclick="window.print();">Print</a>

                    </div>
                    </div>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

        </div>
    </div>
    <!-- End of main -->
</div>