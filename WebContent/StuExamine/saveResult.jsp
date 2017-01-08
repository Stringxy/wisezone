<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交问卷调查结果成功</title>
<jsp:include page="../common/include_CSS.jsp"></jsp:include>
<jsp:include page="../common/include_JS.jsp"></jsp:include>
<style type="text/css">
#back {
	background-color: #caffff;
}
#changeLength{
	margin-top: 50px
}

</style>

</head>
<body id="back">
	<div class="col-md-12">
		<h2 class="page-header">
			WISEZONE <small>调查问卷</small>
		</h2>
	</div>
	<div style="margin-left: 100px">
		<img
			src="${pageContext.request.contextPath }/assets/img/backgrounds/submitSuc.jpg"
			alt="submitSuccess" class="img-circle img-responsive">
		<div class="col-md-12" id="changeLength">
			<h3 class="page-header">
				骚年,谢谢你的参与！！！<small>再去试题闯关挑战一下</small>
			</h3>
			<a href="#" class="btn btn-primary btn-lg active" role="button"  >进入试题闯关</a>
			<a href="${pageContext.request.contextPath }/index.action" class="btn btn-default btn-lg active" role="button">返回首页</a>
		</div>
	</div>



</body>
</html>