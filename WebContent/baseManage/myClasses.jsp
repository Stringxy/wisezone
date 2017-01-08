<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级信息表</title>

<jsp:include page="../common/include_CSS.jsp"></jsp:include>
<jsp:include page="../common/include_JS.jsp"></jsp:include>
<!-- TABLE STYLES-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/common/static/assets/js/dataTables/jquery.dataTables.min.css" />
<!-- DATA TABLE SCRIPTS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/common/static/assets/js/dataTables/jquery.dataTables.min.js"></script>
<!-- 日期插件的css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/datetimepicker/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/datetimepicker/css/bootstrap-datetimepicker.min.css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath }/baseManage/myClasses.js"></script>
<!-- 日期插件的js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body data-url="${pageContext.request.contextPath}">
	<!-- /. NAV SIDE  -->

	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					教师信息设置 <small>所带班级信息表</small>
				</h1>
			</div>
		</div>
		<!-- /. ROW  -->

		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-heading">班级信息表
					<a type="button" onclick="add()" class="btn btn-default">
								添加班级 </a>
					
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="display"
								id="dataTables-example" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>班级编号</th>
										<th>班级名称</th>
										<th>开始时间</th>
										<th>结束时间</th>
										<th>专业名称</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
							
						</div>

					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
		</div>
		<!-- /. ROW  -->
	</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="permissionModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="permissionModalLabel">修改班级信息</h4>
				</div>
				<div class="modal-body">
					<!-- context -->
					<form method="post" id="fm">
						<div class="form-group">
							<label class="col-lg-3 control-label">班级名称</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" name="classesName"
									id="classesName" />
							</div>

							<br /> <label class="col-lg-3 control-label" for="startTime">开班时间:</label>
							<div class="col-lg-9">
								<input size="16" type="text" value="2012-06-15" readonly
									class="form_datetime" id="startTime" name="startTime">
							</div>
							<br /> <label class="col-lg-3 control-label" for="endTime">结课时间:</label>
							<div class="col-lg-9">
								<input size="16" type="text" value="2012-06-15" readonly
									class="form_datetime" id="endTime" name="endTime">
							</div>
							<br /> <label class="col-lg-3 control-label" for="majorId">所属专业</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" name="majorId"
									id="majorId"  readonly="readonly"/>
							</div>
							<br />
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="save()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>


</body>
</html>