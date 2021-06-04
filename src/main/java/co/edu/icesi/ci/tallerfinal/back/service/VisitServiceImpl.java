package co.edu.icesi.ci.tallerfinal.back.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import co.edu.icesi.ci.tallerfinal.back.dao.VisitDao;
import co.edu.icesi.ci.tallerfinal.back.exceptions.VisitException;
import co.edu.icesi.ci.tallerfinal.back.repositories.CampusRepository;
import co.edu.icesi.ci.tallerfinal.back.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.tallerfinal.back.model.Institutioncampus;
import co.edu.icesi.ci.tallerfinal.back.model.Person;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;

@Service
public class VisitServiceImpl implements VisitService{
	
	private VisitDao visitDao;
	private PersonRepository personRepository;
	private CampusRepository campusRepository;
	
	
	public VisitServiceImpl(VisitDao visitDao, PersonRepository personRepository, CampusRepository campusRepository) {
		this.visitDao=visitDao;
		this.personRepository = personRepository;
		this.campusRepository = campusRepository;
	}
	@Transactional
	@Override
	public void addVisit(Visit visit, long personId, long campusId) {
		if(visit == null) {
			throw new VisitException("The visit cannot be null");
		}
		if(visit.getVisitEntrancedate()==null) {
			throw new VisitException("The entrance date cannot be null");
		}
		if(visit.getVisitDetail()==null || visit.getVisitDetail().length() <5) {
			throw new VisitException("The detail must be at least 5 characters long");
		}
		try {
			Person person = personRepository.findById(personId).get();
			visit.setPerson(person);
		}catch(NoSuchElementException e) {
			throw new VisitException("The person to be added to the visit does not exist");
		}
		try {
			Institutioncampus campus = campusRepository.findById(campusId).get();
			visit.setInstitutioncampus(campus);
		}catch(NoSuchElementException e) {
			
			throw new VisitException("The institution campus to be added to the visit does not exist");
		}
		visitDao.save(visit);
		
	}
	@Transactional
	@Override
	public void editVisit(Visit visit) {
		if(visit == null) {
			throw new VisitException("the visit cannot be null");
		}
		if(visit.getVisitEntrancedate()==null) {
			throw new VisitException("the entrance date cannot be null");
		}
		if(visit.getVisitDetail().length() <5) {
			throw new VisitException("the detail must be at least 5 characters long");
		}
		Person person = null;
		try {
			person = personRepository.findById(visit.getPerson().getPersId()).get();
		}catch(NullPointerException e) {
			throw new VisitException("The person to be added to the visit is null");
		}catch(NoSuchElementException e) {
			throw new VisitException("The person to be added to the visit does not exist");
		}
		Institutioncampus campus = null;
		try {
			campus = campusRepository.findById(visit.getInstitutioncampus().getInstcamId()).get();
		}catch(NullPointerException e) {
			throw new VisitException("The campus to be added to the visit is null");
		}catch(NoSuchElementException e) {
			throw new VisitException("The campus to be added to the visit does not exist");
		}
		Visit visitToUpdate = visitDao.findById(visit.getVisitId());
		visitToUpdate.setInstitutioncampus(campus);
		visitToUpdate.setPerson(person);
		visitToUpdate.setPhysicalcheckups(visit.getPhysicalcheckups());
		visitToUpdate.setVisitDetail(visit.getVisitDetail());
		visitToUpdate.setVisitEntrancedate(visit.getVisitEntrancedate());
		visitToUpdate.setVisitExitdate(visit.getVisitExitdate());
		visitToUpdate.setVisitId(visit.getVisitId());
		visitToUpdate.setVisitVisitreasons(visit.getVisitVisitreasons());
			
		visitDao.update(visitToUpdate);
	}

	@Override
	public Visit getVisit(long id) {
		Visit visit = visitDao.findById(id);
		return visit;
	}

	public VisitDao getvisitDao() {
		return visitDao;
	}

	public PersonRepository getPersonRepository() {
		return personRepository;
	}

	@Override
	public Iterable<Visit> findAll() {
		return visitDao.findAll();
	}
	@Transactional
	@Override
	public void delete(Visit visit) {
		visitDao.delete(visit);
		
	}
	@Override
	public List<Visit> findByPersonId(long persId) {
		return visitDao.findByPersonId(persId);
	}
	@Override
	public List<Visit> findByEntrancedate(Date entranceDate) {
		return visitDao.findByEntrancedate(entranceDate);
	}
	@Override
	public List<Visit> findByExitdate(Date exitDate) {
		
		return visitDao.findByExitdate(exitDate);
	}
	@Override
	public Map<Person, Long> findPersonsByVisitDate(Date entranceDate, Date exitDate) {
		
		return visitDao.findPersonsByVisitDate(entranceDate,exitDate);
	}
	@Override
	public List<Visit> findVisitsWihtFewerTwoPhy(Date date) {
		
		return visitDao.findVisitsWihtFewerTwoPhy(date);
	}

}
