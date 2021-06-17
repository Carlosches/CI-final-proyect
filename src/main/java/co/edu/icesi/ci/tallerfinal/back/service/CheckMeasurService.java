package co.edu.icesi.ci.tallerfinal.back.service;

import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasurPK;

import java.util.List;

public interface CheckMeasurService {
	
	public void addCheckMeasur(CheckMeasur checkMeasur,long measurementId, long physicalcheckupId);
	public void editCheckMeasur(CheckMeasur checkMeasur);
	public CheckMeasur getCheckMeasur(CheckMeasurPK id);
	public Iterable<CheckMeasur> findAll();
	public void deleted(CheckMeasur checkMeasur);
	public boolean existById(CheckMeasurPK checkMeasurePK);
	public List<CheckMeasur> findByPycheId(long pycheId);
	public List<CheckMeasur> findByMeasId(long measId);
	CheckMeasur findById(CheckMeasurPK checkMeasurPK);
}
