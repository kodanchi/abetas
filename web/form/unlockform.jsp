<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 2/26/2016
  Time: 5:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>

<%

    if(request.getSession().getAttribute("Msg")!= null){
        out.print("<script>");
        out.print("\n" +
                "bootbox.alert(\""+request.getSession().getAttribute("Msg")+"\");");

        out.print("</script>");

        request.getSession().removeAttribute("Msg");
    }
%>

<div class="container">
    <!-- Here is row -->
    <div class="row">
        <h2 class="text-center">Unlocking Submitted Forms</h2>
        <legend></legend>
        <div class="col-md-8 col-md-offset-2">

            <div class="input-group"> <span class="input-group-addon">Filter</span>

                <input id="filter" type="text" class="form-control" placeholder=" by performance indicator, course name">
            </div>

            <!-- Table -->
            <table class="table table-striped table-bordered text-center">
                <tr>
                    <th>Completed Forms</th>
                    <th>Unlock</th>
                </tr>
                <tbody class="searchable">
                <%
                    AS_Select dbs = new AS_Select();
                    try {
                        ArrayList<ArrayList<String>> formsList = dbs.selectAllSubmittedFormsForValidTerm();

                        for (ArrayList<String>formItem : formsList){
                            out.print("<tr>\n" +
                                    "<td>");

                            if(formItem.get(5).equals("formative")){
                                out.print("<a href=\"index.jsp?page=showForm&type=formative&id="+formItem.get(0)+"\" class=\"list-group-item\">"+
                                        formItem.get(2)+"</br>("+formItem.get(1)+")("+formItem.get(3)+" "+formItem.get(4)+")</br>");
                                out.print("<div class=\"pull-right\">Formative</div></br>");
                                out.print("</a>");
                            }else if(formItem.get(5).equals("summative")){
                                out.print("<a href=\"index.jsp?page=showForm&type=summative&id="+formItem.get(0)+"\" class=\"list-group-item\">"+
                                        formItem.get(2)+"</br>("+formItem.get(1)+")("+formItem.get(3)+" "+formItem.get(4)+")</br>");
                                out.print("<div class=\"pull-right\">Summative</div></br>");
                                out.print("</a>");
                            }


                            out.print("</td><td><a id=\"confirm\" class=\"btn btn-link btn-Y\" href=\"/unlockForm?fid="+
                                    formItem.get(0)+"&ftype="+formItem.get(5)+"\"><i class=\"fa fa-unlock-alt fa-3x\"></i></a></td>" +
                                    "</tr>");

                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                %>
                </tbody>
                <script>
                    $(function () {
                        $("a#confirm").click(function(e) {
                            e.preventDefault();
                            var ahref = $(this).attr('href');
                            bootbox.confirm('When Unlock this form The faculty member will be able to access it again to edit the entered ' +
                                    'data, are you sure you want to continue!?', function(confirmed) {
                                console.log('Confirmed: '+confirmed);
                                if(confirmed){
                                    window.location.href = ahref;
                                }
                            });
                        });
                    });
                </script>
                <script>
                    $(document).ready(function () {

                        (function ($) {

                            $('#filter').keyup(function () {

                                var rex = new RegExp($(this).val(), 'i');
                                $('.searchable tr').hide();
                                $('.searchable tr').filter(function () {
                                    return rex.test($(this).text());
                                }).show();

                            })

                        }(jQuery));

                    });
                </script>
            </table>


            <a class="btn btn-primary pull-right" href="/index.jsp">Back</a>


            <!-- End of col -->
        </div>

        <!-- End of row -->
    </div>

    <!-- End of container -->
</div>
