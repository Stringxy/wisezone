package com.web.controller.base;

import java.util.HashMap;
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
import com.web.biz.CityBiz;
import com.web.biz.MajorBiz;
import com.web.biz.impl.MajorBizImpl;
import com.web.entity.City;
import com.web.entity.Major;
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
//每次请求（每次使用）创建新的实例
public class MajorController extends ActionSupport implements ModelDriven<Major> {

	@Resource(name="pageUtil")
	private PageUtil<Major> paging;
	@Resource(name="majorBizImpl")
	private MajorBiz majorbiz;
	private String iDisplayStart;
	private String iDisplayLength;
	private String sEcho;
	private  Major major;
	@Override
	public Major getModel() {
		if(major==null){
			major= new Major();
		}
		return major;
	}
	



	public Major getMajor() {
		return major;
	}




	public void setMajor(Major major) {
		this.major = major;
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
		majorbiz.searchPaging(null, paging);
		HttpServletResponse response = ServletActionContext.getResponse();
		JsonForDataTableUtil.outJson(response, paging, sEcho);
	}


	
	
 public String addSaveMajor(){
	
	 try {
		 majorbiz.insert(major);
		 
		 super.addActionError("新增成功！");
		 return SUCCESS;
	} catch (Exception e) {
		super.addActionError("新增失败");
		return INPUT;
	}
	 
}
 
  public String edit(){
	  this.major = this.majorbiz.findById(this.major.getMajorId());
	  System.out.println(this.major.getMajorId());
	  String majorName=this.major.getMajorName();
	  System.out.println(majorName);
	  ActionContext.getContext().getSession().put("majorName", majorName);
	  return  "edit";
  }

public String  editSaveMajor(){
	
	majorbiz.update(major);
	System.out.println(this.major.getMajorId());
	
	return SUCCESS;
}
//public String deleteMajor(){
//	
//	 Integer id=  this.major.getMajorId();
//	 
//	 this.majorbiz.delete(id);
//	 
//	return "deleteMajor";
//}
	
	public String toTheStage(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Integer majorId=Integer.parseInt(request.getParameter("majorId"));
		Major major=majorbiz.findById(majorId);
		ActionContext.getContext().getSession().put("majorNow", major);
		return "toTheStage";
	}
	

}
