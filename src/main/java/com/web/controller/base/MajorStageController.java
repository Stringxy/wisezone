package com.web.controller.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.biz.MajorBiz;
import com.web.biz.MajorStageBiz;
import com.web.entity.Major;
import com.web.entity.MajorStage;
import com.web.util.JsonForDataTableUtil;
import com.web.util.PageUtil;
/**
 * 
 * 专业控制器
 * @author cheng
 *
 */
@Controller
@Scope(value = "prototype")
public class MajorStageController extends ActionSupport implements ModelDriven<MajorStage>{


//每次请求（每次使用）创建新的实例



	@Resource(name="pageUtil")
	private PageUtil<MajorStage> paging;
	@Resource(name="majorStageBizImpl")
	private MajorStageBiz majorstagebiz;
	private String iDisplayStart;
	private String iDisplayLength;
	private String sEcho;
	private MajorStage majorStage=null;
	@Resource(name="majorBizImpl")
	private  MajorBiz majorBiz;
	private List<Major> major;
	

	public List<Major> getMajor() {
		return major;
	}



	public void setMajor(List<Major> major) {
		this.major = major;
	}



	@Override
	public MajorStage getModel() {
		if(majorStage== null){
			majorStage=new  MajorStage();
		}
		return majorStage;
	}
	
	
	
	public MajorStage getMajorStage() {
		return majorStage;
	}



	public void setMajorStage(MajorStage majorStage) {
		this.majorStage = majorStage;
	}


	//*************getter，setter*********
	
	/**
	 * 全查询+分页
	 * 分页处理封装在JsonForDataTableUtil中，直接调updatePage即可
	 * json处理封装在JsonForDataTableUtil中，直接调outJson即可
	 */
	public void search(){
		HttpServletRequest request=ServletActionContext.getRequest();
		this.iDisplayStart=(request.getParameter("iDisplayStart"));
		this.iDisplayLength=(request.getParameter("iDisplayLength"));
		this.sEcho = (request.getParameter("sEcho"));

		JsonForDataTableUtil.updatePage(this.iDisplayStart,
				this.iDisplayLength, this.paging);
		
		
		majorstagebiz.searchPaging(null, paging);
		HttpServletResponse response = ServletActionContext.getResponse();
		JsonForDataTableUtil.outJson(response, paging, sEcho);
	}

	public  String  add(){
		
		this.major=majorBiz.findAll();
		ActionContext.getContext().getSession().put("major", major);
		System.out.println(this.major.get(0).getMajorId());
		System.out.println(this.major.get(0).getMajorName());
		return  "add";
	}
	
	
  public String addSaveMajorStage(){
	 Major major=(Major)ActionContext.getContext().getSession().get("majorNow");
	 majorStage.setMajor(major);
	 
	  
	  try {
		 
		  majorstagebiz.insert(majorStage);
		  
		  return SUCCESS;
	} catch (Exception e) {
		
		return INPUT;
	}
	 
	  
	  
  }


	public void searchByMajor(){
		Major major=(Major)ActionContext.getContext().getSession().get("majorNow");
		HttpServletRequest request=ServletActionContext.getRequest();
		this.iDisplayStart=(request.getParameter("iDisplayStart"));
		this.iDisplayLength=(request.getParameter("iDisplayLength"));
		this.sEcho = (request.getParameter("sEcho"));
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("major", major);
		JsonForDataTableUtil.updatePage(this.iDisplayStart,
				this.iDisplayLength, this.paging);
		
		
		majorstagebiz.searchPaging(params, paging);
		HttpServletResponse response = ServletActionContext.getResponse();
		JsonForDataTableUtil.outJson(response, paging, sEcho);
	}



	
}
