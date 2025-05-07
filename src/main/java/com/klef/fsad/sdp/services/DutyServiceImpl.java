package com.klef.fsad.sdp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.klef.fsad.sdp.model.Admin;
import com.klef.fsad.sdp.model.Duty;
import com.klef.fsad.sdp.model.Employee;
import com.klef.fsad.sdp.model.Manager;
import com.klef.fsad.sdp.repository.AdminRepository;
import com.klef.fsad.sdp.repository.DutyRepository;
import com.klef.fsad.sdp.repository.EmployeeRepository;
import com.klef.fsad.sdp.repository.ManagerRepository;

public class DutyServiceImpl implements DutyService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private DutyRepository dutyRepository;
	@Autowired
	private ManagerRepository managerRepository;
	
	
	@Override
	public Duty assignDutyByAdminToEmployee(Duty duty, Long empid, int adminid) {
		Employee emp = employeeRepository.findById(empid).orElse(null);
		Admin admin = adminRepository.findById(adminid).orElse(null);
		if (emp != null && admin != null) {
			duty.setEmployee(emp);
			duty.setAssingedByAdmin(admin);
			return dutyRepository.save(duty);
		}
		return null;
	}

	@Override
	public Duty assignDutyByAdminToManager(Duty duty, Long managerid, int adminid) {
		Manager manager = managerRepository.findById(managerid).orElse(null);
		Admin admin = adminRepository.findById(adminid).orElse(null);
		if (manager != null && admin != null) {
			duty.setEmployee(null);
			duty.setManager(manager);
			duty.setAssingedByAdmin(admin);
			return dutyRepository.save(duty);
		}
		return null;
	}

	@Override
	public Duty assignDutyByManagerToEmployee(Duty duty, Long empid, Long managerid) {
		Employee emp = employeeRepository.findById(empid).orElse(null);
		Manager manager = managerRepository.findById(managerid).orElse(null);
		if (emp != null && manager != null) {
			duty.setEmployee(emp);
			duty.setAssingedByManager(manager);
			return dutyRepository.save(duty);
		}
		return null;
	}

	@Override
	public List<Duty> viewAllDutiesofEmployee(Long eid) {
		return dutyRepository.findByEmployeeId(eid);
	}

	@Override
	public List<Duty> viewDutiesAssignedByManager(Long managerid) {
		return dutyRepository.findByAssingedByManager(managerid);
	}

	@Override
	public List<Duty> viewDutiesAssignedByAdmin(int adminid) {
		return dutyRepository.findByAssingedByAdmin(adminid);
	}
}
