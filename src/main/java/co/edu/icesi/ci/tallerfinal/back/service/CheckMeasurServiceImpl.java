package co.edu.icesi.ci.tallerfinal.back.service;

import java.util.List;
import java.util.NoSuchElementException;

import co.edu.icesi.ci.tallerfinal.back.dao.CheckMeasurDao;
import co.edu.icesi.ci.tallerfinal.back.dao.MeasurementDao;
import co.edu.icesi.ci.tallerfinal.back.dao.PhysicalcheckupDao;
import co.edu.icesi.ci.tallerfinal.back.exceptions.CheckMeasurException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasurPK;
import co.edu.icesi.ci.tallerfinal.back.model.Measurement;
import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
@Service
public class CheckMeasurServiceImpl implements CheckMeasurService {
	
	private CheckMeasurDao checkMeasurDao;
	private MeasurementDao measurementDao;
	private PhysicalcheckupDao physicalcheckupDao;




	public CheckMeasurServiceImpl(CheckMeasurDao checkMeasurDao,
								  MeasurementDao measurementDao, PhysicalcheckupDao physicalcheckupDao) {
		this.checkMeasurDao = checkMeasurDao;
		this.measurementDao = measurementDao;
		this.physicalcheckupDao = physicalcheckupDao;
	}
	
	@Transactional
	@Override
	public void addCheckMeasur(CheckMeasur checkMeasur, long measurementId, long physicalcheckupId) {
		if(checkMeasur == null) {
			throw new CheckMeasurException("The check measur to be added is null");
		}
		if(checkMeasur.getMeasvalue()==null || checkMeasur.getMeasvalue().toString().isEmpty()) {
			throw new CheckMeasurException("The measure value is null or is empty");
		}

		Measurement measurement = measurementDao.findById(measurementId);
		if(measurement==null) {
			throw new CheckMeasurException("The measurement does not exist");
		}
		Physicalcheckup physicalcheckup =physicalcheckupDao.findById(physicalcheckupId);
		if(physicalcheckup==null) {
			throw new CheckMeasurException("The physicalcheckup does not exist");
		}
		checkMeasur.setMeasurement(measurement);
		checkMeasur.setPhysicalcheckup(physicalcheckup);
		checkMeasurDao.save(checkMeasur);
	}
	@Transactional
	@Override
	public void editCheckMeasur(CheckMeasur checkMeasur) {
		if(checkMeasur == null) {
			throw new CheckMeasurException("The check measur to be added is null");
		}
		if(checkMeasur.getMeasvalue()==null || checkMeasur.getMeasvalue().toString().isEmpty()) {
			throw new CheckMeasurException("The measure value is null or is empty");
		}
		Measurement measurement = null;
		try{
			measurement = measurementDao.findById(checkMeasur.getMeasurement().getMeasId());
		}catch(NullPointerException e) {
			throw new CheckMeasurException("The measurement to be added is null");
		}catch(NoSuchElementException e) {
			throw new CheckMeasurException("The measurement does not exist");
		}
		if(measurement==null) {
			throw new CheckMeasurException("The measurement does not exist");
		}
		Physicalcheckup physicalcheckup =null;
		try{
			physicalcheckup=physicalcheckupDao.findById(checkMeasur.getPhysicalcheckup().getPhycheId());
		}catch(NullPointerException e) {
			throw new CheckMeasurException("The physical checkups does not exist");
		}
		if(physicalcheckup==null) {
			throw new CheckMeasurException("The physical checkups does not exist");
		}
		CheckMeasur checkMeasurToUpdate = checkMeasurDao.findById(checkMeasur.getId());
		checkMeasurToUpdate.setMeasurement(measurement);
		checkMeasurToUpdate.setPhysicalcheckup(physicalcheckup);
		checkMeasurToUpdate.setMeasvalue(checkMeasur.getMeasvalue());
		checkMeasurDao.update(checkMeasur);
	}

	@Override
	public CheckMeasur getCheckMeasur(CheckMeasurPK id) {
		CheckMeasur checkMeasur = checkMeasurDao.findById(id);
		return checkMeasur;
	}

	@Transactional
	@Override
	public Iterable<CheckMeasur> findAll() {
		return checkMeasurDao.findAll();
	}

	@Transactional
	@Override
	public void deleted(CheckMeasur checkMeasur) {
		checkMeasurDao.delete(checkMeasur);
		
	}


	@Transactional
	@Override
	public boolean existById(CheckMeasurPK checkMeasurePK) {
		if(checkMeasurDao.findById(checkMeasurePK)==null) {
			return false;
		}
		return true;
		
	}
	@Override
	public List<CheckMeasur> findByPycheId(long pycheId) {
		return checkMeasurDao.findByPycheId(pycheId);
	}

	@Override
	public List<CheckMeasur> findByMeasId(long measId) {
		return checkMeasurDao.findByMeasId(measId);
	}
	@Override
	public CheckMeasur findById(CheckMeasurPK checkMeasurPK) {
		return checkMeasurDao.findById(checkMeasurPK);
	}

}
