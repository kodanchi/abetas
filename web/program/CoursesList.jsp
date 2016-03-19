<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="ASDB.P_AS_Select" %>
<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/4/2016
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>
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
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Add Courses</h2>
                <div class="col-md-8 col-md-offset-2">
                    <p>Click "Add" to add new courses for <%=request.getParameter("name")%> program</p>

                    <div class="input-group"> <span class="input-group-addon">Filter</span>

                        <input id="filter" type="text" class="form-control" placeholder=" by course name, code or level">
                    </div>
                        <!-- Table -->
                        <table class="table table-hover table-striped table-bordered text-center">
                            <tr>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Level</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            <tbody class="searchable">
                                <%


                                    P_AS_Select aselect = new P_AS_Select();
                                    try {
                                        ArrayList<ArrayList<String>> rs = aselect.selectCourses(Integer.parseInt(request.getParameter("id")));
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
                                                    "                            <input name=\"page\" value=\"updateCourses\" hidden />\n" +
                                                    "                            <input name=\"Cid\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                    "                            <input name=\"Courseid\" value=\""+rsRow.get(1)+"\" hidden />\n" +
                                                    "                            <input name=\"CourseName\" value=\""+rsRow.get(2)+"\" hidden />\n" +
                                                    "                            <input name=\"CourseLevel\" value=\""+rsRow.get(3)+"\" hidden />\n" +
                                                    "                            <input name=\"name\" value=\""+request.getParameter("name")+"\" hidden />\n" +
                                                    "                            <input name=\"id\" value=\""+request.getParameter("id")+"\" hidden />\n" +
                                                    "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                    "                               </td>" +
                                                    "                            </form>" +
                                                    "                            <form method=\"post\" class=\"delForm\" action=\"/Delete Course\">\n" +
                                                    "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                    "                            <input name=\"Courseid\" value=\""+rsRow.get(1)+"\" hidden />\n" +
                                                    "                            <input name=\"name\" value=\""+request.getParameter("name")+"\" hidden />\n" +
                                                    "                            <input name=\"id\" value=\""+request.getParameter("id")+"\" hidden />\n" +
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
                        </table>


                    <a class="btn btn-primary" onclick="importPopup('index.jsp?name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>&page=import&data=courses',
                            'index.jsp?page=addCourses&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>',
                            'Add new course',
                            'If you have new courses details in an Excel sheet, you can import the file to add them all at once');" >Add</a>
                    <a class="btn btn-primary pull-right" href="/index.jsp">Finish</a>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- End of container -->
        </div>
