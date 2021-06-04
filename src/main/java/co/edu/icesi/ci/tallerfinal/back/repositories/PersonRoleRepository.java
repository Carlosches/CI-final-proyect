package co.edu.icesi.ci.tallerfinal.back.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.ci.tallerfinal.back.model.PersonRole;
import co.edu.icesi.ci.tallerfinal.back.model.PersonRolePK;


public interface PersonRoleRepository  extends CrudRepository<PersonRole, PersonRolePK>{

}
