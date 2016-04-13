
<%@ page import="ASDB.U_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>
<script src="/js/stupidtable.min.js" type="text/javascript"></script>

    <div class="container">
        <!-- Here is row -->
        <div class="row">
            <h2 class="text-center">User Management</h2>
            <div class="col-md-12">

                <%
                    /**
                     * userlist page used to display the list of users
                     */
                    if(request.getParameter("status") != null){
                        out.print("<script type=\"text/javascript\">\n" +
                                "    $(window).load(function(){\n" +
                                "       bootbox.alert(\""+request.getParameter("status")+"\")\n" +
                                "    });\n" +
                                "</script>");
                    }
                %>


                <%

                    if(request.getSession().getAttribute("Msg")!= null){
                        String msg = (String) request.getSession().getAttribute("Msg");
                        out.print("<script>");
                        out.print("$(document).ready(function(){\n" +
                                "bootbox.alert(\""+msg+"\");\n");

                        out.print("$(function(){\n");
                        if(msg.startsWith("Faculty_Member")){
                            /*out.print("$(this).ready(function(){\n");
                            out.print("showFTable()");
                            out.print("});");*/

                            out.print("$('#FTable').show();\n" +
                                    "                        $('#ETable').hide();\n" +
                                    "                        $('#ASTable').hide();");
                        }else {
                            out.print("$('#ETable').show();\n" +
                                    "                        $('#ASTable').hide();\n" +
                                    "                        $('#FTable').hide();");
                        }
                        out.print("});\n");
                        out.print("\n});\n</script>");

                        request.getSession().removeAttribute("Msg");
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
                        <table class="table table-hover table-striped table-bordered text-center" id="sTablee" >
                            <thead>
                            <tr class="textContainer">
                                <th data-sort="string">First name</th>
                                <th data-sort="string">Middle name</th>
                                <th data-sort="string">Last name</th>
                                <th data-sort="string">Username</th>
                                <th data-sort="string">Email</th>
                                <th data-sort="string">Access level</th>
                                <th>Edit</th>
                                <th>Delete</th>

                            </tr>
                            </thead>
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
                                            switch (j){
                                                case 2:
                                                    out.print("<td>"+suRow.get(j).substring(0,1)+"</td>");
                                                    break;
                                                default:
                                                    out.print("<td >"+suRow.get(j)+"</td>");
                                            }
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
                        <table class="table table-hover table-striped table-bordered text-center" id="sTableee" >
                            <thead>
                            <tr class="textContainer">
                                <th data-sort="string">First name</th>
                                <th data-sort="string">Middle name</th>
                                <th data-sort="string">Last name</th>
                                <th data-sort="string">Username</th>
                                <th data-sort="string">Email</th>
                                <th>Access level</th>
                                <th>Edit</th>
                                <th>Delete</th>

                            </tr>
                            </thead>
                            <%


                                try {

                                    ArrayList<ArrayList<String>> fmArr = dba.selectAllFaculty();
                                    ArrayList<String> fmRow ;

                                    for (int i=0; i<fmArr.size();i++){
                                        fmRow = new ArrayList<String>();
                                        fmRow = fmArr.get(i);
                                        out.print("<tr class=\"textContainer\" >");
                                        for (int j=1; j<fmRow.size();j++) {
                                            switch (j){
                                                case 2:
                                                    out.print("<td>"+fmRow.get(j).substring(0,1)+"</td>");
                                                    break;
                                                default:
                                                    out.print("<td >"+fmRow.get(j)+"</td>");
                                            }

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
                        <table class="table table-hover table-striped table-bordered text-center" id="sTable">
                            <thead>

                            <tr class="textContainer">
                                <th data-sort="string">First name</th>
                                <th data-sort="string">Middle name</th>
                                <th data-sort="string">Last name</th>
                                <th data-sort="string">Username</th>
                                <th data-sort="string">Password</th>
                                <th>Access level</th>
                                <th>Program</th>
                                <th>Edit</th>
                                <th>Delete</th>

                            </tr>

                            </thead>

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
                                                    out.print("<td>"+eRow.get(j).substring(0,1)+"</td>");
                                                    break;
                                                case 3:
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
                <script>
                    $("#sTable").stupidtable();
                </script>
                <script>
                    $("#sTablee").stupidtable();
                </script>
                <script>
                    $("#sTableee").stupidtable();
                </script>

                <button class="btn btn-primary"  onclick="importPopup('index.jsp?page=import','index.jsp?page=add',
                            'Add new user',
                            'If you have new users details in an Excel sheet, you can import the file to add them all at once');">Add</button>
                <a  class="btn btn-primary pull-right" href="/index.jsp">Back</a>



                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>

    </div>
