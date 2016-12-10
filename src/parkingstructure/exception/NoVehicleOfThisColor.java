package parkingstructure.exception;

public class NoVehicleOfThisColor extends Exception {
	private static final long serialVersionUID = 1L;

	public NoVehicleOfThisColor(){
		super("No vehicle present of this color");
	}
}
