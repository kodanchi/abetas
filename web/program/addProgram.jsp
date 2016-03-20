<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/1/2016
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>


<%

    String pname ="";
    String pmission = "";

    if(request.getSession().getAttribute("errMsg") != null){

        String[] programOldVal = (request.getSession().getAttribute("programVal") != null ? (String[]) request.getSession().getAttribute("programVal") : null);
        System.out.print("arry of user data : "+ programOldVal[1]);
        if(programOldVal != null){

            pname = programOldVal[0];
            pmission = programOldVal[1];
            request.getSession().removeAttribute("programVal");
        }


        out.print("<script type=\"text/javascript\">\n" +
                "    $(window).load(function(){\n" +
                "       bootbox.alert(\""+request.getSession().getAttribute("errMsg")+"\")\n" +
                "    });\n" +
                "</script>");
        request.getSession().removeAttribute("errMsg");


    }

%>

<!doctype html>
        <div class="container" id="space">

            <nav>
                <ol class="cd-breadcrumb triangle small">
                    <li class="current"><em>Program</em></li>
                    <li><em>Objectives</em></li>
                    <li><em>Outcomes</em></li>
                    <li><em>Link</em></li>
                    <li><em>Faculty Members</em></li>
                    <li><em>Students</em></li>
                </ol>
            </nav>

            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Add Program</h2>
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("ProgramName")!=null) {out.print("Update");} else out.print("Enter");%> the name and the mission of the program:</p>


                    <form name="myform" action="/Add Program" method="post">

                        <div class="form-group">

                            <label>Program Name</label>

                            <input type="text" class="form-control" placeholder="Program Name" name="Pname" value="<%if (request.getParameter("ProgramName")!=null) {out.print(request.getParameter("ProgramName"));}
                            else {out.print(pname);}%>" required>
                            <input type="hidden" name="ProgramName" value="<%=request.getParameter("ProgramName")%>">
                            <input type="hidden" name="ProgramMission" value="<%=request.getParameter("ProgramMission")%>">
                            <input type="hidden" name="id" value="<%=request.getParameter("id")%>">

                        </div>

                        <div class="form-group">

                            <label>Mission Statement</label>

                            <textarea class="form-control" rows="4" cols="50" placeholder="Mission Statement" name="Pmission" required><%if (request.getParameter("ProgramMission")!=null) {out.print(request.getParameter("ProgramMission"));}
                            else {out.print(pmission);}%></textarea>

                        </div>

                        <br>
                        <button class="btn btn-primary btn-fill" type="submit"><%if (request.getParameter("ProgramName")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn  btn-default pull-right" href="index.jsp">Cancel</a>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>
            <!-- End of container -->
        </div>
</html>