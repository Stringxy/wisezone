function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : $("body").attr("data-url")
				+ "/StudyGame/RecordHistory!addRecordHistory.action",
		data : $('#fm').serialize(),
		async : false,
		error : function(request) {
			alert("网络连接错误！");
		},
		success : function(json) {
			var result = eval('(' + json + ')');
			if (result.success) {
				alert(result.message);
			} else {
				alert(result.message);
			}
			history.back();
		}
	});
}

function collectionQuestion(obj){
	$.ajax({
		cache : true,
		type : "POST",
		url : $("body").attr("data-url")
				+ "/QuestionManage/CollectionQuestion!addCollectionQuestion.action",
		data : "questionId="+obj,
		async : false,
		error : function(request) {
			alert("网络连接错误！");
		},
		success : function(json) {
			var result = eval('(' + json + ')');
			if (result.success) {
				alert(result.message);
			} else {
				alert(result.message);
			}
			
		}
	});
}