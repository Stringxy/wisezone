$(function() {
	
	initTable();
	
	
	initUpload();

});





function initTable() {
	var u=$("body").attr("data-url");
	var table=$("#dataTables-example").DataTable(
			{
				"bFilter" : true,// 去掉搜索框
				// "bAutoWidth": true, //自适应宽度
				"sPaginationType" : "full_numbers",// 指定分页器风格
				// "sAjaxDataProp" : "aData",
				"bDestroy" : true,
				"scrollX": true,
				 "bSort": false,
				"bProcessing" : true,
				"sAjaxSource" : $("body").attr("data-url")
						+ "/studentManager/EmployeeInfo!search.action",
				//"bServerSide" : true,
				"bScrollCollapse" : true,
				"aoColumns" : [ {
					"mDataProp" : "id",
				}, {
					"mDataProp" : "user.stuName",
				},
				{
					"mDataProp" : "classes.classesName",
				},
				{
					"mDataProp" : "employeeWay",
				},
				{
					"mDataProp" : "employeeDate",
				}, {
					"mDataProp" : "employeeUnit",
				},
				{
					"mDataProp" : "employeePost",
				},
				{
					"mDataProp" : "employeeSalary",
				},
				{
					"mDataProp" : "employeeAddress",
				},
				{
					"mDataProp" : "employeeContact",
				},
				{
					"mDataProp" : "employeeTel",
				},
				{
					"mDataProp" : "twoEmployee",
				},
				{
					"sTitle" : "操作",
					"mDataProp" : "id",
					"sClass" : "center"
				}, ],
				"aoColumnDefs" : [

									{
										"aTargets" : [ 12 ], // 目标列位置，下标从0开始
										"mRender" : function(data, type, full) {

											return "<a href='"+u+"/studentManager/EmployeeInfo!editEmp.action?id="+data+"'> <i class=\"fa fa-lg-2 fa-pencil\"> 编辑</i> </a> "


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
					 "sSearch" : "模糊查询:",
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



function initUpload(){

	$("#uploadfile").fileinput({   
		language: 'zh', //设置语言
        uploadUrl: $("body").attr("data-url")+"/studentManager/EmployeeInfo!upload.action", //上传的地址
        allowedFileExtensions: ['xls', 'xlsx'],//接收的文件后缀
        //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : true, //是否显示预览
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式     
        dropZoneEnabled: false,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount: 10, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    slugCallback: function(filename) {  
            return filename.replace('(', '_').replace(']', '_');  
        }  
    });   
          //这是提交完成后的回调函数    
     $("#uploadfile").on("fileuploaded", function (event, data, previewId, index) {  
         console.log(data)
    	 alert(data.response.result); 
         location.reload();
     });  
}


