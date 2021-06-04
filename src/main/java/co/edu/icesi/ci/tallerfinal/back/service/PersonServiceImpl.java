package co.edu.icesi.ci.tallerfinal.back.service;

import java.util.List;

import co.edu.icesi.ci.tallerfinal.back.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.tallerfinal.back.model.Person;
import co.edu.icesi.ci.tallerfinal.back.model.PersonRole;

@Service
public class PersonServiceImpl implements PersonService{
	
	private PersonRepository personRepo;
	
	
	public PersonServiceImpl(PersonRepository personRepo) {
		this.personRepo = personRepo;
	}

	@Transactional
	@Override
	public List<PersonRole> getPersonRoles(Person person) {
		
		return personRepo.findById(person.getPersId()).get().getPersonRoles();
	}
	
	@Override
	public void save(Person person) {
		personRepo.save(person);
		
	}

}
