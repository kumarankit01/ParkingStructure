package parkingstructure.junit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import parkingstructure.core.GovermentNormsForCar;
import parkingstructure.exception.VehicleNotFoundException;
import parkingstructure.regulations.car.CarGovermentRegulationProcessingImpl;
import parkingstructure.vehicle.Car;

public class GovermentRegulationProcessingForCarTest {
	CarGovermentRegulationProcessingImpl govermentRegulationProcessingForCar;


	@Before
	public void setUp() throws Exception {
		govermentRegulationProcessingForCar = new CarGovermentRegulationProcessingImpl();
		Car car1 = new Car("KA­-01-­HH-­1234","White");
		Car car2 = new Car("KA­-01-­HH-­1235","White");
		Car car3 = new Car("KA­-01-­HH-­1236","Black");
		Car car4 = new Car("KA­-01-­HH-­1237","Black");
		Car car5 = new Car("KA­-01-­HH-­1238","Black");
		Car car6 = new Car("KA­-01-­HH-­1239","Blue");
		govermentRegulationProcessingForCar.postParkProcessing(0, car1);
		govermentRegulationProcessingForCar.postParkProcessing(1, car2);
		govermentRegulationProcessingForCar.postParkProcessing(2, car3);
		govermentRegulationProcessingForCar.postParkProcessing(3, car4);
		govermentRegulationProcessingForCar.postParkProcessing(4, car5);
		govermentRegulationProcessingForCar.postParkProcessing(5, car6);

	}

	@Test
	public void testGetGovtRegulationMap() {
		assertNotNull(govermentRegulationProcessingForCar.getGovtRegulationMap());
	}

	@Test
	public void testPostParkProcessing_colorToRegistrationNumer() {
		Map<GovermentNormsForCar, Map<String, List<String>>> map = govermentRegulationProcessingForCar.getGovtRegulationMap();
		Map<String, List<String>> colorToReg = map.get(GovermentNormsForCar.COLOR_TO_REGISTRATION_NUMBERS);
		List<String> regList = colorToReg.get("White");
		assertEquals(2, regList.size());

		List<String> list = new ArrayList<>();
		list.add("KA­-01-­HH-­1234");
		list.add("KA­-01-­HH-­1235");
		assertEquals(list, regList);

		regList = colorToReg.get("Black");
		assertEquals(3, regList.size());
		list = new ArrayList<>();
		list.add("KA­-01-­HH-­1236");
		list.add("KA­-01-­HH-­1237");
		list.add("KA­-01-­HH-­1238");

		assertEquals(list, regList);

	}
	@Test
	public void testPostParkProcessing_colorToSlotNumer() {
		Map<GovermentNormsForCar, Map<String, List<String>>> map = govermentRegulationProcessingForCar.getGovtRegulationMap();
		Map<String, List<String>> colorToSlot = map.get(GovermentNormsForCar.COLOR_TO_SLOT_NUMBERS);
		List<String> slotList = colorToSlot.get("White");
		assertEquals(2, slotList.size());
		List<String> list = new ArrayList<>();
		list.add("0");
		list.add("1");
		assertEquals(list, slotList);
	}
	@Test
	public void testPostParkProcessing_regNumberToSlotNumer() {
		Map<GovermentNormsForCar, Map<String, List<String>>> map = govermentRegulationProcessingForCar.getGovtRegulationMap();
		Map<String, List<String>> regToSlot = map.get(GovermentNormsForCar.REGISTRATION_NUMBER_TO_SLOT);
		List<String> regNumber = regToSlot.get("KA­-01-­HH-­1239");
		List<String> list = new ArrayList<>();
		list.add("5");
		assertEquals(list, regNumber);
	}

	@Test
	public void testPostUnParkProcessing_colorToRegistrationNumer() {
		Car car1 = new Car("KA­-01-­HH-­1234","White");
		Car car2 = new Car("KA­-01-­HH-­1333","pink");

		govermentRegulationProcessingForCar.postUnParkProcessing(0, car1);

		Map<GovermentNormsForCar, Map<String, List<String>>> map = govermentRegulationProcessingForCar.getGovtRegulationMap();
		Map<String, List<String>> colorToReg = map.get(GovermentNormsForCar.COLOR_TO_REGISTRATION_NUMBERS);
		List<String> regList = colorToReg.get("White");
		assertEquals(1, regList.size());

		List<String> list = new ArrayList<>();
		list.add("KA­-01-­HH-­1235");
		assertEquals(list, regList);

		regList = colorToReg.get("Black");
		assertEquals(3, regList.size());
		list = new ArrayList<>();
		list.add("KA­-01-­HH-­1236");
		list.add("KA­-01-­HH-­1237");
		list.add("KA­-01-­HH-­1238");

		assertEquals(list, regList);
		
		govermentRegulationProcessingForCar.postUnParkProcessing(0, car2);


	}
	@Test
	public void testPostUnParkProcessing_regNumberToSlotNumer(){
		Car car1 = new Car("KA­-01-­HH-­1239","Blue");
		govermentRegulationProcessingForCar.postUnParkProcessing(5, car1);

		Map<GovermentNormsForCar, Map<String, List<String>>> map = govermentRegulationProcessingForCar.getGovtRegulationMap();
		Map<String, List<String>> regToSlot = map.get(GovermentNormsForCar.REGISTRATION_NUMBER_TO_SLOT);
		List<String> regNumber = regToSlot.get("KA­-01-­HH-­1239");

		assertEquals(0, regNumber.size());

	}	
	@Test
	public void testPostUnParkProcessing_colorToSlotNumer() {

		Map<GovermentNormsForCar, Map<String, List<String>>> map = govermentRegulationProcessingForCar.getGovtRegulationMap();
		Map<String, List<String>> colorToSlot = map.get(GovermentNormsForCar.COLOR_TO_SLOT_NUMBERS);
		List<String> slotList = colorToSlot.get("White");
		List<String> list = new ArrayList<>();
		list.add("0");
		list.add("1");
		assertEquals(list, slotList);
		Car car1 = new Car("KA­-01-­HH-­1234","White");
		govermentRegulationProcessingForCar.postUnParkProcessing(0, car1);

		colorToSlot = map.get(GovermentNormsForCar.COLOR_TO_SLOT_NUMBERS);
		slotList = colorToSlot.get("White");
		list = new ArrayList<>();
		list.add("1");
		assertEquals(list, slotList);

	}
	@Test(expected = VehicleNotFoundException.class)
	public void testQuery_vehicleNotFound() throws VehicleNotFoundException {
		govermentRegulationProcessingForCar.query(GovermentNormsForCar.COLOR_TO_SLOT_NUMBERS, "Yellow");
	}

}
