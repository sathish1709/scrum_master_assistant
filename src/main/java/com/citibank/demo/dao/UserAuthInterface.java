package com.citibank.demo.dao;

import com.citibank.demo.business.UserAuth;


public interface UserAuthInterface {

	public void addDefaultPassword(UserAuth ua);
	public void deleteDefaultPassword(String soeid);
	public void updatePassword(UserAuth ua);
	public void insertUserlogin(String soeid);
	public String getUserCredential(String soeid);
}
