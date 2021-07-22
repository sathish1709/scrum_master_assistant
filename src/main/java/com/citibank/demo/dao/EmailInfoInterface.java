package com.citibank.demo.dao;

import java.util.List;

import com.citibank.demo.business.EmailInfo;
import com.citibank.demo.business.ProjectInfo;
import com.citibank.demo.business.TaskInfo;

public interface EmailInfoInterface {
	
	public List<EmailInfo> sendEmail(String release_no, String soeid);
	public List<EmailInfo> getEmailId(String manager_soeid);

}
