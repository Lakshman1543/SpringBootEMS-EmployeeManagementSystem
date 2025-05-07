package com.klef.fsad.sdp.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klef.fsad.sdp.model.Admin;
import com.klef.fsad.sdp.model.Email;
import com.klef.fsad.sdp.model.Employee;
import com.klef.fsad.sdp.model.Leave;
import com.klef.fsad.sdp.model.Manager;
import com.klef.fsad.sdp.repository.AdminRepository;
import com.klef.fsad.sdp.repository.EmailRepository;
import com.klef.fsad.sdp.repository.EmployeeRepository;
import com.klef.fsad.sdp.repository.LeaveRepository;
import com.klef.fsad.sdp.repository.ManagerRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmailRepository emailRepository;
	@Autowired
	private LeaveRepository leaveRepository;
	@Autowired
	private EmailService emailService;

	@Override
	public Admin checkadminlogin(String username, String password) {
		return adminRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public Manager addManager(Manager manager) {
		Long manager_id = generateRandomManagerId();
		String randomPassword = generateRandomPassword(8);
	
		manager.setId(manager_id);
		manager.setPassword(randomPassword);
		Manager savedManager = managerRepository.save(manager);
		
		Email e = new Email();
		e.setRecipient(manager.getEmail());
		e.setSubject("Welcome Manager to EMS!");
		e.setMessage("Hi " + manager.getName() + ", \n\nYou have been successfully added. \n\nManager ID: " + manager.getId() + "\nUsername: " + manager.getUsername() + "\nPassword: " + manager.getPassword());
		emailRepository.save(e);
		emailService.sendEmail(e.getRecipient(),e.getSubject(),e.getMessage());
		
		return savedManager;
	}

	@Override
	public List<Manager> viewAllManagers() {
		return managerRepository.findAll();
	}

	@Override
	public String deleteManager(Long mid) {
		Optional<Manager> manager = managerRepository.findById(mid);
		if(manager.isPresent()) {
			managerRepository.deleteById(mid);
			return "Manager Deleted Successfully...!!!";
		}
		else {
			return "Manager ID not Found";
		}
	}

	@Override
	public List<Employee> viewAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public String deleteEmployee(Long eid) {
		Optional<Employee> employee = employeeRepository.findById(eid);
		if(employee.isPresent()) {
			employeeRepository.deleteById(eid);
			return "Employeee Deleted Successfully...!!!";
		}
		else {
			return "Employee ID not Found";
		}
	}

	@Override
	public long managercount() {
		return managerRepository.count();
	}

	@Override
	public long employeecount() {
		return employeeRepository.count();
	}

	@Override
	public List<Leave> viewAllLeaveApplications() {
		return leaveRepository.findAll();
	}
	
	private long generateRandomManagerId() {
		Random random = new Random();
		return 1000 + random.nextInt(9000);
	}
	
	private String generateRandomPassword(int length) {
		String upper = "ABCDEFHIJKLMNOPQRSTUVWXYZ";
		String lower = "abcdefghijklmnopqrstuvwxyz";
		String digits = "0123456789";
		String special = "~!@#$%^&*";
		String combined = upper + lower + digits + special;
		
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		
		sb.append(upper.charAt(random.nextInt(upper.length()))); 
		// upper.length() is 26
		// suppose random.nextInt(26) gives 7
		// so upper.charAt(7) is H 
		// Then sb.append('H') will add this into String builder.
		sb.append(lower.charAt(random.nextInt(lower.length())));
		sb.append(digits.charAt(random.nextInt(digits.length())));
		sb.append(special.charAt(random.nextInt(special.length())));
		
		for(int i = 4 ; i < length ; i++) {
			sb.append(combined.charAt(random.nextInt(combined.length())));
		}
		
		return sb.toString();
	}
}
