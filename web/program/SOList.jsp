<%@ page import="ASDB.P_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 2/1/2016
  Time: 6:52 م
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
<div id="header"></div>

        <div class="container">
            <!--  Here is row -->
            <div class="row">
                <h2 class="text-center">Add Student Outcomes</h2>
                <div class="col-md-10 col-md-offset-1">
                    <p>Click "Add" to enter student outcomes for <%=request.getParameter("name")%> program</p>

                        <!-- Table -->
                        <table class="table table-hover table-striped table-bordered text-center">
                            <tr>
                                <th>Outcome</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            <%
                                P_AS_Select aselect = new P_AS_Select();
                                try {
                                    ArrayList<ArrayList<String>> rs = aselect.selectStudentOutcomes(Integer.parseInt(request.getParameter("id")));
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
                                                "                            <input name=\"page\" value=\"updateOut\" hidden />\n" +
                                                "                            <input name=\"Outid\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"OutValue\" value=\""+rsRow.get(1)+"\" hidden />\n" +
                                                "                            <input name=\"name\" value=\""+request.getParameter("name")+"\" hidden />\n" +
                                                "                            <input name=\"id\" value=\""+request.getParameter("id")+"\" hidden />\n" +
                                                "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                "                               </td>" +
                                                "                            </form>" +
                                                "                            <form class=\"delForm\" method=\"post\" action=\"/Delete Outcome\">\n" +
                                                "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                "                            <input name=\"Outid\" value=\""+rsRow.get(0)+"\" hidden />\n" +
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
                        </table>
                    <a class="btn btn-default pull-right " href="index.jsp?page=programList">Cancel</a>
                    <a class="btn btn-primary" onclick="importPopup('index.jsp?name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>&page=import&data=outcomes',
                    'index.jsp?page=addOut&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>',
                            'Add new student outcome',
                            'If you have new student outcomes details in an Excel sheet, you can import the file to add them all at once');">Add</a>
                    <a class="btn btn-primary pull-right" href="index.jsp?page=LinkOutObj&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Next</a>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- End of container -->
        </div>

<div id="footer"></div>
