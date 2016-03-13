<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 3/2/2016
  Time: 11:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap-number-input.js" type="text/javascript"></script>

<%

    String lout = "";
    String lpi = "";
    String lcourse = "";
    String ltype = "";

    if(request.getSession().getAttribute("errMsg") != null){

        String[] OldVal = (request.getSession().getAttribute("LinkVal") != null ? (String[]) request.getSession().getAttribute("LinkVal") : null);
        System.out.print("arry of user data : "+ OldVal[1]);
        if(OldVal != null){

            lout = OldVal[0];
            lpi = OldVal[1];
            lcourse = OldVal[2];
            ltype = OldVal[3];

            request.getSession().removeAttribute("LinkVal");
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
    if(request.getParameter("cycle") != null){
        id  = request.getParameter("cycle");
        out.println("id is : "+id);
    }

%>

<%
    C_AS_Select aselect = new C_AS_Select();
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
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Specify Rubric Names</h2>

                <div class="col-md-10 col-md-offset-1">
                    <p>Enter the four rubric names</p>

                    <form name="myform" action="/AddRubricNames" method="post">

                                <input type="hidden" name="RubricID" value="<%=request.getParameter("RubricID")%>">
                                <input type="hidden" name="NA" value="<%=request.getParameter("NA")%>">
                                <input type="hidden" name="NB" value="<%=request.getParameter("NB")%>">
                                <input type="hidden" name="NC" value="<%=request.getParameter("NC")%>">
                                <input type="hidden" name="ND" value="<%=request.getParameter("ND")%>">
                                <input type="hidden" name="programID" value="<%=request.getParameter("programID")%>">
                                <input type="hidden" name="cycle" value="<%=id%>">


                            <%
                                String A="";
                                String B="";
                                String C="";
                                String D="";
                                int size = 0;

                                    C_AS_Select eselect = new C_AS_Select();
                                    try {
                                        ArrayList<String> rss = eselect.selectRubricNames(Integer.parseInt(id));

                                        size=rss.size();

                                        A=rss.get(0);
                                        B=rss.get(1);
                                        C=rss.get(2);
                                        D=rss.get(3);

                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                            %>
                            <div class="form-group">
                                <label>First rubrics</label>
                                <input type="text" class="form-control" size="25" name="firstR" value="<%if (A!=null) {out.print(A);}%>" required>
                            </div>

                            <div class="form-group">
                                <label>Second rubrics</label>
                                <input type="text" class="form-control" size="25" name="secondR" value="<%if (B!=null) {out.print(B);}%>" required>
                            </div>

                            <div class="form-group">
                                <label>Third rubrics</label>
                                <input type="text" class="form-control" size="25" name="thirdR" value="<%if (C!=null) {out.print(C);}%>" required>
                            </div>

                            <div class="form-group">
                                <label>Forth rubrics</label>
                                <input type="text" class="form-control" size="25" name="forthR" value="<%if (D!=null) {out.print(D);}%>" required>
                            </div>


                        <br>

                        <button class="btn btn-primary">Back</button>
                        <button class="btn btn-primary pull-right" type="submit">Next</button>
                        <button class="btn btn-default pull-right" href="index.jsp?cycle=<%=id%>&page=piList&programID=<%=request.getParameter("programID")%>" >Cancel</button>

                    <!-- End of col -->

                        </div>
                <!-- End of row -->
            </div>

        </div>

