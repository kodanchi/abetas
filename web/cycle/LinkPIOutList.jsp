<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/11/2016
  Time: 1:36 PM
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

        <div class="container">
            <!--         what is row -->
            <div class="row">
                <h2 class="text-center">Link Performance Indicator with Student Outcomes</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">
                    <p>Click "Add" to enter a new performance indicator and students outcome link for <% if(request.getParameter("programID")!=null) {
                        C_AS_Select ssselect = new C_AS_Select();
                        try {
                            String rs = ssselect.selectProgramName(Integer.parseInt(request.getParameter("programID")));
                            out.print(rs);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    %> program</p>

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table table-hover table-striped table-bordered text-center">
                            <tr>
                                <th>Students Outcome</th>
                                <th>Performance Indicator</th>
                                <th>Course</th>
                                <th>Type</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            <%

                                C_AS_Select aselect = new C_AS_Select();

                                try {
                                    ArrayList<ArrayList<String>> rs = aselect.selectPILinks(Integer.parseInt(Termid),Integer.parseInt(request.getParameter("programID")));
                                    ArrayList<String> rsRow ;

                                    for (int i=0; i<rs.size();i++){
                                        rsRow = new ArrayList<String>();
                                        rsRow = rs.get(i);
                                        out.print("<tr>");
                                        for (int j=1; j<rsRow.size();j++) {
                                            if(j==1||j==2||j==3||j==4){
                                            out.print("<td>");
                                            out.print(rsRow.get(j));
                                            out.print("</td>");
                                            }
                                        }
                                        out.print("<td>" +
                                                "                            <form method=\"post\" action=\"index.jsp\">\n" +
                                                "                            <input name=\"page\" value=\"updatePILink\" hidden />\n" +
                                                "                            <input name=\"cycle\" value=\""+id+"\" hidden />\n" +
                                                "                            <input name=\"term\" value=\""+Termid+"\" hidden />\n" +
                                                "                            <input name=\"LinkID\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"OutValue\" value=\""+rsRow.get(1)+"\" hidden />\n" +
                                                "                            <input name=\"PIValue\" value=\""+rsRow.get(2)+"\" hidden />\n" +
                                                "                            <input name=\"CourseValue\" value=\""+rsRow.get(3)+"\" hidden />\n" +
                                                "                            <input name=\"TypeValue\" value=\""+rsRow.get(4)+"\" hidden />\n" +
                                                "                            <input name=\"PValue\" value=\""+rsRow.get(6)+"\" hidden />\n" +
                                                "                            <input name=\"RubricValue\" value=\""+rsRow.get(5)+"\" hidden />\n" +
                                                "                            <input name=\"TermValue\" value=\""+rsRow.get(7)+"\" hidden />\n" +
                                                "                            <input name=\"programID\" value=\""+request.getParameter("programID")+"\" hidden />\n" +
                                                "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                "                               </td>" +
                                                "                            </form>" +
                                                "                            <form method=\"post\" action=\"/DeletePILinks\">\n" +
                                                "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                "                            <input name=\"cycle\" value=\""+id+"\" hidden />\n" +
                                                "                            <input name=\"term\" value=\""+Termid+"\" hidden />\n" +
                                                "                            <input name=\"LinkID\" value=\""+rsRow.get(0)+"\" hidden />\n" +
                                                "                            <input name=\"RubricValue\" value=\""+rsRow.get(5)+"\" hidden />\n" +
                                                "                            <input name=\"programID\" value=\""+request.getParameter("programID")+"\" hidden />\n" +
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

                    <a class="btn btn-success btn-fill pull-left" href="index.jsp?page=addPILinks&cycle=<%=id%>&term=<%=Termid%>&programID=<%=request.getParameter("programID")%>">Add</a>
                    <a class="btn btn-success btn-fill pull-right" href="index.jsp?page=addTerm&cycle=<%=id%>&term=<%=Termid%>">Finish</a>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>
        </div>
