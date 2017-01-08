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
	src="${pageContext.request.contextPath }/QuestionManage/allQuestion.js"></script>

</head>
<body data-url="${pageContext.request.contextPath}">
<!-- /. NAV SIDE  -->

	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					试题信息设置 <small>试题库信息表</small>
				</h1>
			</div>
		</div>
		<!-- /. ROW  -->

		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-heading">试题库信息表
										<div class="col-lg-5  pull-right" >
				<a type="button" onclick="save()" class="btn btn-info btn-sm">
						添加选中试题
						</a>
					</div>
					</div>
					
					<div class="panel-body">
						<div class="table-responsive">
							<table class="display"
								id="dataTables-example" cellspacing="0" width="100%">
								<thead>
									<tr>
									<th>全选<input type="checkbox" class="checkall" /></th>
										<th>试题编号</th>
										<th>所属专业</th>
										<th>所属阶段</th>
										<th>题目</th>
										<th>试题类型</th>
										<th>操作</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
									<th></th>
										<th><input type="text" placeholder="按编号查询" /></th>
										<th><input type="text" placeholder="按专业查询" /></th>
										<th><input type="text" placeholder="按阶段查询" /></th>
										<th><input type="text" placeholder="按题目查询" /></th>
										<th><input type="text" placeholder="按类型查询" /></th>
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
</div>
</body>
</html>