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




<%
    String id = "";
    if(request.getSession().getAttribute("id") != null){
        id  = (String) request.getSession().getAttribute("id");
        out.print("id is : "+id);
    }

%>
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
                                            <form method="post" action="/AddTerm">
                                                <div class="row tim-row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label>Name</label>
                                                            <input type="text" name="termName" class="form-control" required>
                                                            <input name="cycleId" value="<%=id%>" hidden/>

                                                        </div>
                                                    </div>

                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label>Year</label>
                                                            <select class="form-control" name="termYear">
                                                                <option value="2015-2016">2015-2016</option>
                                                                <option value="2016-2017">2016-2017</option>
                                                            </select>

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

                                                    AS_Select dba = new AS_Select();

                                                    try {
                                                        ArrayList<ArrayList<String>> rs = dba.selectAddTerm(id);
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