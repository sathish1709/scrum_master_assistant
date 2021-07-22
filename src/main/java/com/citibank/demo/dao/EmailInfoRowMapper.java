package com.citibank.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citibank.demo.business.EmailInfo;
import com.citibank.demo.business.ProjectInfo;

public class EmailInfoRowMapper implements RowMapper<EmailInfo> {
	

	
	@Override
	public EmailInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return new EmailInfo(rs.getString("manager_soeid"),rs.getString("email_id"));
	
	}

}
