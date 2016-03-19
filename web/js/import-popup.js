/**
 * Created by Mojahed on 3/19/2016.
 */
function importPopup(excelPage,manualPage,title,message){
    bootbox.dialog({
        message: message,
        title: title,
        buttons: {
            importBtn: {
                label: 'Import Excel Sheet',
                className: 'btn-primary',
                callback: function() {
                    window.location.href = excelPage;
                }
            },
            enterBtn: {
                label: 'Enter Manually',
                className: 'btn-primary',
                callback: function() {
                    window.location.href = manualPage;
                }
            },
            cancelBtn: {
                label: 'Cancel',
                className: 'btn-default',
                callback: function() {

                }
            }
        }
    });
}