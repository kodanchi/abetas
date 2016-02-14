<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/1/2016
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<!doctype html>
<div class="main">
    <div class="section">
        <div class="container" id="space">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Add Program</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("ProgramName")!=null) {out.print("Update");} else out.print("Enter");%> the name and the mission of the program:</p>

                    <form name="myform" action="/Add Program" method="post">

                        <div class="form-group">

                            <label>Program Name</label>

                            <input type="text" class="form-control" placeholder="Program Name" name="Pname" value="<%if (request.getParameter("ProgramName")!=null) {out.print(request.getParameter("ProgramName"));}%>" required>
                            <input type="hidden" name="ProgramName" value="<%=request.getParameter("ProgramName")%>">
                            <input type="hidden" name="ProgramMission" value="<%=request.getParameter("ProgramMission")%>">
                            <input type="hidden" name="id" value="<%=request.getParameter("id")%>">

                        </div>

                        <div class="form-group">

                            <label>Mission Statement</label>

                            <textarea class="form-control" rows="4" cols="50" placeholder="Mission Statement" name="Pmission" required><%if (request.getParameter("ProgramName")!=null) {out.print(request.getParameter("ProgramMission"));}%></textarea>

                        </div>

                        <br>
                        <button class="btn btn-success btn-fill" type="submit"><%if (request.getParameter("ProgramName")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-success btn-primary" href="index.jsp">Cancel</a>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>
            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>
</html>