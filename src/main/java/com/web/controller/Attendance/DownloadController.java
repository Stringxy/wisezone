package com.web.controller.Attendance;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.biz.AttendanceBiz;
import com.web.biz.ClassesBiz;
import com.web.entity.Attendance;
import com.web.entity.Classes;
@Controller
//控制层的注解
@Scope(value = "prototype")
//每次请求（每次使用）创建新的实例
public class DownloadController extends ActionSupport {
	private String path=ServletActionContext.getServletContext().getRealPath("/static/report/report.xls");
	@Resource(name="attendanceBizImpl")
	private AttendanceBiz attendanceBiz;
	@Resource(name="classesBizImpl")
	private ClassesBiz classesBiz;
	private InputStream inputStream; // 读取文件的的流
	private String fileName; // 发给浏览器的给够看到的名称
	private String newfilepath;
	
	
	public InputStream getInputStream() {
		return this.inputStream;
	}



	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String downloadMonth(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer classesId=Integer.parseInt(request.getParameter("classesIdForMonth"));
		String yearmonth=request.getParameter("month");
		Integer Year=Integer.parseInt(yearmonth.substring(0, 4));
		
		Integer Month=Integer.parseInt(yearmonth.substring(5, 7));
		Classes classes=classesBiz.findById(classesId);
		List<Attendance> data=attendanceBiz.findByParams(Year, Month, classes);

		String tempPath=ServletActionContext.getServletContext().getRealPath("/static/report/monthTemple.xlsx");
		File temp=new File(tempPath);
		String name=yearmonth+"monthreport";
		if(!temp.exists()){
			System.out.println("模板不存在！");
		}else{
			System.out.println("找到模板！");
			try {
				this.getDataExcel(data, tempPath,name);
			} catch (Exception e) {
				System.out.println("报错信息："+e.getMessage());
				e.printStackTrace();
			}
		}
		try {
			this.inputStream=new BufferedInputStream(new FileInputStream(this.newfilepath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String downloadWeek(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer classesId=Integer.parseInt(request.getParameter("classesIdForWeek"));
		String monday=request.getParameter("monday");
		Integer Year=Integer.parseInt(monday.substring(0, 4));
		
		Integer Month=Integer.parseInt(monday.substring(5, 7));
		Integer day=Integer.parseInt(monday.substring(8));
		Classes classes=classesBiz.findById(classesId);
		List<Attendance> data=attendanceBiz.findByParams(Year, Month, classes);

		String tempPath=ServletActionContext.getServletContext().getRealPath("/static/report/weekTemple.xlsx");
		File temp=new File(tempPath);
		String name=monday+"weekreport";
		if(!temp.exists()){
			System.out.println("模板不存在！");
		}else{
			System.out.println("找到模板！");
			try {
				this.getWeekExcel(data, tempPath,name,day);
			} catch (Exception e) {
				System.out.println("报错信息："+e.getMessage());
				e.printStackTrace();
			}
		}
		try {
			this.inputStream=new BufferedInputStream(new FileInputStream(this.newfilepath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	@SuppressWarnings("resource")
	public void getDataExcel(List<Attendance>data,String tempPath,String name) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String path=ServletActionContext.getServletContext().getRealPath("/static/report/");
		Workbook workbook;
		File file=new File(path);
		if(!file.exists()){
			file.mkdirs();
		}

		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		File tempFile=new File(tempPath);
		int dot=tempPath.lastIndexOf(".")+1;
		String ext=tempPath.substring(dot).trim().toLowerCase();
		fileName=name+sdf.format(new Date())+"."+ext;
		File destFile=new File(file,fileName);
		FileUtils.copyFile(tempFile,destFile);
		this.newfilepath=path+"\\"+fileName;
		System.out.println(newfilepath);
		if(ext.equals("xls")){
			workbook=new HSSFWorkbook(new FileInputStream(this.newfilepath));
		}else{
			workbook=new XSSFWorkbook(new FileInputStream(this.newfilepath));
		}
		Sheet sheet=workbook.getSheetAt(0);
		Row row;
		Cell cell;
		for(int i=0;i<data.size();i++){
			Attendance a=data.get(i);
			row=sheet.createRow(i+1);
			for(int k=0;k<65;k++){
				row.createCell(k);
			}
			row=sheet.getRow(i+1);
			cell=row.getCell(0);
			cell.setCellValue(a.getUser().getStuName());
			cell=row.getCell(1);
			cell.setCellValue(a.getClasses().getClassesName());
			int temp=2;
			for(int j=0;j<31;j++){
				cell=row.getCell(temp);
				Method get=a.getClass().getMethod("getDayMorning"+(j+1));
				
				Integer state=(Integer) get.invoke(a);
				if(state==1){
					cell.setCellValue("正常");
				}
				if(state==2){
					cell.setCellValue("迟到");
				}
				if(state==3){
					cell.setCellValue("早退");
				}
				if(state==4){
					cell.setCellValue("请假");
				}
				if(state==5){
					cell.setCellValue("旷课");
				}
				if(state==6){
					cell.setCellValue("放假");
				}
				cell=row.getCell(temp+1);
				Method getAfternoon=a.getClass().getMethod("getDayAfternoon"+(j+1));
				
				Integer state1=(Integer) getAfternoon.invoke(a);
				if(state1==1){
					cell.setCellValue("正常");
				}
				if(state1==2){
					cell.setCellValue("迟到");
				}
				if(state1==3){
					cell.setCellValue("早退");
				}
				if(state1==4){
					cell.setCellValue("请假");
				}
				if(state1==5){
					cell.setCellValue("旷课");
				}
				if(state1==6){
					cell.setCellValue("放假");
				}
				temp=temp+2;
			}
		}

		FileOutputStream out=new FileOutputStream(newfilepath);
		workbook.write(out);
		out.close();
	}
	
	public void getWeekExcel(List<Attendance>data,String tempPath,String name,Integer monday) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String path=ServletActionContext.getServletContext().getRealPath("/static/report/");
		Workbook workbook;
		File file=new File(path);
		if(!file.exists()){
			file.mkdirs();
		}

		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		File tempFile=new File(tempPath);
		int dot=tempPath.lastIndexOf(".")+1;
		String ext=tempPath.substring(dot).trim().toLowerCase();
		fileName=name+sdf.format(new Date())+"."+ext;
		File destFile=new File(file,fileName);
		FileUtils.copyFile(tempFile,destFile);
		this.newfilepath=path+"\\"+fileName;
		System.out.println(newfilepath);
		if(ext.equals("xls")){
			workbook=new HSSFWorkbook(new FileInputStream(this.newfilepath));
		}else{
			workbook=new XSSFWorkbook(new FileInputStream(this.newfilepath));
		}
		Sheet sheet=workbook.getSheetAt(0);
		Row row;
		Cell cell;
		for(int i=0;i<data.size();i++){
			Attendance a=data.get(i);
			row=sheet.createRow(i+1);
			for(int k=0;k<16;k++){
				row.createCell(k);
			}
			row=sheet.getRow(i+1);
			cell=row.getCell(0);
			cell.setCellValue(a.getUser().getStuName());
			cell=row.getCell(1);
			cell.setCellValue(a.getClasses().getClassesName());
			int temp=2;
			for(int j=0;j<7;j++){
				cell=row.getCell(temp);
				Method get=a.getClass().getMethod("getDayMorning"+(monday+j));
				
				Integer state=(Integer) get.invoke(a);
			
				if(state==1){
					cell.setCellValue("正常");
				}
				if(state==2){
					cell.setCellValue("迟到");
				}
				if(state==3){
					cell.setCellValue("早退");
				}
				if(state==4){
					cell.setCellValue("请假");
				}
				if(state==5){
					cell.setCellValue("旷课");
				}
				if(state==6){
					cell.setCellValue("放假");
				}
				cell=row.getCell(temp+1);
				Method getAfternoon=a.getClass().getMethod("getDayAfternoon"+(monday+j));
				
				Integer state1=(Integer) getAfternoon.invoke(a);
				
				if(state1==1){
					cell.setCellValue("正常");
				}
				if(state1==2){
					cell.setCellValue("迟到");
				}
				if(state1==3){
					cell.setCellValue("早退");
				}
				if(state1==4){
					cell.setCellValue("请假");
				}
				if(state1==5){
					cell.setCellValue("旷课");
				}
				if(state1==6){
					cell.setCellValue("放假");
				}
				temp=temp+2;
			}
		}

		FileOutputStream out=new FileOutputStream(newfilepath);
		workbook.write(out);
		out.close();
	}
}
