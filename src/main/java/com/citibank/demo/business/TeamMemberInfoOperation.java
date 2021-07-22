package com.citibank.demo.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.citibank.demo.dao.TeamMemberInfoInterface;
import com.citibank.demo.dao.UserSetupRegistrationInterface;

@Component
public class TeamMemberInfoOperation {
	
	private TeamMemberInfoInterface teamMemberRepository;
	
	
	@Autowired
	public TeamMemberInfoOperation(TeamMemberInfoInterface teamMemberRepository) {
		super();
		this.teamMemberRepository = teamMemberRepository;
	}
	
	public TeamMemberInfo newMember(TeamMemberInfo t) {
	
		return teamMemberRepository.addTeamMember(t);
	}
	

	public String deleteTeamMember(String soeid) {
		
		return teamMemberRepository.deleteTeamMember(soeid);
	}
	
}
