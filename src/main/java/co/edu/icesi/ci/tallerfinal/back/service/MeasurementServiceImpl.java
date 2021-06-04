package co.edu.icesi.ci.tallerfinal.back.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import co.edu.icesi.ci.tallerfinal.back.dao.MeasurementDao;
import co.edu.icesi.ci.tallerfinal.back.exceptions.MeasurementException;
import co.edu.icesi.ci.tallerfinal.back.repositories.InstitutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.tallerfinal.back.model.Institution;
import co.edu.icesi.ci.tallerfinal.back.model.Measurement;

@Service
public class MeasurementServiceImpl implements MeasurementService {
	
	public MeasurementDao measurementDao;
	public InstitutionRepository institutionRepository;
	
	public MeasurementServiceImpl(MeasurementDao measurementDao,
			InstitutionRepository institutionRepository) {
		this.measurementDao = measurementDao;
		this.institutionRepository = institutionRepository;
	}

	@Transactional
	@Override
	public void addMeasurement(Measurement measurement, long institutionID) {
		if(measurement==null) {
			throw new MeasurementException("The measurement to be added is null");
		}
		if(measurement.getMeasDescription()==null || measurement.getMeasDescription().isEmpty()) {
			throw new MeasurementException("The measurement description is null or is empty");
		}
		if(measurement.getMeasMaxthreshold()==null || measurement.getMeasMaxthreshold().toString().isEmpty()) {
			throw new MeasurementException("The measurement max threshold is null or is empty");
		}
		if(measurement.getMeasMinthreshold()==null || measurement.getMeasMinthreshold().toString().isEmpty()) {
			throw new MeasurementException("The measurement min threshold is null or is empty");
		}
		if(measurement.getMeasName()==null || measurement.getMeasName().isEmpty()) {
			throw new MeasurementException("The measurement name  is null or is empty");
		}
		if(measurement.getMeasUnit()==null || measurement.getMeasUnit().isEmpty()) {
			throw new MeasurementException("The measurement unit  is null or is empty");
		}
		try {
			Institution institution = institutionRepository.findById(institutionID).get();
			measurement.setInstitution(institution);
		}catch(NoSuchElementException e) {
			throw new MeasurementException("The institution to be added does not exist");
		}
		measurementDao.save(measurement);
		
	}

	@Transactional
	@Override
	public void editMeasurement(Measurement measurement) {
		if(measurement==null) {
			throw new MeasurementException("The measurement to be added is null");
		}
		if(measurement.getMeasDescription()==null || measurement.getMeasDescription().isEmpty()) {
			throw new MeasurementException("The measurement description is null or is empty");
		}
		if(measurement.getMeasMaxthreshold()==null || measurement.getMeasMaxthreshold().toString().isEmpty()) {
			throw new MeasurementException("The measurement max threshold is null or is empty");
		}
		if(measurement.getMeasMinthreshold()==null || measurement.getMeasMinthreshold().toString().isEmpty()) {
			throw new MeasurementException("The measurement min threshold is null or is empty");
		}
		if(measurement.getMeasName()==null || measurement.getMeasName().isEmpty()) {
			throw new MeasurementException("The measurement name  is null or is empty");
		}
		if(measurement.getMeasUnit()==null || measurement.getMeasUnit().isEmpty()) {
			throw new MeasurementException("The measurement unit  is null or is empty");
		}
		Institution institution =null;
		try {
			institution = institutionRepository.findById(measurement.getInstitution().getInstId()).get();
			measurement.setInstitution(institution);
		}catch(NullPointerException e) {
			throw new MeasurementException("The institution to be added is null");
		}
		catch(NoSuchElementException e) {
			throw new MeasurementException("The institution to be added does not exist");
		}
		Measurement measurementToUpdate = measurementDao.findById(measurement.getMeasId());
		measurementToUpdate.setMeasDescription(measurement.getMeasDescription());
		measurementToUpdate.setMeasMaxthreshold(measurement.getMeasMaxthreshold());
		measurementToUpdate.setMeasMinthreshold(measurement.getMeasMinthreshold());
		measurementToUpdate.setMeasName(measurement.getMeasName());
		measurementToUpdate.setMeasUnit(measurement.getMeasUnit());
		measurementToUpdate.setCheckMeasurs(measurement.getCheckMeasurs());
		measurementToUpdate.setInstitution(institution);
		
		measurementDao.update(measurementToUpdate);
	}

	@Override
	public Measurement getMeasurement(long id) {
		Measurement measurement = measurementDao.findById(id);
		return measurement;

	}

	@Transactional
	@Override
	public Iterable<Measurement> findAll() {
		return measurementDao.findAll();
	}

	@Transactional
	@Override
	public void delete(Measurement measure) {
		measurementDao.delete(measure);
		
	}

	@Override
	public List<Measurement> findByValue(BigDecimal value) {
		return measurementDao.findByValue(value);
	}

	@Override
	public List<Measurement> findByDescription(String description) {
		return measurementDao.findByDescription(description);
	}

}
