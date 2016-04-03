<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/1/2016
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/jquery.bsFormAlerts.js" type="text/javascript"></script>


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

        <div class="container" id="space">
            <div class="row">
                <div class="col-md-7 col-md-offset-2">
                    <nav>
                        <ol class="cd-breadcrumb triangle small">
                            <li class="current"><em>Program</em></li>
                            <li><em>Objectives</em></li>
                            <li><em>Outcomes</em></li>
                            <li><em>Link</em></li>
                            <li><em>Courses</em></li>
                        </ol>
                    </nav>
                </div>
            </div>



            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Add Program</h2>
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("ProgramName")!=null) {out.print("Update");} else out.print("Enter");%> the name and the mission of the program:</p>


                    <form name="myform" id="padd" action="/Add Program" method="post">

                        <div class="form-group">

                            <label>Program Name</label>

                            <input type="text" class="form-control" placeholder="Program Name" id="pname" name="Pname" value="<%if (request.getParameter("ProgramName")!=null) {out.print(request.getParameter("ProgramName"));}
                            else {out.print(pname);}%>">
                            <span data-alertid="pname"></span>

                        </div>
                        <input type="hidden" name="ProgramName" value="<%=request.getParameter("ProgramName")%>">
                        <input type="hidden" name="ProgramMission" value="<%=request.getParameter("ProgramMission")%>">
                        <input type="hidden" name="id" value="<%=request.getParameter("id")%>">


                        <div class="form-group">

                            <label>Mission Statement</label>

                            <textarea class="form-control" rows="4"  placeholder="Mission Statement" id="pmiss" name="Pmission" ><%if (request.getParameter("ProgramMission")!=null) {out.print(request.getParameter("ProgramMission"));}
                            else {out.print(pmission);}%></textarea>
                            <span data-alertid="pmiss"></span>

                        </div>

                        <br>
                        <a class="btn  btn-default pull-right" href="index.jsp">Cancel</a>
                        <button class="btn btn-primary btn-fill pull-right" type="submit"><%if (request.getParameter("ProgramName")!=null) {out.print("Next");} else out.print("Next");%></button>


                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>
            <!-- End of container -->
        </div>
<script>

    $(function(){
        $('#padd').submit(function(){

            var panme = document.getElementById("pname");
            var pmiss = document.getElementById("pmiss");


            $(document).trigger("clear-alert-id.pname");
            $(document).trigger("clear-alert-id.pmiss");

            if(panme.value == "") {
                $(document).trigger("clear-alert-id.pname");
                $(document).trigger("set-alert-id-pname", [
                    {
                        message: "Enter the program name",
                        priority: "error"
                    }
                ]);
                pname.focus();
                return false;
            }else if (!/^[A-Za-z\s]+$/g.test(pname.value)) {
                $(document).trigger("clear-alert-id.pname");
                $(document).trigger("set-alert-id-pname", [
                    {
                        message: "name must have only alphabetic letters",
                        priority: "error"
                    }
                ]);
                pname.focus();
                return false;
            }else if(pmiss.value == "") {
                $(document).trigger("clear-alert-id.pmiss");
                $(document).trigger("set-alert-id-pmiss", [
                    {
                        message: "Enter the mission statement",
                        priority: "error"
                    }
                ]);
                pmiss.focus();
                return false;
            }else if (/^\d+$/g.test(pmiss.value)) {
                $(document).trigger("clear-alert-id.pmiss");
                $(document).trigger("set-alert-id-pmiss", [
                    {
                        message: "mission statement must have alphabetic letters",
                        priority: "error"
                    }
                ]);
                pmiss.focus();
                return false;
            }
        });
    });


</script>