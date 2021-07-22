package com.citibank.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citibank.demo.business.TeamMemberInfo;
import com.citibank.demo.business.TeamMemberInfoOperation;
import com.citibank.demo.business.UserAuth;
import com.citibank.demo.business.UserSetupRegistration;
import com.citibank.demo.business.UserSetupRegistrationOperations;

@Controller
public class TeamMemberSpringController {
	@Autowired
	TeamMemberInfoOperation teamOps;
	
	@Autowired
	UserSetupRegistrationOperations operations;
	
	private static String UPLOADED_FOLDER = "C:\\";
	
	@RequestMapping("/newmember")
	String teaminsertnewhandler(HttpServletRequest request, Model model) {
		//soeid, first_name, last_name, email_id, designation, scrum_master
		if(request.getSession().getAttribute("username")!=null) {
			String soeid = request.getParameter("soeid");
			String msoeid = request.getParameter("manager_soeid");
			String sm = request.getParameter("secondary_scrum_master");
			TeamMemberInfo team = new TeamMemberInfo(request.getParameter("soeid"),
					request.getParameter("manager_soeid"),
					request.getParameter("secondary_scrum_master")
					);
		
			teamOps.newMember(team);
			model.addAttribute("my_details",(String)request.getSession().getAttribute("username"));
			return "my_details";
			
		}
	else {
		return "login";
	}
	}
	
	@RequestMapping("/deletemember")
	String teamdeletehandler(HttpServletRequest request, Model model) {
		//soeid, first_name, last_name, email_id, designation, scrum_master
		if(request.getSession().getAttribute("username")!=null) {
			String msoeid = request.getParameter("soeid");
			String soeid = request.getParameter("dsoeid");
			System.out.println(msoeid);
			System.out.println(soeid);
			teamOps.deleteTeamMember(soeid);
			model.addAttribute("my_details", (String)request.getSession().getAttribute("username"));
			return "my_details";
			
		}
		else {
			return "login";
		}
	
	}
	}

