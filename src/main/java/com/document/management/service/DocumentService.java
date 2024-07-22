package com.document.management.service;


public interface DocumentService {
	
	byte[] getDocument(int id);
	
	byte[] getXMLFile(int id);
	
	void SendEmailWithFile(int id);
}
