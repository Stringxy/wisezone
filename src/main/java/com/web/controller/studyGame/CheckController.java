package com.web.controller.studyGame;

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
import com.web.biz.CheckBiz;
import com.web.biz.MajorBiz;
import com.web.biz.UserBiz;
import com.web.controller.CommonPageController;
import com.web.entity.Check;
import com.web.entity.Detail;
import com.web.entity.Major;
import com.web.entity.MajorStage;
import com.web.entity.Question;
import com.web.entity.User;
import com.web.util.JsonForDataTableUtil;
import com.web.util.JsonUtil;
import com.web.util.PageUtil;
import com.web.util.StringUtil;

@Controller
// 控制层的注解
@Scope(value = "prototype")
// 每次请求（每次使用）创建新的实例
public class CheckController extends CommonPageController<Check> implements
		ModelDriven<Check> {

	private List<Major> majors = new ArrayList<Major>();// 前台要用到select list
	private Check check = null;// modelDriven的实体类

	// 要用到的biz
	@Resource(name = "userBizImpl")
	private UserBiz userBiz;

	private CheckBiz checkBiz = null;

	// 设置commonpagecontroller的commonpagebiz
	@Resource(name = "checkBizImpl")
	public void setCheckBiz(CheckBiz checkBiz) {
		this.checkBiz = checkBiz;
		super.setCommonBizPage(checkBiz);
	}

	@Resource(name = "majorBizImpl")
	private MajorBiz majorBiz;

	// 闯关游戏中加载当前玩家对应专业的关卡
	public String getCheck() {
		User user=(User) ActionContext.getContext().getSession().get("userNow");
		
		Detail detail = user.getDetail();// 得到该学生的关卡进度
		
		Check check = detail.getCheck();
		
		Major major = user.getMajors();
		List<Check> checks = checkBiz.findByMajor(major);
		
		ActionContext.getContext().getSession().put("checks", checks);
		Integer count=checks.size();
		Integer temp=0;
		for(Check c:checks){
			if(check.getCheckId()>=c.getCheckId()){
				temp++;
			}
		}
		float percent=temp/(float)count*100;
		
		ActionContext.getContext().getSession()
		.put("percent", percent);
		ActionContext.getContext().getSession()
				.put("nowCheckId", check.getCheckId());
		ActionContext.getContext().getSession()
				.put("nowDetailId", detail.getDetailId());
		return "getCheck";
	}

	// 重写modeldriven的方法
	@Override
	public Check getModel() {
		if (check == null) {
			check = new Check();
		}
		return check;
	}

	// 前台select list对应的getset
	public List<Major> getMajors() {
		return majors;
	}

	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}

	// 跳转到关卡页面，在之前加载好select list中的数据
	public String toCheckJsp() {
		this.majors = majorBiz.findAll();

		ActionContext.getContext().getSession().put("majors", majors);

		return "toCheckJsp";
	}

	// 保存方法
	public void save() {
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();

		this.check.setMajor(majorBiz.findById(Integer.parseInt(request
				.getParameter("majorId"))));
		super.outSaveJson(check, check.getCheckId(), response);
	}

	// 删除
	public void del() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		Integer id =Integer.parseInt(request.getParameter("checkId"));
		super.outDelJson(id, response);
	}

}
