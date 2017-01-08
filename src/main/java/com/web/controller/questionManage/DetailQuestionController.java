package com.web.controller.questionManage;

import java.util.ArrayList;
import java.util.List;

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
import com.web.biz.DetailBiz;
import com.web.biz.DetailQuestionBiz;
import com.web.biz.QuestionBiz;
import com.web.entity.Detail;
import com.web.entity.DetailQuestion;
import com.web.entity.Question;
import com.web.util.JsonForDataTableUtil;
import com.web.util.JsonUtil;
import com.web.util.PageUtil;
import com.web.util.StringUtil;
@Controller
//控制层的注解
@Scope(value = "prototype")
//每次请求（每次使用）创建新的实例
public class DetailQuestionController extends ActionSupport implements
		ModelDriven<DetailQuestion> {
	@Resource(name="pageUtil")
	private PageUtil<DetailQuestion>paging;
	private DetailQuestion detailQuestion=null;
	@Resource(name="detailQuestionBizImpl")
	private DetailQuestionBiz detailQuestionBiz;
	
	@Resource(name = "detailBizImpl")
	private DetailBiz detailBiz;
	@Resource(name = "questionBizImpl")
	private QuestionBiz questionBiz;
	private List<Detail>details=new ArrayList<Detail>();
	private List<Question>questions=new ArrayList<Question>();
	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public DetailQuestion getModel() {
		if(detailQuestion==null){
			detailQuestion=new DetailQuestion();
		}
		return detailQuestion;
	}
	
	public void search(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String iDisplayStart = (request.getParameter("iDisplayStart"));
		String iDisplayLength = (request.getParameter("iDisplayLength"));
		String sEcho = (request.getParameter("sEcho"));
		JsonForDataTableUtil.updatePage(iDisplayStart, iDisplayLength, paging);
		detailQuestionBiz.searchPaging(null, paging);
		HttpServletResponse response = ServletActionContext.getResponse();
		JsonForDataTableUtil.outJson(response, paging, sEcho);
	}
	
	public String toDetailQuestionJsp(){
		this.details=detailBiz.findAll();
		this.questions=questionBiz.findAll();
		ActionContext.getContext().getSession().put("details", details);
		ActionContext.getContext().getSession().put("questions", questions);
		return "toDetailQuestionJsp";
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
		this.detailQuestion.setDetail(detailBiz.findById(Integer.parseInt(request.getParameter("detailId"))));
		this.detailQuestion.setQuestion(questionBiz.findById(Integer.parseInt(request.getParameter("questionId"))));
		JSONObject json=new JSONObject();
		if(detailQuestion.getDetailQuestionId()==0){
			
			try {
				detailQuestionBiz.insert(detailQuestion);
				json.put("success", true);
				json.put("message", "新增成功");
			} catch (Exception e) {
				json.put("success", false);
				json.put("message", "新增失败");
			}
		}else{
			try {
				detailQuestionBiz.update(detailQuestion);
				json.put("success", true);
				json.put("message", "修改成功");
			} catch (Exception e) {
				json.put("success", false);
				json.put("message", "修改失败");
			}
		}
		JsonUtil.outJson(response, json);
	}
	
	public void del(){

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();

		Integer dqid = null;
		if (!StringUtil.isEmpty(request.getParameter("detailQuestionId"))) {
			
			dqid = Integer.parseInt(request.getParameter("detailQuestionId"));
		
			try {
				detailQuestionBiz.delete(dqid);

				json.put("success", true);
				json.put("message", "删除成功");
			} catch (Exception e) {
		
				json.put("success", false);
				json.put("message", "删除失败");
			}
		}
		JsonUtil.outJson(response, json);
	}
	
	
}
