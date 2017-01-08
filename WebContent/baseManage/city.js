$(function() {

	initTable();
});

function initTable() {
var u=$("body").attr("data-url");
	$("#dataTables-example")
			.dataTable(
					{
						"bFilter" : false,// 去掉搜索框
						// "bAutoWidth": true, //自适应宽度
						"sPaginationType" : "full_numbers",// 指定分页器风格
						// "sAjaxDataProp" : "aData",
						"bDestroy" : true,
						"bProcessing" : true,
						"sAjaxSource" : $("body").attr("data-url")
								+ "/baseManage/City!search.action",
						"bServerSide" : true,
						"bScrollCollapse" : true,
						"aoColumns" : [ {
							"mDataProp" : "id",
						}, {
							"mDataProp" : "name",
						}, {
							"sTitle" : "操作",
							"mDataProp" : "id",
							"sClass" : "center"
						}, ],
						"aoColumnDefs" : [

						{
							"aTargets" : [ 2 ], // 目标列位置，下标从0开始
							"mRender" : function(data, type, full) {

								return "<a  type=\"post\" href='"+u+"/baseManage/City!editCity.action?id="+data+"'> <i class=\"fa fa-lg-2 fa-pencil\"> 编辑</i> </a> &nbsp;<a  onclick=\"del('"+data+"')\" > <i class=\"fa fa-lg-2 fa-trash-o\"> 删除</i></a>"



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





