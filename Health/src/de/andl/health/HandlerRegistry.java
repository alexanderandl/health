package de.andl.health;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.TreeMap;

import de.andl.health.recordhandler.AccumulatedValueHandler;
import de.andl.health.recordhandler.AvarageValueHandler;
import de.andl.health.recordhandler.BloodPressureHandler;
import de.andl.health.recordhandler.RecordHandler;

public class HandlerRegistry {

	private static final String HK_QUANTITY_TYPE_IDENTIFIER_RESTING_HEART_RATE = "HKQuantityTypeIdentifierRestingHeartRate";
	private static final String HK_QUANTITY_TYPE_IDENTIFIER_BODY_FAT_PERCENTAGE = "HKQuantityTypeIdentifierBodyFatPercentage";
	private static final String HK_QUANTITY_TYPE_IDENTIFIER_BLOOD_PRESSURE_DIASTOLIC = "HKQuantityTypeIdentifierBloodPressureDiastolic";
	private static final String HK_QUANTITY_TYPE_IDENTIFIER_BLOOD_PRESSURE_SYSTOLIC = "HKQuantityTypeIdentifierBloodPressureSystolic";
	private static final String HK_QUANTITY_TYPE_IDENTIFIER_ACTIVE_ENERGY_BURNED = "HKQuantityTypeIdentifierActiveEnergyBurned";
	private static final String HK_QUANTITY_TYPE_IDENTIFIER_BASAL_ENERGY_BURNED = "HKQuantityTypeIdentifierBasalEnergyBurned";
	private static final String HK_QUANTITY_TYPE_IDENTIFIER_HEART_RATE_VARIABILITY_SDNN = "HKQuantityTypeIdentifierHeartRateVariabilitySDNN";
	private static final String HK_QUANTITY_TYPE_IDENTIFIER_BODY_MASS = "HKQuantityTypeIdentifierBodyMass";
	
	private TreeMap<String, RecordHandler> handler = new TreeMap<>();
	{
		handler.put(HK_QUANTITY_TYPE_IDENTIFIER_BODY_MASS,new AvarageValueHandler("Gewicht"));		
		handler.put(HK_QUANTITY_TYPE_IDENTIFIER_BODY_FAT_PERCENTAGE,new AvarageValueHandler("Fett"));
		handler.put(HK_QUANTITY_TYPE_IDENTIFIER_HEART_RATE_VARIABILITY_SDNN, new AvarageValueHandler("HFV"));
		handler.put(HK_QUANTITY_TYPE_IDENTIFIER_RESTING_HEART_RATE, new AvarageValueHandler("Ruhep."));
		
		handler.put(HK_QUANTITY_TYPE_IDENTIFIER_BASAL_ENERGY_BURNED, new AccumulatedValueHandler("Basal"));
		handler.put(HK_QUANTITY_TYPE_IDENTIFIER_ACTIVE_ENERGY_BURNED, new AccumulatedValueHandler("Aktiv"));
		
		handler.put(HK_QUANTITY_TYPE_IDENTIFIER_BLOOD_PRESSURE_SYSTOLIC, new BloodPressureHandler("Sys"));
		handler.put(HK_QUANTITY_TYPE_IDENTIFIER_BLOOD_PRESSURE_DIASTOLIC, new BloodPressureHandler("Dia"));

	}
	
	public Optional<RecordHandler> getHandler(String key) {
		return Optional.ofNullable(handler.get(key));
	}
	
	public Collection<RecordHandler> getHandlerList() {
		ArrayList<RecordHandler> list = new ArrayList<RecordHandler>();
		list.add(handler.get(HK_QUANTITY_TYPE_IDENTIFIER_BODY_MASS));
		list.add(handler.get(HK_QUANTITY_TYPE_IDENTIFIER_BODY_FAT_PERCENTAGE));
		list.add(handler.get(HK_QUANTITY_TYPE_IDENTIFIER_HEART_RATE_VARIABILITY_SDNN));
		list.add(handler.get(HK_QUANTITY_TYPE_IDENTIFIER_RESTING_HEART_RATE));
		list.add(handler.get(HK_QUANTITY_TYPE_IDENTIFIER_BASAL_ENERGY_BURNED));
		list.add(handler.get(HK_QUANTITY_TYPE_IDENTIFIER_ACTIVE_ENERGY_BURNED));
		list.add(handler.get(HK_QUANTITY_TYPE_IDENTIFIER_BLOOD_PRESSURE_SYSTOLIC));
		list.add(handler.get(HK_QUANTITY_TYPE_IDENTIFIER_BLOOD_PRESSURE_DIASTOLIC));
		return list;
	}

	 
}
