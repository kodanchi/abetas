<%@ page import="ASDB.P_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/3/2016
  Time: 7:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap-select.min.js" type="text/javascript"></script>

<%

    String pname ="";
    String pmission = "";

    if(request.getSession().getAttribute("errMsg") != null){

        /*String[] programOldVal = (request.getSession().getAttribute("programVal") != null ? (String[]) request.getSession().getAttribute("programVal") : null);
        System.out.print("arry of user data : "+ programOldVal[1]);
        if(programOldVal != null){

            pname = programOldVal[0];
            pmission = programOldVal[1];
            request.getSession().removeAttribute("programVal");
        }*/

                        /*out.print("<div id=\"alert\"  class=\"alert alert-danger fade in\"  role=\"alert\" >\n" +
                                "                        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                                "                            <span aria-hidden=\"true\">&times;</span>\n" +
                                "                        </button>\n" +
                                "                        <strong id=\"alertt\" >\n" +
                                "                            " + request.getParameter("err")+
                                "                        </strong>\n" +
                                "                    </div>");*/

        out.print("<script type=\"text/javascript\">\n" +
                "    $(window).load(function(){\n" +
                "        $('#errModal').modal('show');\n" +
                "    });\n" +
                "</script>" +
                "<!-- Modal -->\n" +
                "                    <div class=\"modal fade\" id=\"errModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
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
        <div class="container" id="space">
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Link Program Objective and Student Outcome</h2>
                <div class="col-md-10 col-md-offset-1">
                    <p>Select an objective and student outcome to link them for <%=request.getParameter("name")%> program:</p>

                    <form name="myform" action="/Add Link Outcome and Objective" method="post">

                        <div class="form-group">

                            <label>Program Objective: </label>

                                <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                                <input type="hidden" name="name" value="<%=request.getParameter("name")%>">
                                <input type="hidden" name="Linkid" value="<%=request.getParameter("Linkid")%>">
                                <input type="hidden" name="ObjLinkValue" value="<%=request.getParameter("ObjLinkValue")%>">
                                <input type="hidden" name="OutLinkValue" value="<%=request.getParameter("OutLinkValue")%>">


                                    <select class=" selectpicker" name="Obj" data-live-search="true">
                                        <%
                                            P_AS_Select aselect = new P_AS_Select();
                                            try {
                                                ArrayList<String> rs = aselect.selectObjForLink(Integer.parseInt(request.getParameter("id")));

                                                /*for (int i=0; i<rs.size();i++) {
                                                    out.print("<option value="+rs.get(i).substring(0, rs.get(i).indexOf(':'))+">"+rs.get(i)+"</option>");
                                                }*/
                                                String objValue = request.getParameter("ObjLinkValue")!= null ?
                                                        request.getParameter("ObjLinkValue").substring(0, request.getParameter("ObjLinkValue").indexOf(':')):
                                                        "";
                                                for (int i=0; i<rs.size();i++) {
                                                    String optValue = rs.get(i).substring(0, rs.get(i).indexOf(':'));
                                                    out.print("<option value=\""+optValue);
                                                    out.print("\"");


                                                    System.out.println("the value is :"+optValue +" and oj is :"+objValue);
                                                    if (request.getParameter("ObjLinkValue") != null && optValue.equals(objValue)) {
                                                        System.out.println("inside odj selected if");
                                                        out.print(" selected");
                                                    }
                                                    out.print(">");
                                                    out.print(rs.get(i)+"</option>");
                                                }
                                            } catch (ClassNotFoundException e) {
                                                e.printStackTrace();
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                        %>
                                    </select>
                        </div>


                            <div class="form-group">

                                <label>Student Outcome: </label>

                                        <select class=" selectpicker"  name="Out" data-live-search="true">
                                            <%
                                                P_AS_Select bselect = new P_AS_Select();
                                                try {
                                                    ArrayList<String> rs = bselect.selectOutForLink(Integer.parseInt(request.getParameter("id")));

                                                    /*for (int i=0; i<rs.size();i++) {
                                                        out.print("<option value="+rs.get(i).substring(0, rs.get(i).indexOf(':'))+">"+rs.get(i)+"</option>");
                                                    }*/
                                                    String outValue = request.getParameter("OutLinkValue") != null ?
                                                            request.getParameter("OutLinkValue").substring(0, request.getParameter("OutLinkValue").indexOf(':')):
                                                            "";
                                                    for (int i=0; i<rs.size();i++) {
                                                        String optValue = rs.get(i).substring(0, rs.get(i).indexOf(':'));
                                                        out.print("<option value=\""+optValue);
                                                        out.print("\"");


                                                        if (request.getParameter("OutLinkValue") != null && optValue.equals(outValue)) {
                                                            System.out.println("inside out selected if");
                                                            out.print(" selected");
                                                        }
                                                        out.print(">");
                                                        out.print(rs.get(i)+"</option>");
                                                    }
                                                } catch (ClassNotFoundException e) {
                                                    e.printStackTrace();
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                }
                                            %>
                                        </select>
                            </div>

                                <button class="btn btn-primary" type="submit"><%if (request.getParameter("ObjLinkValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                                <a class="btn btn-default pull-right" href="index.jsp?page=LinkOutObj&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Cancel</a>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- End of container -->
        </div>
