package com.citibank.demo.business;

import java.util.List;

public class EmailInfo {

	private String manager_soeid;
	private String email_id;
	public EmailInfo(String manager_soeid, String email_id) {
		super();
		this.manager_soeid = manager_soeid;
		this.email_id = email_id;
	}
	public String getManager_soeid() {
		return manager_soeid;
	}
	public void setManager_soeid(String manager_soeid) {
		this.manager_soeid = manager_soeid;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	
	
		
}
