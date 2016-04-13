
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/jquery.bsFormAlerts.js" type="text/javascript"></script>

<%
    /**
     * used to display add program objectives page.
     */
%>


<!doctype html>
        <div class="container">
            <!--  Here is row -->
            <div class="row">
                <h2 class="text-center">Add Program Objective</h2>
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("ObjValue")!=null) {out.print("Update");} else out.print("Enter");%> the program objectives</p>



                    <form role="form" id="addObj" name="myform" action="/Add Program Objective" method="post">
                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-4">
                                    <label> <strong>Program: </strong><label><%=request.getParameter("name")%></label></label>
                                </div>

                            </div>
                        </div>

                        <div class="form-group">

                                               <label>Program Objectives:</label>

                                                    <textarea class="form-control" rows="5" id="objTxt" name="Obj"><%if (request.getParameter("ObjValue")!=null) {out.print(request.getParameter("ObjValue"));}%></textarea>
                            <span data-alertid="obj"></span>

                        </div>
                        <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                        <input type="hidden" name="name" value="<%=request.getParameter("name")%>">
                        <input type="hidden" name="Objid" value="<%=request.getParameter("Objid")%>">
                        <input type="hidden" name="ObjValue" value="<%=request.getParameter("ObjValue")%>">

                        <button class="btn btn-primary" type="submit"><%if (request.getParameter("ObjValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-default pull-right" href="index.jsp?page=ObjList&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Cancel</a>

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
        $('#addObj').submit(function(){

            var objTxt = document.getElementById("objTxt");


            $(document).trigger("clear-alert-id.obj");

            if(objTxt.value == "") {
                $(document).trigger("clear-alert-id.obj");
                $(document).trigger("set-alert-id-obj", [
                    {
                        message: "Enter the program objective",
                        priority: "error"
                    }
                ]);
                objTxt.focus();
                return false;
            }else if (/^\d+$/g.test(objTxt.value)) {
                $(document).trigger("clear-alert-id.obj");
                $(document).trigger("set-alert-id-obj", [
                    {
                        message: "program objective must have alphabetic letters",
                        priority: "error"
                    }
                ]);
                objTxt.focus();
                return false;
            }
        });
    });


</script>