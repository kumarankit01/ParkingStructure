package parkingstructure.junit.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import parkingstructure.core.ParkingSlot;
import parkingstructure.exception.NoSlotFreeException;
import parkingstructure.exception.VehicleNotFoundException;
import parkingstructure.parkingspace.CarParkingSpaceImpl;
import parkingstructure.vehicle.Car;

public class CarParkingSpaceImplTest {
	CarParkingSpaceImpl carParkingSpaceImpl ;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	@Before
	public void setUp() throws Exception {
		carParkingSpaceImpl = new CarParkingSpaceImpl(3);
		Car car1 = new Car("KA­-01-­HH-­1234","White");
		Car car2 = new Car("KA­-01-­HH-­1235","White");
		carParkingSpaceImpl.parkVehicle(car1);
		carParkingSpaceImpl.parkVehicle(car2);	
	}

	@Test
	public void testPostParkProcessing() throws NoSlotFreeException {
		Car car3 = new Car("KA­-01-­HH-­1236","Black");
		ParkingSlot<Car> parkingSlot = carParkingSpaceImpl.parkVehicle(car3);
		assertEquals("postParkProcessing Completed", carParkingSpaceImpl.postParkProcessing(parkingSlot.getPosition(), car3));
	}

	@Test
	public void testPostUnParkProcessing() throws NoSlotFreeException {
		Car car3 = new Car("KA­-01-­HH-­1236","Black");
		ParkingSlot<Car> parkingSlot = carParkingSpaceImpl.parkVehicle(car3);
		assertEquals("postUnParkProcessing completed ", carParkingSpaceImpl.postUnParkProcessing(parkingSlot.getPosition(), car3));
	}

	@Test
	public void testParkVehicle() throws NoSlotFreeException {
		Car car1 = new Car("KA­-01-­HH-­1235","White");
		ParkingSlot<Car> parkingSlot = carParkingSpaceImpl.parkVehicle(car1);
		assertEquals(3, parkingSlot.getPosition());
		assertNotNull(parkingSlot.getParkedVehicle());
	}
	@Test(expected = NoSlotFreeException.class)
	public void testParkVehicle_NoSloFree() throws NoSlotFreeException {
		Car car3 = new Car("KA­-01-­HH-­1236","Black");
		carParkingSpaceImpl.parkVehicle(car3);
		Car car4 = new Car("KA­-01-­HH-­1237","Black");
		carParkingSpaceImpl.parkVehicle(car4);
	}

	@Test
	public void testUnparkVehicle() throws NoSlotFreeException, VehicleNotFoundException {

		ParkingSlot<Car> parkingSlot = carParkingSpaceImpl.UnparkVehicle(1);
		assertNull(parkingSlot.getParkedVehicle());
		assertEquals(1, parkingSlot.getPosition());

	}
	@Test(expected=VehicleNotFoundException.class)
	public void testUnparkVehicle_vehicleNotFound() throws NoSlotFreeException, VehicleNotFoundException {
		carParkingSpaceImpl.UnparkVehicle(3);

	}
	@Test
	public void testQueryGovtRegulation() {
		System.setOut(new PrintStream(outContent));
		carParkingSpaceImpl.getStatus();
		String expectedOutput = "SLOT NO.	REGISTRATION NUMBER		COLOR\n"+
								"1		KA­-01-­HH-­1234			White\n"+
								"2		KA­-01-­HH-­1235			White\n";
		assertEquals(expectedOutput, outContent.toString());
	}

}
