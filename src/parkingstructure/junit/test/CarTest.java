package parkingstructure.junit.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import parkingstructure.core.VehicleAttribute;
import parkingstructure.core.VehicleType;
import parkingstructure.vehicle.Car;


public class CarTest {
	Car car;
	@Before
	public void setUp() throws Exception {
		car = new Car("KA-01-HH-1234", "White");
	}

	@Test
	public void testGetAttributes() {
		Map<VehicleAttribute, String> attributes = car.getAttributes();
		assertEquals("KA-01-HH-1234", attributes.get(VehicleAttribute.REGISTRATION_NUMBER));
		assertEquals("White", attributes.get(VehicleAttribute.COLOR));
	}
	@Test
	public void testGetType() {

		assertEquals(VehicleType.CAR, car.getType());
	}
}
