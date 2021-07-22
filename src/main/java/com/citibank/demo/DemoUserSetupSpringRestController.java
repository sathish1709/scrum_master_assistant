package com.citibank.demo;

import java.sql.Connection;
import org.apache.log4j.Logger;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.citibank.demo.business.EmailInfo;
import com.citibank.demo.business.MailStructure;
import com.citibank.demo.business.ProjectInfo;
import com.citibank.demo.business.ProjectInfoOperation;
import com.citibank.demo.business.TeamMemberInfo;
import com.citibank.demo.business.UserAuth;
import com.citibank.demo.business.UserSetupRegistration;
import com.citibank.demo.business.UserSetupRegistrationOperations;
import com.citibank.demo.dao.EmailInfoInterface;
import com.citibank.demo.dao.MailStructureInterface;
import com.citibank.demo.dao.ProjectInfoInterface;
import com.citibank.demo.dao.TeamMemberInfoInterface;
import com.citibank.demo.dao.UserAuthInterface;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Controller
public class DemoUserSetupSpringRestController {

	final static Logger logger = Logger.getLogger(DemoUserSetupSpringRestController.class);

	@Autowired
	UserSetupRegistrationOperations operations;
	
	@Autowired
	UserAuthInterface userauthops;
	
	@Autowired
	ProjectInfoOperation projectOps;
	
	@Autowired
	EmailInfoInterface emaiInfoRepository;
	
	@Autowired
	MailStructureInterface maiInfoRepository;
		
	@RequestMapping("/")
	public String loginHandler(HttpServletRequest request, Model model) {
			return "login";
			
	}
	
	
	@RequestMapping("/login")
	public String loginSiteHandler(HttpServletRequest request, Model model) {
		String user = request.getParameter("username");
		String password = request.getParameter("password");
		if(request.getParameter("username")!=null) {
		boolean getUserfromDb = operations.isUserExist(user);
		String passwordFromDb  = userauthops.getUserCredential(user);

		if(user.equals("superuser") && password.equals("password")) {
			logger.debug("Logged in user : " + user);
			request.getSession().setAttribute("authenticated", true);
			request.getSession().setAttribute("username", user);
			model.addAttribute("user", user);
			return "index";
		}
		else if(getUserfromDb == true && password.equals(passwordFromDb)){
			logger.debug("Logged in user : " + user);
			request.getSession().setAttribute("authenticated", true);
			request.getSession().setAttribute("username", user);
			String soeid = request.getParameter("username");
			
			if(operations.isUserExistInLoginAudit(soeid)) {
				//alert
				model.addAttribute("my_details", operations.getUsers(user));
				
				return "my_details";
			}
			else {
				model.addAttribute("change_password", operations.getUsers(user));
				return "change_password";
			}
			
		}
		else {
			//alert
			return "login";
		}
		}
		else {
			return "login";
		}
		
		
	}
	
	@RequestMapping("/newpassword")
	String insertnewpassword(HttpServletRequest request, Model model) 
	{
		if(request.getSession().getAttribute("username")!=null) {
		String soeid = request.getParameter("soeid");
		UserAuth uauth = new UserAuth(soeid, request.getParameter("npwd"));
		userauthops.updatePassword(uauth);
		userauthops.insertUserlogin(soeid);
		model.addAttribute("my_details", operations.getUsers((String)request.getSession().getAttribute("username")));
		return "my_details";
		}
		else {
			return "login";
		}
		
	}
	
