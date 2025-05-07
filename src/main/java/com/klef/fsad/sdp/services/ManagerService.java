package com.klef.fsad.sdp.services;

import java.util.List;

import com.klef.fsad.sdp.model.Employee;
import com.klef.fsad.sdp.model.Manager;

public interface ManagerService {
	
	public Manager checkmanagerlogin(String username, String password);
	public Manager findManagerById(Long id);
	public Manager findManagerByUsername(String username);
	public Manager findManagerByEmail(String email);
	public List<Manager> viewAllManagers();
	public List<Employee> viewAllEmployees();
	public String updateEmployeeAccountStatus(Long employeeid, String status);
	
	public String generateResetToken(String email);
	public boolean validateResetToken(String token);
	public boolean changePassword(Manager manager, String oldPassword, String newPassword);
	public void updatePassword(String token, String newPassword);
	public void deleteResetToken(String token);
	public boolean isTokenExpired(String token);
}
