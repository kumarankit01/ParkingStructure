package parkingstructure.regulations.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parkingstructure.core.GovermentNormsForCar;
import parkingstructure.core.Vehicle;
import parkingstructure.core.VehicleAttribute;
import parkingstructure.exception.VehicleNotFoundException;
import parkingstructure.vehicle.Car;

/**
 * This class maintain government related regulations
 * @author kuankit
 *
 */
public class CarGovermentRegulationProcessingImpl implements CarGovermentRegulationProcessing {
	private final Map<GovermentNormsForCar, Map<String, List<String>>> govtRegulationMap = new HashMap<>();

	public Map<GovermentNormsForCar, Map<String, List<String>>> getGovtRegulationMap() {
		return govtRegulationMap;
	}
	@Override
	public String postParkProcessing(int slotNumber, Vehicle<Car> vehicle) {
		Map<VehicleAttribute, String> temp  = vehicle.getAttributes();
		String color = temp.get(VehicleAttribute.COLOR);
		String registrationNumber = temp.get(VehicleAttribute.REGISTRATION_NUMBER);

		addVehicleDetail_colorToRegistrationNumbers(color, registrationNumber);
		addVehicleDetail_colorToSlotNumbers(slotNumber, color, registrationNumber);
		addVehicleDetail_registrationNumberToSlot(slotNumber, registrationNumber);

		return "postParkProcessing Completed";
	}
	@Override
	public String postUnParkProcessing(int slotNumber, Vehicle<Car> vehicle) {
		Map<VehicleAttribute, String> temp  = vehicle.getAttributes();
		String color = temp.get(VehicleAttribute.COLOR);
		String registrationNumber = temp.get(VehicleAttribute.REGISTRATION_NUMBER);

		removeVehicleDetail_colorToRegistrationNumbers( color, registrationNumber);
		removeVehicleDetail_colorToSlotNumbers( color, slotNumber);
		removeVehicleDetail_registrationNumberToSlot(registrationNumber , slotNumber);

		return "postUnParkProcessing completed ";
	}
	/** Must be called when vehicle is unparked to maintain detail of
	 * Slot numbers of all slots where a car of a particular colour is parked.
	 * 
	 * @param color
	 * @param slotNumber
	 */
	private void removeVehicleDetail_colorToSlotNumbers( String color,int slotNumber) {
		Map<String, List<String>> colorToSlotNumbersMap = govtRegulationMap.get(GovermentNormsForCar.COLOR_TO_SLOT_NUMBERS);
		List<String> slotList =	colorToSlotNumbersMap.get(color);
		if(slotList != null && slotList.size()>0)
			slotList.remove(String.valueOf(slotNumber));
	}
	/** Must be called when vehicle is parked to maintain detail of
	 * Slot number in which a car with a given registration number is parked.
	 * @param registrationNumber
	 * @param slotNumber
	 */
	private void removeVehicleDetail_registrationNumberToSlot(String registrationNumber,int slotNumber) {
		Map<String, List<String>> registrationNumberToSlotMap = govtRegulationMap.get(GovermentNormsForCar.REGISTRATION_NUMBER_TO_SLOT);
		List<String> slotList =	registrationNumberToSlotMap.get(registrationNumber);
		if(slotList != null && slotList.size()>0)
			slotList.remove(String.valueOf(slotNumber));
	}
	/**
	 * Must be called when vehicle is unparked to maintain detail of
	 * Registration numbers of all cars of a particular colour.
	 * @param color
	 * @param registrationNumber
	 */
	private void removeVehicleDetail_colorToRegistrationNumbers(String color,String registrationNumber) {
		Map<String, List<String>> registrationNumberToSlotMap = govtRegulationMap.get(GovermentNormsForCar.COLOR_TO_REGISTRATION_NUMBERS);
		List<String> slotList =	registrationNumberToSlotMap.get(color);
		if(slotList != null && slotList.size()>0)
			slotList.remove(String.valueOf(registrationNumber));
	}

