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
<script src="/js/bootbox.min.js" type="text/javascript"></script>

    <div class="container">
        <!-- Here is row -->
        <div class="row">
            <h2 class="text-center">User Management</h2>
            <div class="col-md-12">

                <%

                    if(request.getParameter("status") != null){

                        out.print("<script type=\"text/javascript\">\n" +
                                "    $(window).load(function(){\n" +
                                "       bootbox.alert(\""+request.getParameter("status")+"\")\n" +
                                "    });\n" +
                                "</script>");
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
                                                    "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                    "                               </td>" +
                                                    "                           </form>" +
                                                    "                           <form method=\"post\" class=\"delForm\" action=\"/deleteUser\">\n" +
                                                    "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                    "                            <input name=\"id\" value=\""+suRow.get(0)+"\" hidden />\n" +
                                                    "                            <input name=\"type\" value=\"Superuser\" hidden />\n" +
                                                    "                               <td>" +
                                                    "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-link btn-T \"><i class=\"fui-trash icon30\"></i></button>\n" +
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
                                                "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                "                               </td>" +
                                                "                           </form>" +
                                                "                           <form method=\"post\" class=\"delForm\" action=\"/deleteUser\">\n" +
                                                "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                "                            <input name=\"id\" value=\""+fmRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"type\" value=\"Faculty_Member\" hidden />\n" +
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
                                                "                           <form method=\"post\" class=\"delForm\" name=\"delForm\" action=\"/deleteUser\">\n" +
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

                <button class="btn btn-primary"  onclick="importPopup('index.jsp?page=import','index.jsp?page=add',
                            'Add new user',
                            'If you have new users details in an Excel sheet, you can import the file to add them all at once');">Add</button>
                <a  class="btn btn-primary pull-right" href="/index.jsp">Back</a>



                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>

    </div>
