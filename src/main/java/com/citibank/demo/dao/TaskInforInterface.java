package com.citibank.demo.dao;

import java.util.List;

import com.citibank.demo.business.ReleaseInfo;
import com.citibank.demo.business.TaskInfo;
import com.citibank.demo.business.UserSetupRegistration;

public interface TaskInforInterface {

	public TaskInfo addTask(TaskInfo t);
	public int deleteTask(int jira_no);
	public TaskInfo editTask(TaskInfo t);
	public TaskInfo getTask(int jira_no);
	public List<TaskInfo> allTask(int p_id);
}
