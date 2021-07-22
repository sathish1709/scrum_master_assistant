package com.citibank.demo.business;

public class UserAuth {
	private String soeid;
	private String password;
	
	
	public UserAuth(String soeid, String password) {
		super();
		this.soeid = soeid;
		this.password = password;
	}
	
	public String getSoeid() {
		return soeid;
	}
	public void setSoeid(String soeid) {
		this.soeid = soeid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
