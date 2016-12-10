/**
 * 
 */
package parkingstructure.core;

import parkingstructure.exception.NoSlotFreeException;
import parkingstructure.exception.VehicleNotFoundException;

/**
 * @author kuankit
 *
 */
public interface ParkingSpace<T> {
	
	/**
	 * post processing after parking
	 * @param slotNumber
	 * @param vehicle
	 * @return processing completed message
	 */
	String postParkProcessing(int slotNumber, Vehicle<T> vehicle);
	/** 
	 * 	post processing after unparking
	 * @param slotNumber
	 * @param vehicle
	 * @return
	 */
	String postUnParkProcessing(int slotNumber, Vehicle<T> vehicle);
	/**
	 * park the vehicle
	 * @param vehicle
	 * @return slot in which vehicle is parked
	 * @throws NoSlotFreeException
	 */
	ParkingSlot<T> parkVehicle(Vehicle<T> vehicle) throws NoSlotFreeException;;
	/**
	 * unpark vehicle
	 * @param slotNumber from which vehicle was removed
	 * @return
	 * @throws VehicleNotFoundException
	 */
	ParkingSlot<T> UnparkVehicle(int slotNumber) throws VehicleNotFoundException;
}
