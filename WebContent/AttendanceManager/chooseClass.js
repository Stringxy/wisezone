$(function() {
	
	initTime();
});



function initTime(){
	var now = (new Date()).toLocaleDateString();
    $('#current-time').text(now);
}

function toAddAttendance(){
	
	var classesId=$("select option:selected").val();
	
	 window.location.href=$("body").attr("data-url")
		+"/AttendanceManager/Attendance!toAddAttendance?classesId="+classesId;
}