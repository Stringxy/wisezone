package com.web.controller.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.biz.CityBiz;
import com.web.entity.City;
import com.web.util.JsonForDataTableUtil;
import com.web.util.JsonUtil;
import com.web.util.PageUtil;
import com.web.util.StringUtil;

/**
 * city控制层
 * 
 * @author Xy
 *
 */
@Controller
// 控制层的注解
@Scope(value = "prototype")
// 每次请求（每次使用）创建新的实例
public class CityController extends ActionSupport implements ModelDriven<City> {

	@Resource(name = "pageUtil")
	private PageUtil<City> paging;
	@Resource(name = "cityBizImpl")
	private CityBiz citybiz;
	private String iDisplayStart;
	private String iDisplayLength;
	private String sEcho;
	private City city = null;

	@Override
	public City getModel() {
		if (city == null) {
			city = new City();
		}
		return city;
	}

	// *************getter，setter*********

	/**
	 * 全查询+分页 分页处理封装在JsonForDataTableUtil中，直接调updatePage即可
	 * json处理封装在JsonForDataTableUtil中，直接调outJson即可
	 */
	public void search() {
		HttpServletRequest request = ServletActionContext.getRequest();
		this.iDisplayStart = (request.getParameter("iDisplayStart"));
		this.iDisplayLength = (request.getParameter("iDisplayLength"));
		this.sEcho = (request.getParameter("sEcho"));

		JsonForDataTableUtil.updatePage(this.iDisplayStart,
				this.iDisplayLength, this.paging);
		citybiz.searchPaging(null, paging);
		HttpServletResponse response = ServletActionContext.getResponse();
		JsonForDataTableUtil.outJson(response, paging, sEcho);
	}


	public String editCity() {
		Integer id ;
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		
			id= Integer.parseInt(request.getParameter("id"));
		
		
		if (id != 0) {
			this.city = citybiz.findById(id);

			ActionContext.getContext().getSession().put("cityid", id);
			ActionContext.getContext().getSession()
					.put("cityname", this.city.getName());
		} else {
			ActionContext.getContext().getSession().put("cityid", id);
			ActionContext.getContext().getSession().put("cityname", null);
		}
		return "editCity";
	}

	public void saveCity() {

		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		JSONObject json = new JSONObject();
		String cityname = request.getParameter("name");
		Integer id = (Integer) ActionContext.getContext().getSession()
				.get("cityid");
		if (id != 0) {
			this.city = citybiz.findById(id);
			this.city.setName(cityname);

			try {
				this.citybiz.update(city);
			} catch (Exception e) {
				json.put("success", false);
				json.put("message", "修改失败");
			}
			json.put("success", true);
			json.put("message", "修改成功");

		} else {

			City citynew = new City();
			citynew.setName(cityname);
			try {
				this.citybiz.insert(citynew);
			} catch (Exception e) {
				json.put("success", false);
				json.put("message", "新增失败");
			}
			json.put("success", true);
			json.put("message", "新增成功");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JsonUtil.outJson(response, json);

	}
	
	
	public String deleteCity(){
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		
		Integer id= Integer.parseInt(request.getParameter("id"));
		System.out.println(request.getParameter("id"));
		try {
			citybiz.delete(id);
			super.addActionError("删除成功！");
			return "deleteCity";
		} catch (Exception e) {
			
			super.addActionError("删除失败！");
			
			return INPUT;
		}
		
		
		
		
	}

	public void validateSave(){
		if(StringUtil.isEmpty(this.city.getName())){
		super.addFieldError("name", "城市名不能为空！");
		
		}else{
			if(!this.citybiz.addCityNameExists(this.city.getName())){
				super.addFieldError("name", "不要重复使用该名称！");
			}
		}
	}
	
}
