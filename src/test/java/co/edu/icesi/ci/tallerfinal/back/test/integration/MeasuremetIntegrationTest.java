package co.edu.icesi.ci.tallerfinal.back.test.integration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import co.edu.icesi.ci.tallerfinal.back.dao.MeasurementDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.ci.tallerfinal.back.exceptions.MeasurementException;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.Institution;
import co.edu.icesi.ci.tallerfinal.back.model.Measurement;
import co.edu.icesi.ci.tallerfinal.back.repositories.InstitutionRepository;
import co.edu.icesi.ci.tallerfinal.back.service.MeasurementService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MeasuremetIntegrationTest {
	
	private MeasurementService measurementService;
	 
	private MeasurementDao measurementDao;
 
	private InstitutionRepository institutionRepository;
	
	private static Institution institution;
	private static Measurement measurementTest; // object to test the edit methods
	
	@Autowired
	public MeasuremetIntegrationTest(MeasurementService measurementService, MeasurementDao measurementDao,
			InstitutionRepository institutionRepository) {
		this.measurementService = measurementService;
		this.measurementDao = measurementDao;
		this.institutionRepository = institutionRepository;
	}
	
	@BeforeAll
	public static void setup() {
		
		institution = new Institution();
		
		measurementTest = new Measurement();
		measurementTest.setMeasDescription("description:");
		measurementTest.setMeasMaxthreshold(new BigDecimal("1000000"));
		measurementTest.setMeasMinthreshold(new BigDecimal("100"));
		measurementTest.setMeasName("name");
		measurementTest.setMeasUnit("m");
		measurementTest.setInstitution(institution);
		List<CheckMeasur> list = new ArrayList<>();
		CheckMeasur check = new CheckMeasur();
		list.add(check);
		measurementTest.setCheckMeasurs(list);
		
	}
	public void sceneario() {
		institutionRepository.save(institution);
		measurementService.addMeasurement(measurementTest, institution.getInstId());
	}
	
	
	@Nested
	@DisplayName("Test of add Measurement methods")
	class AddMeasurementMethods{
		
		@Test
		public void addMeasurementTest() {  // design id = 63
			sceneario();
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
			assertDoesNotThrow(()-> measurementService.addMeasurement(measurement,institution.getInstId()));   // the assertDoesNotThrow is to check that no exceptions are thrown when everything is correct
			
			assertNotNull(measurementDao.findById(measurement.getMeasId()));  // verify that it has been saved in the database
		}
		
		@Test
		public void addMeasurementWhitoutInstitutionTest() {  // design id = 64
			
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
			
			try {
				assertThrows(MeasurementException.class, ()-> measurementService.addMeasurement(measurement,institution.getInstId()-5));  // institution with id institution.getInstId()-5 does not exist
				assertNull(measurementDao.findById(measurement.getMeasId()));  // verify that it has not been saved in the database
			}catch(NoSuchElementException e) {
				fail();  // institution with id instituion.getInstId()-5 does not exist
			}
		}
	}
	@Nested
	@DisplayName("Test of edit Measurement methods")
	class EditMeasurementMethods{
		@Test
		public void editMeasurementTest() {  // design id = 65
	
			Measurement measurementToUpdate = measurementService.getMeasurement(measurementTest.getMeasId());
			Institution institution2 = new Institution();
			institutionRepository.save(institution2);
			
			measurementToUpdate.setInstitution(institution2);
			measurementToUpdate.setMeasDescription("description2:");
			measurementToUpdate.setMeasMaxthreshold(new BigDecimal("9999.00"));
			measurementToUpdate.setMeasMinthreshold(new BigDecimal("99.00"));
			measurementToUpdate.setMeasName("name2");
			measurementToUpdate.setMeasUnit("cm");
			
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurementToUpdate.setCheckMeasurs(list);
			
			assertDoesNotThrow(()-> measurementService.editMeasurement(measurementToUpdate));
			Measurement updatedMeasurement = measurementDao.findById(measurementTest.getMeasId());
			assertEquals(updatedMeasurement.getInstitution().getInstId(), measurementToUpdate.getInstitution().getInstId() ); // verify that it has been updated in the database
			assertEquals(updatedMeasurement.getMeasDescription(), measurementToUpdate.getMeasDescription()); // verify that it has been updated in the database
			assertEquals(updatedMeasurement.getMeasMaxthreshold(), measurementToUpdate.getMeasMaxthreshold()); // verify that it has been updated in the database
			assertEquals(updatedMeasurement.getMeasMinthreshold(), measurementToUpdate.getMeasMinthreshold()); // verify that it has been updated in the database
			assertEquals(updatedMeasurement.getMeasName(), measurementToUpdate.getMeasName()); // verify that it has been updated in the database
			assertEquals(updatedMeasurement.getMeasUnit(), measurementToUpdate.getMeasUnit()); // verify that it has been updated in the database
		}
		
		@Test
		public void editMeasurementWhitoutInstitutionTest() {// design id = 66
			Measurement measurementToUpdate = measurementService.getMeasurement(measurementTest.getMeasId());
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurementToUpdate.setCheckMeasurs(list);
			Institution institution2 = new Institution();
			
			try {
				measurementToUpdate.setInstitution(institution2); // the institution has not been added to the database
				assertThrows(MeasurementException.class, ()-> measurementService.editMeasurement(measurementToUpdate));
			}catch(NoSuchElementException e) {
				fail();  
			} 
			try {
				measurementToUpdate.setInstitution(null); // the institution is nulll
				assertThrows(MeasurementException.class, ()-> measurementService.editMeasurement(measurementToUpdate));
			}catch(NullPointerException e) {
				fail();  
			}
		}
	}
	@Nested
	@DisplayName("query methods test")
	class QueryMethods{
		
		@Test
		public void findByValueTest() {
			Measurement measurement = new Measurement();
			measurement.setMeasDescription("meas test 11");
			measurement.setMeasMaxthreshold(new BigDecimal("1000000"));
			measurement.setMeasMinthreshold(new BigDecimal("100"));
			measurement.setMeasName("name");
			measurement.setMeasUnit("m");
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurement.setCheckMeasurs(list);
			measurementService.addMeasurement(measurement,institution.getInstId());
			
			List<Measurement> measureFound = measurementService.findByValue(new BigDecimal(200));
			assertNotNull(measureFound);
			assertTrue(measureFound.size()!=0);
			boolean flag=false;
			for(Measurement m : measureFound) {
				if(m.getMeasDescription().equals("meas test 11")) {
					flag=true;
				}
			}
			assertTrue(flag);
		}
		@Test
		public void findByDescriptionTest() {
			Measurement measurement = new Measurement();
			measurement.setMeasDescription("meas test 33");
			measurement.setMeasMaxthreshold(new BigDecimal("1000000"));
			measurement.setMeasMinthreshold(new BigDecimal("100"));
			measurement.setMeasName("name");
			measurement.setMeasUnit("m");
			List<CheckMeasur> list = new ArrayList<>();
			CheckMeasur check = new CheckMeasur();
			list.add(check);
			measurement.setCheckMeasurs(list);
			measurementService.addMeasurement(measurement,institution.getInstId());
			
			List<Measurement> measureFound = measurementService.findByDescription("meas test 33");
			assertNotNull(measureFound);
			assertTrue(measureFound.size()!=0);
			boolean flag=false;
			for(Measurement m : measureFound) {
				if(m.getMeasDescription().equals("meas test 33") && m.getMeasName().equals("name")) {
					flag=true;
				}
			}
			assertTrue(flag);
		}
		
	}
	
}
