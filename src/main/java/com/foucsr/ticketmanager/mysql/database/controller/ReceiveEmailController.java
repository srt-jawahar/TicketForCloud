package com.foucsr.ticketmanager.mysql.database.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foucsr.ticketmanager.mysql.database.model.ReceiveEmail;
import com.foucsr.ticketmanager.mysql.database.service.MapValidationErrorService;
import com.foucsr.ticketmanager.mysql.database.service.ReceiveEmailService;

@RestController
@RequestMapping("/ReceiveEmailDetails/Service")
@CrossOrigin
public class ReceiveEmailController {

	@Autowired
	private ReceiveEmailService receiveEmailService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("/CreateOrUpdate")
	public ResponseEntity<?> createOrUpdateEmailDetails(@Valid @RequestBody ReceiveEmail receiveEmail,
			BindingResult result, Principal principal) {

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null)
			return errorMap;

		ReceiveEmail receiveEmailRes = receiveEmailService.saveOrUpdateEmailDetails(receiveEmail);

		return new ResponseEntity<ReceiveEmail>(receiveEmailRes, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ReceiveEmail getAll(Principal principal) {
		return receiveEmailService.findEmailDetails(principal.getName());
	}

}
