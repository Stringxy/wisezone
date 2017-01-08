<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增教师操作</title>
<jsp:include page="../common/include_CSS.jsp"></jsp:include>
<jsp:include page="../common/include_JS.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/fileinput/css/fileinput.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/fileinput/js/fileinput.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/fileinput/js/fileinput.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/fileinput/js/locales/zh.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath }/teacher/edit.js"></script>
</head>
<body data-url="${pageContext.request.contextPath }">
<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">
					教师信息设置 <small>>教师信息表</small>
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
		                       新增教师
		           <s:actionerror/> 
		       </h3>
		    </div>
		    <div class="panel-body">
		       
		       <form name="form1" method="post" action="${pageContext.request.contextPath }/teacher/addSaveTeacher.action" class="form-horizontal" role="form">
				  <div class="form-group">
				    <label for="teacherName" class="col-sm-2 control-label">教师名称</label>
				    <div class="col-sm-10">
				        
				     
				      <input type="text" class="form-control" id="teacherName" name="teacherName" 
				             placeholder="请输入教师名称"   data-bv-trigger='blur' >
				    </div>
				  </div>
				  
				  <div class="form-group">
								<label class="col-sm-2 control-label">教师头像</label> <img alt=""
									src="${pageContext.request.contextPath}${user.portrait}"
									style="width: 290px; height: 290px; border: 5px solid #4D8AB3;">

							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label" for="uploadFiles">上传头像：</label>
								<input type="file" name="uploadFiles" id="uploadfile"
									class="file-loading" />
							</div>
				  
				  <div class="form-group">
				    <label for="loginName" class="col-sm-2 control-label">教师登录名</label>
				    <div class="col-sm-10">
				        
				     
				      <input class="form-control" id="loginName" name="loginName" 
				             placeholder="请输入教师登录"   >
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="loginPwd" class="col-sm-2 control-label">教师密码</label>
				    <div class="col-sm-10">
				        
				     
				      <input  class="form-control" id="loginPwd" name="loginPwd" 
				             placeholder="请输入教师密码"   >
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="sex" class="col-sm-2 control-label">教师性别</label>
				    <div class="col-sm-10">
				        
				     
				      <s:select list="#{0:'男',1:'女'}"  
				      id="sex"
				      name="sex"
				      listKey="key" 
				     listValue="value"
				     headerKey=""
				     headerValue="请选择"
				     cssClass="form-control"
				     ></s:select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="birthday" class="col-sm-2 control-label">教师生日</label>
				    <div class="col-sm-10">
				        
				     
				      <input type="text" class="form-control" id="birthday" name="birthday" 
				             placeholder=""   data-bv-trigger='blur' >
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="education" class="col-sm-2 control-label">教师学历</label>
				    <div class="col-sm-10">
				        
				      <s:select list="#{1:'大专以下',2:'大专',3:'本科',4:'研究生' }"  
				      id="education"
				      name="education"
				      listKey="key" 
				     listValue="value"
				     headerKey=""
				     headerValue="请选择"
				     cssClass="form-control"
				     ></s:select>
				      
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="graduate" class="col-sm-2 control-label">毕业院校</label>
				    <div class="col-sm-10">
				        
				     
				      <input type="text" class="form-control" id="graduate" name="graduate" 
				             placeholder="请输入毕业学院"   data-bv-trigger='blur' >
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="major" class="col-sm-2 control-label">教师专业</label>
				    <div class="col-sm-10">
				        
				     
				      <input type="text" class="form-control" id="major" name="major" 
				             placeholder="请输入专业"   data-bv-trigger='blur' >
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="identity" class="col-sm-2 control-label">身份证号码</label>
				    <div class="col-sm-10">
				        
				     
				      <input type="text" class="form-control" id="identity" name="identity" 
				             placeholder="请输入身份证号码"   data-bv-trigger='blur' >
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="mobile" class="col-sm-2 control-label">手机号码</label>
				    <div class="col-sm-10">
				        
				     
				      <input type="text" class="form-control" id="mobile" name="mobile" 
				             placeholder="请输入手机号码"   data-bv-trigger='blur' >
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="qq" class="col-sm-2 control-label">qq号码</label>
				    <div class="col-sm-10">
				        
				     
				      <input type="text" class="form-control" id="qq" name="qq" 
				             placeholder="请输入QQ号码"   data-bv-trigger='blur' >
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="city" class="col-sm-2 control-label">所属城市</label>
				    <div class="col-sm-10">
				        <select name="name" id="name" class="form-control">
						<option value="0">请选择</option>
						<c:forEach items="${city}" var="c">
						<option value="${c.id }">${c.name }</option>
						</c:forEach>
						
						
				     </select>
				     
				     
				     
				      </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="address" class="col-sm-2 control-label">家庭地址</label>
				    <div class="col-sm-10">
				        
				     
				      <input type="text" class="form-control" id="address" name="address" 
				             placeholder="请输入城市名称"   data-bv-trigger='blur' >
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="portrait" class="col-sm-2 control-label">教师头像</label>
				    <div class="col-sm-10">
				        
				     
				      <input type="text" class="form-control" id="portrait" name="portrait" 
				             placeholder="请输入城市名称"   data-bv-trigger='blur' >
				    </div>
				  </div>
				  
				   <div class="form-group">
				    <label for="role" class="col-sm-2 control-label">所属角色</label>
				    <div class="col-sm-10">
				        
				        <select name="roleName" id="roleName" class="form-control">
						<c:forEach items="${role}" var="r">
						<option value="0">请选择</option>
						<option value="${r.roleId }">${r.roleName }</option>
						</c:forEach>
						
						
				     </select>
				      
				    </div>
				  </div>
				  
				   <div class="form-group">
				    <label for="state" class="col-sm-2 control-label">状态</label>
				    <div class="col-sm-10">
				       <s:select list="#{1:'正常',2:'休假',3:'离职' }"  
				       id="state"
				       name="state"
				       listKey="key" 
				     listValue="value"
				     headerKey=""
				     headerValue="请选择"
				     cssClass="form-control"
				     ></s:select>
				   
				      
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



  
 
</html>
   
