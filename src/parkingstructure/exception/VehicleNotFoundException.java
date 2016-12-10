/**
 * 
 */
package parkingstructure.exception;

/**
 * @author kuankit
 *
 */
public class VehicleNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VehicleNotFoundException(){
		super("Vehicle is not present in parking lot");
	}
}
