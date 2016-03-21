<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/3/2016
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap-number-input.js" type="text/javascript"></script>

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
                "       bootbox.alert(\""+request.getSession().getAttribute("errMsg")+"\")\n" +
                "    });\n" +
                "</script>");
        request.getSession().removeAttribute("errMsg");


    }

%>


<%

    String id = "";
    if(request.getParameter("cycle") != null){
        id  = request.getParameter("cycle");
    }


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
                <h2 class="text-center">Add Performance Indicator</h2>
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("PIValue")!=null) {out.print("Update");} else out.print("Enter");%> performance indicator for the <%out.print(programName);%> program</p>

                    <form name="myform" action="/AddPI" method="post">

                        <div class="form-group">

                            <!-- Large button group -->
                            <div class="btn-group">

                                <label>Program: <label><%out.print(programName);%> </label></label>
                                <input type="hidden" name="cycle" value="<%=id%>">
                                <input type="hidden" name="programName" value="<%=programName%>">
                                <input type="hidden" name="programID" value="<%=request.getParameter("programID")%>">

                            </div>
                        </div>


                        <div class="form-group">

                            <label>Performance Indicator</label>
                            <input type="hidden" name="PIValue" value="<%=request.getParameter("PIValue")%>">
                            <input type="hidden" name="PILabel" value="<%=request.getParameter("PILabel")%>">
                            <input type="hidden" name="PIThresh" value="<%=request.getParameter("PIThresh")%>">
                            <textarea class="form-control" rows="4" cols="50" name="PI" placeholder="Performance Indicator" required><%if (request.getParameter("PIValue")!=null) {out.print(request.getParameter("PIValue"));}
                            else {
                                out.print(pName);
                            }%></textarea>
                            <br>
                            <input type="text" name="Thresh" onkeypress='validate(event)' required><%if (request.getParameter("PIThresh")!=null) {out.print(request.getParameter("PIThresh"));}%>
<%--
                            <input id="STshold" onchange="onSTsholdChange();" type="text" min="0" max="100" class="text-center" name="PIThresh" value="<%if (request.getParameter("PIThresh")!=null) {out.print(request.getParameter("PIThresh"));}%>">
--%>
                            <script>
                                function onSTsholdChange(){
                                    input = document.getElementById("STshold");
                                    //input = btn.closest('.number-spinner').find('input');

                                    if(parseInt(input.value) > parseInt(input.getAttribute("max"))){
                                        input.value = input.getAttribute("max");
                                    }else if(parseInt(input.value) < parseInt(input.getAttribute("min"))){
                                        input.value = input.getAttribute("min");
                                    }

                                }


                                function validate(evt) {
                                    var theEvent = evt || window.event;
                                    var key = theEvent.keyCode || theEvent.which;
                                    key = String.fromCharCode( key );
                                    var regex = /[0-9]|\./;
                                    if( !regex.test(key) ) {
                                        theEvent.returnValue = false;
                                        if(theEvent.preventDefault) theEvent.preventDefault();
                                    }
                                }

                            </script>
                        </div>

                        <br>
                        <button class="btn btn-primary pull-left" type="submit"><%if (request.getParameter("PIValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-default pull-right" href="index.jsp?page=piList&cycle=<%=id%>&programID=<%=request.getParameter("programID")%>">Cancel</a>


                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


        </div>
