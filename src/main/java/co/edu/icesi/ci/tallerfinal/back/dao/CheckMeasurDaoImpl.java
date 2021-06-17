package co.edu.icesi.ci.tallerfinal.back.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasurPK;

@Repository
@Scope("singleton")
public class CheckMeasurDaoImpl implements CheckMeasurDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public CheckMeasur save(CheckMeasur checkMeasur) {
		entityManager.persist(checkMeasur);
		return checkMeasur;
	}

	@Override
	public CheckMeasur update(CheckMeasur checkMeasur) {
		entityManager.merge(checkMeasur);
		return checkMeasur;
	}

	@Override
	public void delete(CheckMeasur checkMeasur) {
		entityManager.remove(checkMeasur);
	}

	@Override
	public List<CheckMeasur> findAll() {
		String jpql = "SELECT a FROM CheckMeasur a";
		return entityManager.createQuery(jpql).getResultList();
	}


	@Override
	public CheckMeasur findById(CheckMeasurPK id) {
		String jpql = "select c from CheckMeasur c where c.id=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		CheckMeasur ch = null;
		try {
			ch = (CheckMeasur) query.getSingleResult();
		}catch(NoResultException e) {
			
		}
		return ch;
	}
	@Override
	public List<CheckMeasur> findByPycheId(long pycheId){
		String jpql ="select c from CheckMeasur c where c.physicalcheckup.phycheId=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id",pycheId);
		return query.getResultList();
	}

	@Override
	public List<CheckMeasur> findByMeasId(long measId) {
		String jpql ="select c from CheckMeasur c where c.measurement.measId=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id",measId);
		return query.getResultList();
	}
}
