package co.edu.icesi.ci.tallerfinal.back.dao;

import java.util.Date;
import java.util.List;

import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;


public interface PhysicalcheckupDao {
	
	public Physicalcheckup findByVisitId(long id);
	public List<Physicalcheckup> findByDate(Date date);
	
	public Physicalcheckup save(Physicalcheckup phycheckup);
	public Physicalcheckup update(Physicalcheckup phycheckup);
	public void delete(Physicalcheckup phycheckup);
	public List<Physicalcheckup> findAll();
	public Physicalcheckup findById(long id);
}
