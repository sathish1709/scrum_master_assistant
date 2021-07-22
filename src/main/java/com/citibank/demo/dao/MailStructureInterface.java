package com.citibank.demo.dao;

import java.util.List;

import com.citibank.demo.business.MailStructure;
import com.citibank.demo.business.TaskInfo;

public interface MailStructureInterface {
	public List<MailStructure> getTasksByRelease(String release_no, String soeid);

}
