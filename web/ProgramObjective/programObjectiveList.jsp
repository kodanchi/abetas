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
<div id="header"></div>

<div class="main">
    <div class="section">
        <div class="container">
            <!--         what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Add Program Objective</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">
                    <p>Click "Add" to enter program objectives</p>

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table">
                            <tr>
                                <th>Program</th>
                                <th>Objectives</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            <%

                                AS_Select aselect = new AS_Select();
                                int id = Integer.parseInt(request.getParameter("id"));

                                try {
                                    ArrayList<ArrayList<String>> rs = aselect.selectObjective(id);
                                    ArrayList<String> rsRow ;

                                    for (int i=0; i<rs.size();i++){
                                        rsRow = new ArrayList<String>();
                                        rsRow = rs.get(i);
                                        out.print("<tr>");
                                        for (int j=0; j<rsRow.size();j++) {
                                            out.print("<td>"+rsRow.get(j)+"</td>");

                                        }
                                        out.print("<td><a class=\"btn btn-warning btn-simple\" href=\"#\"><i class=\"fa fa-pencil fa-2x\"></i></a></td>\n");
                                        out.print("<td><a class=\"btn btn-danger btn-simple\" href=\"#\"><i class=\"fa fa-trash-o fa-2x\"></i></a></td>\n");
                                        out.print("</tr>");
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
                    <button class="btn btn-primary">Cancel</button>
                    <button class="btn btn-primary pull-right">Next</button>


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

