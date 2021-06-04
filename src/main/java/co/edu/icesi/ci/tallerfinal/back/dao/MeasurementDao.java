package co.edu.icesi.ci.tallerfinal.back.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.ci.tallerfinal.back.model.Measurement;

public interface MeasurementDao {
	
	public  List<Measurement> findByValue(BigDecimal value);
	public List<Measurement> findByDescription(String description);
	public Measurement save(Measurement measurement);
	public Measurement update(Measurement measurement);
	public void delete(Measurement measurement);
	public List<Measurement> findAll();
	public Measurement findById(long id);
}
