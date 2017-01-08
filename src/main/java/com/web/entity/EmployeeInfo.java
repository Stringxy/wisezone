package com.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */

@Entity
@Table(name="employeeinfo")
public class EmployeeInfo implements Serializable{

	private Integer id;
	
	private User user;
	private Classes classes;
	private Integer employeeWay;
	private Date employeeDate;
	private String employeeUnit;
	private String employeePost;
	private double employeeSalary;
	private String employeeAddress;
	private String employeeContact;
	private String employeeTel;
	private Integer twoEmployee;
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
	public Integer getEmployeeWay() {
		return employeeWay;
	}
	public void setEmployeeWay(Integer employeeWay) {
		this.employeeWay = employeeWay;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEmployeeDate() {
		return employeeDate;
	}
	public void setEmployeeDate(Date employeeDate) {
		this.employeeDate = employeeDate;
	}
	public String getEmployeeUnit() {
		return employeeUnit;
	}
	public void setEmployeeUnit(String employeeUnit) {
		this.employeeUnit = employeeUnit;
	}
	public String getEmployeePost() {
		return employeePost;
	}
	public void setEmployeePost(String employeePost) {
		this.employeePost = employeePost;
	}
	public double getEmployeeSalary() {
		return employeeSalary;
	}
	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	public String getEmployeeContact() {
		return employeeContact;
	}
	public void setEmployeeContact(String employeeContact) {
		this.employeeContact = employeeContact;
	}
	public String getEmployeeTel() {
		return employeeTel;
	}
	public void setEmployeeTel(String employeeTel) {
		this.employeeTel = employeeTel;
	}
	public Integer getTwoEmployee() {
		return twoEmployee;
	}
	public void setTwoEmployee(Integer twoEmployee) {
		this.twoEmployee = twoEmployee;
	}
	
}
