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
								+ "/QuestionManage/CollectionQuestion!search.action",
						//"bServerSide" : true,
						"bScrollCollapse" : true,
						"aoColumns" : [ {
							"mDataProp" : "id",
						}, {
							"mDataProp" : "user.stuName",
						}, {
							"mDataProp" : "question.questionName",
						}, {
							"mDataProp" : "createDate",
						},  ],
						
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