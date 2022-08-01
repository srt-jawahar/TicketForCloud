package com.foucsr.ticketmanager.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class JobSchedule {

	@Autowired
   public ReceivedMail receivedMail;
	
	EmailSubject emailSubject;
	
	Logger log = LoggerFactory.getLogger(JobSchedule.class);
  
	@Scheduled(fixedRate = 10 * 10 * 1000)
	public void add2DBJob() {

		try {
			
			log.info("Scheduler Running on every 3 minutes " + "\n");
			
			receivedMail.receiveEmail(emailSubject);
			
			
		} catch (Exception e) {
			log.error("***************** Error while move data from Oracle to Mysql  *********************\n" + e);
		}
	}

}
