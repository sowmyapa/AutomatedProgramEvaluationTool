package com.mindtree.pa.util;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class XmlUtil {

	private Map<Integer, List<String>> validationErrorMessage;
	private final String schemaFile = "calci.xsd";
	private final String entityPackage = "com.mindtree.pa.util";
	private final String encoding = "UTF-8";

	public String generateProblemXml(ProblemType problemType) throws XmlException {
		try {
			JAXBContext context = JAXBContext.newInstance(entityPackage);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			StringWriter stringWriter = new StringWriter();
			marshaller.marshal(problemType, stringWriter);
			return stringWriter.toString();
		} catch (PropertyException e) {
			throw new XmlException(e);
		} catch (JAXBException e) {
			throw new XmlException(e);
		}
	}

	public ProblemType getProblemType(String xml) throws XmlException {
		try {
			// validation
			URL schemaUrl = getClass().getClassLoader().getResource(schemaFile);
			System.out.println(schemaUrl);
			if (schemaUrl == null) {
				System.err.println("XSD missing!");
				return null;
			}
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaUrl);
			JAXBContext jaxbContext = JAXBContext.newInstance(entityPackage);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			unmarshaller.setSchema(schema);
			unmarshaller.setEventHandler(new ValidationHandler());
			unmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes(encoding)));

			ProblemType problemType = (ProblemType) ((JAXBElement) unmarshaller.unmarshal(new StreamSource(
					new StringReader(xml)))).getValue();
			return problemType;
		} catch (UnsupportedEncodingException exception) {
			throw new XmlException(exception);
		} catch (SAXException exception) {
			throw new XmlException(exception);
		} catch (JAXBException exception) {
			throw new XmlException(exception);
		}
	}

	private class ValidationHandler implements ValidationEventHandler {
		public boolean handleEvent(final ValidationEvent validationEvent) {
			ValidationEventLocator validationEventLocator = validationEvent.getLocator();
			if ((validationEvent.getSeverity() == ValidationEvent.FATAL_ERROR)
					|| (validationEvent.getSeverity() == ValidationEvent.ERROR)
					|| (validationEvent.getSeverity() == ValidationEvent.WARNING)) {
				System.err.println("ValidationHandler:(handleEvent" + validationEvent.getMessage() + ") @ line "
						+ validationEventLocator.getLineNumber() + " column : "
						+ validationEventLocator.getColumnNumber());
				addMessage(ValidationEvent.WARNING, validationEvent);
			}
			return true;
		}

		private void addMessage(final int errorLevel, final ValidationEvent validationEvent) {
			List<String> message = null;
			message = validationErrorMessage.get(Integer.valueOf(errorLevel));
			if (message == null) {
				message = new ArrayList<String>();
			}
			message.add(validationEvent.getMessage());
			validationErrorMessage.put(Integer.valueOf(errorLevel), message);
		}
	}
}