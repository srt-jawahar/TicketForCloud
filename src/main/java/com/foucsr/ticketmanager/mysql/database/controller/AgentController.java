package com.foucsr.ticketmanager.mysql.database.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foucsr.ticketmanager.mysql.database.model.Agent;
import com.foucsr.ticketmanager.mysql.database.service.AgentService;
import com.foucsr.ticketmanager.mysql.database.service.MapValidationErrorService;

@RestController
@RequestMapping("/Agent")
public class AgentController {
	
@Autowired 
AgentService agentService;

@Autowired
private MapValidationErrorService mapValidationErrorService;
//Create and Update 

@PostMapping("/createOrUpdateAgentDetails")
public ResponseEntity<?> createOrUpdateAgentDetails(HttpServletRequest http, @Valid @RequestBody Agent agentdetails
		,BindingResult result, Principal principal	) {


	ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
	if (errorMap != null)
		return errorMap;
	ResponseEntity<?> agentResponse = agentService.createOrUpdateAgent(agentdetails,http);

	return agentResponse;
}

@GetMapping("/getListOfAgents")
public List<Agent> getAllProjects(Principal principal) {
	List<Agent> listofUsers = agentService.findAllProjects(principal.getName());
	return listofUsers;
}

//Creating a get Mapping the specific details
@GetMapping("/agent/{agentid}")
private ResponseEntity<Agent> getAgent(@PathVariable("agentid")Long agentid)
{
	return agentService.getAgentbyId(agentid);
	
}


//creating a get mapping that retrieves all the Agent details
@GetMapping("/getallagent")
private List<Agent>getAllAgent()
{
	return agentService.get_Agent();
}

//deleting the particular id
@DeleteMapping("/deleteagentbyid/{agentid}")
private void deleteAgent(@PathVariable("agentid")Long agentid)
{
	agentService.delete(agentid);
}
//that post all the agent details in the database
@PostMapping("/saveagent")
private Long saveAgent(@RequestBody Agent agents)
{
	 agentService.saveAgent(agents);
	 return agents.getAgent_id();
	
}
//Update the agent details
@PutMapping("/updateagents")
private Agent update(@RequestBody Agent agents)
{
	agentService.saveAgent(agents);
	return agents;
}
@RequestMapping("/showpage")
public String showpage1() {
      return "test";
}


@GetMapping("/getListofAgentNames")
public ResponseEntity<?> getListOfAgentNames()
{
	ResponseEntity<?> mess = agentService.getListofAgentNames();
	return mess;
}

}
