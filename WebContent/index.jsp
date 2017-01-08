<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>华信智原学生管理系统</title>
<jsp:include page="common/include_CSS.jsp"></jsp:include>
<jsp:include page="common/include_JS.jsp"></jsp:include>
<link href="${pageContext.request.contextPath }/common/index.css"
	rel="stylesheet" />
<!-- Metis Menu Js -->
<script
	src="${pageContext.request.contextPath }/common/static/assets/js/jquery.metisMenu.js"></script>
<!-- Morris Chart Js -->
<script
	src="${pageContext.request.contextPath }/common/static/assets/js/morris/raphael-2.1.0.min.js"></script>
<script
	src="${pageContext.request.contextPath }/common/static/assets/js/morris/morris.js"></script>
<!-- Custom Js -->
<script
	src="${pageContext.request.contextPath }/common/static/assets/js/custom-scripts.js"></script>
<script type="text/javascript">
	function changeFrameHeight() {

		var screenHeight = $(window).height();

		var iframeContentHeight = $("#mainFrame").contents().find("body")
				.height();
		var maxHeight = Math.max(screenHeight, iframeContentHeight);
		$("#mainFrame").height(maxHeight);

	}
</script>

</head>

<body>
	<div id="wrapper">

		<!-- 顶部+消息栏 -->

		<nav class="navbar navbar-default top-navbar" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand"
				href="${pageContext.request.contextPath }/index.jsp">wisezone</a>
		</div>

		<ul class="nav navbar-top-links navbar-right">


			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#" aria-expanded="false"> <i
					class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<shiro:hasRole name="student">
						<li><a
							href="${pageContext.request.contextPath }/StudentManager/User!toEditUser.action?stuId=${userNow.stuId}"
							target="mainFrame"><i class="fa fa-user fa-fw"></i> 个人资料</a></li>
						<li><a
							href="${pageContext.request.contextPath }/QuestionManage/CollectionQuestion!getMyCollectionQuestion.action"
							target="mainFrame"><i class="fa fa-gear fa-fw"></i> 试题收藏夹</a></li>
					</shiro:hasRole>
					<shiro:hasAnyRoles name="teacher,admin">
						<li><a
							href="${pageContext.request.contextPath }/teacher/Teacher!edit.action?teacherId=${teacherNow.teacherId}"
							target="mainFrame"><i class="fa fa-user fa-fw"></i> 个人资料</a></li>
					</shiro:hasAnyRoles>
					<li class="divider"></li>
					<li><a href="${pageContext.request.contextPath }/Quit.action"><i class="fa fa-sign-out fa-fw"></i> 退出</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		</nav>


		<!--/. 左侧菜单  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">

				<li><a href="${pageContext.request.contextPath }/index.jsp">首页</a>
				</li>
				<shiro:hasAnyRoles name="teacher,admin">
					<li><a href="#">基础信息设置<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<shiro:hasRole name="admin">
							<li><a
								href="${pageContext.request.contextPath }/baseManage/city.jsp"
								target="mainFrame">城市信息表</a></li>
								</shiro:hasRole>
							<shiro:hasRole name="admin">
							<li><a
								href="${pageContext.request.contextPath }/baseManage/major.jsp"
								target="mainFrame">专业信息表</a></li>
								</shiro:hasRole>
						
							<li><a
								href="${pageContext.request.contextPath }/baseManage/classes.jsp"
								target="mainFrame">班级信息表</a></li>

						</ul></li>
				</shiro:hasAnyRoles>
				<shiro:hasRole name="admin">
				<li><a href="#">教师信息设置<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a
							href="${pageContext.request.contextPath }/teacher/teacher.jsp"
							target="mainFrame">老师信息表</a></li>
						

					</ul></li>
					</shiro:hasRole>
					<shiro:hasAnyRoles name="teacher,admin">
				<li><a href="#">学员信息设置<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">

						<li><a
							href="${pageContext.request.contextPath }/StudentManager/userinfo.jsp"
							target="mainFrame">学员信息表</a></li>
						<li><a
							href="${pageContext.request.contextPath }/StudentManager/interview.jsp"
							target="mainFrame">学员访谈信息表</a></li>

						<li><a
							href="${pageContext.request.contextPath }/AttendanceManager/Attendance!toAdminChoose.action"
							target="mainFrame">学员出勤信息表</a></li>

						<li><a
							href="${pageContext.request.contextPath }/StudentManager/classtest.jsp"
							target="mainFrame">学员课堂测验成绩表</a></li>
						<li><a
							href="${pageContext.request.contextPath }/StudentManager/stagetest.jsp"
							target="mainFrame">学员阶段考核成绩表</a></li>
						<li><a
							href="${pageContext.request.contextPath }/StudentManager/integratedproject.jsp"
							target="mainFrame">学员综合项目成绩表</a></li>
						<li><a
							href="${pageContext.request.contextPath }/StudentManager/employeeinfo.jsp"
							target="mainFrame">学员就业信息表</a></li>
					</ul></li>
</shiro:hasAnyRoles>
					<shiro:hasAnyRoles name="teacher,admin">
				<li><a href="#">试题信息设置<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a
							href="${pageContext.request.contextPath }/StudyGame/Check!toCheckJsp.action"
							target="mainFrame">练习题关卡表</a></li>

						<li><a
							href="${pageContext.request.contextPath }/QuestionManage/recordHistory.jsp"
							target="mainFrame">学员闯关记录表</a></li>

						<li><a
							href="${pageContext.request.contextPath }/QuestionManage/CollectionQuestion.jsp"
							target="mainFrame">试题收藏夹</a></li>
					</ul></li>
							</shiro:hasAnyRoles>
			
			</ul>

		</div>

		</nav>

		<!-- /. 主页面  -->
		<div id="page-wrapper">

			<iframe src="${pageContext.request.contextPath }/indexBody.jsp"
				id="mainFrame" name="mainFrame" scrolling="no" frameborder="0"
				width="100%" height="auto" onload="changeFrameHeight()"> </iframe>

			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->


</body>

</html>