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
        <div class="col-md-6">
            <h5>Cycles</h5>
            <div class="just-padding">


                <div class="list-group well">
                    <a href="#cycles" class="list-group-item " data-toggle="collapse"><i class="glyphicon glyphicon-chevron-right"></i>Cycles</a>
                    <div class="list-group collapse" id="cycles">
                    <%
                        E_Select dbs = new E_Select();
                        ArrayList<Integer> cycleList = dbs.selectCycle();

                        for (Integer cycleId : cycleList){
                            out.print("<a href=\"#cycle-"+cycleId+"\" class=\"list-group-item list-group-item-success\" onclick=\"new function (){\n" +
                                    "                    show('page', false);\n" +
                                    "                    show('loading', true);\n" +
                                    "                    $.ajax({\n" +
                                    "                       type: 'POST',\n" +
                                    "                       data:{cid: "+cycleId+"},\n" +
                                    "                       url:'/SelectCycleServlet',\n" +
                                    "                       success: function(result){\n" +
                                    "                        $('#cycle-"+cycleId+"').html(result);\n" +
                                    "                        show('page', true);\n" +
                                    "                        show('loading', false);\n" +
                                    "                        scrollTo('cycle-"+cycleId+"');" +
                                    "                    }});" +
                                    "                }\"" +
                                    " data-toggle=\"collapse\">" +
                                    "                        <i class=\"glyphicon glyphicon-chevron-right\"></i>Cycle-"+cycleId+"\n" +
                                    "</a>" +
                                    "                    <div class=\"list-group collapse\" id=\"cycle-"+cycleId+"\">" +
                                    "                    </div>"
                            );
                        }
                    %>




                    </div>

                </div>

            </div>

            <script>
                /*$(document).on('click','#cycleList a',function(){
                    $('#cycleList a').removeClass('active');
                    $(this).addClass('active');
                    $('#termList').html("");
                    $('#programList').html("");
                    $('#pIList').html("");
                })
                $(document).on('click','#termList a',function(e){
                    e.preventDefault();
                    $('#termList a').removeClass('active');
                    $(this).addClass('active');
                    $('#programList').html("");
                    $('#pIList').html("");
                })
                $(document).on('click','#programList a',function(e){
                    e.preventDefault();
                    $('#programList a').removeClass('active');
                    $(this).addClass('active');
                    $('#pIList').html("");
                })
                $(document).on('click','#pIList a',function(){
                    $('#pIList a').removeClass('active');
                    $(this).addClass('active');
                })*/
                $(function() {

                    $('.list-group-item').on('click', function() {
                        $('.glyphicon', this)
                                .toggleClass('glyphicon-chevron-right')
                                .toggleClass('glyphicon-chevron-down');
                    });

                });
                function scrollTo(hash) {
                    location.hash = "#" + hash;
                }
            </script>
        </div>


        <div class="col-md-6">
            <div class="">
                <h4>Outcome</h4>
                <div id="outcomeList"></div>
            </div>

        </div>






    </div>
</div>
