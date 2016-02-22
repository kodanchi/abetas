<%@ page import="java.util.ArrayList" %>
<%@ page import="ASDB.P_AS_Select" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 2/2/2016
  Time: 2:59 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>


<div id="header"></div>

<div class="main">
    <div class="section">
        <div class="container">
            <!--         what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Link Student Outcome with Objectives</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">
                    <p>Click "Add" to add a link for <%=request.getParameter("name")%> program</p>

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table">
                            <tr>
                                <th>Objectives</th>
                                <th>Outcome</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            <%
                                P_AS_Select aselect = new P_AS_Select();
                                try {
                                    ArrayList<ArrayList<String>> rs = aselect.selectStudentOutcomeWithObjectives(Integer.parseInt(request.getParameter("id")));
                                    ArrayList<String> rsRow ;

                                    for (int i=0; i<rs.size();i++){
                                        rsRow = new ArrayList<String>();
                                        rsRow = rs.get(i);
                                        out.print("<tr>");
                                        for (int j=1; j<rsRow.size();j++) {
                                            out.print("<td>"+rsRow.get(j)+"</td>");

                                        }
                                        out.print("<td>" +
                                                "                            <form method=\"post\" action=\"index.jsp\">\n" +
                                                "                            <input name=\"page\" value=\"updateLink\" hidden />\n" +
                                                "                            <input name=\"Linkid\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"ObjLinkValue\" value=\""+rsRow.get(1)+"\" hidden />\n" +
                                                "                            <input name=\"OutLinkValue\" value=\""+rsRow.get(2)+"\" hidden />\n" +
                                                "                            <input name=\"name\" value=\""+request.getParameter("name")+"\" hidden />\n" +
                                                "                            <input name=\"id\" value=\""+request.getParameter("id")+"\" hidden />\n" +
                                                "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-warning btn-simple\"><i class=\"fa fa-pencil fa-2x \"></i></button>\n" +
                                                "                               </td>" +
                                                "                            </form>" +
                                                "                            <form method=\"post\" action=\"/Delete Link Objective and Outcome\">\n" +
                                                "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                "                            <input name=\"Linkid\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"name\" value=\""+request.getParameter("name")+"\" hidden />\n" +
                                                "                            <input name=\"id\" value=\""+request.getParameter("id")+"\" hidden />\n" +
                                                "                               <td>" +
                                                "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-danger btn-simple\"><i class=\"fa fa-trash-o fa-2x \"></i></button>\n" +
                                                "                               </td>"+
                                                "                        </form>" +
                                                "</tr>");
                                    }

                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            %>

                        </table>
                    </div>

                    <a class="btn btn-success btn-fill" href="index.jsp?page=addLinkO&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Add</a>
                    <a class="btn btn-success btn-fill" href="index.jsp?page=CoursesList&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Next</a>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>

<!--   end modal  -->

<div id="footer"></div>
