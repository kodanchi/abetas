
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/jquery.bsFormAlerts.js" type="text/javascript"></script>

<%
    /**
     * used to display add course page.
     */

    String cCode = "";
    String cName = "";
    String cLvl = "0";

    if(request.getSession().getAttribute("errMsg") != null){

        String[] courseOldVal = (request.getSession().getAttribute("courseVal") != null ? (String[]) request.getSession().getAttribute("courseVal") : null);
        if(courseOldVal != null){

            cCode = courseOldVal[0];
            cName = courseOldVal[1];
            cLvl = courseOldVal[2];
            request.getSession().removeAttribute("courseVal");
        }

        out.print("<script type=\"text/javascript\">\n" +
                "    $(window).load(function(){\n" +
                "       bootbox.alert(\""+request.getSession().getAttribute("errMsg")+"\")\n" +
                "    });\n" +
                "</script>");
        request.getSession().removeAttribute("errMsg");


    }

%>

        <div class="container" id="space">
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Add Course</h2>
                <div class="col-md-8 col-md-offset-2">
                    <p><%if (request.getParameter("CourseName")!=null) {out.print("Update");} else out.print("Enter");%> the name of the course, code and level of <%=request.getParameter("name")%> program:</p>

                    <form id="addC" name="addC" action="/Add Courses" method="post">

                        <div class="form-group">

                            <label>Course Name: </label>

                            <input type="text" class="form-control" placeholder="Course Name" id="cname" name="Cname" value="<%
                            if (request.getParameter("CourseName")!=null)
                            {
                                out.print(request.getParameter("CourseName"));
                            }else {
                                out.print(cName);
                            }
                            %>" >
                            <span data-alertid="cname"></span>

                        </div>

                        <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                        <input type="hidden" name="name" value="<%=request.getParameter("name")%>">
                        <input type="hidden" name="Cid" value="<%=request.getParameter("Cid")%>">
                        <input type="hidden" name="Courseid" value="<%=request.getParameter("Courseid")%>">
                        <input type="hidden" name="CourseName" value="<%=request.getParameter("CourseName")%>">
                        <input type="hidden" name="CourseLevel" value="<%=request.getParameter("CourseLevel")%>">

                        <div class="form-group">

                            <label>Course Code: </label>

                            <input type="text" class="form-control" placeholder="Course Code" id="code" name="Code" value="<%
                            if (request.getParameter("CourseName")!=null) {
                                out.print(request.getParameter("Courseid"));
                            }else {
                                out.print(cCode);
                            }%>">
                            <span data-alertid="code"></span>
                        </div>

                        <div class="row form-group">
                        <div class="col-md-5" style="padding-left: -10px">
                            <label>Course Level: </label>
                            <select class="form-control" id="level" name="level">

                                <%
                                    if(request.getParameter("CourseLevel")!=null){
                                        cLvl = request.getParameter("CourseLevel");
                                    }

                                    for (int i=1;i<11;i++){
                                        out.print("<option value=\""+i);
                                        out.print("\"");



                                        if ( i == Integer.parseInt(cLvl)) {
                                            out.print(" selected");
                                        }
                                        out.print(">");
                                        out.print("Level "+i+"</option>");

                                    }

                                %>
                            </select>
                            <span data-alertid="level"></span>
                        </div>
                        </div>

                        <button class="btn btn-primary " type="submit"><%if (request.getParameter("CourseName")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-default pull-right" href="index.jsp?page=CoursesList&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Cancel</a>

                    </form>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- End of container -->
        </div>

<script>

    $(function(){
        $('#addC').submit(function(){

            var cname = document.getElementById("cname");
            var code = document.getElementById("code");
            var level = document.getElementById("level");


            $(document).trigger("clear-alert-id.cname");
            $(document).trigger("clear-alert-id.code");
            $(document).trigger("clear-alert-id.level");

            if(cname.value == "") {
                $(document).trigger("clear-alert-id.cname");
                $(document).trigger("set-alert-id-cname", [
                    {
                        message: "Enter the course name",
                        priority: "error"
                    }
                ]);
                cname.focus();
                return false;
            }else if (/^\d+$/g.test(cname.value)) {
                $(document).trigger("clear-alert-id.cname");
                $(document).trigger("set-alert-id-cname", [
                    {
                        message: "name must have alphabetic letters",
                        priority: "error"
                    }
                ]);
                cname.focus();
                return false;
            }else if (cname.value.length > 64) {
                $(document).trigger("clear-alert-id.cname");
                $(document).trigger("set-alert-id-cname", [
                    {
                        message: "name must be less than 64 characters",
                        priority: "error"
                    }
                ]);
                cname.focus();
                return false;
            }else if(code.value == "") {
                $(document).trigger("clear-alert-id.code");
                $(document).trigger("set-alert-id-code", [
                    {
                        message: "Enter the course code",
                        priority: "error"
                    }
                ]);
                code.focus();
                return false;
            }else if (/^\d+$/g.test(code.value)) {
                $(document).trigger("clear-alert-id.code");
                $(document).trigger("set-alert-id-code", [
                    {
                        message: "course code must have alphabetic letters",
                        priority: "error"
                    }
                ]);
                code.focus();
                return false;
            }else if (code.value.length > 11) {
                $(document).trigger("clear-alert-id.code");
                $(document).trigger("set-alert-id-code", [
                    {
                        message: "code must be less than 11 characters",
                        priority: "error"
                    }
                ]);
                code.focus();
                return false;
            }
        });
    });


</script>