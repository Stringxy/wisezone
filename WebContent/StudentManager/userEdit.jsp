<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../common/include_CSS.jsp"></jsp:include>
<jsp:include page="../common/include_JS.jsp"></jsp:include>
<!-- 日期插件的css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/datetimepicker/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/datetimepicker/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/fileinput/css/fileinput.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/fileinput/js/fileinput.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/fileinput/js/fileinput.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/fileinput/js/locales/zh.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/StudentManager/userEdit.js"></script>
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
					学生信息设置<small>>修改学生信息</small>
					
				</h1>
			</div>
		</div>
		<!-- /. ROW  -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">学生资料</div>
					<div class="panel-body">
						<form id="fm">
							<div class="form-group">
								<div class="col-md-6 col-sm-6">



									<label class="control-label">学号</label>
									<div class="col-lg-9">
										<input class="form-control" name="stuNo" id="stuNo"
											value="${user.stuNo}" />
									</div>
									<label class="control-label">学生姓名</label>
									<div class="col-lg-9">
										<input class="form-control" name="stuName" id="stuName"
											value="${user.stuName}" />
									</div>
									<label class="control-label">登录密码</label>
									<div class="col-lg-9">
										<input class="form-control" name="password" id="password"
											value="${user.password}" />
									</div>
									<label class="control-label">性别</label>
									<div class="col-lg-9">
										<input type="radio" name="sex" id="sex1" value="0"
											<c:if test="${user.sex==0}">
     												 checked="checked"
									</c:if>>男
										<input type="radio" name="sex" id="sex2" value="1"
											<c:if test="${user.sex==1}">
     												 checked="checked"
									</c:if>>女
									</div>
									<label class="control-label">入学日期</label>
									<div class="col-lg-9">
										<input size="20" type="text"
											<c:if test="${empty user}">value="请选择日期"</c:if>
											<c:if test="${!empty user}">value="${user.joinDate}"</c:if>
											readonly class="form_datetime" id="joinTime" name="joinTime" />
									</div>
									<label class="control-label">身份证号</label>
									<div class="col-lg-9">
										<input class="form-control" name="identity" id="identity"
											value="${user.identity}" />
									</div>
									<label class="control-label">出生日期</label>
									<div class="col-lg-9">
										<input size="20" type="text"
											<c:if test="${empty user}">value="请选择日期"</c:if>
											<c:if test="${!empty user}">value="${user.birthday}"</c:if>
											readonly class="form_datetime" id="birthTime"
											name="birthTime" />
									</div>
									<label class="control-label">毕业院校</label>
									<div class="col-lg-9">
										<input class="form-control" name="graduate" id="graduate"
											value="${user.graduate}" />
									</div>
									<label class="control-label">专业</label>
									<div class="col-lg-9">
										<input class="form-control" name="major" id="major"
											value="${user.major}" />
									</div>
									<label class="control-label" for="educationId">学历</label>
									<div class="col-lg-9">
										<select class="form-control" name="educationId"
											id="educationId" style="display: inline">

											<option value="1"
												<c:if test="${user.educationId==1}">
     									selected="selected"
									</c:if>>大专以下</option>
											<option value="2"
												<c:if test="${user.educationId==2}">
     									selected="selected"
									</c:if>>大专</option>
											<option value="3"
												<c:if test="${user.educationId==3}">
     									selected="selected"
									</c:if>>本科</option>
											<option value="4"
												<c:if test="${user.educationId==4}">
     									selected="selected"
									</c:if>>研究生及以上</option>

										</select>
									</div>
									<label class="control-label">关卡进度</label>
									<div class="col-lg-9">
										<input class="form-control" name="checkName" id="checkName"
											value="${user.detail.checkName}" />
									</div>
									
									<label class="control-label">电子邮箱</label>
									<div class="col-lg-9">
										<input class="form-control" name="email" id="email"
											value="${user.email}" />
									</div>
									<label class="control-label">联系电话</label>
									<div class="col-lg-9">
										<input class="form-control" name="mobile" id="mobile"
											value="${user.mobile}" />
									</div>
									<label class="control-label">QQ</label>
									<div class="col-lg-9">
										<input class="form-control" name="qq" id="qq"
											value="${user.qq}" />
									</div>
									
									<label class="control-label" for="state">用户状态</label>
									<div class="col-lg-9">
										<select class="form-control" name="state" id="state"
											style="display: inline">

											<option value="1"
												<c:if test="${user.state==1}">
     									selected="selected"
									</c:if>>正常</option>
											<option value="2"
												<c:if test="${user.state==2}">
     									selected="selected"
									</c:if>>休学</option>
											<option value="3"
												<c:if test="${user.state==3}">
     									selected="selected"
									</c:if>>流失</option>
											<option value="4"
												<c:if test="${user.state==5}">
     									selected="selected"
									</c:if>>转班</option>
											<option value="5"
												<c:if test="${user.state==5}">
     									selected="selected"
									</c:if>>待就业中</option>
											<option value="6"
												<c:if test="${user.state==6}">
     									selected="selected"
									</c:if>>就业</option>
											<option value="7"
												<c:if test="${user.state==7}">
     									selected="selected"
									</c:if>>二次就业</option>
										</select>
									</div>


								</div>




								<div class="col-md-6 col-sm-6">
									<label class="control-label">用户头像</label>
									<div id="userId" userId="${user.stuId}"></div>

										<img alt=""
											src="${pageContext.request.contextPath}${user.portrait}"
											style="width: 290px; height: 290px;border: 5px solid #4D8AB3;">
								
									<br /> <label class="control-label">上传头像：</label> <input
										type="file" name="uploadFiles" id="uploadfile"
										class="file-loading" />
									
									<!--  -->
									<label class="control-label" for="majorId">所属专业</label> <select
										class="form-control" name="majorId" id="majorId"
										style="display: inline; width: auto">

										<c:forEach items="${majors}" var="major">
											<option value="${major.majorId}"
												<c:if test="${user.majors.majorId==major.majorId}">
     									selected="selected"
									</c:if>>${major.majorName}</option>
										</c:forEach>
									</select>
									<br/>
									 <label class="control-label" for="classesId">所属班级</label> <select
										class="form-control" name="classesId" id="classesId"
										style="display: inline; width: auto">

										<c:forEach items="${allClasses}" var="classes">
											<option value="${classes.classesId}"
												<c:if test="${user.classes.classesId==classes.classesId}">
     									selected="selected"
									</c:if>>${classes.classesName}</option>
										</c:forEach>
									</select> <br /> 
									<label class="control-label" for="classesId">所属城市</label> <select
										class="form-control" name="cityId" id="cityId"
										style="display: inline; width: auto">

										<c:forEach items="${cities}" var="city">
											<option value="${city.id}"
												<c:if test="${user.city.id==city.id}">
     									selected="selected"
									</c:if>>${city.name}</option>
										</c:forEach>
									</select> 
									<br/>
									<label class="control-label">家庭住址</label>
									<div class="col-lg-9">
										<input class="form-control" name="address" id="address"
											value="${user.address}" />
									</div>
									<label class="control-label">现住地址</label>
									<div class="col-lg-9">
										<input class="form-control" name="liveAddress"
											id="liveAddress" value="${user.liveAddress}" />
									</div>
								</div>
								<!-- /.row (nested) -->
							</div>
						</form>
						<div >
						<button type="button" class="btn btn-primary" onclick="save()">提交更改</button>
						<button type="button" class="btn btn-default" onclick="goBack()">返回</button>
						</div>
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>

	<!-- /. PAGE INNER  -->
</body>
</html>