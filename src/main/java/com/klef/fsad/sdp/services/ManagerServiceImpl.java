package com.klef.fsad.sdp.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.model.Employee;
import com.klef.fsad.sdp.model.Manager;
import com.klef.fsad.sdp.model.ResetToken;
import com.klef.fsad.sdp.repository.EmployeeRepository;
import com.klef.fsad.sdp.repository.ManagerRepository;
import com.klef.fsad.sdp.repository.ResetTokenRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ResetTokenRepository resetTokenRepository;
	
	@Override
	public Manager checkmanagerlogin(String username, String password) {
		return managerRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public Manager findManagerById(Long id) {
		return managerRepository.findById(id).get();
	}

	@Override
	public Manager findManagerByUsername(String username) {
		return managerRepository.findByUsername(username);
	}

	@Override
	public Manager findManagerByEmail(String email) {
		return managerRepository.findByEmail(email);
	}

	@Override
	public List<Manager> viewAllManagers() {
		return managerRepository.findAll();
	}

	@Override
	public List<Employee> viewAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public String updateEmployeeAccountStatus(Long employeeid, String status) {
		Optional<Employee> emp = employeeRepository.findById(employeeid);
		
		if(emp.isPresent()) {
			Employee e = new Employee();
			e.setAccountstatus(status); // Accept or Reject
			employeeRepository.save(e);
			return "Employee Account Status Updated Successfully";
		}
		else {
			return "Employee ID Not Found";
		}
	}

	@Override
	public String generateResetToken(String email) {
		Optional<Manager> manager = managerRepository.FindByEmail(email);
		if(manager.isPresent()) {
			String token = UUID.randomUUID().toString();
			
			ResetToken rt = new ResetToken();
			rt.setToken(token);
			rt.setEmail(email);
			rt.setCreatedAt(LocalDateTime.now());
			rt.setExpiresAt(LocalDateTime.now().plusMinutes(5)); // 5mins
			
			resetTokenRepository.save(rt);
			return token;
		}
		return null;
	}

	@Override
	public boolean validateResetToken(String token) {
		Optional<ResetToken> rt = resetTokenRepository.findByToken(token);
		return rt.isPresent() && !isTokenExpired(token);
	}

	@Override
	public boolean changePassword(Manager manager, String oldPassword, String newPassword) {
		if(manager.getPassword().equals(oldPassword)) {
			manager.setPassword(newPassword);
			managerRepository.save(manager);
			return true;
		}
		return false;
	}

	@Override
	public void updatePassword(String token, String newPassword) {
		Optional<ResetToken> resetToken = resetTokenRepository.findByToken(token);
		if(resetToken.isPresent() && !isTokenExpired(token)) {
			Manager m = new Manager();
			m.setPassword(newPassword);
			managerRepository.save(m);
			deleteResetToken(token);
		}
	}

	@Override
	public void deleteResetToken(String token) {
		resetTokenRepository.deleteByToken(token);
	}

	@Override
	public boolean isTokenExpired(String token) {
		Optional<ResetToken> rt = resetTokenRepository.findByToken(token);
		if(rt.isPresent()) {
			return rt.get().getExpiresAt().isBefore(LocalDateTime.now());
		}
		return true;
	}
}