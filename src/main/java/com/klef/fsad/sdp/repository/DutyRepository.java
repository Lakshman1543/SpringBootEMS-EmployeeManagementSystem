package com.klef.fsad.sdp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.model.Duty;
import com.klef.fsad.sdp.model.Employee;

@Repository
public interface DutyRepository extends JpaRepository<Duty, Integer> {
	
	public List<Duty> findByEmployee(Employee employee);
	public List<Duty> findByEmployeeId(Long id);
	public List<Duty> findByAssingedByManager(Long managerid);
	public List<Duty> findByAssingedByAdmin(int adminid);
}
