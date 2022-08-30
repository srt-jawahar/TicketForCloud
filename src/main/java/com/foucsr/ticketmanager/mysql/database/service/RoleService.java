package com.foucsr.ticketmanager.mysql.database.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foucsr.ticketmanager.exception.IdNotFoundException;
import com.foucsr.ticketmanager.mysql.database.model.GroupAgents;
import com.foucsr.ticketmanager.mysql.database.model.Role;
import com.foucsr.ticketmanager.mysql.database.repository.RoleRepository;
import com.foucsr.ticketmanager.payload.ApiResponse;
import com.foucsr.ticketmanager.util.SCAUtil;

@Service
public class RoleService 
{
	@Autowired
	private RoleRepository roleRepository;
	
	Logger log = LoggerFactory.getLogger(RoleService.class);
	SCAUtil sca = new SCAUtil();
	
	public ResponseEntity<?> createOrUpdateRoles(Role role)
	{
		try
		{
//			Long id = (long) 301;
			
			String name = role.getName();
			
			if(name != null)
			{
				Optional<Role> roleNames = roleRepository.findByName(name);
				
				if(name == null)
				{
					return new ResponseEntity(new ApiResponse(false, "No Roles Found"),
							HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
				}
			}
			
			roleRepository.save(role);
		}
		catch(Exception e)
		{
			String msg = sca.getErrorMessage(e);
			
			return new ResponseEntity(new ApiResponse(false, "Unable to save Roles" + msg),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(role,HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<?> getRoles() {

		List<Role> roleList;

		try {
			roleList = roleRepository.findAll();

			if (roleList == null) {
				roleList = new ArrayList<Role>();
			}
		} catch (Exception e) {
			SCAUtil sca = new SCAUtil();
			log.error("***!!! Not able to fetch roleList!!! ***");

			String msg = sca.getErrorMessage(e);

			return new ResponseEntity(new ApiResponse(false, "Couldnt get Roles!" + msg),
					HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity(roleList, HttpStatus.OK);
	}
	
	// GET BY ROLE ID
	public ResponseEntity<Role> getRolebyId(Long id) throws IdNotFoundException {
		Role getRoles = roleRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Given Role Id not Found"));
		
		return new ResponseEntity(getRoles,HttpStatus.OK);
	}
	
	// DELETE BY ID
		public ResponseEntity<?> deleteRoles(Long id) throws IdNotFoundException {
			Role delRole = roleRepository.findById(id)
					.orElseThrow(() -> new IdNotFoundException("Role does not Exsist"));
			roleRepository.delete(delRole);
			return new ResponseEntity("Deleted Successfully", HttpStatus.OK);
		}
	
	
//	public ResponseEntity<Role> deleteRoleByName(String name)
//	{
//		Optional<Role> deleteRole = roleRepository.findByName(name).orElseThrow(() -> new IdNotFoundException("Given Id not Found"));
//		roleRepository.delete(deleteRole);
//	}
}
