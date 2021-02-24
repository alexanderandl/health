package de.andl.health.recordhandler;

import java.util.TreeMap;

public interface RecordHandler {
	
	class Record implements Comparable<Record> {
		public String datum;
		public String wert;
		
		public Record(String datum, String wert) {
			super();
			this.datum = datum;
			this.wert = wert;
		}


		@Override
		public int compareTo(Record o) {
			return datum.compareTo(o.datum);
		}
	}
	
	
	public TreeMap<String,String> finish();
	public String getTitle();
	public void next(String key, String date, String start, String wert);

}
