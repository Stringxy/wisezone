<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index</title>
<jsp:include page="common/include_CSS.jsp"></jsp:include>
<jsp:include page="common/include_JS.jsp"></jsp:include>
</head>
<body>
	<!-- /. 学生闯关 -->
	<shiro:hasRole name="student">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
					<h1>
						欢迎
						<shiro:principal />
						同学！
					</h1>

					<p>
						<a class="btn btn-primary btn-lg" role="button"
							href="${pageContext.request.contextPath }/StudyGame/Check!getCheck.action">开始闯关</a>
					</p>
				</div>
			</div>
		</div>
	</shiro:hasRole>
	<!-- /. 学生闯关  -->

	<!-- /. 教师打考勤 -->
	<shiro:hasAnyRoles name="teacher,admin">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
					<h1>
						欢迎
						<shiro:principal />
						老师！
					</h1>
<hr>
<br/>
					<div class="col-md-9 col-sm-12 col-xs-12">
						<div class="panel panel-default">
							<div class="panel-heading">打考勤</div>
							<div class="panel-body">
								<p>
									<a class="btn btn-success btn-lg" role="button"
										href="${pageContext.request.contextPath }/AttendanceManager/Attendance!toChoose.action">进入当日考勤记录</a>
								</p>

							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</shiro:hasAnyRoles>
	<!-- /. 教师打考勤  -->
</body>
</html>