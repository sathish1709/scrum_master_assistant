package com.citibank.demo.business;

public class ReleaseInfo {
	
	private int release_no;
	private String release_date;
	private String release_status;
	private int project_id;
	private String project_name;
	
	public ReleaseInfo(int release_no, String release_date, String release_status, int project_id,
			String project_name) {
		super();
		this.release_no = release_no;
		this.release_date = release_date;
		this.release_status = release_status;
		this.project_id = project_id;
		this.project_name = project_name;
	}
	
	public int getRelease_no() {
		return release_no;
	}
	public void setRelease_no(int release_no) {
		this.release_no = release_no;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public String getRelease_status() {
		return release_status;
	}
	public void setRelease_status(String release_status) {
		this.release_status = release_status;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
}
