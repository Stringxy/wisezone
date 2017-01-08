package com.web.controller.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.web.biz.ClassesBiz;
import com.web.biz.MajorBiz;
import com.web.biz.TeacherBiz;
import com.web.biz.TeacherClassesBiz;
import com.web.entity.Classes;
import com.web.entity.Detail;
import com.web.entity.DetailQuestion;
import com.web.entity.Major;
import com.web.entity.Question;
import com.web.entity.Teacher;
import com.web.entity.TeacherClasses;
import com.web.util.JsonForDataTableUtil;
import com.web.util.JsonUtil;
import com.web.util.PageUtil;
import com.web.util.StringUtil;

@Controller
public class ClassesController implements ModelDriven<Classes> {

	@Resource(name="pageUtil")
	private PageUtil<Classes> paging;
	@Resource(name="classesBizImpl")
	private ClassesBiz classesbiz;
	@Resource(name="majorBizImpl")
	private MajorBiz majorBiz;
	private String iDisplayStart;//直接从request.getparameter中取，getset方法取不到
	private String iDisplayLength;//直接从request.getparameter中取，getset方法取不到
	private String sEcho;//直接从request.getparameter中取，getset方法取不到
	private Classes classes=null;
	private Integer majorId;
	private List<Major>majors=new ArrayList<Major>();
	@Resource(name="teacherBizImpl")
	private TeacherBiz teacherBiz;
	@Resource(name="teacherClassesBizImpl")
	private TeacherClassesBiz teacherClassesBiz;
	public Integer getMajorId() {
		return majorId;
	}

	public void setMajorId(Integer majorId) {
		if(majorId!=null&&StringUtil.isNumeric(majorId.toString()))
		this.majorId = majorId;
	}

	public List<Major> getMajors() {
		return majors;
	}

	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}

	public void search(){
		HttpServletRequest request=ServletActionContext.getRequest();
		this.iDisplayStart=(request.getParameter("iDisplayStart"));
		this.iDisplayLength=(request.getParameter("iDisplayLength"));
		this.sEcho = (request.getParameter("sEcho"));
		JsonForDataTableUtil.updatePage(this.iDisplayStart,
				this.iDisplayLength, this.paging);
		this.majors=majorBiz.findAll();
		classesbiz.searchPaging(null, paging);
		ActionContext.getContext().getSession().put("majors", majors);
		HttpServletResponse response = ServletActionContext.getResponse();
	
		JsonForDataTableUtil.outJson(response, paging, sEcho);
	}
	
	public String toMyClasses(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Integer teacherId=Integer.parseInt(request.getParameter("teacherId"));
		Teacher teacher =teacherBiz.findById(teacherId);
		ActionContext.getContext().getSession().put("teacherNow", teacher);
		return "toMyClasses";
	}
	
	public void searchByTeacher(){
		HttpServletRequest request=ServletActionContext.getRequest();
		this.iDisplayStart=(request.getParameter("iDisplayStart"));
		this.iDisplayLength=(request.getParameter("iDisplayLength"));
		this.sEcho = (request.getParameter("sEcho"));
		
		Teacher teacher =(Teacher)ActionContext.getContext().getSession().get("teacherNow");
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("teacher", teacher);
		JsonForDataTableUtil.updatePage(this.iDisplayStart,
				this.iDisplayLength, this.paging);
		this.majors=majorBiz.findAll();
		classesbiz.searchPaging(params, paging);
		ActionContext.getContext().getSession().put("majors", majors);
		HttpServletResponse response = ServletActionContext.getResponse();
		JsonForDataTableUtil.outJson(response, paging, sEcho);
	}
	public void del(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		Integer classesId=null;
		Teacher teacher=(Teacher)ActionContext.getContext().getSession().get("teacherNow");
		if(!StringUtil.isEmpty(request.getParameter("classesId"))){
			classesId=Integer.parseInt(request.getParameter("classesId"));
			Classes classes=classesbiz.findById(classesId);
			TeacherClasses tc=teacherClassesBiz.findByClasses(classes, teacher);
			try {
				teacherClassesBiz.delete(tc);
				
				json.put("success", true);
				json.put("message", "移除成功");
			} catch (Exception e) {
				json.put("success", false);
				json.put("message", "移除失败");
			}
		}
		JsonUtil.outJson(response, json);
	}
	
	public void save(){
		HttpServletResponse response = ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date startTime=sdf.parse(request.getParameter("startTime"));
			
			String endTime1=request.getParameter("endTime");
			
			if(endTime1.equals("点击选择时间")||StringUtil.isEmpty(endTime1)){
				System.out.println("endtime格式不正确或为空");
			}else{
				Date endTime=sdf.parse(endTime1);
				classes.setEndTime(endTime);
			}
			classes.setStartTime(startTime);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		JSONObject json = new JSONObject();
		
		classes.setMajor(majorBiz.findById(majorId));
		if(classes.getClassesId()==0){
			try {
				classesbiz.insert(classes);
				json.put("success", true);
				json.put("message", "新增成功");
			} catch (Exception e) {
				json.put("success", false);
				json.put("message", "新增失败");
			}
		}else{
			try {
				classesbiz.update(classes);
				json.put("success", true);
				json.put("message", "修改成功");
			} catch (Exception e) {
				json.put("success", false);
				json.put("message", "修改失败");
			}
		}
		JsonUtil.outJson(response, json);
	}

	@Override
	public Classes getModel() {
		if (classes == null) {
			classes = new Classes();
		}
		return classes;
	}
	
	public void addClassesForTeacher(){
		HttpServletResponse response = ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		
		String str=request.getParameter("classes");
		Teacher teacher=(Teacher)ActionContext.getContext().getSession().get("teacherNow");
		JSONObject json=new JSONObject();
		String [] arr=str.split(",");
		for(String a:arr){
			Integer classesId=Integer.parseInt(a);
			Classes classes=classesbiz.findById(classesId);
			TeacherClasses tc=new TeacherClasses();
			tc.setTeacher(teacher);
			tc.setClasses(classes);
			tc.setBeginDate(new Date());
			try {
				teacherClassesBiz.insert(tc);
				json.put("success", true);
				json.put("message", "新增班级成功！");
			} catch (Exception e) {
				json.put("success", false);
				json.put("message", "新增班级失败！报错信息："+e.getMessage());
				e.printStackTrace();
			}
		}
		
		
		JsonUtil.outJson(response, json);
	}
}
