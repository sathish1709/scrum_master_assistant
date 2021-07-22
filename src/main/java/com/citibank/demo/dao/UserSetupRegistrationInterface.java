package com.citibank.demo.dao;

import java.util.List;

import com.citibank.demo.business.UserSetupRegistration;

public interface UserSetupRegistrationInterface {

	public List<UserSetupRegistration> allUsers();
	public UserSetupRegistration getUsers(String soeid);
	public UserSetupRegistration editUser(UserSetupRegistration u);
	public String deleteUser(String soeid);
	public boolean checkUser(String soeid);
	public boolean checkUserInUserLog(String soeid);
	public UserSetupRegistration addUser(UserSetupRegistration u);
}
