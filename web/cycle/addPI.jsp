<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap-number-input.js" type="text/javascript"></script>
<script src="/js/jquery.bsFormAlerts.js" type="text/javascript"></script>

<%
    /**
     * used to display add performance indicators page
     */


    String pName = "";
    

    if(request.getSession().getAttribute("errMsg") != null){

        String[] OldVal = (request.getSession().getAttribute("PVal") != null ? (String[]) request.getSession().getAttribute("PVal") : null);

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

                    <form name="piform" id="piform" action="/AddPI" method="post">

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
                            <textarea id="piInput" class="form-control" rows="4"  name="PI" placeholder="Performance Indicator"

                            ><%if (request.getParameter("PIValue")!=null) {out.print(request.getParameter("PIValue"));}
                            else {
                                out.print(pName);
                            }%></textarea>
                            <span data-alertid="pi"></span>
                        </div>


                        <div class="form-group">
                            <label>Threshold</label>
                            <div class="input-group number-spinner">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button" data-dir="dwn"><span class="glyphicon glyphicon-minus"></span></button>
                                    </span>
                                <input id="threshold"  onchange="onSTsholdChange();" type="text" min="0" max="100" class="form-control
                                    text-center" name="STshold" onkeypress='return event.charCode >= 48 && event.charCode <= 57' value="<%if (request.getParameter("PIThresh")!=null)
                                    {out.print(request.getParameter("PIThresh"));}
                                    else {out.print("0");}%>">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button" data-dir="up"><span class="glyphicon glyphicon-plus"></span></button>
                                    </span>

                            </div>
                            <span data-alertid="ts"></span>
                        </div>


                            <script>
                                function onSTsholdChange(){
                                    input = document.getElementById("threshold");
                                    //input = btn.closest('.number-spinner').find('input');

                                    if(parseInt(input.value) > parseInt(input.getAttribute("max"))){
                                        input.value = input.getAttribute("max");
                                    }else if(parseInt(input.value) < parseInt(input.getAttribute("min"))){
                                        input.value = input.getAttribute("min");
                                    }
                                    else if(input.value==""){
                                        input.value = input.getAttribute("min");
                                    }

                                }

                                $(function(){
                                    $('#piform').submit(function(){

                                        $(document).trigger("clear-alert-id.pi");
                                        $(document).trigger("clear-alert-id.ts");

                                        var pi = document.getElementById("piInput");
                                        var ts = document.getElementById("threshold");

                                        if(pi.value == ""){
                                            $(document).trigger("clear-alert-id.pi");
                                            $(document).trigger("set-alert-id-pi", [
                                                {
                                                    message: "Enter performance indicator",
                                                    priority: "error"
                                                }
                                            ]);
                                            pi.focus();
                                            return false;
                                        }else if(/^\d+$/g.test(pi.value)){
                                            $(document).trigger("clear-alert-id.pi");
                                            $(document).trigger("set-alert-id-pi", [
                                                {
                                                    message: "performance indicator must have alphabetic letters",
                                                    priority: "error"
                                                }
                                            ]);
                                            pi.focus();
                                            return false;
                                        } else if(/^[a-zA-Z]*$/g.test(ts.value)){
                                            $(document).trigger("clear-alert-id.ts");
                                            $(document).trigger("set-alert-id-ts", [
                                                {
                                                    message: "threshold must be only numerical",
                                                    priority: "error"
                                                }
                                            ]);
                                            ts.focus();
                                            return false;
                                        } else if(ts.value < 0 || ts.value > 100){
                                            $(document).trigger("clear-alert-id.ts");
                                            $(document).trigger("set-alert-id-ts", [
                                                {
                                                    message: "Threshold must be between 0 and 100",
                                                    priority: "error"
                                                }
                                            ]);
                                            ts.focus();
                                            return false;
                                        }
                                    });

                                });

                            </script>


                        <br>
                        <button class="btn btn-primary pull-left" type="submit"><%if (request.getParameter("PIValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-default pull-right" href="index.jsp?page=piList&cycle=<%=id%>&programID=<%=request.getParameter("programID")%>">Cancel</a>


                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


        </div>
