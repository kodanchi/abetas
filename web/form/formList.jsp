<%@ page import="FDB.F_Select" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 2/24/2016
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="application/javascript"></script>
<div class="row">
    <div class="col-md-3 col-md-offset-3">
        <div class="list-group">
            <h4>Uncompleted forms</h4>
            <%--<a href="#" class="list-group-item">Performance Indicator </br> (Course Name)</a>--%>

            <%
                F_Select sdb = new F_Select();
                Integer fid = (Integer) request.getSession().getAttribute("userLvl");

                if(fid != null){
                    ArrayList<ArrayList<String>> formativeFormsList = sdb.selectFacultyUnsubmittedFForms(fid);
                    ArrayList<String> frsRow;

                    for (int i=0; i<formativeFormsList.size(); i++){
                        frsRow = formativeFormsList.get(i);
                        out.print("<a href=\"index.jsp?page=fillForm&type=formative&id="+frsRow.get(0)+"\" class=\"list-group-item\">"+
                                frsRow.get(2)+" </br> (Course : "+frsRow.get(1)+")");
                        out.print("<div class=\"pull-right\">Formative</div>");
                        out.print("</a>");
                    }


                    ArrayList<ArrayList<String>> summativeFormsList = sdb.selectFacultyUnsubmittedSForms(fid);
                    ArrayList<String> srsRow;

                    for (int i=0; i<summativeFormsList.size(); i++){
                        srsRow = summativeFormsList.get(i);
                        out.print("<a href=\"index.jsp?page=fillForm&type=summative&id="+srsRow.get(0)+"\" class=\"list-group-item\">"+
                                srsRow.get(2)+" </br> (Course : "+srsRow.get(1)+")");
                        out.print("<div class=\"pull-right\">Summative</div>");
                        out.print("</a>");
                    }



                }
            %>
        </div>

    </div>
    <div class="col-md-3">
        <div class="list-group">
            <h4>Completed forms</h4>
            <%

                if(fid != null){
                    ArrayList<ArrayList<String>> formativeFormsList = sdb.selectFacultySubmittedFForms(fid);
                    ArrayList<String> frsRow;

                    for (int i=0; i<formativeFormsList.size(); i++){
                        frsRow = formativeFormsList.get(i);
                        out.print("<a href=\"index.jsp?page=showForm&type=formative&id="+frsRow.get(0)+"\" class=\"list-group-item\">"+
                                frsRow.get(2)+" </br> (Course : "+frsRow.get(1)+")");
                        out.print("<div class=\"pull-right\">Formative</div>");
                        out.print("</a>");
                    }


                    ArrayList<ArrayList<String>> summativeFormsList = sdb.selectFacultySubmittedSForms(fid);
                    ArrayList<String> srsRow;

                    for (int i=0; i<summativeFormsList.size(); i++){
                        srsRow = summativeFormsList.get(i);
                        out.print("<a href=\"index.jsp?page=showForm&type=formative&id="+srsRow.get(0)+"\" class=\"list-group-item\">"+
                                srsRow.get(2)+" </br> (Course : "+srsRow.get(1)+")");
                        out.print("<div class=\"pull-right\">Summative</div>");
                        out.print("</a>");
                    }



                }
            %>
        </div>

    </div>

</div>
