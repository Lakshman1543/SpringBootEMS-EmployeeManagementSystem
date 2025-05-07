package com.klef.fsad.sdp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.model.ResetToken;

@Repository
public interface ResetTokenRepository  extends JpaRepository<ResetToken, Long>{

	@Query("select r from ResetToken r where r.token=?1")
	public ResetToken FindByToken(String token);
	
	public Optional<ResetToken> findByToken(String token);
	public Optional<ResetToken> findByEmail(String email);
	public void deleteByToken(String token);
}
