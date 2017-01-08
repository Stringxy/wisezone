$(function() {

	initTable();
});
function initTable() {
	var u = $("body").attr("data-url");
	var table=$("#dataTables-example")
			.DataTable(
					{
						"bFilter" : true,// 去掉搜索框
						// "bAutoWidth": true, //自适应宽度
						"sPaginationType" : "full_numbers",// 指定分页器风格
						// "sAjaxDataProp" : "aData",
						"bDestroy" : true,
						"scrollX": true,
						 "bSort": false,
						"bProcessing" : true,
						"sAjaxSource" : u
								+ "/StudyGame/RecordHistory!search.action",
						//"bServerSide" : true,
						"bScrollCollapse" : true,
						"aoColumns" : [ {
							"mDataProp" : "recordId",
						}, {
							"mDataProp" : "user.stuName",
						}, {
							"mDataProp" : "detail.checkName",
						}, {
							"mDataProp" : "createDate",
						}, {
							"mDataProp" : "correct",
						}, {
							"mDataProp" : "errors",
						}, {
							"mDataProp" : "pass",
						}, {
							"sTitle" : "操作",
							"mDataProp" : "recordId",
							"sClass" : "center"
						}, ],
						"aoColumnDefs" : [

								{
									"aTargets" : [ 7 ], // 目标列位置，下标从0开始
									"mRender" : function(data, type, full) {

										return "<a onclick=\"getDetail("
												+ data
												+ ")\"  > <i class=\"fa fa-lg-2 fa-eye\"> 查看详情</i> </a>"

									}

								}, {
									"aTargets" : [ 6 ], // 目标列位置，下标从0开始
									"mRender" : function(data, type, full) {
										if (data == 1) {
											return "过关"
										}
										if (data == 0) {
											return "未过关"
										}

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
							 "sSearch" : "模糊查询：",
							"oPaginate" : {
								"sFirst" : "首页",
								"sPrevious" : "上一页",
								"sNext" : "下一页",
								"sLast" : "末页"
							}
						}
					});
	// Apply the search
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

function getDetail(obj) {
	
	$.ajax({
		cache : true,
		type : "POST",
		url : $("body").attr("data-url")
				+ "/StudyGame/RecordHistory!getRHDetail.action",
		data : "recordid=" + obj,
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(json) {
			var result = eval('(' + json + ')');
			
			var tbody = "";  
			$("#formbody").html("");
			$.each(result, function(n, value) {
				
				var trs = "";
				trs += "<label class=\"control-label\">题目:&nbsp;</label>" + value.questionName + "<br/> <label class=\"control-label\">学生答案:</label>&nbsp;" + value.stuAnswer
						+ "<br/>";
				tbody += trs;
			});
			$('#formbody').append(tbody);
			$('#myModal').modal('show');

		}
	});
}