package co.edu.icesi.ci.tallerfinal.back.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.tallerfinal.back.model.Measurement;

@Repository
public interface MeasurementRepository extends CrudRepository<Measurement,Long>{

}
