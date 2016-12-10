package parkingstructure.core;

public interface GovermentRegulationProcessing<T> {
	/**
	 * Update government regulation related data after vehicle is parked
	 * @param slotNumber
	 * @param vehicle
	 * @return
	 */
	String postParkProcessing(int slotNumber, Vehicle<T> vehicle);
	/**
	 * Update government regulation related data after vehicle is unparked
	 * @param slotNumber
	 * @param vehicle
	 * @return
	 */
	String postUnParkProcessing(int slotNumber, Vehicle<T> vehicle);
}
