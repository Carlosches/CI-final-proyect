package co.edu.icesi.ci.tallerfinal.back.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;

@Repository
@Scope("singleton")
public class PhysicalcheckupDaoImpl implements PhysicalcheckupDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<Physicalcheckup> findByVisitId(long id) {
		String jpql = "select p from Physicalcheckup p where p.visit.visitId = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	@Override
	public List<Physicalcheckup> findByDate(Date date) {
		String jpql = "select p from Physicalcheckup p where p.phycheDate = :date";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("date", date);
		return query.getResultList();
	}
	@Override
	@Transactional
	public Physicalcheckup save(Physicalcheckup phycheckup) {
		entityManager.persist(phycheckup);
		return phycheckup;
		
	}
	@Override
	public Physicalcheckup update(Physicalcheckup phycheckup) {
		entityManager.merge(phycheckup);
		return phycheckup;
	}
	@Override
	public void delete(Physicalcheckup phycheckup) {
		entityManager.remove(phycheckup);
		
	}
	@Override
	public List<Physicalcheckup> findAll() {
		String jpql = "Select a from Physicalcheckup a";
		return 	entityManager.createQuery(jpql).getResultList();
	}
	@Override
	public Physicalcheckup findById(long id) {
		String jpql = "select p from Physicalcheckup p where p.phycheId=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Physicalcheckup phy = null;
		try {
			phy = (Physicalcheckup) query.getSingleResult();
		}catch(NoResultException e) {
			
		}
		return phy;
	}
}
