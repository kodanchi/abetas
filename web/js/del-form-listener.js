/**
 * Created by Mojahed on 3/19/2016.
 */
$(document).ready( function(){
    $('form.delForm').on('submit',function (e){
        e.preventDefault();
        var form = $(this);
        bootbox.confirm('Are you sure want to delete this?', function(result) {
            if(result == true){
                //var form = $(this).parents('form:first');
                $('form.delForm').off('submit');
                form.submit();
                //alert('fdf');
            }
        });

    });
});