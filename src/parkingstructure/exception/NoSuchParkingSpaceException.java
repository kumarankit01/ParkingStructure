/**
 * 
 */
package parkingstructure.exception;

/**
 * @author kuankit
 *
 */
public class NoSuchParkingSpaceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchParkingSpaceException(){
		super("No paking space present for this type of Vehicle");
	}
}
