<%@ page import="ASDB.U_AS_Select" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>
<script src="/js/jquery.bsFormAlerts.js" type="text/javascript"></script>
<script src="/js/excel-validation.js" type="text/javascript"></script>


        <div class="container" id="space">
            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Import Excel File</h2>

                <div class="col-md-10 col-md-offset-1">


                    <%
                    /**
                         * import page used to display import page
                     */
                        if(request.getParameter("err") != null){

                            out.print("<script type=\"text/javascript\">\n" +
                                    "    $(window).load(function(){\n" +
                                    "       bootbox.alert(\""+request.getParameter("err")+"\")\n" +
                                    "    });\n" +
                                    "</script>");

                        }

                    %>


                    <div class="row ">
                        <div class="col-md-6 lead pull-left">
                            <p class="text-justify">Please insure that the excel file format is following the same
                                format as the figure, you are responsible for any wrong important data. The first column:
                                firstname the second column: middlename the third column: lastname the fourth column:
                                username the fifth column: level the sixth column: email.</p>
                            <p>Note: the evaluator email should be empty</p>
                            <p class="red"><strong>You must enter the Evaluator program manually</strong></p>
                           </div>



                        <div class="col-md-6 img-responsive pull-right">
                            <img src="/img/userExcel.JPG" class="img-responsive" alt="excel format">
                        </div>
                    </div>
                    <form id="import" name="myform" action="/import/users" method="post" enctype="multipart/form-data">

                        <div class="row tim-row">
                            <div class="col-md-8 col-md-offset-2">

                                <div class="form-group">
                                    <div class="input-group">
        <span class="input-group-btn">
                        <span class="btn btn-file" style="color:#ecf0f1; background-color: #7f8c8d;">
                            Browse&hellip; <input type="file" ACCEPT="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" name="excelInput" id="excelInput">
                        </span>
        </span>
                                        <input type="text" class="form-control" readonly>

                                    </div>

                                    <span data-alertid="exceli"></span>
                                </div>

                            </div>
                        </div>
                        <button type="submit"  class="btn btn-primary">Upload</button>
                        <a class="btn btn-primary pull-right" href="/users/index.jsp">Back</a>
                    </form>











                    <!-- End of col -->
                </div>

                <!-- End of row -->
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