<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 2/2/2016
  Time: 7:51 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>




<div class="section">
    <div class="container">
        <!--         what is row -->
        <div class="row tim-row">
            <h2 class="text-center">Cycle Configuration</h2>
            <legend></legend>
            <div class="col-md-8 col-md-offset-2">
                <p>You need to enter the cycle default threashold</p>

                <%
                    String id = "";
                    if(request.getSession().getAttribute("id") != null){
                        id  = (String) request.getSession().getAttribute("id");
                        out.print("id is : "+id);
                    }

                %>

                <form>
                    <div class="form-group"  style="margin-bottom:10px;">
                        <input type="number" min="50" max="100" class="form-control" size="25" required>
                    </div>
                </form>

                <button class="btn btn-success">Cancel</button>
                <button class="btn btn-success">Next</button>


                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>

    </div>
</div>


