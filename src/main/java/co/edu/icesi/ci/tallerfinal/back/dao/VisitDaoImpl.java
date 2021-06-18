package co.edu.icesi.ci.tallerfinal.back.dao;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import co.edu.icesi.ci.tallerfinal.back.model.Person;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;

@Repository
@Scope("singleton")
public class VisitDaoImpl implements VisitDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Visit> findByPersonId(long persId) {
		String jpql = "select v from Visit v where v.person.persId = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", persId);
		return query.getResultList();
	}
	
	@Override
	public List<Visit> findByEntrancedate(Date entranceDate) {
		String jpql = "select v from Visit v where v.visitEntrancedate = :date";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("date", entranceDate);
		return query.getResultList();
	}
	
	@Override
	public  List<Visit> findByExitdate(Date exitDate) {
		String jpql = "select v from Visit v where v.visitExitdate = :date";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("date", exitDate);
		return query.getResultList();
	}
	// map  --->  { person, number of visits}
	@Override
	public List<Person> findPersonsByVisitDate(Date entranceDate, Date exitDate) {
		//Map<Person, Long> map = new HashMap<>();
		List<Person> list = new ArrayList<>();
		String jpql = "select v.person, (select count(*) from v.person.visits) from Visit v where v.visitEntrancedate between :entranceDate and :exitDate ORDER BY v.visitEntrancedate";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("entranceDate", entranceDate);
		query.setParameter("exitDate", exitDate);
		
		List<Object[]> result = query.getResultList();
		
		for(Object[] obj: result) {
			Person person =(Person) obj[0];
			long cant = (long) obj[1];
			
		//	map.put(person, cant);
			person.setVisitsQuantity(person.getVisits().size());
			list.add(person);
		}
		return list;
	}
	
	@Override
	public  List<Visit> findVisitsWihtFewerTwoPhy(Date date) {
		String jpql = "select v from Visit v where v.visitEntrancedate=:date and (select count(*) from v.physicalcheckups) <2 ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("date", date);
		return query.getResultList();
	}
	@Override
	public Visit save(Visit visit) {
		entityManager.persist(visit);
		return visit;
	}
	
	@Override
	public Visit update(Visit visit) {
		entityManager.merge(visit);
		return visit;
	}
	@Override
	public void delete(Visit visit) {
		entityManager.remove(visit);
	}
	
	@Override
	public List<Visit> findAll() {
		String jpql = "Select a from Visit a";
		return 	entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public Visit findById(long id) {
		String jpql = "select v from Visit v where v.visitId=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Visit visit = null;
		try {
			visit = (Visit) query.getSingleResult();
		}catch(NoResultException e) {
			
		}
		return visit;
	}
}
