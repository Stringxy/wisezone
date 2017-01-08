package com.web.controller.studyGame;

import java.util.ArrayList;
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
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.biz.CheckBiz;
import com.web.biz.DetailBiz;
import com.web.controller.CommonPageController;
import com.web.entity.Check;
import com.web.entity.Detail;
import com.web.util.JsonForDataTableUtil;
import com.web.util.JsonUtil;
import com.web.util.PageUtil;
import com.web.util.StringUtil;

@Controller
// 控制层的注解
@Scope(value = "prototype")
// 每次请求（每次使用）创建新的实例
public class DetailController extends CommonPageController<Detail> implements
ModelDriven<Detail>  {

	@Resource(name = "checkBizImpl")
	private CheckBiz checkBiz;
	private DetailBiz detailBiz=null;
	@Resource(name = "detailBizImpl")
	public void setDetailBiz(DetailBiz detailBiz){
		this.detailBiz=detailBiz;
		super.setCommonBizPage(detailBiz);
	}
	private int checkId;
	List<Check>checks=new ArrayList<Check>();
	private Detail detail=null;
	public List<Check> getChecks() {
		return checks;
	}

	public void setChecks(List<Check> checks) {
		this.checks = checks;
	}

	public String getDetail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (!StringUtil.isEmpty(request.getParameter("checkId"))) {
			this.checkId = Integer.parseInt(request.getParameter("checkId"));
		}
		Check check = checkBiz.findById(this.checkId);
		List<Detail> details = detailBiz.findByCheck(check);

		ActionContext.getContext().getSession().put("details", details);
		return "getDetail";
	}
	
	public String toDetailJsp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Check check=checkBiz.findById(Integer.parseInt(request.getParameter("checkId")));
		ActionContext.getContext().getSession().put("checkNow", check);
		List<Check>checks=checkBiz.findAll();
		ActionContext.getContext().getSession().put("checks", checks);
		return "toDetailJsp";
	}
	
	public void searchByCheck(){
		Check check=(Check)ActionContext.getContext().getSession().get("checkNow");
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("check",check);
		super.setParams(params);
		super.search();
	}
	
	public void save(){
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		this.detail.setCheck(checkBiz.findById(Integer.parseInt(request.getParameter("checkId"))));
		super.outSaveJson(detail, detail.getDetailId(), response);
	}

	@Override
	public Detail getModel() {
		if(detail==null){
			detail=new Detail();
		}
		return detail;
	}
	
	public void del(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		Integer detailid = Integer.parseInt(request.getParameter("detailId"));
		super.outDelJson(detailid, response);
	}
	
	public void valid(){
	
		JSONObject json = new JSONObject();
		Detail old=detailBiz.findById(this.detail.getDetailId());
		if(old.getCheckName()==(this.detail.getCheckName())){
			json.put("valid", true);
		}else{
			json.put("valid", detailBiz.isVaild(this.detail.getCheckName()));
		}
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JsonUtil.outJson(response, json);
	}
}
