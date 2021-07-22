package com.citibank.demo.business;

public class TaskInfo {

	private int jira_no;
	private int p_id;
	private String task_name;
	private String task_owner;
	private String task_status;
	private String uat_date;
	private String task_comments;
	
	public TaskInfo(int jira_no, int p_id, String task_name, String task_owner, String task_status, String uat_date,
			String task_comments) {
		super();
		this.jira_no = jira_no;
		this.p_id = p_id;
		this.task_name = task_name;
		this.task_owner = task_owner;
		this.task_status = task_status;
		this.uat_date = uat_date;
		this.task_comments = task_comments;
	}
	public int getJira_no() {
		return jira_no;
	}
	public void setJira_no(int jira_no) {
		this.jira_no = jira_no;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
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
	public String getTask_status() {
		return task_status;
	}
	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}
	public String getUat_date() {
		return uat_date;
	}
	public void setUat_date(String uat_date) {
		this.uat_date = uat_date;
	}
	public String getTask_comments() {
		return task_comments;
	}
	public void setTask_comments(String task_comments) {
		this.task_comments = task_comments;
	}
	
	
	
}
