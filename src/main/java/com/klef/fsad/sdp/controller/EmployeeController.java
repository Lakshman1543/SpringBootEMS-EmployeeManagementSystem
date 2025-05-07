package com.klef.fsad.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klef.fsad.sdp.model.Duty;
import com.klef.fsad.sdp.model.Employee;
import com.klef.fsad.sdp.model.Leave;
import com.klef.fsad.sdp.security.JWTUtilizer;
import com.klef.fsad.sdp.services.EmployeeService;
import com.klef.fsad.sdp.services.LeaveService;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {
	
	@Autowired
	private JWTUtilizer jwtService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private LeaveService leaveService;
	
	private boolean isAuthorized(String authHeader, String expectedrole) {
		try {
			String token = authHeader.substring(7);
			String role = jwtService.validateToken(token).get("role");
			
			return role.equals(expectedrole);
		} catch(Exception e) {
			return false;
		}
	}
	
	@PostMapping(value = "/addemployee", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> registerEmployee(
			@RequestParam String name, 
			@RequestParam String gender,
			@RequestParam int age,
			@RequestParam String designation,
			@RequestParam String department,
			@RequestParam double salary,
			@RequestParam String username,
			@RequestParam String email, 
			@RequestParam String contact, 
			@RequestParam MultipartFile photo) {
		
		try {
			Employee emp = new Employee();
			emp.setName(name);
			emp.setGender(gender);
			emp.setAge(age);
			emp.setDesignation(designation);
			emp.setDepartment(department);
			emp.setSalary(salary);
			emp.setUsername(username);
			emp.setEmail(email);
			emp.setContact(contact);
			emp.setPhoto(photo.getBytes());
			
			return ResponseEntity.ok(employeeService.registerEmployee(emp));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Occured");
		}
	}
	
	@GetMapping("/viewprofile")
	public ResponseEntity<Employee> viewEmpProfile(@RequestParam Long empid,@RequestHeader("Authorization") String authHeader) {
		if(!isAuthorized(authHeader, "EMPLOYEE")) {
			return ResponseEntity.status(403).body(null);
		}
		
		return ResponseEntity.ok(employeeService.findEmployeeById(empid));
	}
	
	@GetMapping("/viewduties")
	public ResponseEntity<List<Duty>> viewAssigedDuties(@RequestParam Long empid,@RequestHeader("Authorization") String authHeader) {
		if(!isAuthorized(authHeader, "EMPLOYEE")) {
			return ResponseEntity.status(403).body(null);
		}
		
		return ResponseEntity.ok(employeeService.viewAssignedDuties(empid));
	}
	
	@PostMapping("/applyleave")
	public ResponseEntity<Leave> applyLeave(@RequestBody Leave leave, @RequestParam Long empid, @RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);
		if(!jwtService.validateToken(token).get("role").equals("EMPLOYEE")) {
			return ResponseEntity.status(403).body(null);
		}
		
		Leave l = leaveService.applyLeaveByEmployee(leave, empid);
		return ResponseEntity.ok(l);
	}
	
	@GetMapping("/viewownleaves") 
	public ResponseEntity<List<Leave>> viewAllOwnLeaves(@RequestParam Long empid, @RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);
		if(!jwtService.validateToken(token).get("role").equals("EMPLOYEE")) {
			return ResponseEntity.status(403).body(null);
		}
		
		List<Leave> le = leaveService.viewLeavesByEmployee(empid);
		return ResponseEntity.ok(le);
	}
}
