<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %> 
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/include_CSS.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath }/common/static/loginStyle/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/common/static/loginStyle/assets/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrapvalidator/css/bootstrapValidator.css">
<title>Insert title here</title>
<style type="text/css">
	.mystyle{
		margin-bottom: 10px
	}
</style>
</head>
<body>
	
	
	<%
	List exams=(List)session.getAttribute("examine");
	List exam=(List)session.getAttribute("examine");
	List teachers=(List)session.getAttribute("teachers");
	List classes=(List)session.getAttribute("classes");
	request.setAttribute("exams", exams);
	request.setAttribute("exam", exam);
	request.setAttribute("teachers", teachers);
	request.setAttribute("classes", classes);
	%>
	<div class="col-md-12">
		<h2 class="page-header">
		WISEZONE
		<small>调查问卷</small>
		</h2>
	</div>
		<!-- 获取action传来的题目list集合，并输出到jsp页面 -->
	<form id="formVal" action="${pageContext.request.contextPath }/StuExamine/Examine!saveResult.action" method="post" name="examResult">
	
	<div class="row">
	<div class="col-xs-2"  style="margin-left: 15px">
	<span class="glyphicon glyphicon-user mystyle"><strong>教师名称</strong></span>
	<select class="form-control" name="examTeacher">
		<c:forEach var="teachers" items="${teachers}">
			<option value="${teachers.teacherId }">${ teachers.teacherName}</option>
		</c:forEach>
	</select>
	</div>
	
	<div class="col-xs-4" >
	<span class="glyphicon glyphicon-plus mystyle">
	<strong>班级</strong>
	</span>
	<select class="form-control" name="examClass">
		<c:forEach var="classes" items="${classes}">
			<option value="${classes.classesId }">${ classes.classesName}</option>
		</c:forEach>
	</select>
	</div>
	<div class="panel panel-default col-md-8">
			<c:forEach var="exams" items="${exams}">
					<c:choose>
						<c:when test="${empty exams.parentId }">
							<div class="panel-heading">
								<c:out value="${exams.examInfo }"></c:out>
							</div>
						
						<div class="panel-body ">
							<c:forEach var="exam" items="${exam }"  varStatus="status">
								<c:if test="${exam.parentId==exams.examId }">
								<div class="alert alert-info  ">
										<strong>(
										<c:out value="${exam.examScore }"></c:out>
										分)</strong>
										<c:out value="${exam.examInfo }"></c:out>
									    <input type="text" class="form-control" name="score"    id="score${status.index }"  placeholder="请打分...">
									    <input type="hidden" name="ids"  value="${exam.examScore }">
									</div>
								</c:if>
							</c:forEach>		
						</div>
						</c:when>
					</c:choose>
			</c:forEach>
			<input type="hidden" name="choice"  value="${exam[1].examType.examTypeId }">
		</div>
		<div class="form-group col-md-8">
			<label>您的意见与建议</label>
			<textarea class="form-control" rows="3" name="psInfo"></textarea>
			<button type="submit"  class="btn btn-primary btn-lg btn-block">SUBMIT</button>
			<button id="reset" type="reset"  class="btn btn-danger btn-lg btn-block">
			<s:actionerror/>
			RESET
			</button>
		</div>	
			
	</form>
	

</body>
	 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath }/static/assets/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/common/static/assets/js/bootstrap.min.js"  type="text/javascript"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
	 <script src="${pageContext.request.contextPath }/static/bootstrapvalidator/js/bootstrapValidator.js"></script>
  	<script src="${pageContext.request.contextPath }/static/bootstrapvalidator/js/language/zh_CN.js"></script>
	 <script type="text/javascript">
	 		$("button#reset").click(function(){
	 			$("button#reset").html('RESET');
	 		})
	 
	 
	 </script>

</html>