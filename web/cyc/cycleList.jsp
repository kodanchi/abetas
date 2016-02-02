<%@ page import="ASDB.AS_Select" %>
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

<div class="main">
    <div class="section">
        <div class="container">
            <!--         what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Cycle Management</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table">
                            <tr>
                                <th>Cycle ID</th>
                                <th>Edit</th>
                                <th>Delete</th>

                            </tr>
                            <%


                                AS_Select aselect = new AS_Select();
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
                                                "                            <input name=\"page\" value=\"update\" hidden />\n" +
                                                "                            <input name=\"id\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-warning btn-simple\"><i class=\"fa fa-pencil fa-2x \"></i></button>\n" +
                                                "                               </td>" +
                                                "                            </form>" +
                                                "                            <form method=\"post\" action=\"index.jsp\">\n" +
                                                "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                "                            <input name=\"id\" value=\""+rsRow.get(0)+"\" hidden />\n" +
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
                    <button class="btn btn-success btn-fill">Add</button>
                    <button class="btn btn-primary">Back</button>


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
