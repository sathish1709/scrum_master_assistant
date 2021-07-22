package com.citibank.demo.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.citibank.demo.business.EmailInfo;
import com.citibank.demo.business.MailStructure;
import com.citibank.demo.business.ProjectInfo;

import com.citibank.demo.business.TaskInfo;
import com.citibank.demo.business.TeamMemberInfo;
import com.citibank.demo.business.UserAuth;
import com.citibank.demo.business.UserSetupRegistration;

@Repository
public class SpringDataRepository implements UserSetupRegistrationInterface,UserAuthInterface, TeamMemberInfoInterface, ProjectInfoInterface, TaskInforInterface,MailStructureInterface, EmailInfoInterface{

	final static Logger logger = Logger.getLogger(SpringDataRepository.class);

	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<UserSetupRegistration> allUsers() {
		
		String sql = "SELECT soeid, emp_first_name, emp_last_name, email_id, emp_designation, scrum_master FROM employee";
		logger.info("SQL Statement : " + sql);
		return template.query(sql, new UserSetupRegistrationRowMapper());
	}

	@Override
	public UserSetupRegistration getUsers(String soeid) {
		// TODO Auto-generated method stub
				String sql = "SELECT soeid, emp_first_name, emp_last_name, email_id, emp_designation, scrum_master FROM employee WHERE soeid=?";
				logger.info("SQL Statement : " + sql);
				return template.queryForObject(sql, new UserSetupRegistrationRowMapper()
		        ,soeid);
	}

	@Override
	public UserSetupRegistration editUser(UserSetupRegistration u) {
		// TODO Auto-generated method stub
		
				String sql = "UPDATE employee SET emp_first_name = ? , emp_last_name= ?, email_id = ?, emp_designation= ?, scrum_master = ?" +
						"WHERE soeid = ?";
				logger.info("SQL Statement : " + sql);
				template.update(sql,u.getFirst_name(),u.getLast_name(),u.getEmail_id(), u.getDesignation(),u.getScrum_master(),u.getSoeid());
				return u;
	}

