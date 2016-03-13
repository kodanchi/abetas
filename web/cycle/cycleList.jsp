<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 2/2/2016
  Time: 3:46 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<div id="header"></div>

        <div class="container">
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Cycle Management</h2>
                <div class="col-md-8 col-md-offset-2">

                        <!-- Table -->
                        <table class="table table-hover table-striped table-bordered text-center">
                            <tr>
                                <th>Cycle ID</th>
                                <th>Edit</th>
                                <th>Delete</th>

                            </tr>
                            <%


                                C_AS_Select aselect = new C_AS_Select();
                                try {
                                    ArrayList<ArrayList<String>> rs = aselect.selectCycle();
                                    ArrayList<String> rsRow ;

                                    for (int i=0; i<rs.size();i++){
                                        rsRow = new ArrayList<String>();
                                        rsRow = rs.get(i);
                                        out.print("<tr>");
                                        for (int j=0; j<rsRow.size();j++) {
                                            out.print("<td>"+rsRow.get(j)+"</td>");
                                        }
                                        out.print("<td>" +
                                                "                            <form method=\"post\" action=\"index.jsp\">\n" +
                                                "                            <input name=\"page\" value=\"piList\" hidden />\n" +
                                                "                            <input name=\"cycle\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                "                               </td>" +
                                                "                            </form>" +
                                                "                            <form method=\"post\" action=\"/DeleteCycle\">\n" +
                                                "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                "                            <input name=\"id\" value=\""+rsRow.get(0)+"\" hidden />\n" +
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

                    <form name="myform" action="/AddCycle" method="post">
                        <button class="btn btn-primary">Back</button>
                        <button class="btn btn-primary" type="submit">Add</button>

                    </form>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

        </div>


<!--   end modal  -->

<div id="footer"></div>
