package com.foucsr.ticketmanager.mysql.database.repository;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foucsr.ticketmanager.mysql.database.model.Agent;
import com.foucsr.ticketmanager.mysql.database.model.Role;

@Repository 
public interface AgentRepository extends JpaRepository<Agent, Long> {

	@Query(value = "SELECT * FROM AGENT_DETAILS WHERE AGENTID = :agent_id", nativeQuery = true)
	Agent findAgentById(@Param("agent_id") Long agent_id);
	
	@Query(value = "SELECT * FROM AGENT_DETAILS ORDER BY AGENTID", nativeQuery = true)
	List<Agent> findListOfAgents();

	@Query(value = "SELECT GROUPAGENT_NAME from GROUP_AGENTS", nativeQuery = true)
	List<String> getListOfGroupNames();
	
	@Query(value = "select concat(FIRSTNAME, ' ',LASTNAME) 'AgentName' from AGENT_DETAILS",nativeQuery = true)
	List<String> getAgentNamebyGroup();
	
}
