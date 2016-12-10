/**
 * 
 */
package parkingstructure.parkinglot;

import java.util.HashMap;
import java.util.Map;

import parkingstructure.core.ParkingSlot;
import parkingstructure.core.ParkingSpace;
import parkingstructure.core.ParkingStructure;
import parkingstructure.core.Vehicle;
import parkingstructure.core.VehicleType;
import parkingstructure.exception.NoSlotFreeException;
import parkingstructure.exception.NoSuchParkingSpaceException;
import parkingstructure.exception.VehicleNotFoundException;
import parkingstructure.parkingspace.CarParkingSpaceImpl;

/**
 * @author kuankit
 *
 */
public class ParkingStructureImpl implements ParkingStructure{
	private  final Map<VehicleType, ParkingSpace> vehicleTypeParkingSapceMap = new HashMap<>();
	
	public ParkingStructureImpl(int carParkingSpaceSize) {
		vehicleTypeParkingSapceMap.put(VehicleType.CAR, new CarParkingSpaceImpl(carParkingSpaceSize));
		System.out.println("Created a parking lot with "+carParkingSpaceSize+" slots");
	}
	public ParkingStructureImpl(VehicleType type , int parkingSpaceSize) {
		System.out.println("Created a parking lot with "+parkingSpaceSize+" slots");
	}
	public ParkingSpace getVehicleTypeParkingSapce(VehicleType v) {
		return vehicleTypeParkingSapceMap.get(v);
	}
	
	@Override
	public <T> void park(Vehicle<T> vehicle) throws NoSuchParkingSpaceException{
		ParkingSpace<T> parkingSpace = vehicleTypeParkingSapceMap.get(vehicle.getType());
		if(parkingSpace == null){
			throw new NoSuchParkingSpaceException();
		}
		ParkingSlot<T> slot;
		try {
			slot = parkingSpace.parkVehicle(vehicle);
			System.out.println("Allocated slot number: "+slot.getPosition());

		} catch (NoSlotFreeException e) {
			System.out.println("Sorry, parking lot is full");
		}
	}
	@Override
	public <T> void unPark(int slotNumber) throws NoSuchParkingSpaceException {
		ParkingSpace<T> parkingSpace = vehicleTypeParkingSapceMap.get(VehicleType.CAR);
		if(parkingSpace == null){
			throw new NoSuchParkingSpaceException();
		}
		ParkingSlot<T> slot;
		try {
			slot = parkingSpace.UnparkVehicle(slotNumber);
			System.out.println("Slot number "+ slot.getPosition()  +" is free");
		} catch (VehicleNotFoundException e) {
			System.out.println("Not found");
		}
	}
}
