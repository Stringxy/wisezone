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
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					我的试题收藏夹
				</h1>
			</div>
		</div>
		<!-- /. 试题内容  -->
		<c:forEach items="${mycollectionquestions}" var="collectionquestion">
 				<div class="col-md-6">
                          <div class="panel panel-default">
                        <div class="panel-heading">
                            ${collectionquestion.question.questionName}
                        </div>
                        <div class="panel-body">
                         <h3>${collectionquestion.question.rightAnswer}</h3>
                           <p>${collectionquestion.question.analysis}</p>
                             </div>
                              </div>
                     </div>
</c:forEach>
		
	</div>
</body>
</html>