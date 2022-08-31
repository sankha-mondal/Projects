// Java Program to Create Rest Controller that
// Defines various API for Sending Mail

package com.controller;

//Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.EmailDetails;
import com.service.EmailService;

//Annotation
@RestController
@RequestMapping("email")    //  http://localhost:8181/email/
@CrossOrigin			    // Enable cors policies 
//Class
public class EmailController {

	 @Autowired 
	 private EmailService emailService;

	 // Sending a simple Email
	 // http://localhost:8181/email/sendMail
	 @PostMapping("/sendMail")
	 public String sendMail(@RequestBody EmailDetails details) {
		 
	     String status = emailService.sendSimpleMail(details);
	
	     return status;
	 }

	 // Sending email with attachment
	 // http://localhost:8181/email/sendMailWithAttachment
	 @PostMapping("/sendMailWithAttachment")
	 public String sendMailWithAttachment(@RequestBody EmailDetails details) {
		 
	     String status = emailService.sendMailWithAttachment(details);
	
	     return status;
	 }
}
