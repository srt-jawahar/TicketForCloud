package com.foucsr.ticketmanager.mysql.database.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foucsr.ticketmanager.mysql.database.model.GroupAgents;
import com.foucsr.ticketmanager.mysql.database.model.Role;
import com.foucsr.ticketmanager.mysql.database.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController 
{
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/createorupdateRoles")
	public ResponseEntity<?> createorupdateRoles(@RequestBody Role roles,HttpServletRequest http)
	{
		//return ResponseEntity.status(HttpStatus.OK).body(groupAgentService.CreateorUpdateGroupAgents(groupAgents));
		ResponseEntity<?> createorupdateRoles = roleService.createOrUpdateRoles(roles);
		return createorupdateRoles;
	}
	
	@GetMapping("getRoles")
	public ResponseEntity<?> getRoles()
	{
		ResponseEntity<?> getRoles = roleService.getRoles ();
		return getRoles;	
	}
	
	@GetMapping("getRole/{id}")
	public ResponseEntity<?> getRoles(@PathVariable Long id)
	{
		ResponseEntity<?> getrole = roleService.getRolebyId(id);
		return getrole;
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteRoles(@PathVariable Long id)
	{	
		ResponseEntity<?> delRole = roleService.deleteRoles(id);
		return delRole;
		//return ResponseEntity.status(HttpStatus.OK).body(groupAgentService.deleteGroupAgents(groupAgentId));
	}
	
}
