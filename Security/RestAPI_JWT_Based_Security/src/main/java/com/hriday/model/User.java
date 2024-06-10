package com.hriday.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "Name")
	private String userName;
	
	@Column(name = "Mail")
	private String userMail;
	
	@Column(name = "PWD")
	private String userPwd;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "Role", joinColumns = @JoinColumn(name="Id", referencedColumnName = "Id"))
	@Column(name = "role")
	private List<String> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
