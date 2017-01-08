<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>华信智原-用户登录</title>

<jsp:include page="common/include_loginCSS.jsp"></jsp:include>
<jsp:include page="common/include_loginJS.jsp"></jsp:include>

<style type="text/css">

.verifycss{
		  padding-left:0;
		  padding-right:0;
		  margin-bottom:20px;
		}

</style>

</head>

<body data-url="${pageContext.request.contextPath}">

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>华信智原</strong> Wisezone
						</h1>
						<div class="description"></div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>会员登录</h3>
								<p>Enter your username and password to log on:</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-key"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form role="form"  method="post" class="login-form" action="${pageContext.request.contextPath}/login.action">
								<div class="form-group">
									<label class="sr-only" for="form-username">登录名</label> <input
										type="text" name="username" placeholder="请输入用户名"
										class="form-username form-control" id="form-username">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">登录密码</label> <input
										type="password" name="password" placeholder="请输入密码"
										class="form-password form-control" id="form-password">
								</div>
								
								  <div class="form-group">
                                 
                                   <div class="col-md-8 verifycss">
                                       <input class="form-control" placeholder="输入验证码" name="verifycode" id="verifycode"  type="text" >
                                   </div>
                                
                                 	 <div class="col-md-4"> 
                                       
                                        <img
                                          id="verifycode" onclick="change(this)"
                                         src="${pageContext.request.contextPath }/verify/image.action" 
                                           title="看不清，换一张"
                                        height="48" />
                                        
                                   </div>
                                </div>
								
								
								
							<!--  	<div class="checkbox">
									<label> <input type="checkbox" name="rememberme"
										value="false" > 记住我
									</label>
								</div>-->

								<div style="color: red; font-weight: bold;">
									<s:fielderror></s:fielderror>
								</div>
								<button type="submit" class="btn">登录</button>
							</form>
						</div>
					</div>
				</div>
				<div class="row"></div>
			</div>
		</div>

	</div>




</body>


  <script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrapvalidator/login.js"> </script>
   
</html>