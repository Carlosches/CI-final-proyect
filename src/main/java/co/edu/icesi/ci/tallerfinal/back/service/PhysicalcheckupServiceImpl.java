package co.edu.icesi.ci.tallerfinal.back.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import co.edu.icesi.ci.tallerfinal.back.dao.PhysicalcheckupDao;
import co.edu.icesi.ci.tallerfinal.back.dao.VisitDao;
import co.edu.icesi.ci.tallerfinal.back.exceptions.PhysicalcheckupException;
import co.edu.icesi.ci.tallerfinal.back.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.tallerfinal.back.model.Person;
import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;

@Service
public class PhysicalcheckupServiceImpl implements PhysicalcheckupService {
	
	private PhysicalcheckupDao physicalcheckupDao;
	private PersonRepository personRepository;
	private VisitDao visitDao;

	@Autowired
	public PhysicalcheckupServiceImpl(PhysicalcheckupDao physicalcheckupDao,
			PersonRepository personRepository, VisitDao visitDao) {
		this.physicalcheckupDao = physicalcheckupDao;
		this.personRepository = personRepository;
		this.visitDao = visitDao;
	}

	@Transactional
	@Override
	public void addPhysicalcheckup(Physicalcheckup physicalcheckup, long idPerson, long idVisit) {
	
		if(physicalcheckup == null) {
			throw new PhysicalcheckupException("the checkup to be added is null");
		}
		if(physicalcheckup.getPhycheDate() == null) {
			throw new PhysicalcheckupException("the physical checkup date is null");
		}
		try {
			Person person = personRepository.findById(idPerson).get();
			physicalcheckup.setPerson(person);
		}catch(NoSuchElementException e) {
			throw new PhysicalcheckupException("the person to be added to the checkup does not exist");
		}
		Visit visit = visitDao.findById(idVisit);
		if(visit==null)
			throw new PhysicalcheckupException("the visit to be added to the checkup does not exist");

		physicalcheckup.setVisit(visit);
		physicalcheckupDao.save(physicalcheckup);
	}

	@Transactional
	@Override
	public void editPhysicalcheckup(Physicalcheckup physicalcheckup) {
		if(physicalcheckup == null) {
			throw new PhysicalcheckupException("the checkup to be updated is null");
		}
		if(physicalcheckup.getPhycheDate() == null) {
			throw new PhysicalcheckupException("the physical checkup date is null");
		}
		Person person = null;
		try {
			 person = personRepository.findById(physicalcheckup.getPerson().getPersId()).get();
		}catch(NullPointerException a) {
			throw new PhysicalcheckupException("the person is null");
		}catch(NoSuchElementException e) {
			throw new PhysicalcheckupException("the person does not exist");
		}
		Visit visit =null;
		try {
			visit = visitDao.findById(physicalcheckup.getVisit().getVisitId());
		}catch(NullPointerException a) {
			throw new PhysicalcheckupException("the visit is null");
		}
		if(visit==null) {
			throw new PhysicalcheckupException("the visit is null");
		}
		Physicalcheckup checkupToUpdate = physicalcheckupDao.findById(physicalcheckup.getPhycheId());
		checkupToUpdate.setCheckMeasurs(physicalcheckup.getCheckMeasurs());
		checkupToUpdate.setPerson(person);
		checkupToUpdate.setVisit(visit);
		checkupToUpdate.setPhycheDate(physicalcheckup.getPhycheDate());
		
		physicalcheckupDao.update(checkupToUpdate);

	}

	@Override
	public Physicalcheckup getPhysicalcheckup(long id) {
		Physicalcheckup physicalcheckup = physicalcheckupDao.findById(id);
		
		return physicalcheckup;
	}

	@Transactional
	@Override
	public Iterable<Physicalcheckup> findAll() {
		return physicalcheckupDao.findAll();
	}

	@Transactional
	@Override
	public void delete(Physicalcheckup phycheckuo) {
		physicalcheckupDao.delete(phycheckuo);
		
	}

	@Override
	public Physicalcheckup findByVisitId(long id) {
		return physicalcheckupDao.findByVisitId(id);
	}

	@Override
	public List<Physicalcheckup> findByDate(Date date) {
		return physicalcheckupDao.findByDate(date);
	}

}
