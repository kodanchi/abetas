<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/13/2016
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<%

    String id = "";
    String Termid = "";
    if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
        id  = (String) request.getSession().getAttribute("id");
        Termid  = (String) request.getSession().getAttribute("Termid");
        out.println("id is : "+id);
        out.print("Termid is : "+Termid);
    }

%>

<div class="main">
    <div class="section">
        <div class="container">
            <!--         what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Select Program/Courses</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">
                    <div class="col-md-8">
                        <div class="dropdown">
                            <a href="#" class="btn dropdown-toggle" data-toggle="dropdown">
                                <% if(request.getParameter("programID")!=null) {
                                    AS_Select sselect = new AS_Select();
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
                                %>

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
                                            out.print("<li><a href=\"/cycle/index.jsp?page=includeCourse&programID="+rsRow.get(0)+"\">"+rsRow.get(1)+"</a></li>");

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


                    <form name="linkForm" method="POST" action="/includeCourse">

                        <input type="hidden" name="programID" value="<% if(request.getParameter("programID")!=null) {out.print(request.getParameter("programID"));}%>">


                        <%
                            if(request.getParameter("programID")!=null) {
                                AS_Select bselect = new AS_Select();
                                try {
                                    ArrayList<String> rs = bselect.selectCourseForLink(Integer.parseInt(request.getParameter("programID")));

                                    if (rs.size()!=0) {


                                        out.print("<p>Select courses to be evaluated for");
                                        if(request.getParameter("programID")!=null) {
                                            AS_Select ssselect = new AS_Select();
                                            try {
                                                String Pname = ssselect.selectProgramName(Integer.parseInt(request.getParameter("programID")));
                                                out.print(Pname);
                                            } catch (ClassNotFoundException e) {
                                                e.printStackTrace();
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        out.print("program</p>");

                                        for (int i = 0; i < rs.size(); i++) {
                                            System.out.println(rs.get(i).substring(0, rs.get(i).indexOf(':')));

                                            out.print("<label class=\"checkbox-inline\">");
                                            out.print("<input type=\"checkbox\" name=\"Code\" value=" + rs.get(i).substring(0, rs.get(i).indexOf(':')) + ">" + rs.get(i));
                                            out.print("</label>");

                                        }

                                        out.print("<br><br><br><button class=\"btn btn-success btn-fill\" type=\"submit\"> Submit </button><br><br><br>");
                                    }else {
                                        out.print("There are no courses in this program<br><br>");
                                        //Display error massage
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


                    </form>


                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table">
                            <tr>

                                <th>Code</th>
                                <th>Name</th>
                                <th>Level</th>
                                <th>Course Info.</th>
                                <th>Delete</th>

                            </tr>
                            <%

                                if(Termid!=null && request.getParameter("programID")!=null) {

                                    AS_Select aselect = new AS_Select();
                                    try {
                                        ArrayList<ArrayList<String>> rs = aselect.selectCourseForTerm(Integer.parseInt(request.getParameter("programID")),Integer.parseInt(Termid));
                                        ArrayList<String> rsRow;

                                        for (int i = 0; i < rs.size(); i++) {
                                            rsRow = new ArrayList<String>();
                                            rsRow = rs.get(i);
                                            out.print("<tr>");
                                            for (int j = 2; j < rsRow.size(); j++) {
                                                out.print("<td>" + rsRow.get(j) + "</td>");
                                            }
                                            out.print("<td>" +
                                                    "                            <form method=\"post\" action=\"index.jsp\">\n" +
                                                    "                            <input name=\"page\" value=\"CourseInfo\" hidden />\n" +
                                                    "                            <input name=\"CourseValue\" value=\""+rsRow.get(2)+"\" hidden />\n" +
                                                    "                            <input name=\"programID\" value=\""+request.getParameter("programID")+"\" hidden />\n" +
                                                    "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-warning btn-simple\"><i class=\"fa fa-pencil fa-2x \"></i></button>\n" +
                                                    "                            </form>" +
                                                    "                            </td>" +
                                                    "                            <form method=\"post\" action=\"/DeleteIC\">\n" +
                                                    "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                    "                            <input name=\"CodeValue\" value=\"" + rsRow.get(2) + "\" hidden />\n" +
                                                    "                            <input name=\"programID\" value=\""+request.getParameter("programID")+"\" hidden />\n" +
                                                    "                            <td>" +
                                                    "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-danger btn-simple\"><i class=\"fa fa-trash-o fa-2x \"></i></button>\n" +
                                                    "                            </td>" +
                                                    "                        </form>" +
                                                    "</tr>");
                                        }

                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("  yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy           ");
                                }else {
                                    System.out.println("  gsgsgsg    gsgsggssdfgs       djskvdsj    sgsgs   sgsgsgsg   fsdsdg            ");
                                }

                            %>

                        </table>


                    </div>
                    <a class="btn btn-success btn-fill" href="index.jsp?page=piList&programID=<% if(request.getParameter("programID")!=null) {out.print(request.getParameter("programID"));}%>">Next</a>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>