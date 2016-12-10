package parkingstructure.regulations.car;

import parkingstructure.core.GovermentNormsForCar;
import parkingstructure.core.GovermentRegulationProcessing;
import parkingstructure.exception.VehicleNotFoundException;
import parkingstructure.vehicle.Car;

/**
 * government regulation for vehicle type car
 * @author kuankit
 *
 */
public interface CarGovermentRegulationProcessing extends GovermentRegulationProcessing<Car> {
	/**
	 * print the government regulation. takes input one of the GovermentNormsForCar
	 *  and the value for which details have to be printed.
	 * @param norms
	 * @param param
	 * @throws VehicleNotFoundException
	 */
	void query(GovermentNormsForCar norms,String param) throws VehicleNotFoundException;
}
