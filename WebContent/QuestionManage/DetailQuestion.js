var dqid;
$(function() {
	
	initTable();
	
	


});


function initTable() {
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
						+ "/QuestionManage/DetailQuestion!search.action",
				"bServerSide" : true,
				"bScrollCollapse" : true,
				"aoColumns" : [ {
					"mDataProp" : "detailQuestionId",
				}, {
					"mDataProp" : "detail.checkName",
				},
				{
					"mDataProp" : "question",
				},
				{
					"sTitle" : "操作",
					"mDataProp" : "detail.detailId",
					"sClass" : "center"
				}, ],
				"aoColumnDefs" : [

									{
										"aTargets" : [ 3 ], // 目标列位置，下标从0开始
										"mRender" : function(data, type, full) {

											return "<a onclick=\"edit(this)\" detailid="+data+" > <i class=\"fa fa-lg-2 fa-pencil\"> 编辑</i> </a> &nbsp;<a  onclick=\"del(this)\" > <i class=\"fa fa-lg-2 fa-trash-o\"> 删除</i></a>"


										}
										

									},{
										"aTargets" : [ 2 ], // 目标列位置，下标从0开始
										"mRender" : function(data, type, full) {

											return "<div questionId="+data.questionId+">"+data.questionName+"</div>"


										}
										

									}, ],
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

function edit(obj){
	dqid=$(obj).parent().parent().children().eq(0).text();
	var detailid = $(obj).attr("detailid");
	var questionId=$(obj).parent().parent().children().eq(2).find("div").attr("questionId");
	


	$("#detailId").find("option[value="+detailid+"]").attr("selected","selected");
	$("#questionId").find("option[value="+questionId+"]").attr("selected","selected");

	$("#myModal").modal('show');
}

function del(obj){
	dqid=$(obj).parent().parent().children().eq(0).text();
	console.log(dqid)
	$.ajax({
		cache: true,
		type: "POST",
		url:$("body").attr("data-url")+"/QuestionManage/DetailQuestion!del.action",
		data:$('#fm').serialize()+"&detailQuestionId="+dqid,
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
		url:$("body").attr("data-url")+"/QuestionManage/DetailQuestion!save.action",
		data:$('#fm').serialize()+"&detailQuestionId="+dqid,
		async: false,
		error: function(request) {
			alert("Connection error");
		},
		success: function(json) {
			var result = eval('(' + json + ')');

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
	dqid=0;

	$("#permissionModalLabel").text("添加关卡试题");
	$("#myModal").modal('show');
}