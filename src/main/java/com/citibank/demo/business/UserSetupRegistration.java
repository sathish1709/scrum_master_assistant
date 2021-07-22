package com.citibank.demo.business;

public class UserSetupRegistration {

	private String soeid;
	private String first_name;
	private String last_name;
	private String email_id;
	private String designation;
	private String scrum_master;
	
	public UserSetupRegistration(String soeid, String first_name, String last_name, String email_id, String designation,
			String scrum_master) {
		super();
		this.soeid = soeid;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email_id = email_id;
		this.designation = designation;
		this.scrum_master = scrum_master;
	}

	public String getSoeid() {
		return soeid;
	}

	public void setSoeid(String soeid) {
		this.soeid = soeid;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getScrum_master() {
		return scrum_master;
	}

	public void setScrum_master(String scrum_master) {
		this.scrum_master = scrum_master;
	}
	
}
