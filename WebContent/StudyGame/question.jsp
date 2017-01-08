<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>question</title>
<jsp:include page="../common/include_CSS.jsp"></jsp:include>
<jsp:include page="../common/include_JS.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/StudyGame/question.js"></script>
  
</head>
<body data-url="${pageContext.request.contextPath}">

	<!-- 导航 -->
	<div class="row">
		<div class="col-md-12">
			<h1 class="page-header">
				试题闯关 <small>>选择阶段>选择关卡>进行答题</small>
			</h1>
		</div>
	</div>
	<!-- 导航 -->
	<!--题目 -->
	<div class="col-lg-12">

		<form role="form" id="fm">
			<c:forEach items="${questions}" var="question">
				<c:if
					test="${question.questionType=='1'||question.questionType=='3'}">
					<!-- 单项选择题 -->
					<div class="form-group">
						<label>${question.questionId}.${question.questionName}</label><a onclick="collectionQuestion(${question.questionId})" > <i class="fa fa-lg-2 fa-pencil"> 收藏该题</i> </a>
						<c:if test="${not empty question.answerA}">
							<div class="radio">
								<label> <input type="radio"
									name="${question.questionId}" id="optionsRadios1"
									value="${question.answerA}" checked="">A.${question.answerA}
								</label>
							</div>
						</c:if>
						<c:if test="${not empty question.answerB}">
							<div class="radio">
								<label> <input type="radio"
									name="${question.questionId}" id="optionsRadios2"
									value="${question.answerB}">B.${question.answerB}
								</label>
							</div>
						</c:if>
						<c:if test="${not empty question.answerC}">
							<div class="radio">
								<label> <input type="radio"
									name="${question.questionId}" id="optionsRadios3"
									value="${question.answerC}">C.${question.answerC}
								</label>
							</div>
						</c:if>
						<c:if test="${not empty question.answerD}">
							<div class="radio">
								<label> <input type="radio"
									name="${question.questionId}" id="optionsRadios3"
									value="${question.answerD}">D.${question.answerD}
								</label>
							</div>
						</c:if>
					</div>
				</c:if>
				<c:if test="${question.questionType=='2'}">
					<!-- 多项选择题 -->
					<div class="form-group">
						<label>${question.questionId}.${question.questionName}</label><a onclick="collectionQuestion(${question.questionId})"  > <i class="fa fa-lg-2 fa-pencil"> 收藏该题</i> </a>
						<c:if test="${not empty question.answerA}">
							<div class="checkbox">
								<label> <input name="${question.questionId}"
									type="checkbox" value="${question.answerA}">A.${question.answerA}

								</label>
							</div>
						</c:if>
						<c:if test="${not empty question.answerB}">
							<div class="checkbox">
								<label> <input name="${question.questionId}"
									type="checkbox" value="${question.answerB}">B.${question.answerB}

								</label>
							</div>
						</c:if>
						<c:if test="${not empty question.answerC}">
							<div class="checkbox">
								<label> <input name="${question.questionId}"
									type="checkbox" value="${question.answerC}">C.${question.answerC}

								</label>
							</div>
						</c:if>
						<c:if test="${not empty question.answerD}">
							<div class="checkbox">
								<label> <input name="${question.questionId}"
									type="checkbox" value="${question.answerD}">D.${question.answerD}

								</label>
							</div>
						</c:if>
					</div>
				</c:if>

				
			</c:forEach>
			<button  type="button" class="btn btn-primary" onclick="save()">提交</button>

			<!--题目 -->
		</form>
		
	</div>


</body>
</html>