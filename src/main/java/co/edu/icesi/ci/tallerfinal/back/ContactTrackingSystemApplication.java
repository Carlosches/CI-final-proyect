package co.edu.icesi.ci.tallerfinal.back;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.icesi.ci.tallerfinal.back.dao.MeasurementDao;
import co.edu.icesi.ci.tallerfinal.back.dao.PhysicalcheckupDao;
import co.edu.icesi.ci.tallerfinal.back.dao.VisitDao;
import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegateImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasurPK;
import co.edu.icesi.ci.tallerfinal.back.model.Institution;
import co.edu.icesi.ci.tallerfinal.back.model.Institutioncampus;
import co.edu.icesi.ci.tallerfinal.back.model.Measurement;
import co.edu.icesi.ci.tallerfinal.back.model.Person;
import co.edu.icesi.ci.tallerfinal.back.model.PersonRole;
import co.edu.icesi.ci.tallerfinal.back.model.PersonRolePK;
import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.back.model.Rolee;
import co.edu.icesi.ci.tallerfinal.back.model.Userr;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;
import co.edu.icesi.ci.tallerfinal.back.repositories.CampusRepository;
import co.edu.icesi.ci.tallerfinal.back.repositories.InstitutionRepository;
import co.edu.icesi.ci.tallerfinal.back.repositories.PersonRoleRepository;
import co.edu.icesi.ci.tallerfinal.back.repositories.RoleRepository;
import co.edu.icesi.ci.tallerfinal.back.service.CheckMeasurService;
import co.edu.icesi.ci.tallerfinal.back.service.MeasurementService;
import co.edu.icesi.ci.tallerfinal.back.service.PersonService;
import co.edu.icesi.ci.tallerfinal.back.service.PhysicalcheckupService;
import co.edu.icesi.ci.tallerfinal.back.service.UserService;
import co.edu.icesi.ci.tallerfinal.back.service.VisitService;


@SpringBootApplication
@ComponentScan("co.edu.icesi.ci.tallerfinal")
public class ContactTrackingSystemApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(ContactTrackingSystemApplication.class, args);


		BusinessDelegateImpl bd = ac.getBean(BusinessDelegateImpl.class);
		//List<co.edu.icesi.ci.tallerfinal.front.model.classes.Visit> visits = bd.visitFindAll();

		List<co.edu.icesi.ci.tallerfinal.front.model.classes.Visit> v = bd.visitFindAll();

