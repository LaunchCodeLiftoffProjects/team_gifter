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
}

function isLeapYear(year) {
    return (year % 100 === 0) ? (year % 400 === 0) : (year % 4 === 0);
}

function selectDaysInMonth() {
    let month = document.getElementById("month").value;

    if (month === "February") {
        document.getElementById("day29").hidden = false;
        document.getElementById("day29").disabled = false;

        document.getElementById("day30").hidden = true;
        document.getElementById("day30").disabled = true;

        document.getElementById("day31").hidden = true;
        document.getElementById("day31").disabled = true;
    } else if (month === "April" || month === "June" || month === "September" || month === "November") {
        document.getElementById("day29").hidden = true;
        document.getElementById("day29").disabled = true;

        document.getElementById("day30").hidden = false;
        document.getElementById("day30").disabled = false;

        document.getElementById("day31").hidden = true;
        document.getElementById("day31").disabled = true;
    } else if (month === "January" || month === "March" || month === "May" || month === "July" || month === "August" || month === "October" || month === "December"){
        document.getElementById("day29").hidden = true;
        document.getElementById("day29").disabled = true;

        document.getElementById("day30").hidden = true;
        document.getElementById("day30").disabled = true;

        document.getElementById("day31").hidden = false;
        document.getElementById("day31").disabled = false;
    }
}

function setYear() {
    let isRecurring = document.getElementById("isRecurring").value;

    let year = document.getElementById("year");

    if (isRecurring) {
        year.value = "9999"
    } else {
        year.value = new Date().getFullYear();
    }

    console.log("setYear function:");
    console.log("Year: " + year.value);
    console.log("Recurring: " + isRecurring);
}

function setDate() {
    let dateStr = "";
    let date = document.getElementById("date");
    let day29 = document.getElementById("day29").value;
    let day30 = document.getElementById("day30").value;
    let day31 = document.getElementById("day31").value;
    let dayId = "";
    if(day29.length > 0) {
        dayId = "day29";
    } else if(day30.length > 0) {
        dayId = "day30";
    } else if (day31.length > 0) {
        dayId = "day31";
    }
    let day = document.getElementById(dayId).value;
    let month = document.getElementById("month").value;
    let year = document.getElementById("year").value;

    dateStr = month + " " + day + ", " + year;
    date.value = dateStr;
//    console.log("setDate function:");
//    console.log("day:" + day);
//    console.log("month: " + month);
//    console.log("year: " + year);
}

function submitOccasion() {
    console.log("submitOccasion function");
    setOccasionName();
    setYear();
    setDate();
}


