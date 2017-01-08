

$(function(){
	initUpload();
	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd',
		minView: "month", 
		todayBtn:  1,
	    autoclose: 1
	});
	
	
	$('#fm').bootstrapValidator(
			{
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok', // 验证成功显示的图标
					invalid : 'glyphicon glyphicon-remove', // 验证失败图标
					validating : 'glyphicon glyphicon-refresh' // 验证中...
				},
				// 设定要验证的字段
				fields : {

					stuName: {

						validators : {
							// 非空验证
							notEmpty : {
								message : '姓名不能为空'
							},

							remote : {
								url : $("body").attr("data-url")
										+ "/StudentManager/User!valid.action?stuId="+$("#userId").attr("userId"),
								message : '该学生名称已被占用,请加数字或字母进行区分！',
								dataType:"json",
								delay :  2000
							},


						}
					},
					// 以下可以自己再加东西

				}
			});
});



function initUpload(){

	$("#uploadfile").fileinput({   
		language: 'zh', //设置语言
        uploadUrl: $("body").attr("data-url")+"/StudentManager/Portrait!uploadPortrait.action?type=student&stuName="+$("#stuName").val(), //上传的地址
        allowedFileExtensions: ['jpg','png','bmp'],//接收的文件后缀
        //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : true, //是否显示预览
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式     
        dropZoneEnabled: false,//是否显示拖拽区域
        minImageWidth: 50, //图片的最小宽度
        minImageHeight: 50,//图片的最小高度
        maxImageWidth: 1000,//图片的最大宽度
        maxImageHeight: 1000,//图片的最大高度
        maxFileSize: 102400,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount: 1, //表示允许同时上传的最大文件个数
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


function save(){
	var stuId=$("#userId").attr("userId");
	$.ajax({
		cache: true,
		type: "POST",
		url:$("body").attr("data-url")+"/StudentManager/User!save.action",
		data:$('#fm').serialize()+"&stuId="+stuId,
		async: false,
		error: function(request) {
			alert("Connection error");
		},
		success: function(json) {
			console.log(result)
			var result = eval('(' + json + ')');
				if(result.success){
					alert(result.message);
					history.back();
					
				}else{
					alert(result.message);
				}
		}
	});
}

function goBack(){
	history.back();
}