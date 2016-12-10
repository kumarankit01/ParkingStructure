package parkingstructure.junit.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)

@Suite.SuiteClasses({
	CarParkingSpaceImplTest.class,
	GovermentRegulationProcessingForCarTest.class,
	ParkingStructureImplTest.class,
	CarTest.class
})
public class ParkingStructureTestSuit {


}
