/**
 * 
 */
package parkingstructure.slot;

import parkingstructure.vehicle.Car;
import parkingstructure.core.ParkingSlot;
import parkingstructure.core.Vehicle;

/**This class represent the parking slot where the car will be parked
 * @author kuankit
 *
 */
public class CarParkingSlot implements ParkingSlot<Car> {
	private int position;
	private boolean isAvailabe = true;
	private Vehicle<Car> car = null;
	public CarParkingSlot(int position) {
		this.position = position;
	}
	@Override
	public boolean isAvailable() {
		return isAvailabe;
	}

	@Override
	public Vehicle<Car> getParkedVehicle() {
		return this.car;
	}

	@Override
	public void setAvailibility(boolean isAvailable) {
		this.isAvailabe = isAvailable;
	}

	@Override
	public void parkVehicle(Vehicle<Car> vehicle) {
		this.car = vehicle;

	}
	@Override
	public int getPosition() {
		return position;
	}
	@Override
	public void unParkVehicle() {
		this.car = null;		
	}


}
