<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 2/1/2016
  Time: 4:10 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<div class="main">
    <div class="section">
        <div class="container">
            <!--         what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Add Program Performance Indicator</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">

                    <form name="myform" action="/AddPI" method="post">
                        <label>Choose a program: </label>
                        <select class="form-control" name="programName" required>
                            <%
                                AS_Select select = new AS_Select();
                                try {
                                    ArrayList<String> rs = select.selectAllPrograms();

                                    for (int i=1; i<rs.size();i++) {
                                        out.print("<option value="+rs.get(i)+">"+rs.get(i)+"</option>");
                                    }
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            %>
                        </select>

                        <p>Click "Add" to enter program performance indicator</p>

                        <div class="panel panel-default">
                            <!-- Default panel contents -->

                            <!-- Table -->
                            <table class="table">
                                <tr>
                                    <th>Performance Indicator</th>
                                    <th>Label</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                                <%

                                    AS_Select aselect = new AS_Select();

                                    try {
                                        ArrayList<ArrayList<String>> rs = aselect.selectObjective(id);
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
                                                    "                            <input name=\"page\" value=\"updateObj\" hidden />\n" +
                                                    "                            <input name=\"Objid\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                    "                            <input name=\"ObjValue\" value=\""+rsRow.get(1)+"\" hidden />\n" +
                                                    "                            <input name=\"name\" value=\""+request.getParameter("name")+"\" hidden />\n" +
                                                    "                            <input name=\"id\" value=\""+request.getParameter("id")+"\" hidden />\n" +
                                                    "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-warning btn-simple\"><i class=\"fa fa-pencil fa-2x \"></i></button>\n" +
                                                    "                               </td>" +
                                                    "                            </form>" +
                                                    "                            <form method=\"post\" action=\"/Delete Objective\">\n" +
                                                    "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                    "                            <input name=\"Objid\" value=\""+rsRow.get(0)+"\" hidden />\n" +
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
                        <a class="btn btn-success btn-fill" href="index.jsp?page=addObj&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Add</a>
                        <a class="btn btn-success btn-fill" href="index.jsp?page=OutcomeList&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Next</a>
                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>