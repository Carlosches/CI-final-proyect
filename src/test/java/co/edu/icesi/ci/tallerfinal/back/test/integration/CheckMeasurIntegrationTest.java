package co.edu.icesi.ci.tallerfinal.back.test.integration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import co.edu.icesi.ci.tallerfinal.back.dao.CheckMeasurDao;
import co.edu.icesi.ci.tallerfinal.back.dao.MeasurementDao;
import co.edu.icesi.ci.tallerfinal.back.dao.PhysicalcheckupDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.ci.tallerfinal.back.exceptions.CheckMeasurException;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasurPK;
import co.edu.icesi.ci.tallerfinal.back.model.Measurement;
import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.back.service.CheckMeasurService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CheckMeasurIntegrationTest {
	
	private CheckMeasurService checkMeasurService;
	
	private CheckMeasurDao checkMeasurDao;
	private MeasurementDao measurementDao;
	private PhysicalcheckupDao physicalcheckupDao;

	private static Measurement measurement;
	private static Physicalcheckup physicalcheckup;
	private static CheckMeasur checkMeasurTest;  // object to test 
	
	@Autowired
	public CheckMeasurIntegrationTest(CheckMeasurService checkMeasurService,
			CheckMeasurDao checkMeasurDao, MeasurementDao measurementDao,
			PhysicalcheckupDao physicalcheckupDao) {
		this.checkMeasurService = checkMeasurService;
		this.checkMeasurDao = checkMeasurDao;
		this.measurementDao = measurementDao;
		this.physicalcheckupDao = physicalcheckupDao;
	}
	@BeforeAll
	public static void setup() {
		
		measurement = new Measurement();
		
		physicalcheckup = new Physicalcheckup();
		
		checkMeasurTest = new CheckMeasur();
		checkMeasurTest.setMeasurement(measurement);
		checkMeasurTest.setPhysicalcheckup(physicalcheckup);
		checkMeasurTest.setMeasvalue(new BigDecimal("900000"));
	}
	public void scenario() {
		measurementDao.save(measurement);
		physicalcheckupDao.save(physicalcheckup);
		CheckMeasurPK checkMeasurPK = new CheckMeasurPK();
		checkMeasurPK.setMeasMeasId(measurement.getMeasId());
		checkMeasurPK.setPhychePhycheId(physicalcheckup.getPhycheId());
		checkMeasurTest.setId(checkMeasurPK);
		checkMeasurDao.save(checkMeasurTest);
	}        
	@Nested
	@DisplayName("Test of add CheckMeasur methods")
	class AddCheckMeasurMethods{ 
		
		@Test
		public void addCheckMeasurTest() { // design id = 67
			scenario();
			CheckMeasur checkMeasur = new CheckMeasur();
			checkMeasur.setMeasvalue(new BigDecimal("100000"));
			
			Measurement measurement2 = new Measurement();
			Physicalcheckup phy = new Physicalcheckup();
			measurementDao.save(measurement2);
			physicalcheckupDao.save(phy);
			CheckMeasurPK checkMeasurPK = new CheckMeasurPK();
			checkMeasurPK.setMeasMeasId(measurement2.getMeasId());
			checkMeasurPK.setPhychePhycheId(phy.getPhycheId());
			checkMeasur.setId(checkMeasurPK);
			
			assertDoesNotThrow(()->checkMeasurService.addCheckMeasur(checkMeasur, measurement2.getMeasId(), phy.getPhycheId()));	
			assertNotNull(checkMeasurDao.findById(checkMeasur.getId()));  // verify that it has been saved in the database
		}
		
		@Test
		public void addCheckMeasurWithouMeasurementTest() { // design id = 68
			CheckMeasur checkMeasur = new CheckMeasur();
			checkMeasur.setMeasvalue(new BigDecimal("100000"));
			CheckMeasurPK checkMeasurPK = new CheckMeasurPK();
			checkMeasur.setId(checkMeasurPK);
			
			try {
				assertThrows(CheckMeasurException.class, ()-> checkMeasurService.addCheckMeasur(checkMeasur, measurement.getMeasId()-5, physicalcheckup.getPhycheId())); // measurement with id measurement.getMeasId()-5 does not exist
				assertNull(checkMeasurDao.findById(checkMeasur.getId()));  // verify that it has not been saved in the database
			}catch(NoSuchElementException e) {
				fail();  // measurement with id  measurement.getMeasId()-5 does not exist
			}
		}
		@Test
		public void addCheckMeasurWithouPhysicalcheckupTest() {// design id = 69
			CheckMeasur checkMeasur = new CheckMeasur();
			checkMeasur.setMeasvalue(new BigDecimal("100000"));
			CheckMeasurPK checkMeasurPK = new CheckMeasurPK();
			checkMeasur.setId(checkMeasurPK);
			try {
				assertThrows(CheckMeasurException.class, ()-> checkMeasurService.addCheckMeasur(checkMeasur, measurement.getMeasId(), physicalcheckup.getPhycheId()-5)); // physicalcheckup.getPhycheId() with id physicalcheckup.getPhycheId()-5 does not exist
				assertNull(checkMeasurDao.findById(checkMeasur.getId()));  // verify that it has not been saved in the database
			}catch(NoSuchElementException e) {
				fail();  //physicalcheckup.getPhycheId() with id physicalcheckup.getPhycheId()-5 does not exist
			}
			
		}
	}
		
	
	@Nested
	@DisplayName("Test of edit CheckMeasur methods")
	class EditCheckMeasurMethods{
		
		@Test
		public void editCheckMeasurTest() { // design id = 70
			
			CheckMeasur checkMeasurToUpdate = checkMeasurService.getCheckMeasur(checkMeasurTest.getId());
			Measurement measurement2 = new Measurement();
			measurementDao.save(measurement2);
			Physicalcheckup physicalcheckup2 = new Physicalcheckup();
			physicalcheckupDao.save(physicalcheckup2);
			
			checkMeasurToUpdate.setMeasurement(measurement2);
			checkMeasurToUpdate.setPhysicalcheckup(physicalcheckup2);
			checkMeasurToUpdate.setMeasvalue(new BigDecimal("1000000.00"));
			
			assertDoesNotThrow( ()->checkMeasurService.editCheckMeasur(checkMeasurToUpdate));
			
			CheckMeasur updatedCheckMeasur = checkMeasurDao.findById(checkMeasurTest.getId());
			assertEquals(updatedCheckMeasur.getMeasvalue(), checkMeasurToUpdate.getMeasvalue());   // verify that it has been updated in the database
			assertNotEquals(updatedCheckMeasur.getMeasurement().getMeasId(), checkMeasurToUpdate.getMeasurement().getMeasId());  
			assertNotEquals(updatedCheckMeasur.getPhysicalcheckup().getPhycheId(), checkMeasurToUpdate.getPhysicalcheckup().getPhycheId()); 
			// In the model the measurement and the physical check of the check measure have the annotation "udatable = false" so it is not possible to update them
		}
		
		@Test
		public void editCheckMeasurWithoutMeasurementTest() { // design id = 71
			
			CheckMeasur checkMeasurToUpdate = checkMeasurService.getCheckMeasur(checkMeasurTest.getId());
			
			Measurement measurement2 = new Measurement();
			try {
				checkMeasurToUpdate.setMeasurement(measurement2); //// the measurement has not been added to the database
				assertThrows(CheckMeasurException.class,()-> checkMeasurService.editCheckMeasur(checkMeasurToUpdate));
			}catch(NoSuchElementException e) {
				fail();  
			} 
			try {
				checkMeasurToUpdate.setMeasurement(null); // the measurement is nulll
				assertThrows(CheckMeasurException.class,()-> checkMeasurService.editCheckMeasur(checkMeasurToUpdate));
			}catch(NullPointerException e) {
				fail();  
			}

		}
		@Test
		public void editCheckMeasurWithoutPhysicalcheckupTest() { // design id = 72
			
			CheckMeasur checkMeasurToUpdate = checkMeasurService.getCheckMeasur(checkMeasurTest.getId());
			
			Physicalcheckup physicalcheckup2 = new Physicalcheckup();
			try {
				checkMeasurToUpdate.setPhysicalcheckup(physicalcheckup2); //// the physical checkup  has not been added to the database
				assertThrows(CheckMeasurException.class,()-> checkMeasurService.editCheckMeasur(checkMeasurToUpdate));
			}catch(NoSuchElementException e) {
				fail();  
			} 
			try {
				checkMeasurToUpdate.setPhysicalcheckup(null); // the measurement is nulll
				assertThrows(CheckMeasurException.class,()-> checkMeasurService.editCheckMeasur(checkMeasurToUpdate));
			}catch(NullPointerException e) {
				fail();  
			}
		}
	}
}
