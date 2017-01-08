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
 * 关卡明细表
 * @author Administrator
 *
 */
@Entity
@Table(name="checkpointdetail")
public class Detail implements Serializable {

	private Integer detailId;
	private Check check;
	private String checkName;//关卡明细的名称 （detailname）
	private Integer correctNum;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	
	@ManyToOne
	@JoinColumn(name="checkId")
	public Check getCheck() {
		return check;
	}
	public void setCheck(Check check) {
		this.check = check;
	}
	
	@Column(nullable=false)
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	
	@Column(nullable=false)
	public Integer getCorrectNum() {
		return correctNum;
	}
	public void setCorrectNum(Integer correctNum) {
		this.correctNum = correctNum;
	}
	
	
}
