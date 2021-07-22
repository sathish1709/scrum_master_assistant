package com.citibank.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citibank.demo.business.ReleaseInfo;


public class ReleaseInfoRowMapper  implements RowMapper<ReleaseInfo> {

	@Override
	public ReleaseInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new ReleaseInfo(rs.getInt("release_no"),rs.getString("release_date"),rs.getString("release_status"),
				rs.getInt("project_id"),rs.getString("project_name"));
	}

}
