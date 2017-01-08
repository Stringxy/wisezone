package com.web.controller.teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.biz.CityBiz;
import com.web.biz.RoleBiz;
import com.web.biz.TeacherBiz;
import com.web.biz.impl.CityBizImpl;
import com.web.entity.City;
import com.web.entity.Role;
import com.web.entity.Teacher;
import com.web.entity.User;
import com.web.util.DateJsonUtil;
import com.web.util.JsonForDataTableUtil;
import com.web.util.JsonUtil;
import com.web.util.PageUtil;
import com.web.util.StringUtil;

/**
 * 
 * @author Xy
 *
 */

@Controller
@Scope(value = "prototype")
// 每次请求（每次使用）创建新的实例
public class TeacherController extends ActionSupport implements
		ModelDriven<Teacher> {
	@Resource(name = "pageUtil")
	private PageUtil<Teacher> paging;
	private String iDisplayStart;
	private String iDisplayLength;
	private String sEcho;
	private String sSearch;// 搜索框的输入内容，暂时不用
	@Resource(name = "teacherBizImpl")
	private TeacherBiz teacherBiz;
	private Teacher teacher=null;
	@Resource(name = "cityBizImpl")
	private CityBiz cityBiz = null;
	@Resource(name = "roleBizImpl")
	private RoleBiz roleBiz = null;




	public PageUtil<Teacher> getPaging() {
		return paging;
	}

	public void setPaging(PageUtil<Teacher> paging) {
		this.paging = paging;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}



	@Override
	public Teacher getModel() {
		if (teacher == null) {

			teacher = new Teacher();
		}
		return teacher;
	}

	/**
	 * 全查询+分页 分页处理封装在JsonForDataTableUtil中，直接调updatePage即可
	 * json处理封装在JsonForDataTableUtil中，直接调outJson即可
	 */
	public void search() {
		System.out.println("进入了search");
		HttpServletRequest request = ServletActionContext.getRequest();
		this.iDisplayStart = (request.getParameter("iDisplayStart"));
		this.iDisplayLength = (request.getParameter("iDisplayLength"));
		this.sEcho = (request.getParameter("sEcho"));
		JsonForDataTableUtil.updatePage(this.iDisplayStart,
				this.iDisplayLength, this.paging);
		List<City>cities=new ArrayList<City>();
		List<Role>roles=new ArrayList<Role>();
		cities= cityBiz.findAll();
		roles= roleBiz.findAll();
		ActionContext.getContext().getSession().put("city", cities);
		ActionContext.getContext().getSession().put("role", roles);

		teacherBiz.searchPaging(null, paging);
		HttpServletResponse response = ServletActionContext.getResponse();
		JsonForDataTableUtil.outJson(response, paging, sEcho);
	}



	public String addSaveTeacher() {

		teacherBiz.insert(teacher);

		return "addSaveTeacher";

	}
	


	
	public String edit(){
		List<City>cities=new ArrayList<City>();
		List<Role>roles=new ArrayList<Role>();
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer teacherId=Integer.parseInt(request.getParameter("teacherId"));
		cities= cityBiz.findAll();
		roles= roleBiz.findAll();
		ActionContext.getContext().getSession().put("city", cities);
		ActionContext.getContext().getSession().put("role", roles);
		this.teacher=teacherBiz.findById(teacherId);
		ActionContext.getContext().getSession().put("teacher", teacher);
		return "edit";
	}
	
	public void valid(){
		
		JSONObject json = new JSONObject();
		Teacher old=teacherBiz.findById(this.teacher.getTeacherId());
		
		if(old.getLoginName().equals(this.teacher.getLoginName())){
			json.put("valid", true);
		}else{
			json.put("valid", teacherBiz.isVaild(this.teacher.getLoginName()));
		}
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JsonUtil.outJson(response, json);
	}
}
