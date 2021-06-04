package co.edu.icesi.ci.tallerfinal.back.test.unit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceContext;

import co.edu.icesi.ci.tallerfinal.back.dao.MeasurementDao;
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

import co.edu.icesi.ci.tallerfinal.back.exceptions.MeasurementException;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.Institution;
import co.edu.icesi.ci.tallerfinal.back.model.Measurement;
import co.edu.icesi.ci.tallerfinal.back.repositories.InstitutionRepository;
import co.edu.icesi.ci.tallerfinal.back.service.MeasurementService;
import co.edu.icesi.ci.tallerfinal.back.service.MeasurementServiceImpl;

@ContextConfiguration(classes = {PersistenceContext.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MeasuremetServiceTestDao {
	
	@InjectMocks
	private static MeasurementService measurementService;
	
	@Mock 
	private static MeasurementDao measurementDao;
	@Mock 
	private static InstitutionRepository institutionRepository;
	
	private static Institution institution;
	private static Measurement measurementTest; // object to test the edit methods
	

	private static long idInstitution;
	private static long idMeasurementTest;
	
	@BeforeAll
	public static void setup() {
		measurementService = new MeasurementServiceImpl(measurementDao, institutionRepository);
		institution = new Institution();
		idInstitution = institution.getInstId();

		measurementTest = new Measurement();
		idMeasurementTest = measurementTest.getMeasId();
		measurementTest.setMeasDescription("description:");
		measurementTest.setMeasMaxthreshold(new BigDecimal("1000000"));
		measurementTest.setMeasMinthreshold(new BigDecimal("100"));
		measurementTest.setMeasName("name");
		measurementTest.setMeasUnit("m");
		List<CheckMeasur> list = new ArrayList<>();
		CheckMeasur check = new CheckMeasur();
		list.add(check);
		measurementTest.setCheckMeasurs(list);
		
	}
	@BeforeEach
	public void init() {
		Optional<Institution> optionalInstitution = Optional.of(institution);
		when(institutionRepository.findById(idInstitution)).thenReturn(optionalInstitution);
		
		when(measurementDao.findById(idMeasurementTest)).thenReturn(measurementTest);
	}
	
	@Nested
	@DisplayName("Test of add Measurement methods")
	class AddMeasurementMethods{
		
		@Test
		public void addMeasurementTest() {  // design id = 23
	
			Measurement measurement = new Measurement();
			measurement.setMeasDescription("description:");
			measurement.setMeasMaxthreshold(new BigDecimal("1000000"));
			measurement.setMeasMinthreshold(new BigDecimal("100"));
			measurement.setMeasName("name");
			measurement.setMeasUnit("m");
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurement.setCheckMeasurs(list);
			when(measurementDao.save(measurement)).thenReturn(measurement);
			assertDoesNotThrow(()-> measurementService.addMeasurement(measurement,idInstitution));   // the assertDoesNotThrow is to check that no exceptions are thrown when everything is correct
	
			
			verify(measurementDao).save(measurement);
			verifyNoMoreInteractions(measurementDao);
		}
		@Test
		public void addNullMeasurementTest() {  // design id = 24
	
			Measurement measurement = null;
			when(measurementDao.save(measurement)).thenReturn(measurement);
			assertThrows(MeasurementException.class, ()-> measurementService.addMeasurement(measurement,idInstitution));  
	
			
			verify(measurementDao, times(0)).save(measurement); //verify that the save method was never called
		}
		@Test
		public void addMeasurementWhitoutDescriptionTest() {  // design id = 25
	
			Measurement measurement = new Measurement();
			measurement.setMeasMaxthreshold(new BigDecimal("1000000"));
			measurement.setMeasMinthreshold(new BigDecimal("100"));
			measurement.setMeasName("name");
			measurement.setMeasUnit("m");
			measurement.setMeasDescription(""); // cannot be empty
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurement.setCheckMeasurs(list);
			when(measurementDao.save(measurement)).thenReturn(measurement);
			assertThrows(MeasurementException.class, ()-> measurementService.addMeasurement(measurement,idInstitution));  
	
			
			verify(measurementDao, times(0)).save(measurement); //verify that the save method was never called
		}
		
		@Test
		public void addMeasurementWhitoutMaxthresholdTest() {  // design id = 26
	
			Measurement measurement = new Measurement();
			measurement.setMeasDescription("Description");
			measurement.setMeasMaxthreshold(null);  //cannot be null
			measurement.setMeasMinthreshold(new BigDecimal("100"));
			measurement.setMeasName("name");
			measurement.setMeasUnit("m");
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurement.setCheckMeasurs(list);
			when(measurementDao.save(measurement)).thenReturn(measurement);
			assertThrows(MeasurementException.class, ()-> measurementService.addMeasurement(measurement,idInstitution));  
	
			
			verify(measurementDao, times(0)).save(measurement); //verify that the save method was never called
		}
		
		@Test
		public void addMeasurementWhitoutMinthresholdTest() {  // design id = 27
	
			Measurement measurement = new Measurement();
			measurement.setMeasDescription("Description");
			measurement.setMeasMaxthreshold(new BigDecimal("1000000"));
			measurement.setMeasMinthreshold(null); //cannot be null
			measurement.setMeasName("name");
			measurement.setMeasUnit("m");
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurement.setCheckMeasurs(list);
			when(measurementDao.save(measurement)).thenReturn(measurement);
			assertThrows(MeasurementException.class, ()-> measurementService.addMeasurement(measurement,idInstitution));  
	
			
			verify(measurementDao, times(0)).save(measurement); //verify that the save method was never called
		}
		@Test
		public void addMeasurementWhitoutNameTest() {  // design id = 28
	
			Measurement measurement = new Measurement();
			measurement.setMeasDescription("Description");
			measurement.setMeasMaxthreshold(new BigDecimal("1000000"));
			measurement.setMeasMinthreshold(new BigDecimal("100"));
			measurement.setMeasName(""); //cannot be empty
			measurement.setMeasUnit("m");
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurement.setCheckMeasurs(list);
			when(measurementDao.save(measurement)).thenReturn(measurement);
			assertThrows(MeasurementException.class, ()-> measurementService.addMeasurement(measurement,idInstitution));  
	
			
			verify(measurementDao, times(0)).save(measurement); //verify that the save method was never called
		}
		@Test
		public void addMeasurementWhitoutUnitTest() {  // design id = 29
	
			Measurement measurement = new Measurement();
			measurement.setMeasDescription("Description");
			measurement.setMeasName("name");
			measurement.setMeasUnit(""); //cannot be empty
			measurement.setMeasMaxthreshold(new BigDecimal("1000000"));
			measurement.setMeasMinthreshold(new BigDecimal("100"));
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurement.setCheckMeasurs(list);
			when(measurementDao.save(measurement)).thenReturn(measurement);
			assertThrows(MeasurementException.class, ()-> measurementService.addMeasurement(measurement,idInstitution));  
	
			
			verify(measurementDao, times(0)).save(measurement); //verify that the save method was never called
		}
		
		@Test
		public void addMeasurementWhitoutInstitutionTest() {  // design id = 31
			
			Measurement measurement = new Measurement();
			measurement.setMeasDescription("description:");
			measurement.setMeasMaxthreshold(new BigDecimal("1000000"));
			measurement.setMeasMinthreshold(new BigDecimal("100"));
			measurement.setMeasName("name");
			measurement.setMeasUnit("m");
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurement.setCheckMeasurs(list);
			
			when(measurementDao.save(measurement)).thenReturn(measurement);
			assertThrows(MeasurementException.class, ()-> measurementService.addMeasurement(measurement,idInstitution-5));  // institution with id idInstitution-5 does not exist
	
			
			verify(measurementDao, times(0)).save(measurement); //verify that the save method was never called
		}
	}
	@Nested
	@DisplayName("Test of edit Measurement methods")
	class EditMeasurementMethods{
		@Test
		public void editMeasurementTest() {  // design id = 32
	
			Measurement measurementToUpdate = measurementService.getMeasurement(idMeasurementTest);
			verify(measurementDao).findById(idMeasurementTest);
			
			Institution institution2 = new Institution();
			institution2.setInstId(4);
			Optional<Institution> optionalInstitution2 = Optional.of(institution2);
			when(institutionRepository.findById((long) 4)).thenReturn(optionalInstitution2);
			
			measurementToUpdate.setInstitution(institution2);
			measurementToUpdate.setMeasDescription("description2:");
			measurementToUpdate.setMeasMaxthreshold(new BigDecimal("9999"));
			measurementToUpdate.setMeasMinthreshold(new BigDecimal("99"));
			measurementToUpdate.setMeasName("name2");
			measurementToUpdate.setMeasUnit("cm");
			
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurementToUpdate.setCheckMeasurs(list);
			
			when(measurementDao.update(measurementToUpdate)).thenReturn(measurementToUpdate);
			assertDoesNotThrow(()-> measurementService.editMeasurement(measurementToUpdate));
			
			verify(measurementDao).update(measurementToUpdate);
		}
		@Test
		public void editNullMeasurementTest() {  // design id = 33
			
			Measurement measurementToUpdate = measurementService.getMeasurement(idMeasurementTest);
			verify(measurementDao).findById(idMeasurementTest);
			
			when(measurementDao.update(measurementToUpdate)).thenReturn(measurementToUpdate);
			assertThrows(MeasurementException.class, ()-> measurementService.editMeasurement(null));
			
			verify(measurementDao, times(0)).update(measurementToUpdate); //verify that the save method was never called
		}
		@Test
		public void editMeasurementWhitoutDescriptionTest() {   // design id = 34
			
			Measurement measurementToUpdate = measurementService.getMeasurement(idMeasurementTest);
			verify(measurementDao).findById(idMeasurementTest);
			
			Institution institution2 = new Institution();
			institution2.setInstId(4);
			Optional<Institution> optionalInstitution2 = Optional.of(institution2);
			when(institutionRepository.findById((long) 4)).thenReturn(optionalInstitution2);
			
			measurementToUpdate.setInstitution(institution2);
			measurementToUpdate.setMeasDescription(null); // cannot be null
			measurementToUpdate.setMeasMaxthreshold(new BigDecimal("9999"));
			measurementToUpdate.setMeasMinthreshold(new BigDecimal("99"));
			measurementToUpdate.setMeasName("name2");
			measurementToUpdate.setMeasUnit("cm");
			
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check2 = new CheckMeasur();
			list.add(check2);
			measurementToUpdate.setCheckMeasurs(list);
			
			when(measurementDao.update(measurementToUpdate)).thenReturn(measurementToUpdate);
			assertThrows(MeasurementException.class, ()-> measurementService.editMeasurement(measurementToUpdate));
			
			verify(measurementDao, times(0)).update(measurementToUpdate); //verify that the save method was never called
			
		}
		@Test
		public void editMeasurementWhitoutMaxthresholdTest() {  // design id = 35
		
			Measurement measurementToUpdate = measurementService.getMeasurement(idMeasurementTest);
			verify(measurementDao).findById(idMeasurementTest);
			
			Institution institution2 = new Institution();
			institution2.setInstId(4);
			Optional<Institution> optionalInstitution2 = Optional.of(institution2);
			when(institutionRepository.findById((long) 4)).thenReturn(optionalInstitution2);
			
			measurementToUpdate.setInstitution(institution2);
			measurementToUpdate.setMeasDescription("description2:");
			measurementToUpdate.setMeasMaxthreshold(null); //cannot be null
			measurementToUpdate.setMeasMinthreshold(new BigDecimal("99"));
			measurementToUpdate.setMeasName("name2");
			measurementToUpdate.setMeasUnit("cm");
			
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurementToUpdate.setCheckMeasurs(list);
			
			when(measurementDao.update(measurementToUpdate)).thenReturn(measurementToUpdate);
			assertThrows(MeasurementException.class, ()-> measurementService.editMeasurement(measurementToUpdate));
			
			verify(measurementDao, times(0)).update(measurementToUpdate); //verify that the save method was never called
			
		}
		@Test
		public void editMeasurementWhitoutMinthresholdTest() {  // design id = 36
		
			Measurement measurementToUpdate = measurementService.getMeasurement(idMeasurementTest);
			verify(measurementDao).findById(idMeasurementTest);
			
			Institution institution2 = new Institution();
			institution2.setInstId(4);
			Optional<Institution> optionalInstitution2 = Optional.of(institution2);
			when(institutionRepository.findById((long) 4)).thenReturn(optionalInstitution2);
			
			measurementToUpdate.setInstitution(institution2);
			measurementToUpdate.setMeasDescription("description2:");
			measurementToUpdate.setMeasMaxthreshold(new BigDecimal("9999"));
			measurementToUpdate.setMeasMinthreshold(null); //cannot be null
			measurementToUpdate.setMeasName("name2");
			measurementToUpdate.setMeasUnit("cm");
			
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurementToUpdate.setCheckMeasurs(list);
			
			when(measurementDao.update(measurementToUpdate)).thenReturn(measurementToUpdate);
			assertThrows(MeasurementException.class, ()-> measurementService.editMeasurement(measurementToUpdate));
			
			verify(measurementDao, times(0)).update(measurementToUpdate); //verify that the save method was never called
			
		}
		@Test
		public void editMeasurementWhitoutNameTest() {  // design id = 37
	
			Measurement measurementToUpdate = measurementService.getMeasurement(idMeasurementTest);
			verify(measurementDao).findById(idMeasurementTest);
			
			Institution institution2 = new Institution();
			institution2.setInstId(4);
			Optional<Institution> optionalInstitution2 = Optional.of(institution2);
			when(institutionRepository.findById((long) 4)).thenReturn(optionalInstitution2);
			
			measurementToUpdate.setInstitution(institution2);
			measurementToUpdate.setMeasDescription("description2:");
			measurementToUpdate.setMeasMaxthreshold(new BigDecimal("9999"));
			measurementToUpdate.setMeasName(null); //cannot be null
			measurementToUpdate.setMeasUnit("cm");
			
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurementToUpdate.setCheckMeasurs(list);
			
			when(measurementDao.update(measurementToUpdate)).thenReturn(measurementToUpdate);
			assertThrows(MeasurementException.class, ()-> measurementService.editMeasurement(measurementToUpdate));
			
			verify(measurementDao, times(0)).update(measurementToUpdate); //verify that the save method was never called
			
		}
		@Test
		public void editMeasurementWhitoutUnitTest() {  // design id = 38
		
			Measurement measurementToUpdate = measurementService.getMeasurement(idMeasurementTest);
			verify(measurementDao).findById(idMeasurementTest);
			
			Institution institution2 = new Institution();
			institution2.setInstId(4);
			Optional<Institution> optionalInstitution2 = Optional.of(institution2);
			when(institutionRepository.findById((long) 4)).thenReturn(optionalInstitution2);
			
			measurementToUpdate.setInstitution(institution2);
			measurementToUpdate.setMeasDescription("description2:");
			measurementToUpdate.setMeasMaxthreshold(new BigDecimal("9999"));
			measurementToUpdate.setMeasName("name2");
			measurementToUpdate.setMeasUnit(null); //cannot be null
			
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurementToUpdate.setCheckMeasurs(list);
			
			when(measurementDao.update(measurementToUpdate)).thenReturn(measurementToUpdate);
			assertThrows(MeasurementException.class, ()-> measurementService.editMeasurement(measurementToUpdate));
			
			verify(measurementDao, times(0)).update(measurementToUpdate); //verify that the save method was never called
			
		}
		@Test
		public void editMeasurementWhitoutInstitutionTest() {  // design id = 40
		
			Measurement measurementToUpdate = measurementService.getMeasurement(idMeasurementTest);
			verify(measurementDao).findById(idMeasurementTest);
	
			
			measurementToUpdate.setInstitution(null);  //cannot be null
			measurementToUpdate.setMeasDescription("description2:");
			measurementToUpdate.setMeasMaxthreshold(new BigDecimal("9999"));
			measurementToUpdate.setMeasName("name2");
			measurementToUpdate.setMeasUnit("cm");
			
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurementToUpdate.setCheckMeasurs(list);
			
			
			
			when(measurementDao.update(measurementToUpdate)).thenReturn(measurementToUpdate);
			assertThrows(MeasurementException.class, ()-> measurementService.editMeasurement(measurementToUpdate));
			
			verify(measurementDao, times(0)).update(measurementToUpdate); //verify that the save method was never called
			
		}
	}
	
}
