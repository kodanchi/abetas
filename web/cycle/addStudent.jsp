<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/13/2016
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/469357/html-text-input-allow-only-numeric-input
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/jquery.bsFormAlerts.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>



<%

    String sName = "";
    String sID = "";

    if(request.getSession().getAttribute("errMsg") != null){

        String[] OldVal = (request.getSession().getAttribute("StudentVal") != null ? (String[]) request.getSession().getAttribute("StudentVal") : null);
        System.out.print("arry of user data : "+ OldVal[1]);
        if(OldVal != null){

            sID = OldVal[0];
            sName = OldVal[1];

            request.getSession().removeAttribute("StudentVal");
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
                <h2 class="text-center">Add Student</h2>
                <div class="col-md-10 col-md-offset-1">
                    <p><%if (request.getParameter("NValue")!=null) {out.print("Update");} else out.print("Enter");%> the student information</p>

                    <form name="addStudentform" id="addStudentform" action="/AddStudent" method="post">

                        <div class="row">

                            <!-- Large button group -->
                            <div class="">

                                <input type="hidden" name="programName" value="<%=programName%>">
                                <input type="hidden" name="programID" value="<%=request.getParameter("programID")%>">
                                <input type="hidden" name="courseCode" value="<%=request.getParameter("courseCode")%>">
                                <input type="hidden" name="courseName" value="<%=request.getParameter("courseName")%>">
                                <input type="hidden" name="section" value="<%=request.getParameter("section")%>">
<%--
                                <input type="hidden" name="programName" value="<%=request.getParameter("programName")%>">
--%>
                                <input type="hidden" name="cycle" value="<%=id%>">
                                <input type="hidden" name="term" value="<%=Termid%>">

                                <%
                                    //System.out.println(request.getParameter("programName"));
                                    System.out.println(request.getParameter("programID"));
                                    System.out.println(request.getParameter("courseCode"));
                                    System.out.println(request.getParameter("courseName"));
                                    System.out.println(request.getParameter("section"));
                                    //System.out.println(request.getParameter("programName"));

                                %>
                                <div class="form-group">
                                    <ul class="list-inline">
                                        <li>
                                            <label>Student ID </label>
                                        </li>
                                        <li>
                                            <input type="text" id="StudentID" name="Student_ID" class="form-control" placeholder="Student ID"
                                                   onkeypress='return event.charCode >= 48 && event.charCode <= 57'
                                                   maxlength="20"
                                                   rel="tooltip"
                                                   title="Less than 20 digits"
                                                   value="<%if (request.getParameter("IDValue")!=null) {out.print(request.getParameter("IDValue"));}
                                else {out.print(sID);}%>"><!--Alert empty: Enter the student ID-->
                                        </li>
                                    </ul>
                                    <span data-alertid="sIDAlert"></span>
                                </div>
                                <div class="form-group">
                                    <ul class="list-inline">
                                    <li>
                                        <label>Student Name </label>
                                    </li>
                                    <li>
                                        <input type="text" id="Sname" class="form-control" name="Sname" placeholder="Student Name"
                                               rel="tooltip"
                                               title="Full Student Name"
                                               value="<%if (request.getParameter("NValue")!=null) {out.print(request.getParameter("NValue"));}
                                else {out.print(sName);}%>">
                                    </li>
                                </ul>
                                    <span data-alertid="sNameAlert"></span>
                                </div>

                                <script>
                                    document.getElementById("StudentID").addEventListener("change",function(){
                                        $(document).trigger("clear-alert-id.sIDAlert");

                                        if($("#StudentID").val().length > 20){
                                            $(document).trigger("set-alert-id-sIDAlert", [
                                                {
                                                    message: "student ID must not longer than 10 digits",
                                                    priority: "error"
                                                }
                                            ]);
                                        }
                                    });

                                    document.getElementById("Sname").addEventListener("change",function(){
                                        $(document).trigger("clear-alert-id.sIDAlert");

                                        if($("#StudentID").val() == ""){
                                            $(document).trigger("set-alert-id-sNameAlert", [
                                                {
                                                    message: "student Name is required",
                                                    priority: "error"
                                                }
                                            ]);
                                        }
                                    });

                                    $(function(){
                                        $("#addStudentform").submit(function() {
                                            var name = document.getElementById("Sname");
                                            var sid = document.getElementById("StudentID");

                                            $(document).trigger("clear-alert-id.sNameAlert");
                                            $(document).trigger("clear-alert-id.sIDAlert");

                                            if(sid.value == ""){
                                                $(document).trigger("set-alert-id-sIDAlert", [
                                                    {
                                                        message: "Enter the student ID",
                                                        priority: "error"
                                                    }
                                                ]);
                                                sid.focus();
                                                return false;
                                            }else if(sid.value.length > 20){
                                                $(document).trigger("set-alert-id-sIDAlert", [
                                                    {
                                                        message: "student ID must be less than 20 digits",
                                                        priority: "error"
                                                    }
                                                ]);
                                                sid.focus();
                                                return false;
                                            } else if(name.value == ""){
                                                $(document).trigger("set-alert-id-sNameAlert", [
                                                    {
                                                        message: "Enter the student Name",
                                                        priority: "error"
                                                    }
                                                ]);
                                                name.focus();
                                                return false;
                                            }else if(!/^[a-zA-Z]*$/g.test(name.value)){
                                                $(document).trigger("clear-alert-id.sNameAlert");
                                                $(document).trigger("set-alert-id-sNameAlert", [
                                                    {
                                                        message: "student name must have only alphabetic letters",
                                                        priority: "error"
                                                    }
                                                ]);
                                                name.focus();
                                                return false;
                                            }

                                        });


                                    });

                                    $(document).ready(function(){
                                        $('[data-toggle="tooltip"]').tooltip();
                                    });
                                </script>

                            </div>
                        </div>


                        <div class="form-group">

                            <input type="hidden" name="NValue" value="<%=request.getParameter("NValue")%>">
                            <input type="hidden" name="S_ID" value="<%=request.getParameter("S_ID")%>">

                        </div>
                        <button class="btn btn-primary" type="submit"><%if (request.getParameter("NValue")!=null) {out.print("Update");} else out.print("Add");%></button>
                        <a class="btn btn-default pull-right" href="${pageContext.request.contextPath}/cycle/index.jsp?page=studentList&cycle=<%=request.getParameter("cycle")%>&term=<%=request.getParameter("term")%>&programID=<%=request.getParameter("programID")%>&courseCode=<%=request.getParameter("courseCode")%>&courseName=<%=request.getParameter("courseName")%>&section=<%=request.getParameter("section")%>">Cancel</a>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

        </div>
