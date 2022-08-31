package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
		System.out.println("Email Server running on port number 8181...");
	}

}

		/**
		 * 	Dependencies: web, devtools.
		 *  Run as: Java Application
		 *  
		 *  Follow The steps:
		 * 
		 * 	Step 1: Adding the spring-boot-starter-mail dependency in pom.xml.
		 *  Step 2: Setting up Application.properties file with configurations required for using Gmail SMTP server.
		 *  Step 3: Creating EmailDetails class that contains fields such as recipient, msgBody, subject, and attachment.
		 *  Step 4: Creating interface EmailService and implementing class EmailServiceImpl of service layer.
		 *  		The EmailService interface defines two methods:
		 *  				A. String sendSimpleMail(EmailDetails details): This method can be used to send a simple 
		 *                     text email to the desired recipient.
		 *                  B. String sendMailWithAttachment(EmailDetails details): This method can be used to send 
		 *                     an email along with an attachment to the desired recipient.
		 *  Step 5: Creating a Rest Controller EmailController which defines various API for sending email.
		 *  Step 6: Running the Spring Boot Application and hitting http://localhost:8181/sendMail to send a simple email.
		 * 			JSON:
		 * 				{
  							"recipient" : "sankhamondal400@gmail.com",
  							"subject" : "Simple Email Message",
  							"msgBody" : "Hey! \n\nThis is a Simple Email \n\nThanks"
						}
		 *  Step 7: Running the Spring Boot Application and hitting http://localhost:8181/sendMailWithAttachment to send 
		 *  		an email along with an attachment.
		 *  		JSON:
		 * 				{
  							"recipient" : "sankhamondal400@gmail.com",
  							"subject" : "Simple Email Message",
  							"msgBody" : "Hey! \n\nThis is a Simple Email \n\nThanks",
  							"attachment" : "F:\AoT\Pic-Sign"
						}
		 *  
		 *  
		 */
