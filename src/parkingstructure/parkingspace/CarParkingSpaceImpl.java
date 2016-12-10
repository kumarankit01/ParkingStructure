/**
 * 
 */
package parkingstructure.parkingspace;

import java.util.Map;

import parkingstructure.core.GovermentNormsForCar;
import parkingstructure.core.ParkingSlot;
import parkingstructure.core.ParkingSpace;
import parkingstructure.core.Vehicle;
import parkingstructure.core.VehicleAttribute;
import parkingstructure.exception.NoSlotFreeException;
import parkingstructure.exception.VehicleNotFoundException;
import parkingstructure.regulations.car.CarGovermentRegulationProcessingImpl;
import parkingstructure.slot.CarParkingSlot;
import parkingstructure.vehicle.Car;

/**
 * @author kuankit
 *
 */
public class CarParkingSpaceImpl implements ParkingSpace<Car> {
	private ParkingSlot<Car>[] slots;
	private CarGovermentRegulationProcessingImpl govermentRegulationProcessingForCar = new CarGovermentRegulationProcessingImpl();
	private boolean isSlotFull = false;

	public CarParkingSpaceImpl(int n) {
		slots = new CarParkingSlot[n];
		for(int i = 0; i < n; i++){
			slots[i] = new CarParkingSlot(i+1);
		}
	}

	/** Method to get the first free slot
	 * @return
	 * @throws NoSlotFreeException if no slot is free
	 */
	private ParkingSlot<Car> getFreeParkingSlotNumber() throws NoSlotFreeException {
		ParkingSlot<Car> slot = null;
		if(isSlotFull){
			throw new NoSlotFreeException();
		}else{
			for (int i = 0; i < slots.length; i++) {
				if(slots[i].isAvailable() == true){
					slot = slots[i];
					break;
				}
			}
		}
		if(slot == null){
			throw new NoSlotFreeException();
		}else{
			return slot;
		}
	}


	@Override
	public String postParkProcessing(int slotNumber, Vehicle<Car> vehicle) {
		return govermentRegulationProcessingForCar.postParkProcessing(slotNumber, vehicle);
	}

	@Override
	public String postUnParkProcessing(int slotNumber, Vehicle<Car> vehicle) {
		return govermentRegulationProcessingForCar.postUnParkProcessing(slotNumber, vehicle);
	}

	@Override
	public ParkingSlot<Car> parkVehicle(Vehicle<Car> vehicle) throws NoSlotFreeException {
		ParkingSlot<Car> slot = getFreeParkingSlotNumber();
		slot.setAvailibility(false);
		slot.parkVehicle(vehicle);
		postParkProcessing(slot.getPosition(), vehicle);
		return slot;
	}

	@Override
	public ParkingSlot<Car> UnparkVehicle(int slotNumber) throws VehicleNotFoundException {
		if(slotNumber < 1 || slotNumber > slots.length){
			throw new VehicleNotFoundException();
		}
		ParkingSlot<Car> slot = slots[slotNumber-1];
		if(slot == null || slot.getParkedVehicle() == null){
			throw new VehicleNotFoundException();
		}
		Car vehicle =  (Car) slot.getParkedVehicle();
		slot.setAvailibility(true);
		slot.unParkVehicle();
		postUnParkProcessing(slot.getPosition(), vehicle);
		isSlotFull = false;
		return slot;
	}

	/** Query to print the government regulation
	 * @param norms
	 * @param param
	 */
	public void queryGovtRegulation(GovermentNormsForCar norms,String param){
		try {
			govermentRegulationProcessingForCar.query(norms, param);
		} catch (VehicleNotFoundException e) {
			System.out.println("Not found");
		}
	}
	/**
	 * print the status of parking lot
	 */
	public void getStatus(){
		System.out.println("SLOT NO.	REGISTRATION NUMBER		COLOR");
		for (ParkingSlot<Car> parkingSlot : slots) {
			if(!parkingSlot.isAvailable()){
				Map<VehicleAttribute, String> CarAttributes = parkingSlot.getParkedVehicle().getAttributes();
				System.out.print(parkingSlot.getPosition()+"		"+CarAttributes.get(VehicleAttribute.REGISTRATION_NUMBER)
				+"			"+CarAttributes.get(VehicleAttribute.COLOR));
				System.out.println();
			}

		}
	}
}
