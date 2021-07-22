package com.citibank.demo.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.citibank.demo.dao.ProjectInfoInterface;
@Component
public class ProjectInfoOperation {

	private ProjectInfoInterface projectInfoRepository;
	
	@Autowired
	public ProjectInfoOperation(ProjectInfoInterface projectInfoRepository) {
		super();
		this.projectInfoRepository = projectInfoRepository;
	}
	
	public List<ProjectInfo> allProjects(String soeid) {
		
		return projectInfoRepository.allProjects(soeid);
	}

	public ProjectInfo getProject(int project_id) {
		
		return projectInfoRepository.getProjects(project_id);
	}

	public ProjectInfo saveProject(ProjectInfo p) {
		
		return projectInfoRepository.editProject(p);
	}

	public int deleteProject(int project_id) {
	
		return projectInfoRepository.deleteProject(project_id);
	}

	public ProjectInfo newProject(ProjectInfo p) {
		
		return projectInfoRepository.addProject(p);
	}

}
