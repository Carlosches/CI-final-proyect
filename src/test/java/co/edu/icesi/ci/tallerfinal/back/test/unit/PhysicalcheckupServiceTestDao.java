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

import co.edu.icesi.ci.tallerfinal.back.dao.PhysicalcheckupDao;
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

import co.edu.icesi.ci.tallerfinal.back.exceptions.PhysicalcheckupException;
import co.edu.icesi.ci.tallerfinal.back.model.Person;
import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;
import co.edu.icesi.ci.tallerfinal.back.repositories.PersonRepository;
import co.edu.icesi.ci.tallerfinal.back.service.PhysicalcheckupService;
import co.edu.icesi.ci.tallerfinal.back.service.PhysicalcheckupServiceImpl;

@ContextConfiguration(classes= {PersistenceContext.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PhysicalcheckupServiceTestDao {
	
	@InjectMocks
	private static PhysicalcheckupService physicalcheckupService; 
	
	@Mock
	private static PhysicalcheckupDao physicalcheckupDao;
	@Mock
	private static PersonRepository personRepository;
	@Mock
	private static VisitDao visitDao;
	
 
	private static Person person;
	private static Visit visit;
	private static Physicalcheckup physicalcheckupTest; // object to test the edit methods
	
	private static long idPerson;
	private static long idVisit;
	private static long idPhysicalcheckupTest;
	
		
	@BeforeAll
	public static void setUp() {
		physicalcheckupService = new PhysicalcheckupServiceImpl(physicalcheckupDao, personRepository, visitDao);
		person = new Person();
		visit = new Visit();
		idPerson = person.getPersId();
		idVisit = visit.getVisitId();
		
		physicalcheckupTest = new Physicalcheckup();
		physicalcheckupTest.setVisit(visit);
		physicalcheckupTest.setPerson(person);
		physicalcheckupTest.setPhycheDate(new Date()); //date mandatory
		
		idPhysicalcheckupTest = physicalcheckupTest.getPhycheId();
	}
	@BeforeEach
	public  void init() {
		
		Optional<Person> optionalPerson = Optional.of(person);   
		when(personRepository.findById(idPerson)).thenReturn(optionalPerson); //the interaction is defined for when a get of person is done in the edit methods
		
	
		when(visitDao.findById(idVisit)).thenReturn(visit); //the interaction is defined for when a get of visit is done in the edit methods
		
 
		when(physicalcheckupDao.findById(idPhysicalcheckupTest)).thenReturn(physicalcheckupTest); //the interaction is defined for when a get of physicalcheckup is done in the edit methods
	}
	@Nested
	@DisplayName("Test of add Physicalcheckup methods")
	class AddPhysicalcheckupMethods{  
		
		@Test
		public void addPhysicalcheckupTest() {    // design id = 13
	
			Physicalcheckup checkup = new Physicalcheckup();
			checkup.setPhycheDate(new Date()); //date mandatory 
			
			when(physicalcheckupDao.save(checkup)).thenReturn(checkup);
			assertDoesNotThrow(()-> physicalcheckupService.addPhysicalcheckup(checkup,idPerson,idVisit));   // the assertDoesNotThrow is to check that no exceptions are thrown when everything is correct
	
			
			verify(physicalcheckupDao).save(checkup);
			verifyNoMoreInteractions(physicalcheckupDao);
		}
	
		@Test
		public void addNullPhysicalcheckupTest() {  // design id = 14
	
			Physicalcheckup checkup = null;
			when(physicalcheckupDao.save(checkup)).thenReturn(checkup);
			assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.addPhysicalcheckup(checkup,idPerson,idVisit));  
	
			
			verify(physicalcheckupDao, times(0)).save(checkup); //verify that the save method was never called
		}
		@Test
		public void addPhysicalcheckupWhitoutDateTest() {   // design id = 15
	
			Physicalcheckup checkup = new Physicalcheckup();
			checkup.setPhycheDate(null); //date mandatory is null
			
			when(physicalcheckupDao.save(checkup)).thenReturn(checkup);
			assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.addPhysicalcheckup(checkup,idPerson,idVisit)); 
			
			verify(physicalcheckupDao, times(0)).save(checkup); //verify that the save method was never called
		}
		
		@Test
		public void addPhysicalcheckupWhitoutPersonTest() {  // design id = 16
	
			Physicalcheckup checkup = new Physicalcheckup();
			checkup.setPhycheDate(new Date()); //date mandatory 
			
			when(physicalcheckupDao.save(checkup)).thenReturn(checkup);
			assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.addPhysicalcheckup(checkup,idPerson-5,idVisit));  //person with id idPerson-5 does not exist
	
			
			verify(physicalcheckupDao, times(0)).save(checkup); //verify that the save method was never called
		}
		
		@Test
		public void addPhysicalcheckupWhitoutVisitTest() {  // design id = 17
	
			Physicalcheckup checkup = new Physicalcheckup();
			checkup.setPhycheDate(new Date()); //date mandatory 
			
			when(physicalcheckupDao.save(checkup)).thenReturn(checkup);
			assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.addPhysicalcheckup(checkup,idPerson,idVisit-5));//visit with id idVisit-5 does not exist
	
			
			verify(physicalcheckupDao, times(0)).save(checkup); //verify that the save method was never called
		}
	}
	@Nested
	@DisplayName("Test of edit Physicalcheckup methods")
	class EditPhysicalcheckupMethods{
		@Test
		public void editPhysicalcheckupTest() { // design id = 18
	
			
			Physicalcheckup checkupToUpdate = physicalcheckupService.getPhysicalcheckup(idPhysicalcheckupTest);
			verify(physicalcheckupDao).findById(idPhysicalcheckupTest);
			
			
			Person person2 = new Person();
			person2.setPersId(4);
			Optional<Person> optionalPerson2 = Optional.of(person2);
			when(personRepository.findById((long) 4)).thenReturn(optionalPerson2);
			
			Visit visit2 = new Visit();
			visit2.setVisitId(4);
			when(visitDao.findById((long) 4)).thenReturn(visit2);
			
			checkupToUpdate.setPhycheDate(new Date());
			checkupToUpdate.setPerson(person2);
			checkupToUpdate.setVisit(visit2);
			when(physicalcheckupDao.update(checkupToUpdate)).thenReturn(checkupToUpdate);
			assertDoesNotThrow(()-> physicalcheckupService.editPhysicalcheckup(checkupToUpdate));
			
			verify(physicalcheckupDao).update(checkupToUpdate);
		}
	
		@Test
		public void editNullPhysicalcheckupTest() {  // design id = 19
			
			Physicalcheckup checkupToUpdate = physicalcheckupService.getPhysicalcheckup(idPhysicalcheckupTest);
			verify(physicalcheckupDao).findById(idPhysicalcheckupTest);

			
			when(physicalcheckupDao.update(checkupToUpdate)).thenReturn(checkupToUpdate);
			assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.editPhysicalcheckup(null));
			
			verify(physicalcheckupDao, times(0)).update(checkupToUpdate); //verify that the save method was never called
		}
		@Test
		public void editPhysicalcheckupWithoutDateTest() { // design id = 20
			
			Physicalcheckup checkupToUpdate = physicalcheckupService.getPhysicalcheckup(idPhysicalcheckupTest);
			verify(physicalcheckupDao).findById(idPhysicalcheckupTest);
			
			checkupToUpdate.setPhycheDate(null); // date mandatory is null

			when(physicalcheckupDao.update(checkupToUpdate)).thenReturn(checkupToUpdate);
			assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.editPhysicalcheckup(checkupToUpdate));
			
			verify(physicalcheckupDao, times(0)).update(checkupToUpdate); //verify that the save method was never called
			
		}
		@Test
		public void editPhysicalcheckupWithoutPersonTest() {   // design id = 21
		
			Physicalcheckup checkupToUpdate = physicalcheckupService.getPhysicalcheckup(idPhysicalcheckupTest);
			verify(physicalcheckupDao).findById(idPhysicalcheckupTest);
			
			Person person2 = new Person();
			person2.setPersId(2);
			checkupToUpdate.setPerson(person2); // the person has not been added to the database

			when(physicalcheckupDao.update(checkupToUpdate)).thenReturn(checkupToUpdate);
			assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.editPhysicalcheckup(checkupToUpdate));
			
			
			checkupToUpdate.setPerson(null); // the person is null

			when(physicalcheckupDao.update(checkupToUpdate)).thenReturn(checkupToUpdate);
			assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.editPhysicalcheckup(checkupToUpdate));
			
			verify(physicalcheckupDao, times(0)).update(checkupToUpdate); //verify that the save method was never called
			
		}
		@Test
		public void editPhysicalcheckupWithoutVisitTest() {  // design id = 22
		
			Physicalcheckup checkupToUpdate = physicalcheckupService.getPhysicalcheckup(idPhysicalcheckupTest);
			verify(physicalcheckupDao).findById(idPhysicalcheckupTest);
			
			Visit visit2 = new Visit();
			visit2.setVisitId(2);
			checkupToUpdate.setVisit(visit2); // the visit has not been added to the database

			when(physicalcheckupDao.update(checkupToUpdate)).thenReturn(checkupToUpdate);
			assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.editPhysicalcheckup(checkupToUpdate));
			
			
			checkupToUpdate.setVisit(null); // the visit is null

			when(physicalcheckupDao.save(checkupToUpdate)).thenReturn(checkupToUpdate);
			assertThrows(PhysicalcheckupException.class, ()-> physicalcheckupService.editPhysicalcheckup(checkupToUpdate));
			
			verify(physicalcheckupDao, times(0)).update(checkupToUpdate); //verify that the save method was never called
			
		}
	}
}
