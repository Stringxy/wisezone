<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>城市信息表</title>

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
	src="${pageContext.request.contextPath }/baseManage/city.js"></script>



</head>
<body data-url="${pageContext.request.contextPath}">
	<!-- /. NAV SIDE  -->

	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					基础信息设置 <small>>城市信息表</small>
				</h1>
			</div>
		</div>
		<!-- /. ROW  -->

		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-heading">城市信息表 

					
					
					<s:actionerror/>
				

				<div class="col-lg-5  pull-right" >
				<a type="button" href="${pageContext.request.contextPath}/baseManage/City!editCity.action?id=0" class="btn btn-info btn-sm">

						点击添加 
						</a>
					</div>
						</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>城市编号</th>
										<th>城市名称</th>
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
		<!-- /. ROW  -->
	</div>



</body>

</html>