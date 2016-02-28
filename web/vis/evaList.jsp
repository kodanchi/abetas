<%@ page import="EDB.E_Select" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 2/27/2016
  Time: 7:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <div id="cycleList" class="list-group">
                <h4>Cycles</h4>
                <%
                    E_Select dbs = new E_Select();
                    ArrayList<Integer> cycleList = dbs.selectCycle();

                    for (Integer cycleId : cycleList){
                        out.print("                    <a type=\"button\" value=\""+cycleId+"\" class=\"list-group-item \" href=\"#\" onclick=\"new function (){\n" +
                                "                    show('page', false);\n" +
                                "                    show('loading', true);\n" +
                                "                    $.ajax({\n" +
                                "                       type: 'POST',\n" +
                                "                       data:{cid: "+cycleId+"},\n" +
                                "                       url:'/SelectCycleServlet',\n" +
                                "                       success: function(result){\n" +
                                "                        $('#termList').html(result);\n" +
                                "                        show('page', true);\n" +
                                "                        show('loading', false);\n" +
                                "                    }});" +
                                "                }\">"+cycleId+"</a>");
                    }
                %>

                <%--<a onclick="new function (){
                    show('page', false);
                    show('loading', true);
                    $.ajax({
                       type: 'POST',
                       data:{cid: 326},
                       url:'/SelectCycleServlet',
                       success: function(result){
                        $('#termList').html(result);
                        show('page', true);
                        show('loading', false);

                       }

                    })

                }" href="#" class="list-group-item active">C001</a>--%>
            </div>

            <script>
                $(document).on('click','#cycleList a',function(){
                    $('#cycleList a').removeClass('active');
                    $(this).addClass('active');
                })
                $(document).on('click','#termList a',function(e){
                    e.preventDefault();
                    $('#termList a').removeClass('active');
                    $(this).addClass('active');
                })
                $(document).on('click','#programList a',function(e){
                    e.preventDefault();
                    $('#programList a').removeClass('active');
                    $(this).addClass('active');
                })
                $(document).on('click','#pIList a',function(){
                    $('#pIList a').removeClass('active');
                    $(this).addClass('active');
                })
            </script>
        </div>
        <div class="col-md-2">
            <div class="list-group">
                <h4>Term</h4>
                <div id="termList"></div>
                <%--<a href="#" class="list-group-item">Term1 2015-2016</a>--%>

            </div>

        </div>

        <div class="col-md-2">
            <div class="list-group">
                <h4>Programs</h4>
                <div id="programList"></div>
            </div>

        </div>

        <div class="col-md-3">
            <div class="list-group">
                <h4>Performance Indicator</h4>
                <div id="pIList"></div>
            </div>

        </div>





    </div>
</div>
