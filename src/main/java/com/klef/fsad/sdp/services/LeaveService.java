package com.klef.fsad.sdp.services;

import java.util.List;

import com.klef.fsad.sdp.model.Leave;

public interface LeaveService {
	
	public Leave applyLeaveByEmployee(Leave leave, Long empid);
	public List<Leave> viewLeavesByEmployee(Long empid);
	public List<Leave> viewAllPendingLeaves();
	public Leave applyLeaveByManager(Leave leave, Long managerid);
	public List<Leave> viewLeavesByManager(Long managerid);
	public String updateLeaveStatus(int leaveid, String status); // Accepted or Rejected	
}
