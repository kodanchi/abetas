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
        <h2 class="text-center">Un-Submitted Forms</h2>
        <legend></legend>
        <div class="col-md-10 col-md-offset-1">

            <div class="input-group"> <span class="input-group-addon">Filter</span>

                <input id="filter" type="text" class="form-control" placeholder=" by performance indicator, course name">
            </div>

            <!-- Table -->
            <table class="table table-striped table-bordered text-center">
                <tr>
                    <th>Performance Indicator</th>
                    <th>Course</th>
                    <th>Faculty</th>
                    <th>Type</th>
                </tr>
                <tbody class="searchable">
                <%

                    ArrayList<String> emailLists = new ArrayList<String>();

                    AS_Select dbs = new AS_Select();
                    String emailList = "";
                    try {
                        ArrayList<ArrayList<String>> formsList = dbs.selectAllUnsubmittedFormsForValidTerm();
                        int x=0;

                        for (ArrayList<String>formItem : formsList){
                            out.print("<tr>\n" +
                                    "<td>"+formItem.get(2)+"</td>\n" +
                                    "<td>"+formItem.get(1)+"</td>\n" +
                                    "<td>"+formItem.get(3)+" "+formItem.get(4)+"</td>\n" +
                                    "<td> "+formItem.get(5)+"</td>\n" +
                                    "</tr>");


                            emailList += formItem.get(6)+";";

                            emailLists.add(formItem.get(6));

                        }

//                        System.out.println("    size    "+emailList.lastIndexOf(';'));
//                        x=emailList.lastIndexOf(';');

                        System.out.println("    xxxxxxxx    "+x);
//                        emailList=emailList.substring(0,x);
                        System.out.println("    emlist    "+emailList);
                        System.out.println("Hello from Unsubmit form "+emailList);
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

 <form method="post" action="/Emailrem">
     <input name="emailList"  value="<%=emailLists%>" hidden />
 <button  type="submit" title="Send Reminder" class="btn btn-primary pull-left ">Send Reminder</button>
 </td>
 </form>
            <a class="btn btn-primary pull-right" href="/index.jsp">Back</a>


            <!-- End of col -->
        </div>

        <!-- End of row -->
    </div>

    <!-- End of container -->
</div>
