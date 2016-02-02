<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/2/2016
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<!doctype html>
<div class="main">
    <div class="section">
        <div class="container">
            <!--         what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Add Program Objective</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">
                    <p>Enter the program objectives</p>

                    <form role="form" name="myform" action="http://localhost:8081/program/index.jsp?page=add" method="post">
                        <div class="form-group">
                            <div class="row tim-row">
                                <div class="col-md-4">
                                    <label>Program: <label><%=request.getParameter("id")%></label></label>

                                </div>

                            </div>
                        </div>

                        <div class="form-group">

                            <div class="row tim-row">
                                <div class="col-md-4">                    <label>Program Objectives:<label style="color:red;">*</label></label>
                                </div>
                                <div class="col-md-8">                    <textarea class="form-control" rows="5" id="comment"></textarea>
                                </div>
                            </div>

                        </div>

                        <button class="btn btn-success btn-fill" type="submit">Add</button>
                        <button class="btn btn-primary">Cancel</button>
                    </form>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>

<!--   end modal  -->
</html>