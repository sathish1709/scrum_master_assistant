package com.citibank.demo;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citibank.demo.business.UserAuth;

@RestController
@CrossOrigin
public class UserSearchRestContoller {

	final static Logger logger = Logger.getLogger(UserSearchRestContoller.class);
	
	@Value("${spring.datasource.url}")
	private String CONNECTION_URL;

	@RequestMapping(method=RequestMethod.GET, value="/allsoeid")
	public List<String> getCustomersalist(@RequestParam String text) {
		List<String> soeid = new ArrayList<>();
	
		Connection cn = null;
		ResultSet rs = null;
		if(text.length() == 0) {
			return soeid;
		}
		else {
			try{
				Driver d = new com.mysql.cj.jdbc.Driver();
				DriverManager.registerDriver(d);
				cn = DriverManager.getConnection(CONNECTION_URL,"root","c0nygre");
				String sql = "SELECT soeid FROM employee WHERE " +
				"emp_designation ='Developer' AND soeid LIKE ?";
				PreparedStatement st = cn.prepareStatement(sql);
				st.setString(1, text + "%");
				rs = st.executeQuery();
				// Process the results of the query, one row at a time
				while (rs.next()) { 
					soeid.add(rs.getString(1));
					
				}
			}
			catch(SQLException ex){
				logger.error("SQL Execption"+ ex);
			}
			finally{
				if(cn != null){
					try{
						if(!cn.isClosed()){
							cn.close();
						}
					}
					catch(SQLException ex){
						logger.error("SQL Execption"+ ex);
					}
				}
			}
		}
		return soeid;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/allmsoeid")
	public List<String> getmanageralist(@RequestParam String text) {
		List<String> manager_soeid = new ArrayList<>();
	
		Connection cn = null;
		ResultSet rs = null;
		if(text.length() == 0) {
			return manager_soeid;
		}
		else {
			try{
				Driver d = new com.mysql.cj.jdbc.Driver();
				DriverManager.registerDriver(d);
				cn = DriverManager.getConnection(CONNECTION_URL,"root","c0nygre");
				String sql = "SELECT soeid FROM employee WHERE " +
				"soeid LIKE ?"+" AND emp_designation ='Project Manager'";
				PreparedStatement st = cn.prepareStatement(sql);
				st.setString(1, text + "%");
				rs = st.executeQuery();
				// Process the results of the query, one row at a time
				while (rs.next()) { 
					manager_soeid.add(rs.getString(1));
					
				}
			}
			catch(SQLException ex){
				logger.error("SQL Execption"+ ex);
			}
			finally{
				if(cn != null){
					try{
						if(!cn.isClosed()){
							cn.close();
						}
					}
					catch(SQLException ex){
						logger.error("SQL Execption"+ ex);
					}
				}
			}
		}
		return manager_soeid;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/deletesoeid")
	public List<String> deleteUseralist(@RequestParam String text) {
		List<String> dsoeid = new ArrayList<>();
	
		Connection cn = null;
		ResultSet rs = null;
		if(text.length() == 0) {
			return dsoeid;
		}
		else {
			try{
				Driver d = new com.mysql.cj.jdbc.Driver();
				DriverManager.registerDriver(d);
				cn = DriverManager.getConnection(CONNECTION_URL,"root","c0nygre");
				String sql = "SELECT soeid FROM team_info WHERE " +
				"soeid LIKE ?";
				PreparedStatement st = cn.prepareStatement(sql);
				st.setString(1, text + "%");
				rs = st.executeQuery();
				// Process the results of the query, one row at a time
				while (rs.next()) { 
					dsoeid.add(rs.getString(1));
					
				}
			}
			catch(SQLException ex){
				logger.error("SQL Execption"+ ex);
			}
			finally{
				if(cn != null){
					try{
						if(!cn.isClosed()){
							cn.close();
						}
					}
					catch(SQLException ex){
						System.out.println(ex.getMessage());
					}
				}
			}
		}
		return dsoeid;
	}
}
