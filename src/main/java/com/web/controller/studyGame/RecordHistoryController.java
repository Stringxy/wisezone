package com.web.controller.studyGame;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.biz.DetailBiz;
import com.web.biz.QuestionBiz;
import com.web.biz.RecordHistoryBiz;
import com.web.biz.RecordHistoryDetailBiz;
import com.web.biz.UserBiz;
import com.web.entity.Detail;
import com.web.entity.Question;
import com.web.entity.RecordHistory;
import com.web.entity.RecordHistoryDetail;
import com.web.entity.User;
import com.web.util.JsonForDataTableUtil;
import com.web.util.JsonUtil;
import com.web.util.PageUtil;

@Controller
// 控制层的注解
@Scope(value = "prototype")
// 每次请求（每次使用）创建新的实例
public class RecordHistoryController extends ActionSupport {
	@Resource(name = "questionBizImpl")
	private QuestionBiz questionBiz;
	@Resource(name = "detailBizImpl")
	private DetailBiz detailBiz;
	@Resource(name = "recordHistoryBizImpl")
	private RecordHistoryBiz recordHistoryBiz;
	@Resource(name = "userBizImpl")
	private UserBiz userBiz;
	@Resource(name="pageUtil")
	private PageUtil<RecordHistory>paging;
	@Resource(name="recordHistoryDetailBizImpl")
	private RecordHistoryDetailBiz rhDetailBiz;
	
	public String addRecordHistory() {
		RecordHistory record = new RecordHistory();// 新建要新增的record
		
		record.setCorrect(0);// 初始化正确数量
		record.setErrors(0);// 初始化错误数量
		HttpServletRequest request = ServletActionContext.getRequest();
		// 取得当前用户
		User user = (User) ActionContext.getContext().getSession()
				.get("userNow");
		
		record.setUser(user);
		// 取得当前关卡
		int detailId = (int) (ActionContext.getContext().getSession()
				.get("detailId"));
		Detail detail = detailBiz.findById(detailId);
		record.setDetail(detail);
		
//		 Enumeration<String> paraNames=request.getParameterNames();
//		 for(Enumeration e=paraNames;e.hasMoreElements();){
//		 String questionId=e.nextElement().toString();
//		 Question question=questionBiz.findById(Integer.parseInt(questionId));
//		 String answer=request.getParameter(questionId);
//		 System.out.println("问题编号："+questionId+"\t提交答案："+answer);
//		 }
		record.setCorrect(0);
		record.setErrors(0);
		record.setCreateDate(new Date());
		record.setPass(0);
		recordHistoryBiz.insert(record);
		
		// 取得当前关卡的所有问题
		List<Question> questions = questionBiz.findByDetail(detail);
		for (Question question : questions) {
			String answer = request.getParameter(question.getQuestionId()
					.toString());
			RecordHistoryDetail rhDetail=new RecordHistoryDetail();
			rhDetail.setQuestionId(question.getQuestionId());
			rhDetail.setStuAnswer(answer);
			rhDetail.setCreateDate(new Date());
			rhDetail.setRecord(record);
			rhDetailBiz.insert(rhDetail);
			if (answer.equals(question.getRightAnswer())) {
				record.setCorrect(record.getCorrect() + 1);
			} else {
				record.setErrors(record.getErrors() + 1);
			}
		}

		JSONObject json = new JSONObject();
		if (record.getCorrect() >= detail.getCorrectNum()) {
			record.setPass(1);
			json.put("success", true);
			json.put("message", "恭喜你，闯关成功！");
			user.setDetail(detail);
			userBiz.update(user);
		} else {
			record.setPass(0);
			json.put("success", false);
			json.put("message", "闯关失败！错了"+record.getErrors()+"道题！");
		}


		try {
			recordHistoryBiz.update(record);
		} catch (Exception e) {
			json.put("success", false);
			json.put("message", "系统出错，请联系程序猿！");

		}

		HttpServletResponse response = ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JsonUtil.outJson(response, json);
		
		return "addRecordHistory";
	}

	
	public void search(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String iDisplayStart=(request.getParameter("iDisplayStart"));
		String iDisplayLength=(request.getParameter("iDisplayLength"));
		String sEcho = (request.getParameter("sEcho"));
		JsonForDataTableUtil.updatePage(iDisplayStart,
				iDisplayLength, paging);
		recordHistoryBiz.searchPaging(null, paging);
		HttpServletResponse response = ServletActionContext.getResponse();
		JsonForDataTableUtil.outJson(response, paging, sEcho);
	}
	
	public void getRHDetail(){
		System.out.println("进入了getRhdetail");
		HttpServletRequest request=ServletActionContext.getRequest();
		int recordId=Integer.parseInt(request.getParameter("recordid"));
		RecordHistory rh=recordHistoryBiz.findById(recordId);
		List<RecordHistoryDetail>rhDetails=rhDetailBiz.findByRecord(rh);
		System.out.println("查出了details："+rhDetails.size());
		Map<String,String>detailMap=new HashMap<String,String>();
		JSONArray json=new JSONArray();
		for(RecordHistoryDetail rhd:rhDetails){
			JSONObject obj=new JSONObject();
			obj.put("questionName", questionBiz.findById(rhd.getQuestionId()).getQuestionName());
			obj.put("stuAnswer", rhd.getStuAnswer());
			json.add(obj);
		}
		System.out.println(json.toString());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JsonUtil.outJson(response, json);
	}
}
