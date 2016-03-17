<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/29/2016
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/10159067/show-hide-button-in-table-row-during-mouseover
--%>
<%@ page import="ASDB.U_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

    <div class="container">
        <!-- Here is row -->
        <div class="row">
            <h2 class="text-center">User Management</h2>
            <div class="col-md-10 col-md-offset-1">

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
                                "    function goToNormal(){\n" +
                                "        window.location.href =\"/users/\";\n" +
                                "    }\n" +
                                "</script>" +
                                "<!-- Modal -->\n" +
                                "                    <div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
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
                                "                                        <a type=\"button\"  data-dismiss=\"modal\" onclick=\"goToNormal()\"  class=\"btn btn-default btn-simple\">OK</a>\n" +
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                            </div>\n" +
                                "                        </div>\n" +
                                "                    </div>");


                    }

                %>

                <script type="text/javascript">
                    $(window).load(function(){
                        $('#asbtn').click();
                        $('#ETable').hide();
                        $('#FTable').hide();
                    });

                    function showETable(){
                        $('#ETable').show();
                        $('#ASTable').hide();
                        $('#FTable').hide();
                    }

                    function showFTable(){
                        $('#FTable').show();
                        $('#ETable').hide();
                        $('#ASTable').hide();
                    }

                    function showASTable(){
                        $('#ASTable').show();
                        $('#ETable').hide();
                        $('#FTable').hide();
                    }

                    function onDelete(formName) {
                        $('#delCheckModal').modal('show');
                        $('#delBtn').click(function () {
                            formName.submit()
                        });
                    }

                </script>
                <div class="text-center">
                    <button id="asbtn" class="btn tab tab-close-button" onclick="showASTable()" >Superusers</button>
                    <button class="btn tab tab-close-button" onclick="showFTable()" >Faculty Members</button>
                    <button class="btn tab tab-close-button" onclick="showETable()" >Evaluators</button>
                </div>
                <legend></legend>

                    <div id="ASTable">
                        <!-- Table -->
                        <table class="table table-hover table-striped table-bordered text-center"  >
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


                                U_AS_Select dba=new U_AS_Select();
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
                                                    "                            <input name=\"type\" value=\"Superuser\" hidden />\n" +
                                                    "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-warning btn-simple\"><i class=\"fa fa-pencil fa-2x \"></i></button>\n" +
                                                    "                               </td>" +
                                                    "                           </form>" +
                                                    "                           <form method=\"post\" action=\"/deleteUser\">\n" +
                                                    "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                    "                            <input name=\"id\" value=\""+suRow.get(0)+"\" hidden />\n" +
                                                    "                            <input name=\"type\" value=\"Superuser\" hidden />\n" +
                                                    "                               <td>" +
                                                    "                            <button type=\"button\"  onclick=\"onDelete(this.form)\" title=\"Delete\" class=\"btn btn-danger btn-simple\"><i class=\"fa fa-trash-o fa-2x \"></i></button>\n" +
                                                    "                               </td>"+
                                                    "                        </form>" +
                                                    "</tr>");
                                        }else {
                                            out.print("<td>Admin</td>");
                                            out.print("<td></td><td></td></tr>");
                                        }

                                    }


                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }


                            %>


                        </table >
                    </div>

                    <div id="FTable">
                        <!-- Table -->
                        <table class="table table-hover table-striped table-bordered text-center"  >
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


                                try {

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
                                                "                            <input name=\"type\" value=\"Faculty_Member\" hidden />\n" +
                                                "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-warning btn-simple\"><i class=\"fa fa-pencil fa-2x \"></i></button>\n" +
                                                "                               </td>" +
                                                "                           </form>" +
                                                "                           <form method=\"post\" action=\"/deleteUser\">\n" +
                                                "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                "                            <input name=\"id\" value=\""+fmRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"type\" value=\"Faculty_Member\" hidden />\n" +
                                                "                               <td>" +
                                                "                            <button type=\"button\" onclick=\"onDelete(this.form)\" title=\"Delete\" class=\"btn btn-danger btn-simple\"><i class=\"fa fa-trash-o fa-2x \"></i></button>\n" +
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

                    <div id="ETable">
                        <!-- Table -->
                        <table class="table table-hover table-striped table-bordered text-center" >
                            <tr class="textContainer">
                                <th>First name</th>
                                <th>Middle name</th>
                                <th>Last name</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Access level</th>
                                <th>Edit</th>
                                <th>Delete</th>

                            </tr>
                            <%

                                try {
                                    ArrayList<ArrayList<String>> eArr = dba.selectAllEvaluators();
                                    ArrayList<String> eRow ;

                                    String calPass = null;
                                    for (int i=0; i<eArr.size();i++){
                                        //suRow = new ArrayList<String>();
                                        eRow = eArr.get(i);
                                        out.print("<tr class=\"textContainer\" >");
                                        for (int j=1; j<eRow.size();j++) {
                                            switch (j){
                                                case 1:
                                                    calPass = eRow.get(j).substring(0,3);
                                                    out.print("<td>"+eRow.get(j)+"</td>");
                                                    break;
                                                case 2:
                                                    calPass += eRow.get(j).substring(0,3);
                                                    out.print("<td>"+eRow.get(j)+"</td>");
                                                    break;
                                                case 5:
                                                    out.print("<td>"+calPass+"123"+"</td>");
                                                    break;
                                                default:
                                                    out.print("<td>"+eRow.get(j)+"</td>");
                                            }



                                        }


                                        out.print("<td>" +
                                                "                           <form method=\"post\"  action=\"index.jsp\">\n" +
                                                "                            <input name=\"page\" value=\"update\" hidden />\n" +
                                                "                            <input name=\"id\" value=\""+eRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"type\" value=\"Evaluator\" hidden />\n" +
                                                "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                "                               </td>" +
                                                "                           </form>" +
                                                "                           <form method=\"post\" name=\"delForm\" action=\"/deleteUser\">\n" +
                                                "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                "                            <input name=\"id\" value=\""+eRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"type\" value=\"Evaluator\" hidden />\n" +
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


                        </table >
                    </div>

                <button class="btn btn-primary"  data-toggle="modal" data-target="#myModal">Add</button>
                <a  class="btn btn-primary pull-right" href="/index.jsp">Back</a>

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
                                    <a type="button" href="index.jsp?page=import"   class="btn btn-default btn-simple">Import Excel file</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- Modal -->
                <div class="modal fade" id="delCheckModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myCheckModalLabel">ALERT</h4>
                            </div>
                            <div class="modal-body">
                                Are you sure you want to delete this user!?
                            </div>
                            <div class="modal-footer">
                                <div class="left-side">
                                    <a id="delBtn" type="button"   class="btn btn-default btn-simple">Yes</a>
                                </div>
                                <div class="divider"></div>
                                <div class="right-side">
                                    <a type="button" href="" data-dismiss="modal" aria-hidden="true"  class="Cancel">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>

    </div>
