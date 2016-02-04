<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/3/2016
  Time: 7:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<div class="main">
    <div class="section">
        <div class="container" id="space">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Link Program Objective and Student Outcome</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p>You need to select an objective and student outcome to link them for <%=request.getParameter("name")%> program:</p>

                    <form name="myform" action="/Add Link Outcome and Objective" method="post">

                        <div class="form-group">

                            <label>Program Objective: </label>

                            <div class="btn-group">

                                <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Objectives <span class="caret"></span>
                                    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                                    <input type="hidden" name="name" value="<%=request.getParameter("name")%>">

                                </button>

                                <div class="col-xs-5 selectContainer" >
                                    <select class="form-control" name="Obj">
                                <%
                                    AS_Select aselect = new AS_Select();
                                    try {
                                        ArrayList<String> rs = aselect.selectObjForLink(Integer.parseInt(request.getParameter("id")));

                                        for (int i=0; i<rs.size();i++) {
                                            out.print("<option value="+rs.get(i).substring(0, rs.get(i).indexOf(':'))+">"+rs.get(i)+"</option>");
                                        }
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                %>
                                    </select>
                                </div>
                        </div>


                        <div class="form-group">

                            <label>Student Outcome: </label>

                            <div class="btn-group">

                                <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Outcomes <span class="caret"></span>

                                </button>

                                <div class="col-xs-5 selectContainer" >
                                    <select class="form-control" name="Out">
                                    <%
                                        AS_Select bselect = new AS_Select();
                                        try {
                                            ArrayList<String> rs = bselect.selectOutForLink(Integer.parseInt(request.getParameter("id")));

                                            for (int i=0; i<rs.size();i++) {
                                                out.print("<option value="+rs.get(i).substring(0, rs.get(i).indexOf(':'))+">"+rs.get(i)+"</option>");
                                            }
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    %>
                                    </select>
                                </div>
                        </div>

                        <br>
                        <button type="submit" class="btn btn-success btn-fill">Add</button>
                        <button type="button" class="btn btn-primary">Cancel</button>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>
