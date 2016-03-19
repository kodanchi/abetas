<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/29/2016
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/10159067/show-hide-button-in-table-row-during-mouseover
--%>
<%@ page import="ASDB.P_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>


<div class="container">
    <!-- Here is row -->
    <div class="row">
            <h2 class="text-center">Program Management</h2>
            <div class="col-lg-10 col-md-offset-1">
                <div class="input-group"> <span class="input-group-addon">Filter</span>

                    <input id="filter" type="text" class="form-control" placeholder=" by program name or mission">
                </div>
                    <!-- Table -->
                    <table class="table table-hover table-striped table-bordered text-center" id="table-sever-list" >
                        <tr class="textContainer">
                            <th>Name</th>
                            <th>Mission</th>
                            <th>Edit</th>
                            <th>Delete</th>

                        </tr>
                        <tbody class="searchable">
                            <%

                                P_AS_Select aselect = new P_AS_Select();
                                try {
                                    ArrayList<ArrayList<String>> rs = aselect.selectProgram();
                                    ArrayList<String> rsRow ;

                                    for (int i=0; i<rs.size();i++){
                                        rsRow = new ArrayList<String>();
                                        rsRow = rs.get(i);
                                        out.print("<tr class=\"textContainer\" >");
                                        for (int j=1; j<rsRow.size();j++) {
                                            out.print("<td>"+rsRow.get(j)+"</td>");
                                        }
                                        out.print("<td>" +
                                                "                            <form method=\"post\" action=\"index.jsp\">\n" +
                                                "                            <input name=\"page\" value=\"updateProgram\" hidden />\n" +
                                                "                            <input name=\"id\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"ProgramName\" value=\""+rsRow.get(1)+"\" hidden />\n" +
                                                "                            <input name=\"ProgramMission\" value=\""+rsRow.get(2)+"\" hidden />\n" +
                                                "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                "                               </td>" +
                                                "                            </form>" +
                                                "                            <form class=\"delForm\" method=\"post\" action=\"/DeleteProgram\">\n" +
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
                        </tbody>

                    </table >
                <a  class="btn btn-primary pull-right" href="/index.jsp">Back</a>
                <a class="btn btn-primary" href="index.jsp?page=add">Add</a>

                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>

        <!-- End of container -->
    </div>
