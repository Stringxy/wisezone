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
import com.web.biz.IntegratedProjectBiz;
import com.web.biz.UserBiz;
import com.web.controller.CommonPageController;
import com.web.entity.ClassTest;
import com.web.entity.Classes;
import com.web.entity.IntegratedProject;
import com.web.entity.User;
import com.web.util.ExcelUtil;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;
@Controller
@Scope(value="prototype")
public class IntegratedProjectController extends CommonPageController<IntegratedProject> implements ModelDriven<IntegratedProject>{

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
	private IntegratedProjectBiz ipBiz;
	@Resource(name="integratedProjectBizImpl")
	public void setIpBiz(IntegratedProjectBiz ipBiz) {
		this.ipBiz = ipBiz;
		super.setCommonBizPage(ipBiz);
	}
	private IntegratedProject integratedProject=null;
	
	private List<User> u=null;
	private List<Classes> c= null;
	@Resource(name="userBizImpl")
	private UserBiz userBiz;
	@Resource(name="classesBizImpl")
	private ClassesBiz classesBiz;
	@Override
	public IntegratedProject getModel() {
		if(integratedProject==null){
			integratedProject=new IntegratedProject();
		}
		return integratedProject;
	}
	public String addIp(){
		u=userBiz.findAll();
		c=classesBiz.findAll();
		ActionContext.getContext().getSession().put("u", u);
		ActionContext.getContext().getSession().put("c", c);
		return "addIp";
	}
	
	public String ipSave(){
//		System.out.println(integratedProject.getUser());
//		System.out.println(integratedProject.getCreateDate());
//		System.out.println(integratedProject.getClasses());
		HttpServletRequest req= ServletActionContext.getRequest();
		this.integratedProject.setUser(userBiz.findById(Integer.parseInt(req.getParameter("stuId"))));
		this.integratedProject.setClasses(classesBiz.findById(Integer.parseInt(req.getParameter("classesId"))));
		this.integratedProject.setCreateDate(StringUtil.StringToDate(req.getParameter("createDate1")));
		this.integratedProject.setTeamName(req.getParameter("teamName"));
		this.integratedProject.setWorkName(req.getParameter("workName"));
		this.integratedProject.setEvaluatingScore(Double.parseDouble(req.getParameter("evaluatingScore")));
		this.integratedProject.setAnswerScore(Double.parseDouble(req.getParameter("answerScore")));
		this.integratedProject.setiDESC(req.getParameter("iDESC"));
		
		this.ipBiz.insert(integratedProject);
		return "ipSave";
	}
	
	public String editIp(){
		HttpServletRequest req= ServletActionContext.getRequest();
		Integer id=Integer.parseInt(req.getParameter("id"));
		IntegratedProject ip=ipBiz.findById(id);
		ActionContext.getContext().getSession().put("ip", ip);
		
		return  "editIp";
	}
	
	public String ipEditSave(){
		HttpServletRequest req= ServletActionContext.getRequest();
		this.integratedProject=(IntegratedProject)ActionContext.getContext().getSession().get("ip");
		this.integratedProject.setCreateDate(StringUtil.StringToDate(req.getParameter("createDate1")));
		this.integratedProject.setTeamName(req.getParameter("teamName"));
		this.integratedProject.setWorkName(req.getParameter("workName"));
		this.integratedProject.setEvaluatingScore(Double.parseDouble(req.getParameter("evaluatingScore")));
		this.integratedProject.setAnswerScore(Double.parseDouble(req.getParameter("answerScore")));
		this.integratedProject.setiDESC(req.getParameter("iDESC"));
		this.ipBiz.update(integratedProject);
		return "ipEditSave";
	}
	
	public String downloadTemp(){
		String path=ServletActionContext.getServletContext().getRealPath("/static/excelTemp/integratedProjectTemp.xlsx");
		File excel=new File(path);
		fileName="integratedProjectTemp.xlsx";
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
						List<List<String>>projects=ExcelUtil.readXlsx(savePath+newName);
						for(List<String>row:projects){
							IntegratedProject ip=new IntegratedProject();
							ip.setUser(userBiz.findById(Integer.parseInt(row.get(0))));
							ip.setClasses(classesBiz.findById(Integer.parseInt(row.get(2))));
							ip.setCreateDate(sdfForFileName.parse(row.get(4)));
							ip.setTeamName(row.get(5));
							ip.setWorkName(row.get(6));
							ip.setEvaluatingScore(Integer.parseInt(row.get(7)));
							ip.setAnswerScore(Integer.parseInt(row.get(8)));
							ip.setiDESC(row.get(9));
							ipBiz.insert(ip);
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
