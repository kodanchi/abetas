<%@ page import="ASDB.P_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap-select.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>

<%

    if(request.getSession().getAttribute("errMsg") != null){


        out.print("<script type=\"text/javascript\">\n" +
                "    $(window).load(function(){\n" +
                "       bootbox.alert(\""+request.getSession().getAttribute("errMsg")+"\")\n" +
                "    });\n" +
                "</script>");
        request.getSession().removeAttribute("errMsg");


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



                                    <select class="selectpicker" name="Obj"  data-live-search="true">

                                        <%
                                            P_AS_Select aselect = new P_AS_Select();
                                            try {
                                                ArrayList<String> objRs = aselect.selectObjForLink(Integer.parseInt(request.getParameter("id")));

                                                String objValue = request.getParameter("ObjLinkValue")!= null ?
                                                        request.getParameter("ObjLinkValue").substring(0, request.getParameter("ObjLinkValue").indexOf(':')):
                                                        "";
                                                for (int i=0; i<objRs.size();i++) {
                                                    String optValue = objRs.get(i).substring(0, objRs.get(i).indexOf(':'));
                                                    out.print("<option value=\""+optValue);
                                                    out.print("\"");


                                                    System.out.println("the value is :"+optValue +" and oj is :"+objValue);
                                                    if (request.getParameter("ObjLinkValue") != null && optValue.equals(objValue)) {
                                                        System.out.println("inside odj selected if");
                                                        out.print(" selected");
                                                    }
                                                    out.print(">");
                                                    if(objRs.get(i).length()> 150){
                                                        out.print(objRs.get(i).substring(0,150)+"..."+"</option>");
                                                    }else {
                                                        out.print(objRs.get(i)+"</option>");
                                                    }

                                                }

                                        %>
                                    </select>
                        </div>


                            <div class="form-group">

                                <label>Student Outcome: </label>

                                        <select class="selectpicker"  name="Out" data-live-search="true">
                                            <%
                                                    P_AS_Select bselect = new P_AS_Select();

                                                    ArrayList<String> outRs = bselect.selectOutForLink(Integer.parseInt(request.getParameter("id")));

                                                    String outValue = request.getParameter("OutLinkValue") != null ?
                                                            request.getParameter("OutLinkValue").substring(0, request.getParameter("OutLinkValue").indexOf(':')):
                                                            "";
                                                    for (int i=0; i<outRs.size();i++) {
                                                        String optValue = outRs.get(i).substring(0, outRs.get(i).indexOf(':'));
                                                        out.print("<option value=\""+optValue);
                                                        out.print("\"");


                                                        if (request.getParameter("OutLinkValue") != null && optValue.equals(outValue)) {
                                                            System.out.println("inside out selected if");
                                                            out.print(" selected");
                                                        }
                                                        out.print(">");
                                                        if(outRs.get(i).length()> 150){
                                                            out.print(outRs.get(i).substring(0,150)+"..."+"</option>");
                                                        }else {
                                                            out.print(outRs.get(i)+"</option>");
                                                        }
                                                    }

                                                    out.print("</select>\n" +
                                                        "                            </div>");

                                                    if(objRs.size() > 0 && outRs.size() > 0){
                                                        out.print("<button class=\"btn btn-primary\" type=\"submit\">");
                                                        if (request.getParameter("ObjLinkValue")!=null) {
                                                            out.print("Update");
                                                        } else {
                                                            out.print("Add");
                                                        }
                                                        out.print("</button>");
                                                    }else {
                                                        out.print("<p class=\"red\" >In order to add a link you must add at least one objecive or outcome</p>");
                                                    }
                                                    } catch (ClassNotFoundException e) {
                                                        e.printStackTrace();
                                                    } catch (SQLException e) {
                                                        e.printStackTrace();
                                                    }
                                            %>




                                <a class="btn btn-default pull-right" href="index.jsp?page=LinkOutObj&name=<%=request.getParameter("name")%>&id=<%=request.getParameter("id")%>">Cancel</a>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- End of container -->
        </div>
