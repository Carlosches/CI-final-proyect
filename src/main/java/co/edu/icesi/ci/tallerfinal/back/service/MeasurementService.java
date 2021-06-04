package co.edu.icesi.ci.tallerfinal.back.service;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.ci.tallerfinal.back.model.Measurement;

public interface MeasurementService {
	
	public void addMeasurement(Measurement measurement, long institutionID);
	public void editMeasurement(Measurement measurement);
	public Measurement getMeasurement(long id);
	public Iterable<Measurement> findAll();
	public void delete(Measurement measure);
	public  List<Measurement> findByValue(BigDecimal value);
	public List<Measurement> findByDescription(String description);
}
