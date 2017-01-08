package com.web.controller.Attendance;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.web.biz.AttendanceBiz;
import com.web.biz.ClassesBiz;
import com.web.biz.UserBiz;
import com.web.controller.CommonPageController;
import com.web.entity.Attendance;
import com.web.entity.Classes;
import com.web.entity.Teacher;
import com.web.entity.User;
import com.web.util.JsonForDataTableUtil;
import com.web.util.PageUtil;

@Controller
//控制层的注解
@Scope(value = "prototype")
//每次请求（每次使用）创建新的实例
public class AttendanceController extends CommonPageController<Attendance> implements
ModelDriven<Attendance> {

	private Attendance attendance=null;
	
	private AttendanceBiz attendanceBiz=null;
	@Resource(name = "attendanceBizImpl")
	public void setAttendanceBiz(AttendanceBiz attendanceBiz){
		this.attendanceBiz=attendanceBiz;
		super.setCommonBizPage(attendanceBiz);
	}
	
	@Resource(name="classesBizImpl")
	private ClassesBiz classesBiz;
	@Resource(name="userBizImpl")
	private UserBiz userBiz;
	@Override
	public Attendance getModel() {
		if(this.attendance==null){
			this.attendance=new Attendance();
		}
		return this.attendance;
	}

	
	public String toChoose(){
		
		Teacher teacher=(Teacher) ActionContext.getContext().getSession().get("teacherNow");
		List<Classes> classes=classesBiz.findByTeacher(teacher);
		ActionContext.getContext().getSession().put("myClasses",classes);
		return "toChoose";
	}
	
	public  String toAddAttendance(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer classesId=Integer.parseInt(request.getParameter("classesId"));
		Classes classes=classesBiz.findById(classesId);
		ActionContext.getContext().getSession().put("classes",classes);
		
		Calendar a=Calendar.getInstance();
		Integer createYear=a.get(Calendar.YEAR);//得到年
		Integer createMonth=a.get(Calendar.MONTH)+1;//由于月份是从0开始的所以加1
		Integer createDay=(a.get(Calendar.DATE));
		System.out.println("判断是否存在记录");
		if(!attendanceBiz.isExist(createYear, createMonth, classes)){
			System.out.println("没有记录");
			List<User>users=userBiz.findByClasses(classes);
			
			for(User u:users){
				Attendance attend=new Attendance();
				attend.setClasses(classes);
				attend.setCreateMonth(createMonth);
				attend.setCreateYear(createYear);
				attend.setUser(u);
				attendanceBiz.insert(attend);
			}
		}
		
		return "toAddAttendance";
	}
	
	public void searchByClasses(){
		Classes classes=(Classes) ActionContext.getContext().getSession().get("classes");
		Calendar a=Calendar.getInstance();
		Integer createYear=a.get(Calendar.YEAR);//得到年
		Integer createMonth=a.get(Calendar.MONTH)+1;//由于月份是从0开始的所以加1
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("classes", classes);
		params.put("createYear",createYear);
		params.put("createMonth",createMonth);
		super.setParams(params);
		
		super.search();
	}
	
	public void save(){
		JSONObject json=new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer morning = Integer.parseInt(request.getParameter("morning"));
		Integer afternoon = Integer.parseInt(request.getParameter("afternoon"));
		
		Calendar a=Calendar.getInstance();
		Integer createDay=(a.get(Calendar.DATE));
		Attendance attendance=attendanceBiz.findById(id);
		Method set;
		try {
			set = attendance.getClass().getDeclaredMethod("setDayMorning"+createDay,Integer.class);
			set.invoke(attendance, morning);
			Method setAfternoon=attendance.getClass().getDeclaredMethod("setDayAfternoon"+createDay, Integer.class);
			setAfternoon.invoke(attendance, afternoon);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
			super.outSaveJson(attendance, attendance.getId(), response);
		
	}
	
	public String toAdminChoose(){
		Teacher teacher=(Teacher) ActionContext.getContext().getSession().get("teacherNow");
		List<Classes> classes=classesBiz.findByTeacher(teacher);
		ActionContext.getContext().getSession().put("myClasses",classes);
		
		return "toAdminChoose";
	}
	
	public String toAdminSearch(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Integer classesId=Integer.parseInt(request.getParameter("classesId"));
		String createTime=request.getParameter("createtime");
		Classes classes=classesBiz.findById(classesId);
		ActionContext.getContext().getSession().put("classes",classes);
		ActionContext.getContext().getSession().put("createtime",createTime);
		
		String Day=createTime.substring(8);
		Integer createDay=Integer.parseInt(Day);
		if(Day.charAt(0)==0){
			createDay=Integer.parseInt(Day.substring(1));
		}
		ActionContext.getContext().getSession().put("createday", createDay);
		return "toAdminSearch";
	}
	

	
	public void searchByAdmin(){
		String createTime=(String) ActionContext.getContext().getSession().get("createtime");
		Classes classes=(Classes) ActionContext.getContext().getSession().get("classes");
		
		Integer createYear=Integer.parseInt(createTime.substring(0, 4));
		String Month=createTime.substring(5, 7);
		Integer createMonth=Integer.parseInt(Month);
		if(Month.charAt(0)==0){
			createMonth=Integer.parseInt(Month.substring(1));
		}
		
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("classes", classes);
		params.put("createYear",createYear);
		params.put("createMonth",createMonth);
		super.setParams(params);
		super.search();
	}
}
