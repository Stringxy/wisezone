package com.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 角色实体类
 * @author Xy
 * @version 创建时间：2016年12月10日  
 */

@Entity
@Table(name="role")
public class Role implements Serializable {

	private Integer roleId;
	private String roleName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	@Column
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
