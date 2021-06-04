package co.edu.icesi.ci.tallerfinal.back.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasurPK;

@Repository
public interface CheckMeasurRepository extends CrudRepository<CheckMeasur,CheckMeasurPK>{
	
}
