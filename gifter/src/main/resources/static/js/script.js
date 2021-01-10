//window.onload = function() {
//    document.getElementById("focus").focus();
//};

$(document).ready(function() {
    var el = document.getElementById("focus");
//    console.log("Input:",el);
    if (el){
        document.getElementById("focus").focus();
    }
});

function disableOccasionInput(){
    let isDisabled = document.getElementById("customName").disabled;
    if(isDisabled === true){
        document.getElementById("standardName").disabled = true;
        document.getElementById("customName").disabled = false;
        document.getElementById("toggleOccasionButton").innerHTML = "Click here to select one that's already been created";

    }else{
        document.getElementById("standardName").disabled = false;
        document.getElementById("customName").disabled = true;
        document.getElementById("toggleOccasionButton").innerHTML = "Click here to create your own occasion";
    }
}

function lockYear() {
    let locked = document.getElementById("recurring");
    let year = new Date().getFullYear();
    if(locked){
        document.getElementById('date').setAttribute("min", year + "-01-01");
        document.getElementById('date').setAttribute("max", year + "-12-31");
    }
}