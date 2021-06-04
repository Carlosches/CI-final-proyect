package co.edu.icesi.ci.tallerfinal.back.test.unit;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import javax.persistence.PersistenceContext;

import co.edu.icesi.ci.tallerfinal.back.dao.VisitDao;
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

import co.edu.icesi.ci.tallerfinal.back.exceptions.VisitException;
import co.edu.icesi.ci.tallerfinal.back.model.Institutioncampus;
import co.edu.icesi.ci.tallerfinal.back.model.Person;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;
import co.edu.icesi.ci.tallerfinal.back.repositories.CampusRepository;
import co.edu.icesi.ci.tallerfinal.back.repositories.PersonRepository;
import co.edu.icesi.ci.tallerfinal.back.service.VisitService;
import co.edu.icesi.ci.tallerfinal.back.service.VisitServiceImpl;

@ContextConfiguration(classes = {PersistenceContext.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VisitServiceTestDao {
	
	@InjectMocks
	private static VisitService visitService; 
	
	@Mock
	private static VisitDao visitDao;
	
	@Mock
	private static PersonRepository personRepository;
	
	@Mock
	private static CampusRepository campusRepository;
	
	private static Person person;
	private static Institutioncampus campus;
	private static Visit visitTest;  // object to test the edit methods
	
	private static long idPerson;
	private static long idVisitTest;
	private static long idCampus;
	
	@BeforeAll
	public static void setUp() {
		visitService = new VisitServiceImpl(visitDao, personRepository,campusRepository);
		person = new Person();
		idPerson= person.getPersId();
		campus = new Institutioncampus();
		idCampus = campus.getInstcamId();
		
		visitTest = new Visit();
		visitTest.setPerson(person);
		visitTest.setInstitutioncampus(campus);
		visitTest.setVisitEntrancedate(new Date()); // entrance date mandatory
		visitTest.setVisitDetail("Invitación a Icesi interactiva"); // detail with more than 5 letters and exit date optional
		idVisitTest = visitTest.getVisitId();
		
	}
	@BeforeEach
	public  void init() {
		Optional<Person> optionalPerson = Optional.of(person);
		when(personRepository.findById(idPerson)).thenReturn(optionalPerson); //the interaction is defined for when a get is done in the edit methods
		
		when(visitDao.findById(idVisitTest)).thenReturn(visitTest); //the interaction is defined for when a get is done in the edit methods
		
		Optional<Institutioncampus> optionalCampus = Optional.of(campus);
		when(campusRepository.findById(idCampus)).thenReturn(optionalCampus); //the interaction is defined for when a get is done in the edit methods
	}
	
	@Nested
	@DisplayName("Test of add visit methods")
	class AddVisitMethods{
		@Test
		public void addVisitTest() {  // design id = 1
	
			Visit visit = new Visit();
			visit.setInstitutioncampus(campus);
			visit.setVisitEntrancedate(new Date()); // entrance date mandatory
			visit.setVisitDetail("Invitación a Icesi interactiva"); // detail with more than 5 letters and exit date optional
			
			
			
			when(visitDao.save(visit)).thenReturn(visit);
			assertDoesNotThrow(()-> visitService.addVisit(visit, idPerson, idCampus));   // the assertDoesNotThrow is to check that no exceptions are thrown when everything is correct
			verify(visitDao).save(visit);
			verifyNoMoreInteractions(visitDao);
		}
		@Test
		public void addVisitWhithouPersonTest() {  // design id = 2
	
			Visit visit = new Visit();
			visit.setInstitutioncampus(campus);
			visit.setVisitEntrancedate(new Date()); // entrance date mandatory
			visit.setVisitDetail("Invitación a Icesi interactiva"); // detail with more than 5 letters and the exit date is optional
			
			
			
			when(visitDao.save(visit)).thenReturn(visit);
			assertThrows(VisitException.class, ()-> visitService.addVisit(visit,idPerson-5,  idCampus));  // person with id idPerson-5 does not exist
	
		
			verify(visitDao, times(0)).save(visit); //verify that the save method was never called
		}
		@Test
		public void addVisitWhithouCampus() {  // design id = 3
	
			Visit visit = new Visit();
			visit.setPerson(person);
			visit.setVisitEntrancedate(new Date()); // entrance date mandatory
			visit.setVisitDetail("Invitación a Icesi interactiva"); // detail with more than 5 letters and the exit date is optional
			
			assertThrows(VisitException.class, ()->visitService.addVisit(visit, idPerson,  idCampus-5)); // campus with id idCampus-5 does not exist
			
			verify(visitDao, times(0)).save(visit); //verify that the save method was never called
		}
		@Test
		public void addNullVisitTest() {   // design id = 4
	
			Visit visit = null;
			when(visitDao.save(visit)).thenReturn(visit);
		    assertThrows(VisitException.class, ()-> visitService.addVisit(visit,idPerson,  idCampus));   
			
			verify(visitDao, times(0)).save(visit); //verify that the save method was never called
		}
		
		@Test
		public void addVisitWithoutEntranceDateTest() { // design id = 5
	
			Visit visit = new Visit();
			visit.setInstitutioncampus(campus);
			visit.setVisitDetail("Invitación a Icesi interactiva"); // detail with more than 5 letters
			visit.setVisitExitdate(new Date()); //  exit date optional
			
			when(visitDao.save(visit)).thenReturn(visit);
			
			
			assertThrows(VisitException.class, 
	        		()-> visitService.addVisit(visit,idPerson, idCampus)
			);
			
			verify(visitDao, times(0)).save(visit); //verify that the save method was never called
		}
		
		@Test
		public void addVisitWithIncorrectDetailTest() { // design id = 6
	
			Visit visit = new Visit();
			visit.setInstitutioncampus(campus);
			visit.setVisitEntrancedate(new Date()); // entrance date mandatory
			visit.setVisitDetail("Inv"); // detail only with 3 letters
			visit.setVisitExitdate(new Date()); //  exit date optional
			
			when(visitDao.save(visit)).thenReturn(visit);
			
			
			assertThrows(VisitException.class, 
	        		()-> visitService.addVisit(visit,idPerson, idCampus)
			);
			
			verify(visitDao, times(0)).save(visit); //verify that the save method was never called
		}
		
		
	}
	@Nested
	@DisplayName("Test of edit visit methods")
	class EditVisitMethods{
		@Test
		public void editVisitTest() { // design id = 7
					
			Visit visitToUpdate = visitService.getVisit(idVisitTest);
			verify(visitDao).findById(idVisitTest);
			
			visitToUpdate.setVisitEntrancedate(new Date());
			visitToUpdate.setVisitDetail("Reunion");
			visitToUpdate.setVisitExitdate(new Date());
			
			when(visitDao.update(visitToUpdate)).thenReturn(visitToUpdate);
			assertDoesNotThrow(()-> visitService.editVisit(visitToUpdate));
			
			verify(visitDao).update(visitToUpdate);
		}
		@Test
		public void editVisitWhithouPersonTest() {   // design id = 8
			
			Visit visitToUpdate = visitService.getVisit(idVisitTest);
			verify(visitDao).findById(idVisitTest);
		
			Person person2 = new Person();
			person2.setPersId(55);
			visitToUpdate.setPerson(person2); // the person has not been added to the database
			
			when(visitDao.update(visitToUpdate)).thenReturn(visitToUpdate);
			assertThrows(VisitException.class, ()-> visitService.editVisit(visitToUpdate));
			
			visitToUpdate.setPerson(null); // the person is null
			assertThrows(VisitException.class, ()-> visitService.editVisit(visitToUpdate));
			
			verify(visitDao, times(0)).update(visitToUpdate); //verify that the save method was never called
		}
		@Test
		public void editVisitWhithouCampusTest() {   // design id = 9
			
			Visit visitToUpdate = visitService.getVisit(idVisitTest);
			verify(visitDao).findById(idVisitTest);
		
			Institutioncampus campus2 = new Institutioncampus();
			campus2.setInstcamId(55);
			visitToUpdate.setInstitutioncampus(campus2); // the campus has not been added to the database
			
			when(visitDao.update(visitToUpdate)).thenReturn(visitToUpdate);
			assertThrows(VisitException.class, ()-> visitService.editVisit(visitToUpdate));
			
			visitToUpdate.setInstitutioncampus(null); // the campus is null
			assertThrows(VisitException.class, ()-> visitService.editVisit(visitToUpdate));
			
			verify(visitDao, times(0)).update(visitToUpdate); //verify that the save method was never called
		}
		@Test
		public void editNullVisitTest() {   // design id = 10
			
			Visit visitToUpdate = visitService.getVisit(idVisitTest);
			verify(visitDao).findById(idVisitTest);
			
			
			when(visitDao.update(visitToUpdate)).thenReturn(visitToUpdate);
			assertThrows(VisitException.class, ()-> visitService.editVisit(null));
			
			verify(visitDao, times(0)).update(visitToUpdate); //verify that the save method was never called
		}
		@Test
		public void editVisitWithoutEntranceDateTest() { // design id = 11
	
			Visit visitToUpdate = visitService.getVisit(idVisitTest);
			verify(visitDao).findById(idVisitTest);
			
			visitToUpdate.setVisitEntrancedate(null); // entrance date null
			visitToUpdate.setVisitDetail("Reunion");
			
			when(visitDao.update(visitToUpdate)).thenReturn(visitToUpdate);
			assertThrows(VisitException.class, ()-> visitService.editVisit(visitToUpdate));
			
			verify(visitDao, times(0)).update(visitToUpdate); //verify that the save method was never called
			
		}
		@Test
		public void editVisitWithIncorrectDetailTest() {    // design id = 12
			Visit visitToUpdate = visitService.getVisit(idVisitTest);
			verify(visitDao).findById(idVisitTest);
			
			visitToUpdate.setVisitDetail("Reu"); // detail only with 3 letters
			visitToUpdate.setVisitExitdate(new Date());
			
			
			when(visitDao.update(visitToUpdate)).thenReturn(visitToUpdate);
			assertThrows(VisitException.class, ()-> visitService.editVisit(visitToUpdate));
			
			verify(visitDao, times(0)).update(visitToUpdate); //verify that the save method was never called
		}
	}
}
