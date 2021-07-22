package com.citibank.demo;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citibank.demo.business.ProjectInfo;
import com.citibank.demo.business.ProjectInfoOperation;
import com.citibank.demo.business.UserSetupRegistrationOperations;

@Controller
public class ProjectSpringController {

	final static Logger logger = Logger.getLogger(ProjectSpringController.class);

	@Autowired
	ProjectInfoOperation projectOps;
	

	@Autowired
	UserSetupRegistrationOperations operations;
	
	@RequestMapping("/allProjects")
	public String projectSetupHandler(Model model, HttpServletRequest request) {
		if(request.getSession().getAttribute("username")!=null) {
		String soeid = request.getParameter("soeid");
		
		String scrumMaster = operations.getUsers((String)request.getSession().getAttribute("username")).getScrum_master();
		logger.info("Is the user Scrum master : " + scrumMaster);
		if(scrumMaster.equals("Yes")) {
			model.addAttribute("allProjects", projectOps.allProjects(soeid));
			return "allProjects";
		}
		else {
			model.addAttribute("allProjects", projectOps.allProjects(soeid));
			return "allProjectsForNonScrum";
		}
		
	}
	
	 else {
		 return "login"; 
		 } 
		}
	 
	
	@RequestMapping("/newProjectSetup")
	String projectnewhandler(HttpServletRequest req) {
		if(req.getSession().getAttribute("username")!=null) {
			return "addProject";
	}
	else {
		return "login";
	}
	}
	
	@RequestMapping("/pedit/{project_id}")
	String projectupdatehandler(@PathVariable("project_id") int project_id, Model model,HttpServletRequest request) {
		if(request.getSession().getAttribute("username")!=null) {
		model.addAttribute("editProjects", projectOps.getProject(project_id));
		return "editProjects";
		
		  } else { 
			  return "login"; 
			  }
		 }

	

	@RequestMapping("/pdelete/{project_id}")
	String projectdeletehandler(@PathVariable("project_id") int project_id, HttpServletRequest request,Model model) {		
		if(request.getSession().getAttribute("username")!=null) {
			String soeid = request.getParameter("soeid");
		projectOps.deleteProject(project_id);
		model.addAttribute("allProjects", projectOps.allProjects(soeid));
		return "allProjects";
	}
	else {
		return "login";
	}

	}

}
