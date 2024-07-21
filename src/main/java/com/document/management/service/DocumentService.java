package com.document.management.service;


public interface DocumentService {

	byte[] generatePdf();
	
	byte[] getDocument();
	
	byte[] getXMLFile();
}
