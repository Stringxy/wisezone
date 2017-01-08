package com.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 问卷调查结果记录表
 * @author johen.deng
 *
 */
@Entity
@Table(name="examresult")
public class ExamResult implements Serializable {
	private int examId;
	private String comIp;
	private ExamType examType;
	private Date examDate;
	private Teacher teacher;
	private Classes classes;
	private double examScore ;
	private int allScore;
	private String examPs;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true)
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public String getComIp() {
		return comIp;
	}
	public void setComIp(String comIp) {
		this.comIp = comIp;
	}
	@ManyToOne
	@JoinColumn(name="examTypeId")
	public ExamType getExamType() {
		return examType;
	}
	public void setExamType(ExamType examType) {
		this.examType = examType;
	}
	@Temporal(TemporalType.DATE)
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	@ManyToOne
	@JoinColumn(name="teacherId")
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	@ManyToOne
	@JoinColumn(name="classesId")
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public double getExamScore() {
		return examScore;
	}
	public void setExamScore(double examScore) {
		this.examScore = examScore;
	}
	public int getAllScore() {
		return allScore;
	}
	public void setAllScore(int allScore) {
		this.allScore = allScore;
	}
	@Column(nullable=true)
	public String getExamPs() {
		return examPs;
	}
	public void setExamPs(String examPs) {
		this.examPs = examPs;
	}
}
