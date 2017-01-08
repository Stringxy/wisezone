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
@Table(name="stagetest")
public class StageTest implements Serializable{

	private Integer id;
	private User user;
	private Classes classes;
	private Date createDate;
	private String workName;
	private double score;
	private Integer makeUp;
	private double makeUpScore;
	private Integer missExam;
	private String sDesc;
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
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public Integer getMakeUp() {
		return makeUp;
	}
	@Column(name="makeup")
	public void setMakeUp(Integer makeUp) {
		this.makeUp = makeUp;
	}
	public double getMakeUpScore() {
		return makeUpScore;
	}
	@Column(name="makeupScore")
	public void setMakeUpScore(double makeUpScore) {
		this.makeUpScore = makeUpScore;
	}
	public Integer getMissExam() {
		return missExam;
	}
	public void setMissExam(Integer missExam) {
		this.missExam = missExam;
	}
	@Column(name="sdesc")
	public String getsDesc() {
		return sDesc;
	}
	public void setsDesc(String sDesc) {
		this.sDesc = sDesc;
	}
	
}
