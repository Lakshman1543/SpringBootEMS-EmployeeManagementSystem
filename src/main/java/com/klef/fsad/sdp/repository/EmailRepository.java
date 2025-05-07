package com.klef.fsad.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer>{

}
