<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择班级</title>
<jsp:include page="../common/include_CSS.jsp"></jsp:include>
<jsp:include page="../common/include_JS.jsp"></jsp:include>
<!-- 日期插件的css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/datetimepicker/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/datetimepicker/css/bootstrap-datetimepicker.min.css" />
<!-- 日期插件的js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/AttendanceManager/chooseClass.js"></script>
</head>
<body>
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					考勤记录 <small>>选择班级</small>
				</h1>
			</div>
		</div>
		<div class="col-md-9 col-sm-12 col-xs-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					当前日期：
					<span id="current-time"></span>
				</div>
				<div class="panel-body">

					<label class="col-lg-3 control-label" for="classes">选择班级:</label> <select
						name="classes">
						<c:if test="${empty myClasses}">
						<option >暂无带班信息</option>
						</c:if>
						<c:forEach items="${myClasses}" var="classes">
							<option value="${classes.classesId}">${classes.classesName}</option>
						</c:forEach>
					</select> <br />

	<br/>
					<button class="btn btn-primary btn-lg" type="button"
						onclick="toAddAttendance()">开始打考勤</button>
				</div>
			</div>
		</div>
	</div>


</body>
</html>