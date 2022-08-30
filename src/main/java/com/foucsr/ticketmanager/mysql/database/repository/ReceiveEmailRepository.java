package com.foucsr.ticketmanager.mysql.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foucsr.ticketmanager.mysql.database.model.ReceiveEmail;

@Repository
public interface ReceiveEmailRepository extends JpaRepository<ReceiveEmail, Long> {
	
	@Override
    List<ReceiveEmail> findAll();
	
	 @Query(value = "select * from RECEIVE_EMAIL_DETAILS", nativeQuery = true)
	 Optional<ReceiveEmail> findOneEmailDetails();
     
	 @Query(value = " select * from RECEIVE_EMAIL_DETAILS", nativeQuery = true)
	 ReceiveEmail findOne();
	
}	