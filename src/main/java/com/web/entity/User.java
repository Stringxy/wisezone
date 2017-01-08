package com.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Entity
@Table(name = "userinfo")
@DynamicInsert(true)
@DynamicUpdate(true)
public class User implements Serializable {

	private Integer stuId;
	private String stuNo;//学号
	private String stuName; //登录名
	private String password;
	private Major majors;//所属专业
	private Classes classes;
	private Date joinDate;
	private String email;
	private Integer sex;// 0:男 1:女
	private Date birthday;
	private Integer educationId; // 学历(1、大专以下 2、大专 3、本科、4、研究生)
	private String graduate;
	private String major;
	private String identity;// 身份证号
	private String mobile;
	private String qq;
	private City city;
	private String address;// 家庭住址
	private String liveAddress;// 现住地址
	private String portrait;// 头像url
	private Detail detail;// 关卡
	private Integer state; // 用户状态：1、正常，2、休学 3、流失 4、转班5、待就业中 6、就业 7、二次就业

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	@Column
	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	@Column
	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	@Column
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToOne
	@JoinColumn(name = "majorId")
	public Major getMajors() {
		return majors;
	}

	public void setMajors(Major majors) {
		this.majors = majors;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "classesId")
	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	@Column
	@Temporal(TemporalType.DATE)
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	@Column
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column
	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Column
	public Integer getEducationId() {
		return educationId;
	}

	public void setEducationId(Integer educationId) {
		this.educationId = educationId;
	}
	@Column
	public String getGraduate() {
		return graduate;
	}

	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}
	@Column
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	@Column
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	@Column
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "cityId")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	@Column
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column
	public String getLiveAddress() {
		return liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
	@Column(name="portrait")
	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="detailId")
	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}
	@Column
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
