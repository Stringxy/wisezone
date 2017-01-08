$(function() {

	initTable();
	
	$(".checkall").click(function () {
	      var check = $(this).prop("checked");
	      $(".checkchild").prop("checked", check);
	});
});
function initTable() {
	var u = $("body").attr("data-url");
	var table=$("#dataTables-example")
			.DataTable(
					{
						"bFilter" : true,// 去掉搜索框
						// "bAutoWidth": true, //自适应宽度
						"bInfo": true,
						"scrollX": true,
						"sPaginationType" : "full_numbers",// 指定分页器风格
						// "sAjaxDataProp" : "aData",
						"bDestroy" : true,
						"bSort" : false,
						"bProcessing" : true,
						"sAjaxSource" : $("body").attr("data-url")
								+ "/StudyGame/Question!searchByCheck.action",
						//"bServerSide" : true,
						"bScrollCollapse" : true,
						"aoColumns" : [ {
							"mDataProp" : "questionId",
						}, {
							"mDataProp" : "questionId",
						}, {
							"mDataProp" : "major.majorName",
						}, {
							"mDataProp" : "majorstage.stageName",
						}, {
							"mDataProp" : "questionName",
						}, {
							"mDataProp" : "questionType",
						}, {
							"sTitle" : "操作",
							"mDataProp" : "questionId",
							"sClass" : "center"
						}, ],
						"aoColumnDefs" : [

								{
									"aTargets" : [ 6 ], // 目标列位置，下标从0开始
									"mRender" : function(data, type, full) {

										return "<a href='"+u+"/StudyGame/Question!toQuestionDetailJsp.action?questionId="+data+"'  > <i class=\"fa fa-lg-2 fa-eye\"> 查看试题详情</i> </a>"

									}

								}, {
									"aTargets" : [ 5 ], // 目标列位置，下标从0开始
									"mRender" : function(data, type, full) {
										if (data == 1) {
											return "单选"
										}
										if (data == 2) {
											return "多选"
										}
										if (data == 3) {
											return "判断题"
										}
										if (data == 4) {
											return "简答题"
										}
										if (data == 5) {
											return "编程题"
										}
									}
								}, {
									"aTargets" : [ 0 ], // 目标列位置，下标从0开始
									"mRender" : function(data, type, full) {
										return "<input type='checkbox' class='checkchild' value='"+data+"' /> "
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

function save(){
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
		url:$("body").attr("data-url")+"/StudyGame/Question!save.action",
		data:"questions="+str,
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
