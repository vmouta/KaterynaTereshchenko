package utils;

import java.io.StringWriter;

import java.util.Map;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import play.data.validation.ValidationError;
import play.i18n.Messages;

public class XmlBuilder {
	
	
	public static String emptyNode(String node) {
		return "<"+node+"/>";
	}
	
	public static String convertToXML(String root, String message) {
		return "<"+root+">" + message + "</"+root+">";
	}
	
	public static String convertToXML(String root, Map<String, List<ValidationError>> errors) throws ParserConfigurationException, TransformerException {
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        Element rootElement = doc.createElement(root);
        doc.appendChild(rootElement);
        
        Iterator<String> it = errors.keySet().iterator();
    	while(it.hasNext()) {
    		String next = it.next();
    		ValidationError error = (ValidationError) errors.get(next);
    		Element errorElement = doc.createElement(error.key());
    		rootElement.appendChild(errorElement);
    		
    		Iterator<Object> args = error.arguments().iterator();
        	while(args.hasNext()) {
        		Object argument = args.next();
				try {
					JAXBContext jaxbContext = JAXBContext.newInstance(argument.getClass());
					Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
					jaxbMarshaller.marshal(argument,errorElement);
				} catch (JAXBException e) {
					AppLogger.errorLog("convertToXML", e);
				}
        	}
        	
        	Iterator<String> messages = error.messages().iterator();
        	while(messages.hasNext()) {
        		String message = messages.next();
        		Node messageNode = doc.createElement("message");
        		errorElement.appendChild(messageNode);
        		messageNode.setTextContent(Messages.get(message));
        	}
		}
    	
    	// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StringWriter outWriter = new StringWriter();
		StreamResult result = new StreamResult( outWriter );
    	transformer.transform(source, result);
		return outWriter.getBuffer().toString();
    }
	
	/**
	 * Use JAXBContext
	 */
	public static String convertToXML(Object object) throws JAXBException {
    	JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter outWriter = new StringWriter();
		StreamResult result = new StreamResult( outWriter );
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		jaxbMarshaller.marshal(object,result);
		return outWriter.getBuffer().toString();
    }
    
	/**
	 * Use JAXBContext
	 */
	public static String convertToXML(String rootNode, Iterable it) throws JAXBException {
    	StringBuffer buffer = new StringBuffer();
    	Iterator i = it.iterator();
    	if(i.hasNext()) {
    		
    		buffer.append("<"+rootNode+">");
    		buffer.append(convertToXML(i.next()));
    		while(i.hasNext()) {
    			buffer.append(convertToXML(i.next()));
    		}
    		buffer.append("</"+rootNode+">");
    	} else buffer.append(emptyNode(rootNode));
		return buffer.toString();
    }
	
}