/*		co.edu.icesi.ci.tallerfinal.front.model.classes.Visit vv = bd.saveVisit(new co.edu.icesi.ci.tallerfinal.front.model.classes.Visit(), 1, 1);
		System.out.println();*/

	}
	
	@Bean
	public CommandLineRunner dummy(UserService userService, CampusRepository campusRepository, PersonService personService,
								   InstitutionRepository institutionRepository, VisitService visitService, MeasurementService measurService,
								   PhysicalcheckupService phyService, CheckMeasurService checkMeasurService, RoleRepository roleRepo,
								   PersonRoleRepository personRoleRepo, VisitDao visitDao, MeasurementDao measurementDao, PhysicalcheckupDao phycheckupDao) {

		return (args) -> {
			Rolee adminRole = new Rolee();
			adminRole.setRoleName("admin");
			roleRepo.save(adminRole);
			
			PersonRole pr1 = new PersonRole();
			PersonRolePK pk1 = new PersonRolePK();
			pr1.setId(pk1);
			pr1.setRolee(adminRole);
			
			personRoleRepo.save(pr1);
			
			Person person1 = new Person();
			person1.setPersName("person 1");
			List<PersonRole> rolesList = new ArrayList<PersonRole>();			
			rolesList.add(pr1);
			person1.setPersonRoles(rolesList);
			
			personService.save(person1);
			Userr user1 = new Userr();
			user1.setUserName("admin");
			user1.setUserPassword("{noop}1234");
			user1.setPerson(person1);
			user1.setRole("admin");
			userService.save(user1);
		
			
			Rolee opRole = new Rolee();
			opRole.setRoleName("operator");
			roleRepo.save(opRole);
			
			PersonRole pr2 = new PersonRole();
			PersonRolePK pk2 = new PersonRolePK();
			pr2.setId(pk2);
			pr2.setRolee(opRole);
			personRoleRepo.save(pr2);
			
			List<PersonRole> rolesList2 = new ArrayList<PersonRole>();			
			rolesList2.add(pr2);
			Person person2 = new Person();
			person2.setPersName("person 2");
			person2.setPersonRoles(rolesList2);
			personService.save(person2);
			
			Userr user2 = new Userr();
			user2.setUserName("ope");
			user2.setUserPassword("{noop}1234");
			user2.setRole("operator");
			user2.setPerson(person2);
			userService.save(user2);

			
			
			Institutioncampus camp1 = new Institutioncampus();
			camp1.setInstcamName("Campus 1");
			campusRepository.save(camp1);
			Person person3 = new Person();
			person3.setPersName("person 3");
			personService.save(person3);
			
			Institution institution = new Institution();
			institution.setInstName("Institution1");
			List<Institutioncampus> listCampus = new ArrayList<>();
			institution.setInstitutioncampuses(listCampus);
			institution.getInstitutioncampuses().add(camp1);
			institutionRepository.save(institution);
			
			
			Visit visit = new Visit();
			//visit.setInstitutioncampus(camp1);
			//visit.setPerson(person1);
			visit.setVisitDetail("visita 1");
			visit.setVisitEntrancedate(new Date());
			visit.setVisitExitdate(new Date());
			visitService.addVisit(visit, person1.getPersId(), camp1.getInstcamId());
			//visitDao.save(visit);
			//System.out.println("by person id: "+ visitDao.findByPersonId(visit.getPerson().getPersId()).get(0).getVisitDetail());
			//System.out.println("by entrance date: "+ visitDao.findByEntrancedate(visit.getVisitEntrancedate()).get(0).getVisitDetail());
			//System.out.println("by exit date: "+ visitDao.findByExitdate(visit.getVisitExitdate()).get(0).getVisitDetail());
			//List<Person> persons = visitDao.findPersonsByVisitDate(visit.getVisitEntrancedate(), visit.getVisitExitdate());
			//System.out.println(persons.get(0).getPersName());
			
			
			Measurement measur = new Measurement();
			measur.setInstitution(institution);
			measur.setMeasName("meas 1");
			measur.setMeasDescription("description 1");
			measur.setMeasMaxthreshold(new BigDecimal("1000"));
			measur.setMeasMinthreshold(new BigDecimal("100"));
			measur.setMeasUnit("unit 1");
			measurService.addMeasurement(measur, institution.getInstId());
			//measurementDao.save(measur);
			//System.out.println("measur description: "+ measurementDao.findByValue(new BigDecimal("150")).get(0).getMeasDescription());
			//System.out.println("measur description: "+ measurementDao.findByDescription(measur.getMeasDescription()).getMeasDescription());
			
			Physicalcheckup phy = new Physicalcheckup();
			phy.setPerson(person1);
			phy.setVisit(visit);
			phy.setPhycheDate(new Date());
			phyService.addPhysicalcheckup(phy, person1.getPersId(), visit.getVisitId());
			//phycheckupDao.save(phy);
			//System.out.println("phy by visit id: "+ phycheckupDao.findByVisitId(phy.getVisit().getVisitId()).getPhycheId());
			//System.out.println("phy by date: "+ phycheckupDao.findByDate(phy.getPhycheDate()).get(0).getPhycheId());
			
			Physicalcheckup phy2= new Physicalcheckup();
			phy2.setPerson(person1);
			phy2.setVisit(visit);
			phy2.setPhycheDate(new Date());
			phyService.addPhysicalcheckup(phy2, person1.getPersId(), visit.getVisitId());
			
			ArrayList<Physicalcheckup> phys = new ArrayList<>();
			phys.add(phy);
			phys.add(phy2);
			visit.setPhysicalcheckups(phys);
			//System.out.println(visit.getPhysicalcheckups().size());
			//visitService.editVisit(visit);
	
			//visitDao.update(visit);
			
			//System.out.println("by person id: "+ visitDao.findByPersonId(visit.getPerson().getPersId()).get(0).getVisitDetail());	
			//System.out.println("by less two phy: "+ visitDao.findVisitsWihtFewerTwoPhy(visit.getVisitEntrancedate()).get(0).getVisitDetail());
			
			CheckMeasur checkMeasur = new CheckMeasur();
			CheckMeasurPK checkMeasurPK = new CheckMeasurPK();
			checkMeasurPK.setMeasMeasId(measur.getMeasId());
			checkMeasurPK.setPhychePhycheId(phy.getPhycheId());
			checkMeasur.setId(checkMeasurPK);
			checkMeasur.setMeasurement(measur);
			checkMeasur.setMeasvalue(new BigDecimal("500"));
			checkMeasur.setPhysicalcheckup(phy);
			
			checkMeasurService.addCheckMeasur(checkMeasur, measur.getMeasId(), phy.getPhycheId());
			
			
			
		};

	}

}
