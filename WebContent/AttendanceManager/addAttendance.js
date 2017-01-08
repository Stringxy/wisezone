$(function() {
	initTime();
	initTable();
});

function initTime(){
	var now = (new Date()).toLocaleDateString();
    $('#current-time').text(now+"考勤记录");
}
function initTable() {
	var day=new Date().getDate();
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
								+ "/AttendanceManager/Attendance!searchByClasses.action",
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
						}, {
							"sTitle" : "操作",
							"mDataProp" : "id",
							"sClass" : "center"
						}, ],
						"aoColumnDefs" : [

						{
							"aTargets" : [ 5 ], // 目标列位置，下标从0开始
							"mRender" : function(data, type, full) {

								return "<a style='cursor:pointer;' onclick='save(this)' attendanceid='"+data+"'> <i class=\"fa fa-lg-2 fa-pencil\"> 保存修改</i> </a> "



							}

						},{
							"aTargets" : [ 3 ], // 目标列位置，下标从0开始
							"mRender" : function(data, type, full) {
									if(data==1){
										return "<select class='morning'>" +
										"<option value='1' selected='selected'>正常</option>" +
										"<option value='2'>迟到</option>" +
										"<option value='3'>早退</option>" +
										"<option value='4'>请假</option>" +
										"<option value='5'>旷课</option>" +
										"<option value='6'>放假</option>" +
										"</select>"
									}
									if(data==2){
										
										return "<select class='morning'>" +
										"<option value='1' >正常</option>" +
										"<option value='2' selected='selected'>迟到</option>" +
										"<option value='3'>早退</option>" +
										"<option value='4'>请假</option>" +
										"<option value='5'>旷课</option>" +
										"<option value='6'>放假</option>" +
										"</select>"
									}
										
										if(data==3){
										
										return "<select class='morning'>" +
										"<option value='1' >正常</option>" +
										"<option value='2' >迟到</option>" +
										"<option value='3' selected='selected'>早退</option>" +
										"<option value='4'>请假</option>" +
										"<option value='5'>旷课</option>" +
										"<option value='6'>放假</option>" +
										"</select>"
									}
										if(data==4){
										
										return "<select class='morning'>" +
										"<option value='1' >正常</option>" +
										"<option value='2' >迟到</option>" +
										"<option value='3'>早退</option>" +
										"<option value='4' selected='selected'>请假</option>" +
										"<option value='5'>旷课</option>" +
										"<option value='6'>放假</option>" +
										"</select>"
									}
										if(data==5){
											
											return "<select class='morning'>" +
											"<option value='1' >正常</option>" +
											"<option value='2'>迟到</option>" +
											"<option value='3'>早退</option>" +
											"<option value='4'>请假</option>" +
											"<option value='5' selected='selected'>旷课</option>" +
											"<option value='6'>放假</option>" +
											"</select>"
										}
										if(data==6){
											
											return "<select class='morning'>" +
											"<option value='1' >正常</option>" +
											"<option value='2'>迟到</option>" +
											"<option value='3'>早退</option>" +
											"<option value='4'>请假</option>" +
											"<option value='5'>旷课</option>" +
											"<option value='6' selected='selected'>放假</option>" +
											"</select>"
										}

							}

						},{
							"aTargets" : [ 4 ], // 目标列位置，下标从0开始
							"mRender" : function(data, type, full) {

								if(data==1){
									return "<select class='afternoon'>" +
									"<option value='1' selected='selected'>正常</option>" +
									"<option value='2'>迟到</option>" +
									"<option value='3'>早退</option>" +
									"<option value='4'>请假</option>" +
									"<option value='5'>旷课</option>" +
									"<option value='6'>放假</option>" +
									"</select>"
								}
								if(data==2){
									
									return "<select class='afternoon'>" +
									"<option value='1' >正常</option>" +
									"<option value='2' selected='selected'>迟到</option>" +
									"<option value='3'>早退</option>" +
									"<option value='4'>请假</option>" +
									"<option value='5'>旷课</option>" +
									"<option value='6'>放假</option>" +
									"</select>"
								}
									
									if(data==3){
									
									return "<select class='afternoon'>" +
									"<option value='1' >正常</option>" +
									"<option value='2' >迟到</option>" +
									"<option value='3' selected='selected'>早退</option>" +
									"<option value='4'>请假</option>" +
									"<option value='5'>旷课</option>" +
									"<option value='6'>放假</option>" +
									"</select>"
								}
									if(data==4){
									
									return "<select class='afternoon'>" +
									"<option value='1' >正常</option>" +
									"<option value='2' >迟到</option>" +
									"<option value='3'>早退</option>" +
									"<option value='4' selected='selected'>请假</option>" +
									"<option value='5'>旷课</option>" +
									"<option value='6'>放假</option>" +
									"</select>"
								}
									if(data==5){
										
										return "<select class='afternoon'>" +
										"<option value='1' >正常</option>" +
										"<option value='2'>迟到</option>" +
										"<option value='3'>早退</option>" +
										"<option value='4'>请假</option>" +
										"<option value='5' selected='selected'>旷课</option>" +
										"<option value='6'>放假</option>" +
										"</select>"
									}
									if(data==6){
										
										return "<select class='afternoon'>" +
										"<option value='1' >正常</option>" +
										"<option value='2'>迟到</option>" +
										"<option value='3'>早退</option>" +
										"<option value='4'>请假</option>" +
										"<option value='5'>旷课</option>" +
										"<option value='6' selected='selected'>放假</option>" +
										"</select>"
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

function save(obj){
	
	var morning=$(obj).parent().parent().children().eq(3).find(".morning option:selected").val();
	var afternoon=$(obj).parent().parent().children().eq(4).find(".afternoon option:selected").val();
	var id=$(obj).attr("attendanceid");
	
	$.ajax({
		cache: true,
		type: "POST",
		url:$("body").attr("data-url")+"/AttendanceManager/Attendance!save.action",
		data:"morning="+morning+"&afternoon="+afternoon+"&id="+id,
		async: false,
		error: function(request) {
			alert("Connection error");
		},
		success: function(json) {
			var result = eval('(' + json + ')');
			console.log(result)
				if(result.success){
					alert(result.message);
					
					
				}else{
					alert(result.message);
				}
		}
	});
}