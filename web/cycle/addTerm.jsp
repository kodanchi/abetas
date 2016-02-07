<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 2/2/2016
  Time: 7:51 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>


    <div class="section">
        <div class="container">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Cycle Configuration</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p class="text-center">You need to enter the terms of the cycle</p>

                    <div>
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <div class="row tim-row">
                            <div class="col-md-6">
                                <form name="myform" action="/Add Link Outcome and Objective" method="post">
                                    <div class="row tim-row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input type="text" name="termsname" class="form-control" required>

                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Year</label>
                                                <div class="dropdown">
                                                    <a href="#" name="year" class="btn dropdown-toggle" data-toggle="dropdown">
                                                        Year
                                                        <b class="caret"></b>
                                                    </a>
                                                    <ul class="dropdown-menu">
                                                        <li><a href="#">2014-2015</a></li>
                                                        <li><a href="#">2015-2016</a></li>
                                                    </ul>
                                                </div>

                                            </div>
                                        </div>

                                    </div>
                                    <button type="submit" class="btn btn-success">Add</button>

                                </form>
                            </div>

                            <div class="col-md-6 panel panel-default">
                                <table class="table">
                                    <tr>
                                        <th>Name</th>
                                        <th>Year</th>
                                        <th>edit</th>
                                        <th>delete</th>
                                    </tr>
                                    <%
                                        String id = "";
                                        if(request.getSession().getAttribute("id") != null){
                                           id  = (String) request.getSession().getAttribute("id");
                                            System.out.print("id id : "+id);
                                        }

                                        AS_Select aselect = new AS_Select();

                                        try {
                                            ArrayList<ArrayList<String>> rs = aselect.selectAddTerm();
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
                                                        "                            <input name=\"Termid\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                        "                            <input name=\"id\" value=\""+id+"\" hidden />\n" +
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
                                        } catch (NullPointerException e){
                                            e.fillInStackTrace();
                                        }
                                        finally {
                                            //request.getSession().removeAttribute("id");
                                        }

                                    %>

                                </table>
                            </div>
                        </div>

                    </div>
                    <button class="btn btn-success pull-right">Cancel</button>

                    <button class="btn btn-success pull-right">Next</button>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>

<!--   end modal  -->



