package com.citibank.demo.business;

public class ProjectInfo {

	private String project_name;
	private int project_id;
	private String dept_name;
	private String project_owner;
	private String release_no;
	private String release_status;
	
	public ProjectInfo(String project_name, int project_id, String dept_name, String project_owner, String release_no,
			String release_status) {
		super();
		this.project_name = project_name;
		this.project_id = project_id;
		this.dept_name = dept_name;
		this.project_owner = project_owner;
		this.release_no = release_no;
		this.release_status = release_status;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getProject_owner() {
		return project_owner;
	}

	public void setProject_owner(String project_owner) {
		this.project_owner = project_owner;
	}

	public String getRelease_no() {
		return release_no;
	}

	public void setRelease_no(String release_no) {
		this.release_no = release_no;
	}

	public String getRelease_status() {
		return release_status;
	}

	public void setRelease_status(String release_status) {
		this.release_status = release_status;
	}
	
	
	
}
