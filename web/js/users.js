/**
 * Created by Mojahed on 1/25/2016.
 */


function onUserTypeChng()
{
    var ddl = document.getElementById("userType");
    var selectedValue = ddl.options[ddl.selectedIndex].value;
    if (selectedValue == "Superuser" || selectedValue == "Faculty_Member")
    {
        document.getElementById("emailDiv").innerHTML="<label>Email address</label>" +
            "<input type='email' class='form-control' placeholder='Email' required>";
    }else {
        document.getElementById("emailDiv").innerHTML="";
    }
}