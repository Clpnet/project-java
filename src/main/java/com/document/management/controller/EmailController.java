package com.document.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.document.management.service.EmailService;

@RestController
@RequestMapping("/api/email")
@CrossOrigin("*")
public class EmailController {


	@Autowired
	
	@GetMapping("/send")
	public void SendEmail() {
		

		//_emailService.sendEmail("cristhianlp17@gmail.com", "MENSAJE DE PRUEBA", "Hola este es el cuerpo del mensaje");
	}
	
}
