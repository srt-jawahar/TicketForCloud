package com.foucsr.ticketmanager.mysql.database.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foucsr.ticketmanager.mysql.database.model.SlaPolicy;
import com.foucsr.ticketmanager.mysql.database.service.SlaService;


@RestController
@RequestMapping("/slapolicy")
public class SlaController {
	@Autowired
	private SlaService slaservice;
	
	// CREATE OR UPDATE
		@PostMapping("/createorupdate")
		public ResponseEntity<?> createOrUpdateSlaPolicy(@RequestBody SlaPolicy slapolicy)
		{
			
			ResponseEntity<?> createOrUpdate = slaservice.createOrUpdateSlaPolicy(slapolicy);
			return createOrUpdate;
		}
	
	

	

	
	}
