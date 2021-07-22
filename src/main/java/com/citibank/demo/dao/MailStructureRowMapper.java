package com.citibank.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citibank.demo.business.EmailInfo;
import com.citibank.demo.business.MailStructure;
import com.citibank.demo.business.ProjectInfo;

public class MailStructureRowMapper implements RowMapper<MailStructure> {
	

	
	@Override
	public MailStructure mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new MailStructure(rs.getString("release_no"),rs.getString("p_owner_soeid"),
				 rs.getInt("jira_no"), rs.getString("task_name"),
				rs.getString("task_owner"),rs.getString("update_space"));
	}

}
