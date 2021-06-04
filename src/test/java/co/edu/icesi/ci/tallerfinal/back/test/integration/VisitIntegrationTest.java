package co.edu.icesi.ci.tallerfinal.back.test.integration;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


import co.edu.icesi.ci.tallerfinal.back.dao.VisitDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.ci.tallerfinal.back.exceptions.VisitException;
import co.edu.icesi.ci.tallerfinal.back.model.Institutioncampus;
import co.edu.icesi.ci.tallerfinal.back.model.Person;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;
import co.edu.icesi.ci.tallerfinal.back.repositories.CampusRepository;
import co.edu.icesi.ci.tallerfinal.back.repositories.PersonRepository;
import co.edu.icesi.ci.tallerfinal.back.service.VisitService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VisitIntegrationTest {
	
	private VisitService visitService; 
	
	private PersonRepository personRepository; 
	private CampusRepository campusRepository; 
	private VisitDao visitDao;
	private static Person person;
	private static Institutioncampus campus;
	private static Visit visitTest;  // object to test the edit methods
	
	@Autowired
	public VisitIntegrationTest(VisitService visitService, PersonRepository personRepository, 
			CampusRepository campusRepository, VisitDao visitDao) {
		this.visitService = visitService;
		this.personRepository = personRepository;
		this.campusRepository = campusRepository;
		this.visitDao = visitDao;
	}
	
	@BeforeAll
	public static void setUp() {
		person = new Person();
		campus = new Institutioncampus();
		
		visitTest = new Visit();
		visitTest.setVisitEntrancedate(new Date()); // entrance date mandatory
		visitTest.setVisitDetail("Invitación a Icesi interactiva"); // detail with more than 5 letters and exit date optional
		
		
		visitTest.setInstitutioncampus(campus);
		visitTest.setPerson(person);
	}
	public void scenario() {
		personRepository.save(person);
		campusRepository.save(campus);
		visitService.addVisit(visitTest, person.getPersId(), campus.getInstcamId());  // save the object to test the edit methods
	}
	@Nested
	@DisplayName("Test of add visit methods")
	class AddVisitMethods{
		@Test
		public void addVisit() {   // design id = 51
			scenario();
			Visit visit = new Visit();
			visit.setVisitEntrancedate(new Date()); // entrance date mandatory
			visit.setVisitDetail("Invitación a Icesi interactiva"); // detail with more than 5 letters and exit date optional
			
			assertDoesNotThrow(()->visitService.addVisit(visit, person.getPersId(), campus.getInstcamId()));
			assertNotNull(visitDao.findById(visit.getVisitId())); // verify that it has been saved in the database
		}
		@Test
		public void addVisitWhithouPerson() {  // design id = 52
	
			Visit visit = new Visit();
			visit.setVisitEntrancedate(new Date()); // entrance date mandatory
			visit.setVisitDetail("Invitación a Icesi interactiva"); // detail with more than 5 letters and the exit date is optional
			System.out.println("person id in test: "+ person.getPersId() );
			try {
				
				assertThrows(VisitException.class, ()->visitService.addVisit(visit, person.getPersId()-15, campus.getInstcamId())); 
				assertNull(visitDao.findById(visit.getVisitId())); // verify that it has not been saved in the database
			}catch(NoSuchElementException e) {
				fail();  // person with id person.getPersId()-5 does not exist
			} 
			
		}
		@Test
		public void addVisitWhithouCampus() {  // design id = 53
	
			Visit visit = new Visit();
			visit.setVisitEntrancedate(new Date()); // entrance date mandatory
			visit.setVisitDetail("Invitación a Icesi interactiva"); // detail with more than 5 letters and the exit date is optional
			
			try {
				assertThrows(VisitException.class, ()->visitService.addVisit(visit, person.getPersId(), campus.getInstcamId()-5)); 
				assertNull(visitDao.findById(visit.getVisitId())); // verify that it has not been saved in the database
			}catch(NoSuchElementException e) {
				fail();  // campus with id campus.getVisitId()-5 does not exist
			} 
		}
	
	}
	@Nested
	@DisplayName("Test of edit visit methods")
	class EditVisitMethods{
		@SuppressWarnings("deprecation")
		@Test
		public void editVisitTest() {  // design id = 54
			Visit visitToUpdate = visitService.getVisit(visitTest.getVisitId());
			
			visitToUpdate.setVisitEntrancedate(new Date());
			visitToUpdate.setVisitDetail("Reunion");
			visitToUpdate.setVisitExitdate(new Date());
			
			assertDoesNotThrow(()-> visitService.editVisit(visitToUpdate));
			
			Visit updatedVisit = visitDao.findById(visitTest.getVisitId());
			assertEquals(updatedVisit.getVisitDetail(), visitToUpdate.getVisitDetail()); // verify that it has been update in the database
			
			assertAll("verify visitEntranceDate equals",    
					 () -> assertEquals(updatedVisit.getVisitEntrancedate().getDay(), visitToUpdate.getVisitEntrancedate().getDay()),
					 ()->assertEquals(updatedVisit.getVisitEntrancedate().getMonth(), visitToUpdate.getVisitEntrancedate().getMonth()),
					 ()->assertEquals(updatedVisit.getVisitEntrancedate().getYear(), visitToUpdate.getVisitEntrancedate().getYear())
			);  // check the dates this way because the equals method takes the two dates in different format
			assertAll("verify visitExitDate equals", 
					 () -> assertEquals(updatedVisit.getVisitExitdate().getDay(), visitToUpdate.getVisitExitdate().getDay()),
					 ()->assertEquals(updatedVisit.getVisitExitdate().getMonth(), visitToUpdate.getVisitExitdate().getMonth()),
					 ()->assertEquals(updatedVisit.getVisitExitdate().getYear(), visitToUpdate.getVisitExitdate().getYear())
			);

			
		}
		@Test
		public void editVisitWhithouPersonTest() {   // design id = 55
			
			Visit visitToUpdate = visitService.getVisit(visitTest.getVisitId());
			
			Person person2 = new Person();
			try {
				visitToUpdate.setPerson(person2); // the person has not been added to the database
				assertThrows(VisitException.class, ()-> visitService.editVisit(visitToUpdate));
			}catch(NoSuchElementException e) {
				fail();  
			} 
			try {
				visitToUpdate.setPerson(null); // the person is null
				assertThrows(VisitException.class, ()-> visitService.editVisit(visitToUpdate));
				
			}catch(NullPointerException e) {
				fail();  
			}
				
		}
		@Test
		public void editVisitWhithouCampusTest() {   // design id = 56
			
			Visit visitToUpdate = visitService.getVisit(visitTest.getVisitId());
			
			Institutioncampus campus2 = new Institutioncampus();
			try {
				visitToUpdate.setInstitutioncampus(campus2); // the campus has not been added to the database
				assertThrows(VisitException.class, ()-> visitService.editVisit(visitToUpdate));
			}catch(NoSuchElementException e) {
				fail();  
			} 
			try {
				visitToUpdate.setInstitutioncampus(null); // the campus is null
				assertThrows(VisitException.class, ()-> visitService.editVisit(visitToUpdate));
			
			}catch(NullPointerException e) {
				fail();  
			}
		}
		
	}
		
	@Nested
	@DisplayName("query methods test")
	class QueryMethods{
		
		@Test
		public void findByPersonIdTest() {
		//	String personName = "Test person 1";
			Visit visit = new Visit();
			
			String visitDetail = "Test visit 1";
			
			visit.setVisitDetail(visitDetail);
			visit.setVisitEntrancedate(new Date());
			visitService.addVisit(visit, person.getPersId(), campus.getInstcamId()); 
			
			List<Visit> visitFound = visitService.findByPersonId(person.getPersId());
			Visit visitFo1 = visitFound.get(0);
			Visit visitFo2 = visitFound.get(visitFound.size()-1);
			assertFalse(visitFound.size()==0);
			assertNotNull(visitFo1);
			assertNotNull(visitFo2);
			assertEquals(visitFo2.getVisitDetail(), "Test visit 1");
			
		}
		@Test
		public void findByEntrancedateTest() {
			Visit visit = new Visit();
			Visit visit2 = new Visit();
			visit.setVisitDetail("visit test 1");
			Date date = new Date();
			visit.setVisitEntrancedate(date);
			visit2.setVisitDetail("visit test 2");
			visit2.setVisitEntrancedate(date);
			
			visitService.addVisit(visit, person.getPersId(), campus.getInstcamId()); 
			visitService.addVisit(visit2, person.getPersId(), campus.getInstcamId()); 
			
			List<Visit> visitFound = visitService.findByEntrancedate(date);
			assertNotNull(visitFound);
			assertTrue(visitFound.size()!=0);
			boolean flag = false;
			for(Visit v : visitFound) {
				if(v.getVisitDetail().equals("visit test 1") || v.getVisitDetail().equals("visit test 2"))
					flag = true;
			}
			assertTrue(flag);
		}
		@Test
		public void findByExitdate() {
			Visit visit = new Visit();
			Visit visit2 = new Visit();
			visit.setVisitDetail("visit test 11");
			Date date = new Date();
			visit.setVisitEntrancedate(date);
			visit.setVisitExitdate(date);
			visit2.setVisitDetail("visit test 22");
			visit2.setVisitEntrancedate(date);
			visit.setVisitExitdate(date);
			
			visitService.addVisit(visit, person.getPersId(), campus.getInstcamId()); 
			visitService.addVisit(visit2, person.getPersId(), campus.getInstcamId()); 
			
			List<Visit> visitFound = visitService.findByExitdate(date);
			assertNotNull(visitFound);
			assertTrue(visitFound.size()!=0);
			boolean flag = false;
			for(Visit v : visitFound) {
				if(v.getVisitDetail().equals("visist test 22") || v.getVisitDetail().equals("visit test 11"))
					flag = true;
			}
			assertTrue(flag);
			
		}
		@Test
		public void findVisitsWihtFewerTwoPhyTest() {
			Visit visit = new Visit();
			Visit visit2 = new Visit();
			visit.setVisitDetail("visit test 3");
			Date date = new Date();
			visit.setVisitEntrancedate(date);
			visit2.setVisitDetail("visit test 4");
			visit2.setVisitEntrancedate(date);
			
			visitService.addVisit(visit, person.getPersId(), campus.getInstcamId()); 
			visitService.addVisit(visit2, person.getPersId(), campus.getInstcamId()); 
			
			List<Visit> visitFound = visitService.findVisitsWihtFewerTwoPhy(date);
			assertNotNull(visitFound);
			assertTrue(visitFound.size()!=0);
			boolean flag = false;
			for(Visit v : visitFound) {
				if(v.getVisitDetail().equals("visist test 3") || v.getVisitDetail().equals("visit test 4"))
					flag = true;
			}
			assertTrue(flag);
			
		}
		@Test
		public void findPersonsByVisitDateTest() {
			Visit visit = new Visit();
			Visit visit2 = new Visit();
			visit.setVisitDetail("visit test 3");
			Date date = new Date();
			Date exitDate = new Date();
			visit.setVisitEntrancedate(date);
			visit.setVisitExitdate(exitDate);
			visit2.setVisitDetail("visit test 4");
			visit2.setVisitEntrancedate(date);
			visit2.setVisitExitdate(exitDate);
			
			visitService.addVisit(visit, person.getPersId(), campus.getInstcamId()); 
			visitService.addVisit(visit2, person.getPersId(), campus.getInstcamId()); 
			
			// map  --->  { person, number of visits}
			Map<Person, Long> visitFound = visitService.findPersonsByVisitDate(date,exitDate);
			assertNotNull(visitFound);
			assertTrue(visitFound.size()!=0);
			boolean flag = false;
			for(Map.Entry<Person, Long> e: visitFound.entrySet()) {
				if(e.getKey().getPersId() == person.getPersId())
					flag=true;
			}
			assertTrue(flag);
			
		}
		
	}
}

