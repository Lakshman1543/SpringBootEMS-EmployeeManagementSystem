package com.klef.fsad.sdp.services;

import java.util.List;

import com.klef.fsad.sdp.model.Admin;
import com.klef.fsad.sdp.model.Employee;
import com.klef.fsad.sdp.model.Leave;
import com.klef.fsad.sdp.model.Manager;

public interface AdminService {
	
	public Admin checkadminlogin(String username, String password);
	
	public Manager addManager(Manager manager);
	public List<Manager> viewAllManagers();
	public String deleteManager(Long mid);
	public List<Employee> viewAllEmployees();
	public String deleteEmployee(Long eid);
	public long managercount();
	public long employeecount();
	
	public List<Leave> viewAllLeaveApplications();
}