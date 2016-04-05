<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/11/2016
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/jquery.bsFormAlerts.js" type="text/javascript"></script>
<script src="/js/bootstrap-select.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>


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
                "       bootbox.alert(\""+request.getSession().getAttribute("errMsg")+"\")\n" +
                "    });\n" +
                "</script>");
        request.getSession().removeAttribute("errMsg");


    }

%>


<%

    String id = "";
    String Termid = "";
    if(request.getParameter("cycle") != null && request.getParameter("term") != null){
        id  = request.getParameter("cycle");
        Termid  = request.getParameter("term");
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
                <h2 class="text-center">Performance Indicator Links</h2>
                <div class="col-md-10 col-md-offset-1">
                    <p>Select a student outcome, performance indicator, course and type of form link them for <%out.print(programName);%> program:</p>

                    <form name="piLinkForm" id="piLinkForm" action="/AddPILinks" method="post">

                        <input type="hidden" name="LinkID" value="<%=request.getParameter("LinkID")%>">
                        <input type="hidden" name="OutValue" value="<%=request.getParameter("OutValue")%>">
                        <input type="hidden" name="PIValue" value="<%=request.getParameter("PIValue")%>">
                        <input type="hidden" name="CourseValue" value="<%=request.getParameter("CourseValue")%>">
                        <input type="hidden" name="oldCourseValue" value="<%=request.getParameter("CourseValue")%>">
                        <input type="hidden" name="TypeValue" value="<%=request.getParameter("TypeValue")%>">
                        <input type="hidden" name="PValue" value="<%=request.getParameter("PValue")%>">
                        <input type="hidden" name="TypeValue" value="<%=request.getParameter("TypeValue")%>">
                        <input type="hidden" name="oldTypeValue" value="<%=request.getParameter("TypeValue")%>">
                        <input type="hidden" name="RubricValue" value="<%=request.getParameter("RubricValue")%>">
                        <input type="hidden" name="TermValue" value="<%=request.getParameter("TermValue")%>">
                        <input type="hidden" name="programID" value="<%=request.getParameter("programID")%>">
                        <input type="hidden" name="cycle" value="<%=id%>">
                        <input type="hidden" name="term" value="<%=Termid%>">
                        <div class="form-group">

                            <label for="Out">Student Outcome: </label>

                            <select class="selectpicker"  name="Out" id="Out" onchange="onRubricFetch();"  data-live-search="true">
                                <%
                                    C_AS_Select bselect = new C_AS_Select();
                                    try {
                                        ArrayList<String> rs = bselect.selectOutForLink(Integer.parseInt(request.getParameter("programID")));

                                        for (int i=0; i<rs.size();i++) {
                                            out.print("<option value=\""+rs.get(i).substring(0, rs.get(i).indexOf(':')));
                                            out.print("\"");
                                            if (request.getParameter("OutValue") != null && rs.get(i).substring(0, rs.get(i).indexOf(':')).equals(request.getParameter("OutValue"))) {
                                                System.out.println("fdvfdfdgdbfjkvbjk njkrenjk nejrklnj jkl njkln gjklng jkngjlk nglknl gknkl kn krenrklelkg klnlg nreklnrklenjreklgnklregklgn kl grgrre");
                                                out.print(" selected");
                                            }else if(rs.get(i).substring(0, rs.get(i).indexOf(':')).equals(lout)){
                                                out.print(" selected");
                                            }
                                            out.print(">");

                                            if(rs.get(i).length()> 150){
                                                out.print(rs.get(i).substring(0,150)+"..."+"</option>");
                                            }else {
                                                out.print(rs.get(i)+"</option>");
                                            }

                                        }
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                %>
                            </select>
                            <span data-alertid="out"></span>





                        </div>

                            <div class="form-group">

                                <label>Performance Indicator: </label>

                                <div class="btn-group">

                                        <select class="selectpicker"  data-live-search="true" name="PI" id="PI" onchange="onRubricFetch();" >
                                            <%
                                                C_AS_Select cselect = new C_AS_Select();
                                                try {
                                                    ArrayList<String> rs = cselect.selectPIForLink(Integer.parseInt(id),Integer.parseInt(request.getParameter("programID")));

                                                    for (int i=0; i<rs.size();i++) {

                                                        out.print("<option value=\""+rs.get(i).substring(0, rs.get(i).indexOf(':')));
                                                        out.print("\"");
                                                        if (request.getParameter("PIValue") != null && rs.get(i).substring(0, rs.get(i).indexOf(':')).equals(request.getParameter("PIValue"))) {
                                                            System.out.println("fdvfdfdgdbfjkvbjk njkrenjk nejrklnj jkl njkln gjklng jkngjlk nglknl gknkl kn krenrklelkg klnlg nreklnrklenjreklgnklregklgn kl grgrre");
                                                            out.print(" selected");
                                                        }else if(rs.get(i).substring(0, rs.get(i).indexOf(':')).equals(lpi)){
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
                                    <span data-alertid="pi"></span>
                                </div>
                            </div>

                        <div class="form-group">

                            <label>Course: </label>

                            <div class="btn-group">

                                    <select class="selectpicker"  data-live-search="true" name="Course" id="Course" onchange="onRubricFetch();" >
                                        <%
                                            C_AS_Select dselect = new C_AS_Select();
                                            try {
                                                ArrayList<String> rs = dselect.selectCourseForLink(Integer.parseInt(request.getParameter("programID")), Integer.parseInt(Termid));

                                                for (int i=0; i<rs.size();i++) {


                                                    out.print("<option value=\""+rs.get(i).substring(0, rs.get(i).indexOf(':')));
                                                    out.print("\"");
                                                    if (request.getParameter("CourseValue") != null && rs.get(i).substring(0, rs.get(i).indexOf(':')).equals(request.getParameter("CourseValue"))) {
                                                        System.out.println("fdvfdfdgdbfjkvbjk njkrenjk nejrklnj jkl njkln gjklng jkngjlk nglknl gknkl kn krenrklelkg klnlg nreklnrklenjreklgnklregklgn kl grgrre");
                                                        out.print(" selected");
                                                    }else if(rs.get(i).substring(0, rs.get(i).indexOf(':')).equals(lcourse)){
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
                                <span data-alertid="course"></span>
                            </div>
                        </div>







                        <div class="form-group">
                            <ul class="list-inline">
<li>
                            <label>Type: </label>
</li>
                                <li>
                                 <select class="selectpicker"  data-live-search="true" name="Type" id="Type" >
                                        <option value="Summative"
                                        <% if (request.getParameter("TypeValue") != null && "Summative".equals(request.getParameter("TypeValue"))) {
                                            out.print(" selected");
                                        }else if ("Summative".equals(ltype)){
                                            out.print(" selected");
                                        }
                                            out.print(">");
                                            out.print("Summative</option>\"");

                                        %>
                                        <option value="Formative"
                                        <% if (request.getParameter("TypeValue") != null && "Formative".equals(request.getParameter("TypeValue"))) {
                                            out.print(" selected");
                                        }else if("Formative".equals(ltype)){
                                            out.print(" selected");
                                        }
                                            out.print(">");
                                            out.print("Formative </option>\"");

                                        %>

                                    </select>
                                    <span data-alertid="type"></span>
                                </li>
                                </ul>
                            </div>




                        <script>

                            function onRubricFetch(){
                                var ol = document.getElementById("Out");
                                var oid = ol.options[ol.selectedIndex].value;

                                var pil = document.getElementById("PI");
                                var pIid = pil.options[pil.selectedIndex].value;

                                var cl = document.getElementById("Course");
                                var cId = cl.options[cl.selectedIndex].value;
                                show('page', false);
                                show('loading', true);
                                $.ajax({
                                    type: 'POST',
                                    data: {
                                        out: oid,
                                        pi: pIid,
                                        pid: <%=request.getParameter("programID")%>,
                                        cid:<%=id%>,
                                        cor: cId
                                    },
                                    url: '/fetchRubrics',
                                    success: function (result) {
                                        $('#fetchedRubricsDiv').html(result);
                                        //$('#rubricsDiv').show();
                                        show('page', true);
                                        show('loading', false);

                                    }

                                })
                            }

                        </script>


                        <div class="row tim-row">
                            <h2 class="text-center">Add Rubrics</h2>
                            <legend></legend>
                                <p>You need to enter the four rubrics and their details</p>
                            <%
                                String A="";
                                String B="";
                                String C="";
                                String D="";
                                String E="";
                                String F="";
                                String G="";
                                String H="";

                                C_AS_Select nselect = new C_AS_Select();
                                try {

                                    ArrayList<String> rsss = nselect.selectRubricNames(Integer.parseInt(id));

                                    A=rsss.get(0);
                                    B=rsss.get(1);
                                    C=rsss.get(2);
                                    D=rsss.get(3);

                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                                if (request.getParameter("RubricValue")!=null) {
                                    C_AS_Select eselect = new C_AS_Select();
                                    try {
                                        ArrayList<String> rss = eselect.selectRubrics(Integer.parseInt(request.getParameter("RubricValue")));

                                        E=rss.get(0);
                                        F=rss.get(1);
                                        G=rss.get(2);
                                        H=rss.get(3);

                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            %>
                                    <div id="fetchedRubricsDiv"></div>
                                    <div id="">

                                        <div class="form-group">
                                            <label>First rubrics</label>
                                            <p ><%out.print(A);%></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea class="form-control" rows="3" name="firstD" id="firstD" ><%if (request.getParameter("RubricValue")!=null) {out.print(E);}%></textarea>
                                            <span data-alertid="firstD"></span>
                                        </div>
                                        <div class="form-group">
                                            <label>Second rubrics</label>
                                            <p ><%out.print(B);%></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea class="form-control" rows="3" name="secondD" id="secondD" ><%if (request.getParameter("RubricValue")!=null) {out.print(F);}%></textarea>
                                            <span data-alertid="secondD"></span>
                                        </div>

                                        <div class="form-group">
                                            <label>Third rubrics</label>
                                            <p><%out.print(C);%></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea class="form-control" rows="3" name="thirdD" id="thirdD" ><%if (request.getParameter("RubricValue")!=null) {out.print(G);}%></textarea>
                                            <span data-alertid="thirdD"></span>
                                        </div>

                                        <div class="form-group">
                                            <label>Forth rubrics</label>
                                            <p><%out.print(D);%></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea class="form-control" rows="3" name="forthD" id="forthD" ><%if (request.getParameter("RubricValue")!=null) {out.print(H);}%></textarea>
                                            <span data-alertid="forthD"></span>
                                        </div>

                                    </div>
                            <!-- End of row -->
                        </div>

                        <br>

                        <button class="btn btn-primary btn-fill" type="submit"><%if (request.getParameter("OutValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a  class="btn btn-default pull-right" href="index.jsp?cycle=<%=id%>&term=<%=Termid%>&page=LinkPIOutList&programID=<%=request.getParameter("programID")%>" >Cancel</a>


                    </form>
                    <!-- End of col -->
                    <script>
                        $(function(){


                            $('#piLinkForm').submit(function(){
                                $(document).trigger("clear-alert-id.out");
                                $(document).trigger("clear-alert-id.pi");
                                $(document).trigger("clear-alert-id.course");
                                $(document).trigger("clear-alert-id.firstD");
                                $(document).trigger("clear-alert-id.secondD");
                                $(document).trigger("clear-alert-id.thirdD");
                                $(document).trigger("clear-alert-id.forthD");
                                if($('#Out').val() == ""){
                                    $(document).trigger("clear-alert-id.out");
                                    $(document).trigger("set-alert-id-out", [
                                        {
                                            message: "Choose an outcome",
                                            priority: "error"
                                        }
                                    ]);
                                    $('#Out').focus();
                                    return false;
                                }else if ($('#PI').val() == ""){
                                    $(document).trigger("clear-alert-id.pi");
                                    $(document).trigger("set-alert-id-pi", [
                                        {
                                            message: "Choose a performance indicator",
                                            priority: "error"
                                        }
                                    ]);
                                    $('#PI').focus();
                                    return false;
                                }else if ($('#Course').val() == ""){
                                    $(document).trigger("clear-alert-id.course");
                                    $(document).trigger("set-alert-id-course", [
                                        {
                                            message: "Choose a course",
                                            priority: "error"
                                        }
                                    ]);
                                    $('#Course').focus();
                                    return false;
                                }else if ($('#Type').val() == ""){
                                    $(document).trigger("clear-alert-id.type");
                                    $(document).trigger("set-alert-id-type", [
                                        {
                                            message: "Choose a link type",
                                            priority: "error"
                                        }
                                    ]);
                                    $('#Type').focus();
                                    return false;
                                }else if ($('#firstD').val() == ""){
                                    $(document).trigger("clear-alert-id.firstD");
                                    $(document).trigger("set-alert-id-firstD", [
                                        {
                                            message: "Enter first rubric",
                                            priority: "error"
                                        }
                                    ]);
                                    $('#firstD').focus();
                                    return false;
                                }else if ($('#secondD').val() == ""){
                                    $(document).trigger("clear-alert-id.secondD");
                                    $(document).trigger("set-alert-id-secondD", [
                                        {
                                            message: "Enter second rubric",
                                            priority: "error"
                                        }
                                    ]);
                                    $('#secondD').focus();
                                    return false;
                                }else if ($('#thirdD').val() == ""){
                                    $(document).trigger("clear-alert-id.thirdD");
                                    $(document).trigger("set-alert-id-thirdD", [
                                        {
                                            message: "Enter third rubric",
                                            priority: "error"
                                        }
                                    ]);
                                    $('#thirdD').focus();
                                    return false;
                                }else if ($('#forthD').val() == ""){
                                    $(document).trigger("clear-alert-id.forthD");
                                    $(document).trigger("set-alert-id-forthD", [
                                        {
                                            message: "Enter fourth rubric",
                                            priority: "error"
                                        }
                                    ]);
                                    $('#forthD').focus();
                                    return false;
                                }
                            });
                        });
                        /*$(document).ready(function(){
                            $('.selectpicker').selectpicker();
                        });*/
                    </script>
                </div>

                <!-- End of row -->
            </div>

        </div>
