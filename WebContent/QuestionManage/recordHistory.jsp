<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../common/include_CSS.jsp"></jsp:include>
<jsp:include page="../common/include_JS.jsp"></jsp:include>
<!-- TABLE STYLES-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/common/static/assets/js/dataTables/jquery.dataTables.min.css" />
<!-- DATA TABLE SCRIPTS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/common/static/assets/js/dataTables/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/QuestionManage/recordHistory.js"></script>
</head>
<body data-url="${pageContext.request.contextPath}">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					试题信息设置 <small>学员闯关记录表</small>
				</h1>
			</div>
		</div>
		<!-- /. ROW  -->

		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-heading">
						学员闯关记录表
					
					</div>

					<div class="panel-body">
						<div class="table-responsive">
							<table class="display"
								id="dataTables-example" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>记录编号</th>
										<th>学生姓名</th>
										<th>关卡明细名称</th>
										<th>创建日期</th>
										<th>正确数量</th>
										<th>错误数量</th>
										<th>过关情况</th>
										<th>操作</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th><input type="text" placeholder="按编号查询" /></th>
										<th><input type="text" placeholder="按学生查询" /></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th><input type="text" placeholder="按是否过关查询" /></th>
										<th></th>
									</tr>
								</tfoot>
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
					<h4 class="modal-title" id="permissionModalLabel">学员闯关明细</h4>
				</div>
				<div class="modal-body">
					<!-- context -->
					<form method="post" id="fm">
						<div class="form-group" id="formbody">
							
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