<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改专业操作</title>
<jsp:include page="../common/include_CSS.jsp"></jsp:include>
<jsp:include page="../common/include_JS.jsp"></jsp:include>
</head>
<body data-url="${pageContext.request.contextPath }">
<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					基础信息设置 <small>>专业信息表</small>
				</h1>
			</div>
		</div>
		<!-- /. ROW  -->

		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
			
       <!-- 内容 面板区 -->
       <div class="panel panel-primary">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		                       修改专业
		            
		       </h3>
		    </div>
		    <div class="panel-body">
		       
		       <form name="form1" method="post" action="${pageContext.request.contextPath }/baseManage/editsavemajor.action" class="form-horizontal" role="form">
				  <div class="form-group">
				    <label for="majorName" class="col-sm-2 control-label">专业名称</label>
				    <div class="col-sm-10">
				        
						
				      <input type="text" class="form-control" id="majorName" name="majorName" 
				             placeholder="请输入专业名称"   value="${majorName }" >
				      
				      
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


<script src="${pageContext.request.contextPath }/common/static/assets/js/cityvalidator.js"></script>
  
 
</html>
   
