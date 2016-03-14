<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 2/1/2016
  Time: 4:10 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>
<script src="/js/bootstrap-select.min.js" type="text/javascript"></script>


<%

    String id = "";
    if(request.getParameter("cycle") != null){
        id  = request.getParameter("cycle");
        out.println("id is : "+id);
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
            <!-- Here is row -->
            <div class="row">

            <h2 class="text-center">Add Program Performance Indicator</h2>

                <div class="col-md-8 col-md-offset-2">




                    <%--<div class="dropdown">
                                <a href="#" class="btn dropdown-toggle" data-toggle="dropdown">--%>
                                    <%--<% if(request.getParameter("programID")!=null) {
                                        C_AS_Select sselect = new C_AS_Select();
                                        try {
                                            String rs = sselect.selectProgramName(Integer.parseInt(request.getParameter("programID")));
                                            out.print(rs);
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }else {
                                        out.print("Choose a program");
                                    }
                                    %>--%>
                        <select class="selectpicker" id="prgList" onchange="onPrgramChange(<%=id%>)" data-live-search="true">
                            <option data-tokens="select">Select program</option>

                                    <%
                                        C_AS_Select select = new C_AS_Select();
                                        ArrayList<Integer> pid = new ArrayList<Integer>();
                                        try {
                                            ArrayList<ArrayList<String>> rs = select.selectAllPrograms();
                                            ArrayList<String> rsRow;


                                            for (int i=0; i<rs.size();i++) {
                                                rsRow = rs.get(i);

                                                out.print("<option data-tokens=\""+rsRow.get(1)+"\" " );

                                                if(request.getParameter("programID")!=null && request.getParameter("programID").equals(rsRow.get(0))){
                                                    out.print(" selected=\"selected\" ");
                                                }

                                                        out.print(" value=\""+rsRow.get(0)+"\">"+rsRow.get(1)+"</option>");

                                            }
                                            if(request.getParameter("programID")!=null){
                                                out.print("<script>\n" +
                                                        "                                $(document).ready(function(){\n" +
                                                        "                                            onPrgramChange("+id+");\n" +
                                                        "                                        });\n" +
                                                        "                            </script>");
                                            }
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    %>

                            <script>
                                function onPrgramChange(cid){
                                    var pl = document.getElementById("prgList");
                                    var pid = pl.options[pl.selectedIndex].value;
                                    if(pid == "Select program"){
                                        $("#pIList").hide();
                                    }else {
                                        show('page', false);
                                        show('loading', true);
                                        $.ajax({
                                            type: 'POST',
                                            data: {
                                                cid: cid,
                                                pid: pid
                                            },
                                            url: '/selectPI',
                                            success: function (result) {
                                                $('#pIList').html(result);
                                                $('#pIList').show();
                                                show('page', true);
                                                show('loading', false);

                                            }

                                        })
                                    }
                                }
                            </script>
                    </select>
                        </div>

                        <br>
                        <br>
                        <br>

                            <input name="cycle" value="<%=id%>" hidden/>




                <div id="pIList"></div>
<%--

                            <%
                                if(request.getParameter("programID")!=null) {
                                    C_AS_Select bselect = new C_AS_Select();
                                    try {
                                        ArrayList<String> rs = bselect.selectPerformanceIndicatorsForCycle(Integer.parseInt(request.getParameter("programID")), Integer.parseInt(id));

                                            out.print("<p>Click \"Add\" to add new performance indicator for ");
                                                C_AS_Select ssselect = new C_AS_Select();
                                                try {
                                                    String Pname = ssselect.selectProgramName(Integer.parseInt(request.getParameter("programID")));
                                                    out.print(Pname);
                                                } catch (ClassNotFoundException e) {
                                                    e.printStackTrace();
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                }
                                            out.print(" program</p>");













                                        int size=0;






                                        if(request.getParameter("programID")!=null) {

                                            out.print("<div class=\"panel panel-default\">\n" +
                                                    "                            <!-- Default panel contents -->\n" +
                                                    "\n" +
                                                    "                            <!-- Table -->\n" +
                                                    "                            <table class=\"table\">\n" +
                                                    "                                <tr>\n" +
                                                    "\n" +
                                                    "                                    <th>Label</th>\n" +
                                                    "                                    <th>Performance Indicator</th>\n" +
                                                    "                                    <th>Edit</th>\n" +
                                                    "                                    <th>Delete</th>\n" +
                                                    "\n" +
                                                    "                                </tr>" +
                                                    "" +
                                                    "" +
                                                    "");


                                            try {
                                                C_AS_Select aselect = new C_AS_Select();
                                                ArrayList<ArrayList<String>> rsss = aselect.selectPerformanceIndicators(Integer.parseInt(request.getParameter("programID")), Integer.parseInt(id));
                                                ArrayList<String> rsRow;

                                                for (int i = 0; i < rsss.size(); i++) {
                                                    rsRow = new ArrayList<String>();
                                                    rsRow = rsss.get(i);
                                                    size=rsss.size();
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
                                                            "                            <input name=\"programID\" value=\"" + request.getParameter("programID") + "\" hidden />\n" +
                                                            "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                            "                               </td>" +
                                                            "                            <td></form>" +
                                                            "                            <form method=\"post\" action=\"/DeletePI\">\n" +
                                                            "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                            "                            <input name=\"PILabel\" value=\"" + rsRow.get(0) + "\" hidden />\n" +
                                                            "                            <input name=\"cycle\" value=\"" + request.getParameter("cycle") + "\" hidden />\n" +
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



                                        out.print("</table></div>\n");


                            %>



                       &lt;%&ndash; <a class="btn btn-primary btn-fill pull-left"  onclick="new function(){
                            bootbox.dialog({
                                message: 'If you have the Performance Indicators details in an Excel sheet, you can import the file to add them all at once',
                                title: 'Add Performance Indicators',
                                buttons: {
                                    importBtn: {
                                        label: 'Import Excel Sheet',
                                        className: 'btn-primary',
                                        callback: function() {
                                            window.location.href = 'index.jsp?&cycle=<%=id%>&programID=<%=request.getParameter("programID")%>&page=import&data=pis';
                                        }
                                    },
                                    enterBtn: {
                                        label: 'Enter Manually',
                                        className: 'btn-primary',
                                        callback: function() {
                                            window.location.href = 'index.jsp?page=addPI&cycle=<%=id%>&programID=<%=request.getParameter("programID")%>';
                                        }
                                    },
                                    cancelBtn: {
                                        label: 'Cancel',
                                        className: 'btn-default',
                                        callback: function() {

                                        }
                                    }
                                }
                            });
                        }"  >Add</a>&ndash;%&gt;
                        <%
                                        if(size>0) {
                                            out.print("<a class=\"btn btn-success btn-fill pull-right\" href=\"index.jsp?page=rubricNames&cycle=" + id + "&programID=" + request.getParameter("programID") + "\">Next</a>");
                                        }



                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    System.out.println("no program");
                                }
                            %>

--%>





                    <a class="btn btn-default pull-right" href="index.jsp">Cancel</a>
<%--

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
                                        <a type="button" href="index.jsp?page=addPI&cycle=<%=id%>&programID=<%=request.getParameter("programID")%>"  class="btn btn-default btn-simple">Enter manually</a>
                                    </div>
                                    <div class="divider"></div>
                                    <div class="right-side">
                                        <a type="button" href="index.jsp?&cycle=<%=id%>&programID=<%=request.getParameter("programID")%>&page=import&data=pis"   class="btn btn-default btn-simple">Import Excel file</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

--%>

                    <!-- End of col -->
                </div>
                <!-- End of row -->
            </div>

        </div>
