package parkingstructure.util;

/** This class contains regex to match the parking command
 * @author kuankit
 *
 */
public class CommandMatcher {
	private CommandMatcher(){}
	public static final String CREATE_PARKING_LOT_REGEX ="^create_parking_lot\\s+[1-9]+[0-9]*\\s*"; 
	public static final String PARK_CAR_REGEX ="^park\\s+[A-Z]{2}-[0-9]{2}-[A-Z]{1,2}-[0-9]{3,4}\\s+[A-Z]{1}[a-z]+\\s*"; 
	public static final String UNPARK_CAR_REGEX ="^leave\\s+[1-9]+[0-9]*\\s*"; 
	public static final String STATUS_REGEX ="^status\\s*"; 
	public static final String REGISTRATION_NUMBER_FOR_CARS_WITH_COLOR_REGEX ="^registration_numbers_for_cars_with_colour\\s+[A-Z]{1}[a-z]+\\s*"; 
	public static final String SLOT_NUMBER_FOR_CARS_WITH_COLOR_REGEX ="^slot_numbers_for_cars_with_colour\\s+[A-Z]{1}[a-z]+\\s*"; 
	public static final String SLOT_NUMBER_FOR_REGISTRATION_NUMBER_REGEX ="^slot_number_for_registration_number\\s+[A-Z]{2}-[0-9]{2}-[A-Z]{2}-[0-9]{4}\\s*"; 
	public static final String SPACE ="\\s"; 

}
