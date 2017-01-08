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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 班级类
 * @author cheng
 *
 */
@Entity
@Table(name="classes")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Classes implements Serializable{

	private Integer classesId;//班级编号
	private String classesName;//班级名称
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private Major major;//外键专业
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getClassesId() {
		return classesId;
	}
	public void setClassesId(Integer classesId) {
		this.classesId = classesId;
	}
	public String getClassesName() {
		return classesName;
	}
	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}
	
	@Column
	@Temporal(TemporalType.DATE)
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Column
	@Temporal(TemporalType.DATE)
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@ManyToOne
	@JoinColumn(name="majorId")
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	
	
}
