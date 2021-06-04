package co.edu.icesi.ci.tallerfinal.back.test.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import co.edu.icesi.ci.tallerfinal.back.dao.PhysicalcheckupDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.ci.tallerfinal.back.exceptions.PhysicalcheckupException;
import co.edu.icesi.ci.tallerfinal.back.model.Institutioncampus;
import co.edu.icesi.ci.tallerfinal.back.model.Person;
import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;
import co.edu.icesi.ci.tallerfinal.back.repositories.CampusRepository;
import co.edu.icesi.ci.tallerfinal.back.repositories.PersonRepository;
import co.edu.icesi.ci.tallerfinal.back.service.PhysicalcheckupService;
import co.edu.icesi.ci.tallerfinal.back.service.VisitService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PhysicalcheckupIntegrationTest {
	
	private PhysicalcheckupService physicalcheckupService; 
	
	private PhysicalcheckupDao physicalcheckupDao;

	private PersonRepository personRepository;
	
	private VisitService visitService;
	private CampusRepository campusRepository;
 
	private static Person person;
	private static Visit visit;
	private static Physicalcheckup physicalcheckupTest; // object to test the edit methods
	
	
	@Autowired
	public PhysicalcheckupIntegrationTest(PhysicalcheckupService physicalcheckupService,
			PhysicalcheckupDao physicalcheckupDao, PersonRepository personRepository,
			VisitService visitService, CampusRepository campusRepository) {
		this.physicalcheckupService = physicalcheckupService;
		this.physicalcheckupDao = physicalcheckupDao;
		this.personRepository = personRepository;
		this.visitService = visitService;
		this.campusRepository = campusRepository;
	}
	@BeforeAll
	public static void setUp() {
		person = new Person();
		
		visit = new Visit();
		visit.setVisitEntrancedate(new Date());
		visit.setVisitDetail("icesi int");
		
		physicalcheckupTest = new Physicalcheckup();
		physicalcheckupTest.setVisit(visit);
		physicalcheckupTest.setPerson(person);
		physicalcheckupTest.setPhycheDate(new Date()); //date mandatory
		
		
	}
	public void scenario() {
		personRepository.save(person);
		Institutioncampus campus = new Institutioncampus();
		campusRepository.save(campus);
		visitService.addVisit(visit, person.getPersId(), campus.getInstcamId());
		
		physicalcheckupService.addPhysicalcheckup(physicalcheckupTest,person.getPersId(), visit.getVisitId());
	}
	@Nested
	@DisplayName("Test of add Physicalcheckup methods")
	@Order(1)
	class AddPhysicalcheckupMethods{
		
		@Test
		public void addPhysicalcheckupTest() {  // design id = 57
			scenario();
			Physicalcheckup checkup = new Physicalcheckup();
			checkup.setPhycheDate(new Date()); //date mandatory
			
			assertDoesNotThrow(()-> physicalcheckupService.addPhysicalcheckup(checkup,person.getPersId(),visit.getVisitId()));   // the assertDoesNotThrow is to check that no exceptions are thrown when everything is correct
			assertNotNull(physicalcheckupDao.findById(checkup.getPhycheId())); // verify that it has been saved in the database
		}
		
		@Test
		public void addPhysicalcheckupWhitoutPersonTest() {   // design id = 58
	
			Physicalcheckup checkup = new Physicalcheckup();
			checkup.setPhycheDate(new Date()); //date mandatory 
			
			try {
				assertThrows(PhysicalcheckupException.class, ()->physicalcheckupService.addPhysicalcheckup(checkup,person.getPersId()-5,visit.getVisitId())); 
				assertNull(physicalcheckupDao.findById(checkup.getPhycheId())); // verify that it has not been saved in the database
			}catch(NoSuchElementException e) {
				fail();  // person with id person.getPersId()-5 does not exist
			}
	
		}
		
		@Test
		public void addPhysicalcheckupWhitoutVisitTest() {  // design id = 59
	
			Physicalcheckup checkup = new Physicalcheckup();
			checkup.setPhycheDate(new Date()); //date mandatory 
			
			try {
				assertThrows(PhysicalcheckupException.class, ()->physicalcheckupService.addPhysicalcheckup(checkup,person.getPersId(),visit.getVisitId()-5)); 
				assertNull(physicalcheckupDao.findById(checkup.getPhycheId())); // verify that it has not been saved in the database
			}catch(NoSuchElementException e) {
				fail();  // visit with id visit.getVisitId()-5 does not exist
			}
		
		}
	}
	@Nested
	@DisplayName("Test of edit Physicalcheckup methods")
	class EditPhysicalcheckupMethods{
		@SuppressWarnings("deprecation")
		@Test
		public void editPhysicalcheckupTest() {  // design id = 60
	
			Physicalcheckup checkupToUpdate = physicalcheckupService.getPhysicalcheckup(physicalcheckupTest.getPhycheId());
			
			Person person2 = new Person();
			personRepository.save(person2);

			checkupToUpdate.setPhycheDate(new Date());
			checkupToUpdate.setPerson(person2);
			checkupToUpdate.setVisit(visit);
			assertDoesNotThrow(()-> physicalcheckupService.editPhysicalcheckup(checkupToUpdate));
			
			Physicalcheckup updatedCheckup =  physicalcheckupDao.findById(physicalcheckupTest.getPhycheId());
			assertAll("verify PhycheDate equals",    
					 () -> assertEquals(updatedCheckup.getPhycheDate().getDay(), checkupToUpdate.getPhycheDate().getDay()),
					 ()->assertEquals(updatedCheckup.getPhycheDate().getMonth(), checkupToUpdate.getPhycheDate().getMonth()),
					 ()->assertEquals(updatedCheckup.getPhycheDate().getYear(), checkupToUpdate.getPhycheDate().getYear())
			); // check the dates this way because the equals method takes the two dates in different format
			assertEquals(updatedCheckup.getPerson().getPersId(), checkupToUpdate.getPerson().getPersId());
			assertEquals(updatedCheckup.getVisit().getVisitId(), checkupToUpdate.getVisit().getVisitId());     // verify that it has been updated in the database
			
		}
		
		@Test
		public void editPhysicalcheckupWithoutPersonTest() {  // design id = 61
		
			Physicalcheckup checkupToUpdate = physicalcheckupService.getPhysicalcheckup(physicalcheckupTest.getPhycheId());
			
			Person person2 = new Person();
			
			try {
				checkupToUpdate.setPerson(person2); // the person has not been added to the database
				assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.editPhysicalcheckup(checkupToUpdate));
			}catch(NoSuchElementException e) {
				fail();  
			} 
			try {
				checkupToUpdate.setPerson(null); // the person is nulll
				assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.editPhysicalcheckup(checkupToUpdate));
			}catch(NullPointerException e) {
				fail();  
			}
		}
		@Test
		public void editPhysicalcheckupWithoutVisitTest() {   // design id = 62
		
			Physicalcheckup checkupToUpdate = physicalcheckupService.getPhysicalcheckup(physicalcheckupTest.getPhycheId());
			
			Visit visit2 = new Visit();
			try {
				checkupToUpdate.setVisit(visit2); // the visit has not been added to the database
				assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.editPhysicalcheckup(checkupToUpdate));
			}catch(NoSuchElementException e) {
				fail();  
			} 
			try {
				checkupToUpdate.setVisit(null); // the visit is null
				assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.editPhysicalcheckup(checkupToUpdate));
			}catch(NullPointerException e) {
				fail();  
			}
		}
	}
	@Nested
	@DisplayName("query methods test")
	class QueryMethods{
		
		@Test
		public void findByVisitIdTest() {
			Person person2 = new Person();
			personRepository.save(person2);
			Institutioncampus campus2 = new Institutioncampus();
			campusRepository.save(campus2);
			Visit visit2 = new Visit();
			visit2.setVisitDetail("visitTest");
			visit2.setVisitEntrancedate(new Date());
			visitService.addVisit(visit2, person2.getPersId(), campus2.getInstcamId());
			
			Physicalcheckup checkup = new Physicalcheckup();
			checkup.setPhycheDate(new Date()); //date mandatory
			
			physicalcheckupService.addPhysicalcheckup(checkup,person2.getPersId(),visit2.getVisitId());  
			Physicalcheckup phyche = physicalcheckupService.findByVisitId(visit2.getVisitId());
			assertNotNull(phyche);
			assertEquals(checkup.getPhycheId(), phyche.getPhycheId());
		}
		@Test
		public void findByDateTest() {
			Physicalcheckup checkup = new Physicalcheckup();
			Date date = new Date();
			checkup.setPhycheDate(date); //date mandatory
			
			physicalcheckupService.addPhysicalcheckup(checkup,person.getPersId(),visit.getVisitId());  
			List<Physicalcheckup> phyche = physicalcheckupService.findByDate(date);
			assertNotNull(phyche);
			boolean flag=false;
			for(Physicalcheckup py : phyche) {
				if(py.getPhycheId()==checkup.getPhycheId()) {
					flag = true;
				}
			}
			assertTrue(flag);
		}
	}
}
