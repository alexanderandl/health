package de.andl.health;

import java.util.HashSet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HealthContantHandler extends DefaultHandler {

	private long c;
	private HashSet<String> keys = new HashSet<>();	
	
	final private String von;
	final private HandlerRegistry reg;	

	public HealthContantHandler(String von,HandlerRegistry reg) {		
		this.von = von;
		this.reg = reg;
	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		if (!qName.startsWith("Record")) return;
		
		c++;
		
		keys.add(atts.getValue(0));
		
		String key = atts.getValue(0);
		reg.getHandler(key).ifPresent(x -> {
			String start = atts.getValue("startDate");
			String date = start.substring(0, 10);	
			if (date.compareTo(von) < 0) return;		
			String wert = atts.getValue("value");			
			x.next(key,date,start,wert);
			
//		    for ( int i = 0; i < atts.getLength(); i++ )
//		        System.out.printf( "Attribut no. %d: %s = %s%n", i,
//		                           atts.getQName( i ), atts.getValue( i ) );

		});
		

	}

	public void printStat() {
		System.out.println("Anzahl:" + c);
		keys.forEach(x->System.out.println(x));		
	}

}
