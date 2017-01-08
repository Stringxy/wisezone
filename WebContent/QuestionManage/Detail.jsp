<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../common/include_CSS.jsp"></jsp:include>

<jsp:include page="../common/include_JS.jsp"></jsp:include>
<!-- TABLE STYLES-->
<link
	href="${pageContext.request.contextPath }/common/static/assets/js/dataTables/dataTables.bootstrap.css"
	rel="stylesheet" />
<!-- DATA TABLE SCRIPTS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/common/static/assets/js/dataTables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/common/static/assets/js/dataTables/dataTables.bootstrap.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/QuestionManage/CheckPointDetail.js"></script>
</head>
<body data-url="${pageContext.request.contextPath}">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					试题信息设置 <small>关卡明细表</small>
				</h1>
			</div>
		</div>
		<!-- /. ROW  -->

		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-heading">
						关卡明细表
						<div class="col-lg-5  pull-right">
							<a type="button" onclick="add()" class="btn btn-info btn-sm">
								点击添加 </a>
						</div>
					</div>

					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>关卡明细编号</th>
										<th>关卡明细名称</th>
										<th>过关正确题目数量</th>
										<th>所属关卡</th>
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
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria -hidden="true">&times;</button>
					<h4 class="modal-title" id="permissionModalLabel">修改关卡明细信息</h4>
				</div>
				<div class="modal-body">
					<!-- context -->
					<form method="post" id="fm">
						<div class="form-group">
							<label class="control-label">关卡明细名称</label>
							
								<input type="text" class="form-control" name="checkName"
									id="checkName" />
							

							 <label class="control-label" for="checkId">所属关卡</label>
							
							<input type="text" class="form-control" name="checkId"
									id="checkId"  readonly="readonly" value="${checkNow.checkName}"/>

							
<br/>
							<label class="control-label">过关正确题目数量</label>
							
								<input type="text" class="form-control" name="correctNum"
									id="correctNum" />
							


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