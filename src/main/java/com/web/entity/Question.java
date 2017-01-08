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
@Table(name="question")
public class Question implements Serializable {

	private Integer questionId;//主键
	private Major major;//所属专业
	private MajorStage majorstage;//所属专业阶段
	private String questionName;//题目
	private Integer questionType;//试题类型(1、单选  2、多选  3、判断  4、简答 5、编程题)
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private String rightAnswer;//正确答案
	private String analysis;//答案解析
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
	@ManyToOne
	@JoinColumn(name="majorId")
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	
	@ManyToOne
	@JoinColumn(name="stageId")
	public MajorStage getMajorstage() {
		return majorstage;
	}
	public void setMajorstage(MajorStage majorstage) {
		this.majorstage = majorstage;
	}
	
	@Column(nullable=false)
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	
	@Column(nullable=false)
	public Integer getQuestionType() {
		return questionType;
	}
	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}
	
	@Column
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	
	@Column
	public String getAnswerB() {
		return answerB;
	}
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	
	@Column
	public String getAnswerC() {
		return answerC;
	}
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	
	@Column
	public String getAnswerD() {
		return answerD;
	}
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	
	@Column
	public String getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	
	@Column
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
}
