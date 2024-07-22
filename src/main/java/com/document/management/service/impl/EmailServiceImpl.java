package com.document.management.service.impl;


import org.springframework.stereotype.Service;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;

import utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import com.document.management.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	private static final String API_KEY = "c3fc7f1d8287870c5638c4b0451e4f3e";
    private static final String API_SECRET ="5a72bdc3ade61d2d6a30be1507ab951b";
	
	@Override
	public void sendEmail(String to, String subject, String body, byte[] file,String nameFile) {
		  MailjetClient client = new MailjetClient(API_KEY, API_SECRET);
	        MailjetRequest request;
	        MailjetResponse response;

	        // Codificar el archivo en Base64
	        String base64File = Utils.encodeFileToBase64(file);

	        request = new MailjetRequest(Emailv31.resource)
	            .property(Emailv31.MESSAGES, new JSONArray()
	                .put(new JSONObject()
	                    .put(Emailv31.Message.FROM, new JSONObject()
	                        .put("Email", "cristhianlp17@gmail.com")  // Dirección verificada
	                        .put("Name", "Cristian López"))  // Nombre del remitente
	                    .put(Emailv31.Message.TO, new JSONArray()
	                        .put(new JSONObject()
	                            .put("Email", to)  // Dirección del destinatario
	                            .put("Name", "Developer Crilop")))  // Nombre del destinatario (opcional)
	                    .put(Emailv31.Message.SUBJECT, subject)  // Asunto del correo
	                    .put(Emailv31.Message.TEXTPART, body)  // Cuerpo del mensaje en texto plano
	                    .put(Emailv31.Message.HTMLPART, "<h3>" + body + "</h3>")  // Cuerpo del mensaje en HTML
	                    .put(Emailv31.Message.ATTACHMENTS, new JSONArray()
	                        .put(new JSONObject()
	                            .put("ContentType", "application/octet-stream")  // Tipo de contenido
	                            .put("Filename", nameFile)  // Nombre del archivo adjunto
	                            .put("Base64Content", base64File)))));  // Contenido del archivo en Base64

	        try {
	            response = client.post(request);
	            System.out.println("Status: " + response.getStatus());
	            System.out.println("Data: " + response.getData());

	            // Verificar si hay errores específicos en la respuesta
	            if (response.getStatus() != 200) {
	                System.out.println("Error: " + response.getStatus());
	                System.out.println("Error data: " + response.getData().toString());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	}

}
