function onReady(callback) {
    var intervalID = window.setInterval(checkReady, 1);
    function checkReady() {
        if (document.getElementsByTagName('body')[0] !== undefined) {
            window.clearInterval(intervalID);
            callback.call(this);
        }
    }
}

function show(id, value) {
    document.getElementById(id).style.display = value ? 'block' : 'none';
}

$(document).ready(function () {
    show('page', true);
    show('loading', false);
});/**
 * Created by Mojahed on 3/12/2016.
 */
