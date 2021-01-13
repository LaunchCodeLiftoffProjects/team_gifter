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

function setOccasionName(){
    let custom = document.getElementById("customName").value;
    let standard = document.getElementById("standardName").value;

    if (custom){
        document.getElementById("nameField").value = custom;
    } else {
        document.getElementById("nameField").value = standard;
    }

    function isLeapYear(year) {
    return (year % 100 === 0) ? (year % 400 === 0) : (year % 4 === 0);
    }
}

function selectDaysInMonth() {
    let month = document.getElementById("month").value;

    if (month === "02") {
        document.getElementById("day29").hidden = false;
        document.getElementById("day29").disabled = false;

        document.getElementById("day30").hidden = true;
        document.getElementById("day30").disabled = true;

        document.getElementById("day31").hidden = true;
        document.getElementById("day31").disabled = true;
    } else if (month === "04" || month === "06" || month === "09" || month === "11") {
        document.getElementById("day29").hidden = true;
        document.getElementById("day29").disabled = true;

        document.getElementById("day30").hidden = false;
        document.getElementById("day30").disabled = false;

        document.getElementById("day31").hidden = true;
        document.getElementById("day31").disabled = true;
    } else if (month === "01" || month === "03" || month === "05" || month === "07" || month === "08" || month === "10" || month === "12"){
        document.getElementById("day29").hidden = true;
        document.getElementById("day29").disabled = true;

        document.getElementById("day30").hidden = true;
        document.getElementById("day30").disabled = true;

        document.getElementById("day31").hidden = false;
        document.getElementById("day31").disabled = false;
    }
}

