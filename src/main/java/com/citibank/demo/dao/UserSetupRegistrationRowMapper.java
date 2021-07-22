package com.citibank.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citibank.demo.business.UserSetupRegistration;;

public class UserSetupRegistrationRowMapper implements RowMapper<UserSetupRegistration> {

	@Override
	public UserSetupRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub soeid, first_name, last_name, email_id, designation, scrum_master
		return new UserSetupRegistration(rs.getString("soeid"),
				rs.getString("emp_first_name"),
				rs.getString("emp_last_name"),
				rs.getString("email_id"),
				rs.getString("emp_designation"),
				rs.getString("scrum_master")
				);
	}

		
}