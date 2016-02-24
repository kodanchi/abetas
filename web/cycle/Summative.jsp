<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/23/2016
  Time: 5:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<% String FK_Link_ID="";
    if(request.getParameter("Summative_ID")!=null) {
        AS_Select select = new AS_Select();
        try {
            FK_Link_ID = select.selectLinkIDOfFormF(Integer.parseInt(request.getParameter("Summative_ID")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ArrayList<String> linkValues = new ArrayList<String>();
    if(!FK_Link_ID.equals("")) {
        AS_Select aselect = new AS_Select();
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
                                    AS_Select bselect = new AS_Select();
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
                                    AS_Select cselect = new AS_Select();
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
                                        AS_Select dselect = new AS_Select();
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


                    <form name="formativeForm" action="/Summative" method="post">

                        <input type="hidden" name="Formative_ID" value="<%=request.getParameter("Summative_ID")%>">
                        <input type="hidden" name="Section_ID" value="<%=Section%>">

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
                                            AS_Select zselect = new AS_Select();
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
                                    <td class="text-center"></td>
                                    <td class="text-center"></td>
                                    <th class="text-center"><%out.print(D1);%></th>
                                    <th class="text-center"><%out.print(D2);%></th>
                                    <th class="text-center"><%out.print(D3);%></th>
                                    <th class="text-center"><%out.print(D4);%></th>
                                </tr>
                                <tr>

                                    <%

                                        AS_Select Sselect = new AS_Select();

                                        try {
                                            ArrayList<ArrayList<String>> rs = Sselect.selectStudents(Integer.parseInt(request.getParameter("section")));
                                            ArrayList<String> rsRow ;

                                            out.print("<input type=\"hidden\" name=\"studentsNumber\" value=\""+rs.size()+"\">");

                                            for (int i=0; i<rs.size();i++){
                                                rsRow = new ArrayList<String>();
                                                rsRow = rs.get(i);
                                                out.print("<tr>");
                                                for (int j=1; j<rsRow.size();j++) {
                                                    out.print("<td>");
                                                    out.print(rsRow.get(j));
                                                    out.print("</td>");
                                                }
                                                out.print("<input type=\"hidden\" name=\"SID"+i+"\" value=\""+rs.get(0)+"\">");
                                                out.print("<td>");
                                                out.print("<label class=\"radio radio-blue\">\n" +
                                                        "                                            <input type=\"radio\" name=\"optionsRadios"+i+"\" data-toggle=\"radio\" id=\"optionsRadios1\" value=\"option1\">\n" +
                                                        "                                        </label>");
                                                out.print("</td>");
                                                out.print("<td>");
                                                out.print("<label class=\"radio radio-blue\">\n" +
                                                        "                                            <input type=\"radio\" name=\"optionsRadios"+i+"\" data-toggle=\"radio\" id=\"optionsRadios1\" value=\"option1\">\n" +
                                                        "                                        </label>");
                                                out.print("</td>");
                                                out.print("<td>");
                                                out.print("<label class=\"radio radio-blue\">\n" +
                                                        "                                            <input type=\"radio\" name=\"optionsRadios"+i+"\" data-toggle=\"radio\" id=\"optionsRadios1\" value=\"option1\">\n" +
                                                        "                                        </label>");
                                                out.print("</td>");
                                                out.print("<td>");
                                                out.print("<label class=\"radio radio-blue\">\n" +
                                                        "                                            <input type=\"radio\" name=\"optionsRadios"+i+"\" data-toggle=\"radio\" id=\"optionsRadios1\" value=\"option1\">\n" +
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

                            <label>Evidence: </label><input type="text" name="evidence" value="<%if (request.getParameter("WrittenRubricsV")!=null) {out.print(request.getParameter("evidenceV"));}%>">

                            <div class="row tim-row">
                                <label>Faculty Name: </label>
                                <label><%
                                    AS_Select yselect = new AS_Select();
                                    try {
                                        String name = yselect.selectFacultyForForm(Integer.parseInt(request.getParameter("FacilityID")));

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

                        </div>

                        <button class="btn btn-info">Upload evidence</button>
                        <button class="btn btn-danger btn-fill">Save</button>
                        <button class="btn btn-primary pull-right">Submit</button>

                        </form>

                        <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>
        </div>
    </div>
    <!-- End of main -->
</div>