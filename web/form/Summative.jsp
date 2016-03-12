<%@ page import="FDB.F_Select" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/23/2016
  Time: 5:56 PM
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

    Integer fid = Integer.parseInt((String) request.getSession().getAttribute("userId"));

    ArrayList<String> linkSectionData = null;
    int summativeID = 0;
    int FK_Link_ID = 0;
    int section = 0;
    String evidance = null;
    String isSubmitted = null;
    String faculatyId = null;

    if(request.getParameter("Summative_ID")!=null) {
        summativeID = Integer.parseInt(request.getParameter("Summative_ID"));
        F_Select select = new F_Select();
        try {
            linkSectionData = select.selectLinkIDAndSectionIDOfFormS(summativeID);
            if(linkSectionData != null){
                FK_Link_ID = Integer.parseInt(linkSectionData.get(0));
                section = Integer.parseInt(linkSectionData.get(1));
                evidance = linkSectionData.get(2);
                isSubmitted = linkSectionData.get(3);
                faculatyId = linkSectionData.get(4);
            }
            if(isSubmitted.equals("1")){
                request.getSession().setAttribute("Msg","Invalid form");
                response.sendRedirect("index.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ArrayList<String> linkValues = new ArrayList<String>();
    if(FK_Link_ID != 0) {
        F_Select aselect = new F_Select();
        try {
            linkValues = aselect.selectPILinksValuse(FK_Link_ID);
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
                <h2 class="text-center">Summative Data Collection Sheet</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table table-striped table-bordered text-center">
                            <tr>
                                <th class="text-center">Student Outcome:</th>
                                <td><% if(linkValues.size()!=0) {
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
                                %></td>
                            </tr>

                            <tr>
                                <th class="text-center">Performance Indicator:</th>
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

                            <tr>
                                <th class="text-center">Course for Summative data:</th>
                                <td><%
                                    out.print(linkValues.get(2)+" ");
                                %> <%
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
                        </table>
                    </div>


                    <form id="summativeForm" name="summativeForm" enctype="multipart/form-data" method="post">

                        <input type="hidden" name="Summative_ID" value="<%=summativeID%>">
                        <input type="hidden" name="Section_ID" value="<%=section%>">

                        <div class="panel panel-default">
                            <!-- Default panel contents -->

                            <!-- Table -->
                            <table class="table table-striped table-bordered text-center">

                                <tr>
                                    <th class="text-center">Student ID:</th>
                                    <th class="text-center">Name</th>
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
                                                rubricsNames = zselect.selectRubricsSummativeNames(summativeID);
                                                System.out.println("rubrics : "+ rubrics);
                                                System.out.println("rubricsNames : "+ rubricsNames);
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
                                    <td class="text-center"></td>
                                    <td class="text-center"></td>
                                    <th class="text-center"><%out.print(D1);%></th>
                                    <th class="text-center"><%out.print(D2);%></th>
                                    <th class="text-center"><%out.print(D3);%></th>
                                    <th class="text-center"><%out.print(D4);%></th>
                                </tr>
                                <tr>

                                    <%

                                        F_Select Sselect = new F_Select();

                                        try {
                                            ArrayList<ArrayList<String>> rs = Sselect.selectStudents(section);
                                            ArrayList<String> rsRow ;

                                            out.print("<input type=\"hidden\" name=\"studentsNumber\" value=\""+rs.size()+"\">");

                                            String studentRubric = null;

                                            for (int i=0; i<rs.size();i++){
                                                rsRow = new ArrayList<String>();
                                                rsRow = rs.get(i);

                                                studentRubric = Sselect.selectStudentRubric(Integer.parseInt(rsRow.get(0)),summativeID);




                                                out.print("<tr>");
                                                for (int j=1; j<rsRow.size();j++) {
                                                    out.print("<td>");
                                                    out.print(rsRow.get(j));
                                                    out.print("</td>");
                                                }
                                                out.print("<input type=\"hidden\" name=\"SID"+i+"\" value=\""+rsRow.get(0)+"\">");
                                                out.print("<td>");
                                                out.print("<label class=\"radio radio-blue\">\n" +
                                                        "                                            <input type=\"radio\" ");
                                                          if(studentRubric!=null && studentRubric.equals(N1)){
                                                          out.print(" checked=\"checked\" ");
                                                          }
                                                          out.print(" name=\"optionsRadios"+i+"\" data-toggle=\"radio\" id=\"optionsRadios1"+i+"\" value=\""+N1+"\">\n" +
                                                        "                                        </label>");
                                                out.print("</td>");
                                                out.print("<td>");
                                                out.print("<label class=\"radio radio-blue\">\n" +
                                                        "                                            <input type=\"radio\" ");
                                                          if(studentRubric!=null && studentRubric.equals(N2)){
                                                          out.print(" checked=\"checked\" ");
                                                          }
                                                          out.print(" name=\"optionsRadios"+i+"\" data-toggle=\"radio\" id=\"optionsRadios2\"+i+\"\" value=\""+N2+"\">\n" +
                                                        "                                        </label>");
                                                out.print("</td>");
                                                out.print("<td>");
                                                out.print("<label class=\"radio radio-blue\">\n" +
                                                        "                                            <input type=\"radio\" ");
                                                          if(studentRubric!=null && studentRubric.equals(N3)){
                                                          out.print(" checked=\"checked\" ");
                                                          }
                                                          out.print(" name=\"optionsRadios"+i+"\" data-toggle=\"radio\" id=\"optionsRadios3\"+i+\"\" value=\""+N3+"\">\n" +
                                                        "                                        </label>");
                                                out.print("</td>");
                                                out.print("<td>");
                                                out.print("<label class=\"radio radio-blue\">\n" +
                                                        "                                            <input type=\"radio\" ");
                                                          if(studentRubric!=null && studentRubric.equals(N4)){
                                                          out.print(" checked=\"checked\" ");
                                                          }
                                                          out.print(" name=\"optionsRadios"+i+"\" data-toggle=\"radio\" id=\"optionsRadios4\"+i+\"\" value=\""+N4+"\">\n" +
                                                        "                                        </label>");
                                                out.print("</td>");
                                                out.print("</tr>");
                                            }

                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }

                                    %>

                            </table>


                            <div class="row tim-row">
                                <div class="col-md-8 col-sm-8">
                                    <div class="form-group">
                                        <label>Evidence:</label>

                                        <div class="input-group">
                                            <span class="input-group-btn">
                                                <span class="btn btn-fill btn-primary btn-file">
                                                    Browse&hellip; <input type="file" id="evidence" name="evidence" accept="application/pdf">
                                                </span>
                                            </span>
                                            <input type="text" class="form-control" value="" readonly>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-4">
                                    <%
                                        if(evidance != null){

                                            out.print("<a  target=\"_blank\" href=\""+evidance+"\">Click here to view the evidence</a>");
                                        }else { out.print("<h4>no evidence were uploaded</h4>");}
                                    %>

                                </div>
                            </div>

                            <div class="row tim-row">
                                <label>Faculty Name: </label>
                                <label><%
                                    F_Select yselect = new F_Select();
                                    try {
                                        String name = yselect.selectFacultyForForm(Integer.parseInt(faculatyId));

                                        out.print(name);

                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    DateFormat fdate = new SimpleDateFormat("EEE, d MMM yyyy");
                                    Date date = new Date();
                                %></label>
                                <input name="dateInput" id="dateInput" value="<%=fdate.format(date)%>" hidden/>
                                <label class="pull-right" id="date">Date: <%=fdate.format(date)%></label>

                                <label class="pull-right">Date: </label>
                            </div>

                        </div>

                        <button class="btn btn-danger btn-fill" type="submit" formaction="/SaveSummative">Save</button>
                        <button id="confirm" class="btn btn-primary pull-right" type="submit" >Submit</button>

                        <script>
                            $(function () {
                                $("button#confirm").click(function(e) {
                                    e.preventDefault();
                                    bootbox.confirm("when submitting this form you cannot access it again to edit the entered " +
                                            "data, are you sure you want to continue!?", function(confirmed) {
                                        console.log("Confirmed: "+confirmed);
                                        $('#summativeForm').attr('action', '/SubmitSummative');
                                        $('#summativeForm').submit();
                                    });
                                });
                            });
                        </script>
                        </form>

                        <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>
        </div>
    </div>
    <!-- End of main -->
</div>