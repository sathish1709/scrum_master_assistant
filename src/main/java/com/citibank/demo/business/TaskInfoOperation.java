package com.citibank.demo.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citibank.demo.dao.TaskInforInterface;
import com.citibank.demo.dao.UserSetupRegistrationInterface;
@Component
public class TaskInfoOperation {
	
	private TaskInforInterface taskInfoRepository;
	
	@Autowired
	public TaskInfoOperation(TaskInforInterface taskInfoRepository) {
		super();
		this.taskInfoRepository = taskInfoRepository;
	}
	
	
	public List<TaskInfo> allTasks(int p_id) {
		
		return taskInfoRepository.allTask(p_id);
	}

	public TaskInfo getTask(int jira_no) {
		
		return taskInfoRepository.getTask(jira_no);
	}

	public TaskInfo saveTask(TaskInfo t) {
	
		return taskInfoRepository.addTask(t);
	}

	public TaskInfo editTask(TaskInfo t) {
		
		return taskInfoRepository.editTask(t);
	} 
	
	public int deleteTask(int jira_no) {
	
		return taskInfoRepository.deleteTask(jira_no);
	}

	public TaskInfo newInfo(TaskInfo t) {
		
		return taskInfoRepository.addTask(t);
	}

}
