package co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces;

import co.edu.icesi.ci.tallerfinal.back.model.Measurement;

public interface MeasurementRestController {
    public Iterable<Measurement> getMeasuremets();
    public void saveMeasurement(Measurement measurement, long insitutionId);
    public void updateMeasurement(Measurement measurement);

}
