package co.edu.icesi.ci.tallerfinal.back.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.tallerfinal.back.model.Userr;

@Repository
public interface UserRepository extends CrudRepository<Userr, Long>{
	
	Userr findByuserName(String username);
}