	@RequestMapping("/logout")
	public String logoutSiteHandler(HttpServletRequest request) {
			request.getSession().invalidate();
			return "login";
	}
	
	
	@RequestMapping("/home")
	public String homePageHandler(HttpServletRequest request, Model model) {
	
		if(request.getSession().getAttribute("username")!=null) {
			String user = (String)request.getSession().getAttribute("username");
			model.addAttribute("my_details", operations.getUsers(user));
			return "my_details";
		}
		else {
			return "login";
		}
	
		
	}
	
	
	@RequestMapping("/shome")
	public String superUserhomePageHandler(HttpServletRequest request, Model model) {
	
		if(request.getSession().getAttribute("username")!=null) {
			
			return "index";
		}
		else {
			return "login";
		}
	
		
	}
	@RequestMapping("/allUsers")
	public String userSetupHandler(Model model, HttpServletRequest request) {
		if(request.getSession().getAttribute("authenticated")!= null) {
			model.addAttribute("allUsers", operations.allUsers());
		
			return "allUsers";
		}
		else
		{
			return "login";
		}
		
	}
	
	@RequestMapping("/newUserSetup")
	String usernewhandler(HttpServletRequest req) {
		if(req.getSession().getAttribute("username")!=null) {
			return "addUser";
			
		}
		else {
			return "login";
		}
	}

	@RequestMapping("/new")
	String userinsertnewhandler(HttpServletRequest request, Model model) {
		//soeid, first_name, last_name, email_id, designation, scrum_master
		if(request.getSession().getAttribute("username")!=null) {
		String soeid = request.getParameter("soeid");
		if(operations.isUserExist(soeid)) {
			//alert
			return "addUser";
		}
		else {
			UserSetupRegistration auser = new UserSetupRegistration(request.getParameter("soeid"),
					request.getParameter("first_name"), request.getParameter("last_name"),
					request.getParameter("email_id"),request.getParameter("designation"),
					request.getParameter("scrum_master")
					);
			operations.newUser(auser);
		
			//setting default password
			UserAuth uauth = new UserAuth(request.getParameter("soeid"), "Welcome@123");
			userauthops.addDefaultPassword(uauth);
		
			model.addAttribute("allUsers", operations.allUsers());
			return "allUsers";
			
		}
		}
		else {
			return "login";
		}
	}
	
	@RequestMapping("/edit/{soeid}")
	String userupdatehandler(@PathVariable("soeid") String soeid, Model model,HttpServletRequest request) {
		if(request.getSession().getAttribute("username")!=null) {
		model.addAttribute("editUsers", operations.getUsers(soeid));
		return "editUsers";
		}
		else {
			return "login";
		}
	}

	@RequestMapping("/save")
	String usersavehandler(@ModelAttribute UserSetupRegistration newUser, Model model,HttpServletRequest request) {
		if(request.getSession().getAttribute("username")!=null) {
		operations.saveUser(newUser);
		model.addAttribute("allUsers", operations.allUsers());
		return "allUsers";
	}
	else {
		return "login";
	}
	}

	@RequestMapping("/delete/{soeid}")
	String userdeletehandler(@PathVariable("soeid") String soeid, Model model,HttpServletRequest request) {		
		if(request.getSession().getAttribute("username")!=null) {
		userauthops.deleteDefaultPassword(soeid);
		operations.deleteUser(soeid);
		model.addAttribute("allUsers", operations.allUsers());
		return "allUsers";
	}
	else {
		return "login";
	}
	}
	
