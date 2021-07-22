package com.citibank.demo.dao;

import java.util.List;

import com.citibank.demo.business.ProjectInfo;

public interface ProjectInfoInterface {
	public List<ProjectInfo> allProjects(String soeid);
	public ProjectInfo getProjects(int p_id);
	public ProjectInfo editProject(ProjectInfo p);
	public int deleteProject(int project_id);
	public ProjectInfo addProject(ProjectInfo p);
}
