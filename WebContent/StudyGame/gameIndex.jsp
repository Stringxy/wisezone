<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>game</title>
<jsp:include page="../common/include_CSS.jsp"></jsp:include>
<jsp:include page="../common/include_JS.jsp"></jsp:include>
</head>
<body>
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					试题闯关 <small>>选择阶段</small>
				</h1>
			</div>
		</div>

		<c:forEach items="${checks}" var="check">

			<div class="col-md-4 col-sm-4">
				<div class="panel panel-default">
					<div class="panel-heading">${check.checkName}</div>
					<div class="panel-body">
						<p>关卡总数量：${check.checkCount}关</p>
					</div>
					<div class="panel-footer">
						<c:if test="${nowCheckId+1 ge check.checkId}">
							<a
								href="${pageContext.request.contextPath }/StudyGame/Detail!getDetail.action?checkId=${check.checkId}"
								class="btn btn-primary btn-sm">进入该阶段</a>
						</c:if>
						<c:if test="${nowCheckId+1 lt check.checkId}">
							<fieldset disabled="">
								<button class="btn btn-primary">此阶段未开通</button>
							</fieldset>
						</c:if>
					</div>
				</div>
			</div>
		</c:forEach>



		<!-- 闯关进度条 -->
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">闯关进度</div>

				<div class="panel-body">
					<div class="progress progress-striped active">
						<div class="progress-bar progress-bar-primary" role="progressbar"
							aria-valuenow="${percent}" aria-valuemin="0" aria-valuemax="100"
							style="width: ${percent}%">
							<span class="sr-only">40% Complete (success)</span>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>

</body>
</html>