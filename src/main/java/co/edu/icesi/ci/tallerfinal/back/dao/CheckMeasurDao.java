package co.edu.icesi.ci.tallerfinal.back.dao;

import java.util.List;

import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasurPK;

public interface CheckMeasurDao {
	public CheckMeasur save(CheckMeasur checkMeasur);
	public CheckMeasur update(CheckMeasur checkMeasur);
	public void delete(CheckMeasur checkMeasur);
	public List<CheckMeasur> findAll();
	public CheckMeasur findById(CheckMeasurPK id);
	public List<CheckMeasur> findByPycheId(long pycheId);
	public List<CheckMeasur> findByMeasId(long measId);
}
