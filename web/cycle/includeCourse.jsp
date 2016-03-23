<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/13/2016
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>
<script src="/js/bootstrap-select.min.js" type="text/javascript"></script>

<%

    String id = "";
    String Termid = "";
    if(request.getParameter("cycle") != null && request.getParameter("term") != null){
        id  = request.getParameter("cycle");
        Termid  = request.getParameter("term");
    }

%>

        <div class="container">

            <div class="row">
                <div class="col-md-7 col-md-offset-2">
                    <nav>
                        <ol class="cd-breadcrumb triangle small">
                            <li class="current" ><em>Courses</em></li>
                            <li><em>Link PI/Outcomes</em></li>
                        </ol>
                    </nav>
                </div>
            </div>

            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Select Program/Courses</h2>

                <div class="col-md-8 col-md-offset-2">
                    <div class="col-md-8">
                        <div class="dropdown">
                            <a href="#" class="btn btn-primary  dropdown-toggle" data-toggle="dropdown">
                                <% if(request.getParameter("programID")!=null) {
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
                                %>

                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">

                                <%
                                    C_AS_Select select = new C_AS_Select();
                                    ArrayList<Integer> pid = new ArrayList<Integer>();
                                    try {
                                        ArrayList<ArrayList<String>> rs = select.selectAllPrograms();
                                        ArrayList<String> rsRow;


                                        for (int i=0; i<rs.size();i++) {
                                            rsRow = rs.get(i);

                                            //pid.add(Integer.valueOf(rsRow.get(0)));
                                            //System.out.println("pid : "+pid.get(i));
                                            //out.print("<option value="+rsRow.get(0)+">"+rsRow.get(1)+"</option>");
                                            out.print("<li><a href=\"/cycle/index.jsp?page=includeCourse&cycle="+id+
                                                    "&term="+Termid+"&programID="+rsRow.get(0)+"\">"+rsRow.get(1)+"</a></li>");

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
                        <input name="cycle" value="<%=id%>" hidden/>
                        <input name="term" value="<%=Termid%>" hidden/>

                        <input type="hidden" name="programID" value="<% if(request.getParameter("programID")!=null) {out.print(request.getParameter("programID"));}%>">


                        <%
                            if(request.getParameter("programID")!=null) {
                                C_AS_Select bselect = new C_AS_Select();
                                try {
                                    ArrayList<String> rs = bselect.selectCourseForProgram(Integer.parseInt(request.getParameter("programID")), Integer.parseInt(Termid));

                                    if (rs.size()!=0) {


                                        out.print("<p>Select courses to be evaluated for ");
                                        if(request.getParameter("programID")!=null) {
                                            C_AS_Select ssselect = new C_AS_Select();
                                            try {
                                                String Pname = ssselect.selectProgramName(Integer.parseInt(request.getParameter("programID")));
                                                out.print(Pname);
                                            } catch (ClassNotFoundException e) {
                                                e.printStackTrace();
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        out.print(" program</p>");
                                        out.print("<select name=\"Code\"  class=\"selectpicker\" multiple>");

                                        for (int i = 0; i < rs.size(); i++) {
                                            System.out.println(rs.get(i).substring(0, rs.get(i).indexOf(':')));


                                           /* out.print("<label class=\"checkbox-inline\">");
                                            out.print("<input type=\"checkbox\" name=\"Code\" value=\"" + rs.get(i).substring(0, rs.get(i).indexOf(':')) + "\" >" + rs.get(i));
                                            out.print("</label>");
                                            */
                                            out.print("<option");
                                            out.print(" value=\"" + rs.get(i).substring(0, rs.get(i).indexOf(':')) + "\" >" + rs.get(i));
                                            out.print("</option>");

                                        }
                                        out.print("</select>");

                                        out.print("<button class=\"btn btn-primary\" type=\"submit\"> Include course/s </button><br><br><br>");
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

                    <div class="input-group"> <span class="input-group-addon">Filter</span>

                        <input id="filter" type="text" class="form-control" placeholder=" by course code, name or level ">
                    </div>
                        <!-- Table -->
                        <table class="table table-hover table-striped table-bordered text-center">
                            <tr>

                                <th>Code</th>
                                <th>Name</th>
                                <th>Level</th>
                                <th>Course Info.</th>
                                <th>Delete</th>

                            </tr>
                            <tbody class="searchable">
                                <%

                                    if(Termid!=null && request.getParameter("programID")!=null) {

                                        C_AS_Select aselect = new C_AS_Select();
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
                                                        "                            <input name=\"cycle\" value=\""+id+"\" hidden />\n" +
                                                        "                            <input name=\"term\" value=\""+Termid+"\" hidden />\n" +
                                                        "                            <input name=\"courseCode\" value=\""+rsRow.get(2)+"\" hidden />\n" +
                                                        "                            <input name=\"programID\" value=\""+request.getParameter("programID")+"\" hidden />\n" +
                                                        "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                                                        "                            </form>" +
                                                        "                            </td><td>" +
                                                        "                            <form method=\"post\" class=\"delForm\" action=\"/DeleteIC\">\n" +
                                                        "                            <input name=\"cycle\" value=\""+id+"\" hidden />\n" +
                                                        "                            <input name=\"term\" value=\""+Termid+"\" hidden />\n" +
                                                        "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                                                        "                            <input name=\"Code\" value=\"" + rsRow.get(2) + "\" hidden />\n" +
                                                        "                            <input name=\"programID\" value=\""+request.getParameter("programID")+"\" hidden />\n" +
                                                        "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-link btn-T \"><i class=\"fui-trash icon30\"></i></button>\n" +
                                                        "                        </form></td>" +
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
                            </tbody>

                        </table>

                    <a class="btn btn-default pull-right" href="index.jsp?page=addTerm&cycle=<%=id%>">Cancel</a>
                    <%
                        if(request.getParameter("programID")!=null){
                           out.print("<a class=\"btn btn-primary pull-right\" href=\"index.jsp?page=LinkPIOutList&cycle="+id+
                    "&term="+Termid+"&programID="+request.getParameter("programID")+"\">Next</a>\n");
                        }
                    %>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

        </div>
