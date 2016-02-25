<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/3/2016
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<html>
        <div class="container" id="space">
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Add Student Outcome</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("OutValue")!=null) {out.print("Update");} else out.print("Enter");%> the program outcomes</p>

                    <form name="myform" action="/Add Student Outcome" method="post">

                        <div class="form-group">

                            <!-- Large button group -->
                            <div class="btn-group">

                                <label>Program: <label><%=request.getParameter("name")%></label></label>
                                <input type="hidden" name="name" value="<%=request.getParameter("name")%>">
                            </div>
                        </div>


                        <div class="form-group">

                            <label>Student Outcome</label>
                            <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                            <input type="hidden" name="Outid" value="<%=request.getParameter("Outid")%>">
                            <input type="hidden" name="OutValue" value="<%=request.getParameter("OutValue")%>">
                            <textarea class="form-control" rows="4" cols="50" name="Out" placeholder="Student Outcome" required><%if (request.getParameter("OutValue")!=null) {out.print(request.getParameter("OutValue"));}%></textarea>

                        </div>

                        <br>
                        <button class="btn btn-success btn-fill" type="submit"><%if (request.getParameter("OutValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-success btn-primary" href="index.jsp?page=OutcomeList&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Cancel</a>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- End of container -->
        </div>
</html>
