$(function() {
	
	initTable();
});

function initTable() {

var u=$("body").attr("data-url");
$("#dataTables-example")
		.dataTable(
				{
					"bFilter" : true,// 去掉搜索框
					// "bAutoWidth": true, //自适应宽度
					"sPaginationType" : "full_numbers",// 指定分页器风格
					// "sAjaxDataProp" : "aData",
					"bDestroy" : true,
					 "bSort": false,
					"bProcessing" : true,
					"sAjaxSource" : $("body").attr("data-url")
							+ "/teacher/Teacher!search.action",
					//"bServerSide" : true,
					"bScrollCollapse" : true,
					"scrollX": true,
					"aoColumns" : [ {
					"mDataProp" : "teacherId",
				}, {
					"mDataProp" : "teacherName",
				}, {
					"mDataProp" : "loginName",
				},  {
					"mDataProp" : "sex",
				}, {
					"mDataProp" : "major",
				}, {
					"mDataProp" : "city.name",
				},  {
					"mDataProp" : "portrait",
				}, {
					"mDataProp" : "role.roleName",
				}, {
					"mDataProp" : "state",
				},
				{
					"sTitle" : "操作",
					"mDataProp" : "teacherId",
					"sClass" : "center"
				},],
				"aoColumnDefs" : [

									{
										"aTargets" : [ 9 ], // 目标列位置，下标从0开始
										"mRender" : function(data, type, full) {
											return "<a href='"+u+"/teacher/Teacher!edit.action?teacherId="+data+"' > <i class=\"fa fa-lg-2 fa-pencil\"> 编辑或查看详细资料</i> </a> &nbsp;"
													+"<a href='"+u+"/baseManage/Classes!toMyClasses.action?teacherId="+data+"'  > <i class=\"fa fa-lg-2 fa-eye\"> 查看所带班级</i> </a>"
										}

									},{
										"aTargets" : [ 6 ], // 目标列位置，下标从0开始
										"mRender" : function(data, type, full) {
											
											return "<img src='"+u+data+"' style='width:50px;height:50px'>"
										}

									},{
										"aTargets" : [ 8 ], // 目标列位置，下标从0开始
										"mRender" : function(data, type, full) {
												if(data==1){return "正常"}
												if(data==2){return "休长假"}
												if(data==3){return "离职"}
										}

									},{
										"aTargets" : [ 3 ], // 目标列位置，下标从0开始
										"mRender" : function(data, type, full) {
												if(data==0){return "男"}
												if(data==1){return "女"}
												
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

