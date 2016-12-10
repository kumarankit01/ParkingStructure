/**
 * 
 */
package parkingstructure.exception;

/**
 * @author kuankit
 *
 */
public class NoSlotFreeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSlotFreeException(){
		super("Slot is Full");
	}
}
