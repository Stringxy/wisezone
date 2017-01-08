function save(){
	$.ajax({
		cache: true,
		type: "POST",
		url:$("body").attr("data-url")+"/baseManage/City!saveCity.action",
		data:$('#form1').serialize(),
		async: false,
		error: function(request) {
			alert("网络连接错误！");
		},
		success: function(json) {
			var result = eval('(' + json + ')');
				if(result.success){
					alert(result.message);	
				}else{
					alert(result.message);	
				}
				history.back();
			
		}
	});
}