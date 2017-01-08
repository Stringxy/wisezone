$(function() {
	initUpload();

	$('#fm')
			.bootstrapValidator(
					{
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok', // 验证成功显示的图标
							invalid : 'glyphicon glyphicon-remove', // 验证失败图标
							validating : 'glyphicon glyphicon-refresh' // 验证中...
						},
						// 设定要验证的字段
						fields : {

							loginName : {

								validators : {
									// 非空验证
									notEmpty : {
										message : '登录名不能为空'
									},

									remote : {
										url : $("body").attr("data-url")
												+ "/teacher/Teacher!valid.action?teacherId="
												+ $("#teacherId")
														.attr("userId"),
										message : '该登录名已被占用,请加数字或字母进行区分！',
										dataType : "json",
										delay : 2000
									},

								}
							},
							teacherName : {

								validators : {
									// 非空验证
									notEmpty : {
										message : '姓名不能为空'
									}
								}
							}
							,
							loginPwd : {

								validators : {
									// 非空验证
									notEmpty : {
										message : '密码不能为空'
									}
								}
							}
							,
							identity : {

								validators : {
									// 非空验证
									notEmpty : {
										message : '身份证号不能为空'
									}
								}
							},
							mobile : {

								validators : {
									// 非空验证
									notEmpty : {
										message : '联系电话不能为空'
									}
								}
							},
							address : {

								validators : {
									// 非空验证
									notEmpty : {
										message : '地址不能为空'
									}
								}
							}
						// 以下可以自己再加东西

						}
					});
});

function initUpload() {

	$("#uploadfile")
			.fileinput(
					{
						language : 'zh', // 设置语言
						uploadUrl : $("body").attr("data-url")
								+ "/StudentManager/Portrait!uploadPortrait.action?type=teacher&teacherName="
								+ $("#loginName").val(), // 上传的地址
						allowedFileExtensions : [ 'jpg', 'png', 'bmp' ],// 接收的文件后缀
						// uploadExtraData:{"id": 1, "fileName":'123.mp3'},
						uploadAsync : true, // 默认异步上传
						showUpload : true, // 是否显示上传按钮
						showRemove : true, // 显示移除按钮
						showPreview : true, // 是否显示预览
						showCaption : false,// 是否显示标题
						browseClass : "btn btn-primary", // 按钮样式
						dropZoneEnabled : false,// 是否显示拖拽区域
						minImageWidth : 50, // 图片的最小宽度
						minImageHeight : 50,// 图片的最小高度
						maxImageWidth : 1000,// 图片的最大宽度
						maxImageHeight : 1000,// 图片的最大高度
						maxFileSize : 102400,// 单位为kb，如果为0表示不限制文件大小
						// minFileCount: 0,
						maxFileCount : 1, // 表示允许同时上传的最大文件个数
						enctype : 'multipart/form-data',
						validateInitialCount : true,
						previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
						msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
						slugCallback : function(filename) {
							return filename.replace('(', '_').replace(']', '_');
						}
					});
	// 这是提交完成后的回调函数
	$("#uploadfile").on("fileuploaded",
			function(event, data, previewId, index) {
				console.log(data)
				alert(data.response.result);
				location.reload();
			});

}

function save() {
	var teacherId = $("#teacherId").attr("userId");
	$.ajax({
		cache : true,
		type : "POST",
		url : $("body").attr("data-url") + "/teacher/Teacher!save.action",
		data : $('#fm').serialize() + "&teacherId=" + teacherId,
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(json) {
			console.log(result)
			var result = eval('(' + json + ')');
			if (result.success) {
				alert(result.message);

				location.reload();
			} else {
				alert(result.message);
			}
		}
	});
}

function goBack() {
	history.back();
}