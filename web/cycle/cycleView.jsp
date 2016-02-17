<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 2/2/2016
  Time: 6:21 م
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
                <h4 class="text-center">Cycle ID</h4>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">

                    <ul class="list-inline">
                        <li><p>Defualt Threshlod: </p></li>
                        <li><p><p>75%</p></p></li>
                        <li><p><a class="btn btn-warning btn-simple" href="#"><i class="fa fa-pencil fa-2x"></i></a></p></li>
                    </ul>

                    <ul class="list-inline" style="margin-bottom:-10px;">
                        <li><p>Terms: </p></li>
                        <li><p><a class="btn btn-warning btn-simple" href="#"><i class="fa fa-pencil fa-2x"></i></a></p></li>
                    </ul>





                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table">
                            <tr>
                                <th>Name</th>
                                <th>Year</th>
                            </tr>
                            <%

                                int id = Integer.parseInt(request.getParameter("id"));
                                AS_Select aselect = new AS_Select();
                                try {
                                    ArrayList<ArrayList<String>> rs = aselect.selectCycleManagement(id);
                                    ArrayList<String> rsRow ;

                                    for (int i=0; i<rs.size();i++){
                                        rsRow = new ArrayList<String>();
                                        rsRow = rs.get(i);
                                        out.print("<tr>");
                                        for (int j=0; j<rsRow.size();j++) {
                                            out.print("<td>"+rsRow.get(j)+"</td>");
                                        }
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

                    <ul class="list-inline" style="margin-bottom:-10px;">
                        <li><p>Performance Indicator: </p></li>
                        <li><p><a class="btn btn-warning btn-simple" href="#"><i class="fa fa-pencil fa-2x"></i></a></p></li>
                    </ul>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum </p>


                    <button class="btn btn-primary">PI matching Table</button>
                    <button class="btn btn-primary">Print</button>
                    <button class="btn btn-danger btn-fill">Delete</button>
                    <button class="btn btn-primary pull-right">Back</button>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


        </div>
    </div>
    <!-- End of main -->
</div>



<div id="footer"></div>