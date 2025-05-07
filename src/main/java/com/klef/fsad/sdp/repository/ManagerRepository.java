package com.klef.fsad.sdp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.model.Manager;


@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long>{
	public Manager findByUsernameAndPassword(String username, String password);
	public Manager findByUsername(String username);
	public Manager findByEmail(String email);
	
	public Optional<Manager> FindByEmail(String email);
}
