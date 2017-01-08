package com.web.controller.studentManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.web.biz.CityBiz;
import com.web.biz.ClassesBiz;
import com.web.biz.MajorBiz;
import com.web.biz.UserBiz;
import com.web.controller.CommonPageController;
import com.web.entity.City;
import com.web.entity.Classes;
import com.web.entity.Detail;
import com.web.entity.Major;
import com.web.entity.User;
import com.web.util.ExcelUtil;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;
@Controller //控制层ioc注释
@Scope(value="prototype") //每次请求创建新的实例
public class UserController extends CommonPageController<User> implements
		ModelDriven<User>{
	private List<Major> majors1=new ArrayList<Major>() ;// 前台要用到select list
	private List<City> cities=new ArrayList<City>() ;;
	private List<Classes> allClasses=new ArrayList<Classes>() ;;
	@Resource(name="classesBizImpl")
	private ClassesBiz classesBiz;
	@Resource(name="cityBizImpl")
	private CityBiz cityBiz;
	private InputStream inputStream; // 读取文件的的流
	private String fileName; // 发给浏览器的给够看到的名称
	private List<File> uploadFiles;
	private List<String> uploadFilesFileName;
	private List<String> uploadFilesContentType;
	private String extMap="xls,xlsx";
	
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

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Resource(name = "majorBizImpl")
	private MajorBiz majorBiz;
	private UserBiz userBiz;
	@Resource(name="userBizImpl")
	public void setUserBiz(UserBiz userBiz){
		this.userBiz=userBiz;
		super.setCommonBizPage(userBiz);
	}
	
	private User user;
	@Override
	public User getModel() {
		if(user==null){
			this.user=new User();
		}
		return user;
	}

	
	public String toEditUser(){
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		Integer stuId=Integer.parseInt(request.getParameter("stuId"));
		if(stuId>0){
		this.user=userBiz.findById(stuId);
		ActionContext.getContext().getSession().put("user", user);}else{
			ActionContext.getContext().getSession().put("user", null);
		}
		
		this.majors1 = majorBiz.findAll();
		ActionContext.getContext().getSession().put("majors", majors1);
		
			this.cities=cityBiz.findAll();

			ActionContext.getContext().getSession().put("cities", cities);
		
		this.allClasses=classesBiz.findAll();

		ActionContext.getContext().getSession().put("allClasses", allClasses);
		return "toEditUser";
	}

	// 保存方法
	public void save() {
		System.out.println("进入了save");
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest request = (HttpServletRequest) ServletActionContext
				.getRequest();
		this.user.setJoinDate(StringUtil.StringToDate(request.getParameter("joinTime")));
		this.user.setBirthday(StringUtil.StringToDate(request.getParameter("birthTime")));
		
		this.user.setMajors(majorBiz.findById(Integer.parseInt(request
				.getParameter("majorId"))));
		this.user.setCity(cityBiz.findById(Integer.parseInt(request.getParameter("cityId"))));
		this.user.setClasses(classesBiz.findById(Integer.parseInt(request.getParameter("classesId"))));

		super.outSaveJson(user,user.getStuId(), response);
	}
	
	public void valid(){
		
		JSONObject json = new JSONObject();
		User old=userBiz.findById(this.user.getStuId());
		
		if(old.getStuName().equals(this.user.getStuName())){
			json.put("valid", true);
		}else{
			json.put("valid", userBiz.isVaild(this.user.getStuName()));
		}
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JsonUtil.outJson(response, json);
	}
	
	
	public void upload(){
		String savePath=ServletActionContext.getServletContext().getRealPath("/upload/");
		JSONObject json=new JSONObject();
		File uploadDir=new File(savePath);
		if(!uploadDir.exists()){
			uploadDir.mkdirs();
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String ymd=sdf.format(new Date());
		savePath+="\\"+ymd+"\\";
		File dirFile=new File(savePath);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		if(this.uploadFiles!=null){
			for(int i=0;i<uploadFiles.size();i++){
				String fileExt=uploadFilesFileName.get(i).substring(uploadFilesFileName.get(i).lastIndexOf(".")+1).trim().toLowerCase();
				List<String>arr=Arrays.asList(extMap.split(","));
				if(!arr.contains(fileExt)){
					super.addActionError(this.uploadFilesFileName.get(i)+"文件类型错误！只允许"+extMap+"格式。");
					continue;
				}
				SimpleDateFormat sdfForFileName=new SimpleDateFormat("yyyy-MM-dd");
				String newName=sdfForFileName.format(new Date())+"_"+new Random().nextInt(1000)+"."+fileExt;
				File destFile=new File(dirFile,newName);
				
				try {
					FileUtils.copyFile(uploadFiles.get(i), destFile);
					List<List<String>>users=ExcelUtil.readXlsx(savePath+newName);
					for(List<String>row:users){
						User user=new User();
						try {
							user.setStuNo(row.get(0));
							user.setStuName(row.get(1));
							user.setPassword(row.get(2));
							user.setMajors(majorBiz.findById(Integer.parseInt(row.get(3).substring(0, row.get(3).indexOf(".")))));
							user.setClasses(classesBiz.findById(Integer.parseInt(row.get(3).substring(0, row.get(3).indexOf(".")))));
							user.setJoinDate(sdfForFileName.parse(row.get(5)));
							user.setEmail(row.get(6));
							user.setSex(Integer.parseInt(row.get(7).substring(0, row.get(7).indexOf("."))));
							user.setBirthday(sdfForFileName.parse(row.get(8)));
							String str9=row.get(9);
							user.setEducationId(Integer.parseInt(str9.substring(0, str9.indexOf("."))));
							user.setGraduate(row.get(10));
							user.setMajor(row.get(11));
							user.setIdentity(row.get(12));
							user.setMobile(row.get(13));
							user.setQq(row.get(14));
							String str15=row.get(15);
							user.setCity(cityBiz.findById(Integer.parseInt(str15.substring(0, str15.indexOf(".")))));
							user.setAddress(row.get(16));
							user.setLiveAddress(row.get(17));
							userBiz.insert(user);
						} catch (Exception e) {
							System.out.println("上传报错信息："+e.getMessage());
							e.printStackTrace();
							json.put("result", this.uploadFilesFileName.get(i)+"上传失败！"+e.getMessage());
							continue;
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			json.put("result","上传成功！");
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JsonUtil.outJson(response, json);
	}
	
	public String downloadTemp(){
		String path=ServletActionContext.getServletContext().getRealPath("/static/excelTemp/userinfoTemp.xlsx");
		File excel=new File(path);
		fileName="userinfoTemp.xlsx";
		try {
			this.inputStream = new BufferedInputStream(
					new FileInputStream(path));
		} catch (FileNotFoundException e) {
			System.out.println("文件没有找到");
		
		}
		return SUCCESS;
	}
}
