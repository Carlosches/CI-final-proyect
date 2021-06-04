package co.edu.icesi.ci.tallerfinal.back.service;

import java.util.List;

import co.edu.icesi.ci.tallerfinal.back.model.Person;
import co.edu.icesi.ci.tallerfinal.back.model.PersonRole;

public interface PersonService {
	
	public List<PersonRole> getPersonRoles(Person person);
	public void save(Person person);
}
