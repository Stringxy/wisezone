package com.web.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * DataTable要用到的工具类
 * @author Xy
 *
 */
public class JsonForDataTableUtil {
/**
 * 把查询数据和分页数据json格式化传给前台
 * @param response 当前的response
 * @param paging 
 * @param sEcho
 */
	public static void outJson(HttpServletResponse response,PageUtil paging,String sEcho){

		JSONObject json=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonUtil("yyyy-MM-dd"));
		JSONArray jsonarray=JSONArray.fromObject(paging.getData(),jsonConfig);
		

		json.put("aaData", jsonarray);
		json.put("iTotalRecords", paging.getTotalCount());
		json.put("iTotalDisplayRecords", paging.getTotalCount());
		json.put("sEcho", sEcho);
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


/**
 * 根据前台传过来的分页参数重新计算分页
 * @param iDisplayStart 
 * @param iDisplayLength
 * @param paging
 */
	public static void updatePage(String iDisplayStart,String iDisplayLength,PageUtil paging){
		if(!StringUtil.isEmpty(iDisplayStart)&&!StringUtil.isEmpty(iDisplayLength)){
			int length=Integer.parseInt(iDisplayLength);
			int start=Integer.parseInt(iDisplayStart);
			paging.setPageIndex((int)Math.ceil((double)(start+1)/(double)length));
			paging.setPageSize(length);
			
		}
	}
}
