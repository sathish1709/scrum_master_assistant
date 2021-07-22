package com.citibank.demo.dao;

import java.util.List;

import com.citibank.demo.business.TeamMemberInfo;
public interface TeamMemberInfoInterface {
	
	public TeamMemberInfo getTeamFromEmployee(String soeid);
	public String deleteTeamMember(String soeid);
	public TeamMemberInfo addTeamMember(TeamMemberInfo t);

}
