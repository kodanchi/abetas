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
                    <p>Select an objective and student outcome to link them for <%=request.getParameter("name")%> program:</p>

                    <form name="myform" action="/Add Link Outcome and Objective" method="post">

                        <div class="form-group">

                            <label>Program Objective: </label>

                            <div class="btn-group">


                                <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                                <input type="hidden" name="name" value="<%=request.getParameter("name")%>">
                                <input type="hidden" name="Linkid" value="<%=request.getParameter("Linkid")%>">
                                <input type="hidden" name="ObjLinkValue" value="<%=request.getParameter("ObjLinkValue")%>">
                                <input type="hidden" name="OutLinkValue" value="<%=request.getParameter("OutLinkValue")%>">


                                <div class="col-xl-50 selectContainer" >
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



                                    <div class="col-xl-25 selectContainer" >
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
                                <button class="btn btn-success btn-fill" type="submit"><%if (request.getParameter("ObjLinkValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                                <a class="btn btn-success btn-primary" href="index.jsp?page=LinkOutObj&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Cancel</a>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>
