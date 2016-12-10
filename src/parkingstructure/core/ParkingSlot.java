package parkingstructure.core;

public interface ParkingSlot<T> {

	/**
	 * check if parking place is free or not
	 * @return true is slot is free else false
	 */
	boolean isAvailable();
	/**
	 * get parked vehicle in the slot
	 * @return vehicle in the slot or null if it is empty
	 */

	Vehicle<T> getParkedVehicle();

	/**
	 * set true if slot is free else false if vehicle is parked
	 * @param isAvailable
	 */
	void setAvailibility(boolean isAvailable);
	/**
	 * park vehicle
	 * @param vehicle
	 */
	void parkVehicle(Vehicle<T> vehicle);
	/**
	 * remove vehicle
	 */
	void unParkVehicle();

	/** get slot Number
	 * @return slot Number
	 */
	int getPosition();
}