	/**Must be called when vehicle is unparked to maintain detail of
	 * Slot number in which a car with a given registration number is parked.
	 * @param slotNumber
	 * @param registrationNumber
	 */
	private void addVehicleDetail_registrationNumberToSlot(int slotNumber, String registrationNumber) {
		Map<String, List<String>> registrationNumberToSlotMap = govtRegulationMap.get(GovermentNormsForCar.REGISTRATION_NUMBER_TO_SLOT);
		if(registrationNumberToSlotMap == null){
			registrationNumberToSlotMap = new HashMap<>();
			List<String> slotList = new ArrayList<>();
			slotList.add(String.valueOf(slotNumber));
			registrationNumberToSlotMap.put(registrationNumber, slotList);
			govtRegulationMap.put(GovermentNormsForCar.REGISTRATION_NUMBER_TO_SLOT,registrationNumberToSlotMap);
		}else{
			List<String> SlotList =	registrationNumberToSlotMap.get(registrationNumber);
			if(SlotList == null ){
				SlotList = new ArrayList<>();
			}
			SlotList.add(String.valueOf(slotNumber));
			registrationNumberToSlotMap.put(registrationNumber, SlotList);
			govtRegulationMap.put(GovermentNormsForCar.REGISTRATION_NUMBER_TO_SLOT,registrationNumberToSlotMap);

		}
	}

	/**
	 * Must be called when vehicle is parked to maintain detail of
	 * Slot numbers of all slots where a car of a particular colour is parked.
	 * 
	 * @param slotNumber
	 * @param color
	 * @param registrationNumber
	 */
	private void addVehicleDetail_colorToSlotNumbers(int slotNumber, String color, String registrationNumber) {
		Map<String, List<String>> colorToSlotNumbersMap = govtRegulationMap.get(GovermentNormsForCar.COLOR_TO_SLOT_NUMBERS);
		if(colorToSlotNumbersMap == null){
			colorToSlotNumbersMap = new HashMap<>();
			List<String> slotList = new ArrayList<>();
			slotList.add(String.valueOf(slotNumber));
			colorToSlotNumbersMap.put(color, slotList);
			govtRegulationMap.put(GovermentNormsForCar.COLOR_TO_SLOT_NUMBERS,colorToSlotNumbersMap);
		}else{
			List<String> slotList =	colorToSlotNumbersMap.get(color);
			if(slotList == null ){
				slotList = new ArrayList<>();
			}
			slotList.add(String.valueOf(slotNumber));
			colorToSlotNumbersMap.put(color, slotList);
			govtRegulationMap.put(GovermentNormsForCar.COLOR_TO_SLOT_NUMBERS,colorToSlotNumbersMap);

		}
	}

	/**
	 * Must be called when vehicle is parked to maintain detail of
	 * Registration numbers of all cars of a particular colour.
	 * @param color
	 * @param registrationNumber
	 */
	private void addVehicleDetail_colorToRegistrationNumbers(String color, String registrationNumber) {
		Map<String, List<String>> colorToRegistrationNumbersMap = govtRegulationMap.get(GovermentNormsForCar.COLOR_TO_REGISTRATION_NUMBERS);
		if(colorToRegistrationNumbersMap == null){
			colorToRegistrationNumbersMap = new HashMap<>();
			List<String> list = new ArrayList<>();
			list.add(registrationNumber);
			colorToRegistrationNumbersMap.put(color, list);
			govtRegulationMap.put(GovermentNormsForCar.COLOR_TO_REGISTRATION_NUMBERS,colorToRegistrationNumbersMap);
		}else{
			List<String> list =	colorToRegistrationNumbersMap.get(color);
			if(list == null ){
				list = new ArrayList<>();
			}
			list.add(registrationNumber);
			colorToRegistrationNumbersMap.put(color, list);
			govtRegulationMap.put(GovermentNormsForCar.COLOR_TO_REGISTRATION_NUMBERS,colorToRegistrationNumbersMap);

		}
	}
	@Override
	public void query(GovermentNormsForCar norms,String param) throws VehicleNotFoundException{
		Map<String, List<String>> map =  govtRegulationMap.get(norms);
		formatAndPrint(param, map);
	}
	/**utility method to format and print
	 * @param key
	 * @param map
	 * @throws VehicleNotFoundException
	 */
	private void formatAndPrint(String key, Map<String, List<String>> map) throws VehicleNotFoundException {
		if(map == null){
			throw new VehicleNotFoundException();
		}
		List<String> slotNumbers = map.get(key);
		if (slotNumbers == null || slotNumbers.size() == 0) {
			throw new VehicleNotFoundException();
		}
		for (int i = 0; i < slotNumbers.size()-1; i++) {
			System.out.print(slotNumbers.get(i)+" , ");

		}
		System.out.print(slotNumbers.get(slotNumbers.size()-1));
		System.out.println();
	}
}
