<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ASDB.AS_Select" %><%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 2/2/2016
  Time: 8:38 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header"></div>

        <div class="container">
            <!--   Here is row -->
            <div class="row">
                <h2 class="text-center">Term Management</h2>
                <h4 class="text-center">Term Name</h4>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">
                    <ul class="list-inline">
                        <li><p class="lead">Year: </p></li>
                        <li><p class="lead">2015-2016</p></li>
                        <li><p><a class="btn btn-warning btn-simple" href="#"><i class="fa fa-pencil fa-2x"></i></a></p></li>
                    </ul>

                    <ul class="list-inline" style="margin-bottom:-10px;">
                        <li><p>Courses: </p></li>
                        <li><p><a class="btn btn-warning btn-simple" href="#"><i class="fa fa-pencil fa-2x"></i></a></p></li>
                    </ul>



                    <!--    <div class="panel panel-default">
                            <!-- Default panel contents -->

                        <!-- Table -->
                    <table class="table table-striped table-bordered text-center">
                            <tr>
                                <th>Course Name</th>
                                <th>Code</th>
                                <th>Level</th>
                                <th>Group</th>
                                <th>Include</th>
                            </tr>
                            <%
                                int id = Integer.parseInt(request.getParameter("id"));
                                AS_Select aselect = new AS_Select();
                                try {
                                    ArrayList<ArrayList<String>> rs = aselect.selectTerm(id);
                                    ArrayList<String> rsRow ;

                                    for (int i=0; i<rs.size();i++){
                                        rsRow = new ArrayList<String>();
                                        rsRow = rs.get(i);
                                        out.print("<tr>");
                                        for (int j=0; j<rsRow.size();j++) {
                                            out.print("<td>"+rsRow.get(j)+"</td>");
                                        }
                                        out.print("<td><a class=\"btn btn-danger btn-simple\" href=\"#\"><i class=\"fa  fa-plus fa-2x\"></i></a></td>\n");
                                        out.print("<td>\n" +
                                                "                                    <label class=\"checkbox checkbox-azure center-block\" for=\"checkbox2\" style=\"margin-left:10px;\">\n" +
                                                "                                        <input type=\"checkbox\" value=\"\" id=\"checkbox2\" data-toggle=\"checkbox\" checked>\n" +
                                                "                                    </label>\n" +
                                                "                                </td>");
                                        out.print("</tr>");
                                    }

                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }




                            %>


                        </table>
                    <!-- </div> -->
                    <button class="btn btn-danger">Delete</button>
                    <button class="btn btn-primary">Back</button>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

            <!-- End of container -->

        </div>


<div id="footer"></div>
