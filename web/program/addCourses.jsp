<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/4/2016
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<div class="main">
    <div class="section">
        <div class="container" id="space">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Add Course</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p>You need to enter the name of the course, code, level and type:</p>

                    <form name="myform" action="/Add Courses" method="post">

                        <div class="form-group">

                            <label>Course Name: </label>

                            <input type="text" class="form-control" placeholder="Course Name" name="Cname" required>
                            <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                            <input type="hidden" name="name" value="<%=request.getParameter("name")%>">

                        </div>

                        <div class="form-group">

                            <label>Course Code: </label>

                            <input type="text" class="form-control" placeholder="Course Code" name="Code" required>

                        </div>

                        <div class="col-xs-5 selectContainer">
                            <label>Course Level: </label>
                            <select class="form-control" name="level">
                                <option value="1">Level 1</option>
                                <option value="2">Level 2</option>
                                <option value="3">Level 3</option>
                                <option value="4">Level 4</option>
                                <option value="5">Level 5</option>
                                <option value="6">Level 6</option>
                                <option value="7">Level 7</option>
                                <option value="8">Level 8</option>
                                <option value="9">Level 9</option>
                                <option value="10">Level 10</option>
                            </select>
                        </div>

                        <br>
                        <br>
                        <br>
                        <br>
                        <br>
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