	@Override
	public String deleteUser(String soeid) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM employee WHERE soeid = ?";
		logger.info("SQL Statement : " + sql);
		template.update(sql,soeid);
		return soeid;
	}

	@Override
	public UserSetupRegistration addUser(UserSetupRegistration u) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO employee(soeid, emp_first_name, emp_last_name, email_id, emp_designation, scrum_master) " +
				"VALUES(?,?,?,?,?,?)";
		logger.info("SQL Statement : " + sql);
		template.update(sql,u.getSoeid(),u.getFirst_name(),u.getLast_name(),u.getEmail_id(), u.getDesignation(),u.getScrum_master());
		
		return u;
	}

	@Override
	public void addDefaultPassword(UserAuth ua){
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO employee_auth (soeid, password_info) " + "VALUES(?,?)";
		logger.info("SQL Statement : " + sql);
		template.update(sql,ua.getSoeid(),ua.getPassword());
		
	}
	
	@Override
	public void deleteDefaultPassword(String soeid) {

		String sql = "DELETE FROM employee_auth WHERE soeid = ?";
		logger.info("SQL Statement : " + sql);
		template.update(sql,soeid);
		
	}

	@Override
	public void updatePassword(UserAuth ua) {

		String sql = "UPDATE employee_auth SET password_info = ? WHERE soeid = ?";
		logger.info("SQL Statement : " + sql);
		template.update(sql,ua.getPassword(),ua.getSoeid());
		
	}

	@Override
	public void insertUserlogin(String soeid) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sql = "INSERT INTO user_login_audit (soeid, login_time) " + "VALUES(?,?)";
		logger.info("SQL Statement : " + sql);
		template.update(sql,soeid, timestamp);
	}
	
	@Override
	public boolean checkUser(String soeid) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM employee WHERE soeid=?";
		 boolean result = false;
		    int count = template.queryForObject(sql, new Object[] {soeid},Integer.class);
		    if (count > 0) {
		      result = true;
		    }
		    return result;
	}
	
	

	@Override
	public String getUserCredential(String soeid) {
		// TODO Auto-generated method stub
		String sql = "SELECT password_info FROM employee_auth WHERE soeid=?";
		try {
			String password = (String) template.queryForObject(
		            sql, new Object[] { soeid }, String.class);
			return password;
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
		
	}

	

	@Override
	public String deleteTeamMember(String soeid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql = "DELETE FROM team_info WHERE soeid = ?";
		template.update(sql,soeid);
		logger.info("SQL Statement : " + sql);
		return soeid;
	}

	@Override
	public TeamMemberInfo addTeamMember(TeamMemberInfo t) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO team_info(soeid, manager_soeid, secondary_scrum_master) " +
				"VALUES(?,?,?)";
		template.update(sql,t.getSoeid(),t.getManager_soeid(),t.getSecondary_scrum_master());
		String sql1 = "UPDATE employee SET scrum_master = ? " + "WHERE soeid = ?";
		template.update(sql1,t.getSecondary_scrum_master(),t.getSoeid());
		logger.info("SQL Statement : " + sql);
		logger.info("SQL Statement : " + sql);
		return t;
	}

	@Override
	public boolean checkUserInUserLog(String soeid) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM user_login_audit WHERE soeid=?";
		 boolean result = false;
		    int count = template.queryForObject(sql, new Object[] {soeid},Integer.class);
		    if (count > 0) {
		      result = true;
		    }
		    logger.info("SQL Statement : " + sql);
		    return result;
	}

	@Override
	public List<ProjectInfo> allProjects(String soeid) {
		// TODO Auto-generated method stub
		String sql = "SELECT p.p_name,p.p_id, p.dept_name, p.p_owner_soeid, pr.release_no, pr.release_status FROM project p INNER JOIN project_release_info pr "
				+ "ON p.p_id = pr.p_id"
				+ " where p_owner_soeid = ?";
		logger.info("SQL Statement : " + sql);
		return template.query(sql, new ProjectInfoRowMapper(),"sm51850");
	}

	@Override
	public ProjectInfo getProjects(int project_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT p_name,p_id, dept_name, p_owner_soeid FROM project where p_id=?";
		logger.info("SQL Statement : " + sql);
        return template.queryForObject(sql, new ProjectInfoRowMapper()
        ,project_id);
        
	}

	@Override
	public ProjectInfo editProject(ProjectInfo p) {
		// TODO Auto-generated method stub
		String sql = "UPDATE project SET p_name = ? , dept_name = ?, p_owner_soeid= ?" +
				"WHERE p_id = ?";
		template.update(sql,p.getProject_name(),p.getDept_name(), p.getProject_owner(),p.getProject_id());
		logger.info("SQL Statement : " + sql);
		return p;
	}

	@Override
	public int deleteProject(int project_id) {
		// TODO Auto-generated method stub
		String sql1 = "DELETE FROM jira_ticket_info WHERE p_id = ?";
		String sql2 = "DELETE FROM project_release_info WHERE p_id = ?";
		String sql = "DELETE FROM project WHERE p_id = ?";
		template.update(sql1,project_id);
		template.update(sql2,project_id);
		template.update(sql,project_id);
		logger.info("SQL Statement : " + sql);
		logger.info("SQL Statement : " + sql1);
		logger.info("SQL Statement : " + sql2);
		return project_id;
	}

	@Override
	public ProjectInfo addProject(ProjectInfo p) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO project(p_name,p_id, dept_name, p_owner_soeid) " +
				"VALUES(?,?,?,?)";
		String sql1 = "INSERT INTO project_release_info(p_name,p_id, release_no, release_status) " +
				"VALUES(?,?,?,?)";
		template.update(sql,p.getProject_name(),p.getProject_id(),p.getDept_name(),p.getProject_owner());
		template.update(sql1,p.getProject_name(),p.getProject_id(),p.getRelease_no(),p.getRelease_status());
		logger.info("SQL Statement : " + sql);
		logger.info("SQL Statement : " + sql1);
		return p;
	}

	@Override
	public TaskInfo addTask(TaskInfo t) {
		// TODO Auto-generated method stub
		System.out.println("******* " + t.getTask_owner());
		String sql = "INSERT INTO jira_ticket_info( p_id, task_name, task_owner, task_status, uat_date,update_space) " +
				"VALUES(?,?,?,?,?,?)";
	
		template.update(sql,t.getP_id(),t.getTask_name(),t.getTask_owner(),t.getTask_status(),t.getUat_date(),t.getTask_comments());
		logger.info("SQL Statement : " + sql);
		return t;
	}

	@Override
	public int deleteTask(int jira_no) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM jira_ticket_info WHERE jira_no = ?";
		template.update(sql,jira_no);
		logger.info("SQL Statement : " + sql);
		return jira_no;
	}

	@Override
	public TaskInfo editTask(TaskInfo t) {
		// TODO Auto-generated method stub
		String sql = "UPDATE jira_ticket_info SET task_status = ? , uat_date = ?, update_space= ?" +
				"WHERE jira_no = ?";
		logger.info("SQL Statement : " + sql);
		template.update(sql,t.getTask_status(),t.getUat_date(),t.getTask_comments(),t.getJira_no());
		return t;
	}

	
	@Override
	public TaskInfo getTask(int jira_no) {
		// TODO Auto-generated method stub
		System.out.println("Hi...."+jira_no);
		String sql = "SELECT jira_no,p_id, task_name, task_owner,task_status, uat_date,update_space FROM jira_ticket_info WHERE jira_no = ?";
		logger.info("SQL Statement : " + sql);
		return template.queryForObject(sql, new TaskInfoRowMapper(),jira_no);
	}

	@Override
	public List<TaskInfo> allTask(int p_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM jira_ticket_info where p_id = ?";
		logger.info("SQL Statement : " + sql);
		return template.query(sql,new TaskInfoRowMapper(),p_id);
	}


	@Override
	public List<EmailInfo> sendEmail(String release_no, String soeid) {
		System.out.println("Hi ..");
		
		// TODO Auto-generated method stub
		String sql = "SELECT  pr.release_no, p.p_owner_soeid, e.soeid, e.email_id,  j.jira_no, j.task_name,\r\n" + 
				"j.task_owner, j.update_space FROM jira_ticket_info j INNER JOIN project_release_info pr \r\n" + 
				"ON j.p_id = pr.p_id \r\n" + 
				"INNER JOIN project p on p.p_id = pr.p_id\r\n" + 
				"INNER JOIN team_info t on t.manager_soeid = p.p_owner_soeid\r\n" + 
				"INNER JOIN employee e on e.soeid = t.soeid"
				+ " where pr.release_no = ?"
				+ " and p.p_owner_soeid = ?";
		logger.info("SQL Statement : " + sql);
		return template.query(sql, new EmailInfoRowMapper(),release_no,soeid);
		
	}

	@Override
	public List<MailStructure> getTasksByRelease(String release_no, String soeid) {
		// TODO Auto-generated method stub
		String sql = "SELECT  pr.release_no, p.p_owner_soeid,  j.jira_no, j.task_name,\r\n" + 
				"j.task_owner, j.update_space FROM jira_ticket_info j INNER JOIN project_release_info pr \r\n" + 
				"ON j.p_id = pr.p_id \r\n" + 
				"INNER JOIN project p on p.p_id = pr.p_id\r\n" + 
				"INNER JOIN team_info t on t.manager_soeid = p.p_owner_soeid\r\n" + 
				 " where pr.release_no = ?"
				+ " and p.p_owner_soeid = ?";
		logger.info("SQL Statement : " + sql);
		return template.query(sql, new MailStructureRowMapper(),release_no,soeid);
	}

	@Override
	public List<EmailInfo> getEmailId(String manager_soeid) {
		// TODO Auto-generated method stub
		
		 
		String sql = "select  e.email_id ,t.manager_soeid from Employee e Inner Join team_info t on e.soeid = t.soeid "
				+ "where t.manager_soeid = ? ";
		logger.info("SQL Statement : " + sql);
		return template.query(sql, new EmailInfoRowMapper(),manager_soeid);
	}

	@Override
	public TeamMemberInfo getTeamFromEmployee(String soeid) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
