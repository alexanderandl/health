package de.andl.health.recordhandler;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.TreeMap;

public class AvarageValueHandler implements RecordHandler {

	class W {
		double wert;
		long count;
	}

	private HashMap<String, W> map = new HashMap<>();

	private final String titel;

	public AvarageValueHandler(String titel) {
		this.titel = titel;
	}

	@Override
	public TreeMap<String, String> finish() {

		final TreeMap<String, String> treeMap = new TreeMap<>();
		map.forEach((x, y) -> treeMap.put(x, "" + f(y.wert / y.count)));
		return treeMap;
	}

	private String f(double value) {
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
		((DecimalFormat) numberFormat).applyPattern("###.##");
		return numberFormat.format(value);
		
	}

	@Override
	public void next(String key, String date, String start, String wert) {

		double d = Double.parseDouble(wert);
		W hrvh = map.get(date);
		if (hrvh == null) {
			hrvh = new W();
			hrvh.count = 1;
			hrvh.wert = d;
			map.put(date, hrvh);
		} else {
			hrvh.wert += d;
			hrvh.count++;
		}

	}

	@Override
	public String getTitle() {
		return titel;
	}

}
