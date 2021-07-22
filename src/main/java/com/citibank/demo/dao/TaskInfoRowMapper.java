package com.citibank.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citibank.demo.business.TaskInfo;
import com.citibank.demo.business.TeamMemberInfo;

public class TaskInfoRowMapper  implements RowMapper<TaskInfo> {

	@Override
	public TaskInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new TaskInfo(rs.getInt("jira_no"),rs.getInt("p_id"),rs.getString("task_name"),rs.getString("task_owner"),rs.getString("task_status"),
				rs.getString("uat_date"),rs.getString("update_space"));
	}

}
