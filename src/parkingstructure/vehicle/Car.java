/**
 * 
 */
package parkingstructure.vehicle;

import java.util.HashMap;
import java.util.Map;

import parkingstructure.core.Vehicle;
import parkingstructure.core.VehicleAttribute;
import parkingstructure.core.VehicleType;

/** This class represent real world car
 * @author kuankit
 *
 */
public class Car implements Vehicle<Car>{
	private final VehicleType TYPE = VehicleType.CAR;
	private Map<VehicleAttribute, String>  carAttributes = new HashMap<>();
	public Car(String registrationNumber){
		carAttributes.put(VehicleAttribute.REGISTRATION_NUMBER, registrationNumber);
	}
	public Car(String registrationNumber,String color){
		carAttributes.put(VehicleAttribute.REGISTRATION_NUMBER, registrationNumber);
		carAttributes.put(VehicleAttribute.COLOR, color);
	}

	@Override
	public Map<VehicleAttribute, String> getAttributes() {
		return carAttributes;
	}
	@Override
	public VehicleType getType() {
		return this.TYPE;
	}

}
