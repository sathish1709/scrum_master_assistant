package com.citibank.demo.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citibank.demo.dao.UserSetupRegistrationInterface;

@Component
public class UserSetupRegistrationOperations {


	private UserSetupRegistrationInterface userSetupRepository;
	
	@Autowired
	public UserSetupRegistrationOperations(UserSetupRegistrationInterface userSetupRepository) {
		super();
		this.userSetupRepository = userSetupRepository;
	}
	
	public List<UserSetupRegistration> allUsers() {
	
		return userSetupRepository.allUsers();
	}

	public UserSetupRegistration getUsers(String soeid) {
		
		return userSetupRepository.getUsers(soeid);
	}

	public UserSetupRegistration saveUser(UserSetupRegistration u) {
		
		return userSetupRepository.editUser(u);
	}

	public String deleteUser(String soeid) {
		
		return userSetupRepository.deleteUser(soeid);
	}

	public UserSetupRegistration newUser(UserSetupRegistration u) {
		
		return userSetupRepository.addUser(u);
	}
	
	public boolean isUserExist(String soeid) {
		return userSetupRepository.checkUser(soeid);
	}
	
	public boolean isUserExistInLoginAudit(String soeid) {
		return userSetupRepository.checkUserInUserLog(soeid);
	}

}
