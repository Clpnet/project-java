package com.document.management.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.document.management.service.DocumentService;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin("*")
public class DocumentController {

	@Autowired
	private DocumentService _documentService;

	@GetMapping("/pdf")
	public ResponseEntity<byte[]> getPdf() {
		byte[] pdfContents = _documentService.generatePdf();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "document.pdf");

		return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);

	}

	@GetMapping("/facture")
	private ResponseEntity<InputStreamResource> getFacture() {

		byte[] bytes = _documentService.getDocument();

		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		InputStreamResource resource = new InputStreamResource(bis);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=template.pdf")
				.contentType(MediaType.APPLICATION_PDF).contentLength(bytes.length).body(resource);

	}

	@GetMapping("/xml")
	private ResponseEntity<byte[]> getXml() {

		byte[] xmlContent = _documentService.getXMLFile();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_XML);
		headers.setContentDispositionFormData("filename", "document.xml");

		return new ResponseEntity<>(xmlContent, headers, HttpStatus.OK);
	}

	@GetMapping("/lee")
	private String lee() {

		return "Eres lo mejor que me ha pasado este 2024, te quiero mucho Shadia hermosa <3";
	}

}
