package com.foucsr.ticketmanager.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Properties;

import javax.crypto.NoSuchPaddingException;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Provider;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foucsr.ticketmanager.mysql.database.model.EmailDetails;
import com.foucsr.ticketmanager.mysql.database.model.ReceiveEmail;
import com.foucsr.ticketmanager.mysql.database.repository.EmailDetailsRepository;
import com.foucsr.ticketmanager.mysql.database.repository.ReceiveEmailRepository;

@Service
public class ReceivedMail {
	
	@Autowired
	ReceiveEmailRepository receiveEmailRepository;

	public ReceiveEmail receiveEmail;
	
	public  void receiveEmail(ReceiveSubject receiveSubject) {
		
		
		try {
			
			try {
				receiveSubject = ReceiveSubject.getInstance(receiveEmailRepository);
				
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
					| InvalidKeySpecException e) {
				e.printStackTrace();
			}
			
			Properties prop = receiveSubject.getProps();
		
			Session emailSession = Session.getDefaultInstance(prop);
			
			emailSession.getProperties();
			
			String propvider = "imap";//Smtp
			
        	Provider props =emailSession.getProvider(propvider);

        	String host = receiveSubject.getHost();
			String username = receiveSubject.getUsername();
		    String password = receiveSubject.getPassword();
			
        	Store store = emailSession.getStore(props);
	                                      
			store.connect(host,username,password);
			
			Folder emailFolder = store.getFolder("INBOX");

			emailFolder.open(Folder.READ_WRITE);

			Message messages[] = emailFolder.getMessages();

			int i = ((messages.length) - 1);
			
			//System.out.println(" Length : " + i + "\n");

			if (i >= 0) {

				Message message = messages[i];

				
				System.out.println("---------------------------------" + "\n");
				System.out.println(" We Received the New Mail." + "\n");
				System.out.println(" Email Number " + (i + 1)+ "\n");
				System.out.println(" Subject : " + message.getSubject() + "\n");

				String sub = message.getSubject();
				String name = "M1299";

				boolean subject = sub.contains(name);

				if (subject == true) {

					System.out.println(" Subject Is Match: " + subject + "\n");

					int fromcount = message.getFrom().length;
					Address[] fromAddressList = new Address[fromcount];
					fromAddressList = message.getFrom();
					String fromAddress = "";

					for (int a = 0; a < fromcount; a++) {
						fromAddress = fromAddress + fromAddressList[a].toString() + ";";
					}
					
					System.out.println("From: " + fromAddress + "\n");

					MimeMultipart mime = (MimeMultipart) message.getContent();

					String textBody = getTextFromMimeMultipart(mime);

					System.out.println(" Body of the email : " + textBody );

					emailFolder.close(false);
					store.close();

				} else {
					System.out.println(" Subject Is Not Match: " + subject + "\n");
				}

			} else if (i == 0) {
				System.out.println(" Length is Not equal to Zero  : " + i + "\n");

			} else {
				System.out.println(" We Dont Recived Any New Mails" + "\n");
			}
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = html;
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}

	public  void receiveEmail(Properties prop) {
		System.out.println(" yes ");
		
	}
	
}
