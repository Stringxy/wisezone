<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/common/static/assets/js/dataTables/jquery.dataTables.min.css" />
<!-- DATA TABLE SCRIPTS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/common/static/assets/js/dataTables/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/StudentManager/userinfo.js"></script>
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
					学生信息设置 <small>>学生信息表</small>
				</h1>
			</div>
		</div>
		<!-- /. ROW  -->

		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-heading">
						学生信息表
						<div class="col-lg-5  pull-right">
							<a type="button"
								href="${pageContext.request.contextPath}/StudentManager/User!toEditUser.action?stuId=0"
								class="btn btn-info btn-sm"> 点击添加 </a>
						</div>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="display"
								id="dataTables-example" cellspacing="0" width="100%">
								<thead>
									<tr>
										
										<th>学号</th>
										<th>学生姓名</th>
										<th>性别</th>
										<th>所属专业</th>
										<th>班级</th>
										<th>城市</th>
										<th>状态</th>
										<th>操作</th>

									</tr>
								</thead>
								<tfoot>
									<tr>
										
										<th><input type="text" placeholder="按学号查询" /></th>
										<th><input type="text" placeholder="按姓名查询" /></th>
										<th></th>
										<th><input type="text" placeholder="按专业查询" /></th>
										<th><input type="text" placeholder="按班级查询" /></th>
										<th><input type="text" placeholder="按城市查询" /></th>
										<th><input type="text" placeholder="按状态查询" /></th>
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
	<div class="col-md-9 col-sm-12 col-xs-12">
		<div class="panel panel-default">
			<div class="panel-heading">Excel批量导入</div>
			<div class="panel-body">

				<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath }/StudentManager/User!downloadTemp.action">下载Excel模板</a>
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