
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="ASDB.U_AS_Select" %>
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
                            /**
                             * log page used to display the history
                             */
                            int pageNum = 1;
                            int recordsPerPage = 20;
                            int noOfPages = 0;
                            if(request.getParameter("pnum")!= null){
                                pageNum = Integer.valueOf(request.getParameter("pnum"));
                            }

                            U_AS_Select dbs = new U_AS_Select();



                            try {

                                int records = dbs.selectNumberOfRecords("abetasdb.auditing");

                                ArrayList<ArrayList<String>> rs = dbs.auditingList((pageNum-1)*recordsPerPage,recordsPerPage);
                                ArrayList<String> rsRow ;

                                for (int i=0; i<rs.size();i++){
                                    rsRow = rs.get(i);
                                    out.print("<tr>");
                                    for (int j=0; j<rsRow.size();j++) {
                                        out.print("<td>"+rsRow.get(j)+"</td>");
                                    }
                                    out.print("</tr>");
                                }

                                noOfPages = (int) Math.ceil(records * 1.0 / recordsPerPage);

                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }



                        %>
                        <tfoot>

                        </tfoot>
                    </table>
                    <div class="row">
                        <a class="btn btn-primary pull-right mrgB" href="/index.jsp">Back</a>
                        <ul class="pagination">

                            <li class="previous pull-left" >
                                <%=pageNum!=1?"<a href=\"index.jsp?page=log&pnum="+(pageNum-1)+"\">Previous</a>":""%>
                            </li>
                            <%
                                for (int i=1;i<=noOfPages;i++){
                                    out.println("                   <li><a \n" );


                                    if(pageNum == i) {
                                        out.print(" class=\"active\" ");
                                    }else {
                                        out.println("  href=\"index.jsp?page=log&pnum=" + i+"\"" );
                                    }
                                    out.print(">"+i +"</a></li>");
                                }
                            %>

                            <li class="next pull-right">
                                <%=pageNum!=noOfPages?"<a href=\"index.jsp?page=log&pnum="+(pageNum+1)+"\">Next</a>":""%>
                            </li>
                        </ul>
                    </div>
                </div>


            </div>
        </div>


