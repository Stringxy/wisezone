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

					checkName : {

						validators : {
							// 非空验证
							notEmpty : {
								message : $("#checkName").parent().parent()
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

});
var checkid;

function initTable() {
	var u=$("body").attr("data-url");
	var table=$("#dataTables-example").DataTable(
			{
				"bFilter" : true,// 去掉搜索框
				// "bAutoWidth": true, //自适应宽度
				"sPaginationType" : "full_numbers",// 指定分页器风格
				// "sAjaxDataProp" : "aData",
				"bDestroy" : true,
				 "bSort": false,
				 "bInfo": true,
				 "scrollX": true,
				"bProcessing" : true,
				"sAjaxSource" : $("body").attr("data-url")
						+ "/StudyGame/Check!search.action",
				//"bServerSide" : true,
				"bScrollCollapse" : true,
				"aoColumns" : [ {
					"mDataProp" : "checkId",
				}, {
					"mDataProp" : "checkName",
				},
				{
					"mDataProp" : "checkCount",
				},
				{
					"mDataProp" : "major.majorName",
				},
				{
					"sTitle" : "操作",
					"mDataProp" : "major.majorId",
					"sClass" : "center"
				}, ],
				"aoColumnDefs" : [

									{
										"aTargets" : [ 4 ], // 目标列位置，下标从0开始
										"mRender" : function(data, type, full) {

											return "<a onclick=\"edit(this)\" majorid="+data+" > <i class=\"fa fa-lg-2 fa-pencil\"> 编辑</i> </a> &nbsp;"
											+"<a onclick=\"getDetail(this)\"  > <i class=\"fa fa-lg-2 fa-eye\"> 查看关卡详情</i> </a>"


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
					"sSearch" : "模糊查询:",
					"oPaginate" : {
						"sFirst" : "首页",
						"sPrevious" : "上一页",
						"sNext" : "下一页",
						"sLast" : "末页"
					}
				}
			});
	 table.columns().every( function () {
	        var that = this;
	 
	        $( 'input', this.footer() ).on( 'keyup change', function () {
	            if ( that.search() !== this.value ) {
	                that
	                    .search( this.value )
	                    .draw();
	            }
	        } );
	    } );
}

function edit(obj){
	checkid=$(obj).parent().parent().children().eq(0).text();
	var majorid = $(obj).attr("majorid");

	
	var checkName = $(obj).parent().parent().children().eq(1).text();
	var checkCount = $(obj).parent().parent().children().eq(2).text();
	
	$("#checkName").attr("value", checkName);
	$("#majorId").find("option[value="+majorid+"]").attr("selected","selected");
	
	$("#checkCount").attr("value", checkCount);
	

	$("#myModal").modal('show');
}



function save(){
	$.ajax({
		cache: true,
		type: "POST",
		url:$("body").attr("data-url")+"/StudyGame/Check!save.action",
		data:$('#fm').serialize()+"&checkId="+checkid,
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
	checkid=0;

	$("#permissionModalLabel").text("添加关卡");
	$("#myModal").modal('show');
}

function getDetail(obj){
	checkid=$(obj).parent().parent().children().eq(0).text();
	 window.location.href=$("body").attr("data-url")
		+"/StudyGame/Detail!toDetailJsp.action?checkId="+checkid;
}