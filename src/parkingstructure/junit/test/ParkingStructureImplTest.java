package parkingstructure.junit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import parkingstructure.core.ParkingSpace;
import parkingstructure.core.Vehicle;
import parkingstructure.core.VehicleType;
import parkingstructure.exception.NoSuchParkingSpaceException;
import parkingstructure.parkinglot.ParkingStructureImpl;
import parkingstructure.parkingspace.CarParkingSpaceImpl;
import parkingstructure.vehicle.Car;

public class ParkingStructureImplTest {
	private ParkingStructureImpl myParkingLot;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUp() throws Exception {
		myParkingLot = new ParkingStructureImpl(4);
		Vehicle<Car> vehicle1 = new Car("KA-01-HH-1234","White");
		Vehicle<Car> vehicle2 = new Car("KA-01-HH-1235","White");
		Vehicle<Car> vehicle3 = new Car("KA-01-HH-1236","White");

		myParkingLot.park(vehicle1);
		myParkingLot.park(vehicle2);
		myParkingLot.park(vehicle3);
	}

	@Test
	public void testGetVehicleTypeParkingSapceMap_default() {
		ParkingSpace parkingSpace = myParkingLot.getVehicleTypeParkingSapce(VehicleType.CAR);
		assertEquals(CarParkingSpaceImpl.class,parkingSpace.getClass());
		assertNotNull(myParkingLot);
	}
	@Test
	public void testGetVehicleTypeParkingSapceMap_Truck() {
		ParkingSpace parkingSpace = myParkingLot.getVehicleTypeParkingSapce(VehicleType.TRUCK);
		assertNull(parkingSpace);
	}
	@Test
	public void testPark() throws NoSuchParkingSpaceException {
		Vehicle<Car> vehicle = new Car("KA-01-HH-1234","White");
		System.setOut(new PrintStream(outContent));
		myParkingLot.park(vehicle);
		assertEquals("Allocated slot number: 4", outContent.toString().trim());
	}
	@Test
	public void testPark_parkingFull() throws NoSuchParkingSpaceException {
		Vehicle<Car> vehicle = new Car("KA-01-HH-1234","White");
		myParkingLot.park(vehicle);
		
		Vehicle<Car> vehicle5 = new Car("KA-01-HH-1238","White");
		System.setOut(new PrintStream(outContent));
		myParkingLot.park(vehicle5);
		assertEquals("Sorry, parking lot is full", outContent.toString().trim());
	}
	@Test
	public void testUnPark() throws NoSuchParkingSpaceException {
		System.setOut(new PrintStream(outContent));
		myParkingLot.unPark(1);
		assertEquals("Slot number 1 is free", outContent.toString().trim());

	}
	@Test
	public void testUnPark_NotFound() throws NoSuchParkingSpaceException {
		System.setOut(new PrintStream(outContent));
		myParkingLot.unPark(7);
		assertEquals("Not found", outContent.toString().trim());
	}
	@Test
	public void testUnPark_NotFoundNegetive() throws NoSuchParkingSpaceException {
		System.setOut(new PrintStream(outContent));
		myParkingLot.unPark(-1);
		assertEquals("Not found", outContent.toString().trim());
	}

}
