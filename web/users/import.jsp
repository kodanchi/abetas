<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 2/1/2016
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>




    <div class="section">
        <div class="container" id="space">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Import Excel File</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">

                    <div class="row tim-row">
                        <p class="col-md-6 lead pull-left">Please insure that the excel file format is following the same format as the figure, you are responsible for any wrong important data.</p>


                        <div class="col-md-6 img-responsive pull-right">
                            <img src="http://i.stack.imgur.com/irt3e.jpg" class="img-responsive" alt="excel format">
                        </div>
                    </div>
                    <form name="myform" action="#" method="post">


                        <div class="row tim-row">
                            <div class="col-md-8 col-md-offset-2">


                                <div class="input-group">
    <span class="input-group-btn">
                    <span class="btn btn-fill btn-primary btn-file">
                        Browse&hellip; <input type="file" >
                    </span>
    </span>
                                    <input type="text" class="form-control" readonly>
                                </div>
                                <!--    <input class="form-control" type="text" name="first" value="" size="50" /> -->
                                <!--    <input class="btn btn-lg btn-success pull-right" type="submit" value="Browse" name="AddUser"/> -->


                            </div>
                        </div>
                    </form>





                    <button class="btn btn-success btn-fill">Upload</button>


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