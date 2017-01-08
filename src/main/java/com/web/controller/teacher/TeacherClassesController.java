package com.web.controller.teacher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.biz.TeacherClassesBiz;
import com.web.entity.TeacherClasses;
import com.web.util.JsonForDataTableUtil;
import com.web.util.PageUtil;
@Controller
public class TeacherClassesController extends ActionSupport implements ModelDriven<TeacherClasses>{

	@Resource(name="pageUtil")
	private PageUtil<TeacherClasses> paging;
	@Resource(name="teacherClassesBizImpl")
	private TeacherClassesBiz tb;
	private String iDisplayStart;
	private String iDisplayLength;
	private String sEcho;
	private TeacherClasses tc=null;
	
	
	@Override
	public TeacherClasses getModel() {
		if(tc==null){
			tc=new TeacherClasses();
		}
		return tc;
	}
	
	public PageUtil<TeacherClasses> getPaging() {
		return paging;
	}
	public void setPaging(PageUtil<TeacherClasses> paging) {
		this.paging = paging;
	}
	public TeacherClassesBiz getTb() {
		return tb;
	}
	public void setTb(TeacherClassesBiz tb) {
		this.tb = tb;
	}
	public String getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(String iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public String getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(String iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	
	
	public void search(){
		HttpServletRequest request = ServletActionContext.getRequest();
		this.iDisplayStart = (request.getParameter("iDisplayStart"));
		this.iDisplayLength = (request.getParameter("iDisplayLength"));
		this.sEcho = (request.getParameter("sEcho"));
		JsonForDataTableUtil.updatePage(this.iDisplayStart,
				this.iDisplayLength, this.paging);
		
		tb.searchPaging(null, paging);
		
		System.out.println(paging.getData());
		
		HttpServletResponse response = ServletActionContext.getResponse();
		JsonForDataTableUtil.outJson(response, paging, sEcho);
	}
	
	public String addSave(){
		
		
		tb.insert(tc);

		
		return SUCCESS;
		
	}
	
}
