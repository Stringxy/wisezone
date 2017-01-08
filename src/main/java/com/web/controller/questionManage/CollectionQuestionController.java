package com.web.controller.questionManage;

import java.util.Date;
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
import com.web.biz.CollectionQuestionBiz;
import com.web.biz.QuestionBiz;
import com.web.biz.UserBiz;
import com.web.controller.CommonPageController;
import com.web.entity.CollectionQuestion;
import com.web.entity.Question;
import com.web.entity.User;
import com.web.util.JsonUtil;

@Controller
//控制层的注解
@Scope(value = "prototype")
//每次请求（每次使用）创建新的实例
public class CollectionQuestionController extends CommonPageController<CollectionQuestion> implements
		ModelDriven<CollectionQuestion> {

	private CollectionQuestionBiz collectionQuestionBiz;
	
	@Resource(name="collectionQuestionBizImpl")
	public void setCollectionQuestionBiz(CollectionQuestionBiz collectionQuestionBiz){
		this.collectionQuestionBiz=collectionQuestionBiz;
		super.setCommonBizPage(collectionQuestionBiz);
	}
	// 要用到的biz
	@Resource(name = "userBizImpl")
	private UserBiz userBiz;
	@Resource(name="questionBizImpl")
	private QuestionBiz questionBiz;
	private CollectionQuestion collectionQuestion=null;
	@Override
	public CollectionQuestion getModel() {
		if(collectionQuestion==null){
			collectionQuestion=new CollectionQuestion();
		}
		return collectionQuestion;
	}

	
	public void addCollectionQuestion(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		User user=(User) ActionContext.getContext().getSession().get("userNow");
		Integer questionId=Integer.parseInt(request.getParameter("questionId"));
		Question question=questionBiz.findById(questionId);
		if(collectionQuestionBiz.isExist(user, question)){
			JSONObject json = new JSONObject();
			json.put("success", false);
			json.put("message", "你已收藏了此题！");
			JsonUtil.outJson(response, json);
		}
		CollectionQuestion cq=new CollectionQuestion();
		cq.setCreateDate(new Date());
		cq.setQuestion(question);
		cq.setUser(user);
		
		super.outSaveJson(cq, 0, response);
		
	}
	
	public String getMyCollectionQuestion(){
		User user=(User) ActionContext.getContext().getSession().get("userNow");
		List<CollectionQuestion> collectionQuestions=collectionQuestionBiz.findByUser(user);
		ActionContext.getContext().getSession().put("mycollectionquestions", collectionQuestions);
		
		return "getMyCollectionQuestion";
	}
}
