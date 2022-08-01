package com.foucsr.ticketmanager.mysql.database.repository;

import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foucsr.ticketmanager.mysql.database.model.Agent;

@Repository 
public interface AgentRepository extends JpaRepository<Agent, Long> {

	@Query(value = "SELECT * FROM AGENT_DETAILS WHERE AGENTID = :agent_id", nativeQuery = true)
	Agent findAgentById(@Param("agent_id") Long agent_id);
	
	@Query(value = "SELECT * FROM AGENT_DETAILS ORDER BY AGENTID", nativeQuery = true)
	List<Agent> findListOfAgents();

	
	
}
