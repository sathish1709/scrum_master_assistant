package com.citibank.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citibank.demo.business.ProjectInfo;

public class ProjectInfoRowMapper implements RowMapper<ProjectInfo>{

	@Override
	public ProjectInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new ProjectInfo(rs.getString("p_name"),rs.getInt("p_id"),
				rs.getString("dept_name"), rs.getString("p_owner_soeid"), rs.getString("release_no"), rs.getString("release_status"));
	}

}
