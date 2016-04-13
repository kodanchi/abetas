<%@ page import="ASDB.P_AS_Select" %>
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
<script src="/js/bootbox.min.js" type="text/javascript"></script>
<script src="/js/stupidtable.min.js" type="text/javascript"></script>

<%

    if(request.getParameter("status") != null){

        out.print("<script type=\"text/javascript\">\n" +
                "    $(window).load(function(){\n" +
                "       bootbox.alert(\""+request.getParameter("status")+"\")\n" +
                "    });\n" +
                "</script>");
    }

%>
        <div class="container">


            <div class="row">
                <div class="col-md-7 col-md-offset-2">
                    <nav>
                        <ol class="cd-breadcrumb triangle small">
                            <li><em>Program</em></li>
                            <li class="current"><em>Objectives</em></li>
                            <li><em>Outcomes</em></li>
                            <li><em>Link</em></li>
                            <li><em>Courses</em></li>
                        </ol>
                    </nav>
                </div>
            </div>

            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Add Program Objective</h2>
                <div class="col-md-10 col-md-offset-1">
                    <p>Click "Add" to enter program objectives for <%=request.getParameter("name")%> program</p>

                    <div class="input-group"> <span class="input-group-addon">Filter</span>

                        <input id="filter" type="text" class="form-control" placeholder=" by program objective">
                    </div>
                        <!-- Table -->
                        <table class="table table-hover table-striped table-bordered text-center" id="sTable">
                            <thead>
                            <tr>
                                <th data-sort="string">Objectives</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <tbody class="searchable">
                                <%

                                    P_AS_Select aselect = new P_AS_Select();
                                    int id = Integer.parseInt(request.getParameter("id"));

                                    try {
                                        ArrayList<ArrayList<String>> rs = aselect.selectObjective(id);
                                        ArrayList<String> rsRow ;

                                        for (int i=0; i<rs.size();i++){
                                            rsRow = new ArrayList<String>();
                                            rsRow = rs.get(i);
                                            out.print("<tr>");
                                            for (int j=1; j<rsRow.size();j++) {
                                                out.print("<td>"+rsRow.get(j)+"</td>");

                                            }
                                            out.print("<td>" +
                                                    "                            <form method=\"post\" action=\"index.jsp\">\n" +
                                                    "                            <input name=\"page\" value=\"updateObj\" hidden />\n" +
                                                    "                            <input name=\"Objid\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                    "                            <input name=\"ObjValue\" value=\""+rsRow.get(1)+"\" hidden />\n" +
                                                    "                            <input name=\"name\" value=\""+request.getParameter("name")+"\" hidden />\n" +
                                                    "                            <input name=\"id\" value=\""+request.getParameter("id")+"\" hidden />\n" +
                                                    "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                    "                               </td>" +
                                                    "                            </form>" +
                                                    "                            <form method=\"post\" class=\"delForm\" action=\"/Delete Objective\">\n" +
                                                    "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                    "                            <input name=\"Objid\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                    "                            <input name=\"name\" value=\""+request.getParameter("name")+"\" hidden />\n" +
                                                    "                            <input name=\"id\" value=\""+request.getParameter("id")+"\" hidden />\n" +
                                                    "                               <td>" +
                                                    "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-link btn-T \"><i class=\"fui-trash icon30\"></i></button>\n" +
                                                    "                               </td>"+
                                                    "                        </form>" +
                                                    "</tr>" +
                                                    "" +
                                                    "");
                                        }

                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }



                                %>
                            </tbody>
                        </table>

                    <script>
                        $("#sTable").stupidtable();
                    </script>

                    <a class="btn btn-default pull-right" href="index.jsp?page=programList">Cancel</a>

                    <a class="btn btn-primary" onclick="importPopup('index.jsp?name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>&page=import&data=obj',
                            'index.jsp?page=addObj&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>',
                            'Add new objective',
                            'If you have new program objectives details in an Excel sheet, you can import the file to add them all at once');">Add</a>


                    <a class="btn btn-primary pull-right" href="index.jsp?page=OutcomeList&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Next</a>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- End of container -->
        </div>
