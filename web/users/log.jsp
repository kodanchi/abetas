<%@ page import="ASDB.Auditor" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="ASDB.U_AS_Select" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 3/10/2016
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

        <div class="container">
            <div class="row">
                <h2 class="text-center">System Log</h2>
                <div class="col-md-10 col-md-offset-1">
                    <table class="table table-hover table-striped table-bordered text-center">
                        <tr>
                            <th>ID</th>
                            <th>User Name</th>
                            <th>Action</th>
                            <th>Time</th>
                        </tr>
                        <%

                            U_AS_Select dbs = new U_AS_Select();

                            try {
                                ArrayList<ArrayList<String>> rs = dbs.auditingList();
                                ArrayList<String> rsRow ;

                                for (int i=0; i<rs.size();i++){
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

                <a class="btn btn-primary pull-right" href="index.jsp">Back</a>

            </div>
        </div>


