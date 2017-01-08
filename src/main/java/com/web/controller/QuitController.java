package com.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller //控制层ioc注释
@Scope(value="prototype") //每次请求创建新的实例
public class QuitController extends ActionSupport {

	public String quit(){
		ActionContext.getContext().getSession().clear();
		return "quit";
	}
}
