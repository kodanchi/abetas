<%@ page import="ASDB.AS_Select" %>
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

    String id = "";
    String Termid = "";
    if(request.getParameter("cycle") != null && request.getParameter("term") != null){
        id  = request.getParameter("cycle");
        Termid  = request.getParameter("term");
        out.println("id is : "+id);
        out.print("Termid is : "+Termid);
    }

%>

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
            <!--   Here is row -->
            <div class="row">
                <h2 class="text-center">Add Program Performance Indicator</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">



                        <p>Click "Add" to enter <% if(request.getParameter("programID")!=null) {
                            AS_Select ssselect = new AS_Select();
                            try {
                                String rs = ssselect.selectProgramName(Integer.parseInt(request.getParameter("programID")));
                                out.print(rs);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        %> program performance indicator</p>

                    <!--    <div class="panel panel-default">
                            <!-- Default panel contents -->

                            <!-- Table -->
                    <table class="table table-striped table-bordered text-center">
                                <tr>

                                    <th>Label</th>
                                    <th>Performance Indicator</th>
                                    <th>Edit</th>
                                    <th>Delete</th>

                                </tr>
                            <%


                                if(request.getParameter("programID")!=null) {

                                    AS_Select aselect = new AS_Select();
                                    try {
                                        ArrayList<ArrayList<String>> rs = aselect.selectPerformanceIndicators(Integer.parseInt(request.getParameter("programID")), Integer.parseInt(id));
                                        ArrayList<String> rsRow;

                                        for (int i = 0; i < rs.size(); i++) {
                                            rsRow = new ArrayList<String>();
                                            rsRow = rs.get(i);
                                            out.print("<tr>");
                                            for (int j = 0; j < rsRow.size(); j++) {
                                                out.print("<td>" + rsRow.get(j) + "</td>");
                                            }
                                            out.print("<td>" +
                                                    "                            <form method=\"post\" action=\"index.jsp\">\n" +
                                                    "                            <input name=\"page\" value=\"updatePI\" hidden />\n" +
                                                    "                            <input name=\"PILabel\" value=\"" + rsRow.get(0) + "\" hidden />\n" +
                                                    "                            <input name=\"PIValue\" value=\"" + rsRow.get(1) + "\" hidden />\n" +
                                                    "                            <input name=\"cycle\" value=\"" + request.getParameter("cycle") + "\" hidden />\n" +
                                                    "                            <input name=\"term\" value=\"" + request.getParameter("term") + "\" hidden />\n" +
                                                    "                            <input name=\"programID\" value=\"" + request.getParameter("programID") + "\" hidden />\n" +
                                                    "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                    "                               </td>" +
                                                    "                            <td></form>" +
                                                    "                            <form method=\"post\" action=\"/DeletePI\">\n" +
                                                    "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                    "                            <input name=\"PILabel\" value=\"" + rsRow.get(0) + "\" hidden />\n" +
                                                    "                            <input name=\"cycle\" value=\"" + request.getParameter("cycle") + "\" hidden />\n" +
                                                    "                            <input name=\"term\" value=\"" + request.getParameter("term") + "\" hidden />\n" +
                                                    "                            <input name=\"programID\" value=\"" + request.getParameter("programID") + "\" hidden />\n" +
                                                    "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-link btn-T \"><i class=\"fui-trash icon30\"></i></button>\n" +
                                                    "                        </form></td>" +
                                                    "</tr>");
                                        }

                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("  yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy           "+ request.getParameter("programID"));
                                }else {
                                    System.out.println("  gsgsgsg    gsgsggssdfgs       djskvdsj    sgsgs   sgsgsgsg   fsdsdg            ");
                                }

                            %>


                            </table>


                    <!-- </div> -->
                    <a class="btn btn-success pull-left"  data-toggle="modal" data-target="#addModal"  >Add</a>
                    <a class="btn btn-success pull-right" href="index.jsp?page=addTerm&cycle=<%=id%>">Cancel</a>
                    <a class="btn btn-success pull-right" href="index.jsp?page=LinkPIOutList&cycle=<%=id%>&term=<%=Termid%>&programID=<%=request.getParameter("programID")%>">Next</a>

                    <!-- Modal -->
                    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">Add Performance Indicators</h4>
                                </div>
                                <div class="modal-body">
                                    If you have the Performance Indicators details in an Excel sheet, you can import the file to add them all at once
                                </div>
                                <div class="modal-footer">
                                    <div class="left-side">
                                        <a type="button" href="index.jsp?page=addPI&cycle=<%=id%>&term=<%=Termid%>&programID=<%=request.getParameter("programID")%>"  class="btn btn-default btn-simple">Enter manually</a>
                                    </div>
                                    <div class="divider"></div>
                                    <div class="right-side">
                                        <a type="button" href="index.jsp?&cycle=<%=id%>&term=<%=Termid%>&programID=<%=request.getParameter("programID")%>&page=import&data=pis"   class="btn btn-default btn-simple">Import Excel file</a>
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
