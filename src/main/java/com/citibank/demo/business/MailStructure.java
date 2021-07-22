package com.citibank.demo.business;

import java.util.List;

public class MailStructure {

	
	private String release_no;
	private String p_owner_soeid;
	private int jira_no;
	private String task_name;
	private String task_owner;
	private String update_space;
	public MailStructure(String release_no, String p_owner_soeid, int jira_no, String task_name, String task_owner,
			String update_space) {
		super();
		this.release_no = release_no;
		this.p_owner_soeid = p_owner_soeid;
		this.jira_no = jira_no;
		this.task_name = task_name;
		this.task_owner = task_owner;
		this.update_space = update_space;
	}
	public String getRelease_no() {
		return release_no;
	}
	public void setRelease_no(String release_no) {
		this.release_no = release_no;
	}
	public String getP_owner_soeid() {
		return p_owner_soeid;
	}
	public void setP_owner_soeid(String p_owner_soeid) {
		this.p_owner_soeid = p_owner_soeid;
	}
	public int getJira_no() {
		return jira_no;
	}
	public void setJira_no(int jira_no) {
		this.jira_no = jira_no;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_owner() {
		return task_owner;
	}
	public void setTask_owner(String task_owner) {
		this.task_owner = task_owner;
	}
	public String getUpdate_space() {
		return update_space;
	}
	public void setUpdate_space(String update_space) {
		this.update_space = update_space;
	}
	
	
	
	
}
