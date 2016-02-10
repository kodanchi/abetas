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

<div class="main">
    <div class="section">
        <div class="container">
            <!--         what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Add Program Performance Indicator</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">

                    <div class="col-md-8">
                        <div class="dropdown">
                            <label>Choose a program: </label>
                            <a href="#" class="btn dropdown-toggle" data-toggle="dropdown">
                                Program
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">

                                <%
                                    AS_Select select = new AS_Select();
                                    ArrayList<Integer> pid = new ArrayList<Integer>();
                                    try {
                                        ArrayList<ArrayList<String>> rs = select.selectAllPrograms();
                                        ArrayList<String> rsRow;


                                        for (int i=0; i<rs.size();i++) {
                                            rsRow = rs.get(i);

                                            //pid.add(Integer.valueOf(rsRow.get(0)));
                                            //System.out.println("pid : "+pid.get(i));
                                            //out.print("<option value="+rsRow.get(0)+">"+rsRow.get(1)+"</option>");
                                            out.print("<li><a href=\"/cycle/index.jsp?page=piList&programID="+rsRow.get(0)+"\">"+rsRow.get(1)+"</a></li>");

                                        }
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                %>
                            </ul>
                        </div>
                    </div>

                    <br>
                    <br>
                    <br>
                    <br>
                    <br>

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

                        <div class="panel panel-default">
                            <!-- Default panel contents -->

                            <!-- Table -->
                            <table class="table">
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
                                        ArrayList<ArrayList<String>> rs = aselect.selectPerformanceIndicators(Integer.parseInt(request.getParameter("programID")));
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
                                                    "                            <input name=\"progID\" value=\"" + request.getParameter("programID") + "\" hidden />\n" +
                                                    "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-warning btn-simple\"><i class=\"fa fa-pencil fa-2x \"></i></button>\n" +
                                                    "                               </td>" +
                                                    "                            </form>" +
                                                    "                            <form method=\"post\" action=\"/DeletePI\">\n" +
                                                    "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                    "                            <input name=\"PILabel\" value=\"" + rsRow.get(0) + "\" hidden />\n" +
                                                    "                            <input name=\"progID\" value=\"" + request.getParameter("programID") + "\" hidden />\n" +
                                                    "                               <td>" +
                                                    "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-danger btn-simple\"><i class=\"fa fa-trash-o fa-2x \"></i></button>\n" +
                                                    "                               </td>" +
                                                    "                        </form>" +
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


                        </div>
                    <a class="btn btn-success btn-fill" href="index.jsp?page=addPI&programID=<%=request.getParameter("programID")%>">Add</a>
                    <button class="btn btn-success">Cancel</button>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>