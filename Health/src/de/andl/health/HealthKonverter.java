package de.andl.health;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

import de.andl.health.recordhandler.RecordHandler;

public class HealthKonverter {

	private static final String VON = "2020-09-15";

	public void convert(String filename, String ausgabe)
			throws XMLStreamException, ParserConfigurationException, SAXException, IOException {

		HandlerRegistry reg = new HandlerRegistry();

		lesen(filename, reg);
		ausgeben(reg, ausgabe);

	}

	private void lesen(String filename, HandlerRegistry reg)
			throws ParserConfigurationException, SAXException, IOException {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		HealthContantHandler handler = new HealthContantHandler(VON, reg);
		parser.parse(new File(filename), handler);
		handler.printStat();
	}

	private void ausgeben(HandlerRegistry reg, String ausgabe) throws IOException {
		ArrayList<String> datumlist = getDatumList();
		ArrayList<TreeMap<String, String>> list = new ArrayList<>();

		Collection<RecordHandler> handlerList = reg.getHandlerList();

		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(ausgabe));

		write(fileWriter, "Datum;");
		handlerList.forEach(x -> write(fileWriter, x.getTitle() + ";"));
		write(fileWriter, "\n");

		reg.getHandlerList().forEach(x -> list.add(x.finish()));

		datumlist.forEach(d -> {

			write(fileWriter,d + ";");
			list.forEach(x -> {
				if (x != null) {
					String string = x.get(d);
					if (string != null)
						write(fileWriter,string);
				}
				write(fileWriter,";");
			});
			write(fileWriter,"\n");
		});

		fileWriter.close();
	}

	private void write(BufferedWriter fileWriter, String string) {
		try {
			fileWriter.write(string);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private ArrayList<String> getDatumList() {
		String[] split = VON.split("-");
		GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1,
				Integer.parseInt(split[2]));

		ArrayList<String> datumlist = new ArrayList<String>();
		Date today = new Date();
		for (int i = 0; i < 356; i++) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			if (calendar.getTime().before(today)) {
				String string = calendar.get(Calendar.YEAR) + "-" + fill(calendar.get(Calendar.MONTH) + 1) + "-"
						+ fill(calendar.get(Calendar.DAY_OF_MONTH));
				datumlist.add(string);
			}
		}
		return datumlist;
	}

	private String fill(int i) {
		if (i < 10)
			return "0" + i;
		return "" + i;
	}

}
