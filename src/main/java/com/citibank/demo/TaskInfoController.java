package com.citibank.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citibank.demo.business.ProjectInfo;
import com.citibank.demo.business.ProjectInfoOperation;
import com.citibank.demo.business.TaskInfo;
import com.citibank.demo.business.TaskInfoOperation;
import com.citibank.demo.business.TeamMemberInfo;
import com.citibank.demo.business.UserSetupRegistration;
import com.citibank.demo.business.UserSetupRegistrationOperations;

@Controller
public class TaskInfoController {
	
	final static Logger logger = Logger.getLogger(TaskInfoController.class);

	private static String UPLOADED_FOLDER = "C:\\";
	
	@Autowired
	TaskInfoOperation taskOps;
	
	@Autowired
	ProjectInfoOperation projectOps;
	
	@Autowired
	UserSetupRegistrationOperations operations;
	
	@RequestMapping("/createrelease")
	String taskinsertnewhandler(@RequestParam("file") MultipartFile textfile,
            RedirectAttributes redirectAttributes,HttpServletRequest request, Model model) {
		if(request.getSession().getAttribute("username")!=null) {
		
	
		 if(textfile.isEmpty()){
		        redirectAttributes.addFlashAttribute("message", "File is Empty. Please select a file to upload");
		        }

		        try{
		           // Get the file and save it somewhere
		           byte[] bytes = textfile.getBytes();
		       
				Path path = Paths.get(UPLOADED_FOLDER + textfile.getOriginalFilename());
		           Files.write(path, bytes);
		         

		           BufferedReader br = new BufferedReader(new FileReader("C:\\release.txt"));
		           String line;
				/*
				 * int jira_no =1;
				 *  int project_id = 1000;
				 */
		           ProjectInfo project = new ProjectInfo(request.getParameter("project_name"),
		        		   Integer.parseInt(request.getParameter("project_id")), request.getParameter("dept_name"),
	        			   request.getParameter("soeid"), request.getParameter("release_no"),request.getParameter("release_status")
	        			   );
	        	   projectOps.newProject(project);
	        	   
		           while ((line  = br.readLine()) != null) {
		        	   String[] data = line.split(",");
		        	   System.out.println(line);


		        	   // Task task = new Task(data[0],data[1],data[2],data[3]);
		        	   
		        	   String task_name = data[0];
		        	   String task_owner = data[1];
		        	   String uat_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		        	   String task_status = data[2];
		        	   String task_comments = null;
		        	  
		        	   logger.info("Task Name from Flat file : " + task_name);
		        	   logger.info("Task Owner from Flat file : " + task_owner);
		        	   logger.info("Task Status from Flat file : " + task_status);
		        		
		        	 
		        	   TaskInfo task = new TaskInfo(1,
		        			   Integer.parseInt(request.getParameter("project_id")), task_name,task_owner, task_status, uat_date, task_comments);
		        	   
		        	   
		        	   taskOps.newInfo(task);
		        	   System.out.println("1: "+ data[0] + " 2: " + data[1]+ " 3: "+ data[2] );

		           }
		           br.close();

		           redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + textfile.getOriginalFilename() + "'");

		        }
		        catch (IOException e) {
		        	e.printStackTrace();
		        }
				
				model.addAttribute("my_details", operations.getUsers(request.getParameter("soeid")));
		        return "my_details";
	}
		else {
			return "login";
		}
	}

	@RequestMapping("/pview/{project_id}")
	String taskViewHandler(@PathVariable("project_id") String project_id,HttpServletRequest request, Model model) {
		if(request.getSession().getAttribute("username")!=null) {
		System.out.println("String"+project_id);
		int p_id =Integer.parseInt(project_id);
		logger.info("Project Id to be viewed is : " + p_id);
		model.addAttribute("viewTasks", taskOps.allTasks(p_id));
		String soeid=(String)request.getSession().getAttribute("username");
		String scrumMaster = operations.getUsers(soeid).getScrum_master();
		if(scrumMaster.equals("Yes")) {
		return "viewTasks";
		}
		else {
			return "viewTasksForNoneScrum";
		}
	
	}
	
	  else { 
		  return "login"; 
		  } 
		}
	 
	
	@RequestMapping("/tedit/{jira_no}")
	String taskupdatehandler(@PathVariable("jira_no") String jira_no, Model model,HttpServletRequest request) {
		if(request.getSession().getAttribute("username")!=null) {
			int jira_num =Integer.parseInt(jira_no);
			logger.info("Jira Id to be edited is : " + jira_num);
			model.addAttribute("viewTaskInfo", taskOps.getTask(jira_num));
			return "viewTaskInfo";
		}
		else {
			return "login";
		}
	}

	@RequestMapping("/tsave")
	String tasksavehandler(@ModelAttribute TaskInfo newTask, Model model,HttpServletRequest request) {
		if(request.getSession().getAttribute("username")!=null) {
			taskOps.editTask(newTask);
			int p_id = Integer.parseInt(request.getParameter("p_id"));
		model.addAttribute("viewTasks", taskOps.allTasks(p_id));
		String soeid=(String)request.getSession().getAttribute("username");
		String scrumMaster = operations.getUsers(soeid).getScrum_master();
		if(scrumMaster.equals("Yes")) {
		return "viewTasks";
		}
		else {
			return "viewTasksForNoneScrum";
		}
	
	}
	else {
		return "login";
	}
	}
}
