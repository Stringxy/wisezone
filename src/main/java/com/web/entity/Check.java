package com.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 练习题关卡表
 * @author Administrator
 *
 */


@Entity
@Table(name="checkpoint")
public class Check implements Serializable {

	private Integer checkId;
	private String checkName;  //关卡名称
	private Integer checkCount; //关卡总数量
	private Major major; //所属专业

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getCheckId() {
		return checkId;
	}
	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}
	
	@Column(nullable=false)
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	
	@Column(nullable=false)
	public Integer getCheckCount() {
		return checkCount;
	}
	public void setCheckCount(Integer checkCount) {
		this.checkCount = checkCount;
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
