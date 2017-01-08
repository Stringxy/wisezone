 package com.web.controller.studentManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
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
import org.apache.struts2.components.ActionComponent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.web.biz.ClassTestBiz;
import com.web.biz.ClassesBiz;
import com.web.biz.UserBiz;
import com.web.controller.CommonPageController;
import com.web.entity.ClassTest;
import com.web.entity.Classes;
import com.web.entity.User;
import com.web.util.ExcelUtil;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;
@Controller
@Scope(value="prototype")
public class ClassTestController extends CommonPageController<ClassTest> implements
ModelDriven<ClassTest>{

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
	
	private List<User> user=null;
	private List<Classes> classes=null;
	@Resource(name="userBizImpl")
	private UserBiz userBiz;
	@Resource(name="classesBizImpl")
	private ClassesBiz classesBiz;
	private ClassTest classTest=null;
	private ClassTestBiz classTestBiz;
	@Resource(name="classTestBizImpl")
	public void setClassTestBiz(ClassTestBiz classTestBiz) {
		this.classTestBiz = classTestBiz;
		super.setCommonBizPage(classTestBiz);
	}
	
	
	
	@Override
	public ClassTest getModel() {
		if(classTest==null){
			classTest=new ClassTest();
		}
		return classTest;
	}
	
	public String addClassTest(){
		this.user=userBiz.findAll();
		this.classes=classesBiz.findAll();
		ActionContext.getContext().getSession().put("user", user);
		ActionContext.getContext().getSession().put("classes", classes);
		
		return "addClassTest";
		
		
	}
	
	public String addSave(){
		HttpServletRequest req= ServletActionContext.getRequest();
		System.out.println("进入search");
		System.out.println(req.getParameter("createDate1"));
		this.classTest.setUser(userBiz.findById(Integer.parseInt(req.getParameter("stuId"))));
		this.classTest.setClasses(classesBiz.findById(Integer.parseInt(req.getParameter("classesId"))));
		this.classTest.setCreateDate(StringUtil.StringToDate(req.getParameter("createDate1")));
		this.classTest.setScore(Double.parseDouble(req.getParameter("score")));
		this.classTest.setMissExam(Integer.parseInt(req.getParameter("missExam")));
		this.classTest.setcDesc(req.getParameter("cDesc"));
		
		this.classTestBiz.insert(classTest);
		
		return "addSave";
	}
	
	public String editClassTest(){
		System.out.println(this.classTest.getId());
		
			this.classTest=this.classTestBiz.findById(classTest.getId());
			ActionContext.getContext().getSession().put("classtest", classTest);
			
			return "editClassTest";
		
	}
	
	public String editSave(){
		HttpServletRequest req= ServletActionContext.getRequest();
		
		this.classTest=(ClassTest)ActionContext.getContext().getSession().get("classtest");
		this.classTest.setCreateDate(StringUtil.StringToDate(req.getParameter("createDate1")));
		this.classTest.setScore(Double.parseDouble(req.getParameter("score")));
		this.classTest.setMissExam(Integer.parseInt(req.getParameter("missExam")));
		this.classTest.setcDesc(req.getParameter("cDesc"));
		
		this.classTestBiz.update(classTest);
		
		return "editSave";
	}
	
	public String downloadTemp(){
		String path=ServletActionContext.getServletContext().getRealPath("/static/excelTemp/classTestTemp.xlsx");
		File excel=new File(path);
		fileName="classTestTemp.xlsx";
		try {
			this.inputStream = new BufferedInputStream(
					new FileInputStream(path));
		} catch (FileNotFoundException e) {
			System.out.println("文件没有找到");
		
		}
		return SUCCESS;
	}
	
	public void upload(){
		JSONObject json=new JSONObject();
		String savePath=ServletActionContext.getServletContext().getRealPath("/upload/");
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
						List<List<String>>classTests=ExcelUtil.readXlsx(savePath+newName);
						for(List<String>row:classTests){
							ClassTest ct=new ClassTest();
							
							ct.setUser(userBiz.findById(Integer.parseInt(row.get(0))));
							ct.setScore(Integer.parseInt(row.get(5)));
							ct.setClasses(classesBiz.findById(Integer.parseInt(row.get(2))));
							ct.setCreateDate(sdfForFileName.parse(row.get(4)));
							ct.setcDesc(row.get(7));
							ct.setMissExam(Integer.parseInt(row.get(6)));
							classTestBiz.insert(ct);
						}
					
					} catch (Exception e) {
						System.out.println("上传报错信息："+e.getMessage());
						e.printStackTrace();
						json.put("result", this.uploadFilesFileName.get(i)+"上传失败！"+e.getMessage());
						continue;
					} 
			}
			json.put("result", "上传成功！");
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			JsonUtil.outJson(response, json);
		}
	}
}
