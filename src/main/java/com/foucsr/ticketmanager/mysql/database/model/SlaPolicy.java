package com.foucsr.ticketmanager.mysql.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SLA_MASTER")
public class SlaPolicy {
	@Id
	@SequenceGenerator(name = "SLA_MASTER_SEQ", sequenceName = "SLA_MASTER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SLA_MASTER_SEQ")
	@Column(name = "SLA_ID")
	private Long sla_Id;

	@Column(name = "SLA_POLICY_NAME")
	private String slaPolicyName;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PRIORITY")
	private String priority;

	@Column(name = "RESPONSE_TIME")
	private Double responseTime;

	@Column(name = "RESOLVE_TIME")
	private Double resolveTime;

	@Column(name = "OPERATIONAL_HRS")
	private String operationalHrs;

	@Column(name = "ESCALATION_MAIL")
	private String escalationMail;

	

	

	public Long getSla_Id() {
		return sla_Id;
	}

	public void setSla_Id(Long sla_Id) {
		this.sla_Id = sla_Id;
	}

	public String getSlaPolicyName() {
		return slaPolicyName;
	}

	public void setSlaPolicyName(String slaPolicyName) {
		this.slaPolicyName = slaPolicyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Double getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Double responseTime) {
		this.responseTime = responseTime;
	}

	public Double getResolveTime() {
		return resolveTime;
	}

	public void setResolveTime(Double resolveTime) {
		this.resolveTime = resolveTime;
	}

	public String getOperationalHrs() {
		return operationalHrs;
	}

	public void setOperationalHrs(String operationalHrs) {
		this.operationalHrs = operationalHrs;
	}

	public String getEscalationMail() {
		return escalationMail;
	}

	public void setEscalationMail(String escalationMail) {
		this.escalationMail = escalationMail;
	}

	public SlaPolicy(Long sla_Id, String slaPolicyName, String description, String priority, Double responseTime,
			Double resolveTime, String operationalHrs, String escalationMail) {
		super();
		this.sla_Id = sla_Id;
		this.slaPolicyName = slaPolicyName;
		this.description = description;
		this.priority = priority;
		this.responseTime = responseTime;
		this.resolveTime = resolveTime;
		this.operationalHrs = operationalHrs;
		this.escalationMail = escalationMail;
	}

	public SlaPolicy() {
		super();
		
	}
	
}
	