package co.edu.icesi.ci.tallerfinal.back.test.unit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import javax.persistence.PersistenceContext;

import co.edu.icesi.ci.tallerfinal.back.dao.CheckMeasurDao;
import co.edu.icesi.ci.tallerfinal.back.dao.MeasurementDao;
import co.edu.icesi.ci.tallerfinal.back.dao.PhysicalcheckupDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.ci.tallerfinal.back.exceptions.CheckMeasurException;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasurPK;
import co.edu.icesi.ci.tallerfinal.back.model.Measurement;
import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.back.service.CheckMeasurService;
import co.edu.icesi.ci.tallerfinal.back.service.CheckMeasurServiceImpl;

@ContextConfiguration(classes = {PersistenceContext.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CheckMeasurServiceTestDao {
	
	@InjectMocks
	private static CheckMeasurService checkMeasurService;
	
	@Mock
	private static CheckMeasurDao checkMeasurDao;
	@Mock
	private static MeasurementDao measurementDao;
	@Mock
	private static PhysicalcheckupDao physicalcheckupDao;
	
	private static Measurement measurement;
	private static Physicalcheckup physicalcheckup;
	private static CheckMeasur checkMeasurTest;  // object to test the edit methods
	
	private static long measurementId;
	private static long physicalcheckupId;
	private static CheckMeasurPK  checkMeasurTestId; 
	
	@BeforeAll
	public static void setup() {
		checkMeasurService = new CheckMeasurServiceImpl(checkMeasurDao,measurementDao,physicalcheckupDao);
		measurement = new Measurement();
		measurementId = measurement.getMeasId();
		
		physicalcheckup = new Physicalcheckup();
		physicalcheckupId = physicalcheckup.getPhycheId();
		
		checkMeasurTest = new CheckMeasur();
		checkMeasurTestId = checkMeasurTest.getId();
		checkMeasurTest.setMeasurement(measurement);
		checkMeasurTest.setPhysicalcheckup(physicalcheckup);
		checkMeasurTest.setMeasvalue(new BigDecimal("900000"));
	}
	@BeforeEach
	public void init() {
		when(measurementDao.findById(measurementId)).thenReturn(measurement); //the interaction is defined for when a get of Measurement is done in the edit methods
		
		when(physicalcheckupDao.findById(physicalcheckupId)).thenReturn(physicalcheckup);//the interaction is defined for when a get of Physicalcheckup is done in the edit methods 
		
		when(checkMeasurDao.findById(checkMeasurTestId)).thenReturn(checkMeasurTest);//the interaction is defined for when a get of Physicalcheckup is done in the edit methods 
		
	}        
	@Nested
	@DisplayName("Test of add CheckMeasur methods")
	class AddCheckMeasurMethods{
		
		@Test
		public void addCheckMeasurTest() { // design id = 41
			CheckMeasur checkMeasur = new CheckMeasur();
			checkMeasur.setMeasvalue(new BigDecimal("100000"));
			
			when(checkMeasurDao.save(checkMeasur)).thenReturn(checkMeasur);
			assertDoesNotThrow(()->checkMeasurService.addCheckMeasur(checkMeasur, measurementId, physicalcheckupId));
			
			verify(checkMeasurDao).save(checkMeasur);
			verifyNoMoreInteractions(checkMeasurDao);
		}
		@Test
		public void addNullCheckMeasurTest() { // design id = 42
			CheckMeasur checkMeasur = null;
			
			when(checkMeasurDao.save(checkMeasur)).thenReturn(checkMeasur);
			assertThrows(CheckMeasurException.class, 
					()-> checkMeasurService.addCheckMeasur(checkMeasur, measurementId, physicalcheckupId) 
			);
			verify(checkMeasurDao, times(0)).save(checkMeasur);
		}
		@Test
		public void addCheckMeasurWithouMeasurValueTest() {   // design id = 43
			CheckMeasur checkMeasur = new CheckMeasur(); 
			checkMeasur.setMeasvalue(null); // measure value cannot be null or empty
			
			when(checkMeasurDao.save(checkMeasur)).thenReturn(checkMeasur);
			assertThrows(CheckMeasurException.class, 
					()-> checkMeasurService.addCheckMeasur(checkMeasur, measurementId, physicalcheckupId)
			);
			verify(checkMeasurDao, times(0)).save(checkMeasur);
		}
		@Test
		public void addCheckMeasurWithouMeasurementTest() {  // design id = 44
			CheckMeasur checkMeasur = new CheckMeasur();
			checkMeasur.setMeasvalue(new BigDecimal("100000"));
			
			when(checkMeasurDao.save(checkMeasur)).thenReturn(checkMeasur);
			assertThrows(CheckMeasurException.class, 
					()-> checkMeasurService.addCheckMeasur(checkMeasur, measurementId-5, physicalcheckupId) // measurement with id measurementId-5 does not exist
			);
			verify(checkMeasurDao, times(0)).save(checkMeasur);
		}
		@Test
		public void addCheckMeasurWithouPhysicalcheckupTest() {  // design id = 45
			CheckMeasur checkMeasur = new CheckMeasur();
			checkMeasur.setMeasvalue(new BigDecimal("100000"));
			
			when(checkMeasurDao.save(checkMeasur)).thenReturn(checkMeasur);
			assertThrows(CheckMeasurException.class, 
					()-> checkMeasurService.addCheckMeasur(checkMeasur, measurementId, physicalcheckupId-5) // physicalcheckupId with id physicalcheckupId-5 does not exist
			);
			verify(checkMeasurDao, times(0)).save(checkMeasur);
		}
	}
	
	@Nested
	@DisplayName("Test of edit CheckMeasur methods")
	class EditCheckMeasurMethods{
		
		@Test
		public void editCheckMeasurTest() {  // design id = 46
			
			CheckMeasur checkMeasurToUpdate = checkMeasurService.getCheckMeasur(checkMeasurTestId);
			verify(checkMeasurDao).findById(checkMeasurTestId);
			
			Measurement measurement2 = new Measurement();
			when(measurementDao.findById(measurement2.getMeasId())).thenReturn(measurement2);
			
			Physicalcheckup physicalcheckup2 = new Physicalcheckup();
			when(physicalcheckupDao.findById(physicalcheckup2.getPhycheId())).thenReturn(physicalcheckup2);
			
			checkMeasurToUpdate.setMeasurement(measurement2);
			checkMeasurToUpdate.setPhysicalcheckup(physicalcheckup2);
			checkMeasurToUpdate.setMeasvalue(new BigDecimal("1000000"));
			
			when(checkMeasurDao.update(checkMeasurToUpdate)).thenReturn(checkMeasurToUpdate);
			assertDoesNotThrow( ()->checkMeasurService.editCheckMeasur(checkMeasurToUpdate));
			
			verify(checkMeasurDao).update(checkMeasurToUpdate);
			verify(physicalcheckupDao).findById(physicalcheckup2.getPhycheId());
			verify(measurementDao).findById(measurement2.getMeasId());
		}
		@Test
		public void editNullCheckMeasurTest() { // design id = 47
			
			CheckMeasur checkMeasurToUpdate = checkMeasurService.getCheckMeasur(checkMeasurTestId);
			verify(checkMeasurDao).findById(checkMeasurTestId);
			
			
			checkMeasurToUpdate =null;
			
			when(checkMeasurDao.update(checkMeasurToUpdate)).thenReturn(checkMeasurToUpdate);
			assertThrows(CheckMeasurException.class,
					()-> checkMeasurService.editCheckMeasur(null)
			);
			verify(checkMeasurDao, times(0)).update(checkMeasurToUpdate);
			
		}
		@Test
		public void editCheckMeasurWithoutMeasurValueTest() { // design id = 48
			
			CheckMeasur checkMeasurToUpdate = checkMeasurService.getCheckMeasur(checkMeasurTestId);
			verify(checkMeasurDao).findById(checkMeasurTestId);
			
			Measurement measurement2 = new Measurement();
			when(measurementDao.findById(measurement2.getMeasId())).thenReturn(measurement2);
			
			Physicalcheckup physicalcheckup2 = new Physicalcheckup();
			when(physicalcheckupDao.findById(physicalcheckup2.getPhycheId())).thenReturn(physicalcheckup2);
			
			checkMeasurToUpdate.setMeasurement(measurement2);
			checkMeasurToUpdate.setPhysicalcheckup(physicalcheckup2);
			checkMeasurToUpdate.setMeasvalue(null); // measure value cannot be null
			
			when(checkMeasurDao.update(checkMeasurToUpdate)).thenReturn(checkMeasurToUpdate);
			assertThrows(CheckMeasurException.class,
					()-> checkMeasurService.editCheckMeasur(checkMeasurToUpdate)
			);
			verify(checkMeasurDao, times(0)).update(checkMeasurToUpdate);
			
		}
		
		@Test
		public void editCheckMeasurWithoutMeasurementTest() { // design id = 49
			
			CheckMeasur checkMeasurToUpdate = checkMeasurService.getCheckMeasur(checkMeasurTestId);
			verify(checkMeasurDao).findById(checkMeasurTestId);
			
			Measurement measurement2 = null; //measurement cannot be null
			
			Physicalcheckup physicalcheckup2 = new Physicalcheckup();
			when(physicalcheckupDao.findById(physicalcheckup2.getPhycheId())).thenReturn(physicalcheckup2);
			
			checkMeasurToUpdate.setMeasurement(measurement2);
			checkMeasurToUpdate.setPhysicalcheckup(physicalcheckup2);
			checkMeasurToUpdate.setMeasvalue(new BigDecimal("1000000"));
			
			when(checkMeasurDao.update(checkMeasurToUpdate)).thenReturn(checkMeasurToUpdate);
			assertThrows(CheckMeasurException.class,
					()-> checkMeasurService.editCheckMeasur(checkMeasurToUpdate)
			);

			verify(checkMeasurDao, times(0)).update(checkMeasurToUpdate);
			
		}
		@Test 
		public void editCheckMeasurWithoutPhysicalcheckupTest() {  // design id = 50
			
			CheckMeasur checkMeasurToUpdate = checkMeasurService.getCheckMeasur(checkMeasurTestId);
			verify(checkMeasurDao).findById(checkMeasurTestId);
			
			Measurement measurement2 = new Measurement();
			when(measurementDao.findById(measurement2.getMeasId())).thenReturn(measurement2);
			
			Physicalcheckup physicalcheckup2 = null;
			
			checkMeasurToUpdate.setMeasurement(measurement2);
			checkMeasurToUpdate.setPhysicalcheckup(physicalcheckup2);
			checkMeasurToUpdate.setMeasvalue(new BigDecimal("1000000"));
			
			when(checkMeasurDao.update(checkMeasurToUpdate)).thenReturn(checkMeasurToUpdate);
			assertThrows(CheckMeasurException.class,
					()-> checkMeasurService.editCheckMeasur(checkMeasurToUpdate)
			);
			verify(measurementDao).findById(measurement2.getMeasId());
			verify(checkMeasurDao, times(0)).update(checkMeasurToUpdate);
			
		}
	}
}
