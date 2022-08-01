package com.foucsr.ticketmanager.mysql.database.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foucsr.ticketmanager.mysql.database.model.Agent;
import com.foucsr.ticketmanager.mysql.database.repository.AgentRepository;
import com.foucsr.ticketmanager.payload.ApiResponse;
import com.foucsr.ticketmanager.util.SCAUtil;



@Service
public class AgentService {
	@Autowired
	AgentRepository agentRepo;

	Logger log=LoggerFactory.getLogger(AgentService.class);
	
	public ResponseEntity<?> createOrUpdateAgent(Agent agentdetails,HttpServletRequest http)
	{
		SCAUtil sca = new SCAUtil();
		try
		{
			Long id = agentdetails.getAgent_id();
		
		String value="";
		
		if(agentdetails.getFirstName()==null)
			value="firstName";
		if(agentdetails.getLastName()==null)
			value="lastName";
		if(agentdetails.getMobileNumber()==null)
			value="mobileNumber";
		if(agentdetails.getLandNumber()==null)
			value="landNumber";
		if(agentdetails.getTitle()==null)
			value="title";
		if(agentdetails.getEmail()==null)
			value = "email";
		
		if (!"".equals(value)) {

			return new ResponseEntity(new ApiResponse(false, "Should fill the mandatory field: " + value),
					HttpStatus.BAD_REQUEST);
		}
		//Long agent_details_id = agentdetails.getAgent_id();
		if(agentdetails!=null){
			
			List<Agent> updateAgent = agentRepo.findAll();
			//Agent agentdetailsquery=agentRepo.findAgentById(agentdetails);
			if(updateAgent==null)	{
				
				return new ResponseEntity(new ApiResponse(false,"no Agent "),HttpStatus.BAD_REQUEST);
			}
		}
		
    agentRepo.save(agentdetails) ;
		
	}catch(Exception e)
		{
		log.error("***************** Unable to save leave master!  *********************\n" + e);

		String msg = sca.getErrorMessage(e);

		return new ResponseEntity(new ApiResponse(false, "Unable to save Agent Details!" + msg),
				HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(agentdetails, HttpStatus.CREATED);
	}
	
	//finding list of users
	public List<Agent> findAllProjects(String username) {
		return agentRepo.findListOfAgents();
	}
	
	//getting a specific record
		public ResponseEntity<Agent> getAgentbyId(Long id)
		{
			Optional<Agent>agentopt=agentRepo.findById(id);
			if(agentopt.isPresent())
			{
				return new ResponseEntity<Agent>(agentopt.get(), HttpStatus.OK);
			}else
			{
				return new ResponseEntity<Agent>( HttpStatus.NOT_FOUND);
			}
			
		}
	
	
	//save specific record
	public void saveAgent(Agent agent)
	{
		 agentRepo.save(agent);
		
	}
	//saving all the record 
	public List<Agent> save_AgentDetails(List<Agent> agent)
	{
		return agentRepo.saveAll(agent);
	}
	//getting all agents 
	public List<Agent> get_Agent()
	{
		List<Agent> agents=new ArrayList<Agent>();
		agentRepo.findAll().forEach(agentsdet->agents.add(agentsdet));
		return agents;		
		
	}
	
	public ResponseEntity<Agent> get_Agentdetails()
	{
		
		List<Agent> agents=new ArrayList<Agent>();
		agentRepo.findAll().forEach(agentsdet->agents.add(agentsdet));
		return new ResponseEntity<Agent>(HttpStatus.OK);		
		
	}
	
	//deleting a specific record 
	public void delete(Long id)
	{
		agentRepo.deleteById(id);
	}
	
	//updating a record
	public void update(Agent agent,Long agentid)
	{
		 agentRepo.save(agent);
	}
	
}
