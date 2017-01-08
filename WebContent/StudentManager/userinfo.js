$(function() {
	
	initTable();
	initUpload();
});

function initTable() {
	
var u=$("body").attr("data-url");
	var table=$("#dataTables-example")
			.DataTable(
					{
						"bFilter" : true,// 搜索框
						// "searching": true,//本地搜索
						"bAutoWidth": true, //自适应宽度
						"bInfo": true,
						"sPaginationType" : "full_numbers",// 指定分页器风格
						// "sAjaxDataProp" : "aData",
						"bDestroy" : true,
						 "bSort": false,
						"bProcessing" : true,
						"sAjaxSource" : $("body").attr("data-url")
								+ "/StudentManager/User!search.action",
					//	"bServerSide" : true,//开启服务器模式，使用服务器端处理配置datatable。
						"bStateSave": true,//状态保存，使用了翻页或者改变了每页显示数据数量，会保存在cookie中，下回访问时会显示上一次关闭页面时的内容。
						"bScrollCollapse" : true,
						"scrollX": true,
						"aoColumns" : [  {
							"mDataProp" : "stuNo",
						},{
							"mDataProp" : "stuName",
						},{
							"mDataProp" : "sex",
						},{
							"mDataProp" : "majors.majorName",
						},{
							"mDataProp" : "classes.classesName",
						},{
							"mDataProp" : "city.name",
						},{
							"mDataProp" : "state",
						}, {
							"sTitle" : "操作",
							"mDataProp" : "stuId",
							"sClass" : "center"
						}, ],
						"aoColumnDefs" : [

						{
							"aTargets" : [ 7 ], // 目标列位置，下标从0开始
							"mRender" : function(data, type, full) {

								return "<a  type=\"post\" href='"+u+"/StudentManager/User!toEditUser.action?stuId="+data+"'> <i class=\"fa fa-lg-2 fa-pencil\"> 编辑或查看详情</i> </a> &nbsp;"



							}

						},{
							"aTargets" : [ 6 ], // 目标列位置，下标从0开始
							"mRender" : function(data, type, full) {

								if(data==1){return "正常"}
								if(data==2){return "休学"}
								if(data==3){return "流失"}
								if(data==4){return "转班"}
								if(data==5){return "待就业中"}
								if(data==6){return "就业"}
								if(data==7){return "二次就业"}
							}

						},{
							"aTargets" : [ 2 ], // 目标列位置，下标从0开始
							"mRender" : function(data, type, full) {

								if(data==1){return "女"}
								if(data==0){return "男"}

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


	
 
    // DataTable
   // var table = $('#dataTables-example').DataTable();
 
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
        uploadUrl: $("body").attr("data-url")+"/StudentManager/User!upload.action", //上传的地址
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
