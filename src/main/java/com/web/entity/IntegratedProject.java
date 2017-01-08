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
@Entity
@Table(name="integratedproject")
public class IntegratedProject implements Serializable{

	
	private Integer id;
	private User user;
	private Classes classes;
	private Date createDate;
	private String teamName;
	private String workName;
	private double evaluatingScore;
	private  double answerScore;
	private String iDESC;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="stuId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name="classesId")
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public double getEvaluatingScore() {
		return evaluatingScore;
	}
	public void setEvaluatingScore(double evaluatingScore) {
		this.evaluatingScore = evaluatingScore;
	}
	public double getAnswerScore() {
		return answerScore;
	}
	public void setAnswerScore(double answerScore) {
		this.answerScore = answerScore;
	}
	@Column(name="idesc")
	public String getiDESC() {
		return iDESC;
	}
	public void setiDESC(String iDESC) {
		this.iDESC = iDESC;
	}
	
	
	
}
