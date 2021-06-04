package co.edu.icesi.ci.tallerfinal.back.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;

@Repository
public interface PhysicalcheckupRepository extends CrudRepository<Physicalcheckup,Long>{

}
