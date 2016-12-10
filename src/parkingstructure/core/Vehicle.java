/**
 * 
 */
package parkingstructure.core;

import java.util.Map;

/**
 * @author kuankit
 *
 */
public interface Vehicle<T> {
	/** 
	 * @return map which contains all the vehicle attributes
	 */
	Map<VehicleAttribute, String> getAttributes();
	/**
	 * get the type of vehicle.
	 * 
	 * @return type of vehicle
	 */
	VehicleType getType();
	
}
