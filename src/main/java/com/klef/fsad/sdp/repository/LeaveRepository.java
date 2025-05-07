package com.klef.fsad.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.model.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {
	
	public List<Leave> findByEmployeeId(Long eid);
	public List<Leave> findByStatus(String status);
	public List<Leave> findByManagerId(Long mid);

}
