$(function() {
	

	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd',
		minView: "month", 
		todayBtn:  1,
	    autoclose: 1,
	    language: 'zh-CN'  
	});

	$('.form_datetime').datetimepicker('setStartDate', '2017-01-01');
	
	
});

function search(){
	
	var classesId=$("#classesId option:selected").val();
	var createtime=$("#createtime").val();
	console.log(classesId)
	 window.location.href=$("body").attr("data-url")
		+"/AttendanceManager/Attendance!toAdminSearch?classesId="+classesId+"&createtime="+createtime;
}


function downloadMonth(){
	var classesId=$("#classesIdForMonth option:selected").val();
	var month=$("#month").val();
	 window.location.href=$("body").attr("data-url")
		+"/AttendanceManager/Download!downloadMonth?classesIdForMonth="+classesId+"&month="+month;
}

function downloadWeek(){
	var classesId=$("#classesIdForWeek option:selected").val();
	var monday=$("#monday").val();
	window.location.href=$("body").attr("data-url")
	+"/AttendanceManager/Download!downloadWeek?classesIdForWeek="+classesId+"&monday="+monday;
}