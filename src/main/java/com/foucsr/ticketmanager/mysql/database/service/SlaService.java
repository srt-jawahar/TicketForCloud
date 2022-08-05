package com.foucsr.ticketmanager.mysql.database.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foucsr.ticketmanager.mysql.database.model.SlaPolicy;
import com.foucsr.ticketmanager.mysql.database.repository.SlaRepository;
import com.foucsr.ticketmanager.payload.ApiResponse;
import com.foucsr.ticketmanager.util.SCAUtil;



@Service
public class SlaService {
	@Autowired
	private SlaRepository slapolicyrepo;
	public ResponseEntity<?> createOrUpdateSlaPolicy(SlaPolicy slapolicy)
	{
		String value ="";
		
		if(slapolicy.getSlaPolicyName()==null) {
			value = "getSlaPolicyName";
		}
		if(slapolicy.getDescription()==null) {
			value="getDescription";
		}
		if(slapolicy.getPriority()==null) {
			value ="getPriority";
		}
		if(slapolicy.getResponseTime()==null) {
			value ="getResponseTime";
		}
		if(slapolicy.getResolveTime()==null) {
			value ="getResolveTime";
		}
		if(slapolicy.getOperationalHrs()==null) {
			value ="getOperationalHrs";
		}
		if(slapolicy.getEscalationMail()==null) {
			value ="getEscalationMail";
		}


//		//NOT NULL CHECK
		if(!"".equals(value))
		{
			return new ResponseEntity(new ApiResponse(false,"Null Values can't allowed" + value),HttpStatus.NOT_ACCEPTABLE);
		}
		
		
//		// TICKET ASSIGNMENTS (Y/N)
//		if(!"Y".equals(SlaPolicy.getTicketAssignment())||!"N".equals(SlaPolicy.getTicketAssignment())) {
//			
//			return new ResponseEntity(new ApiResponse(false, "Only Y/N type"),HttpStatus.NOT_ACCEPTABLE);
//		}
		
		try
		{
			Long sla_Id = slapolicy.getSla_Id();
			
			if(slapolicy.getSla_Id() != null)
			{
				SlaPolicy isSlaPolicy = slapolicyrepo.findSlaPolicyById(sla_Id);
				
				if(isSlaPolicy == null)
				{
					return new ResponseEntity(new ApiResponse(false, "No SLA Policy Found"),HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
				}
			}
			
			slapolicyrepo.save(slapolicy);
			
		}
		
		catch(Exception e)
		{
			SCAUtil sca = new SCAUtil();
			Logger log = LoggerFactory.getLogger(SlaService.class);
			
			log.error("**!!! UNABLE TO CREATE !!!**"+e);
			
			String msg = sca.getErrorMessage(e);
			
			return new ResponseEntity(new ApiResponse(false, "Unable to save SlaPolicy"+msg),HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(slapolicy,HttpStatus.CREATED);
	}



}
