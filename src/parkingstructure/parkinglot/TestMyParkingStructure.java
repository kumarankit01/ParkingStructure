package parkingstructure.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import parkingstructure.core.GovermentNormsForCar;
import parkingstructure.core.VehicleType;
import parkingstructure.exception.NoSuchParkingSpaceException;
import parkingstructure.parkingspace.CarParkingSpaceImpl;
import parkingstructure.util.CommandMatcher;
import parkingstructure.vehicle.Car;

/** Main class. either add all the test inputs in the file and pass the name as run time args[]
 * if args[] is empty then it takes the input from console.
 * @author kuankit
 *
 */
public class TestMyParkingStructure {
	private static final String INVALID_VEHICLE_TYPE = "Sorry !! This vehicle cannot be parked here";
	private static final String INVALID_COMMAND = "Please check your command!!";
	private static ParkingStructureImpl parkingLot;
	private static CarParkingSpaceImpl carParkingSpace;
	
	public static void main(String[] args) {
		if(args.length!=0){
			File f = new File(args[0]);
			try(BufferedReader br = new BufferedReader(new FileReader(f))) {
				for(String line; (line = br.readLine()) != null; ) {
					executeCommand(line);
					System.out.println();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			Scanner s = new Scanner(System.in);
			try{
				while(s.hasNext()){
					String line = s.nextLine();
					if(line.trim().equals("exit")){
						s.close();
						System.out.println("Thank you! Have a good day!");
						break;
					}
					else if(line.trim().length()!=0)
						executeCommand(line);
					System.out.println();
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				s.close();
			}
		}
	}


	/** this method will scan the line and find the correct command
	 * and execute it. It uses regex in class CommandMatcher to match
	 * the command.
	 * @param line
	 */
	private static void executeCommand(String line) {
		// create parking space
		if(line.matches(CommandMatcher.CREATE_PARKING_LOT_REGEX)){
			String []lines = line.split(CommandMatcher.SPACE);
			int parkingSize = Integer.parseInt(lines[1].trim());
			parkingLot = new ParkingStructureImpl(parkingSize);
			carParkingSpace = (CarParkingSpaceImpl) parkingLot.getVehicleTypeParkingSapce(VehicleType.CAR);

		}
		//park vehicle
		else if(line.matches(CommandMatcher.PARK_CAR_REGEX)){
			String []lines = line.split(CommandMatcher.SPACE);
			Car vehicle = new Car(lines[1].trim(), lines[2].trim());
			try {
				parkingLot.park(vehicle);
			} catch (NoSuchParkingSpaceException e) {
				System.out.println(INVALID_VEHICLE_TYPE);
			}
		}
		//remove vehivle
		else if(line.matches(CommandMatcher.UNPARK_CAR_REGEX)){
			String []lines = line.split(CommandMatcher.SPACE);
			int slotNumber = Integer.parseInt(lines[1].trim());
			try {
				parkingLot.unPark(slotNumber);
			} catch (NoSuchParkingSpaceException e) {
				System.out.println(INVALID_VEHICLE_TYPE);
			}
		}
		// print status
		else if(line.matches(CommandMatcher.STATUS_REGEX)){
			carParkingSpace.getStatus();
		}
		// print all the registration number of car's having a given color
		else if (line.matches(CommandMatcher.REGISTRATION_NUMBER_FOR_CARS_WITH_COLOR_REGEX)) {
			String []lines = line.split(CommandMatcher.SPACE);
			carParkingSpace.queryGovtRegulation(GovermentNormsForCar.COLOR_TO_REGISTRATION_NUMBERS, lines[1].trim());
		}
		//print all the slot numbers in which car of a particular color is parked
		else if (line.matches(CommandMatcher.SLOT_NUMBER_FOR_CARS_WITH_COLOR_REGEX)) {
			String []lines = line.split(CommandMatcher.SPACE);
			carParkingSpace.queryGovtRegulation(GovermentNormsForCar.COLOR_TO_SLOT_NUMBERS, lines[1].trim());		
		}
		// print slot number 
		else if (line.matches(CommandMatcher.SLOT_NUMBER_FOR_REGISTRATION_NUMBER_REGEX)) {
			String []lines = line.split(CommandMatcher.SPACE);
			carParkingSpace.queryGovtRegulation(GovermentNormsForCar.REGISTRATION_NUMBER_TO_SLOT, lines[1].trim());
		}else{
			System.out.println(INVALID_COMMAND);
		}
	}
}
