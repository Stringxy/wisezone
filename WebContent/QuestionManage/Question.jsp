<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../common/include_CSS.jsp"></jsp:include>
<jsp:include page="../common/include_JS.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/fileinput/css/fileinput.min.css" />
<!-- TABLE STYLES-->
<link
	href="${pageContext.request.contextPath }/common/static/assets/js/dataTables/dataTables.bootstrap.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/QuestionManage/Question.js"></script>
<!-- DATA TABLE SCRIPTS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/common/static/assets/js/dataTables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/common/static/assets/js/dataTables/dataTables.bootstrap.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/fileinput/js/fileinput.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/fileinput/js/fileinput.min.js"></script>


</head>
<body data-url="${pageContext.request.contextPath}">
	<!-- /. NAV SIDE  -->

	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					试题信息设置 <small>关卡试题信息表</small>
				</h1>
			</div>
		</div>
		<!-- /. ROW  -->

		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-heading">关卡试题信息表
										<div class="col-lg-5  pull-right" >
				<a type="button" onclick="add()" class="btn btn-info btn-sm">
						添加试题
						</a>
					</div>
					</div>
					
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>试题编号</th>
										<th>所属专业</th>
										<th>所属阶段</th>
										<th>题目</th>
										<th>试题类型</th>
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
	
<!-- /. excel  -->
	<div class="col-md-9 col-sm-12 col-xs-12">
		<div class="panel panel-default">
			<div class="panel-heading">Excel批量导入</div>
			<div class="panel-body">

				<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath }/StudyGame/Question!downloadTemp.action">下载Excel模板</a>
				<br><br>
				 <label
					class="control-label">请选择要导入的Excel文件：</label> <input type="file"
					name="uploadFiles" id="uploadfile" multiple class="file-loading" />

			</div>
		</div>
	</div>
	</div>

</body>
</html>