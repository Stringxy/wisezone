<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<script
	src="${pageContext.request.contextPath }/AttendanceManager/AdminChoose.js"></script>
</head>
<body data-url="${pageContext.request.contextPath}">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					学生信息设置 <small>学生出勤信息表>选择班级日期</small>
				</h1>
			</div>
		</div>

		<div class="col-md-9 col-sm-12 col-xs-12">
			<div class="panel panel-default">
				<div class="panel-heading">查询单日考勤记录</div>
				<div class="panel-body">


					<label class="control-label" for="classesId">选择班级：</label> <select
						class="form-control" name="classesId" id="classesId"
						style="display: inline; width: auto">

						<c:forEach items="${myClasses}" var="classes">
							<option value="${classes.classesId}"
								<c:if test="${user.classes.classesId==classes.classesId}">
     									selected="selected"
									</c:if>>${classes.classesName}</option>
						</c:forEach>
					</select> <br /> <br /> <label class="control-label" for="createtime">选择指定日期:</label>
					<br />
					<div class="col-lg-9">
						<input size="16" type="text" value="点击选择日期" readonly
							class="form_datetime" id="createtime" name="createtime">
					</div>
					<p>
						<button class="btn btn-primary btn-lg" type="button"
							onclick="search()">查看记录</button>
					</p>
				</div>
			</div>
		</div>

		<div class="col-md-9 col-sm-12 col-xs-12">
			<div class="panel panel-default">
				<div class="panel-heading">生成考勤月度报表</div>
				<div class="panel-body">
					
						<label class="control-label" for="classesIdForMonth">选择班级：</label> <select
							class="form-control" name="classesIdForMonth" id="classesIdForMonth"
							style="display: inline; width: auto">

							<c:forEach items="${myClasses}" var="classes">
								<option value="${classes.classesId}"
									<c:if test="${user.classes.classesId==classes.classesId}">
     									selected="selected"
									</c:if>>${classes.classesName}</option>
							</c:forEach>
						</select><br /> <br /> <label class="control-label" for="month">选择年月:</label>
						<br /> <div class="col-lg-9">
						<input size="16" type="text" value="点击选择日期" readonly
							class="form_datetime" id="month" name="month">
					</div><button class="btn btn-primary btn-lg" onclick="downloadMonth()">生成月报表</button>

					<s:actionerror />


				</div>
			</div>
		</div>

		<div class="col-md-9 col-sm-12 col-xs-12">
			<div class="panel panel-default">
				<div class="panel-heading">生成考勤周度报表</div>
				<div class="panel-body">
				
						<label class="control-label" for="classesIdForWeek">选择班级：</label> <select
							class="form-control" name="classesIdForWeek" id="classesIdForWeek"
							style="display: inline; width: auto">

							<c:forEach items="${myClasses}" var="classes">
								<option value="${classes.classesId}"
									<c:if test="${user.classes.classesId==classes.classesId}">
     									selected="selected"
									</c:if>>${classes.classesName}</option>
							</c:forEach>
						</select><br /> <br /> <label class="control-label" for="monday">选择年月:</label>
						<br /> <div class="col-lg-9">
						<input size="16" type="text" value="点击选择日期" readonly
							class="form_datetime" id="monday" name="monday">
					</div>
						
						<button class="btn btn-primary btn-lg" role="button"
							onclick="downloadWeek()">生成周报表</button>
					

				</div>
			</div>
		</div>
	
		</div>
</body>
</html>