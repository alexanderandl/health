package de.andl.health.recordhandler;

import java.util.HashMap;
import java.util.TreeMap;

public class AccumulatedValueHandler implements RecordHandler {

	class EB {
		double wert;
		long count;
	}
	
	private final String titel;
	
	public AccumulatedValueHandler(String titel) {
		this.titel = titel;
	}
		
	private HashMap<String, EB> map = new HashMap<>();
	@Override
	public TreeMap<String,String> finish() {
		final TreeMap<String,String> treeMap = new TreeMap<>();		
		map.forEach((x,y) -> treeMap.put(x, ""+(long)y.wert));
		return treeMap;
	}

	@Override
	public void next(String key, String date, String start, String wert) {
		double d = Double.parseDouble(wert);
		EB hrvh = map.get(date);
		if (hrvh == null) {
			hrvh = new EB();
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
