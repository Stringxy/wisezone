package com.web.controller.studentManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.web.biz.ClassesBiz;
import com.web.biz.EmployeeInfoBiz;
import com.web.biz.UserBiz;
import com.web.controller.CommonPageController;
import com.web.entity.ClassTest;
import com.web.entity.Classes;
import com.web.entity.EmployeeInfo;
import com.web.entity.User;
import com.web.util.ExcelUtil;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;
@Controller
@Scope(value="prototype")
public class EmployeeInfoController extends CommonPageController<EmployeeInfo> implements ModelDriven<EmployeeInfo>{

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
	
	private EmployeeInfoBiz  empBiz;
	@Resource(name="employeeInfoBizImpl")
	public void setEmpBiz(EmployeeInfoBiz empBiz) {
		this.empBiz = empBiz;
		super.setCommonBizPage(empBiz);
	}
	public EmployeeInfo emp=null;
	
	private List<User> uu=null;
	private List<Classes> cc=null;
	@Resource(name="userBizImpl")
	private UserBiz  userBiz;
	@Resource(name="classesBizImpl")
	private ClassesBiz classesBiz;
	@Override
	public EmployeeInfo getModel() {
		if(emp==null){
			emp= new EmployeeInfo();
		}
		return emp;
	}

	

	public String addemp(){
		this.uu=userBiz.findAll();
		this.cc=classesBiz.findAll();
		ActionContext.getContext().getSession().put("uu", uu);
		ActionContext.getContext().getSession().put("cc", cc);
		
		return "addemp";
	}
	
	public String empSave(){
		
//		System.out.println(emp.getUser());
//		System.out.println(emp.getEmployeeAddress());
//		System.out.println(emp.getClasses());
	HttpServletRequest req= ServletActionContext.getRequest();
		this.emp.setUser(userBiz.findById(Integer.parseInt(req.getParameter("stuId"))));
		this.emp.setClasses(classesBiz.findById(Integer.parseInt(req.getParameter("classesId"))));
		this.emp.setEmployeeWay(Integer.parseInt(req.getParameter("employeeWay")));	
		this.emp.setEmployeeDate(StringUtil.StringToDate(req.getParameter("employeeDate1")));
		this.emp.setEmployeeUnit(req.getParameter("employeeUnit"));
		this.emp.setEmployeePost(req.getParameter("employeePost"));
		if(!StringUtil.isEmpty(req.getParameter("employeeSalary"))){
			this.emp.setEmployeeSalary(Double.parseDouble(req.getParameter("employeeSalary")));
		}
	
		this.emp.setEmployeeAddress(req.getParameter("employeeAddress"));
		this.emp.setEmployeeContact(req.getParameter("employeeContact"));
		this.emp.setEmployeeTel(req.getParameter("employeeTel"));
		this.emp.setTwoEmployee(Integer.parseInt(req.getParameter("twoEmployee")));
		
		
		empBiz.insert(emp);
		
		return "empSave";
	}
	
	public String editEmp(){
		HttpServletRequest req= ServletActionContext.getRequest();
		Integer id=Integer.parseInt(req.getParameter("id"));
		EmployeeInfo ei=empBiz.findById(id);
		
		ActionContext.getContext().getSession().put("ei", ei);
		return "editEmp";
	}
	
	public String empEditSave(){
		HttpServletRequest req= ServletActionContext.getRequest();
		
		this.emp=(EmployeeInfo)ActionContext.getContext().getSession().get("emp");
		this.emp.setEmployeeWay(Integer.parseInt(req.getParameter("employeeWay")));	
		this.emp.setEmployeeDate(StringUtil.StringToDate(req.getParameter("employeeDate1")));
		this.emp.setEmployeeUnit(req.getParameter("employeeUnit"));
		this.emp.setEmployeePost(req.getParameter("employeePost"));
		if(!StringUtil.isEmpty(req.getParameter("employeeSalary"))){
			this.emp.setEmployeeSalary(Double.parseDouble(req.getParameter("employeeSalary")));
		}
	
		this.emp.setEmployeeAddress(req.getParameter("employeeAddress"));
		this.emp.setEmployeeContact(req.getParameter("employeeContact"));
		this.emp.setEmployeeTel(req.getParameter("employeeTel"));
		this.emp.setTwoEmployee(Integer.parseInt(req.getParameter("twoEmployee")));
		
		
		empBiz.update(emp);
		return "empEditSave";
	}
	
	public String downloadTemp(){
		String path=ServletActionContext.getServletContext().getRealPath("/static/excelTemp/employeeinfoTemp.xlsx");
		File excel=new File(path);
		fileName="employeeInfoTemp.xlsx";
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
						List<List<String>>emps=ExcelUtil.readXlsx(savePath+newName);
						for(List<String>row:emps){
							EmployeeInfo ei=new EmployeeInfo();
							ei.setUser(userBiz.findById(Integer.parseInt(row.get(0))));
							ei.setClasses(classesBiz.findById(Integer.parseInt(row.get(2))));
							ei.setEmployeeWay(Integer.parseInt(row.get(4)));
							ei.setEmployeeDate(sdfForFileName.parse(row.get(5)));
							ei.setEmployeeUnit(row.get(6));
							ei.setEmployeePost(row.get(7));
							ei.setEmployeeSalary(Double.parseDouble(row.get(8)));
							ei.setEmployeeAddress(row.get(9));
							ei.setEmployeeContact(row.get(10));
							ei.setEmployeeTel(row.get(11));
							ei.setTwoEmployee(Integer.parseInt(row.get(12)));
							
							empBiz.insert(ei);
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
