package de.andl.health.recordhandler;

import java.util.HashMap;
import java.util.TreeMap;

public class BloodPressureHandler implements RecordHandler {
	
	private final String titel;
	class B {
		String mo = "";
		String mi = "";
		String ab = "";
	}
	
	private HashMap<String, B> map = new HashMap<>();
	
	public BloodPressureHandler(String titel) {
		this.titel = titel;
	}
	
	@Override
	public TreeMap<String, String> finish() {
		TreeMap<String, String> tm = new TreeMap<>();
		map.forEach((x,y)->tm.put(x, y.mo + ";" + y.mi + ";" + y.ab));		
		return tm;
	}

	@Override
	public void next(String key, String date, String start, String wert) {
		
		B b = map.get(date);
		if (b==null) {
			b=new B();
			map.put(date, b);
		}
		
		int i = Integer.parseInt(start.substring(11, 13));
		if (i<11) {
			b.mo = wert ;
		} else if (i<16) {
			b.mi = wert;
		} else {
			b.ab = wert;
		}
		
	}

	@Override
	public String getTitle() {		
		return titel + " mo;" + titel + "mi;" + titel + "ab" ;
	}

}
