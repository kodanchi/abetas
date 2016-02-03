<%@ page import="ASDB.AS_Select" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 2/1/2016
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/11832930/html-input-file-accept-attribute-file-type-csv
  http://stackoverflow.com/questions/15201071/how-to-get-full-path-of-selected-file-on-change-of-input-type-file-using-jav
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.js" type="text/javascript"></script>


    <div class="section">
        <div class="container" id="space">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Import Excel File</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">

                    <%

                    if(request.getParameter("err") != null){

                        /*out.print("<div id=\"alert\"  class=\"alert alert-danger fade in\"  role=\"alert\" >\n" +
                                "                        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                                "                            <span aria-hidden=\"true\">&times;</span>\n" +
                                "                        </button>\n" +
                                "                        <strong id=\"alertt\" >\n" +
                                "                            " + request.getParameter("err")+
                                "                        </strong>\n" +
                                "                    </div>");*/

                        out.print("<script type=\"text/javascript\">\n" +
                                "    $(window).load(function(){\n" +
                                "        $('#myModal').modal('show');\n" +
                                "    });\n" +
                                "</script>" +
                                "<!-- Modal -->\n" +
                                "                    <div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
                                "                        <div class=\"modal-dialog\">\n" +
                                "                            <div class=\"modal-content\">\n" +
                                "                                <div class=\"modal-header\">\n" +
                                "                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n" +
                                "                                    <h4 class=\"modal-title\" id=\"myModalLabel\">ERROR</h4>\n" +
                                "                                </div>\n" +
                                "                                <div class=\"modal-body\">\n" +
                                                                        request.getParameter("err") +
                                "                                </div>\n" +
                                "                                <div class=\"modal-footer\">\n" +
                                "\n" +
                                "                                    <div class=\"text-center\">\n" +
                                "                                        <a type=\"button\"  data-dismiss=\"modal\"  class=\"btn btn-default btn-simple\">OK</a>\n" +
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                            </div>\n" +
                                "                        </div>\n" +
                                "                    </div>");


                    }

                %>


                    <div class="row tim-row">
                        <p class="col-md-6 lead pull-left">Please insure that the excel file format is following the same format as the figure, you are responsible for any wrong important data.</p>


                        <div class="col-md-6 img-responsive pull-right">
                            <img src="http://i.stack.imgur.com/irt3e.jpg" class="img-responsive" alt="excel format">
                        </div>
                    </div>
                    <form name="myform" action="/import/users" method="post" enctype="multipart/form-data">

                        <div class="row tim-row">
                            <div class="col-md-8 col-md-offset-2">


                                <div class="input-group">
    <span class="input-group-btn">
                    <span class="btn btn-fill btn-primary btn-file">
                        Browse&hellip; <input type="file" ACCEPT="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" name="excelInput">
                    </span>
    </span>
                                    <input type="text" class="form-control" readonly>
                                </div>
                                <!--    <input class="form-control" type="text" name="first" value="" size="50" /> -->
                                <!--    <input class="btn btn-lg btn-success pull-right" type="submit" value="Browse" name="AddUser"/> -->


                            </div>
                        </div>
                        <button type="submit" class="btn btn-success btn-fill">Upload</button>
                    </form>








                    <button class="btn btn-primary pull-right">Back</button>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>

<script src="/js/ct-paper.js"></script>
<script>

    // got it from this link
    $(document).on('change', '.btn-file :file', function() {
        var input = $(this),
                numFiles = input.get(0).files ? input.get(0).files.length : 1,
                label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', [numFiles, label]);
    });

    $(document).ready( function() {
        $('.btn-file :file').on('fileselect', function(event, numFiles, label) {

            var input = $(this).parents('.input-group').find(':text'),
                    log = numFiles > 1 ? numFiles + ' files selected' : label;

            if( input.length ) {
                input.val(log);
            } else {
                if( log ) alert(log);
            }

        });
    });




</script>

</html>