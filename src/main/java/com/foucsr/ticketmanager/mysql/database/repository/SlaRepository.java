package com.foucsr.ticketmanager.mysql.database.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.foucsr.ticketmanager.mysql.database.model.SlaPolicy;
public interface SlaRepository extends JpaRepository<SlaPolicy, Integer>  
{

	@Query(value="SELECT * FROM SLA_MASTER WHERE SLA_ID=:sla_Id",nativeQuery = true)
	SlaPolicy findSlaPolicyById(@Param("sla_Id") Long sla_Id);
	  

}  
