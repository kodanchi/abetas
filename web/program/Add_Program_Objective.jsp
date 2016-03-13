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
        <div class="container">
            <!--  Here is row -->
            <div class="row">
                <h2 class="text-center">Add Program Objective</h2>
                <div class="col-md-8 col-md-offset-2">
                    <p><%if (request.getParameter("ObjValue")!=null) {out.print("Update");} else out.print("Enter");%> the program objectives</p>

                    <form role="form" name="myform" action="/Add Program Objective" method="post">
                        <div class="form-group">
                            <div class="row tim-row">
                                <div class="col-md-4">
                                    <label>Program: <label><%=request.getParameter("name")%></label></label>
                                </div>

                            </div>
                        </div>

                        <div class="form-group">

                            <div class="row tim-row">
                                <div class="col-md-4">                    <label>Program Objectives:<label style="color:red;">*</label></label>
                                </div>
                                <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                                <input type="hidden" name="name" value="<%=request.getParameter("name")%>">
                                <input type="hidden" name="Objid" value="<%=request.getParameter("Objid")%>">
                                <input type="hidden" name="ObjValue" value="<%=request.getParameter("ObjValue")%>">
                                <div class="col-md-8">                    <textarea class="form-control" rows="5" id="comment" name="Obj"><%if (request.getParameter("ObjValue")!=null) {out.print(request.getParameter("ObjValue"));}%></textarea>
                                </div>
                            </div>

                        </div>

                        <button class="btn btn-success btn-fill" type="submit"><%if (request.getParameter("ObjValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-success btn-primary" href="index.jsp?page=ObjList&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Cancel</a>

                    </form>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- End of container -->
        </div>

</html>