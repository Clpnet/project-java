package com.document.management.service.impl;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.document.management.service.DocumentService;
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

	@Override
	public byte[] generatePdf() {
		// TODO Auto-generated method stub
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		PdfWriter writer = new PdfWriter(byteArrayOutputStream);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);

		document.add(new Paragraph(
				"En el Jardín de Tus Ojos\r\n"
				+ "\r\n"
				+ "En el jardín de tus ojos, encuentro el sol,\r\n"
				+ "donde cada rayo ilumina mi ser,\r\n"
				+ "en tus palabras hallo un consuelo,\r\n"
				+ "que en la noche oscura me hace renacer.\r\n"
				+ "\r\n"
				+ "Tus sonrisas, estrellas en mi cielo,\r\n"
				+ "dibujan caminos de esperanza y paz,\r\n"
				+ "en cada gesto tuyo encuentro la magia\r\n"
				+ "que mi corazón ansía y siempre soñará.\r\n"
				+ "\r\n"
				+ "Tus abrazos son refugios de ternura,\r\n"
				+ "en el bullicio del mundo, un dulce rincón,\r\n"
				+ "y en el eco de tu risa descubro\r\n"
				+ "una melodía que en mi alma se quedó.\r\n"
				+ "\r\n"
				+ "Eres el sueño que despierta mi vida,\r\n"
				+ "el faro que guía mi barco errante,\r\n"
				+ "en el universo de tus sueños me pierdo,\r\n"
				+ "y en tu amor, siempre seré amante."
				+ ""
				+ "\nEste poema es para ti, Shadia"));

		document.close();
		return byteArrayOutputStream.toByteArray();

	}

	@Override
	public byte[] getDocument() {
		// TODO Auto-generated method stub
		byte[] pdfBytes = null;
		try {
			// Obtiene la plantilla FreeMarker
			Template template = freemarkerConfig.getTemplate("plantilla-facturacion.ftl");
			System.err.println("Entro aqui");

			// Datos a insertar en la plantilla
			Map<String, Object> model = new HashMap<>();
			model.put("descripcion", "Hola este es un valor de reemplazo desde backend");
			model.put("total", "6000000");
			model.put("param1", "dato1");
			model.put("param2", "dato2");
			model.put("param3", "dato3");
			model.put("param4", "dato4");
			model.put("param5", "dato5");

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
			System.err.println("Entro aqui 2");

			// Convierte el contenido HTML a PDF
			ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
			ConverterProperties converterProperties = new ConverterProperties();
			System.err.println("Entro aqui 3");

			HtmlConverter.convertToPdf(new ByteArrayInputStream(htmlContent.getBytes()), pdfOutputStream,
					converterProperties);

			System.err.println("Entro aqui 4");

			// Convierte el OutputStream a un array de bytes
			pdfBytes = pdfOutputStream.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pdfBytes;
	}

	@Override
	public byte[] getXMLFile() {
		// TODO Auto-generated method stub
		String xmlContent = "";
		Map<String, String> data = new HashMap<>();
		data.put("name", "Cristhian");
		data.put("email", "cristhian.lopez@pichincha.pe");

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

}
