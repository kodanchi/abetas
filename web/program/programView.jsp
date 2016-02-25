<%@ page import="ASDB.P_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 1/31/2016
  Time: 7:09 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<div id="header"></div>

        <div class="container">
            <!--  Here is row -->
            <div class="row tim-row">
                <h2 class="text-center">Program Management</h2>
                <h4 class="text-center">Program Name</h4>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">

                    <ul class="list-inline" style="margin-bottom:-10px;">
                        <li><p>Mission Statement:</p></li>
                        <li><button  type="submit" title="Edit" class="btn btn-link btn-Y"><i class="fui-new icon30"></i></button></li>
                    </ul>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                    <ul class="list-inline" style="margin-bottom:-10px;">
                        <li><p>Program Objectives:</p></li>
                        <li><button  type="submit" title="Edit" class="btn btn-link btn-Y"><i class="fui-new icon30"></i></button></li>
                    </ul>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                    <ul class="list-inline" style="margin-bottom:-10px;">
                        <li><p>Student Outcomes:</p></li>
                        <li><button  type="submit" title="Edit" class="btn btn-link btn-Y"><i class="fui-new icon30"></i></button></li>
                    </ul>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>



                    <ul class="list-inline" style="margin-bottom:-10px;">
                        <li><p>Courses: </p></li>
                        <li><button  type="submit" title="Edit" class="btn btn-link btn-Y"><i class="fui-new icon30"></i></button></li>
                    </ul>





                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table table-striped table-bordered text-center">
                            <tr>
                                <th>Course Name</th>
                                <th>Code</th>
                                <th>Level</th>
                            </tr>
                            <%
                                int id = Integer.parseInt(request.getParameter("id"));
                                P_AS_Select aselect = new P_AS_Select();
                                try {
                                    ArrayList<ArrayList<String>> rs = aselect.selectProgramManagementFig30(id);
                                    ArrayList<String> rsRow ;

                                    for (int i=0; i<rs.size();i++){
                                        rsRow = new ArrayList<String>();
                                        rsRow = rs.get(i);
                                        out.print("<tr class=\"textContainer\" >");
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

                    <button class="btn btn-primary">Print</button>
                    <button class="btn btn-danger btn-fill">Delete</button>
                    <button class="btn btn-primary pull-right">Back</button>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

            <!-- End of container -->
</div>