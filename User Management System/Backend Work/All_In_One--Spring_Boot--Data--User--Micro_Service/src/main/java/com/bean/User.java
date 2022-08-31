package com.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="uId")
//	private int uId;
	
	@Id
	@Column(name="uEmail")
	private String uEmail;
	
	@Column(name="uName")
	private String uName;
	
	@Column(name="uPassword")
	private String uPassword;
	
	@Column(name="uRole")
	private String uRole;
	
	@Column(name="url")
	private String url;
	
	
//	public int getuId() {
//		return uId;
//	}
//	public void setuId(int uId) {
//		this.uId = uId;
//	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public String getuPassword() {
		return uPassword;
	}
	public String getuRole() {
		return uRole;
	}
	public void setuRole(String uRole) {
		this.uRole = uRole;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
		

}


