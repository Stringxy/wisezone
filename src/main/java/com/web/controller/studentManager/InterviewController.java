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
import com.web.biz.InterviewBiz;
import com.web.biz.TeacherBiz;
import com.web.biz.UserBiz;
import com.web.controller.CommonPageController;
import com.web.entity.ClassTest;
import com.web.entity.Classes;
import com.web.entity.Interview;
import com.web.entity.Teacher;
import com.web.entity.User;
import com.web.util.ExcelUtil;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;
@Controller
@Scope(value="prototype")
public class InterviewController extends CommonPageController<Interview> implements ModelDriven<Interview>{
  
	private Interview interview;
	private InterviewBiz interviewBiz;
	@Resource(name="interviewBizImpl")
	public void setInterviewBiz(InterviewBiz interviewBiz) {
		this.interviewBiz = interviewBiz;
		super.setCommonBizPage(interviewBiz);
	}
	private List<User> us;
	private List<Classes> cls;
	private List<Teacher>  te;
	@Resource(name="userBizImpl")
	private UserBiz userbiz;
	@Resource(name="classesBizImpl")
	private ClassesBiz classbiz;
	@Resource(name="teacherBizImpl")
	private TeacherBiz teacherbiz;
	@Override
	public Interview getModel() {
		if(interview==null){
			interview= new Interview();
		}
		return interview;
	}
	
	
	public Interview getInterview() {
		return interview;
	}
	public void setInterview(Interview interview) {
		this.interview = interview;
	}
	
	

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
	public String  AddInterview(){
		
		us=userbiz.findAll();
		ActionContext.getContext().getSession().put("us", us);
		cls=classbiz.findAll();
		ActionContext.getContext().getSession().put("cls", cls);
		te= teacherbiz.findAll();
		ActionContext.getContext().getSession().put("te", te);
		return "AddInterview";
	}
	
	public String  saveIv(){
		HttpServletRequest req= ServletActionContext.getRequest();
		this.interview.setUser(userbiz.findById(Integer.parseInt( req.getParameter("stuId"))));
		this.interview.setClasses(classbiz.findById(Integer.parseInt(req.getParameter("classesId"))));
		this.interview.setTeacher(teacherbiz.findById(Integer.parseInt(req.getParameter("teacherId"))));
		this.interview.setCreateDate(StringUtil.StringToDate(req.getParameter("createDate1")));
		this.interview.setInformation(req.getParameter("information"));
		this.interview.setFollowUp(Integer.parseInt(req.getParameter("followUp")));
		this.interview.setiDesc(req.getParameter("iDesc"));
		this.interviewBiz.insert(interview);
		return "saveIv";
		
		
	} 
		public String editIv(){
			
		Interview editIv=this.interviewBiz.findById(this.interview.getId());
		ActionContext.getContext().getSession().put("iv", editIv);
		
		return  "editIv";
	}
		
		public String editSaveIv(){
			HttpServletRequest req= ServletActionContext.getRequest();
			this.interview=(Interview)ActionContext.getContext().getSession().get("iv");
			this.interview.setCreateDate(StringUtil.StringToDate(req.getParameter("createDate1")));
			this.interview.setInformation(req.getParameter("information"));
			this.interview.setFollowUp(Integer.parseInt(req.getParameter("followUp")));
			this.interview.setiDesc(req.getParameter("iDesc"));
			this.interviewBiz.update(interview);
			
			
			return "editSaveIv";
		}
		
		public String downloadTemp(){
			String path=ServletActionContext.getServletContext().getRealPath("/static/excelTemp/interviewTemp.xlsx");
			File excel=new File(path);
			fileName="interviewTemp.xlsx";
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
							List<List<String>>interviews=ExcelUtil.readXlsx(savePath+newName);
							for(List<String>row:interviews){
								Interview inter=new Interview();
								inter.setUser(userbiz.findById(Integer.parseInt(row.get(0))));
								inter.setClasses(classbiz.findById(Integer.parseInt(row.get(2))));
								inter.setTeacher(teacherbiz.findById(Integer.parseInt(row.get(4))));
								inter.setCreateDate(sdfForFileName.parse(row.get(6)));
								inter.setInformation(row.get(7));
								inter.setFollowUp(Integer.parseInt(row.get(8)));
								inter.setiDesc(row.get(9));
								interviewBiz.insert(inter);
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
