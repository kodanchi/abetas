<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/29/2016
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/10159067/show-hide-button-in-table-row-during-mouseover
--%>
<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<div class="section">
    <div class="container">
        <!--         what is row -->
        <div class="row tim-row">
            <h2 class="text-center">Program Management</h2>
            <legend></legend>
            <div class="col-lg-10 col-md-offset-1">

                <div class="panel panel-default">
                    <!-- Default panel contents -->

                    <!-- Table -->
                    <table class="table table-hover" id="table-sever-list" >
                        <tr class="textContainer">
                            <th>Program name</th>
                            <th>Edit</th>
                            <th>Delete</th>

                        </tr>
                        <%

                            AS_Select aselect = new AS_Select();
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


                    </table >
                </div>
                <a class="btn btn-success btn-fill" href="index.jsp?page=add">Add</a>
                <button  class="btn btn-primary">Back</button>


                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>


        <!-- Modal Bodies come here -->
    </div>
</div>
