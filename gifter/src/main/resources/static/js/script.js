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

//when user chooses "other" in the drop down list for occasion names, text input appears for their custom occasion name.
function chooseOther(){
    document.getElementById("customName").disabled = false;
    document.getElementById("customName").hidden = false;
}

//when user chooses a standard occasion name, custom name input is disabled
function chooseStandard(){
    document.getElementById("customName").disabled = true;
    document.getElementById("customName").hidden = true;

}

