package com.document.management.service.impl;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.document.management.entity.Client;
import com.document.management.service.ClientService;
import com.document.management.service.DocumentService;
import com.document.management.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private Configuration freemarkerConfig;
	
	@Autowired
	private ClientService _clientService;

	@Autowired
	private EmailService _emailService;
	
	

	@Override
	public byte[] getDocument(int id) {
		// TODO Auto-generated method stub
		
	
		Client client= _clientService.getList().stream().filter(e->e.id == id).findFirst().orElse(null);
		
		
		
		byte[] pdfBytes = null;
		try {
			// Obtiene la plantilla FreeMarker
			Template template = freemarkerConfig.getTemplate("plantilla-facturacion.ftl");
			System.err.println("Entro aqui");

			// Datos a insertar en la plantilla
			Map<String, Object> model = new HashMap<>();
			model.put("descripcion", "Este es un desarrollo PoC, realizado por CRILOP");
			model.put("name", client.name);
			model.put("lastname",client.lastname);
			model.put("debt", client.debt);
			model.put("paymentDate", client.paymentDate);


			// Si necesitas incluir una imagen de firma en Base64 (descomentar y ajustar el
			// path)
			// byte[] firmaBytes =
			// Files.readAllBytes(Paths.get("/path/to/your/signature.png"));
			// String firmaBase64 = Base64.getEncoder().encodeToString(firmaBytes);
			// model.put("firmaBase64", firmaBase64);

			// Escribe el contenido de la plantilla en un StringWriter
			StringWriter stringWriter = new StringWriter();
			template.process(model, stringWriter);
			String htmlContent = stringWriter.toString();

			// Convierte el contenido HTML a PDF
			ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
			ConverterProperties converterProperties = new ConverterProperties();

			HtmlConverter.convertToPdf(new ByteArrayInputStream(htmlContent.getBytes()), pdfOutputStream,
					converterProperties);



			// Convierte el OutputStream a un array de bytes
			pdfBytes = pdfOutputStream.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pdfBytes;
	}

	@Override
	public byte[] getXMLFile(int id) {
		// TODO Auto-generated method stub
		

		Client client= _clientService.getList().stream().filter(e->e.id == id).findFirst().orElse(null);
		
		String xmlContent = "";
		Map<String, String> data = new HashMap<>();
		data.put("name", client.name);
		data.put("lastname", client.lastname);
		data.put("debt", String.valueOf(client.debt));
		data.put("paymentDate", client.paymentDate);
		data.put("email", client.name +"@pichincha.pe");

		XmlMapper xmlMapper = new XmlMapper();

		try {
			xmlMapper.writeValueAsString(data);

			xmlContent = xmlMapper.writeValueAsString(data);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		byte[] xmlBytes = xmlContent.getBytes();

		return xmlBytes;
	}

	@Override
	public void SendEmailWithFile(int id) {
		// TODO Auto-generated method stub
		
		
		byte[]file = getDocument(id);
		
		_emailService.sendEmail("cristhianlp17@gmail.com","ESTA ES UNA POC DE ENVIO DE BYTES","DESARROLLOS DE PROYECTOS - CRILOP",file,"PDF-CRILOP.pdf");
		
	}
	
	

}
