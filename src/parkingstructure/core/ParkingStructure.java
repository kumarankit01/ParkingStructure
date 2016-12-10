/**
 * 
 */
package parkingstructure.core;

import parkingstructure.exception.NoSuchParkingSpaceException;

/**
 * @author kuankit
 *
 */
public interface ParkingStructure {

	/**
	 * park the vehicle in the first free slot
	 * @param vehicle
	 * @throws NoSuchParkingSpaceException if parking space for vehicle.type not available
	 */
	<T> void park(Vehicle<T> vehicle) throws NoSuchParkingSpaceException;
	/**
	 * unpark the vehicle
	 * @param slotNumber
	 * @throws NoSuchParkingSpaceException if vehicle in not parked in.
	 */
	<T> void unPark(int slotNumber) throws NoSuchParkingSpaceException;
}
