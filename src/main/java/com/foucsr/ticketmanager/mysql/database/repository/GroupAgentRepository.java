package com.foucsr.ticketmanager.mysql.database.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foucsr.ticketmanager.mysql.database.model.GroupAgents;

@Repository
public interface GroupAgentRepository extends JpaRepository<GroupAgents, Long>
{
	@Query(value="SELECT * FROM GROUP_AGENTS WHERE GROUPAGENT_ID=:groupAgentId",nativeQuery = true)
	GroupAgents findGroupAgentsById(@Param("groupAgentId") Long groupAgentId);
	
	@Query(value="SELECT * FROM GROUP_AGENTS WHERE GROUPAGENT_NAME=:groupAgentName",nativeQuery = true)
	GroupAgents findGroupAgentsByName(@Param("groupAgentName") String groupAgentName);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM GROUP_AGENTS WHERE GROUPAGENT_ID=:groupAgentId", nativeQuery = true)
	void deleteByIdMappings(@Param("groupAgentId")Long groupAgentId);
	
	@Query(value = "SELECT GROUPAGENT_NAME from GROUP_AGENTS", nativeQuery = true)
	List<String> getListOfGroupNames();
	
	Boolean existsByGroupAgentName(String groupAgentName);
	
}
