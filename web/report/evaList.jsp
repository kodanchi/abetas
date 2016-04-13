<%@ page import="EDB.E_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="EDB.EncDec" %><%--
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
        <h3 class="text-center">Result</h3>
        <div class="col-md-6">
            <h4>Cycle List</h4>

            <div class="just-padding">


                <div class="list-group well">
                    <p>Please choose the cycle down to the outcome to view the evaluation result:</p>

                    <%
                        Integer userLvl = (Integer) request.getSession().getAttribute("userLvl");


                        E_Select dbs = new E_Select();
                        ArrayList<Integer> cycleList = null;
                        try {
                            cycleList = dbs.selectCycle();


                        for (Integer cycleId : cycleList){

                            %>
                        <a href="#cycle-<%=cycleId%>" class="list-group-item" data-toggle="collapse">
                            <i class="glyphicon glyphicon-chevron-right"></i>Cycle-<%=cycleId%>
                            </a>
                        <div class="list-group list-group-root collapse" id="cycle-<%=cycleId%>">
                            <%
                                ArrayList<ArrayList<String>> termList = dbs.selectTermToEvaluate(cycleId);
                                for (ArrayList<String> term : termList){
                                    %>
                                <a href="#term-<%=term.get(0)%>" class="list-group-item" data-toggle="collapse">
                                    <i class="glyphicon glyphicon-chevron-right"></i><%=term.get(1)%>-<%=term.get(2)%>
                                </a>
                                <div class="list-group collapse" id="term-<%=term.get(0)%>">

                                    <%
                                        ArrayList<ArrayList<String>> programList = null;

                                        if(userLvl ==3){
                                            Integer eid = Integer.parseInt((String) request.getSession().getAttribute("userId"));
                                            programList = dbs.selectAllProgramsofEvaluatorToEvaluate(eid);
                                        }else {
                                            programList = dbs.selectAllProgramsToEvaluate();
                                        }

                                        for (ArrayList<String> program : programList){
                                            %>
                                    <a href="#program-<%=program.get(0)%>-<%=term.get(0)%>" class="list-group-item" data-toggle="collapse">
                                        <i class="glyphicon glyphicon-chevron-right"></i><%=program.get(1)%>
                                    </a>
                                    <div class="list-group collapse" id="program-<%=program.get(0)%>-<%=term.get(0)%>">
                                        <%
                                            ArrayList<ArrayList<String>> outcomeList =  dbs.selectOutcomesToEvaluate(Integer.parseInt(term.get(0)),
                                                    Integer.parseInt(program.get(0)));

                                            for (ArrayList<String> outcome : outcomeList){
                                        %>
                                        <a href="#outcome-<%=outcome.get(0)%>" class="list-group-item"
                                        onclick="new function (){
                                                            show('page', false);
                                                            show('loading', true);
                                                            $.ajax({
                                                               type: 'POST',
                                                               data:{
                                                                   tid: <%=term.get(0)%>,
                                                                   pid: <%=program.get(0)%>,
                                                                   oid: <%=outcome.get(0)%>,
                                                                   oName:'<%=EncDec.getEncr(outcome.get(1))%>',
                                                               },
                                                               url:'/SelectOutcomeServlet',
                                                               success: function(result){
                                                                $('#outcomeList').html(result);
                                                                show('page', true);
                                                                show('loading', false);
                                                                scrollTo('outcomeList');
                                                               }
                                                           });
                                                        }" >
                                            <%=outcome.get(1)%>
                                        </a>
                                        <div class="list-group " id="outcome-<%=outcome.get(0)%>">

                                        </div>
                                        <%
                                            }
                                        %>
                                    </div>
                                    <%
                                        }

                                    %>





                                </div>
                            <%
                                }
                            %>
                        </div>
                        <%
                            /*out.print("<a href=\"#cycle-"+cycleId+"\" class=\"list-group-item \" onclick=\"new function (){\n" +
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
                            );*/
                        }

                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                    %>






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

                <div id="outcomeList"></div>
            </div>

        </div>






    </div>
</div>
