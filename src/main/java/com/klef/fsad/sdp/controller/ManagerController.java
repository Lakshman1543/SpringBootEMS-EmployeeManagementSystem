package com.klef.fsad.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.sdp.model.Duty;
import com.klef.fsad.sdp.model.Employee;
import com.klef.fsad.sdp.model.Leave;
import com.klef.fsad.sdp.security.JWTUtilizer;
import com.klef.fsad.sdp.services.DutyService;
import com.klef.fsad.sdp.services.LeaveService;
import com.klef.fsad.sdp.services.ManagerService;

@RestController
@RequestMapping("/manager")
@CrossOrigin("*")
public class ManagerController {
	
	@Autowired
	private JWTUtilizer jwtService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private DutyService dutyService;
	
	private boolean isAuthorized(String authHeader, String expectedrole) {
		try {
			String token = authHeader.substring(7);
			String role = jwtService.validateToken(token).get("role");
			
			return role.equals(expectedrole);
		} catch(Exception e) {
			return false;
		}
	}
	
	@GetMapping("/viewallemployees")
	public ResponseEntity<List<Employee>> viewAllEmployees(@RequestHeader("Authorization") String authHeader) {
		if(!isAuthorized(authHeader, "MANAGER")) {
			return ResponseEntity.status(403).body(null);
		}
		
		return ResponseEntity.ok(managerService.viewAllEmployees());
	}
	
	@PutMapping("/updateempaccstatus") 
	public ResponseEntity<String> updateEmployeeAccountStatus(@RequestParam Long empid, @RequestParam String status,@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);
		if(!jwtService.validateToken(token).get("role").equals("MANAGER")) {
			return ResponseEntity.status(403).body("Access Denied! Manager privileges required");
		}
		
		String message = managerService.updateEmployeeAccountStatus(empid, status.toUpperCase());
		return ResponseEntity.ok(message);
	}
	
	@PostMapping("/applyleave")
	public ResponseEntity<Leave> applyLeave(@RequestBody Leave leave, @RequestParam Long managerid, @RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);
		if(!jwtService.validateToken(token).get("role").equals("MANAGER")) {
			return ResponseEntity.status(403).body(null);
		}
		
		Leave l = leaveService.applyLeaveByManager(leave, managerid);
		return ResponseEntity.ok(l);
	}
	
	@GetMapping("/viewownleaves") 
	public ResponseEntity<List<Leave>> viewAllOwnLeaves(@RequestParam Long managerid, @RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);
		if(!jwtService.validateToken(token).get("role").equals("MANAGER")) {
			return ResponseEntity.status(403).body(null);
		}
		
		List<Leave> le = leaveService.viewLeavesByManager(managerid);
		return ResponseEntity.ok(le);
	}
	
	@PutMapping("/updateleavestatus")
	public ResponseEntity<String> updateEmployeeLeaveStatus(@RequestHeader("Authorization") String authHeader,@RequestParam int leaveid,@RequestParam String status) {
		String token = authHeader.substring(7);
		if(!jwtService.validateToken(token).get("role").equals("MANAGER")) {
			return ResponseEntity.status(403).body("Access Denied! Manager privileges required");
		}
		
		String result = leaveService.updateLeaveStatus(leaveid, status);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/assigndutytoemp") 
	public ResponseEntity<String> assignDutyToEmployee(@RequestBody Duty duty, @RequestParam Long managerid, @RequestParam Long empid,@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);
		if(!jwtService.validateToken(token).get("role").equals("MANAGER")) {
			return ResponseEntity.status(403).body("Access Denied! Manager privileges required");
		}
		
		dutyService.assignDutyByManagerToEmployee(duty, empid, managerid);
		return ResponseEntity.ok("Duty Assinged to Employee Successfully");
	}
	
	public ResponseEntity<List<Duty>> viewAssingedDuties(@RequestParam Long managerid,@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);
		if(!jwtService.validateToken(token).get("role").equals("MANAGER")) {
			return ResponseEntity.status(403).body(null);
		}
		
		List<Duty> le = dutyService.viewDutiesAssignedByManager(managerid);
		return ResponseEntity.ok(le);
	}
}
