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
            <h2 class="text-center">User Management</h2>
            <legend></legend>
            <div class="col-lg-10 col-md-offset-1">

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
                                "        $('#myModal').modal('show');\n" +
                                "    });\n" +
                                "</script>" +
                                "<!-- Modal -->\n" +
                                "                    <div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
                                "                        <div class=\"modal-dialog\">\n" +
                                "                            <div class=\"modal-content\">\n" +
                                "                                <div class=\"modal-header\">\n" +
                                "                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n" +
                                "                                    <h4 class=\"modal-title\" id=\"myModalLabel\">ALERT</h4>\n" +
                                "                                </div>\n" +
                                "                                <div class=\"modal-body\">\n");
                        if(request.getParameter("status").equals("Success")){
                            out.print("All user were added to the database Successfully.");
                        } else if(request.getParameter("status").equals("failed")){
                            out.print("Something wrong!, please try again.");
                        }
                                out.print("                                </div>\n" +
                                "                                <div class=\"modal-footer\">\n" +
                                "\n" +
                                "                                    <div class=\"text-center\">\n" +
                                "                                        <a type=\"button\"  data-dismiss=\"modal\"  class=\"btn btn-default btn-simple\">OK</a>\n" +
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                            </div>\n" +
                                "                        </div>\n" +
                                "                    </div>");


                    }

                %>


                <div class="panel panel-default">
                    <!-- Default panel contents -->

                    <!-- Table -->
                    <table class="table table-hover" id="table-sever-list" >
                        <tr class="textContainer">
                            <th>First name</th>
                            <th>Middle name</th>
                            <th>Last name</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Access level</th>
                            <th>Edit</th>
                            <th>Delete</th>

                        </tr>
                        <%


                            AS_Select dba=new AS_Select();
                            try {
                                ArrayList<ArrayList<String>> suArr = dba.selectAllSuperusers();
                                ArrayList<String> suRow ;

                                for (int i=0; i<suArr.size();i++){
                                    //suRow = new ArrayList<String>();
                                    suRow = suArr.get(i);
                                    out.print("<tr class=\"textContainer\" >");
                                    for (int j=1; j<suRow.size() - 1;j++) {
                                        out.print("<td>"+suRow.get(j)+"</td>");
                                    }

                                    if(!suRow.get(6).equals("1")){
                                        out.print("<td>Superuser</td>");

                                        out.print("<td>" +
                                                "                           <form method=\"post\" action=\"index.jsp\">\n" +
                                                "                            <input name=\"page\" value=\"update\" hidden />\n" +
                                                "                            <input name=\"id\" value=\""+suRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"type\" value=\"superuser\" hidden />\n" +
                                                "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-warning btn-simple\"><i class=\"fa fa-pencil fa-2x \"></i></button>\n" +
                                                "                               </td>" +
                                                "                           </form>" +
                                                "                           <form method=\"post\" action=\"index.jsp\">\n" +
                                                "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                "                            <input name=\"id\" value=\""+suRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"type\" value=\"superuser\" hidden />\n" +
                                                "                               <td>" +
                                                "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-danger btn-simple\"><i class=\"fa fa-trash-o fa-2x \"></i></button>\n" +
                                                "                               </td>"+
                                                "                        </form>" +
                                                "</tr>");
                                    }else {
                                        out.print("<td>Admin</td>");
                                        out.print("<td><i class=\"fa fa-pencil fa-2x \"></i></td><td><i class=\"fa fa-trash fa-2x \"></i></td></tr>");
                                    }

                                }

                                ArrayList<ArrayList<String>> fmArr = dba.selectAllFaculty();
                                ArrayList<String> fmRow ;

                                for (int i=0; i<fmArr.size();i++){
                                    fmRow = new ArrayList<String>();
                                    fmRow = fmArr.get(i);
                                    out.print("<tr class=\"textContainer\" >");
                                    for (int j=1; j<fmRow.size();j++) {
                                        out.print("<td >"+fmRow.get(j)+"</td>");
                                    }

                                    out.print("<td>" +
                                            "                           <form method=\"post\" action=\"index.jsp\">\n" +
                                            "                            <input name=\"page\" value=\"update\" hidden />\n" +
                                            "                            <input name=\"id\" value=\""+fmRow.get(0)+"\" hidden />\n" +
                                            "                            <input name=\"type\" value=\"superuser\" hidden />\n" +
                                            "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-warning btn-simple\"><i class=\"fa fa-pencil fa-2x \"></i></button>\n" +
                                            "                               </td>" +
                                            "                           </form>" +
                                            "                           <form method=\"post\" action=\"index.jsp\">\n" +
                                            "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                            "                            <input name=\"id\" value=\""+fmRow.get(0)+"\" hidden />\n" +
                                            "                            <input name=\"type\" value=\"superuser\" hidden />\n" +
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
                <button class="btn btn-success btn-fill"  data-toggle="modal" data-target="#myModal">Add</button>
                <button class="btn btn-primary">Back</button>

                <!-- Modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">User Management</h4>
                            </div>
                            <div class="modal-body">
                                If you have the users' information in an Excel sheet, you can import the file to add them all at once
                            </div>
                            <div class="modal-footer">
                                <div class="left-side">
                                    <a type="button" href="index.jsp?page=add"  class="btn btn-default btn-simple">Enter manually</a>
                                </div>
                                <div class="divider"></div>
                                <div class="right-side">
                                    <a type="button" href="index.jsp?page=import"  class="btn btn-default btn-simple">Import Excel file</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>


        <!-- Modal Bodies come here -->
    </div>
</div>
