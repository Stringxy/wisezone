package com.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.biz.TeacherBiz;
import com.web.biz.UserBiz;
import com.web.entity.Teacher;
import com.web.entity.User;
import com.web.util.StringUtil;
/**
 * 登录控制
 * @author Xy
 *
 */
@Controller //控制层ioc注释
@Scope(value="prototype") //每次请求创建新的实例
public class LoginController extends ActionSupport {

	private String username;
	private String password;
	private boolean rememberme;
	@Resource(name = "userBizImpl")
	private UserBiz userBiz;
	@Resource(name="teacherBizImpl")
	TeacherBiz teacherbiz;
	@Override
	public String execute() throws Exception {
		Subject subject=SecurityUtils.getSubject();
		System.out.println("进入了LOGINACTION。。。");
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		String verifycode=request.getParameter("verifycode");
		String rnCode=(String) ActionContext.getContext().getSession().get("rnCode");
		if(!verifycode.toLowerCase().equals(rnCode.toLowerCase())){
			super.addFieldError("tips", "验证码错误！");
			return INPUT;
		}
		if(StringUtil.isEmpty(username)){
			super.addFieldError("username", "用户名不能为空");
		}
		if(StringUtil.isEmpty(password)){
			super.addFieldError("password", "密码不能为空");
		}
	UsernamePasswordToken token=new UsernamePasswordToken(username,password,rememberme);
		try {
			subject.login(token);
		}  catch(UnknownAccountException e){
			super.addFieldError("tips", "用户名或者密码错误!");

			return INPUT;
		}catch (IncorrectCredentialsException e) {
			super.addFieldError("tips", "用户名或者密码错误!");

			return INPUT;
		}catch(AuthenticationException  e){
			super.addFieldError("tips", "账户被锁定!");

			return INPUT;
		}
		Teacher teacher=null;
		User user = userBiz.findByname(username);
		if(user==null){
			teacher=teacherbiz.login(username, password);
			ActionContext.getContext().getSession().put("teacherNow", teacher);
		}else{
		ActionContext.getContext().getSession().put("userNow", user);}
		return SUCCESS;
	}

	//getter，setter
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRememberme() {
		return rememberme;
	}
	public void setRememberme(boolean rememberme) {
		this.rememberme = rememberme;
	}
	
}
