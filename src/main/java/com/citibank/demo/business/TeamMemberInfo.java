package com.citibank.demo.business;

public class TeamMemberInfo {
	
	private String soeid;
	private String secondary_scrum_master;
	private String manager_soeid;
	
	public TeamMemberInfo(String soeid, String manager_soeid,String secondary_scrum_master) {
		super();
		this.soeid = soeid;
		this.secondary_scrum_master = secondary_scrum_master;
		this.manager_soeid = manager_soeid;
	}
	
	public String getSoeid() {
		return soeid;
	}
	public void setSoeid(String soeid) {
		this.soeid = soeid;
	}
	public String getSecondary_scrum_master() {
		return secondary_scrum_master;
	}
	public void setSecondary_scrum_master(String secondary_scrum_master) {
		this.secondary_scrum_master = secondary_scrum_master;
	}
	public String getManager_soeid() {
		return manager_soeid;
	}
	public void setManager_soeid(String manager_soeid) {
		this.manager_soeid = manager_soeid;
	}
	
	
}