package co.edu.icesi.ci.tallerfinal.back.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.tallerfinal.back.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long>{
	

}
