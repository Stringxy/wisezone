$(function() {
	
	initTable();
	
	
	$('#fm').bootstrapValidator(
			{
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok', // 验证成功显示的图标
					invalid : 'glyphicon glyphicon-remove', // 验证失败图标
					validating : 'glyphicon glyphicon-refresh' // 验证中...
				},
				// 设定要验证的字段
				fields : {

					classesName : {

						validators : {
							// 非空验证
							notEmpty : {
								message : $("#classesName").parent().parent()
										.find("label").html()
										+ '不能为空'
							},

//							remote : {
//								url : $("body").attr("data-url")
//										+ "/permission/Admin!valid.action",
//										data:"adminId="+adminid,
//								message : '该用户名已被占用！'
//							},


						}
					},
					// 以下可以自己再加东西

				}
			});
	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd',
		minView: "month", 
		todayBtn:  1,
	    autoclose: 1
	});
});
var classesid;

function initTable() {
	var u=$("body").attr("data-url");
	$("#dataTables-example").dataTable(
			{
				"bFilter" : true,// 搜索框
				// "bAutoWidth": true, //自适应宽度
				"sPaginationType" : "full_numbers",// 指定分页器风格
				// "sAjaxDataProp" : "aData",
				"bDestroy" : true,
				"bProcessing" : true,
				"sAjaxSource" : $("body").attr("data-url")
						+ "/baseManage/Classes!searchByTeacher.action",
				//"bServerSide" : true,
				"scrollX": true,
				 "bSort": false,
				"bScrollCollapse" : true,
				"aoColumns" : [ {
					"mDataProp" : "classesId",
				}, {
					"mDataProp" : "classesName",
				},
				{
					"mDataProp" : "startTime",
				},
				{
					"mDataProp" : "endTime",
				},
				{
					"mDataProp" : "major.majorName",
				}, {
					"sTitle" : "操作",
					"mDataProp" : "major.majorId",
					"sClass" : "center"
				}, ],
				"aoColumnDefs" : [

									{
										"aTargets" : [ 5 ], // 目标列位置，下标从0开始
										"mRender" : function(data, type, full) {

											return "<a onclick=\"edit(this)\" majorid="+data+"> <i class=\"fa fa-lg-2 fa-pencil\"> 编辑并查看</i> </a> &nbsp;<a  onclick=\"del(this)\" > <i class=\"fa fa-lg-2 fa-trash-o\"> 移除</i></a>"


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
					"sSearch" : "模糊查询：",
					"oPaginate" : {
						"sFirst" : "首页",
						"sPrevious" : "上一页",
						"sNext" : "下一页",
						"sLast" : "末页"
					}
				}
			});
}

function edit(obj){
	classesid=$(obj).parent().parent().children().eq(0).text();
	var name = $(obj).parent().parent().children().eq(1).text();
	var starttime = $(obj).parent().parent().children().eq(2).text();
	var endtime = $(obj).parent().parent().children().eq(3).text();
	var major = $(obj).parent().parent().children().eq(4).text();
	
	$("#majorId").attr("value", major);

	$("#classesName").attr("value", name);
	$("#startTime").attr("value", starttime);
	$("#endTime").attr("value", endtime);
	
	$("#myModal").modal('show');
}

function del(obj){
	classesid=$(obj).parent().parent().children().eq(0).text();
	$.ajax({
		cache: true,
		type: "POST",
		url:$("body").attr("data-url")+"/baseManage/Classes!del.action",
		data:$('#fm').serialize()+"&classesId="+classesid,
		async: false,
		error: function(request) {
			alert("Connection error");
		},
		success: function(json) {
			var result = eval('(' + json + ')');
			console.log(result)
				if(result.success){
					alert(result.message);
					$('#myModal').modal('hide');
					location.reload();
				}else{
					alert(result.message);
				}
		}
	});
}

function save(){
	$.ajax({
		cache: true,
		type: "POST",
		url:$("body").attr("data-url")+"/baseManage/Classes!save.action",
		data:$('#fm').serialize()+"&classesId="+classesid,
		async: false,
		error: function(request) {
			alert("Connection error");
		},
		success: function(json) {
			var result = eval('(' + json + ')');
			console.log(result)
				if(result.success){
					alert(result.message);
					$('#myModal').modal('hide');
					location.reload();
				}else{
					alert(result.message);
				}
		}
	});
}

function add(){
	window.location.href=$("body").attr("data-url")
	+"/teacher/addClasses.jsp";
}