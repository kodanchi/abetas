<%@ page import="ASDB.C_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap-number-input.js" type="text/javascript"></script>
<script src="/js/jquery.bsFormAlerts.js" type="text/javascript"></script>



<%


    /**
     * used to display rubrics names for selected cycle.
     */



    String id = "";
    if(request.getParameter("cycle") != null){
        id  = request.getParameter("cycle");
    }

%>


        <div class="container" id="space">

            <div class="row">
                <div class="col-md-7 col-md-offset-2">
                    <nav>
                        <ol class="cd-breadcrumb triangle small">
                            <li ><em>PI</em></li>
                            <li class="current"><em>Rubrics Names</em></li>
                            <li><em>Terms</em></li>
                        </ol>
                    </nav>
                </div>
            </div>

            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Rubrics Names</h2>

                <div class="col-md-10 col-md-offset-1">
                    <p>Enter the four rubrics names</p>

                    <form id="rn" name="myform" action="/AddRubricNames" method="post">

                                <input type="hidden" name="RubricID" value="<%=request.getParameter("RubricID")%>">
                                <input type="hidden" name="NA" value="<%=request.getParameter("NA")%>">
                                <input type="hidden" name="NB" value="<%=request.getParameter("NB")%>">
                                <input type="hidden" name="NC" value="<%=request.getParameter("NC")%>">
                                <input type="hidden" name="ND" value="<%=request.getParameter("ND")%>">
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
                                <input type="text" class="form-control" size="25" id="firstR" name="firstR" value="<%if (A!=null) {out.print(A);}%>" >
                                <span data-alertid="firstR"></span>
                            </div>

                            <div class="form-group">
                                <label>Second rubrics</label>
                                <input type="text" class="form-control" size="25" id="secondR" name="secondR" value="<%if (B!=null) {out.print(B);}%>" >
                                <span data-alertid="secondR"></span>
                            </div>

                            <div class="form-group">
                                <label>Third rubrics</label>
                                <input type="text" class="form-control" size="25" id="thirdR" name="thirdR" value="<%if (C!=null) {out.print(C);}%>" >
                                <span data-alertid="thirdR"></span>
                            </div>

                            <div class="form-group">
                                <label>Forth rubrics</label>
                                <input type="text" class="form-control" size="25" id="forthR" name="forthR" value="<%if (D!=null) {out.print(D);}%>" >
                                <span data-alertid="forthR"></span>
                            </div>


                        <br>

                        <a class="btn btn-primary" href="index.jsp?page=piList&cycle=<%=id%>">Back</a>
                        <button class="btn btn-primary pull-right" type="submit">Next</button>

                    <!-- End of col -->

                        </div>
                <!-- End of row -->
            </div>

        </div>
<script>

    $(function(){
        $('#rn').submit(function(){

            var firstR = document.getElementById("firstR");
            var secondR = document.getElementById("secondR");
            var thirdR = document.getElementById("thirdR");
            var forthR = document.getElementById("forthR");


            $(document).trigger("clear-alert-id.firstR");
            $(document).trigger("clear-alert-id.secondR");
            $(document).trigger("clear-alert-id.thirdR");
            $(document).trigger("clear-alert-id.forthR");

            if(firstR.value == "") {
                $(document).trigger("clear-alert-id.firstR");
                $(document).trigger("set-alert-id-firstR", [
                    {
                        message: "Enter the first rubric name",
                        priority: "error"
                    }
                ]);
                firstR.focus();
                return false;
            }else if (!/^[A-Za-z\s]+$/g.test(firstR.value)) {
                $(document).trigger("clear-alert-id.firstR");
                $(document).trigger("set-alert-id-firstR", [
                    {
                        message: "rubric name must have only alphabetic letters",
                        priority: "error"
                    }
                ]);
                firstR.focus();
                return false;
            }else if(secondR.value == "") {
                $(document).trigger("clear-alert-id.secondR");
                $(document).trigger("set-alert-id-secondR", [
                    {
                        message: "Enter the second rubric name",
                        priority: "error"
                    }
                ]);
                secondR.focus();
                return false;
            }else if (!/^[A-Za-z\s]+$/g.test(secondR.value)) {
                $(document).trigger("clear-alert-id.secondR");
                $(document).trigger("set-alert-id-secondR", [
                    {
                        message: "rubric name must have only alphabetic letters",
                        priority: "error"
                    }
                ]);
                secondR.focus();
                return false;
            }else if(thirdR.value == "") {
                $(document).trigger("clear-alert-id.thirdR");
                $(document).trigger("set-alert-id-thirdR", [
                    {
                        message: "Enter the third rubric name",
                        priority: "error"
                    }
                ]);
                thirdR.focus();
                return false;
            }else if (!/^[A-Za-z\s]+$/g.test(thirdR.value)) {
                $(document).trigger("clear-alert-id.thirdR");
                $(document).trigger("set-alert-id-thirdR", [
                    {
                        message: "rubric name must have only alphabetic letters",
                        priority: "error"
                    }
                ]);
                thirdR.focus();
                return false;
            }else if(forthR.value == "") {
                $(document).trigger("clear-alert-id.forthR");
                $(document).trigger("set-alert-id-forthR", [
                    {
                        message: "Enter the fourth rubric name",
                        priority: "error"
                    }
                ]);
                forthR.focus();
                return false;
            }else if (!/^[A-Za-z\s]+$/g.test(forthR.value)) {
                $(document).trigger("clear-alert-id.forthR");
                $(document).trigger("set-alert-id-forthR", [
                    {
                        message: "rubric name must have only alphabetic letters",
                        priority: "error"
                    }
                ]);
                forthR.focus();
                return false;
            }
        });
    });


</script>

