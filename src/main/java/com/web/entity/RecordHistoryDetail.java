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
/**
 * 学员闯关记录明细表
 * @author Administrator
 *
 */
@Entity
@Table(name="recordhistorydetail")
public class RecordHistoryDetail implements Serializable {

	private Integer id;
	private RecordHistory record;//闯关记录表(外键)
	private Integer questionId;//试题编号(不要设定外键，只是为了记录试题编号)
	private String stuAnswer;
	private Date createDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="recordId")
	public RecordHistory getRecord() {
		return record;
	}
	public void setRecord(RecordHistory record) {
		this.record = record;
	}
	
	@Column(nullable=false)
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
	@Column(nullable=false)
	public String getStuAnswer() {
		return stuAnswer;
	}
	public void setStuAnswer(String stuAnswer) {
		this.stuAnswer = stuAnswer;
	}
	
	@Column(nullable=false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
