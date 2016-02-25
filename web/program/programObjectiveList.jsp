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
<%

    if(request.getParameter("status") != null){

                        /*out.print("<div id=\"alert\"  class=\"alert alert-danger fade in\"  role=\"alert\" >\n" +
                                "                        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                                "                            <span aria-hidden=\"true\">&times;</span>\n" +
                                "                        </button>\n" +
                                "                        <strong id=\"alertt\" >\n" +
                                "                            " + request.getParameter("err")+
                                "                        </strong>\n" +
                                "                    </div>");*/

        out.print("<script type=\"text/javascript\">\n" +
                "    $(window).load(function(){\n" +
                "        $('#errModal').modal('show');\n" +
                "    });\n" +
                "    function goToNormal(){\n" +
                "        window.location.href =\"/program/\";\n" +
                "    }\n" +
                "</script>" +
                "<!-- Modal -->\n" +
                "                    <div class=\"modal fade\" id=\"errModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
                "                        <div class=\"modal-dialog\">\n" +
                "                            <div class=\"modal-content\">\n" +
                "                                <div class=\"modal-header\">\n" +
                "                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n" +
                "                                    <h4 class=\"modal-title\" id=\"myModalLabel\">INFO</h4>\n" +
                "                                </div>\n" +
                "                                <div class=\"modal-body\">\n");
        if(request.getParameter("status").equals("Success")){
            out.print("All user were added to the database Successfully.");
        } else if(request.getParameter("status").equals("failed")){
            out.print("Something wrong!, please try again.");
        }else if(request.getParameter("status").equals("userAdded")){
            out.print("User was added successfully!");
        }else if(request.getParameter("status").equals("userUpdated")){
            out.print("User was updated successfully!");
        }else if(request.getParameter("status").equals("userDeleted")){
            out.print("User was deleted successfully!");
        }
        out.print("                                </div>\n" +
                "                                <div class=\"modal-footer\">\n" +
                "\n" +
                "                                    <div class=\"text-center\">\n" +
                "                                        <a type=\"button\"  data-dismiss=\"modal\"   class=\"btn btn-default btn-simple\">OK</a>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>");


    }

%>
        <div class="container">
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Add Program Objective</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">
                    <p>Click "Add" to enter program objectives for <%=request.getParameter("name")%> program</p>

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table table-striped table-bordered text-center">
                            <tr>
                                <th>Objectives</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
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
                                                "                            <form method=\"post\" action=\"/Delete Objective\">\n" +
                                                "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                "                            <input name=\"Objid\" value=\""+rsRow.get(0)+"\" hidden />\n" +
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
                        </table>
                    </div>
                    <a class="btn btn-success btn-fill" data-toggle="modal" data-target="#addModal">Add</a>
                    <a class="btn btn-success btn-fill" href="index.jsp?page=OutcomeList&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Next</a>

                    <!-- Modal -->
                    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">Program Objectives</h4>
                                </div>
                                <div class="modal-body">
                                    If you have the Program Objectives in an Excel sheet, you can import the file to add them all at once
                                </div>
                                <div class="modal-footer">
                                    <div class="left-side">
                                        <a type="button" href="index.jsp?page=addObj&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>"  class="btn btn-default btn-simple">Enter manually</a>
                                    </div>
                                    <div class="divider"></div>
                                    <div class="right-side">
                                        <a type="button" href="index.jsp?name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>&page=import&data=obj"   class="btn btn-default btn-simple">Import Excel file</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- End of container -->
        </div>
