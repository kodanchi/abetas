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
                    <p><%if (request.getParameter("CourseName")!=null) {out.print("Update");} else out.print("Enter");%> the name of the course, code and level of <%=request.getParameter("name")%> program:</p>

                    <form name="myform" action="/Add Courses" method="post">

                        <div class="form-group">

                            <label>Course Name: </label>

                            <input type="text" class="form-control" placeholder="Course Name" name="Cname" value="<%if (request.getParameter("CourseName")!=null) {out.print(request.getParameter("CourseName"));}%>" required>
                            <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                            <input type="hidden" name="name" value="<%=request.getParameter("name")%>">
                            <input type="hidden" name="Cid" value="<%=request.getParameter("Cid")%>">
                            <input type="hidden" name="Courseid" value="<%=request.getParameter("Courseid")%>">
                            <input type="hidden" name="CourseName" value="<%=request.getParameter("CourseName")%>">
                            <input type="hidden" name="CourseLevel" value="<%=request.getParameter("CourseLevel")%>">

                        </div>

                        <div class="form-group">

                            <label>Course Code: </label>

                            <input type="text" class="form-control" placeholder="Course Code" name="Code" value="<%if (request.getParameter("CourseName")!=null) {out.print(request.getParameter("Courseid"));}%>" required>

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
                                <%if (request.getParameter("CourseName")!=null) {out.print("<option value=\""+request.getParameter("CourseLevel")+"\""+" selected>Level "+request.getParameter("CourseLevel")+"</option>");}%>
                            </select>
                        </div>

                        <br>
                        <br>
                        <br>
                        <br>
                        <br>
                        <br>


                        <button class="btn btn-success btn-fill" type="submit"><%if (request.getParameter("CourseName")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-success btn-primary" href="index.jsp?page=CoursesList&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Cancel</a>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>