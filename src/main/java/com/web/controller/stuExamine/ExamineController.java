package com.web.controller.stuExamine;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.biz.ClassesBiz;
import com.web.biz.ExamResultBiz;
import com.web.biz.ExamTypeBiz;
import com.web.biz.TeacherBiz;
import com.web.biz.ExamFormBiz;
import com.web.entity.Classes;
import com.web.entity.ExamForm;
import com.web.entity.ExamResult;
import com.web.entity.ExamType;
import com.web.entity.Teacher;
/**
 * 问卷调查action
 * @author johen.deng
 *
 */
@Controller
@Scope(value="request")//用户请求时创建新的实例
public class ExamineController extends ActionSupport  {
	private  int choice;
	
	@Resource(name="examFormBizImpl")
	private ExamFormBiz ExamFormBiz;
	
	@Resource(name="teacherBizImpl")
	private TeacherBiz teacherBiz; 
	@Resource(name="classesBizImpl")
	private ClassesBiz classesBiz;

	@Resource(name="examTypeBizImpl")
	private ExamTypeBiz examTypeBiz;
	@Resource(name="examResultBizImpl")
	private ExamResultBiz examResultBiz;
	
	private String examTeacher;
	private String[] score;
	private Integer[] ids;
	private String examClass;
	private String psInfo;
	
	private List<ExamForm> arr=null;
	private List<Teacher> teaArr=null;
	private  List<Classes> claArr=null;
	
	@Override
	public  String  execute() throws Exception {
		System.out.println("得到了用户选择的问卷编号"+choice);
		teaArr=teacherBiz.findAll();
		claArr=classesBiz.findAll();
		ActionContext.getContext().getSession().put("teachers", teaArr);
		ActionContext.getContext().getSession().put("classes", claArr);
		arr=ExamFormBiz.findByTypeId(choice);
		ActionContext.getContext().getSession().put("examine", arr);
		return SUCCESS;
		
	}
	public String saveResult() {
		System.out.println("得到了用户选择的问卷编号"+choice);
		System.out.println("得到了"+examTeacher);
		System.out.println("得到了"+score.length);
		System.out.println("得到了"+ids.length);
		System.out.println("得到了"+examClass);
		System.out.println("得到了"+psInfo);
		int allScore=0;
		for(Integer s:ids){
			allScore+=s.intValue();
		}
		System.out.println("总分是----"+allScore);
		double examScore=0;
		for(int i=0;i<score.length;i++){
			if(NumberUtils.isNumber(score[i])){
				examScore+=Double.valueOf(score[i]);
			}else{
				super.addActionError("骚年,请用数字打分");
				return INPUT;
			}
		}
		System.out.println("老师的得分是----"+examScore);
		ExamResult res=new ExamResult();
		
		String ip=ServletActionContext.getRequest().getRemoteAddr();
		System.out.println(ip);
		ExamType examType=examTypeBiz.findById(choice);
		Date examDate=new Date();
		System.out.println(examDate);
		Teacher tea=teacherBiz.findById(Integer.valueOf(examTeacher));
		Classes cla=classesBiz.findById(Integer.valueOf(examClass));
		res.setComIp(ip);
		res.setExamType(examType);
		res.setExamDate(examDate);
		res.setTeacher(tea);
		res.setClasses(cla);
		res.setExamScore(examScore);
		res.setAllScore(allScore);
		res.setExamPs(psInfo);
		examResultBiz.insert(res);
		return "saveResult";
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}

	public String getExamTeacher() {
		return examTeacher;
	}

	public void setExamTeacher(String examTeacher) {
		this.examTeacher = examTeacher;
	}

	public String[] getScore() {
		return score;
	}

	public void setScore(String[] score) {
		this.score = score;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public String getExamClass() {
		return examClass;
	}

	public void setExamClass(String examClass) {
		this.examClass = examClass;
	}

	public String getPsInfo() {
		return psInfo;
	}

	public void setPsInfo(String psInfo) {
		this.psInfo = psInfo;
	}
	
	
}
