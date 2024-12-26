package com.bornfire.services;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bornfire.entities.LeaveTable;
import com.bornfire.entities.LeaveTableRep;
import com.bornfire.entities.salary_parameter;
import com.bornfire.entities.salary_parameter_rep;

@Service
public class Mail {
	

	@Autowired
	static
	salary_parameter_rep salary_parameter_Rep;
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	static
	LeaveTableRep leaveTableRep;

	
	
	public static String sendmailss(String userId, String username,String email1,String email2,String email3,
			String email4,String email5,String usernamelogin,String password,String from,String host) {

        Properties properties = new Properties();
        
		System.out.println("email1 :"+email1);
		System.out.println("email1 :"+email2);
		System.out.println("email1 :"+email3);
		System.out.println("email1 :"+email4);
		try { String[] to = {email1, email2, email3, email4, email5};
        String subject = "Leave Request Notification";
        String body = "Dear Sir, " + username + " [" + userId + "] employee has applied for leave. Regards, " + from + " HR Department";

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", host); // Trust the server certificate
        properties.put("mail.debug", "true");  // Enable debugging

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usernamelogin, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));

        boolean hasValidRecipients = false;
        for (String recipient : to) {
        	System.out.print("The recipient is :"+recipient);
            if (recipient != null && !recipient.trim().isEmpty() && recipient.contains("@")) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                hasValidRecipients = true;
            }
        }

        if (!hasValidRecipients) {
            throw new MessagingException("No valid recipient addresses provided.");
        }

        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);

        System.out.println("Sent message successfully.");
			
		return "Sent message successfully....";
	 } catch (Exception e) {
         e.printStackTrace();
 		return "Sent message Unsuccessfull...";
     }
	}

	

	public static String sendapprovlstages(List<Map<String, String>> tableData) {
		
		  try {
	            for (Map<String, String> row : tableData) {
	                LeaveTable leaveTable = new LeaveTable();
	                for (int i = 1; i <= 5; i++) {
	                    String input = row.get("input-" + i);
	                    if (input != null) {
	                        leaveTable.setNo_row(new BigDecimal(i));
	                        leaveTable.setNumber_of_days(new BigDecimal(input));
	                        leaveTableRep.save(leaveTable);
	                    }
	                }
	            }
	            return "Data processed successfully.";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Failed to process data.";
	        }
		
	}

	
	
	
	
	
}