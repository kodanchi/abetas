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
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("ObjValue")!=null) {out.print("Update");} else out.print("Enter");%> the program objectives</p>

                    <form role="form" name="myform" action="/Add Program Objective" method="post">
                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-4">
                                    <label>Program: <label><%=request.getParameter("name")%></label></label>
                                </div>

                            </div>
                        </div>

                        <div class="form-group">

                                               <label>Program Objectives:</label>

                                <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                                <input type="hidden" name="name" value="<%=request.getParameter("name")%>">
                                <input type="hidden" name="Objid" value="<%=request.getParameter("Objid")%>">
                                <input type="hidden" name="ObjValue" value="<%=request.getParameter("ObjValue")%>">
                                                    <textarea class="form-control" rows="5" id="comment" name="Obj"><%if (request.getParameter("ObjValue")!=null) {out.print(request.getParameter("ObjValue"));}%></textarea>
                               

                        </div>

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