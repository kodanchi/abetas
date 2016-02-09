<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/3/2016
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<%

    String id = "";
    String Termid = "";
    if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
        id  = (String) request.getSession().getAttribute("id");
        Termid  = (String) request.getSession().getAttribute("Termid");
        out.println("id is : "+id);
        out.print("Termid is : "+Termid);
    }

%>

<%
    AS_Select aselect = new AS_Select();
    String programName = "";
    try {
        programName = aselect.selectProgramName(55);

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>

<div class="main">
    <div class="section">
        <div class="container" id="space">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Add Performance Indicator</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("PIValue")!=null) {out.print("Update");} else out.print("Enter");%> performance indicator for the <%out.print(programName);%> program</p>

                    <form name="myform" action="/AddPI" method="post">

                        <div class="form-group">

                            <!-- Large button group -->
                            <div class="btn-group">

                                <label>Program: <label><%out.print(programName);%> </label></label>
                                <input type="hidden" name="programName" value="<%out.print(programName);%> ">
                            </div>
                        </div>


                        <div class="form-group">

                            <label>Performance Indicator</label>
                            <input type="hidden" name="PIValue" value="<%=request.getParameter("PIValue")%>">
                            <textarea class="form-control" rows="4" cols="50" name="PI" placeholder="Performance Indicator" required><%if (request.getParameter("PIValue")!=null) {out.print(request.getParameter("PIValue"));}%></textarea>

                        </div>

                        <br>
                        <button class="btn btn-success btn-fill" type="submit"><%if (request.getParameter("PIValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-success btn-primary" href="index.jsp?page=piList">Cancel</a>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>
