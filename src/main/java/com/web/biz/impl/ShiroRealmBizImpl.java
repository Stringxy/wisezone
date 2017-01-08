package com.web.biz.impl;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import com.web.biz.CheckBiz;
import com.web.biz.ShiroRealmBiz;
import com.web.biz.TeacherBiz;
import com.web.biz.UserBiz;
import com.web.entity.Major;
import com.web.entity.Teacher;
import com.web.entity.User;


/**
 * realm判断授权，认证
 * @author Xy
 *
 */

@Service
public class ShiroRealmBizImpl extends AuthorizingRealm implements ShiroRealmBiz{

	//http://blog.csdn.net/javaee_ssh/article/details/42834949
	
	@Resource(name="teacherBizImpl")
	TeacherBiz teacherbiz;
	@Resource(name="userBizImpl")
	UserBiz userbiz;
	@Resource(name="checkBizImpl")
	CheckBiz checkBiz;
	/**
	 * 授权实现
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		String username=(String)getAvailablePrincipal(principals);
		User user=userbiz.findByname(username);
		if(user!=null){
			
			info.addRole("student");

			
		}else{
			Teacher teacher=teacherbiz.findByName(username);
			if(teacher!=null){
				String roleName=teacher.getRole().getRoleName();
				if(roleName.equals("教师")){
				info.addRole("teacher");
				}else{
					info.addRole("admin");
				}
			}
		}
		return info;
	}

	
	/**
	 * 认证实现
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username=token.getPrincipal().toString();
		String password=new String((char[])token.getCredentials());
		Teacher teacher=teacherbiz.login(username, password);
		if(teacher==null){
			User user=userbiz.login(username, password);
			if(user==null){
				throw new UnknownAccountException();
			}
			if(user.getState()==3){
				throw new AuthenticationException();
			}
		}else if(teacher.getState()==3){
			throw new AuthenticationException();
		}
		
		
		return new SimpleAuthenticationInfo(username,password,getName());
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "loginRealm";
	}

	
}