	@RequestMapping("/sendmail")
	public String sendEmailHandler( Model model, HttpServletRequest request) throws ParseException {
		if(request.getSession().getAttribute("username")!=null) {
			String soeid = (String) request.getSession().getAttribute("username");
			
			String release_no = request.getParameter("release_no");
			logger.info("Release number selected for sending mail : " + release_no);
			String atteendees = request.getParameter("attendees");
			
			String nonatteendees = request.getParameter("nattendees");
			String time_take = request.getParameter("time_taken");
			
			final String username = "uname0396@gmail.com";
		    final String password = "3xDust!!";
		      
		      Properties prop = new Properties();
				prop.put("mail.smtp.host", "smtp.gmail.com");
		        prop.put("mail.smtp.port", "587");
		        prop.put("mail.smtp.auth", "true");
		        prop.put("mail.smtp.starttls.enable", "true");
		        
		        Session session = Session.getInstance(prop,
		        		new javax.mail.Authenticator() {
		        	protected PasswordAuthentication getPasswordAuthentication() {
		        		return new PasswordAuthentication(username, password);
		        	}
		        });
		        try {
		        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        	Date dateWithoutTime = sdf.parse(sdf.format(new Date()));
		            Message message = new MimeMessage(session);
		            List<EmailInfo> email_info  =emaiInfoRepository.getEmailId(soeid);
			           
		            for(EmailInfo ei : email_info) {
		            message.setFrom(new InternetAddress("uname0396@gmail.com"));
		            System.out.println(ei.getEmail_id());
		           
		            message.setRecipients(
		                    Message.RecipientType.TO,
		                    InternetAddress.parse(ei.getEmail_id())
		            );
		            
		            List<MailStructure> mail_info = maiInfoRepository.getTasksByRelease(release_no, soeid);
		            String email_structure = "";
		            
		            for(MailStructure mi: mail_info) {
		            	
		            	email_structure += "<tr><td>" + mi.getJira_no() + "<td>" +
		            			mi.getTask_name() + "<td>" + mi.getTask_owner() + "<td>" +
		            			mi.getUpdate_space() ;
		            }
		            	
		           
		            message.setSubject("Testing Gmail");
		            message.setContent("<!DOCTYPE html>\n" + 
		            		"<html>\n" + 
		   	            	"  <head>\n" + 
		            		"  </head>\n" + 
		            		"<body style=\"background-color: beige; color:brown;\">\n" + 
		            		
		            		"  <table>\n" + 
		            		"    <tr>\n" + 
		            		"    <td>\n" + 
		            		"      Attendees:\n" + 
		            		"    </td>\n" + 
		            		"    <td>" + atteendees + 
		            		"    </td>\n" + 
		            		"    </tr>\n" + 
		            		"    <tr>\n" + 
		            		"    <td>\n" + 
		            		"      Non Attendees:\n" + 
		            		"    </td>\n" + 
		            		
		            		"    <td>" + nonatteendees + 
		            		"    </td>\n" + 
		            		"    </tr>\n" + 
		            		"    <tr>\n" + 
		            		"    <td>\n" + 
		            		"      Date:\n" + 
		            		"    </td>\n" + 
		            		"    <td>\n" + dateWithoutTime + 
		            		"    </td>\n" + 
		            		"    </tr>\n" + 
		            		"    <tr>\n" + 
		            		"    <td>\n" + 
		            		"      Time Taken:\n" + 
		            		"    </td>\n" + 
		            		"    <td> " + time_take +
		            		"    </td>\n" + 
		            		"    </tr>\n" + 
		            		"    <tr>\n" + 
		            		"    <td>\n" + 
		            		"      Release Number:\n" + 
		            		"    </td>\n" + 
		            		"    <td>\n" + release_no +
		            		"     </td>\n" + 
		            		"   </tr>\n" + 
		            		"  </table>\n" + 
		            		"  <table border=\"1\" style=\"width:100%\">\n" + 
		            		"    <tr><th style=\"width:25%\">JIRA Number</th><th style=\"width:25%\">Name</th><th style=\"width:25%\">Owner</th><th style=\"width:25%\">Update</th></tr>\n" + 
		            		"    <tr>\n" + email_structure +
		            
		            		"    </tr>\n" + 
		            		"  </table>\n" + 
		            		"</body>\n" + 
		            		"</html>","text/html" );
		            
			
		            Transport.send(message);

		            System.out.println("Mail Successfully Sent");

		            
		            }
		        }
		        catch (MessagingException e) {
		            e.printStackTrace();
		        }
		
			
			model.addAttribute("my_details", operations.getUsers(soeid));
		
			return "my_details";
		}
		else {
			return "login";
		}
		
		
	}
}
