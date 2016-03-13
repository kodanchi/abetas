<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/4/2016
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<%

    String cCode = "";
    String cName = "";
    String cLvl = "0";

    if(request.getSession().getAttribute("errMsg") != null){

        String[] courseOldVal = (request.getSession().getAttribute("courseVal") != null ? (String[]) request.getSession().getAttribute("courseVal") : null);
        System.out.print("arry of user data : "+ courseOldVal[1]);
        if(courseOldVal != null){

            cCode = courseOldVal[0];
            cName = courseOldVal[1];
            cLvl = courseOldVal[2];
            request.getSession().removeAttribute("courseVal");
        }

                        /*out.print("<div id=\"alert\"  class=\"alert alert-danger fade in\"  role=\"alert\" >\n" +
                                "                        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                                "                            <span aria-hidden=\"true\">&times;</span>\n" +
                                "                        </button>\n" +
                                "                        <strong id=\"alertt\" >\n" +
                                "                            " + request.getParameter("err")+
                                "                        </strong>\n" +
                                "                    </div>");*/

        out.print("<script type=\"text/javascript\">\n" +
                "    $(window).load(function(){\n" +
                "        $('#myModal').modal('show');\n" +
                "    });\n" +
                "</script>" +
                "<!-- Modal -->\n" +
                "                    <div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
                "                        <div class=\"modal-dialog\">\n" +
                "                            <div class=\"modal-content\">\n" +
                "                                <div class=\"modal-header\">\n" +
                "                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n" +
                "                                    <h4 class=\"modal-title\" id=\"myModalLabel\">INFO</h4>\n" +
                "                                </div>\n" +
                "                                <div class=\"modal-body\">\n");
        out.print(request.getSession().getAttribute("errMsg"));
        request.getSession().removeAttribute("errMsg");

        out.print("                                </div>\n" +
                "                                <div class=\"modal-footer\">\n" +
                "\n" +
                "                                    <div class=\"text-center\">\n" +
                "                                        <a type=\"button\"  data-dismiss=\"modal\"  class=\"btn btn-default btn-simple\">OK</a>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>");


    }

%>

        <div class="container" id="space">
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Add Course</h2>
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("CourseName")!=null) {out.print("Update");} else out.print("Enter");%> the name of the course, code and level of <%=request.getParameter("name")%> program:</p>

                    <form name="myform" action="/Add Courses" method="post">

                        <div class="form-group">

                            <label>Course Name: </label>

                            <input type="text" class="form-control" placeholder="Course Name" name="Cname" value="<%if (request.getParameter("CourseName")!=null)
                            {out.print(request.getParameter("CourseName"));
                            }else {
                            out.print(cName);
                            }%>" required>
                            <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                            <input type="hidden" name="name" value="<%=request.getParameter("name")%>">
                            <input type="hidden" name="Cid" value="<%=request.getParameter("Cid")%>">
                            <input type="hidden" name="Courseid" value="<%=request.getParameter("Courseid")%>">
                            <input type="hidden" name="CourseName" value="<%=request.getParameter("CourseName")%>">
                            <input type="hidden" name="CourseLevel" value="<%=request.getParameter("CourseLevel")%>">

                        </div>

                        <div class="form-group">

                            <label>Course Code: </label>

                            <input type="text" class="form-control" placeholder="Course Code" name="Code" value="<%
                            if (request.getParameter("CourseName")!=null) {
                                out.print(request.getParameter("Courseid"));
                            }else {
                                out.print(cCode);
                            }%>" required>

                        </div>

                        <div class="col-xs-5 selectContainer">
                            <label>Course Level: </label>
                            <select class="form-control" name="level">

                                <%
                                    if(request.getParameter("CourseLevel")!=null){
                                        cLvl = request.getParameter("CourseLevel");
                                    }

                                    for (int i=1;i<11;i++){
                                        out.print("<option value=\""+i);
                                        out.print("\"");



                                        if ( i == Integer.parseInt(cLvl)) {
                                            System.out.println("the reqValue is :"+cLvl +" and level is :"+i);
                                            out.print(" selected");
                                        }
                                        out.print(">");
                                        out.print("Level "+i+"</option>");
                                            /*out.print("<option value=\""+request.getParameter("CourseLevel")+"\""+" selected>Level "+
                                                    request.getParameter("CourseLevel")+"</option>");*/
                                    }
                                    /*if (request.getParameter("CourseName")!=null) {


                                    }else {
                                        out.print("<option value=\"1\">Level 1</option>\n" +
                                                "                                <option value=\"2\">Level 2</option>\n" +
                                                "                                <option value=\"3\">Level 3</option>\n" +
                                                "                                <option value=\"4\">Level 4</option>\n" +
                                                "                                <option value=\"5\">Level 5</option>\n" +
                                                "                                <option value=\"6\">Level 6</option>\n" +
                                                "                                <option value=\"7\">Level 7</option>\n" +
                                                "                                <option value=\"8\">Level 8</option>\n" +
                                                "                                <option value=\"9\">Level 9</option>\n" +
                                                "                                <option value=\"10\">Level 10</option>");
                                    }*/
                                %>
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


            <!-- End of container -->
        </div>