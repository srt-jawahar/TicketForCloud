package com.foucsr.ticketmanager.mysql.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "RECEIVE_EMAIL_DETAILS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "IMAP_HOST"
            })
    })
public class ReceiveEmail {
	
	@Id	
	@SequenceGenerator(name="RECEIVE_EMAIL_DETAILS_SEQ", sequenceName="RECEIVE_EMAIL_DETAILS_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RECEIVE_EMAIL_DETAILS_SEQ")	
	@Column(name = "ID")
	private Long id;
	
	@Column(name="IMAP_HOST")
	private String imap_host;	
	
	@Column(name="PORT")
	private long port;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImap_host() {
		return imap_host;
	}

	public void setImap_host(String imap_host) {
		this.imap_host = imap_host;
	}

	public long getPort() {
		return port;
	}

	public void setPort(long port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ReceiveEmail() {

	}

		
}	

