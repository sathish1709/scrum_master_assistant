package com.citibank.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citibank.demo.business.UserAuth;
import com.citibank.demo.business.UserSetupRegistration;

public class UserAuthRowMapper implements RowMapper<UserAuth>  {

	@Override
	public UserAuth mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new UserAuth(rs.getString("soeid"),
				rs.getString("password_info")
				);
	}

}
