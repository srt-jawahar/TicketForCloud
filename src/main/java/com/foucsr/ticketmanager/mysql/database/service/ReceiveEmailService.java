package com.foucsr.ticketmanager.mysql.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foucsr.ticketmanager.exception.AppException;
import com.foucsr.ticketmanager.exception.ResourceNotFoundException;
import com.foucsr.ticketmanager.mysql.database.model.ReceiveEmail;
import com.foucsr.ticketmanager.mysql.database.repository.ReceiveEmailRepository;
import com.foucsr.ticketmanager.util.EncryptionUtil;

@Service
public class ReceiveEmailService {

	@Autowired
	private ReceiveEmailRepository receiveEmailRepository;
	
	private EncryptionUtil encryptionUtil;

	public ReceiveEmail findEmailDetails(String username) {
		
		ReceiveEmail emailDetailsList = receiveEmailRepository.findOneEmailDetails().orElseThrow(
				() -> new ResourceNotFoundException("IMAP details  does not exist!", "", ""));
		
		try {
			
		encryptionUtil = EncryptionUtil.getInstance();
			
		encryptionUtil.decryptEmailDetails(emailDetailsList);
		
		} catch (Exception e) {
			throw new AppException("Unable to get Email details");
		}
		
		return emailDetailsList;
	}

	public ReceiveEmail saveOrUpdateEmailDetails(ReceiveEmail receiveEmail) {

		try {
			
			encryptionUtil = EncryptionUtil.getInstance();
			
			encryptionUtil.encryptEmailDetails(receiveEmail);
			
			receiveEmailRepository.save(receiveEmail);
			
			encryptionUtil.decryptEmailDetails(receiveEmail);
			
			return receiveEmail;

		} catch (Exception e) {
			throw new AppException("Unable to update Email details");
		}

	}

}
