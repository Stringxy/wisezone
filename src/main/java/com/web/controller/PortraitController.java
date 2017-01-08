package com.web.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.biz.TeacherBiz;
import com.web.biz.UserBiz;
import com.web.entity.Teacher;
import com.web.entity.User;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;
@Controller //控制层ioc注释
@Scope(value="prototype") //每次请求创建新的实例
public class PortraitController extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private List<File> uploadFiles;
	private List<String> uploadFilesFileName;
	private List<String> uploadFilesContentType;
	private String extMap="jpg,bmp,png";
	@Resource(name="userBizImpl")
	private UserBiz userBiz;
	@Resource(name="teacherBizImpl")
	private TeacherBiz teacherBiz;
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
	

	public void uploadPortrait(){
		JSONObject json=new JSONObject();
		String savePath=ServletActionContext.getServletContext().getRealPath("/static/Portraits/");
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		String portraitURL="/static/Portraits/";
		String type=request.getParameter("type");
		String name=null;
		User user=null;
		Teacher teacher=null;
		if(type.equals("teacher")){
			name=request.getParameter("teacherName");
			teacher=teacherBiz.findByName(name);
			savePath+="/teacher/"+name;
			portraitURL+="teacher/"+name;
		}else{
			if(!StringUtil.isEmpty(request.getParameter("stuName"))){
			name=request.getParameter("stuName");
			user=userBiz.findByname(name);
			savePath+="/student/"+name;
			portraitURL+="student/"+name;
			}
		}

		File portraitDir=new File(savePath);
		if(!portraitDir.exists()){
			portraitDir.mkdirs();
		}
	
		if(this.uploadFiles!=null){
			for(int i=0;i<uploadFiles.size();i++){
				String fileExt=uploadFilesFileName.get(i).substring(uploadFilesFileName.get(i).lastIndexOf(".")+1).trim().toLowerCase();
				List<String>arr=Arrays.asList(extMap.split(","));
				if(!arr.contains(fileExt)){
					json.put("result",this.uploadFilesFileName.get(i)+"文件类型错误！只允许"+extMap+"格式。");

					continue;
				}
				SimpleDateFormat sdfForFileName=new SimpleDateFormat("yyyyMMddHHmmss");
				String newName=sdfForFileName.format(new Date())+"_"+new Random().nextInt(1000)+"."+fileExt;
				File destFile=new File(portraitDir,newName);
				portraitURL+="/"+newName;
				try {
					
					FileUtils.copyFile(uploadFiles.get(i), destFile);
					
				} catch (Exception e) {
					json.put("result", this.uploadFilesFileName.get(i)+"上传失败！"+e.getMessage());
					
					continue;
				}
				json.put("result",this.uploadFilesFileName.get(i)+"上传成功！");
				if(user!=null){
					user.setPortrait(portraitURL);
					userBiz.update(user);
				}
				if(teacher!=null){
					System.out.println("开始设置");
					teacher.setPortrait(portraitURL);
					teacherBiz.update(teacher);
				}
			
				System.out.println("头像上传地址:"+savePath+"\t头像url："+portraitURL);
			}
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JsonUtil.outJson(response, json);
	}
}
