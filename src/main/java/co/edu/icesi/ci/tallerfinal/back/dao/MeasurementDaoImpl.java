package co.edu.icesi.ci.tallerfinal.back.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.tallerfinal.back.model.Measurement;

@Repository
@Scope("singleton")
public class MeasurementDaoImpl implements MeasurementDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Measurement> findByValue(BigDecimal value) {
		String jpql = "select m from Measurement m where :value between m.measMinthreshold and m.measMaxthreshold";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("value", value);
		return query.getResultList();
	}

	@Override
	public List<Measurement> findByDescription(String description) {
		String jpql = "select m from Measurement m where m.measDescription=:description";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("description", description);
		return query.getResultList();
	}

	@Override
	@Transactional
	public Measurement save(Measurement measurement) {
		entityManager.persist(measurement);
		return measurement;
		
	}
	
	@Override
	public Measurement update(Measurement measurement) {
		entityManager.merge(measurement);
		return measurement;
	}
	
	@Override
	public void delete(Measurement measurement) {
		entityManager.remove(measurement);
		
	}
	
	@Override
	public List<Measurement> findAll() {
		String jpql = "SELECT a FROM Measurement a";
		return entityManager.createQuery(jpql).getResultList();
	}
	public Measurement findById(long id) {
		String jpql = "select m from Measurement m where m.measId=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Measurement meas = null;
		try {
			meas =  (Measurement) query.getSingleResult();
		}catch(NoResultException e) {
			
		}
		return meas;
	}
	
}
