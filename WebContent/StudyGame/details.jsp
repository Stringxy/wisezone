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
</head>
<body>

	<div id="page-inner">
		<!-- 导航 -->
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					试题闯关 <small>>选择阶段>选择关卡</small>
				</h1>
			</div>
		</div>

		<!-- 导航 -->

		<!-- 关卡 -->
		<c:forEach items="${details}" var="detail">
			<div class="col-md-3 col-sm-12 col-xs-12">
				<div class="panel panel-primary text-center no-boder bg-color-green">
					<div class="panel-body">
						<i class="fa fa-bar-chart-o fa-5x"></i>
						<h3>${detail.checkName}</h3>
					</div>
					<c:if test="${nowDetailId+1 ge detail.detailId}">
						<a
							href="${pageContext.request.contextPath }/StudyGame/Question!getQuestion.action?detailId=${detail.detailId}"
							class="btn btn-primary btn-sm">开始</a>
					</c:if>
					<c:if test="${nowDetailId+1 lt detail.detailId}">
						<fieldset disabled="">
							<button class="btn btn-primary">此关卡未开通</button>
						</fieldset>
					</c:if>
				</div>
			</div>
		</c:forEach>
		<!-- 关卡 -->

		<!-- 禁用标签 -->
		<fieldset disabled=""></fieldset>
	</div>
</body>
</html>