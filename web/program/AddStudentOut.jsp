
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/jquery.bsFormAlerts.js" type="text/javascript"></script>


<%
    /**
     * used to display add student outcome page.
     */
%>

<html>
        <div class="container" id="space">
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Add Student Outcome</h2>
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("OutValue")!=null) {out.print("Update");} else out.print("Enter");%> the program outcomes</p>

                    <form id="addOut" name="addOut" action="/Add Student Outcome" method="post">

                        <div class="form-group">

                            <!-- Large button group -->
                            <div class="btn-group">

                                <label>Program: <strong><%=request.getParameter("name")%></strong></label>
                                <input type="hidden" name="name" value="<%=request.getParameter("name")%>">
                            </div>
                        </div>

                        <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                        <input type="hidden" name="Outid" value="<%=request.getParameter("Outid")%>">
                        <input type="hidden" name="OutValue" value="<%=request.getParameter("OutValue")%>">

                        <div class="form-group">

                            <label>Student Outcome</label>
                            <textarea class="form-control" rows="4"  id="out" name="Out" placeholder="Student Outcome" ><%if (request.getParameter("OutValue")!=null) {out.print(request.getParameter("OutValue"));}%></textarea>

                            <span data-alertid="out"></span>
                        </div>

                        <br>
                        <button class="btn btn-primary" type="submit"><%if (request.getParameter("OutValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-default pull-right" href="index.jsp?page=OutcomeList&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Cancel</a>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

            <!-- End of container -->
        </div>


</html>


<script>

    $(function(){
        $('#addOut').submit(function(){

            var out = document.getElementById("out");


            $(document).trigger("clear-alert-id.out");


            if(out.value == "") {
                $(document).trigger("clear-alert-id.out");
                $(document).trigger("set-alert-id-out", [
                    {
                        message: "Enter the student outcome",
                        priority: "error"
                    }
                ]);
                out.focus();
                return false;
            }else if (/^\d+$/g.test(out.value)) {
                $(document).trigger("clear-alert-id.out");
                $(document).trigger("set-alert-id-out", [
                    {
                        message: "student outcome must have alphabetic letters",
                        priority: "error"
                    }
                ]);
                out.focus();
                return false;
            }
        });
    });


</script>