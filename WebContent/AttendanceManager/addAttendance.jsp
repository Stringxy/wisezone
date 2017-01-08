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
<link
	href="${pageContext.request.contextPath }/common/static/assets/js/dataTables/dataTables.bootstrap.css"
	rel="stylesheet" />
<!-- DATA TABLE SCRIPTS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/common/static/assets/js/dataTables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/common/static/assets/js/dataTables/dataTables.bootstrap.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/AttendanceManager/addAttendance.js"></script>
</head>
<body data-url="${pageContext.request.contextPath}">
	<!-- /. NAV SIDE  -->

	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					考勤记录
				</h1>
			</div>
		</div>
		<!-- /. ROW  -->
<form id="fm">
		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-heading" id="current-time">
					
						</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>学生编号</th>
										<th>学生姓名</th>
										<th>所属班级</th>
										<th>上午</th>
										<th>下午</th>
										<th >操作</th>

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
		</form>
		<!-- /. ROW  -->
	</div>



</body>

</html>