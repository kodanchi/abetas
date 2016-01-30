<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/29/2016
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/10159067/show-hide-button-in-table-row-during-mouseover
--%>
<%@ page import="com.database.ASDB" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<div class="section">
    <div class="container">
        <!--         what is row -->
        <div class="row tim-row">
            <h2 class="text-center">User Management</h2>
            <legend></legend>
            <div class="col-md-8 col-md-offset-2">

                <div class="panel panel-default">
                    <!-- Default panel contents -->

                    <!-- Table -->
                    <table class="table table-hover" id="table-sever-list" >
                        <tr >
                            <th>First name</th>
                            <th>Middle name</th>
                            <th>Last name</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Access level</th>
                            <th></th>

                        </tr>
                        <%


                            ASDB dba=new ASDB();
                            try {
                                ArrayList<ArrayList<String>> suArr = dba.selectAllSuperusers();
                                ArrayList<String> suRow ;

                                for (int i=0; i<suArr.size();i++){
                                    suRow = new ArrayList<String>();
                                    suRow = suArr.get(i);
                                    out.print("<tr>");
                                    for (int j=1; j<suRow.size();j++) {
                                        out.print("<td>"+suRow.get(j)+"</td>");
                                    }

                                    if(!suRow.get(6).equals("1")){
                                        out.print("<td><a class=\"rowOption\" href=\"index.jsp?page=update&id="+suRow.get(0)
                                                +"&type=superuser\" >edit" +
                                                "</a></td></tr>");
                                    }else {
                                        out.print("<td></td></tr>");
                                    }

                                }

                                ArrayList<ArrayList<String>> fmArr = dba.selectAllFaculty();
                                ArrayList<String> fmRow ;

                                for (int i=0; i<fmArr.size();i++){
                                    fmRow = new ArrayList<String>();
                                    fmRow = fmArr.get(i);
                                    out.print("<tr>");
                                    for (int j=1; j<fmRow.size();j++) {
                                        out.print("<td>"+fmRow.get(j)+"</td>");
                                    }

                                        out.print("<td><a class=\"rowOption\" href=\"index.jsp?page=update&id="+fmRow.get(0)
                                                +"&type=faculty\" >edit" +
                                                "</a></td></tr>");

                                }

                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }


                        %>

                    </table>
                </div>
                <a class="btn btn-success btn-fill" href="index.jsp?page=add">Add</a>
                <button class="btn btn-primary">Back</button>


                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>


        <!-- Modal Bodies come here -->
    </div>
</div>
