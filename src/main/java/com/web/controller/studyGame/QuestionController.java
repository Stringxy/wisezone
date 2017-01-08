package com.web.controller.studyGame;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
import com.web.biz.DetailBiz;
import com.web.biz.DetailQuestionBiz;
import com.web.biz.MajorBiz;
import com.web.biz.MajorStageBiz;
import com.web.biz.QuestionBiz;
import com.web.controller.CommonPageController;
import com.web.entity.Check;
import com.web.entity.Detail;
import com.web.entity.DetailQuestion;
import com.web.entity.Major;
import com.web.entity.MajorStage;
import com.web.entity.Question;
import com.web.util.JsonForDataTableUtil;
import com.web.util.JsonUtil;
import com.web.util.PageUtil;
import com.web.util.StringUtil;

@Controller //控制层的注解
@Scope(value="prototype")//每次请求（每次使用）创建新的实例
public class QuestionController extends CommonPageController<Question>  implements ModelDriven<Question>  {

	private InputStream inputStream; // 读取文件的的流
	private String fileName; // 发给浏览器的给够看到的名称
	private List<File> uploadFiles;
	private List<String> uploadFilesFileName;
	private List<String> uploadFilesContentType;
	private String extMap="xls,xlsx";
	
	
	public InputStream getInputStream() {
		return inputStream;
	}





	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public List<File> getUploadFiles() {
		return uploadFiles;
	}


	public void setUploadFiles(List<File> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}


	public List<String> getUploadFilesFileName() {
		return uploadFilesFileName;
	}


	public void setUploadFilesFileName(List<String> uploadFilesFileName) {
		this.uploadFilesFileName = uploadFilesFileName;
	}


	public List<String> getUploadFilesContentType() {
		return uploadFilesContentType;
	}


	public void setUploadFilesContentType(List<String> uploadFilesContentType) {
		this.uploadFilesContentType = uploadFilesContentType;
	}
	
	@Resource(name="detailBizImpl")
	private DetailBiz detailBiz;
	private QuestionBiz questionBiz;
	@Resource(name="detailQuestionBizImpl")
	private DetailQuestionBiz detailQuestionBiz;
	@Resource(name="questionBizImpl")
	public void setQuestionBiz(QuestionBiz questionBiz){
		this.questionBiz=questionBiz;
		super.setCommonBizPage(questionBiz);
	}
	private Question question=null;

	@Resource(name="majorBizImpl")
	private MajorBiz majorBiz;
	@Resource(name="majorStageBizImpl")
	private MajorStageBiz majorStageBiz;
	private List<Major>majors=new ArrayList<Major>();
	private List<MajorStage>stages=new ArrayList<MajorStage>();
	public List<MajorStage> getStages() {
		return stages;
	}

	public void setStages(List<MajorStage> stages) {
		this.stages = stages;
	}

	public List<Major> getMajors() {
		return majors;
	}

	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}

	@Override
	public Question getModel() {
		if(question==null){
			question=new Question();
		}
		return question;
	}
	
	public void searchByDetail(){
		Detail detail=(Detail)ActionContext.getContext().getSession().get("detailNow");
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("detail", detail);
		super.setParams(params);
		super.search();
	}
	
	public void searchByCheck(){
		Check check=(Check)ActionContext.getContext().getSession().get("checkNow");
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("major", check.getMajor());
		super.setParams(params);
		super.search();
	}
	
	public String getQuestion(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Integer detailId=Integer.parseInt(request.getParameter("detailId"));
		Detail detail=detailBiz.findById(detailId);
		List<Question>questions=questionBiz.findByDetail(detail);
		ActionContext.getContext().getSession().put("questions", questions);
		ActionContext.getContext().getSession().put("detailId",detailId);
		return "getQuestion";
	}
	
	public String toQuestionJsp(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Integer detailId=Integer.parseInt(request.getParameter("detailId"));
		Detail detail=detailBiz.findById(detailId);
		this.majors=majorBiz.findAll();
		this.stages=majorStageBiz.findAll();
		ActionContext.getContext().getSession().put("detailNow", detail);
		ActionContext.getContext().getSession().put("majors", majors);
		ActionContext.getContext().getSession().put("stages", stages);
		return "toQuestionJsp";
	}
	

	
//	public void save(){
//		HttpServletResponse response = ServletActionContext.getResponse();
//		/*
//		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
//		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
//		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
//		 */
//		response.setContentType("text/html;charset=utf-8");
//		response.setCharacterEncoding("UTF-8");
//		HttpServletRequest request = (HttpServletRequest) ServletActionContext
//				.getRequest();
//		this.question.setMajor(majorBiz.findById(Integer.parseInt(request.getParameter("majorId"))));
//		this.question.setMajorstage(majorStageBiz.findById(Integer.parseInt(request.getParameter("stageId"))));
//		super.outSaveJson(question, question.getQuestionId(), response);
//	}
	
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
		
		String str=request.getParameter("questions");
		Detail detail=(Detail)ActionContext.getContext().getSession().get("detailNow");
		JSONObject json=new JSONObject();
		String [] arr=str.split(",");
		for(String a:arr){
			Integer questionId=Integer.parseInt(a);
			Question question=questionBiz.findById(questionId);
			DetailQuestion dq=new DetailQuestion();
			dq.setQuestion(question);
			dq.setDetail(detail);
			try {
				detailQuestionBiz.insert(dq);
				json.put("success", true);
				json.put("message", "新增试题成功！");
			} catch (Exception e) {
				json.put("success", false);
				json.put("message", "新增试题失败！报错信息："+e.getMessage());
				e.printStackTrace();
			}
		}
		
		
		JsonUtil.outJson(response, json);
	}
	
	public void del(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		Detail detail=(Detail)ActionContext.getContext().getSession().get("detailNow");
		JSONObject json = new JSONObject();
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		Integer questionId=Integer.parseInt(request.getParameter("questionId"));
		Question q=questionBiz.findById(questionId);
		DetailQuestion dq=detailQuestionBiz.findByQuestion(q,detail);
		
		try {
			detailQuestionBiz.delete(dq);
			json.put("success", true);
			json.put("message", "移除成功！");
		} catch (Exception e) {
			json.put("success", true);
			json.put("message", "移除失败！");
			e.printStackTrace();
		}
		JsonUtil.outJson(response, json);
	}
	
	public String toQuestionDetailJsp(){
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		Integer questionId=Integer.parseInt(request.getParameter("questionId"));
		Question question=questionBiz.findById(questionId);
		ActionContext.getContext().getSession().put("questionNow", question);
		return "toQuestionDetailJsp";
	}
	
	public String downloadTemp(){
		String path=ServletActionContext.getServletContext().getRealPath("/static/excelTemp/questionTemp.xlsx");
		File excel=new File(path);
		fileName="questionTemp.xlsx";
		try {
			this.inputStream = new BufferedInputStream(
					new FileInputStream(path));
		} catch (FileNotFoundException e) {
			System.out.println("文件没有找到");
		
		}
		return SUCCESS;
	}
}
