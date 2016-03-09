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

    String pName = "";
    

    if(request.getSession().getAttribute("errMsg") != null){

        String[] OldVal = (request.getSession().getAttribute("PVal") != null ? (String[]) request.getSession().getAttribute("PVal") : null);
        System.out.print("arry of user data : "+ OldVal[0]);
        if(OldVal != null){

            pName = OldVal[0];

            request.getSession().removeAttribute("PVal");
        }


        out.print("<script type=\"text/javascript\">\n" +
                "    $(window).load(function(){\n" +
                "        $('#myModal').modal('show');\n" +
                "    });\n" +
                "</script>" +
                "<!-- Modal -->\n" +
                "                    <div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
                "                        <div class=\"modal-dialog\">\n" +
                "                            <div class=\"modal-content\">\n" +
                "                                <div class=\"modal-header\">\n" +
                "                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n" +
                "                                    <h4 class=\"modal-title\" id=\"myModalLabel\">INFO</h4>\n" +
                "                                </div>\n" +
                "                                <div class=\"modal-body\">\n");
        out.print(request.getSession().getAttribute("errMsg"));
        request.getSession().removeAttribute("errMsg");

        out.print("                                </div>\n" +
                "                                <div class=\"modal-footer\">\n" +
                "\n" +
                "                                    <div class=\"text-center\">\n" +
                "                                        <a type=\"button\"  data-dismiss=\"modal\"  class=\"btn btn-default btn-simple\">OK</a>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>");


    }

%>


<%

    String id = "";
    String Termid = "";
    if(request.getParameter("cycle") != null && request.getParameter("term") != null){
        id  = request.getParameter("cycle");
        Termid  = request.getParameter("term");
        out.println("id is : "+id);
        out.print("Termid is : "+Termid);
    }


    AS_Select aselect = new AS_Select();
    String programName = "";
    try {
        System.out.println("scscsc     "+Integer.parseInt(request.getParameter("programID")));
        programName = aselect.selectProgramName(Integer.parseInt(request.getParameter("programID")));

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>

        <div class="container" id="space">
            <!-- what is row -->
            <div class="row">
                <h2 class="text-center">Add Performance Indicator</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("PIValue")!=null) {out.print("Update");} else out.print("Enter");%> performance indicator for the <%out.print(programName);%> program</p>

                    <form name="myform" action="/AddPI" method="post">

                        <div class="form-group">

                            <!-- Large button group -->
                            <div class="btn-group">

                                <label>Program: <label><%out.print(programName);%> </label></label>
                                <input type="hidden" name="cycle" value="<%=id%>">
                                <input type="hidden" name="term" value="<%=Termid%>">
                                <input type="hidden" name="programName" value="<%=programName%>">
                                <input type="hidden" name="programID" value="<%=request.getParameter("programID")%>">

                            </div>
                        </div>


                        <div class="form-group">

                            <label>Performance Indicator</label>
                            <input type="hidden" name="PIValue" value="<%=request.getParameter("PIValue")%>">
                            <input type="hidden" name="PILabel" value="<%=request.getParameter("PILabel")%>">
                            <textarea class="form-control" rows="4" cols="50" name="PI" placeholder="Performance Indicator" required><%if (request.getParameter("PIValue")!=null) {out.print(request.getParameter("PIValue"));}
                            else {
                                out.print(pName);
                            }%></textarea>

                        </div>

                        <br>
                        <button class="btn btn-success btn-fill pull-left" type="submit"><%if (request.getParameter("PIValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-success btn-fill pull-right" href="index.jsp?page=piList&cycle=<%=id%>&term=<%=Termid%>&programID=<%=request.getParameter("programID")%>">Cancel</a>


                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


        </div>
