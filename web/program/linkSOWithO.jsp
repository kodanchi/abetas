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
<script src="/js/bootbox.min.js" type="text/javascript"></script>


<%

    if(request.getParameter("status") != null){

        out.print("<script type=\"text/javascript\">\n" +
                "    $(window).load(function(){\n" +
                "       bootbox.alert(\""+request.getParameter("status")+"\")\n" +
                "    });\n" +
                "</script>");
    }

%>

        <div class="container">
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Link Student Outcome with Objectives</h2>
                <div class="col-md-12">
                    <p>Click "Add" to add a link for <%=request.getParameter("name")%> program</p>

                    <div class="input-group"> <span class="input-group-addon">Filter</span>

                        <input id="filter" type="text" class="form-control" placeholder=" by student outcomes or program objectives, by name or by ID">
                    </div>

                        <!-- Table -->
                        <table class="table table-hover table-striped table-bordered text-center">
                            <tr>
                                <th>Objectives</th>
                                <th>Outcome</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            <tbody class="searchable">
                                <%
                                    P_AS_Select aselect = new P_AS_Select();
                                    try {
                                        ArrayList<ArrayList<String>> rs = aselect.selectStudentOutcomeWithObjectives(Integer.parseInt(request.getParameter("id")));
                                        ArrayList<String> rsRow ;

                                        for (int i=0; i<rs.size();i++){
                                            rsRow = new ArrayList<String>();
                                            rsRow = rs.get(i);
                                            out.print("<tr >");
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
                                                    "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                    "                               </td>" +
                                                    "                            </form>" +
                                                    "                            <form method=\"post\" class=\"delForm\" action=\"/Delete Link Objective and Outcome\">\n" +
                                                    "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                    "                            <input name=\"Linkid\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                    "                            <input name=\"name\" value=\""+request.getParameter("name")+"\" hidden />\n" +
                                                    "                            <input name=\"id\" value=\""+request.getParameter("id")+"\" hidden />\n" +
                                                    "                               <td>" +
                                                    "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-link btn-T \"><i class=\"fui-trash icon30\"></i></button>\n" +
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
                            </tbody>

                        </table>

                    <a class="btn btn-default pull-right " href="index.jsp?page=programList">Cancel</a>
                    <a class="btn btn-primary" href="index.jsp?page=addLinkO&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Add</a>
                    <a class="btn btn-primary pull-right" href="index.jsp?page=CoursesList&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Next</a>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- End of container -->
        </div>


