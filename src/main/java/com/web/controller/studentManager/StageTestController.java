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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
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
import com.web.biz.StageTestBiz;
import com.web.biz.UserBiz;
import com.web.controller.CommonPageController;
import com.web.entity.Classes;
import com.web.entity.Interview;
import com.web.entity.StageTest;
import com.web.entity.User;
import com.web.util.ExcelUtil;
import com.web.util.JsonUtil;
import com.web.util.StringUtil;
@Controller
@Scope(value="prototype")
public class StageTestController extends CommonPageController<StageTest> implements ModelDriven<StageTest>{

	
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
	private StageTestBiz stageTestBiz;
	@Resource(name="stageTestBizImpl")
	public void setStageTestBiz(StageTestBiz stageTestBiz) {
		this.stageTestBiz = stageTestBiz;
		super.setCommonBizPage(stageTestBiz);
	}
	private StageTest stageTest=null;
	
	private List<User> urs=null;
	private List<Classes> classes=null;
	@Resource(name="userBizImpl")
	private UserBiz userBiz;
	@Resource(name="classesBizImpl")
	private ClassesBiz classesBiz;
	@Override
	public StageTest getModel() {
		if(stageTest==null){
			stageTest=new StageTest();
		}
		return stageTest;
	}
	
	
	public String addStageTest(){
		this.urs=userBiz.findAll();
		this.classes=classesBiz.findAll();
		ActionContext.getContext().getSession().put("urs", urs);
		ActionContext.getContext().getSession().put("classes", classes);
		
		return "addStageTest";

		}
	public String saveSt(){
		System.out.println("进入！");
		HttpServletRequest req= ServletActionContext.getRequest();
		this.stageTest.setUser(userBiz.findById(Integer.parseInt(req.getParameter("stuId"))));
		this.stageTest.setClasses(classesBiz.findById(Integer.parseInt(req.getParameter("classesId"))));
		this.stageTest.setCreateDate(StringUtil.StringToDate(req.getParameter("createDate1")));
		this.stageTest.setWorkName(req.getParameter("workName"));
		this.stageTest.setScore(Double.parseDouble(req.getParameter("score")));
		this.stageTest.setMakeUp(Integer.parseInt(req.getParameter("makeup")));
		
		if(!StringUtil.isEmpty(req.getParameter("makeupScore"))){
			this.stageTest.setMakeUpScore(Double.parseDouble(req.getParameter("makeupScore")));
		}
		
		this.stageTest.setMissExam(Integer.parseInt(req.getParameter("missExam")));
		this.stageTest.setsDesc(req.getParameter("sDesc"));
		this.stageTestBiz.insert(stageTest);
		return "saveSt";
	}
	
	
	public String editSt(){
		HttpServletRequest req= ServletActionContext.getRequest();
		Integer id=Integer.parseInt(req.getParameter("id"));
		StageTest st=stageTestBiz.findById(id);
		ActionContext.getContext().getSession().put("st", st);
		
		return  "editSt";
	}
	
	public String saveEditSt(){
		HttpServletRequest req= ServletActionContext.getRequest();
		this.stageTest=(StageTest)ActionContext.getContext().getSession().get("st");
		this.stageTest.setCreateDate(StringUtil.StringToDate(req.getParameter("createDate1")));
		this.stageTest.setWorkName(req.getParameter("workName"));
		this.stageTest.setScore(Double.parseDouble(req.getParameter("score")));
		this.stageTest.setMakeUp(Integer.parseInt(req.getParameter("makeup")));
		
		if(!StringUtil.isEmpty(req.getParameter("makeupScore"))){
			this.stageTest.setMakeUpScore(Double.parseDouble(req.getParameter("makeupScore")));
		}
		
		this.stageTest.setMissExam(Integer.parseInt(req.getParameter("missExam")));
		this.stageTest.setsDesc(req.getParameter("sDesc"));
		stageTestBiz.update(stageTest);
		
		return "saveEditSt";
	}
	
	public String downloadTemp(){
		String path=ServletActionContext.getServletContext().getRealPath("/static/excelTemp/stagetestTemp.xlsx");
		File excel=new File(path);
		fileName="stageTestTemp.xlsx";
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
						List<List<String>>stageTests=ExcelUtil.readXlsx(savePath+newName);
						for(List<String>row:stageTests){
							StageTest st=new StageTest();
							st.setUser(userBiz.findById(Integer.parseInt(row.get(0))));
							st.setClasses(classesBiz.findById(Integer.parseInt(row.get(2))));
							st.setCreateDate(sdfForFileName.parse(row.get(4)));
							st.setWorkName(row.get(5));
							st.setScore(Integer.parseInt(row.get(6)));
							st.setMakeUp(Integer.parseInt(row.get(7)));
							String row8=row.get(8);
							if(StringUtil.isNumeric(row8)){
							st.setMakeUpScore(Integer.parseInt(row8));}
							st.setMissExam(Integer.parseInt(row.get(9)));
							st.setsDesc(row.get(10));
							stageTestBiz.insert(st);
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
