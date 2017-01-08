$(function() {
	
	initTable();
	initUpload();
	
	$('#fm').bootstrapValidator(
			{
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok', // 验证成功显示的图标
					invalid : 'glyphicon glyphicon-remove', // 验证失败图标
					validating : 'glyphicon glyphicon-refresh' // 验证中...
				},
				// 设定要验证的字段
				fields : {

					questionName : {

						validators : {
							// 非空验证
							notEmpty : {
								message : $("#questionName").parent().parent()
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
var questionid;

function initTable() {
	var u=$("body").attr("data-url");
	$("#dataTables-example").dataTable(
			{
				"bFilter" : false,// 去掉搜索框
				// "bAutoWidth": true, //自适应宽度
				"sPaginationType" : "full_numbers",// 指定分页器风格
				// "sAjaxDataProp" : "aData",
				"bDestroy" : true,
				 "bSort": false,
				"bProcessing" : true,
				"sAjaxSource" : $("body").attr("data-url")
						+ "/StudyGame/Question!searchByDetail.action",
				"bServerSide" : true,
				"bScrollCollapse" : true,
				"aoColumns" : [ {
					"mDataProp" : "questionId",
				}, {
					"mDataProp" : "major.majorName",
				},
				{
					"mDataProp" : "majorstage.stageName",
				},
				{
					"mDataProp" : "questionName",
				},
				{
					"mDataProp" : "questionType",
				},
				{
					"sTitle" : "操作",
					"mDataProp" : "questionId",
					"sClass" : "center"
				}, ],
				"aoColumnDefs" : [

									{
										"aTargets" : [ 5 ], // 目标列位置，下标从0开始
										"mRender" : function(data, type, full) {

											return "<a  onclick=\"del(this)\" > <i class=\"fa fa-lg-2 fa-trash-o\"> 移除</i></a>&nbsp;"
											+"<a href='"+u+"/StudyGame/Question!toQuestionDetailJsp.action?questionId="+data+"'  > <i class=\"fa fa-lg-2 fa-eye\"> 查看试题详情</i> </a>"

										}
										

									},{
										"aTargets" : [ 4 ], // 目标列位置，下标从0开始
										"mRender" : function(data, type, full) {
											if(data==1){return "单选"}
											if(data==2){return "多选"}
											if(data==3){return "判断题"}
											if(data==4){return "简答题"}
											if(data==5){return "编程题"}
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



function del(obj){
	questionid=$(obj).parent().parent().children().eq(0).text();
	$.ajax({
		cache: true,
		type: "POST",
		url:$("body").attr("data-url")+"/StudyGame/Question!del.action",
		data:"questionId="+questionid,
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

function save(){
	$.ajax({
		cache: true,
		type: "POST",
		url:$("body").attr("data-url")+"/StudyGame/Question!save.action",
		data:$('#fm').serialize()+"&questionId="+questionid,
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

function add(){
	window.location.href=$("body").attr("data-url")
	+"/QuestionManage/allQuestion.jsp";
}

function getDetail(obj){
	questionid=$(obj).parent().parent().children().eq(0).text();
	var majorid = $(obj).attr("majorid");

	var majorstageid= $(obj).attr("stageid");
	var questionName = $(obj).parent().parent().children().eq(3).text();
	var type = $(obj).parent().parent().children().eq(4).text();
	var typeId;
	if(type=="单选题"){
		typeId=1;
	}
	if(type=="多选题"){typeId=2}
	if(type=="判断题"){typeId=3}
	if(type=="简答题"){typeId=4}
	if(type=="编程题"){typeId=5}
	var answerA=$(obj).parent().parent().children().eq(5).text();
	var answerB=$(obj).parent().parent().children().eq(6).text();
	var answerC=$(obj).parent().parent().children().eq(7).text();
	var answerD=$(obj).parent().parent().children().eq(8).text();
	var rightanswer=$(obj).parent().parent().children().eq(9).text();
	var analysis=$(obj).parent().parent().children().eq(9).text();
	$("#questionName").attr("value", questionName);
	$("#majorId").find("option[value="+majorid+"]").attr("selected","selected");
	$("#stageId").find("option[value="+majorstageid+"]").attr("selected","selected");
	$("#questionType").find("option[value="+typeId+"]").attr("selected","selected");
	$("#answerA").attr("value", answerA);
	$("#answerB").attr("value", answerB);
	$("#answerC").attr("value", answerC);
	$("#answerD").attr("value", answerD);
	$("#rightAnswer").attr("value", rightanswer);
	$("#analysis").attr("value", analysis);


	$("#myModal").modal('show');
}

function initUpload(){

	$("#uploadfile").fileinput({   
		language: 'zh', //设置语言
        uploadUrl: $("body").attr("data-url")+"/StudyGame/Question!upload.action", //上传的地址
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
