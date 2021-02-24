package de.andl.health;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

public class Health {

	private static final String INPUT = "C:\\Users\\alexa\\OneDrive\\Desktop\\healt\\Export.xml";

	public static void main(String[] args) throws XMLStreamException, ParserConfigurationException, SAXException, IOException {
		
		new HealthKonverter().convert(INPUT, INPUT + ".csv");

	}

}
