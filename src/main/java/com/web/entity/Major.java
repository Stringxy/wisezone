package com.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 专业类
 * @author cheng
 *
 */

@Entity
@Table(name="major")
public class Major implements Serializable{

	private Integer majorId;//专业编号
	private String majorName;//专业名字
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getMajorId() {
		return majorId;
	}
	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}
	@Column(name="majorName")
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	
}
