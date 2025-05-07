package com.klef.fsad.sdp.services;

import java.util.List;

import com.klef.fsad.sdp.model.Duty;
import com.klef.fsad.sdp.model.Employee;

public interface EmployeeService {
	
	public Employee checkemplogin(String username, String password);
	public String registerEmployee(Employee emp);
	public String updateEmployeeProfile(Employee emp);
	public Employee findEmployeeById(Long id);
	public Employee findEmployeeByUsername(String username);
	public Employee findEmployeeByEmail(String email);
	public List<Employee> viewAllEmployees();
	
	public String updateAccountStatus(Long empid, String status);
	public List<Duty> viewAssignedDuties(Long empid);
	
	public String generateResetToken(String email);
	public boolean validateResetToken(String token);
	public boolean changePassword(Employee employee, String oldPassword, String newPassword);
	public void updatePassword(String token, String newPassword);
	public void deleteResetToken(String token);
	public boolean isTokenExpired(String token);
}
