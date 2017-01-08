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
		
		
					<!-- context -->
					<form method="post" id="fm">
						<div class="form-group">
							<label class="control-label">题目</label>
						
								<input type="text" class="form-control" name="questionName"
									id="questionName" value="${questionNow.questionName}" 
									readonly="readonly"/>
							

							<br /> <label class="control-label" for="majorId">所属专业</label>
							
								<input type="text" class="form-control" name="majorId"
									id="majorId" value="${questionNow.major.majorName}" 
									readonly="readonly"/>
						
							<label class="control-label" for="stageId">所属阶段</label>
							
								<input type="text" class="form-control" name="stageId"
									id="stageId" value="${questionNow.majorstage.stageName}" 
									readonly="readonly"/>
						
							<label class="control-label" for="questionType">试题类型</label>
							
								<input type="text" class="form-control" name="questionType"
									id="questionType" 
									<c:if test="${questionNow.questionType==1}">value="单选"</c:if>
									<c:if test="${questionNow.questionType==2}">value="多选"</c:if>
									<c:if test="${questionNow.questionType==3}">value="判断"</c:if>
									readonly="readonly"/>
							
								 <label class="control-label">答案A</label>
							
									<input type="text" class="form-control" name="answerA"
										id="answerA" 
										value="${questionNow.answerA}" 
									readonly="readonly"/>
						
								<label class="control-label">答案B</label>
							
									<input type="text" class="form-control" name="answerB"
										id="answerB" value="${questionNow.answerB}" 
									readonly="readonly"/>
					
								<label class="control-label">答案C</label>
					
									<input type="text" class="form-control" name="answerC"
										id="answerC" value="${questionNow.answerC}" 
									readonly="readonly"/>
							
								<label class="control-label">答案D</label>
							
									<input type="text" class="form-control" name="answerD"
										id="answerD" value="${questionNow.answerD}" 
									readonly="readonly" />
							
								<label class="control-label">正确答案</label>
						
									<input type="text" class="form-control" name="rightAnswer"
										id="rightAnswer" value="${questionNow.rightAnswer}" 
									readonly="readonly" />
							
								<label class="control-label">答案解析</label>
						
									<input type="text" class="form-control" name="analysis"
										id="analysis" value="${questionNow.analysis}" 
									readonly="readonly" />
						
							
							<br />
						</div>
					</form>

				
</div>
</body>
</html>