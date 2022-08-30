package com.foucsr.ticketmanager.mysql.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.foucsr.ticketmanager.model.audit.DateAudit;

@Entity
@Table (name="AGENT_DETAILS")
public class Agent extends DateAudit {
@Id
@SequenceGenerator(name = "AGENT_DETAIL_SEQ", sequenceName = "AGENT_DETAIL_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "AGENT_DETAIL_SEQ")
@Column(name="AGENTID")
private Long agent_id;

@Column(name="FIRSTNAME")
private String firstName ;

@Column(name="LASTNAME")
private String lastName;

@Column(name="TITLE")
private String title;

@Column(name="LANDNUMBER")
private Long landNumber;

@Column(name="MOBILENUMBER")
private Long mobileNumber;

@Column(name="EMAIL")
private String email;


public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Long getAgent_id() {
	return agent_id;
}

public void setAgent_id(Long agent_id) {
	this.agent_id = agent_id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public Long getLandNumber() {
	return landNumber;
}

public void setLandNumber(Long landNumber) {
	this.landNumber = landNumber;
}

public Long getMobileNumber() {
	return mobileNumber;
}

public void setMobileNumber(Long mobileNumber) {
	this.mobileNumber = mobileNumber;
}

public Agent(Long agent_id, String firstName, String lastName, String title, Long landNumber, Long mobileNumber,String email) {
	super();
	this.agent_id = agent_id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.title = title;
	this.landNumber = landNumber;
	this.mobileNumber = mobileNumber;
	this.email = email;
}

public Agent() {
	super();
	// TODO Auto-generated constructor stub
}





}
