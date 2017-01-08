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

@Entity
@Table(name="checkpointdetailquestion")
public class DetailQuestion implements Serializable {

	private Integer detailQuestionId;
	private Detail detail;
	private Question question;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getDetailQuestionId() {
		return detailQuestionId;
	}
	public void setDetailQuestionId(Integer detailQuestionId) {
		this.detailQuestionId = detailQuestionId;
	}
	
	@ManyToOne
	@JoinColumn(name="detailId")
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}
	

	@ManyToOne
	@JoinColumn(name="questionId")
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
}
