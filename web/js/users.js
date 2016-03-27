/**
 * Created by Mojahed on 1/25/2016.
 */


function onUserTypeChng()
{
    var ddl = document.getElementById("userType");
    var selectedValue = ddl.options[ddl.selectedIndex].value;
    if (selectedValue == "Superuser" || selectedValue == "Faculty_Member")
    {
        document.getElementById("emailDiv").style.visibility = "visible";
        document.getElementById("userEmail").focus();
    }else {
        document.getElementById("emailDiv").style.visibility = "hidden";
        document.getElementById("userEmail").value = "";
    }
}

function onSubmitAddUser(){

    var fname = document.getElementById("fname");
    var mname = document.getElementById("mname");
    var lname = document.getElementById("lname");
    var uname = document.getElementById("uname");



    $(document).trigger("clear-alert-id.lvl");
    $(document).trigger("clear-alert-id.uemail");
    $(document).trigger("clear-alert-id.fname");
    $(document).trigger("clear-alert-id.mname");
    $(document).trigger("clear-alert-id.lname");
    $(document).trigger("clear-alert-id.uname");

    var lvl = document.getElementById("userType");
    var selectedValue = lvl.options[lvl.selectedIndex].value;
    if (selectedValue == "Superuser" || selectedValue == "Faculty_Member")
    {
        var email = document.getElementById("userEmail");
    }


    if(fname.value == "") {
        $(document).trigger("clear-alert-id.fname");
        $(document).trigger("set-alert-id-fname", [
            {
                message: "Enter first name",
                priority: "error"
            }
        ]);
        fname.focus();
        return false;
    }else if (!/^[a-zA-Z]*$/g.test(fname.value)) {
        $(document).trigger("clear-alert-id.fname");
        $(document).trigger("set-alert-id-fname", [
            {
                message: "Name must have only alphabetic letters",
                priority: "error"
            }
        ]);
        fname.focus();
        return false;
    }else if (fname.value.length < 3 || fname.value.length > 20) {
        $(document).trigger("clear-alert-id.fname");
        $(document).trigger("set-alert-id-fname", [
            {
                message: "First name must be in the range 3-20 characters",
                priority: "error"
            }
        ]);
        fname.focus();
        return false;
    }else if(mname.value == "") {
        $(document).trigger("clear-alert-id.mname");
        $(document).trigger("set-alert-id-mname", [
            {
                message: "Please enter middle name",
                priority: "error"
            }
        ]);
        mname.focus();
        return false;
    }else if (!/^[a-zA-Z]*$/g.test(mname.value)) {
        $(document).trigger("clear-alert-id.mname");
        $(document).trigger("set-alert-id-mname", [
            {
                message: "name must have only alphabetic letters",
                priority: "error"
            }
        ]);
        mname.focus();
        return false;
    }else if (mname.value.length < 3 || mname.value.length > 20) {
        $(document).trigger("clear-alert-id.mname");
        $(document).trigger("set-alert-id-mname", [
            {
                message: "middle name must be in the range 3-20 characters",
                priority: "error"
            }
        ]);
        mname.focus();
        return false;
    }else if(lname.value == "") {
        $(document).trigger("clear-alert-id.lname");
        $(document).trigger("set-alert-id-lname", [
            {
                message: "Please enter middle name",
                priority: "error"
            }
        ]);
        lname.focus();
        return false;
    }else if (!/^[a-zA-Z]*$/g.test(lname.value)) {
        $(document).trigger("clear-alert-id.lname");
        $(document).trigger("set-alert-id-lname", [
            {
                message: "name must have only alphabetic letters",
                priority: "error"
            }
        ]);
        lname.focus();
        return false;
    }else if (lname.value.length < 3 || lname.value.length > 20) {
        $(document).trigger("clear-alert-id.lname");
        $(document).trigger("set-alert-id-lname", [
            {
                message: "last name must be in the range 3-20 characters",
                priority: "error"
            }
        ]);
        lname.focus();
        return false;
    }else if(uname.value == "") {
        $(document).trigger("clear-alert-id.uname");
        $(document).trigger("set-alert-id-uname", [
            {
                message: "You must enter user's username",
                priority: "error"
            }
        ]);
        uname.focus();
        return false;
    }else if (uname.value.length < 7 || uname.value.length > 20) {
        $(document).trigger("clear-alert-id.uname");
        $(document).trigger("set-alert-id-uname", [
            {
                message: "username must be in the range 7-20 characters",
                priority: "error"
            }
        ]);
        uname.focus();
        return false;
    }else if(selectedValue == "User Type" || selectedValue == "") {
        $(document).trigger("clear-alert-id.lvl");
        $(document).trigger("set-alert-id-lvl", [
            {
                message: "You must select user type",
                priority: "error"
            }
        ]);
        lvl.focus();
        return false;
    }else if(selectedValue == "Superuser" || selectedValue == "Faculty_Member") {
        if (email.value != "") {
            var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if (!re.test(email.value)) {
                $(document).trigger("clear-alert-id.email");
                $(document).trigger("set-alert-id-email", [
                    {
                        message: "You must enter valid email format",
                        priority: "error"
                    }
                ]);
                email.focus();
                return false;
            } else {
            document.getElementById("UserAddForm").submit();
        }
        }else {
            $(document).trigger("clear-alert-id.email");
            $(document).trigger("set-alert-id-email", [
                {
                    message: "You must enter a valid email",
                    priority: "error"
                }
            ]);
            email.focus();
            return false;
        }
    }else {
        document.getElementById("UserAddForm").submit();
    }

}