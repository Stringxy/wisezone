package com.web.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	public static void outJson(HttpServletResponse response,JSONObject json){
		try {
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public static void outJson(HttpServletResponse response,JSONArray json){
		try {
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
