package com.citibank.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citibank.demo.business.TeamMemberInfo;


public class TeamInfoRowMapper implements RowMapper<TeamMemberInfo> {

	@Override
	public TeamMemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new TeamMemberInfo(rs.getString("soeid"),
				rs.getString("manager_soeid"),
				rs.getString("secondary_scrum_master")
				);
	}

	

}
