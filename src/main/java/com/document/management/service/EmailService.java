package com.document.management.service;

public interface EmailService {

	
	void sendEmail(String to, String subject, String body, byte[]file,String nameFile);
}
