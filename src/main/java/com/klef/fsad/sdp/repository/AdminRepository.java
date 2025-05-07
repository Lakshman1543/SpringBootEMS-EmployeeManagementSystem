package com.klef.fsad.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public Admin findByUsernameAndPassword(String username, String password);
}
