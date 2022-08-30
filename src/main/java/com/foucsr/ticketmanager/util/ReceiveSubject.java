package com.foucsr.ticketmanager.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Properties;

import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Component;

import com.foucsr.ticketmanager.exception.ResourceNotFoundException;
import com.foucsr.ticketmanager.mysql.database.model.ReceiveEmail;
import com.foucsr.ticketmanager.mysql.database.repository.ReceiveEmailRepository;

@Component
public class ReceiveSubject {

	private static ReceiveSubject emailTextObject;

	private static Properties props;

	private String emailSubject;

	private String emailText;

	private String emailFrom;

	private List<String> emailTo;

	private List<String> emailCC;

	private List<File> files;
	
	private static String username;
	
	private static String password;

	private static EncryptionUtil encryptionUtil;
	
	private static ReceiveEncryptionUtil receiveEncryptionUtil;
	
	private boolean isHTML;
	
	private String host ;
		
//	private static long userId;


	public ReceiveSubject() {
		
	}

	public static ReceiveSubject getInstance(ReceiveEmailRepository receiveemailRepository) throws InvalidKeyException, UnsupportedEncodingException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {

//		if (emailTextObject == null) {

			emailTextObject = new ReceiveSubject();

			ReceiveEmail receiveEmail = receiveemailRepository.findOneEmailDetails().orElseThrow(
					() -> new ResourceNotFoundException("IMAP details  does not exist!", "", ""));

			receiveEncryptionUtil = ReceiveEncryptionUtil.getInstance();

			receiveEncryptionUtil.decryptEmailDetails(receiveEmail);

			Properties prop = System.getProperties();
//			prop.put("mail.smtp.auth", "true");
			prop.put("mail.imap.auth", "false");
			prop.put("mail.imap.port", receiveEmail.getPort());
			prop.put("mail.imap.host", receiveEmail.getImap_host());
			prop.put("mail.imap.starttls.enable", "true");
			prop.put("mail.debug", "true");
			
			// for ATA
			prop.put("mail.imap.ssl.trust", receiveEmail.getImap_host());
			
			emailTextObject.props = prop;
			
			emailTextObject.username = receiveEmail.getUsername();
			emailTextObject.password = receiveEmail.getPassword();
			emailTextObject.host = receiveEmail.getImap_host();

//		}

		return emailTextObject;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void init(String emailFrom, List<String> emailTo, List<String> emailCC, List<File> files,
			String emailSubject, String emailText) {

		this.emailFrom = emailFrom;
		this.emailCC = emailCC;
		this.emailTo = emailTo;
		this.files = files;
		this.emailSubject = emailSubject;
		this.emailText = emailText;
	}
	
	public static ReceiveSubject getEmailTextObject() {
		return emailTextObject;
	}

	public static void setEmailTextObject(ReceiveSubject emailTextObject) {
		ReceiveSubject.emailTextObject = emailTextObject;
	}

	public Properties getProps() {
		return props;
	}

	public static void setProps(Properties props) {
		ReceiveSubject.props = props;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailText() {
		return emailText;
	}

	public void setEmailText(String emailText) {
		this.emailText = emailText;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public List<String> getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(List<String> emailTo) {
		this.emailTo = emailTo;
	}

	public List<String> getEmailCC() {
		return emailCC;
	}

	public void setEmailCC(List<String> emailCC) {
		this.emailCC = emailCC;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFds(List<File> files) {
		this.files = files;
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
	
	public boolean isHTML() {
		return isHTML;
	}

	public void setHTML(boolean isHTML) {
		this.isHTML = isHTML;
	}
	
//	public long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(long userId) {
//		this.userId = userId;
//	}
	

//	@Override
//	public String toString() {
//		return "EmailSubject [emailSubject=" + emailSubject + ", emailText=" + emailText + ", emailFrom=" + emailFrom
//				+ ", emailTo=" + emailTo + ", emailCC=" + emailCC + ", fds=" + files + ", isHTML=" + isHTML + "]";
//	}
	
	@Override
	public String toString() {
		return "ReceiveSubject [emailSubject=" + emailSubject + ", emailText=" + emailText + ", emailFrom=" + emailFrom
				+ ", emailTo=" + emailTo + ", emailCC=" + emailCC + ", files=" + files + ", isHTML=" + isHTML
				+ ", host=" + host + "]";
	}
	
	

}
