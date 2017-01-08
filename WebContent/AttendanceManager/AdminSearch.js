$(function() {
	initTime();
	initTable();
});

function initTime(){
	var now = (new Date()).toLocaleDateString();
    $('#current-time').text(now+"考勤记录");
}
function initTable() {
	var day=$('#current-time').attr("createday");
var u=$("body").attr("data-url");
	$("#dataTables-example").dataTable(
					{
						"bFilter" : false,// 去掉搜索框
						// "bAutoWidth": true, //自适应宽度
						"sPaginationType" : "full_numbers",// 指定分页器风格
						// "sAjaxDataProp" : "aData",
						"bDestroy" : true,
						"bProcessing" : true,
						"sAjaxSource" : $("body").attr("data-url")
								+ "/AttendanceManager/Attendance!searchByAdmin.action",
						"bServerSide" : true,
						"bScrollCollapse" : true,
						"aoColumns" : [ {
							"mDataProp" : "user.stuId",
						}, {
							"mDataProp" : "user.stuName",
						},  {
							"mDataProp" : "classes.classesName",
						}, {
							"sTitle" : "上午",
							"mDataProp" : "dayMorning"+day,
							"sClass" : "center"
						},{
							"sTitle" : "下午",
							"mDataProp" : "dayAfternoon"+day,
							"sClass" : "center"
						},  ],
						"aoColumnDefs" : [

						{
							"aTargets" : [ 3 ], // 目标列位置，下标从0开始
							"mRender" : function(data, type, full) {

								if(data==1){
									return '正常'
								}
								if(data==2){
									return '迟到'
								}
								if(data==3){
									return '早退'
								}
								if(data==4){
									return '请假'
								}
								if(data==5){
									return '旷课'
								}
								if(data==6){
									return '放假'
								}


							}

						},{
							"aTargets" : [ 4 ], // 目标列位置，下标从0开始
							"mRender" : function(data, type, full) {

								if(data==1){
									return '正常'
								}
								if(data==2){
									return '迟到'
								}
								if(data==3){
									return '早退'
								}
								if(data==4){
									return '请假'
								}
								if(data==5){
									return '旷课'
								}
								if(data==6){
									return '放假'
								}



							}

						} ],
						"oLanguage" : {
							"sProcessing" : "正在加载中......",
							"sLengthMenu" : "每页显示 _MENU_ 条记录",
							"sZeroRecords" : "没有数据！",
							"sEmptyTable" : "表中无数据存在！",
							"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
							"sInfoEmpty" : "显示0到0条记录",
							"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
							// "sSearch" : "搜索",
							"oPaginate" : {
								"sFirst" : "首页",
								"sPrevious" : "上一页",
								"sNext" : "下一页",
								"sLast" : "末页"
							}
						}
					});
}
