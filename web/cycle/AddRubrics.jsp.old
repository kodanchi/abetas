<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/8/2016
  Time: 5:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<%
    String id = "";
    String Termid = "";
    if(request.getParameter("cycle") != null && request.getParameter("term") != null){
        id  = request.getParameter("cycle");
        Termid  = request.getParameter("term");
        out.println("Cycle id is : "+id);
        out.print("Termid is : "+Termid);
    }

%>

<div class="main">
    <div class="section">
        <div class="container">
            <!--         what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Add Rubrics</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">
                    <p>You need to enter the four rubrics and their details</p>

                    <form method="post" action="/AddRubrics">
                        <div class="form-group">
                            <label>First rubrics</label>
                            <input type="text" class="form-control" size="25" name="firstR" required>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <textarea class="form-control" rows="3" name="firstD" required></textarea>
                        </div>
                        <div class="form-group">
                            <label>Second rubrics</label>
                            <input type="text" class="form-control" size="25" name="secondR" required>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <textarea class="form-control" rows="3" name="secondD" required></textarea>
                        </div>

                        <div class="form-group">
                            <label>Third rubrics</label>
                            <input type="text" class="form-control" size="25" name="thirdR" required>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <textarea class="form-control" rows="3" name="thirdD" required></textarea>
                        </div>

                        <input name="cycle" value="<%=id%>" hidden/>
                        <input name="term" value="<%=Termid%>" hidden/>
                        <button class="btn btn-success" type="submit">Next</button>
                        <button class="btn btn-success">Cancel</button>
                    </form>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


        </div>
    </div>
</div>