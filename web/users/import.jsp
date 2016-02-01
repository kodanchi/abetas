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

                            <div class="col-lg-9 col-md-8 col-sm-9 col-xs-7">  <input class="form-control" type="file" name="first" value="" size="50" /></div>



                            <div class="col-lg-3 col-md-4 col-sm-3 col-xs-5"> <input class="btn btn-lg btn-success pull-right" type="submit" value="Browse" name="AddUser" /></div>


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
