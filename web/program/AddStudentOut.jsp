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
<div class="main">
    <div class="section">
        <div class="container" id="space">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Add Student Outcome</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p>You need to select the program and enter a student outcome:</p>

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
                            <textarea class="form-control" rows="4" cols="50" name="Out" placeholder="Student Outcome" required></textarea>

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
</html>
