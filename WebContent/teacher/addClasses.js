$(function() {

	initTable();

	$(".checkall").click(function() {
		var check = $(this).prop("checked");
		$(".checkchild").prop("checked", check);
	});
});
var classesid;

function initTable() {
	var u = $("body").attr("data-url");
	var table=$("#dataTables-example")
			.DataTable(
					{
						"bFilter" : true,// 搜索框
						// "bAutoWidth": true, //自适应宽度
						"sPaginationType" : "full_numbers",// 指定分页器风格
						// "sAjaxDataProp" : "aData",
						"bDestroy" : true,
						"bProcessing" : true,
						"sAjaxSource" : $("body").attr("data-url")
								+ "/baseManage/Classes!search.action",
						// "bServerSide" : true,
						"scrollX" : true,
						"bSort" : false,
						"bScrollCollapse" : true,
						"aoColumns" : [ {
							"mDataProp" : "classesId",
						}, {
							"mDataProp" : "classesId",
						}, {
							"mDataProp" : "classesName",
						}, {
							"mDataProp" : "startTime",
						}, {
							"mDataProp" : "endTime",
						}, {
							"mDataProp" : "major.majorName",
						} ],
						"aoColumnDefs" : [ {
							"aTargets" : [ 0 ], // 目标列位置，下标从0开始
							"mRender" : function(data, type, full) {
								return "<input type='checkbox' class='checkchild' value='"
										+ data + "' /> "
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

function save() {
	var arr=$(".checkchild");
	var str="";
	
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			str+=arr[i].value+",";
		}
	}
	
	$.ajax({
		cache: true,
		type: "POST",
		url:$("body").attr("data-url")+"/baseManage/Classes!addClassesForTeacher.action",
		data:"classes="+str,
		async: false,
		error: function(request) {
			alert("Connection error");
		},
		success: function(json) {
			var result = eval('(' + json + ')');
			console.log(result)
				if(result.success){
					alert(result.message);
					history.back();
				}else{
					alert(result.message);
				}
		}
	});
}
