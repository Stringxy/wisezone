package com.web.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.web.biz.CommonBizPage;
import com.web.util.JsonForDataTableUtil;
import com.web.util.JsonUtil;
import com.web.util.PageUtil;

public class CommonPageController<T> extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//通用的分页类
	@Resource(name = "pageUtil")
	protected PageUtil<T> paging;
	
	protected Map<String,Object>params=null;
	
	protected void setParams(Map<String,Object>params){
		this.params=params;
	}
	//通用的biz
	private CommonBizPage<T> commonBizPage=null;
	
	protected void setCommonBizPage(CommonBizPage<T> commonBizPage){
		this.commonBizPage=commonBizPage;
	}
	
	//通用的search方法
	public void search(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String iDisplayStart = (request.getParameter("iDisplayStart"));
		String iDisplayLength = (request.getParameter("iDisplayLength"));
		String sEcho = (request.getParameter("sEcho"));
		JsonForDataTableUtil.updatePage(iDisplayStart, iDisplayLength, paging);
		commonBizPage.searchPaging(params, paging);
		HttpServletResponse response = ServletActionContext.getResponse();
		JsonForDataTableUtil.outJson(response, paging, sEcho);
	}
	
	/**
	 * 返回新增修改的json结果
	 * @param t 要新增或修改的实体类
	 * @param id 该实体类的id（前台传过来判断新增或修改的id）
	 * @param response 当前的响应
	 */
	protected void outSaveJson(T t,Integer id,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		if ( id==null||id== 0) {
			
			try {
				commonBizPage.insert(t);;
				json.put("success", true);
				json.put("message", "新增成功");
			} catch (Exception e) {
				json.put("success", false);
				json.put("message", "新增失败"+e.getMessage());
			}
		} else {
			try {
				commonBizPage.update(t);
				json.put("success", true);
				json.put("message", "修改成功");
			} catch (Exception e) {
				json.put("success", false);
				json.put("message", "修改失败"+e.getMessage());
			}
		}
		JsonUtil.outJson(response, json);
	}
	
	
	/**
	 * 返回删除的json结果
	 * @param id 前台传过来删除的id
	 * @param response
	 */
	protected void outDelJson(Integer id,HttpServletResponse response){
		JSONObject json = new JSONObject();
	
		
			try {
				commonBizPage.delete(id);
				json.put("success", true);
				json.put("message", "删除成功");
			} catch (Exception e) {
				json.put("success", false);
				json.put("message", "删除失败"+e.getMessage());
			}
		
		JsonUtil.outJson(response, json);
	}
	
}
