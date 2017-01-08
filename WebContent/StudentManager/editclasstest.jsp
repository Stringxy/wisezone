<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增成绩测试表</title>
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<jsp:include page="../common/include_CSS.jsp"></jsp:include>
<jsp:include page="../common/include_JS.jsp"></jsp:include>
<!-- 日期插件的css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/datetimepicker/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/datetimepicker/css/bootstrap-datetimepicker.min.css" />
<!-- DATA TABLE SCRIPTS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/common/static/assets/js/dataTables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/common/static/assets/js/dataTables/dataTables.bootstrap.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/baseManage/classes.js"></script>


</head>
<body>
<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					学员信息设置 <small>>学员课堂测验成绩表</small>
				</h1>
			</div>
		</div>
		

		<div class="row">
			<div class="col-md-12">
				
			
       
       <div class="panel panel-primary">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		                     编辑课堂测验成绩信息
		           
		       </h3>
		    </div>
		    <div class="panel-body">
		       
		       <form name="form1" method="post" action="${pageContext.request.contextPath }/studentManager/ClassTest!editSave.action" class="form-horizontal" role="form">
				  <div class="form-group">
				    <label for="stuName" class="col-sm-2 control-label">学员登录名</label>
				    <div class="col-sm-10">
				      <input type="text" name="stuName" id="stuName" class="form-control"  value="${classtest.user.stuName}" readonly="readonly" >
				    </div>
				  </div>
				  
				  
				    <div class="form-group">
				    <label for="classesName" class="col-sm-2 control-label">所属班级</label>
				    <div class="col-sm-10">
				     <input type="text" name="classesName" id="classesName" class="form-control"  value="${classtest.classes.classesName}" readonly="readonly" >
				    </div>
				  </div>
				  
				  
				 
				  <div class="form-group">
				    <label for="createDate1" class="col-sm-2 control-label">访谈日期</label>
				    <div class="col-sm-10">
				    <input type="text" class="form-control form_datetime" id="createDate1" name="createDate1" 
				         value="<f:formatDate value="${classtest.createDate}" type="both" dateStyle="long" pattern="yyyy-MM-dd"/>" readonly >
				    </div>
				  </div>
				  
				   <div class="form-group">
				    <label for="score" class="col-sm-2 control-label">主要信息描述</label>
				    <div class="col-sm-10">
				   <input type="text" name="score" id="score" class="form-control"  value="${classtest.score}" >
				    </div>
				  </div>
				  
				  
				  <div class="form-group">
				    <label for="missExam" class="col-sm-2 control-label">是否缺考</label>
				    <div class="col-sm-10">
				   <select name="missExam" id="missExam" class="form-control" >
				   <option value="0"
				   <c:if test="${classtest.missExam==0}">
				   selected="selected"
				   </c:if>
				   
				   >是</option>
				   <option value="1" <c:if test="${classtest.missExam==1}">
				   selected="selected"
				   </c:if>>否</option>
				  
				   </select>
				    </div>
				  </div>
				  
				    <div class="form-group">
				    <label for="cDesc" class="col-sm-2 control-label">备注</label>
				    <div class="col-sm-10">
				   <input type="text" name="cDesc" id="cDesc" style="height: 135px;" class="form-control"  value="${classtest.cDesc }" >
				    </div>
				  </div>
				 
				  
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-default">保存</button>
				    </div>
				  </div>
				</form>		
		       
		       
		       
		    </div>
	  </div>
       
       
   
   </div>
</div>
</div>




</body>
<!-- 日期插件的js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">
$('.form_datetime').datetimepicker({
    minView: "month", //选择日期后，不会再跳转去选择时分秒 
    language:  'zh-CN',
    format: 'yyyy-mm-dd',
    todayBtn:  1,
    autoclose: 1,
});
</script>
  
 
</html>