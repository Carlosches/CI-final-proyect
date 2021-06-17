package co.edu.icesi.ci.tallerfinal.back.service;

import java.util.Date;
import java.util.List;

import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;

public interface PhysicalcheckupService {
	
	public void addPhysicalcheckup(Physicalcheckup physicalcheckup, long personId, long visitId);
	public void editPhysicalcheckup(Physicalcheckup physicalcheckup);
	public Physicalcheckup getPhysicalcheckup(long id);
	public Iterable<Physicalcheckup> findAll();
	public void delete(Physicalcheckup phycheckuo);
	public List<Physicalcheckup> findByVisitId(long id);
	public List<Physicalcheckup> findByDate(Date date);
}
