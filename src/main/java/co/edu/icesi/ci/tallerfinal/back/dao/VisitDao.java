package co.edu.icesi.ci.tallerfinal.back.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import co.edu.icesi.ci.tallerfinal.back.model.Person;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;

public interface VisitDao {
	
	public List<Visit> findByPersonId(long persId);
	public List<Visit> findByEntrancedate(Date entranceDate);
	public List<Visit> findByExitdate(Date exitDate);
	public Visit save(Visit visit);
	public Visit update(Visit visit);
	public void delete(Visit visit);
	public List<Visit> findAll();
	public List<Person> findPersonsByVisitDate(Date entranceDate, Date exitDate);
	public List<Visit> findVisitsWihtFewerTwoPhy(Date date);
	public Visit findById(long id);
	
	
	
	

}
